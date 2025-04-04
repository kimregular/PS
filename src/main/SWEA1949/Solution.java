package main.SWEA1949;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	private void run() {
		StringBuilder answer = new StringBuilder();
		Resolver r = new Resolver();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int TEST_CASES = Integer.parseInt(br.readLine());
			for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
				Input ip = readInput(br);
				answer.append("#").append(TEST_CASE + 1).append(" ").append(r.resolve(ip.dig, ip.field)).append("\n");
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}

		System.out.println(answer);
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int dig = Integer.parseInt(st.nextToken());

		int[][] field = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		return new Input(dig, field);
	}

	private static class Input {

		final int dig;
		final int[][] field;

		public Input(int dig, int[][] field) {
			this.dig = dig;
			this.field = field;
		}
	}
}

class Resolver {

	private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	private int dig;
	private int[][] field;
	private boolean[][] visited;
	private List<int[]> tops;
	private int result;

	public int resolve(int dig, int[][] field) {
		init(dig, field);
		calc();
		return result;
	}

	private void init(int dig, int[][] field) {
		this.dig = dig;
		this.field = field;
		this.visited = new boolean[field.length][field.length];
		this.tops = new ArrayList<>();
		this.result = Integer.MIN_VALUE;

		int prevTop = 1;
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if (field[i][j] == prevTop) {
					tops.add(new int[]{i, j});
				} else if (field[i][j] > prevTop) {
					prevTop = field[i][j];
					tops = new ArrayList<>();
					tops.add(new int[]{i, j});
				}
			}
		}
	}

	private void calc() {
		for (int[] top : tops) {
			int x = top[0];
			int y = top[1];
			dfs(x, y,  field[x][y], 0, 1);
		}
	}

	private void dfs(int x, int y, int height, int digCnt, int len) {
		visited[x][y] = true;
		result = Math.max(result, len);

		for (int[] direction : DIRECTIONS) {
			int nx = x + direction[0];
			int ny = y + direction[1];

			if (isOutOfBounds(nx, ny)) continue;
			if (visited[nx][ny]) continue;

			if (height > field[nx][ny]) {
				dfs(nx, ny, field[nx][ny], digCnt, len + 1);
			} else {
				if (digCnt == 0) {
					for (int i = 1; i <= dig; i++) {
						int tempHeight = field[nx][ny] - i;
						if (tempHeight < field[x][y]) {
							dfs(nx, ny, tempHeight, digCnt + 1, len + 1);
						}
					}
				}
			}
		}
		visited[x][y] = false;
	}

	private boolean isOutOfBounds(int x, int y) {
		return x < 0 || x >= field.length || y < 0 || y >= field.length;
	}
}
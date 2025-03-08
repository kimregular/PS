package main.BJ2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Solution s = new Solution();
			Input input = readInput(br);
			System.out.println(s.solution(input.max, input.field));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	private Input readInput(BufferedReader br) throws IOException {
		int len = Integer.parseInt(br.readLine());
		int[][] result = new int[len][len];

		int max = Integer.MIN_VALUE;

		for (int i = 0; i < len; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < len; j++) {
				int value = Integer.parseInt(st.nextToken());
				max = Math.max(max, value);
				result[i][j] = value;
			}
		}

		return new Input(max, result);
	}

	private static class Input {

		final int max;
		final int[][] field;

		public Input(int max, int[][] field) {
			this.max = max;
			this.field = field;
		}
	}
}

class Solution {

	private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

	private int[][] field;
	private int result;

	public int solution(int max, int[][] field) {
		init(field);
		calc(0, max);
		return result;
	}

	private void init(int[][] field) {
		this.field = field;
		this.result = Integer.MIN_VALUE;
	}

	private void calc(int min, int max) {
		for (int level = min; level <= max; level++) {
			calc(level);
		}
	}

	private void calc(int level) {
		boolean[][] visited = new boolean[field.length][field.length];

		int safeArea = 0;
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if(visited[i][j]) continue;
				if(field[i][j] <= level) continue;
				safeArea++;
				explore(i, j, level, visited);
			}
		}
		result = Math.max(result, safeArea);
	}

	private void explore(int x, int y, int level, boolean[][] visited) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{x, y});
		visited[x][y] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int[] direction : DIRECTIONS) {
				int nx = cur[0] + direction[0];
				int ny = cur[1] + direction[1];

				if(isOutOfBounds(nx, ny)) continue;
				if(field[nx][ny] <= level) continue;
				if(visited[nx][ny]) continue;

				q.offer(new int[]{nx, ny});
				visited[nx][ny] = true;
			}
		}
	}

	private boolean isOutOfBounds(int x, int y) {
		return x < 0 || x >= field.length || y < 0 || y >= field.length;
	}
}
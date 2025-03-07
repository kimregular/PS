package main.SWEA1868;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	private void run() {

		StringBuilder result = new StringBuilder();
		Resolver r = new Resolver();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int TEST_CASES = Integer.parseInt(br.readLine());
			for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
				result.append("#").append(TEST_CASE + 1).append(" ").append(r.resolve(readInput(br))).append("\n");
			}

			System.out.println(result.toString().trim());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private char[][] readInput(BufferedReader br) throws IOException {
		int len = Integer.parseInt(br.readLine());
		char[][] field = new char[len][len];

		for (int i = 0; i < len; i++) {
			field[i] = br.readLine().toCharArray();
		}
		return field;
	}
}

class Resolver {

	private static final int[][] DIRECTIONS = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};

	private char[][] field;
	private boolean[][] visited;
	private int result;

	public int resolve(char[][] field) {
		init(field);
		calc();
		return result;
	}

	private void init(char[][] field) {
		this.field = field;
		this.visited = new boolean[field.length][field.length];
		this.result = 0;
	}

	private void calc() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if(field[i][j] == '*') continue;
				if(visited[i][j]) continue;
				if(isMineAround(i, j)) continue;
				explore(i, j);
				result++;
			}
		}

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if(field[i][j] == '*') continue;
				if(visited[i][j]) continue;
				explore(i, j);
				result++;
			}
		}
	}

	private boolean isMineAround(int x, int y) {
		for (int[] direction : DIRECTIONS) {
			int nx = x + direction[0];
			int ny = y + direction[1];

			if(isOutOfBounds(nx, ny)) continue;
			if(field[nx][ny] == '*') return true;
		}
		return false;
	}

	private void explore(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{x, y});
		visited[x][y] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			if(isMineAround(cur[0], cur[1])) continue;

			for (int[] direction : DIRECTIONS) {
				int nx = cur[0] + direction[0];
				int ny = cur[1] + direction[1];

				if(isOutOfBounds(nx, ny)) continue;
				if(field[nx][ny] == '*') continue;
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
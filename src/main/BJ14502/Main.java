package main.BJ14502;

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
			System.out.println(s.solution(readInput(br)));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private int[][] readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		int[][] board = new int[height][width];

		for (int i = 0; i < height; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < width; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		return board;
	}
}

class Solution {

	private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	private int[][] field;
	private int result;
	private Queue<int[]> viruses;


	public int solution(int[][] field) {
		init(field);
		calc(3, 0, 0, 0);
		return result;
	}

	private void init(int[][] field) {
		this.field = field;
		this.result = Integer.MIN_VALUE;
		this.viruses = new ArrayDeque<>();

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j] != 2) continue;
				viruses.offer(new int[]{i, j});
			}
		}
	}

	private void calc(int limit, int select, int x, int y) {
		if (select == limit) {
			measure();
			return;
		}
		for (int i = x; i < field.length; i++) {
			for (int j = y; j < field[i].length; j++) {
				if (field[i][j] != 0) continue;
				field[i][j] = 1;
				calc(limit, select + 1, x, y);
				field[i][j] = 0;
			}
		}
	}

	private void measure() {
		int[][] map = copyField();
		Queue<int[]> q = new ArrayDeque<>(viruses);

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int[] direction : DIRECTIONS) {
				int nx = cur[0] + direction[0];
				int ny = cur[1] + direction[1];

				if (isOutOfBounds(nx, ny)) continue;
				if (map[nx][ny] != 0) continue;

				map[nx][ny] = 2;
				q.offer(new int[]{nx, ny});
			}
		}

		int safeArea = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 0) safeArea++;
			}
		}
		result = Math.max(result, safeArea);
	}

	private boolean isOutOfBounds(int x, int y) {
		return x < 0 || x >= field.length || y < 0 || y >= field[x].length;
	}

	private int[][] copyField() {
		int[][] result = new int[field.length][field[0].length];
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[0].length; j++) {
				result[i][j] = field[i][j];
			}
		}
		return result;
	}
}
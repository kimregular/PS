package main.BJ14940;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
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
		int[][] result = new int[height][width];

		for (int i = 0; i < height; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < width; j++) {
				result[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		return result;
	}
}

class Solution {

	private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

	private int[][] field;
	private boolean[][] visited;
	private int[] start;

	public String solution(int[][] field) {
		init(field);
		calc();
		return getAnswer();
	}

	private void init(int[][] field) {
		this.field = field;
		this.visited = new boolean[field.length][field[0].length];

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if(field[i][j] != 2) continue;
				start = new int[]{i, j};
				visited[i][j] = true;
				return;
			}
		}
	}

	private void calc() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{start[0], start[1], 0});

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			field[cur[0]][cur[1]] = cur[2];

			for (int[] direction : DIRECTIONS) {
				int nx = cur[0] + direction[0];
				int ny = cur[1] + direction[1];

				if(isOutOfBounds(nx, ny)) continue;
				if(visited[nx][ny]) continue;
				if(field[nx][ny] == 0) continue;

				q.offer(new int[]{nx, ny, cur[2] + 1});
				visited[nx][ny] = true;
			}
		}

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if(visited[i][j]) continue;
				if(field[i][j] == 0) continue;
				field[i][j] = -1;
			}
		}
	}

	private boolean isOutOfBounds(int x, int y) {
		return x < 0 || x >= field.length || y < 0 || y >= field[x].length;
	}

	private String getAnswer() {
		StringBuilder answer = new StringBuilder();

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				answer.append(field[i][j]).append(" ");
			}
			answer.append("\n");
		}

		return answer.toString();
	}
}
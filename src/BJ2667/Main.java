package src.BJ2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

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

	private char[][] readInput(BufferedReader br) throws IOException {
		int len = Integer.parseInt(br.readLine());
		char[][] result = new char[len][len];
		for (int i = 0; i < len; i++) {
			result[i] = br.readLine().toCharArray();
		}
		return result;
	}
}

class Solution {

	private static int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	PriorityQueue<Integer> pq = new PriorityQueue<>();

	private char[][] field;
	private boolean[][] isVisited;

	public String solution(char[][] field) {
		init(field);

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if (field[i][j] == '1' && !isVisited[i][j]) {
					pq.add(explore(i, j));
				}
			}
		}

		return getAnswer();
	}

	private void init(char[][] field) {
		this.field = field;
		this.isVisited = new boolean[field.length][field.length];
	}

	private int explore(int x, int y) {
		int width = 1;
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{x, y});
		isVisited[x][y] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int[] direction : DIRECTIONS) {
				int nx = cur[0] + direction[0];
				int ny = cur[1] + direction[1];

				if(isOutOfBound(nx, ny)) continue;
				if(field[nx][ny] == '0') continue;
				if(isVisited[nx][ny]) continue;

				isVisited[nx][ny] = true;
				width++;
				q.offer(new int[]{nx, ny});
			}
		}
		return width;
	}

	private boolean isOutOfBound(int x, int y) {
		return x < 0 || x >= field.length || y < 0 || y >= field.length;
	}

	private String getAnswer() {
		StringBuilder result = new StringBuilder();
		result.append(pq.size()).append("\n");
		while (!pq.isEmpty()) {
			result.append(pq.poll()).append("\n");
		}
		return result.toString();
	}
}
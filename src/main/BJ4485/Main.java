package main.BJ4485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		Solution s = new Solution();
		StringBuilder result = new StringBuilder();

		int TEST_CASE = 1;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int len;

			while ((len = Integer.parseInt(br.readLine())) != 0) {
				int[][] input = readInput(br, len);
				result.append("Problem ").append(TEST_CASE++).append(": ").append(s.solution(input)).append("\n");
			}

		} catch (IOException e) {
			throw new RuntimeException();
		}

		System.out.println(result);
	}

	private int[][] readInput(BufferedReader br, int len) throws IOException {
		int[][] result = new int[len][len];

		for (int i = 0; i < len; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < len; j++) {
				result[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		return result;
	}
}

class Solution {

	private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

	private int[][] field;
	private int[][] cost;
	private PriorityQueue<int[]> q;

	public int solution(int[][] field) {
		init(field);
		return calc();
	}

	private void init(int[][] field) {
		this.field = field;
		this.cost = new int[field.length][field.length];
		this.q = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
		for (int i = 0; i < cost.length; i++) {
			for (int j = 0; j < cost.length; j++) {
				cost[i][j] = Integer.MAX_VALUE;
			}
		}
	}

	private int calc() {
		q.offer(new int[]{0, 0, field[0][0]});
		cost[0][0] = field[0][0];

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			if (cur[0] == field.length - 1 && cur[1] == field.length - 1) {
				return cur[2];
			}

			for (int[] d : DIRECTIONS) {
				int nx = cur[0] + d[0];
				int ny = cur[1] + d[1];

				if(out(nx, ny)) continue;

				int nextCost = cur[2] + field[nx][ny];
				if (nextCost < cost[nx][ny]) {
					cost[nx][ny] = nextCost;
					q.offer(new int[]{nx, ny, nextCost});
				}
			}
		}

		return -1;
	}

	private boolean out(int x, int y) {
		return x < 0 || x >= field.length || y < 0 || y >= field.length;
	}
}
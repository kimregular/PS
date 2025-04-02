package main.SWE2206;

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
		Solution s = new Solution();
		System.out.println(s.solution(readInput()));
	}

	private char[][] readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int height = Integer.parseInt(st.nextToken());
			int width = Integer.parseInt(st.nextToken());

			char[][] field = new char[height][width];

			for (int i = 0; i < height; i++) {
				field[i] = br.readLine().toCharArray();
			}

			return field;
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
}

class Solution {

	private static final int CAN_DEMOLISH = 1;
	private static final int CANNOT_DEMOLISH = 0;

	private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

	private char[][] field;
	private boolean[][][] visited;

	public int solution(char[][] field) {
		init(field);
		return explore();
	}

	private void init(char[][] field) {
		this.field = field;
		this.visited = new boolean[field.length][field[0].length][2];
	}

	private int explore() {
		// {x, y, distance, demolishChance}
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[]{0, 0, 1, CAN_DEMOLISH});
		visited[0][0][CAN_DEMOLISH] = true;

		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int x = current[0];
			int y = current[1];
			int distance = current[2];
			int demolishChance = current[3];

			if (x == field.length - 1 && y == field[0].length - 1) {
				return distance;
			}

			for (int[] dir : DIRECTIONS) {
				int nx = x + dir[0];
				int ny = y + dir[1];

				if (isOutOfBounds(nx, ny)) continue;

				if (field[nx][ny] == '1') {  // 벽인 경우
					if (demolishChance == CAN_DEMOLISH && !visited[nx][ny][CANNOT_DEMOLISH]) {
						queue.offer(new int[]{nx, ny, distance + 1, CANNOT_DEMOLISH});
						visited[nx][ny][CANNOT_DEMOLISH] = true;
					}
				} else {  // 빈 공간인 경우
					if (!visited[nx][ny][demolishChance]) {
						queue.offer(new int[]{nx, ny, distance + 1, demolishChance});
						visited[nx][ny][demolishChance] = true;
					}
				}
			}
		}
		return -1;
	}

	private boolean isOutOfBounds(int x, int y) {
		return x < 0 || x >= field.length || y < 0 || y >= field[x].length;
	}
}

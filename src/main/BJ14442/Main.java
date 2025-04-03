package main.BJ14442;

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
		Input ip = readInput();
		System.out.println(s.solution(ip.demolish, ip.field));
	}

	private Input readInput() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int height = Integer.parseInt(st.nextToken());
			int width = Integer.parseInt(st.nextToken());
			int demolish = Integer.parseInt(st.nextToken());

			char[][] field = new char[height][width];

			for (int i = 0; i < height; i++) {
				field[i] = br.readLine().toCharArray();
			}

			return new Input(demolish, field);

		}catch (IOException e) {
			throw new RuntimeException();
		}
	}

	private static class Input {

		final int demolish;
		final char[][] field;

		public Input(int demolish, char[][] field) {
			this.demolish = demolish;
			this.field = field;
		}
	}
}

class Solution {

	private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	private char[][] field;
	private boolean[][][] visited;

	public int solution(int demolish, char[][] field) {
		init(demolish, field);
		return explore(demolish);
	}

	private void init(int demoish, char[][] field) {
		this.field = field;
		this.visited = new boolean[field.length][field[0].length][demoish + 1];
	}

	private int explore(int demolish) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{0, 0, 1, demolish});
		visited[0][0][demolish] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			if (cur[0] == field.length - 1 && cur[1] == field[0].length - 1) {
				return cur[2];
			}

			for (int[] direction : DIRECTIONS) {
				int nx = cur[0] + direction[0];
				int ny = cur[1] + direction[1];

				if(isOutOfBounds(nx, ny)) continue;
				if (field[nx][ny] == '1') {
					if (cur[3] - 1 >= 0 && !visited[nx][ny][cur[3] - 1]) {
						q.offer(new int[]{nx, ny, cur[2] + 1, cur[3] - 1});
						visited[nx][ny][cur[3] - 1] = true;
					}
				} else {
					if (!visited[nx][ny][cur[3]]) {
						q.offer(new int[]{nx, ny, cur[2] + 1, cur[3]});
						visited[nx][ny][cur[3]] = true;
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
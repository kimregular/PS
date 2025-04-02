package main.SWE1600;

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
		System.out.println(s.solution(ip.horse, ip.field));
	}

	private Input readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int horse = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int width = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			int[][] field = new int[height][width];

			for (int i = 0; i < height; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < width; j++) {
					field[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			return new Input(horse, field);
		}catch(IOException e) {
			throw new RuntimeException();
		}
	}

	private static class Input {

		final int horse;
		final int[][] field;

		public Input(int horse, int[][] field) {
			this.horse = horse;
			this.field = field;
		}
	}
}

class Solution {

	private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	private static final int[][] HORSE_DIRECTIONS = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};

	private int[][] field;
	private boolean[][][] visited;

	public int solution(int horse, int[][] field) {
		init(horse, field);
		return explore(horse);
	}

	private void init(int horse, int[][] field) {
		this.field = field;
		this.visited = new boolean[field.length][field[0].length][horse + 1];
	}

	private int explore(int horse) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{0, 0, 0, horse});
		visited[0][0][horse] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			if (cur[0] == field.length - 1 && cur[1] == field[0].length - 1) {
				return cur[2];
			}

			move(cur, q);
			horseMove(cur, q);
		}
		return -1;
	}

	private void move(int[] cur, Queue<int[]> q) {
		for (int[] direction : DIRECTIONS) {
			int nx = cur[0] + direction[0];
			int ny = cur[1] + direction[1];

			if(isOutOfBounds(nx, ny)) continue;
			if(field[nx][ny] == 1) continue;
			if(visited[nx][ny][cur[3]]) continue;
			q.offer(new int[]{nx, ny, cur[2] + 1, cur[3]});
			visited[nx][ny][cur[3]] = true;
		}
	}

	private void horseMove(int[] cur, Queue<int[]> q) {
		if(cur[3] == 0) return;
		for (int[] horseDirection : HORSE_DIRECTIONS) {
			int nx = cur[0] + horseDirection[0];
			int ny = cur[1] + horseDirection[1];

			if(isOutOfBounds(nx, ny)) continue;
			if(field[nx][ny] == 1) continue;
			if(visited[nx][ny][cur[3] - 1]) continue;
			q.offer(new int[]{nx, ny, cur[2] + 1, cur[3] - 1});
			visited[nx][ny][cur[3] - 1] = true;
		}
	}

	private boolean isOutOfBounds(int x, int y) {
		return x < 0 || x >= field.length || y < 0 || y >= field[x].length;
	}
}


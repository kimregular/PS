package main.BJ2636;

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

	private int[][] readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int height = Integer.parseInt(st.nextToken());
			int width = Integer.parseInt(st.nextToken());
			int[][] field = new int[height][width];

			for (int i = 0; i < height; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < width; j++) {
					field[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			return field;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

class Solution {

	public String solution(int[][] input) {

		Field field = new Field(input);

		int hours = 0;
		int numOfCheese = field.getCheeseCount();
		while (field.hasCheese()) {
			field.meltCheese();
			hours++;
			if(field.hasCheese()) numOfCheese = field.getCheeseCount();
		}

		return hours + "\n" + numOfCheese;
	}

}

class Field {
	private static int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	private static int CHEESE = 1;
	private static int OUTSIDE = 0;

	private int[][] field;
	private boolean[][] outside;

	private Queue<int[]> cheeseQ;

	public Field(int[][] field) {
		this.field = field;
		this.cheeseQ = countCheese();
	}

	public boolean hasCheese() {
		return !cheeseQ.isEmpty();
	}

	public int getCheeseCount() {
		return cheeseQ.size();
	}

	public void meltCheese() {
		Queue<int[]> meltQ = new ArrayDeque<>();
		this.outside = checkOutside();
		this.cheeseQ = countCheese();

		int len = cheeseQ.size();
		for (int i = 0; i < len; i++) {
			int[] cur = cheeseQ.poll();

			if(isOutsideAround(cur)) meltQ.offer(cur);
			else cheeseQ.offer(cur);
		}

		for (int[] meltTarget : meltQ) {
			int x = meltTarget[0];
			int y = meltTarget[1];
			field[x][y] = 0;
		}
	}

	private boolean isOutsideAround(int[] cheese) {
		for (int[] direction : DIRECTIONS) {
			int nx = cheese[0] + direction[0];
			int ny = cheese[1] + direction[1];

			if(outOfBounds(nx ,ny)) continue;
			if(field[nx][ny] == 1) continue;
			if(!outside[nx][ny]) continue;
			return true;
		}
		return false;
	}

	private boolean[][] checkOutside() {
		boolean[][] visited = new boolean[field.length][field[0].length];
		explore(0, 0, OUTSIDE, visited);
		return visited;
	}

	private Queue<int[]> countCheese() {
		Queue<int[]> q = new ArrayDeque<>();

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if(field[i][j] == CHEESE) q.offer(new int[]{i, j});
			}
		}
		return q;
	}

	private void explore(int x, int y, int target, boolean[][] visited) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{x, y});
		visited[x][y] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int[] direction : DIRECTIONS) {
				int nx = cur[0] + direction[0];
				int ny = cur[1] + direction[1];

				if (outOfBounds(nx, ny)) continue;
				if (field[nx][ny] != target) continue;
				if (visited[nx][ny]) continue;

				visited[nx][ny] = true;
				q.offer(new int[]{nx, ny});
			}
		}
	}

	private boolean outOfBounds(int x, int y) {
		return x < 0 || x >= field.length || y < 0 || y >= field[x].length;
	}
}
package main.BJ1926;

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
			int[][] result = new int[height][width];

			for (int i = 0; i < height; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < width; j++) {
					result[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			return result;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

class Solution {

	private static int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
	private static int UNCOLORED = 0;

	private int[][] picture;
	private boolean[][] checked;
	private int numOfPictures;
	private int maxWidth;

	public String solution(int[][] picture) {
		init(picture);
		calc();
		return numOfPictures + "\n" + maxWidth;
	}

	private void init(int[][] picture) {
		this.picture = picture;
		this.checked = new boolean[picture.length][picture[0].length];
		this.numOfPictures = 0;
		this.maxWidth = 0;
	}

	private void calc() {

		for (int i = 0; i < picture.length; i++) {
			for (int j = 0; j < picture[i].length; j++) {
				if(picture[i][j] == UNCOLORED) continue;
				if(checked[i][j]) continue;
				numOfPictures++;
				maxWidth = Math.max(maxWidth, explore(i, j));
			}
		}
	}

	private int explore(int x, int y) {
		int result = 1;
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{x, y});
		checked[x][y] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int[] direction : DIRECTIONS) {
				int nx = cur[0] + direction[0];
				int ny = cur[1] + direction[1];

				if(outOfBounds(nx, ny)) continue;
				if(picture[nx][ny] == UNCOLORED) continue;
				if(checked[nx][ny]) continue;

				checked[nx][ny] = true;
				q.offer(new int[]{nx, ny});
				result++;
			}
		}

		return result;
	}

	private boolean outOfBounds(int x, int y) {
		return x < 0 || x >= picture.length || y < 0 || y >= picture[x].length;
	}
}
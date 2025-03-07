package main.SWE2105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	private void run() {

		Resolver r = new Resolver();
		StringBuilder result = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int TEST_CASES = Integer.parseInt(br.readLine());
			for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
				result.append("#").append(TEST_CASE + 1).append(" ").append(r.resolver(readInput(br))).append("\n");
			}
			System.out.println(result.toString().trim());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private int[][] readInput(BufferedReader br) throws IOException {
		int len = Integer.parseInt(br.readLine());
		int[][] field = new int[len][len];

		for (int i = 0; i < len; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < len; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		return field;
	}
}

class Resolver {

	private static final int[][] DIRECTIONS = {{1, -1}, {1, 1}, {-1, 1}, {-1, -1}};

	private int[][] field;
	private boolean[][] visited;
	private int[] unique;
	private int answer;

	public int resolver(int[][] field) {
		init(field);
		calc();
		return answer;
	}

	private void init(int[][] field) {
		this.field = field;
		this.visited = new boolean[field.length][field.length];
		this.unique = new int[101];
		this.answer = -1;
	}

	private void calc() {
		for (int startX = 0; startX < field.length - 1; startX++) {
			for (int startY = 1; startY < field.length - 1; startY++) {
				explore(startX, startY, startX, startY, 0, 0);
			}
		}
	}

	private void explore(int startX, int startY, int x, int y, int direction, int depth) {
		if (direction == 4 && x == startX && y == startY && depth >= 4) {
			if (answer < depth) {
				answer = depth;
			}
			return;
		}

		if(direction >= 4) return;

		int nx = x + DIRECTIONS[direction][0];
		int ny = y + DIRECTIONS[direction][1];

		if(isOutOfBounds(nx, ny)) return;
		if(visited[nx][ny]) return;
		if(unique[field[nx][ny]] != 0) return;

		visited[nx][ny] = true;
		unique[field[nx][ny]]++;
		explore(startX, startY, nx, ny, direction, depth + 1);
		explore(startX, startY, nx, ny, direction + 1, depth + 1);
		visited[nx][ny] = false;
		unique[field[nx][ny]]--;
	}

	private boolean isOutOfBounds(int x, int y) {
		return x < 0 || x >= field.length || y < 0 || y >= field.length;
	}
}


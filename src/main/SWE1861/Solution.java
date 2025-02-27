package main.SWE1861;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	private void run() {

		StringBuilder result = new StringBuilder();
		Resolver r = new Resolver();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int TEST_CASES = Integer.parseInt(br.readLine());
			for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
				result.append("#").append(TEST_CASE + 1).append(" ").append(r.resolve(readInput(br))).append("\n");
			}

			System.out.println(result.toString().trim());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private int[][] readInput(BufferedReader br) throws IOException {
		int len = Integer.parseInt(br.readLine());
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

class Resolver {

	private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	private int[][] field;
	private int[] memo;

	public String resolve(int[][] field) {
		init(field);
		calc();
		return getAnswer();
	}

	private void init(int[][] field) {
		this.field = field;
		this.memo = new int[field.length * field.length + 1];
	}

	private void calc() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				explore(field[i][j], 1, i, j);
			}
		}
	}

	private void explore(int startNum, int depth, int x, int y) {
		if (memo[startNum] < depth) {
			memo[startNum] = depth;
		}

		for (int[] direction : DIRECTIONS) {
			int nx = x + direction[0];
			int ny = y + direction[1];

			if(isOutOfBounds(nx, ny)) continue;
			if(field[nx][ny] - 1 != field[x][y]) continue;

			explore(startNum, depth + 1, nx, ny);
		}
	}

	private boolean isOutOfBounds(int x, int y) {
		return x < 0 || x >= field.length || y < 0 || y >= field.length;
	}

	private String getAnswer() {
		int prevRoom = 0;
		int prevMax = 0;
		for (int i = 1; i < memo.length; i++) {
			if (prevMax < memo[i]) {
				prevRoom = i;
				prevMax = memo[i];
			}
		}
		return prevRoom + " " + prevMax;
	}
}
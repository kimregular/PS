package main.SWE2001;

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
				Input ip = readInput(br);
				result.append("#").append(TEST_CASE + 1).append(" ").append(r.resolve(ip.area, ip.field)).append("\n");
			}

			System.out.println(result.toString().trim());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int len = Integer.parseInt(st.nextToken());
		int area = Integer.parseInt(st.nextToken());

		int[][] field = new int[len][len];

		for (int i = 0; i < len; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < len; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		return new Input(area, field);
	}

	private static class Input {

		final int area;
		final int[][] field;

		public Input(int area, int[][] field) {
			this.area = area;
			this.field = field;
		}
	}
}

class Resolver {

	private int result;

	public int resolve(int area, int[][] field) {
		result = Integer.MIN_VALUE;

		int len = field.length;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {

				int score = 0;

				for (int k = i; k < i + area; k++) {
					for (int l = j; l < j + area; l++) {
						if(k >= len || l >= len) continue;
						score += field[k][l];
					}
				}

				if (result < score) {
					result = score;
				}
			}
		}
		return result;
	}
}
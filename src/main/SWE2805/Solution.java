package main.SWE2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
			char[] tokens = br.readLine().toCharArray();
			for (int j = 0; j < len; j++) {
				result[i][j] = (tokens[j] - '0');
			}
		}
		return result;
	}
}

class Resolver {

	public int resolve(int[][] field) {
		int result = 0;
		int center = field.length / 2;

		for (int i = 0; i < field.length; i++) {
			int start = Math.abs(center - i);
			int end = field.length - start;

			for (int j = start; j < end; j++) {
				result += field[i][j];
			}
		}

		return result;
	}
}
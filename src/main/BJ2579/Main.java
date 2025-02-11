package main.BJ2579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Solution s = new Solution();
			System.out.println(s.solution(readInput(br)));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private int[] readInput(BufferedReader br) throws IOException {
		int len = Integer.parseInt(br.readLine());
		int[] result = new int[len];
		for (int i = 0; i < len; i++) {
			result[i] = Integer.parseInt(br.readLine());
		}
		return result;
	}
}

class Solution {

	private static final int LIMIT = 301;
	private int[] dp;

	public int solution(int[] input) {
		init(input);
		return dp[input.length];
	}

	private void init(int[] input) {
		int[] stairs = new int[LIMIT];
		this.dp = new int[LIMIT];
		for (int i = 0; i < input.length; i++) {
			stairs[i + 1] = input[i];
		}
		dp[1] = stairs[1];
		dp[2] = stairs[1] + stairs[2];
		dp[3] = Math.max(stairs[1], stairs[2]) + stairs[3];

		for (int i = 4; i < LIMIT; i++) {
			dp[i] = Math.max(dp[i - 3] + stairs[i - 1], dp[i - 2]) + stairs[i];
		}
	}
}
package main.BJ12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		Solution s = new Solution();
		Input ip = readInput();
		System.out.println(s.solution(ip.limit, ip.things));
	}

	private Input readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int numOfThings = Integer.parseInt(st.nextToken());
			int limit = Integer.parseInt(st.nextToken());

			int[][] things = new int[numOfThings][2];

			for (int i = 0; i < numOfThings; i++) {
				st = new StringTokenizer(br.readLine());
				things[i][0] = Integer.parseInt(st.nextToken());
				things[i][1] = Integer.parseInt(st.nextToken());
			}

			return new Input(limit, things);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	private static class Input {

		final int limit;
		final int[][] things;

		public Input(int limit, int[][] things) {
			this.limit = limit;
			this.things = things;
		}
	}
}

class Solution {

	private int[][] things;
	private int[][] dp;

	public int solution(int limit, int[][] things) {
		init(limit, things);
		return calc(things.length - 1, limit);
	}

	private void init(int limit, int[][] things) {
		this.things = things;
		this.dp = new int[things.length + 1][limit + 1];
	}

	private int calc(int idx, int weight) {
		if(idx < 0 || weight < 0) return 0;
		if(dp[idx][weight] != 0) return dp[idx][weight];

		if (things[idx][0] > weight) {
			dp[idx][weight] = calc(idx - 1, weight);
		} else {
			dp[idx][weight] = Math.max(calc(idx - 1, weight), things[idx][1] + calc(idx - 1, weight - things[idx][0]));
		}
		return dp[idx][weight];
	}
}

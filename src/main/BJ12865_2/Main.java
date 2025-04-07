package main.BJ12865_2;

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
	private int[] dp;

	public int solution(int limit, int[][] things) {
		init(limit, things);
		return calc(limit);
	}

	private void init(int limit, int[][] things) {
		this.things = things;
		this.dp = new int[limit + 1];
	}

	private int calc(int weight) {
		for (int i = 0; i < things.length; i++) {
			for (int j = weight; j >= things[i][0]; j--) {
				dp[j] = Math.max(dp[j], dp[j - things[i][0]] + things[i][1]);
			}
		}
		return dp[weight];
	}
}
package main.BJ14728;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		Solution s = new Solution();
		Input ip = readInput();
		System.out.println(s.solution(ip.weight, ip.things));
	}

	private Input readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			int[][] things = new int[len][2];
			for (int i = 0; i < len; i++) {
				st = new StringTokenizer(br.readLine());
				things[i][0] = Integer.parseInt(st.nextToken());
				things[i][1] = Integer.parseInt(st.nextToken());
			}
			return new Input(weight, things);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	private static class Input {

		final int weight;
		final int[][] things;

		public Input(int weight, int[][] things) {
			this.weight = weight;
			this.things = things;
		}
	}
}

class Solution {

	private int[][] things;
	private int[][] dp;

	public int solution(int weight, int[][] things) {
		init(weight, things);
		return calc(things.length - 1, weight);
	}

	private void init(int weight, int[][] things) {
		this.things = things;
		this.dp = new int[things.length + 1][weight + 1];
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
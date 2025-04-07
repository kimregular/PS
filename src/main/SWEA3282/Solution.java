package main.SWEA3282;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	private void run() {
		StringBuilder answer = new StringBuilder();
		Resolver r = new Resolver();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int TEST_CASES = Integer.parseInt(br.readLine());
			for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
				Input ip = readInput(br);
				answer.append("#").append(TEST_CASE + 1).append(" ").append(r.resolve(ip.things, ip.limit)).append("\n");
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}

		System.out.println(answer);
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numOfThings = Integer.parseInt(st.nextToken());
		int limit = Integer.parseInt(st.nextToken());

		int[][] things = new int[numOfThings][2];

		for (int i = 0; i < numOfThings; i++) {
			st = new StringTokenizer(br.readLine());
			things[i][0] = Integer.parseInt(st.nextToken());
			things[i][1] = Integer.parseInt(st.nextToken());
		}

		return new Input(things, limit);
	}

	private static class Input {

		final int[][] things;
		final int limit;

		public Input(int[][] things, int limit) {
			this.things = things;
			this.limit = limit;
		}
	}
}

class Resolver {

	private int[][] things;
	private int[] dp;

	public int resolve(int[][] things, int limit) {
		init(things, limit);
		return calc(limit);
	}

	private void init(int[][] things, int limit) {
		this.things = things;
		this.dp = new int[limit + 1];
	}

	private int calc(int limit) {
		for (int i = 0; i < things.length; i++) {
			for (int j = limit; j >= things[i][0]; j--) {
				dp[j] = Math.max(dp[j], dp[j - things[i][0]] + things[i][1]);
			}
		}
		return dp[limit];
	}
}
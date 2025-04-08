package main.BJ11053;

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
		System.out.println(s.solution(readInput()));
	}

	private int[] readInput() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int len = Integer.parseInt(br.readLine());
			int[] result = new int[len];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < len; i++) {
				result[i] = Integer.parseInt(st.nextToken());
			}
			return result;
		}catch(IOException e) {
			throw new RuntimeException();
		}
	}
}

class Solution {

	private int[] field;
	private int[] dp;

	public int solution(int[] field) {
		init(field);
		return calc();
	}

	private void init(int[] field) {
		this.field = field;
		this.dp = new int[field.length];
	}

	private int calc() {
		int result = 0;
		for (int i = 0; i < field.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (field[j] < field[i] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
				}
			}
			result = Math.max(result, dp[i]);
		}
		return result;
	}
}
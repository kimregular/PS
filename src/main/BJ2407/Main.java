package main.BJ2407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
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

			StringTokenizer st = new StringTokenizer(br.readLine());
			return new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

		}catch(IOException e) {
			throw new RuntimeException();
		}
	}
}

class Solution {

	private BigInteger[][] dp;

	public BigInteger solution(int[] input) {
		init(input);
		return calc(input[0], input[1]);
	}

	private void init(int[] input) {
		this.dp = new BigInteger[input[0] + 1][input[1] + 1];
	}

	private BigInteger calc(int n, int r) {
		if(r == 0 || n == r) return BigInteger.ONE;
		if(dp[n][r] != null) return dp[n][r];
		dp[n][r] = calc(n - 1, r -1).add(calc(n - 1, r));
		return dp[n][r];
	}
}

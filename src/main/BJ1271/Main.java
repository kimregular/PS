package main.BJ1271;

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
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Solution s = new Solution();
			System.out.println(s.solution(readInput(br)));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private BigInteger[] readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		return new BigInteger[]{new BigInteger(st.nextToken()), new BigInteger(st.nextToken())};
	}
}

class Solution {

	public String solution(BigInteger[] input) {
		StringBuilder result = new StringBuilder();

		BigInteger divide = input[0].divide(input[1]);
		BigInteger rest = input[0].remainder(input[1]);

		return result.append(divide).append("\n").append(rest).toString();
	}
}
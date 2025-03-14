package main.BJ2420;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	private long[] readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		return new long[]{Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken())};
	}
}

class Solution {

	public long solution(long[] input) {
		return Math.abs(input[0] - input[1]);
	}
}
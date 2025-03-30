package main.BJ15963;

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

	private long[] readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());

			return new long[]{a, b};

		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
}

class Solution {

	public int solution(long[] input) {
		return input[0] == input[1] ? 1 : 0;
	}
}
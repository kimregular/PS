package main.BJ5522;

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
		int len = 5;
		int[] result = new int[len];
		for (int i = 0; i < len; i++) {
			result[i] = Integer.parseInt(br.readLine());
		}
		return result;
	}
}

class Solution {

	public int solution(int[] scores) {
		int result = 0;
		for (int score : scores) {
			result += score;
		}
		return result;
	}
}
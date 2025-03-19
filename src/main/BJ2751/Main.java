package main.BJ2751;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {

		Solution s = new Solution();
		System.out.println(s.solution(readInput()));
	}

	private int[] readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int len = Integer.parseInt(br.readLine());
			int[] result = new int[len];
			for (int i = 0; i < len; i++) {
				result[i] = Integer.parseInt(br.readLine());
			}
			return result;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

class Solution {

	public String solution(int[] targets) {
		Arrays.sort(targets);

		StringBuilder result = new StringBuilder();
		for (int target : targets) {
			result.append(target).append("\n");
		}
		return result.toString();
	}
}
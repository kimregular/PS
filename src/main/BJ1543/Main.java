package main.BJ1543;

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
			System.out.println(s.solution(readInput(br), readInput(br)));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String readInput(BufferedReader br) throws IOException {
		return br.readLine();
	}
}

class Solution {

	public int solution(String target, String word) {
		int result = 0;

		while (target.contains(word)) {
			target = target.replaceFirst(word, "_");
			result++;
		}
		return result;
	}
}
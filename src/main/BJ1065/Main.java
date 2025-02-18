package main.BJ1065;

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

	private int readInput(BufferedReader br) throws IOException {
		return Integer.parseInt(br.readLine());
	}
}

class Solution {

	public int solution(int target) {
		if (target < 10) return target;

		int result = 9;
		for (int i = 10; i <= target; i++) {
			if (valid(String.valueOf(i))) result++;
		}
		return result;
	}

	private boolean valid(String target) {
		char prev = target.charAt(1);
		int differ = prev - target.charAt(0);

		for (int i = 2; i < target.length(); i++) {
			char temp = target.charAt(i);
			if (temp - prev != differ) return false;
			prev = temp;
		}
		return true;
	}
}
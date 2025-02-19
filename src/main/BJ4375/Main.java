package main.BJ4375;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {

		StringBuilder result = new StringBuilder();
		Solution s = new Solution();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			String input = "";

			while ((input = br.readLine()) != null) {
				result.append(s.solution(Integer.parseInt(input))).append("\n");
			}

			System.out.println(result.toString().trim());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

class Solution {

	public long solution(int target) {
		long num = 1;
		long length = 1;

		while (num % target != 0) {
			num = (num * 10 + 1) % target;
			length++;
		}
		return length;
	}
}
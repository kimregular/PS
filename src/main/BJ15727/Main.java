package main.BJ15727;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		Solution s = new Solution();
		System.out.println(s.solution(readInput()));
	}

	private int readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			return Integer.parseInt(br.readLine());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

class Solution {

	public int solution(int dist) {
		int result = 0;

		result += dist / 5;

		if(dist % 5 != 0) result++;

		return result;
	}
}
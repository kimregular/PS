package main.BJ2743;

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

	private String readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			return br.readLine();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

class Solution {

	public int solution(String target) {
		return target.length();
	}
}
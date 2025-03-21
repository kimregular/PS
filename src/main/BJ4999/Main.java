package main.BJ4999;

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

	private String[] readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			String speak = br.readLine();
			String target = br.readLine();

			return new String[]{speak, target};

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

class Solution {

	public String solution(String[] target) {
		return target[0].length() >= target[1].length() ? "go" : "no";
	}
}
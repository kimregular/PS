package main.BJ10989;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	public void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Solution s = new Solution();
			System.out.println(s.solution(readInput(br)));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private int[] readInput(BufferedReader br) throws IOException {
		int len = Integer.parseInt(br.readLine());
		int[] result = new int[len];
		for (int i = 0; i < len; i++) {
			result[i] = Integer.parseInt(br.readLine());
		}
		return result;
	}
}

class Solution {

	private static final int LIMIT = 10_001;

	private int[] field;

	public String solution(int[] targets) {
		init(targets);

		StringBuilder answer = new StringBuilder();

		for (int i = 0; i < LIMIT; i++) {
			if(field[i] == 0) continue;
			for (int j = 0; j < field[i]; j++) {
				answer.append(i).append("\n");
			}
		}
		return answer.toString();
	}

	private void init(int[] targets) {
		this.field = new int[LIMIT];
		for (int target : targets) {
			field[target]++;
		}
	}
}
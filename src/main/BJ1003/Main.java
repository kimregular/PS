package main.BJ1003;

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
			s.solution(readInput(br));
			System.out.println(s.getAnswer());

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

// 규칙
// 0 -> 1, 0
// 1 -> 0, 1
// 2 -> 1, 1
// 3 -> 1, 2
// 4 -> 2, 3

class Solution {

	private int[][] field;
	private int[][] result;

	public int[][] solution(int[] targets) {
		init(targets);
		for (int i = 0; i < result.length; i++) {
			result[i] = field[targets[i]];
		}

		target: for (int i = 0; i < targets.length; i++) {
			
		}
		return result;
	}

	private void init(int[] targets) {
		this.field = new int[41][2];
		field[0] = new int[]{1, 0};
		field[1] = new int[]{0, 1};
		for (int i = 2; i < field.length; i++) {
			field[i][0] = field[i - 2][0] + field[i - 1][0];
			field[i][1] = field[i - 2][1] + field[i - 1][1];
		}
		this.result = new int[targets.length][2];
	}

	public String getAnswer() {
		StringBuilder answer = new StringBuilder();
		for (int[] ints : result) {
			answer.append(ints[0]).append(" ").append(ints[1]).append("\n");
		}
		return answer.toString();
	}
}
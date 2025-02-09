package main.BJ9095;

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

class Solution {

	private int[] field;
	private int[] result;

	public int[] solution(int[] targets) {
		init(targets);

		result = new int[targets.length]; // Initialize result here
		for (int i = 0; i < targets.length; i++) {
			result[i] = field[targets[i]];
		}

		return result;
	}

	private void init(int[] targets) {
		this.field = new int[12];
		this.result = new int[targets.length];
		field[1] = 1;
		field[2] = 2;
		field[3] = 4;
		for (int i = 4; i < field.length; i++) {
			field[i] = field[i - 1] + field[i - 2] + field[i - 3];
		}
	}

	public String getAnswer() {
		StringBuilder answer = new StringBuilder();
		for (int target : result) {
			answer.append(target).append("\n");
		}
		return answer.toString();
	}
}
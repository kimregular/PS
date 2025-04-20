package main.BJ11403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		System.out.println(new Solution().solution(readInput()));
	}

	private int[][] readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int len = Integer.parseInt(br.readLine());
			int[][] result = new int[len][len];

			for (int i = 0; i < len; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < len; j++) {
					result[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			return result;

		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
}

class Solution {

	private static final int MAX = 1000;

	private int[][] field;

	public String solution(int[][] field) {
		init(field);
		calc();
		return getAnswer();
	}

	private void init(int[][] field) {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if(field[i][j] == 1) continue;
				field[i][j] = MAX;
			}
		}
		this.field = field;
	}

	private void calc() {
		for (int k = 0; k < field.length; k++) {
			for (int i = 0; i < field.length; i++) {
				for (int j = 0; j < field.length; j++) {
					if (field[i][j] > field[i][k] + field[k][j]) {
						field[i][j] = field[i][k] + field[k][j];
					}
				}
			}
		}
	}

	private String getAnswer() {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				result.append(field[i][j] == MAX ? 0 : 1).append(" ");
			}
			result.append("\n");
		}
		return result.toString();
	}
}

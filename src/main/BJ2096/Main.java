package main.BJ2096;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		Solution s = new Solution();
		System.out.println(s.solution(readInput()));
	}

	private int[][] readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int len = Integer.parseInt(br.readLine());
			int[][] result = new int[len][3];

			for (int i = 0; i < len; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				result[i][0] = a;
				result[i][1] = b;
				result[i][2] = c;
			}

			return result;
		}catch (IOException e) {
			throw new RuntimeException();
		}
	}
}

class Solution {

	private int[][] maxField;
	private int[][] minField;

	public String solution(int[][] field) {
		init(field);
		calc();
		return getMax() + " " + getMin();
	}

	private void init(int[][] field) {
		this.maxField = copy(field);
		this.minField = copy(field);
	}

	private int[][] copy(int[][] target) {
		int[][] result = new int[target.length][3];

		int idx = 0;
		for (int[] ints : target) {
			System.arraycopy(ints, 0, result[idx++], 0, ints.length);
		}
		return result;
	}

	private void calc() {
		for (int i = 1; i < maxField.length; i++) {
			for (int j = 0; j < 3; j++) {
				if (j == 0) {
					maxField[i][j] += Math.max(maxField[i - 1][j], maxField[i - 1][j + 1]);
					minField[i][j] += Math.min(minField[i - 1][j], minField[i - 1][j + 1]);
				} else if (j == 1) {
					maxField[i][j] += Math.max(maxField[i - 1][j - 1], Math.max(maxField[i - 1][j], maxField[i - 1][j + 1]));
					minField[i][j] += Math.min(minField[i - 1][j - 1], Math.min(minField[i - 1][j], minField[i - 1][j + 1]));
				} else {
					maxField[i][j] += Math.max(maxField[i - 1][j - 1], maxField[i - 1][j]);
					minField[i][j] += Math.min(minField[i - 1][j - 1], minField[i - 1][j]);
				}
			}
		}
	}

	private int getMax() {
		int result = Integer.MIN_VALUE;
		for (int i = 0; i < 3; i++) {
			result = Math.max(result, maxField[maxField.length - 1][i]);
		}
		return result;
	}

	private int getMin() {
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			result = Math.min(result, minField[minField.length - 1][i]);
		}
		return result;
	}
}
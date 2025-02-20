package main.BJ11659;

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
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Input ip = readInput(br);
			Solution s = new Solution();
			System.out.println(s.solution(ip.field, ip.ranges));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int len = Integer.parseInt(st.nextToken());
		int rangeLen = Integer.parseInt(st.nextToken());

		int[] field = new int[len];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < len; i++) {
			field[i] = Integer.parseInt(st.nextToken());
		}

		int[][] ranges = new int[rangeLen][2];

		for (int i = 0; i < rangeLen; i++) {
			st = new StringTokenizer(br.readLine());
			ranges[i][0] = Integer.parseInt(st.nextToken());
			ranges[i][1] = Integer.parseInt(st.nextToken());
		}

		return new Input(field, ranges);
	}

	private static class Input {

		final int[] field;
		final int[][] ranges;

		public Input(int[] field, int[][] ranges) {
			this.field = field;
			this.ranges = ranges;
		}
	}
}

class Solution {

	private int[] accumulatedField;

	public String solution(int[] field, int[][] ranges) {
		init(field);

		StringBuilder result = new StringBuilder();
		for (int[] range : ranges) {
			int end = range[1];
			int from = range[0];
			result.append(accumulatedField[end] - accumulatedField[from - 1]).append("\n");
		}

		return result.toString();
	}

	private void init(int[] field) {
		this.accumulatedField = new int[field.length + 1];
		for (int i = 0; i < field.length; i++) {
			accumulatedField[i + 1] = accumulatedField[i] + field[i];
		}
	}
}
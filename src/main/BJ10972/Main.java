package main.BJ10972;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < len; i++) {
			result[i] = Integer.parseInt(st.nextToken());
		}
		return result;
	}
}

class Solution {

	private int[] field;

	public String solution(int[] field) {
		init(field);
		return nextPermutate() ? getResult() : "-1";
	}

	private void init(int[] field) {
		this.field = field;
	}

	private boolean nextPermutate() {
		int i = field.length - 1;

		while(i > 0 && field[i - 1] >= field[i]) --i;

		if(i == 0) return false;

		int j = field.length - 1;
		while(field[i - 1] >= field[j]) --j;

		swap(i - 1, j);

		int k = field.length - 1;
		while(i < k) swap(i++, k--);

		return true;
	}

	private void swap(int i, int j) {
		int temp = field[i];
		field[i] = field[j];
		field[j] = temp;
	}

	private String getResult() {
		StringBuilder result = new StringBuilder();
		for (int i : field) {
			result.append(i).append(" ");
		}
		return result.toString().trim();
	}
}
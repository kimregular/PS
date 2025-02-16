package main.BJ15654;

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

			Solution s = new Solution();
			Input ip = readInput(br);
			System.out.println(s.solution(ip.select, ip.field));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int len = Integer.parseInt(st.nextToken());
		int select = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] field = new int[len];
		for (int i = 0; i < len; i++) {
			field[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(field);

		return new Input(select, field);
	}

	private static class Input {

		final int select;
		final int[] field;

		public Input(int select, int[] field) {
			this.select = select;
			this.field = field;
		}
	}
}

class Solution {

	private int[] field;
	private int select;
	private int[] permutated;
	private boolean[] used;
	private StringBuilder result;

	public String solution(int select, int[] field) {
		init(select, field);
		permutate(0);
		return result.toString();
	}

	private void init(int select, int[] field) {
		this.field = field;
		this.select = select;
		this.permutated = new int[select];
		this.used = new boolean[field.length];
		this.result = new StringBuilder();
	}

	private void permutate(int cnt) {
		if (cnt == select) {
			savePermutated();
			return;
		}

		for (int i = 0; i < field.length; i++) {
			if(used[i]) continue;
			used[i] = true;
			permutated[cnt] = field[i];
			permutate(cnt + 1);
			used[i] = false;
		}
	}

	private void savePermutated() {
		for (int i : permutated) {
			result.append(i).append(" ");
		}
		result.append("\n");
	}
}
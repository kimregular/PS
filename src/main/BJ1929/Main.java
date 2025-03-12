package main.BJ1929;

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
		StringTokenizer st = new StringTokenizer(br.readLine());
		return new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
	}
}

class Solution {

	public String solution(int[] input) {
		Siever siever = new Siever(input);
		return siever.sieved();
	}
}

class Siever {

	private final boolean[] field;
	private final int start;
	private final int limit;

	public Siever(int[] input) {
		this.field = new boolean[input[1] + 1];
		field[0] = true;
		field[1] = true;
		this.start = input[0];
		this.limit = input[1];

		for (int i = 2; i <= Math.sqrt(limit); i++) {
			if(field[i]) continue;
			for (int j = i * i; j <= limit; j += i) {
				field[j] = true;
			}
		}
	}

	public String sieved() {
		StringBuilder result = new StringBuilder();
		for (int i = start; i <= limit; i++) {
			if(field[i]) continue;
			result.append(i).append("\n");
		}
		return result.toString();
	}
}
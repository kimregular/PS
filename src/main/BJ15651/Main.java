package main.BJ15651;

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

	private int field;
	private int select;
	private int[] permutated;
	private int[] used;
	private StringBuilder answer;

	public String solution(int[] input) {
		init(input);
		permutate(0);
		return answer.toString();
	}

	private void init(int[] input) {
		this.field = input[0];
		this.select = input[1];
		this.permutated = new int[select];
		this.used = new int[field + 1];
		this.answer = new StringBuilder();
	}

	private void permutate(int cnt) {
		if(cnt == select) {
			savePermutated();
			return;
		}

		for (int i = 1; i <= field; i++) {
			if(used[i] == select) continue;
			used[i]++;
			permutated[cnt] = i;
			permutate(cnt + 1);
			used[i]--;
		}
	}

	private void savePermutated() {
		for (int i : permutated) {
			answer.append(i).append(" ");
		}
		answer.append("\n");
	}
}
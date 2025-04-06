package main.BJ3671;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		Solution s = new Solution();
		System.out.println(s.solution(readInput()));
	}

	private char[][] readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int len = Integer.parseInt(br.readLine());
			char[][] result = new char[len][];

			for (int i = 0; i < len; i++) {
				result[i] = br.readLine().toCharArray();
			}

			return result;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

class Solution {

	private StringBuilder result = new StringBuilder();

	private Set<Integer> cnt;
	private char[] field;
	private boolean[] used;
	private char[] permutated;

	public String solution(char[][] testCases) {
		for (char[] testCase : testCases) {
			init(testCase);
			calc();
			result.append(cnt.size()).append("\n");
		}

		return result.toString();
	}

	private void init(char[] testCase) {
		this.cnt = new HashSet<>();
		this.field = testCase;
		this.used = new boolean[testCase.length];
	}

	private void calc() {
		for (int i = 1; i <= field.length; i++) {
			this.permutated = new char[i];
			permutate(0, i);
		}
	}

	private void permutate(int cnt, int limit) {
		if (cnt == limit) {
			save();
			return;
		}

		for (int i = 0; i < field.length; i++) {
			if(used[i]) continue;
			used[i] = true;
			permutated[cnt] = field[i];
			permutate(cnt + 1, limit);
			used[i] = false;
		}
	}

	private void save() {
		int target = getNumOf(permutated);
		if(isPrime(target)) cnt.add(target);
	}

	private int getNumOf(char[] permutated) {
		StringBuilder temp = new StringBuilder();
		for (char c : permutated) {
			temp.append(c);
		}
		return Integer.parseInt(temp.toString());
	}

	private boolean isPrime(int target) {
		if(target <= 1) return false;

		for (int i = 2; i <= Math.sqrt(target); i++) {
			if(target % i == 0) return false;
		}
		return true;
	}
}
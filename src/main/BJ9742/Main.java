package main.BJ9742;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		Solution s = new Solution();
		System.out.println(s.solution(readInput()));
	}

	private List<String> readInput() {
		List<String> result = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			String input;

			while ((input = br.readLine()) != null) {
				result.add(input);
			}
		}catch (IOException e) {
			throw new RuntimeException();
		}

		return result;
	}
}

class Solution {

	public String solution(List<String> testCases) {
		StringBuilder result = new StringBuilder();
		for (String testCase : testCases) {
			result.append(testCase).append(" = ").append(calc(testCase)).append("\n");
		}
		return result.toString();
	}

	private String calc(String testCase) {
		String[] input = testCase.split(" ");
		char[] field = input[0].toCharArray();
		int n = Integer.parseInt(input[1]);
		String permutation = getPermutationOf(field, n);
		return permutation != null ? permutation : "No permutation";
	}

	private String getPermutationOf(char[] field, int n) {
		List<Character> chars = new ArrayList<>();
		for(char c : field) chars.add(c);

		int total = factorial(chars.size());
		if(n > total) return null;

		n--;

		StringBuilder result = new StringBuilder();

		while (!chars.isEmpty()) {
			int size = chars.size();
			int f = factorial(size - 1);
			int index = n / f;

			result.append(chars.get(index));
			chars.remove(index);

			n %= f;
		}
		return result.toString();
	}

	private int factorial(int size) {
		int result = 1;
		for (int i = 1; i <= size; i++) {
			result *= i;
		}
		return result;
	}
}
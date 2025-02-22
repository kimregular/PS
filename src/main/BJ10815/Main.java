package main.BJ10815;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Solution s = new Solution();
			System.out.println(s.solution(readInput(br), readInput(br)));

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

	private Set<Integer> set;

	public String solution(int[] field, int[] targets) {
		init(field);

		StringBuilder result = new StringBuilder();

		for (int target : targets) {
			result.append(set.contains(target) ? 1 : 0).append(" ");
		}
		return result.toString();
	}

	private void init(int[] field) {
		set = new HashSet<>();
		for (int i : field) {
			set.add(i);
		}
	}
}
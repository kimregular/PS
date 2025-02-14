package main.BJ9012;

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
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Solution s = new Solution();
			StringBuilder answer = new StringBuilder();

			int TEST_CASES = Integer.parseInt(br.readLine());
			for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
				answer.append(s.solution(readInput(br))).append("\n");
			}

			System.out.println(answer);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String readInput(BufferedReader br) throws IOException {
		return br.readLine();
	}
}

class Solution {

	public String solution(String target) {
		return valid(target) ? "YES" : "NO";
	}

	private boolean valid(String target) {
		List<Character> stk = new ArrayList<>();

		for (char c : target.toCharArray()) {
			if (c == '(') {
				stk.add(c);
			} else {
				if (stk.isEmpty()) {
					return false;
				} else {
					stk.remove(stk.size() - 1);
				}
			}
		}

		return stk.isEmpty();
	}
}
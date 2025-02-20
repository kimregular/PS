package main.SWE1218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	private void run() {

		StringBuilder result = new StringBuilder();
		Resolver r = new Resolver();
		int TEST_CASES = 10;

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
				result.append("#").append(TEST_CASE + 1).append(" ").append(r.resolve(readInput(br))).append("\n");
			}

			System.out.println(result.toString().trim());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String readInput(BufferedReader br) throws IOException {
		br.readLine();
		return br.readLine();
	}
}

class Resolver {

	private List<Character> stck;

	public int resolve(String target) {
		init();
		return valid(target) ? 1 : 0;
	}

	private void init() {
		this.stck = new ArrayList<>();
	}

	private boolean valid(String target){
		for (char c : target.toCharArray()) {
			if(c == '(' || c == '{' || c == '[' || c == '<') stck.add(c);
			else {
				if (stck.isEmpty()) {
					return false;
				} else if (c == ')') {
					if(stck.get(stck.size() - 1) != '(') return false;
				} else if (c == '}') {
					if(stck.get(stck.size() - 1) != '{') return false;
				} else if (c == ']') {
					if(stck.get(stck.size() - 1) != '[') return false;
				} else if (c == '>') {
					if(stck.get(stck.size() - 1) != '<') return false;
				}
				stck.remove(stck.size() - 1);
			}
		}

		return stck.isEmpty();
	}
}
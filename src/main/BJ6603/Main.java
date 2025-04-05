package main.BJ6603;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		Solution s = new Solution();
		System.out.println(s.solution(readInput()));
	}

	private List<List<Integer>> readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			List<List<Integer>> result = new ArrayList<>();
			StringTokenizer st;
			int idx = 0;
			while (true) {
				st = new StringTokenizer(br.readLine());

				int flag = Integer.parseInt(st.nextToken());
				if(flag == 0) return result;

				result.add(new ArrayList<>());
				for (int i = 0; i < flag; i++) {
					result.get(idx).add(Integer.parseInt(st.nextToken()));
				}
				idx++;
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
}

class Solution {

	private static final int LIMIT = 6;

	private List<Integer> field;
	private int[] combinated;
	private StringBuilder result = new StringBuilder();

	public String solution(List<List<Integer>> testCases) {
		for (List<Integer> testCase : testCases) {
			init(testCase);
			combinate(0, 0);
			result.append("\n");
		}
		return result.toString();
	}

	private void init(List<Integer> testCase) {
		this.field = testCase;
		this.combinated = new int[LIMIT];
	}

	private void combinate(int idx, int start) {
		if (idx == LIMIT) {
			save();
			return;
		}

		for (int i = start; i < field.size(); i++) {
			combinated[idx] = field.get(i);
			combinate(idx + 1, i + 1);
		}
	}

	private void save() {
		for (int i : combinated) {
			result.append(i).append(" ");
		}
		result.append("\n");
	}
}
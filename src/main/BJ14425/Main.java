package main.BJ14425;

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
		Solution s = new Solution();
		Input ip = readInput();
		System.out.println(s.solution(ip.field, ip.targets));
	}

	private Input readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int fieldLen = Integer.parseInt(st.nextToken());
			int targetsLen = Integer.parseInt(st.nextToken());

			String[] field = new String[fieldLen];
			for (int i = 0; i < fieldLen; i++) {
				field[i] = br.readLine();
			}

			String[] targets = new String[targetsLen];
			for (int i = 0; i < targetsLen; i++) {
				targets[i] = br.readLine();
			}

			return new Input(field, targets);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static class Input {

		final String[] field;
		final String[] targets;

		public Input(String[] field, String[] targets) {
			this.field = field;
			this.targets = targets;
		}
	}
}

class Solution {

	public int solution(String[] field, String[] targets) {
		int result = 0;
		Set<String> set = createSetOf(field);
		for (String target : targets) {
			if(set.contains(target)) result++;
		}
		return result;
	}

	private Set<String> createSetOf(String[] field) {
		Set<String> result = new HashSet<>();
		for (String s : field) {
			result.add(s);
		}
		return result;
	}
}
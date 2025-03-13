package main.BJ10814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

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

	private String[][] readInput(BufferedReader br) throws IOException {
		int len = Integer.parseInt(br.readLine());
		String[][] result = new String[len][2];

		for (int i = 0; i < len; i++) {
			result[i] = br.readLine().split(" ");
		}
		return result;
	}
}

class Solution {

	public String solution(String[][] people) {
		Member[] members = new Member[people.length];

		for (int i = 0; i < people.length; i++) {
			members[i] = new Member(Integer.parseInt(people[i][0]), people[i][1], i);
		}

		Arrays.sort(members);

		StringBuilder result = new StringBuilder();
		for (Member member : members) {
			result.append(member.age).append(" ").append(member.name).append("\n");
		}
		return result.toString();
	}

	private static class Member implements Comparable<Member> {

		final int age;
		final String name;
		final int idx;

		public Member(int age, String name, int idx) {
			this.age = age;
			this.name = name;
			this.idx = idx;
		}

		@Override
		public int compareTo(Member o) {
			if(age == o.age) return Integer.compare(idx, o.idx);
			return Integer.compare(age, o.age);
		}
	}
}
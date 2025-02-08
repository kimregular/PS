package main.BJ11723;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {

		Solution s = new Solution();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int len = Integer.parseInt(br.readLine());
			for (int i = 0; i < len; i++) {
				s.oper(br.readLine());
			}
			System.out.println(s.getResult());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

class Solution {

	private static int[] field = new int[21];
	private StringBuilder answer = new StringBuilder();

	public void oper(String order) {
		if (order.startsWith("add")) {
			add(order.split(" ")[1]);
		} else if (order.startsWith("remove")) {
			remove(order.split(" ")[1]);
		} else if (order.startsWith("check")) {
			answer.append(check(order.split(" ")[1])).append("\n");
		} else if (order.startsWith("toggle")) {
			toggle(order.split(" ")[1]);
		} else if (order.startsWith("all")) {
			Arrays.fill(field, 1);
		} else {
			field = new int[21];
		}
	}

	public String getResult() {
		return answer.toString();
	}

	private void add(String targetStr) {
		int target = Integer.parseInt(targetStr);
		field[target] = 1;
	}

	private void remove(String targetStr) {
		int target = Integer.parseInt(targetStr);
		field[target] = 0;
	}

	private int check(String targetStr) {
		int target = Integer.parseInt(targetStr);
		return field[target] == 1 ? 1 : 0;
	}

	private void toggle(String targetStr) {
		int target = Integer.parseInt(targetStr);
		field[target] = field[target] == 0 ? 1 : 0;
	}
}
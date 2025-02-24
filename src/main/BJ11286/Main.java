package main.BJ11286;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

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
		int len = Integer.parseInt(br.readLine());
		int[] result = new int[len];
		for (int i = 0; i < len; i++) {
			result[i] = Integer.parseInt(br.readLine());
		}
		return result;
	}
}

class Solution {

	private PriorityQueue<Number> q;

	public String solution(int[] orders) {
		init();

		StringBuilder result = new StringBuilder();

		for (int order : orders) {
			if (order != 0) {
				q.offer(new Number(order));
			} else {
				if (q.isEmpty()) {
					result.append(0);
				} else {
					result.append(q.poll().value);
				}
				result.append("\n");
			}
		}
		return result.toString();
	}

	private void init() {
		this.q = new PriorityQueue<>();
	}

	private static class Number implements Comparable<Number> {

		final int value;
		final int abs;

		public Number(int value) {
			this.value = value;
			this.abs = Math.abs(value);
		}

		@Override
		public int compareTo(Number o) {
			if (abs == o.abs) return Integer.compare(value, o.value);
			return Integer.compare(abs, o.abs);
		}
	}
}
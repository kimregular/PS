package main.BJ10816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Input ip = readInput(br);
			Solution s = new Solution();
			System.out.println(s.solution(ip.field, ip.targets));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		int len = Integer.parseInt(br.readLine());
		int[] field = new int[len];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < len; i++) {
			field[i] = Integer.parseInt(st.nextToken());
		}
		len = Integer.parseInt(br.readLine());
		int[] targets = new int[len];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < len; i++) {
			targets[i] = Integer.parseInt(st.nextToken());
		}
		return new Input(field, targets);
	}

	private static class Input {

		final int[] field;
		final int[] targets;

		public Input(int[] field, int[] targets) {
			this.field = field;
			this.targets = targets;
		}
	}
}

class Solution {

	private int[] field;

	public String solution(int[] field, int[] targets) {
		init(field);
		StringBuilder result = new StringBuilder();
		for (int target : targets) {
			result.append(getUpperBound(target) - getLowerBound(target)).append(" ");
		}

		return result.toString();
	}

	private void init(int[] field) {
		Arrays.sort(field);
		this.field = field;
	}

	private int getUpperBound(int target) {
		int lp = 0;
		int rp = field.length - 1;

		while (lp <= rp) {
			int mid = (lp + rp) / 2;

			if (field[mid] <= target) {
				lp = mid + 1;
			} else {
				rp = mid - 1;
			}
		}
		return lp - 1;
	}

	private int getLowerBound(int target) {
		int lp = 0;
		int rp = field.length - 1;

		while (lp <= rp) {
			int mid = (lp + rp) / 2;

			if (field[mid] < target) {
				lp = mid + 1;
			} else {
				rp = mid - 1;
			}
		}
		return lp - 1;
	}
}

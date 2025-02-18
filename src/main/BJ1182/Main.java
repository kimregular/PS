package main.BJ1182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Input ip = readInput(br);
			Solution s = new Solution();
			System.out.println(s.solution(ip.target, ip.field));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int len = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());
		int[] field = new int[len];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < len; i++) {
			field[i] = Integer.parseInt(st.nextToken());
		}
		return new Input(target, field);
	}

	private static class Input {

		final int target;
		final int[] field;

		public Input(int target, int[] field) {
			this.target = target;
			this.field = field;
		}
	}
}

class Solution {

	private int target;
	private int[] field;
	private int result;

	public int solution(int target, int[] field) {
		init(target, field);
		func(0, 0, 0);
		return result;
	}

	private void init(int target, int[] field) {
		this.target = target;
		this.field = field;
		this.result = 0;
	}

	private void func(int idx, int usedElem, int sum) {
		if (idx == field.length) {
			if (usedElem != 0 && sum == target) {
				result++;
			}
			return;
		}

		func(idx + 1, usedElem, sum);
		func(idx + 1, usedElem + 1, sum + field[idx]);
	}
}
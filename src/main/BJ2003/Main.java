package main.BJ2003;

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

	public int solution(int target, int[] field) {
		int result = 0;

		int lp = 0;
		int rp = 0;
		int sum = 0;

		while (lp < field.length) {
			if (rp < field.length && sum < target) {
				sum += field[rp++];
			} else {
				sum -= field[lp++];
			}

			if (sum == target) {
				result++;
			}
		}

		return result;
	}
}
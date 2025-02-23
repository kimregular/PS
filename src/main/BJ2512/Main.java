package main.BJ2512;

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
			System.out.println(s.solution(ip.requires, ip.budget));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		int len = Integer.parseInt(br.readLine());
		int[] requires = new int[len];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < len; i++) {
			requires[i] = Integer.parseInt(st.nextToken());
		}
		int budget = Integer.parseInt(br.readLine());
		return new Input(requires, budget);
	}

	private static class Input {

		final int[] requires;
		final int budget;

		public Input(int[] requires, int budget) {
			this.requires = requires;
			this.budget = budget;
		}
	}
}

class Solution {

	public int solution(int[] requires, int budget) {
		Arrays.sort(requires);

		int lp = 1;
		int rp = budget;

		while (lp <= rp) {
			int mid = (lp + rp) / 2;

			int cnt = 0;

			for (int require : requires) {
				cnt += Math.min(mid, require);
			}

			if (cnt > budget) {
				rp = mid - 1;
			} else {
				lp = mid + 1;
			}
		}
		return lp - 1 == budget ? requires[requires.length - 1] : lp - 1;
	}
}
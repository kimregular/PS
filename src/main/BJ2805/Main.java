package main.BJ2805;

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
			System.out.println(s.solution(ip.target, ip.trees));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int len = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] trees = new int[len];
		for (int i = 0; i < len; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
		}
		return new Input(target, trees);
	}

	private static class Input {

		final int target;
		final int[] trees;

		public Input(int target, int[] trees) {
			this.target = target;
			this.trees = trees;
		}
	}
}

class Solution {

	private int[] trees;

	public long solution(int target, int[] trees) {
		init(trees);

		long lp = 0;
		long rp = trees[trees.length - 1];

		while (lp <= rp) {
			long mid = (lp + rp) / 2;

			long heightSum = getHeightSum(mid);

			if (target <= heightSum) {
				lp = mid + 1;
			} else {
				rp = mid - 1;
			}
		}
		return lp - 1;
	}

	private void init(int[] trees) {
		Arrays.sort(trees);
		this.trees = trees;
	}

	private long getHeightSum(long height) {
		long result = 0;

		for (int tree : trees) {
			if (tree <= height) continue;
			result += tree - height;
		}
		return result;
	}
}


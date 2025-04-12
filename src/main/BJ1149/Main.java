package main.BJ1149;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		Solution s = new Solution();
		System.out.println(s.solution(readInput()));
	}

	private int[][] readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int len = Integer.parseInt(br.readLine());
			int[][] result = new int[len][3];

			for (int i = 0; i < len; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				result[i][0] = Integer.parseInt(st.nextToken());
				result[i][1] = Integer.parseInt(st.nextToken());
				result[i][2] = Integer.parseInt(st.nextToken());
			}
			return result;
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
}

class Solution {

	public int solution(int[][] target) {
		calc(target);
		return getMin(target);
	}

	private void calc(int[][] target) {
		for (int i = 1; i < target.length; i++) {
			for (int j = 0; j < target[i].length; j++) {
				if (j == 0) {
					target[i][j] += Math.min(target[i - 1][j + 1], target[i - 1][j + 2]);
				} else if (j == 1) {
					target[i][j] += Math.min(target[i - 1][j - 1], target[i - 1][j + 1]);
				} else {
					target[i][j] += Math.min(target[i - 1][j - 1], target[i - 1][j - 2]);
				}
			}
		}
	}

	private int getMin(int[][] target) {
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			result = Math.min(result, target[target.length - 1][i]);
		}
		return result;
	}
}

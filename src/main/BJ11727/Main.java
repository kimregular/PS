package main.BJ11727;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		Solution s = new Solution();
		System.out.println(s.solution(readInput()));
	}

	private int readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			return Integer.parseInt(br.readLine());

		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
}

class Solution {

	private static final int DIVIDOR = 10_007;

	private int[] memo;

	public int solution(int target) {
		if(target == 1) return 1;
		if(target == 2) return 3;
		this.memo = new int[target + 1];
		memo[1] = 1;
		memo[2] = 3;
		return calc(target);
	}

	private int calc(int target) {
		if(memo[target] != 0) return memo[target];
		memo[target] = (calc(target - 1) + 2 * calc(target - 2)) % DIVIDOR;
		return memo[target];
	}

}
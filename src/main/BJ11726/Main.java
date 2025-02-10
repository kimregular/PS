package main.BJ11726;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

	private int readInput(BufferedReader br) throws IOException {
		return Integer.parseInt(br.readLine());
	}
}

class Solution {

	private static final int DIVIDOR = 10_007;

	public int solution(int target) {
		if(target < 3) return target;
		int[] field = new int[target + 1];
		field[1] = 1;
		field[2] = 2;
		for (int i = 3; i < field.length; i++) {
			field[i] = (field[i - 1] + field[i - 2]) % DIVIDOR;
		}

		return field[target];
	}
}

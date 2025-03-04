package main.BJ27959;

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

			Solution s = new Solution();
			System.out.println(s.solution(readInput(br)));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private int[] readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		return new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
	}
}

class Solution {

	public String solution(int[] info) {
		return afforable(info) ? "Yes" : "No";
	}

	private boolean afforable(int[] info) {
		int money = info[0] * 100;
		int price = info[1];
		return money >= price;
	}
}
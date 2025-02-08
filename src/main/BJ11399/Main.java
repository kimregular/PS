package main.BJ11399;

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

			Solution s = new Solution();
			System.out.println(s.solution(readInput(br)));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private int[] readInput(BufferedReader br) throws IOException {
		int len = Integer.parseInt(br.readLine());
		int[] result = new int[len];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < len; i++) {
			result[i] = Integer.parseInt(st.nextToken());
		}
		return result;
	}
}

class Solution {

	public int solution(int[] queue) {
		int result = 0;
		Arrays.sort(queue);
		int prev = 0;
		for (int i : queue) {
			prev += i;
			result += prev;
		}
		return result;
	}
}
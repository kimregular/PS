package main.BJ2798;

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
			Input ip = readInput(br);
			System.out.println(s.solution(ip.target, ip.cards));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int len = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());

		int[] cards = new int[len];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < len; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		return new Input(target, cards);
	}

	private static class Input {

		final int target;
		final int[] cards;

		public Input(int target, int[] cards) {
			this.target = target;
			this.cards = cards;
		}
	}
}

class Solution {

	private int target;
	private int[] cards;
	private int result;

	public int solution(int target, int[] cards) {
		init(target, cards);
		calc();
		return result;
	}

	private void init(int target, int[] cards) {
		this.target = target;
		this.cards = cards;
		this.result = Integer.MIN_VALUE;
	}

	private void calc() {
		for (int i = 0; i < cards.length; i++) {
			for (int j = i + 1; j < cards.length; j++) {
				for (int k = j + 1; k < cards.length; k++) {
					int sum = cards[i] + cards[j] + cards[k];
					if(sum > target) continue;
					result = Math.max(result, sum);
				}
			}
		}
	}
}
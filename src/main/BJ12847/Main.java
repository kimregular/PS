package main.BJ12847;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Solution s = new Solution();
			Input ip = readInput(br);
			System.out.println(s.solution(ip.available, ip.moneys));

		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int len = Integer.parseInt(st.nextToken());
		int available = Integer.parseInt(st.nextToken());

		int[] moneys = new int[len];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < len; i++) {
			moneys[i] = Integer.parseInt(st.nextToken());
		}

		return new Input(available, moneys);
	}

	private static class Input {

		final int available;
		final int[] moneys;

		public Input(int available, int[] moneys) {
			this.available = available;
			this.moneys = moneys;
		}
	}
}

class Solution {

	private int available;
	private int[] moneys;
	private long result;

	public long solution(int available, int[] moneys) {
		init(available, moneys);
		calc();
		return result;
	}

	private void init(int available, int[] moneys) {
		this.available = available;
		this.moneys = moneys;
		this.result = 0;
	}

	private void calc() {
		long sum = 0;
		for(int i = 0; i < available; i++) {
			sum += moneys[i];
		}
		result = Math.max(result, sum);
		int idx = 0;
		for(int i = available; i < moneys.length; i++) {
			sum += moneys[i];
			sum -= moneys[idx++];
			result = Math.max(result, sum);
		}
	}
}


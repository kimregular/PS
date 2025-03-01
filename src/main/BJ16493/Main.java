package main.BJ16493;

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
			System.out.println(s.solution(ip.LIMIT, ip.infos));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int LIMIT = Integer.parseInt(st.nextToken());
		int len = Integer.parseInt(st.nextToken());

		int[][] infos = new int[len][2];

		for (int i = 0; i < len; i++) {
			st = new StringTokenizer(br.readLine());
			infos[i][0] = Integer.parseInt(st.nextToken());
			infos[i][1] = Integer.parseInt(st.nextToken());
		}

		return new Input(LIMIT, infos);
	}

	private static class Input {

		final int LIMIT;
		final int[][] infos;

		public Input(int LIMIT, int[][] infos) {
			this.LIMIT = LIMIT;
			this.infos = infos;
		}
	}
}

class Solution {

	private int LIMIT;
	private int[][] infos;
	private int result;

	public int solution(int LIMIT, int[][] infos) {
		init(LIMIT, infos);
		calc(0, 0, 0);
		return result;
	}

	private void init(int LIMIT, int[][] infos) {
		this.LIMIT = LIMIT;
		this.infos = infos;
		this.result = Integer.MIN_VALUE;
	}

	private void calc(int idx, int days, int pages) {
		if(days > LIMIT) return;

		if (idx == infos.length) {
			if (result < pages) {
				result = pages;
			}
			return;
		}

		calc(idx + 1, days, pages);
		calc(idx + 1, days + infos[idx][0], pages + infos[idx][1]);
	}
}
package main.BJ1654;

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
			System.out.println(s.solution(ip.target, ip.wires));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int len = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());
		int[] wires = new int[len];
		for (int i = 0; i < len; i++) {
			wires[i] = Integer.parseInt(br.readLine());
		}
		return new Input(target, wires);
	}

	private static class Input {

		final int target;
		final int[] wires;

		public Input(int target, int[] wires) {
			this.target = target;
			this.wires = wires;
		}
	}
}

class Solution {

	private int[] wires;

	public long solution(int target, int[] wires) {
		init(wires);

		long lp = 1;
		long rp = this.wires[this.wires.length - 1];

		while (lp <= rp) {
			long mid = (rp + lp) / 2;

			long sumOfWires = cut(mid);

			if (sumOfWires < target) {
				rp = mid - 1;
			} else {
				lp = mid + 1;
			}
		}
		return lp - 1;
	}

	private void init(int[] wires) {
		Arrays.sort(wires);
		this.wires = wires;
	}

	private long cut(long length) {
		long result = 0;

		for (int wire : wires) {
			result += wire / length;
		}
		return result;
	}
}
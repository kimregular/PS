package main.SWE1486;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	private void run() {

		StringBuilder result = new StringBuilder();
		Resolver r = new Resolver();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int TEST_CASES = Integer.parseInt(br.readLine());
			for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
				Input ip = readInput(br);
				result.append("#").append(TEST_CASE + 1).append(" ").append(r.resolve(ip.target, ip.heights)).append("\n");
			}

			System.out.println(result.toString().trim());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int len = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());
		int[] heights = new int[len];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < len; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}
		return new Input(target, heights);
	}

	private static class Input {

		final int target;
		final int[] heights;

		public Input(int target, int[] heights) {
			this.target = target;
			this.heights = heights;
		}
	}
}

class Resolver {

	private int target;
	private int[] heights;
	private int result;

	public int resolve(int target, int[] heights) {
		init(target, heights);
		calc(0, 0);
		return result;
	}

	private void init(int target, int[] heights) {
		this.target = target;
		this.heights = heights;
		this.result = Integer.MAX_VALUE;
	}

	private void calc(int depth, int heightSum) {
		if(depth == heights.length) {
			if(heightSum < target) return;

			int temp = heightSum - target;
			if(temp < result) {
				result = temp;
			}
			return;
		}

		calc(depth + 1, heightSum);
		calc(depth + 1, heightSum + heights[depth]);
	}
}

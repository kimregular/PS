package main.SWE1225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	private void run() {

		StringBuilder result = new StringBuilder();
		Resolver r = new Resolver();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int TEST_CASES = 10;
			for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
				result.append("#").append(TEST_CASE + 1).append(" ").append(r.resolve(readInput(br))).append("\n");
			}

			System.out.println(result.toString().trim());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private int[] readInput(BufferedReader br) throws IOException {
		br.readLine();
		int len = 8;
		int[] result = new int[len];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < len; i++) {
			result[i] = Integer.parseInt(st.nextToken());
		}
		return result;
	}
}

class Resolver {

	public String resolve(int[] nums) {
		Queue<Integer> q = getQ(nums);
		int minus = 1;
		while (q.peek() - minus > 0) {
			q.offer(q.poll() - minus);
			minus++;
			if(minus == 6) minus = 1;
		}
		q.poll();
		q.offer(0);
		return getResult(q);
	}

	private Queue<Integer> getQ(int[] nums) {
		Queue<Integer> result = new ArrayDeque<>(8);
		for (int num : nums) {
			result.offer(num);
		}
		return result;
	}

	private String getResult(Queue<Integer> dq) {
		StringBuilder result = new StringBuilder();
		for (Integer i : dq) {
			result.append(i).append(" ");
		}
		return result.toString();
	}
}
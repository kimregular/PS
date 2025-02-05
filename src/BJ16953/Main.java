package src.BJ16953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

	private long start;
	private long target;
	private Set<Long> visited;

	public long solution(int[] input) {
		init(input);
		return calc();
	}

	private void init(int[] input) {
		this.start = input[0];
		this.target = input[1];
		this.visited = new HashSet<>();
	}

	private long calc() {
		Queue<long[]> q = new ArrayDeque<>();
		q.offer(new long[]{start, 1});
		visited.add(start);

		while (!q.isEmpty()) {
			long[] cur = q.poll();
			long num = cur[0];
			long step = cur[1];

			if (num == target) return step;

			long nextStep1 = num * 2;
			long nextStep2 = num * 10 + 1;

			if (nextStep1 <= target && !visited.contains(nextStep1)) {
				q.offer(new long[]{nextStep1, step + 1});
				visited.add(nextStep1);
			}

			if (nextStep2 <= target && !visited.contains(nextStep2)) {
				q.offer(new long[]{nextStep2, step + 1});
				visited.add(nextStep2);
			}
		}

		return -1;
	}
}
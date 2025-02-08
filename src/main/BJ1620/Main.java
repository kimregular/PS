package main.BJ1620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Input ip = readInput(br);
			Solution s = new Solution();
			System.out.println(s.solution(ip.targets, ip.orders));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numOfTargets = Integer.parseInt(st.nextToken());
		int numOfOrders = Integer.parseInt(st.nextToken());

		String[] targets = new String[numOfTargets];
		for (int i = 0; i < numOfTargets; i++) {
			targets[i] = br.readLine();
		}

		String[] orders = new String[numOfOrders];
		for (int i = 0; i < numOfOrders; i++) {
			orders[i] = br.readLine();
		}
		return new Input(targets, orders);
	}

	private static class Input {

		private final String[] targets;
		private final String[] orders;

		public Input(String[] targets, String[] orders) {
			this.targets = targets;
			this.orders = orders;
		}
	}
}

class Solution {

	private Map<String, Integer> targetsByNames;
	private Map<Integer, String> targetsByNumbers;

	public String solution(String[] targets, String[] orders) {
		init(targets);

		StringBuilder answer = new StringBuilder();
		for (String order : orders) {
			try {
				int idx = Integer.parseInt(order);
				answer.append(targetsByNumbers.get(idx));
			} catch (NumberFormatException e) {
				answer.append(targetsByNames.get(order));
			}
			answer.append("\n");
		}
		return answer.toString();
	}

	private void init(String[] targets) {
		targetsByNames = new HashMap<>();
		targetsByNumbers = new HashMap<>();

		for (int i = 0; i < targets.length; i++) {
			targetsByNames.put(targets[i], i + 1);
			targetsByNumbers.put(i + 1, targets[i]);
		}
	}
}
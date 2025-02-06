package src.main.BJ1717;

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

			Input ip = readInput(br);
			Solution s = new Solution();
			System.out.println(s.solution(ip.numOfPeople, ip.orders));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numOfPeople = Integer.parseInt(st.nextToken());
		int numOfOrders = Integer.parseInt(st.nextToken());

		int[][] orders = new int[numOfOrders][3];

		for (int i = 0; i < numOfOrders; i++) {
			st = new StringTokenizer(br.readLine());
			int orderType = Integer.parseInt(st.nextToken());
			int group1 = Integer.parseInt(st.nextToken());
			int group2 = Integer.parseInt(st.nextToken());
			orders[i][0] = orderType;
			orders[i][1] = group1;
			orders[i][2] = group2;
		}
		return new Input(numOfPeople, orders);
	}

	private static class Input {
		final int numOfPeople;
		final int[][] orders;

		public Input(int numOfPeople, int[][] orders) {
			this.numOfPeople = numOfPeople;
			this.orders = orders;
		}
	}
}

class Solution {

	private int[] people;
	private int[] ranks;

	public String solution(int numOfPeople, int[][] orders) {
		init(numOfPeople);

		StringBuilder st = new StringBuilder();

		for (int[] order : orders) {
			if (order[0] == 0) {
				connect(order[1], order[2]);
			} else {
				st.append(isSameGroup(order[1], order[2])).append("\n");
			}
		}
		return st.toString();
	}

	private void init(int numOfPeople) {
		this.people = new int[numOfPeople + 1];
		this.ranks = new int[numOfPeople + 1];
		for (int i = 0; i < people.length; i++) {
			people[i] = i;
			ranks[i] = 1;
		}
	}

	private void connect(int target1, int target2) {
		int group1 = getGroup(target1);
		int group2 = getGroup(target2);
		if (group1 != group2) {
			if (ranks[group1] > ranks[group2]) {
				people[group2] = group1;
			} else if (ranks[group1] < ranks[group2]) {
				people[group1] = group2;
			} else {
				people[group2] = group1;
				ranks[group1]++;
			}
		}
	}

	private int getGroup(int target) {
		if(target != people[target]) people[target] = getGroup(people[target]);
		return people[target];
	}

	private String isSameGroup(int target1, int target2) {
		return getGroup(target1) == getGroup(target2) ? "YES" : "NO";
	}
}
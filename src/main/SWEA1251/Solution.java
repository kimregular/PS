package main.SWEA1251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	private void run() {
		Resolver r = new Resolver();
		StringBuilder answer = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int TEST_CASES = Integer.parseInt(br.readLine());

			for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
				Input ip = readInput(br);
				answer.append("#").append(TEST_CASE + 1).append(" ").append(r.resolve(ip.nodes, ip.tax)).append("\n");
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}

		System.out.println(answer);
	}

	private Input readInput(BufferedReader br) throws IOException {
		int len = Integer.parseInt(br.readLine());

		int[][] nodes = new int[len][2];

		StringTokenizer xTokens = new StringTokenizer(br.readLine());
		StringTokenizer yTokens = new StringTokenizer(br.readLine());

		for (int i = 0; i < len; i++) {
			int x = Integer.parseInt(xTokens.nextToken());
			int y = Integer.parseInt(yTokens.nextToken());
			nodes[i][0] = x;
			nodes[i][1] = y;
		}

		double tax = Double.parseDouble(br.readLine());

		return new Input(nodes, tax);
	}

	private static class Input {

		final int[][] nodes;
		final double tax;

		public Input(int[][] nodes, double tax) {
			this.nodes = nodes;
			this.tax = tax;
		}
	}
}

class Resolver {

	private double tax;
	private int nodeCount;
	private double totalCost;
	private double[] groupOf;
	private PriorityQueue<double[]> q;

	public long resolve(int[][] nodes, double tax) {
		init(nodes, tax);

		while (!q.isEmpty()) {
			double[] cur = q.poll();

			double from = cur[0];
			double to = cur[1];

			if(isConnected(from, to)) continue;

			connect(from, to);

			totalCost += cur[2];
			nodeCount--;

			if(nodeCount == 0) break;
		}
		return Math.round(totalCost);
	}

	private void init(int[][] nodes, double tax) {
		this.tax = tax;
		this.nodeCount = nodes.length;
		this.totalCost = 0;
		this.groupOf = new double[nodes.length];
		this.q = new PriorityQueue<>((a, b) -> Double.compare(a[2], b[2]));

		for (int i = 0; i < groupOf.length; i++) {
			groupOf[i] = i;
		}

		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes.length; j++) {
				if(i == j) continue;
				q.offer(getCost(i, j, nodes[i], nodes[j]));
			}
		}
	}

	private double[] getCost(int from, int to, int[] node1, int[] node2) {
		double dist = Math.pow(node1[0] - node2[0], 2) + Math.pow(node1[1] - node2[1], 2);
		return new double[]{from, to, dist * tax};
	}

	private boolean isConnected(double node1, double node2) {
		return getGroupOf(node1) == getGroupOf(node2);
	}

	private double getGroupOf(double node) {
		if(groupOf[(int) node] != node) return groupOf[(int) node] = getGroupOf(groupOf[(int) node]);
		return node;
	}

	private void connect(double node1, double node2) {
		double group1 = getGroupOf(node1);
		double group2 = getGroupOf(node2);
		groupOf[(int) Math.max(group1, group2)] = (int) Math.min(group1, group2);
	}
}
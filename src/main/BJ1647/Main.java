package main.BJ1647;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		Solution s = new Solution();
		Input ip = readInput();
		System.out.println(s.solution(ip.numOfNodes, ip.edges));
	}

	private Input readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int numOfNodes = Integer.parseInt(st.nextToken());
			int numOfEdges = Integer.parseInt(st.nextToken());

			int[][] edges = new int[numOfEdges][3];

			for (int i = 0; i < numOfEdges; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				edges[i][0] = from;
				edges[i][1] = to;
				edges[i][2] = cost;
			}

			return new Input(numOfNodes, edges);

		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	private static class Input {

		final int numOfNodes;
		final int[][] edges;

		public Input(int numOfNodes, int[][] edges) {
			this.numOfNodes = numOfNodes;
			this.edges = edges;
		}
	}
}

class Solution {

	private int checkCnt;
	private PriorityQueue<int[]> q;
	private int[] groupOf;
	private int result;
	private int maxCost;

	public int solution(int numOfNodes, int[][] edges) {
		init(numOfNodes, edges);
		calc();
		return result - maxCost;
	}

	private void init(int numOfNodes, int[][] edges) {
		this.checkCnt = numOfNodes - 1;
		this.q = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
		this.groupOf = new int[numOfNodes + 1];
		this.result = 0;
		this.maxCost = 0;


		for (int[] edge : edges) {
			q.offer(edge);
		}

		for (int i = 0; i < numOfNodes + 1; i++) {
			groupOf[i] = i;
		}
	}

	private void calc() {
		while (!q.isEmpty()) {
			int[] cur = q.poll();

			int from = cur[0];
			int to = cur[1];

			if (isConnected(from, to)) continue;

			connect(from, to);
			result += cur[2];
			maxCost = Math.max(maxCost, cur[2]);
			checkCnt--;
			if (checkCnt == 0) break;
		}
	}

	private boolean isConnected(int node1, int node2) {
		return getGroupOf(node1) == getGroupOf(node2);
	}

	private int getGroupOf(int node) {
		if (groupOf[node] != node) return groupOf[node] = getGroupOf(groupOf[node]);
		return node;
	}

	private void connect(int node1, int node2) {
		int group1 = getGroupOf(node1);
		int group2 = getGroupOf(node2);
		groupOf[Math.max(group1, group2)] = Math.min(group1, group2);
	}
}
package main.BJ1922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

			int numOfNodes = Integer.parseInt(br.readLine());

			int numOfEdges = Integer.parseInt(br.readLine());

			int[][] edges = new int[numOfEdges][3];

			for (int i = 0; i < numOfEdges; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
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

	private int cnt;
	private int[] groupOf;
	private PriorityQueue<int[]> q;
	private int minValue;


	public int solution(int numOfNodes, int[][] edges) {
		init(numOfNodes, edges);
		calc();
		return minValue;
	}

	private void init(int numOfNodes, int[][] edges) {
		this.cnt = numOfNodes - 1;
		this.groupOf = new int[numOfNodes + 1];
		this.q = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
		this.minValue = 0;

		for (int i = 0; i < groupOf.length; i++) {
			groupOf[i] = i;
		}

		for (int[] edge : edges) {
			q.offer(edge);
		}
	}

	private void calc() {
		while (!q.isEmpty()) {
			int[] cur = q.poll();

			int from = cur[0];
			int to = cur[1];

			if(isConnected(from, to)) continue;

			connect(from, to);
			minValue += cur[2];
			cnt--;

			if(cnt == 0) continue;
		}
	}

	private boolean isConnected(int node1, int node2) {
		return getGroupOf(node1) == getGroupOf(node2);
	}

	private int getGroupOf(int node) {
		if(node != groupOf[node]) return groupOf[node] = getGroupOf(groupOf[node]);
		return node;
	}

	private void connect(int node1, int node2) {
		int group1 = getGroupOf(node1);
		int group2 = getGroupOf(node2);
		groupOf[Math.max(group1, group2)] = Math.min(group1, group2);
	}
}
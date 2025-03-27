package main.BJ1197;

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
		System.out.println(s.solution(ip.numOfNodes, ip.infos));
	}

	private Input readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int numOfNodes = Integer.parseInt(st.nextToken());
			int numOfEdges = Integer.parseInt(st.nextToken());

			int[][] infos = new int[numOfEdges][3];

			for (int i = 0; i < numOfEdges; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				infos[i][0] = from;
				infos[i][1] = to;
				infos[i][2] = cost;
			}

			return new Input(numOfNodes, infos);

		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	private static class Input {

		final int numOfNodes;
		final int[][] infos;

		public Input(int numOfNodes, int[][] infos) {
			this.numOfNodes = numOfNodes;
			this.infos = infos;
		}
	}
}

class Solution {

	private int cnt;
	private int[] groupOf;
	private PriorityQueue<int[]> q;
	private long weight;

	public long solution(int numOfNodes, int[][] infos) {
		init(numOfNodes, infos);
		calc();
		return weight;
	}

	private void init(int numOfNodes, int[][] infos) {
		this.cnt = numOfNodes - 1;
		this.groupOf = new int[numOfNodes + 1];
		this.q = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
		this.weight = 0;

		for (int i = 0; i < groupOf.length; i++) {
			groupOf[i] = i;
		}

		for (int[] info : infos) {
			q.offer(info);
		}
	}

	private void calc() {
		while (!q.isEmpty()) {
			int[] cur = q.poll();

			int from = cur[0];
			int to = cur[1];

			if(isConnected(from, to)) continue;

			connect(from, to);
			weight += cur[2];
			cnt--;

			if(cnt == 0) return;
		}
	}

	private boolean isConnected(int node1, int node2) {
		return getGroupOf(node1) == getGroupOf(node2);
	}

	private int getGroupOf(int node) {
		if(groupOf[node] != node) return groupOf[node] = getGroupOf(groupOf[node]);
		return node;
	}

	private void connect(int node1, int node2) {
		int group1 = getGroupOf(node1);
		int group2 = getGroupOf(node2);

		groupOf[Math.max(group1, group2)] = groupOf[Math.min(group1, group2)];
	}
}
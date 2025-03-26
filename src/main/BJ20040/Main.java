package main.BJ20040;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		Solution s = new Solution();
		Input ip = readInput();
		System.out.println(s.solution(ip.numOfNodes, ip.draws));
	}

	private Input readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int numOfNodes = Integer.parseInt(st.nextToken());
			int numOfEdges = Integer.parseInt(st.nextToken());

			int[][] draws = new int[numOfEdges][2];

			for (int i = 0; i < numOfEdges; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				draws[i][0] = from;
				draws[i][1] = to;
			}

			return new Input(numOfNodes, draws);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static class Input {

		final int numOfNodes;
		final int[][] draws;

		public Input(int numOfNodes, int[][] draws) {
			this.numOfNodes = numOfNodes;
			this.draws = draws;
		}
	}
}

class Solution {

	private int[] groupOf;

	public int solution(int numOfNodes, int[][] draws) {
		init(numOfNodes);

		int turn = 0;

		for (int[] draw : draws) {
			turn++;
			if (isSameGroup(draw)) {
				return turn;
			}

			connect(draw);
		}

		return 0;
	}

	private void init(int numOfNodes) {
		this.groupOf = new int[numOfNodes];
		for (int i = 0; i < numOfNodes; i++) {
			groupOf[i] = i;
		}
	}

	private boolean isSameGroup(int[] draw) {
		return getGroupOf(draw[0]) == getGroupOf(draw[1]);
	}

	private int getGroupOf(int node) {
		if(groupOf[node] != node) return groupOf[node] = getGroupOf(groupOf[node]);
		return groupOf[node];
	}

	private void connect(int[] draw) {
		int group1 = getGroupOf(draw[0]);
		int group2 = getGroupOf(draw[1]);

		groupOf[Math.max(group1, group2)] = Math.min(group1, group2);
	}
}
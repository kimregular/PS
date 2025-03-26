package main.SWE1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	private void run() {
		Resolver r = new Resolver();
		StringBuilder answer = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			for (int TEST_CASE = 0; TEST_CASE < 10; TEST_CASE++) {
				Input ip = readInput(br);
				answer.append("#").append(TEST_CASE + 1).append(" ").append(r.resolve(ip.nodes, ip.startNode)).append("\n");
			}
			System.out.println(answer);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int len = Integer.parseInt(st.nextToken());
		int startNode = Integer.parseInt(st.nextToken());

		String[] nodes = new String[len];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < len; i++) {
			nodes[i] = st.nextToken();
		}

		return new Input(nodes, startNode);
	}

	private static class Input {

		final String[] nodes;
		final int startNode;

		public Input(String[] nodes, int startNode) {
			this.nodes = nodes;
			this.startNode = startNode;
		}
	}
}

class Resolver {

	private static final int MAX_SIZE = 101;

	private List<List<Integer>> graph;
	private boolean[] checked;
	private int result;
	private List<List<Integer>> last;

	public int resolve(String[] nodes, int startNode) {
		init(nodes);
		explore(startNode);
		calcResult();
		return result;
	}

	private void init(String[] nodes) {
		this.graph = getGraphOf(nodes);
		this.checked = new boolean[MAX_SIZE];
		this.result = Integer.MIN_VALUE;
		this.last = new ArrayList<>();
	}

	private List<List<Integer>> getGraphOf(String[] nodes) {
		List<List<Integer>> result = new ArrayList<>();
		for (int i = 0; i < MAX_SIZE; i++) {
			result.add(new ArrayList<>());
		}

		for (int i = 0; i < nodes.length; i+=2) {
			int from = Integer.parseInt(nodes[i]);
			int to = Integer.parseInt(nodes[i + 1]);
			result.get(from).add(to);
		}
		return result;
	}

	private void explore(int startNode) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{startNode, 0});
		checked[startNode] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			while (last.size() <= cur[1]) {
				last.add(new ArrayList<>());
			}

			last.get(cur[1]).add(cur[0]);

			for (Integer nextNode : graph.get(cur[0])) {
				if(checked[nextNode]) continue;
				checked[nextNode] = true;
				q.offer(new int[] {nextNode, cur[1] + 1});
			}
		}
	}

	private void calcResult() {
		int max = 0;
		List<Integer> targets = last.get(last.size() - 1);
		for (Integer target : targets) {
			max = Math.max(max, target);
		}
		result = max;
	}
}

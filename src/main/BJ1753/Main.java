package main.BJ1753;

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

			Input ip = readInput(br);
			Solution s = new Solution();
			System.out.println(s.solution(ip.startNode, ip.graph));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numOfNodes = Integer.parseInt(st.nextToken());
		int numOfLinks = Integer.parseInt(st.nextToken());
		int startNode = Integer.parseInt(br.readLine());

		List<List<int[]>> graph = new ArrayList<>();
		for (int i = 0; i < numOfNodes + 1; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < numOfLinks; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(from).add(new int[]{to, cost});
		}

		return new Input(startNode, graph);
	}

	private static class Input {

		final int startNode;
		final List<List<int[]>> graph;

		public Input(int startNode, List<List<int[]>> graph) {
			this.startNode = startNode;
			this.graph = graph;
		}
	}
}

class Solution {

	private List<List<int[]>> graph;
	private int[] dist;

	public String solution(int startNode, List<List<int[]>> graph) {
		init(graph);
		explore(startNode);
		return getAnswer();
	}

	private void init(List<List<int[]>> graph) {
		this.graph = graph;
		this.dist = new int[graph.size()];
		Arrays.fill(dist, Integer.MAX_VALUE);
	}

	private void explore(int startNode) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
		pq.offer(new int[]{startNode, 0});
		dist[startNode] = 0;

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();

			if (cur[1] > dist[cur[0]]) continue;

			for (int[] nextNode : graph.get(cur[0])) {
				if (dist[nextNode[0]] <= cur[1] + nextNode[1]) continue;
				dist[nextNode[0]] = cur[1] + nextNode[1];
				pq.offer(new int[]{nextNode[0], cur[1] + nextNode[1]});
			}
		}
	}

	private String getAnswer() {
		StringBuilder result = new StringBuilder();

		for (int i = 1; i < dist.length; i++) {
			result.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append("\n");
		}
		return result.toString();
	}
}

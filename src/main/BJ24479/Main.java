package main.BJ24479;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Solution s = new Solution();
			Input ip = readInput(br);
			System.out.println(s.solution(ip.startNode, ip.graph));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numOfNodes = Integer.parseInt(st.nextToken());
		int numOfLinks = Integer.parseInt(st.nextToken());
		int startNode = Integer.parseInt(st.nextToken());

		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < numOfNodes + 1; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < numOfLinks; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(to);
			graph.get(to).add(from);
		}

		for (List<Integer> nodes : graph) {
			nodes.sort(null);
		}
		return new Input(startNode, graph);
	}

	private static class Input {

		final int startNode;
		final List<List<Integer>> graph;

		public Input(int startNode, List<List<Integer>> graph) {
			this.startNode = startNode;
			this.graph = graph;
		}
	}
}

class Solution {

	private List<List<Integer>> graph;
	private int[] visited;
	private StringBuilder result;
	private int cnt;

	public String solution(int startNode, List<List<Integer>> graph) {
		init(graph);
		explore(startNode);
		return getResult();
	}

	private void init(List<List<Integer>> graph) {
		this.graph = graph;
		this.visited = new int[graph.size()];
		this.result = new StringBuilder();
		this.cnt = 1;
	}

	private void explore(int node) {
		visited[node] = cnt++;

		for (Integer nextNode : graph.get(node)) {
			if(visited[nextNode] != 0) continue;
			explore(nextNode);
		}
	}

	private String getResult() {
		for (int i = 1; i < visited.length; i++) {
			result.append(visited[i]).append("\n");
		}
		result.setLength(result.length() - 1);
		return result.toString();
	}
}


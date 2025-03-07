package main.SWE1267;

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
		StringBuilder result = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int TEST_CASES = 10;
			for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
				result.append("#").append(TEST_CASE + 1).append(" ").append(r.resolve(readInput(br))).append("\n");
			}

			System.out.println(result.toString().trim());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private List<List<Integer>> readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numOfNodes = Integer.parseInt(st.nextToken());
		int numOfEdges = Integer.parseInt(st.nextToken());

		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= numOfNodes; i++) graph.add(new ArrayList<>());

		st = new StringTokenizer(br.readLine());

		while (st.hasMoreTokens()) {
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			graph.get(from).add(to);
		}

		return graph;
	}
}

class Resolver {

	private List<List<Integer>> graph;
	private int[] indegrees;
	private Queue<Integer> q;
	private StringBuilder result;

	public String resolve(List<List<Integer>> graph) {
		init(graph);
		calc();
		return result.toString();
	}

	private void init(List<List<Integer>> graph) {
		this.graph = graph;
		this.indegrees = new int[graph.size()];
		this.q = new ArrayDeque<>();
		this.result = new StringBuilder();

		for (int i = 1; i < graph.size(); i++) {
			for (Integer nodes : graph.get(i)) {
				indegrees[nodes]++;
			}
		}

		for (int i = 1; i < indegrees.length; i++) {
			if(indegrees[i] == 0) q.offer(i);
		}
	}

	private void calc() {
		while (!q.isEmpty()) {
			int cur = q.poll();

			if (indegrees[cur] == 0) {
				result.append(cur).append(" ");
			}

			for (Integer nextNode : graph.get(cur)) {
				indegrees[nextNode]--;
				if(indegrees[nextNode] == 0) q.offer(nextNode);
			}
		}
	}
}
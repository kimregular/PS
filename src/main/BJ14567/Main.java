package main.BJ14567;

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

			Solution s = new Solution();
			System.out.println(s.solution(readInput(br)));

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

		for (int i = 0; i < numOfEdges; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(to);
		}
		return graph;
	}
}

class Solution {

	private List<List<Integer>> graph;
	private int[] indegreeOf;
	private Queue<Integer> q;
	private int[] result;

	public String solution(List<List<Integer>> graph) {
		init(graph);
		calc();
		return getAnswer();
	}

	private void init(List<List<Integer>> graph) {
		this.graph = graph;
		this.indegreeOf = new int[graph.size()];
		this.q = new ArrayDeque<>();
		this.result = new int[graph.size()];

		for (int i = 1; i < graph.size(); i++) {
			for (Integer node : graph.get(i)) {
				indegreeOf[node]++;
			}
		}

		for (int i = 1; i < indegreeOf.length; i++) {
			if(indegreeOf[i] == 0) q.offer(i);
		}
	}

	private void calc() {
		int semester = 1;
		while (!q.isEmpty()) {

			int len = q.size();

			for (int i = 0; i < len; i++) {
				int cur = q.poll();

				if (indegreeOf[cur] == 0) {
					result[cur] = semester;
				}

				for (Integer nextNode : graph.get(cur)) {
					indegreeOf[nextNode]--;
					if(indegreeOf[nextNode] == 0) q.offer(nextNode);
				}
			}
			semester++;
		}
	}

	private String getAnswer() {
		StringBuilder answer = new StringBuilder();
		for (int i = 1; i < result.length; i++) {
			answer.append(result[i]).append(" ");
		}
		return answer.toString();
	}
}
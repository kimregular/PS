package src.main.BJ11725;

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
		int len = Integer.parseInt(br.readLine());
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= len; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 1; i < len; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(to);
			graph.get(to).add(from);
		}
		return graph;
	}
}

class Solution {

	private List<List<Integer>> graph;
	private int[] parents;

	public String solution(List<List<Integer>> graph) {
		init(graph);
		explore();
		return getAnswer();
	}

	private void init(List<List<Integer>> graph) {
		this.graph = graph;
		this.parents = new int[graph.size()];
	}

	private void explore() {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(1);
		parents[1] = 1;

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (Integer next : graph.get(cur)) {
				if(parents[next] != 0) continue;
				parents[next] = cur;
				q.offer(next);
			}
		}
	}

	private String getAnswer() {
		StringBuilder answer = new StringBuilder();
		for (int i = 2; i < parents.length; i++) {
			answer.append(parents[i]).append("\n");
		}
		return answer.toString();
	}

}
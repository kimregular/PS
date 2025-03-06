package main.BJ1068;

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
			System.out.println(s.solution(ip.root, ip.graph, ip.cut));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		int len = Integer.parseInt(br.readLine());
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < len; i++) graph.add(new ArrayList<>());

		int root = -1;

		int node = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			int parent = Integer.parseInt(st.nextToken());

			if(parent == -1) {
				root = node;
				node++;
				continue;
			}

			graph.get(parent).add(node);
			node++;
		}

		int cut = Integer.parseInt(br.readLine());

		return new Input(root, graph, cut);
	}

	private static class Input {

		final int root;
		final List<List<Integer>> graph;
		final int cut;

		public Input(int root, List<List<Integer>> graph, int cut) {
			this.root = root;
			this.graph = graph;
			this.cut = cut;
		}
	}
}

class Solution {

	private List<List<Integer>> graph;
	private boolean[] visited;
	private int result;

	public int solution(int startNode, List<List<Integer>> graph, int cut) {
		init(graph, cut);
		explore(startNode);
		return result;
	}

	private void init(List<List<Integer>> graph, int cut) {
		this.graph = graph;
		this.visited = new boolean[graph.size()];
		this.result = 0;
		this.graph.get(cut).clear();
		this.visited[cut] = true;
	}

	private void explore(int startNode) {
		if(visited[startNode]) return;
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(startNode);
		visited[startNode] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();

			if (isLeaf(cur)) {
				result++;
				continue;
			}


			for (Integer nextNode : graph.get(cur)) {
				if(visited[nextNode]) continue;
				q.offer(nextNode);
				visited[nextNode] = true;
			}
		}
	}

	private boolean isLeaf(int node) {
		for (Integer child : graph.get(node)) {
			if(!visited[child]) return false;
		}
		return true;
	}
}


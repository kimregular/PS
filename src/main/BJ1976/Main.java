package main.BJ1976;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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
			System.out.println(s.solution(ip.graph, ip.cities));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		int numOfNodes = Integer.parseInt(br.readLine());
		br.readLine();
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= numOfNodes; i++) graph.add(new ArrayList<>());

		for (int i = 0; i < numOfNodes; i++) {
			int idx = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				int target = Integer.parseInt(st.nextToken());
				idx++;
				if(target == 0) continue;
				graph.get(i + 1).add(idx);
			}
		}

		List<Integer> cities = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			cities.add(Integer.parseInt(st.nextToken()));
		}

		return new Input(graph, cities);
	}

	private static class Input {

		final List<List<Integer>> graph;
		final List<Integer> cities;

		public Input(List<List<Integer>> graph, List<Integer> cities) {
			this.graph = graph;
			this.cities = cities;
		}
	}
}

class Solution {

	private List<List<Integer>> graph;
	private int[] groups;

	public String solution(List<List<Integer>> graph, List<Integer> cities) {
		init(graph);
		calc();

		int flag = getGroupOf(cities.get(0));

		for (int i = 1; i < cities.size(); i++) {
			int nextCity = getGroupOf(cities.get(i));

			if(nextCity != flag) return "NO";
		}
		return "YES";
	}

	private void init(List<List<Integer>> graph) {
		this.graph = graph;
		this.groups = new int[graph.size()];

		for (int i = 0; i < groups.length; i++) groups[i] = i;
	}

	private void calc() {
		for (int from = 1; from < graph.size(); from++) {
			List<Integer> children = graph.get(from);

			for (int child = 0; child < children.size(); child++) {
				int to = children.get(child);
				if(to == 0) continue;

				int fromGroup = getGroupOf(from);
				int toGroup = getGroupOf(to);

				if(fromGroup == toGroup) continue;

				connect(from, to);
			}
		}
	}

	private int getGroupOf(int node) {
		if(groups[node] != node) return getGroupOf(groups[node]);
		return groups[node];
	}

	private void connect(int node1, int node2) {
		int group1 = getGroupOf(node1);
		int group2 = getGroupOf(node2);
		groups[Math.max(group1, group2)] = Math.min(group1, group2);
	}
}

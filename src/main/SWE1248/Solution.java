package src.main.SWE1248;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	public void run() {

		StringBuilder answer = new StringBuilder();
		Resolver r = new Resolver();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int TEST_CASES = Integer.parseInt(br.readLine());
			for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
				Input ip = readInput(br);
				answer.append("#").append(TEST_CASE + 1).append(" ")
						.append(r.resolve(ip.target1, ip.target2, ip.parentGraph, ip.childGraph)).append("\n");
			}

			System.out.println(answer);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numOfNodes = Integer.parseInt(st.nextToken());
		int numOfLinks = Integer.parseInt(st.nextToken());
		int target1 = Integer.parseInt(st.nextToken());
		int target2 = Integer.parseInt(st.nextToken());

		List<List<Integer>> parentGraph = new ArrayList<>();
		List<List<Integer>> childGraph = new ArrayList<>();
		for (int i = 0; i <= numOfNodes; i++) {
			parentGraph.add(new ArrayList<>());
			childGraph.add(new ArrayList<>());
		}

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < numOfLinks; i++) {
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			parentGraph.get(to).add(from);
			childGraph.get(from).add(to);
		}

		return new Input(target1, target2, parentGraph, childGraph);
	}

	private static class Input {

		final int target1;
		final int target2;
		final List<List<Integer>> parentGraph;
		final List<List<Integer>> childGraph;

		public Input(int target1, int target2, List<List<Integer>> parentGraph, List<List<Integer>> childGraph) {
			this.target1 = target1;
			this.target2 = target2;
			this.parentGraph = parentGraph;
			this.childGraph = childGraph;
		}
	}
}

class Resolver {

	private List<List<Integer>> parentGraph;
	private List<List<Integer>> childGraph;
	private int[] nodeCheck;
	private int targetParent = 1;
	private int parentWidth = 0;

	public String resolve(int target1, int target2, List<List<Integer>> parentGraph, List<List<Integer>> childGraph) {
		init(parentGraph, childGraph);
		checkParent(target1);
		checkParent(target2);
		getSubgraphWidth(targetParent);
		return getAnswer();
	}

	private void init(List<List<Integer>> parentGraph, List<List<Integer>> childGraph) {
		this.parentGraph = parentGraph;
		this.childGraph = childGraph;
		this.nodeCheck = new int[parentGraph.size()];
	}

	private void checkParent(int startNode) {
		boolean[] visited = new boolean[parentGraph.size()];
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(startNode);
		visited[startNode] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();

			if(nodeCheck[cur] == 2) {
				targetParent = cur;
				return;
			}

			for (Integer next : parentGraph.get(cur)) {
				if (visited[next]) continue;
				q.offer(next);
				visited[next] = true;
				nodeCheck[next]++;
			}
		}
	}

	private void getSubgraphWidth(int startNode) {
		boolean[] visited = new boolean[childGraph.size()];
		Queue<Integer> q = new ArrayDeque<>();
		int cnt = 1;
		q.offer(startNode);
		visited[startNode] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (Integer next : childGraph.get(cur)) {
				if(visited[next]) continue;
				visited[next] = true;
				cnt++;
				q.offer(next);
			}
		}
		parentWidth = cnt;
	}

	private String getAnswer() {
		return String.format("%d %d", targetParent, parentWidth);
	}
}
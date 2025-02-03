package src.BJ1389;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

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

	private int[][] readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numOfNodes = Integer.parseInt(st.nextToken());
		int numOfLinks = Integer.parseInt(st.nextToken());

		int[][] graph = new int[numOfNodes + 1][numOfNodes + 1];
		for (int i = 0; i < numOfNodes + 1; i++) {
			Arrays.fill(graph[i], 5_0001);
		}

		for (int i = 0; i < numOfLinks; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			graph[from][to] = 1;
			graph[to][from] = 1;
		}

		return graph;
	}
}

class Solution {

	public int solution(int[][] graph) {
		for (int k = 1; k < graph.length; k++) {
			for (int i = 1; i < graph.length; i++) {
				for (int j = 1; j < graph.length; j++) {
					if (graph[i][k] + graph[k][j] < graph[i][j]) {
						graph[i][j] = graph[i][k] + graph[k][j];
					}
				}
			}
		}
		return getAnswer(graph);
	}

	private int getAnswer(int[][] graph) {
		int result = 0;
		int value = Integer.MAX_VALUE;

		for (int i = 1; i < graph.length; i++) {
			int candidate = 0;
			for (int j = 1; j < graph.length; j++) {
				if(i == j) continue;
				candidate += graph[i][j];
			}
			if (candidate < value) {
				value = candidate;
				result = i;
			}
		}
		return result;
	}
}
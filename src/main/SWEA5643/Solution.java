package main.SWEA5643;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	private void run() {
		StringBuilder answer = new StringBuilder();
		Resolver r = new Resolver();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int TEST_CASES = Integer.parseInt(br.readLine());
			for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
				answer.append("#").append(TEST_CASE + 1).append(" ").append(r.resolve(readInput(br))).append("\n");
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}
		System.out.println(answer);
	}

	private int[][] readInput(BufferedReader br) throws IOException {
		int nodes = Integer.parseInt(br.readLine());
		int numOfEdges = Integer.parseInt(br.readLine());

		int[][] graph = new int[nodes + 1][nodes + 1];

		for (int i = 0; i < numOfEdges; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from][to] = 1;
		}

		return graph;
	}
}

class Resolver {
	private static final int MAX_VALUE = 502;
	private int[][] graph;
	private int result;

	public int resolve(int[][] graph) {
		init(graph);
		FW();
		count();
		return result;
	}

	private void init(int[][] graph) {
		for (int i = 1; i < graph.length; i++) {
			for (int j = 1; j < graph.length; j++) {
				if (i == j) continue;
				if (graph[i][j] == 0) graph[i][j] = MAX_VALUE;
			}
		}
		this.graph = graph;
		this.result = 0;
	}

	private void FW() {
		for (int k = 1; k < graph.length; k++) {
			for (int i = 1; i < graph.length; i++) {
				for (int j = 1; j < graph.length; j++) {
					if(i == k || j == k) continue;
					if (graph[i][k] != MAX_VALUE && graph[k][j] != MAX_VALUE) {
						graph[i][j] = 1; // 연결 여부만 체크
					}
				}
			}
		}
	}

	private void count() {
		int numOfStudents = graph.length - 1; // 학생 수 조정

		for (int i = 1; i < graph.length; i++) {
			int taller = 0;
			int shorter = 0;
			for (int j = 1; j < graph.length; j++) {
				if (graph[i][j] == 1) shorter++;
				if (graph[j][i] == 1) taller++;
			}

			if (taller + shorter == numOfStudents - 1) { // 정확한 키 순서를 아는 경우만 카운트
				result++;
			}
		}
	}
}


package main.BJ11404;

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
		Input ip = readInput();
		System.out.println(new Solution().solution(ip.cities, ip.edges));
	}

	private Input readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int cities = Integer.parseInt(br.readLine());
			int len = Integer.parseInt(br.readLine());
			int[][] edges = new int[len][3];

			for (int i = 0; i < len; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				edges[i][0] = from;
				edges[i][1] = to;
				edges[i][2] = cost;
			}

			return new Input(cities, edges);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	private static class Input {

		final int cities;
		final int[][] edges;

		public Input(int cities, int[][] edges) {
			this.cities = cities;
			this.edges = edges;
		}
	}
}

class Solution {

	private static final int MAX = 100_000_000;

	private int[][] map;

	public String solution(int cities, int[][] edges) {
		init(cities, edges);
		FW();
		return getAnswer();
	}

	private void init(int cities, int[][] edges) {
		this.map = new int[cities + 1][cities + 1];

		for (int[] edge : edges) {
			int from = edge[0];
			int to = edge[1];
			int cost = edge[2];
			if (map[from][to] != 0) {
				map[from][to] = Math.min(map[from][to], cost);
			} else {
				map[from][to] = cost;
			}
		}

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if(i == j) continue;
				if(map[i][j] != 0) continue;
				map[i][j] = MAX;
			}
		}

	}

	private void FW() {
		for (int k = 1; k < map.length; k++) {
			for (int i = 1; i < map.length; i++) {
				for (int j = 1; j < map.length; j++) {
					if(k == i || k == j || i == j) continue;
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
	}

	private String getAnswer() {
		StringBuilder result = new StringBuilder();
		for (int i = 1; i < map.length; i++) {
			for (int j = 1; j < map.length; j++) {
				result.append(map[i][j] == MAX ? 0 : map[i][j]).append(" ");
			}
			result.append("\n");
		}
		return result.toString();
	}
}
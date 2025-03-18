package main.BJ11651;

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

		Solution s = new Solution();
		System.out.println(s.solution(readInput()));
	}

	private int[][] readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int len = Integer.parseInt(br.readLine());
			int[][] result = new int[len][2];

			for (int i = 0; i < len; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				result[i][0] = x;
				result[i][1] = y;
			}
			return result;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

class Solution {

	public String solution(int[][] targets) {
		Coordinate[] coordinates = new Coordinate[targets.length];
		for (int i = 0; i < targets.length; i++) {
			coordinates[i] = new Coordinate(targets[i][0], targets[i][1]);
		}

		Arrays.sort(coordinates);

		StringBuilder result = new StringBuilder();

		for (Coordinate coordinate : coordinates) {
			result.append(coordinate.x).append(" ").append(coordinate.y).append("\n");
		}
		return result.toString();
	}

	private static class Coordinate implements Comparable<Coordinate> {

		final int x;
		final int y;

		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Coordinate o) {
			if (y == o.y) return Integer.compare(x, o.x);
			return Integer.compare(y, o.y);
		}
	}
}

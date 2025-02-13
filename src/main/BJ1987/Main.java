package main.BJ1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
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

	private char[][] readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		char[][] result = new char[height][width];
		for (int i = 0; i < height; i++) {
			result[i] = br.readLine().toCharArray();
		}
		return result;
	}
}

class Solution {

	private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	private char[][] field;
	private Set<Character> used;
	private int result;

	public int solution(char[][] input) {
		init(input);
		explore(0, 0, 1);
		return result;
	}

	private void init(char[][] input) {
		this.field = input;
		this.used = new HashSet<>();
		this.result = 0;
	}

	private void explore(int x, int y, int size) {
		useLetter(x, y);

		if (size > result) {
			result = size;
		}

		for (int[] direction : DIRECTIONS) {
			int nx = x + direction[0];
			int ny = y + direction[1];

			if(isOutOfBounds(nx, ny)) continue;
			if(usedLetter(nx, ny)) continue;
			useLetter(nx, ny);
			explore(nx, ny, size + 1);
			releaseLetter(nx, ny);
		}

		releaseLetter(x, y);
	}

	private boolean usedLetter(int x, int y) {
		return used.contains(field[x][y]);
	}

	private void useLetter(int x, int y) {
		used.add(field[x][y]);
	}

	private void releaseLetter(int x, int y) {
		used.remove(field[x][y]);
	}

	private boolean isOutOfBounds(int x, int y) {
		return x < 0 || x >= field.length || y < 0 || y >= field[x].length;
	}
}
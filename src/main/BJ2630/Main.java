package main.BJ2630;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
			int[][] paper = new int[len][len];

			for (int i = 0; i < len; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < len; j++) {
					paper[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			return paper;
		}catch(IOException e) {
			throw new RuntimeException();
		}
	}
}

class Solution {

	private static final char WHITE = 0;

	private int[][] paper;
	private int whiteCnt;
	private int blueCnt;

	public String solution(int[][] paper) {
		init(paper);
		calc( 0, 0, paper.length);
		return whiteCnt + "\n" + blueCnt;
	}

	private void init(int[][] paper) {
		this.paper = paper;
		this.whiteCnt = 0;
		this.blueCnt = 0;
	}

	private void calc(int x, int y, int size) {
		if (isOneColored(x, y, size)) {
			makeCount(x, y);
			return;
		}

		int half = size / 2;

		calc(x, y + half, half); // 1
		calc(x, y, half); // 2
		calc(x + half, y, half); // 3
		calc(x + half, y + half, half); // 4
	}

	private boolean isOneColored(int x, int y, int size) {
		int first = paper[x][y];

		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if(paper[i][j] != first) return false;
			}
		}
		return true;
	}

	private void makeCount(int x, int y) {
		int color = paper[x][y];
		if (color == WHITE) {
			whiteCnt++;
		} else {
			blueCnt++;
		}
	}
}
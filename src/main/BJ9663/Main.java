package main.BJ9663;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

	private int readInput(BufferedReader br) throws IOException {
		return Integer.parseInt(br.readLine());
	}
}

class Solution {

	private int N;
	private boolean[] col;
	private boolean[] slash;
	private boolean[] bSlash;
	private int result;

	public int solution(int N) {
		init(N);
		setQueens(1);
		return result;
	}

	private void init(int N) {
		this.N = N;
		this.col = new boolean[N + 1]; // 0은 사용하지 않는다.
		this.slash = new boolean[N + N + 1];
		this.bSlash = new boolean[N + N];
		this. result = 0;
	}

	private void setQueens(int rowNum) {
		if (rowNum == col.length) {
			result++;
			return;
		}

		for (int nextCol = 1; nextCol <= N; nextCol++) {
			if(isUnavailable(rowNum, nextCol)) continue;
			col[nextCol] = slash[rowNum + nextCol] = bSlash[rowNum - nextCol + N] = true;
			setQueens(rowNum + 1);
			col[nextCol] = slash[rowNum + nextCol] = bSlash[rowNum - nextCol + N] = false;
		}
	}

	private boolean isUnavailable(int rowNum, int nextCol) {
		return col[nextCol] || slash[rowNum + nextCol] || bSlash[rowNum - nextCol + N];
	}
}
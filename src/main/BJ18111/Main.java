package main.BJ18111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		Solution s = new Solution();
		Input ip = readInput();
		System.out.println(s.solution(ip.min, ip.max, ip.blocks, ip.field));
	}

	private Input readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int height = Integer.parseInt(st.nextToken());
			int width = Integer.parseInt(st.nextToken());
			int blocks = Integer.parseInt(st.nextToken());

			int[][] field = new int[height][width];

			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;

			for (int i = 0; i < height; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < width; j++) {
					int v = Integer.parseInt(st.nextToken());
					min = Math.min(min, v);
					max = Math.max(max, v);
					field[i][j] = v;
				}
			}

			return new Input(min, max, blocks, field);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static class Input {

		final int min;
		final int max;
		final int blocks;
		final int[][] field;

		public Input(int min, int max, int blocks, int[][] field) {
			this.min = min;
			this.max = max;
			this.blocks = blocks;
			this.field = field;
		}
	}
}

class Solution {

	private static final int DIG = 2;

	private int blocks;
	private int[][] field;
	private int finalRequire;
	private int finalHeight;

	public String solution(int min, int max, int blocks, int[][] field) {
		init(blocks, field);

		for (int height = min; height <= max; height++) {
			calc(height);
		}

		return finalRequire + " " + finalHeight;
	}

	private void init(int blocks, int[][] field) {
		this.blocks = blocks;
		this.field = field;
		this.finalRequire = Integer.MAX_VALUE;
		this.finalHeight = Integer.MIN_VALUE;
	}

	private void calc(int height) {
		int require = 0;
		int pocket = blocks;


		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j] > height) {
					int differ = field[i][j] - height;
					pocket += differ;
					require += differ * DIG;
				} else if (field[i][j] < height) {
					int differ = height - field[i][j];
					pocket -= differ;
					require += differ;
				}
			}
		}

		if(pocket < 0) return;

		if (require < finalRequire) {
			finalRequire = require;
			finalHeight = height;
		} else if (require == finalRequire) {
			finalHeight = height;
		}
	}
}


package main.BJ1535;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		int len = Integer.parseInt(br.readLine());
		int[][] result = new int[len][2];

		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());

		for (int i = 0; i < len; i++) {
			result[i][0] = Integer.parseInt(st1.nextToken());
			result[i][1] = Integer.parseInt(st2.nextToken());
		}
		return result;
	}
}

class Solution {

	private int health;
	private int[][] targets;
	private int result;

	public int solution(int[][] targets) {
		init(targets);
		calc(0, 0, 0);
		return result;
	}

	private void init(int[][] targets) {
		this.targets = targets;
		this.health = 100;
	}

	private void calc(int idx, int fatigue, int happy) {
		if(fatigue >= health) return;

		if(idx == targets.length) {
			if (result < happy) {
				result = happy;
			}
			return;
		}

		calc(idx + 1, fatigue, happy);
		calc(idx + 1, fatigue + targets[idx][0], happy + targets[idx][1]);
	}
}
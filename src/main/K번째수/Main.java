package main.K번째수;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {

		int[] array = new int[]{1, 5, 2, 6, 3, 7, 4};
		int[][] commands = new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};

		Solution s = new Solution();
		System.out.println(Arrays.toString(s.solution(array, commands)));
	}
}

class Solution {
	public int[] solution(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];

		for(int i = 0; i < commands.length; i++) {
			answer[i] = getResult(array, commands[i]);
		}
		return answer;
	}

	private int getResult(int[] array, int[] command) {
		int start = command[0] - 1;
		int end = command[1];
		int idx = command[2] - 1;
		int[] field = Arrays.copyOfRange(array, start, end);
		Arrays.sort(field);
		return field[idx];
	}
}
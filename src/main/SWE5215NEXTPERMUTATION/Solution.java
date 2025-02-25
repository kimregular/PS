package main.SWE5215NEXTPERMUTATION;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	private void run() {

		StringBuilder answer = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int numOfTestCases = Integer.parseInt(br.readLine());
			for (int i = 0; i < numOfTestCases; i++) {
				Input ip = readInput(br);
				Resolver r = new Resolver();
				answer.append("#").append(i + 1).append(" ")
						.append(r.resolve(ip.limit, ip.ingredients)).append("\n");
			}

			System.out.println(answer);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		String[] temp = br.readLine().split(" ");
		int numOfIngredients = Integer.parseInt(temp[0]);
		int limit = Integer.parseInt(temp[1]);

		StringTokenizer st;
		int[][] ingredients = new int[numOfIngredients][2];
		for (int i = 0; i < numOfIngredients; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				ingredients[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		return new Input(limit, ingredients);
	}

	private static class Input {

		private final int limit;
		private final int[][] ingredients;

		public Input(int limit, int[][] ingredients) {
			this.limit = limit;
			this.ingredients = ingredients;
		}
	}
}

class Resolver {

	private int CAL_LIMIT;
	private int[][] ingredients;
	private int[] used;
	private int maxedFavor;


	public int resolve(int limit, int[][] ingredients) {
		init(limit, ingredients);

		for (int i = 0; i <= ingredients.length; i++) {
			initUsed(i);
			do {
				checkMaxedFavor();
			} while (nextPermutate());
		}

		return maxedFavor;
	}

	private void initUsed(int i) {
		Arrays.fill(used, 0);
		for(int j = 0; j < i; j++) {
			used[j] = 1;
		}
		Arrays.sort(used);
	}

	private void init(int limit, int[][] ingredients) {
		this.CAL_LIMIT = limit;
		this.ingredients = ingredients;
		this.used = new int[ingredients.length];
		this.maxedFavor = 0;
	}

	private boolean nextPermutate() {
		int i = used.length - 1;
		while(i > 0 && used[i - 1] >= used[i]) --i;

		if(i == 0) return false;

		int j = used.length - 1;
		while(used[i - 1] >= used[j]) --j;

		swap(i - 1, j);

		int k = used.length - 1;
		while(i < k) swap(i++, k--);

		return true;
	}

	private void checkMaxedFavor() {
		int favorSum = 0;
		int calSum = 0;

		for(int i = 0; i < used.length; i++) {
			if(used[i] == 0) continue;
			favorSum += ingredients[i][0];
			calSum += ingredients[i][1];

			if(calSum >= CAL_LIMIT) return;
		}

		if(maxedFavor < favorSum) {
			maxedFavor = favorSum;
		}
	}

	private void swap(int i, int j) {
		int temp = used[i];
		used[i] = used[j];
		used[j] = temp;
	}
}

package main.SWE5215COMBINATION;

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
	private int maxedFavor;


	public int resolve(int limit, int[][] ingredients) {
		init(limit, ingredients);

		for (int depthLimit = 0; depthLimit <= ingredients.length; depthLimit++) {
			comb(depthLimit, 0, 0, 0, 0);
		}

		return maxedFavor;
	}

	private void init(int limit, int[][] ingredients) {
		this.CAL_LIMIT = limit;
		this.ingredients = ingredients;
		this.maxedFavor = Integer.MIN_VALUE;
	}

	private void comb(int depthLimit, int depth, int start, int favor, int cal) {
		if(cal >= CAL_LIMIT) return;

		if (depth == depthLimit) {
			if (favor > maxedFavor) {
				maxedFavor = favor;
			}
			return;
		}

		for (int i = start; i < ingredients.length; i++) {
			comb(depthLimit, depth + 1, i + 1, favor + ingredients[i][0], cal + ingredients[i][1]);
		}
	}
}

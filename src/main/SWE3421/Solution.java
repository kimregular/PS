package main.SWE3421;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	private void run() {

		StringBuilder result = new StringBuilder();
		Resolver r = new Resolver();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int TEST_CASES = Integer.parseInt(br.readLine());
			for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
				Input ip = readInput(br);
				result.append("#")
						.append(TEST_CASE + 1)
						.append(" ")
						.append(r.resolve(ip.numOfIngredients, ip.forbiddenCombinations))
						.append("\n");
			}

			System.out.println(result.toString().trim());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numOfIngredients = Integer.parseInt(st.nextToken());
		int len = Integer.parseInt(st.nextToken());
		int[][] forbiddenCombinations = new int[len][2];

		for (int i = 0; i < len; i++) {
			st = new StringTokenizer(br.readLine());
			forbiddenCombinations[i][0] = Integer.parseInt(st.nextToken());
			forbiddenCombinations[i][1] = Integer.parseInt(st.nextToken());
		}

		return new Input(numOfIngredients, forbiddenCombinations);
	}

	private static class Input {

		final int numOfIngredients;
		final int[][] forbiddenCombinations;

		public Input(int numOfIngredients, int[][] forbiddenCombinations) {
			this.numOfIngredients = numOfIngredients;
			this.forbiddenCombinations = forbiddenCombinations;
		}
	}
}

class Resolver {

	private int numOfIngredients;
	private boolean[][] forbidden;
	private int result;

	public int resolve(int numOfIngredients, int[][] forbiddenCombinations) {
		init(numOfIngredients, forbiddenCombinations);
		calc(0, new ArrayList<>(), 1);
		return result;
	}

	private void init(int numOfIngredients, int[][] forbiddenCombinations) {
		this.numOfIngredients = numOfIngredients;
		this.forbidden = new boolean[numOfIngredients + 1][numOfIngredients + 1];
		this.result = 0;

		for (int[] forbiddenCombination : forbiddenCombinations) {
			forbidden[forbiddenCombination[0]][forbiddenCombination[1]] = true;
			forbidden[forbiddenCombination[1]][forbiddenCombination[0]] = true;
		}
	}

	private void calc(int cnt, List<Integer> combination, int ingredient) {
		if (invalid(combination)) return;

		if (cnt == numOfIngredients) {
			result++;
			return;
		}

		calc(cnt + 1, combination, ingredient + 1);
		combination.add(ingredient);
		calc(cnt + 1, combination, ingredient + 1);
		combination.remove(combination.size() - 1);
	}

	private boolean invalid(List<Integer> combination) {
		int size = combination.size();

		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				if(forbidden[combination.get(i)][combination.get(j)]) return true;
			}
		}
		return false;
	}
}


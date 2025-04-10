package main.SWEA5656;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	private void run() {
		StringBuilder answer = new StringBuilder();
		Resolver r = new Resolver();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int TEST_CASES = Integer.parseInt(br.readLine());
			for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
				Input ip = readInput(br);
				answer.append("#").append(TEST_CASE + 1).append(" ").append(r.resolve(ip.hit, ip.field)).append("\n");
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}

		System.out.println(answer);
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int hit = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		int height = Integer.parseInt(st.nextToken());

		int[][] field = new int[height][width];

		for (int i = 0; i < height; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < width; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		return new Input(hit, field);
	}

	private static class Input {

		final int hit;
		final int[][] field;

		public Input(int hit, int[][] field) {
			this.hit = hit;
			this.field = field;
		}
	}
}

class Resolver {

	private int hit;
	private int[][] field;
	private int result;

	public int resolve(int hit, int[][] field) {
		init(hit, field);
		calc();
		return result;
	}

	private void init(int hit, int[][] field) {
		this.hit = hit;
		this.field = field;
		this.result = Integer.MAX_VALUE;
	}

	private void calc() {
		PermutationResolver pr = new PermutationResolver(field[0].length, hit);
		for (List<Integer> hitLocations : pr.getResult()) {
			Simulator s = new Simulator(hitLocations, field);
			int testResult = s.getResult();
			if(testResult == 0) {
				result = 0;
				return;
			}
			result = Math.min(result, testResult);
		}
	}
}

class Simulator {

	private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

	private final List<Integer> hitLocations;
	private final int[][] field;
	private final Queue<int[]> q;

	public Simulator(List<Integer> hitLocations, int[][] field) {
		this.hitLocations = hitLocations;
		this.field = new int[field.length][field[0].length];
		this.q = new ArrayDeque<>();

		for (int i = 0; i < field.length; i++) {
			this.field[i] = field[i].clone();
		}
	}

	public int getResult() {
		simulate();
		int result = 0;
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j] != 0) result++;
			}
		}
		return result;
	}

	private void simulate() {
		for (Integer hitLocation : hitLocations) {
			hit(hitLocation);
			hitChainActivate();
			dropBlocks();
		}
	}

	private void hit(int c) {
		for (int i = 0; i < field.length; i++) {
			if (field[i][c] == 0) continue;
			q.offer(new int[]{i, c, field[i][c]});
			return;
		}
	}

	private void hitChainActivate() {
		while (!q.isEmpty()) {
			int[] cur = q.poll();

			field[cur[0]][cur[1]] = 0;

			if (cur[2] == 0) return;
			for (int[] direction : DIRECTIONS) {
				int x = cur[0];
				int y = cur[1];

				for (int i = 1; i < cur[2]; i++) {
					x += direction[0];
					y += direction[1];

					if (out(x, y)) break;
					if (field[x][y] == 0) continue;

					q.offer(new int[]{x, y, field[x][y]});
				}
			}
		}
	}

	private void dropBlocks() {
		int h = field.length - 1;
		int w = field[0].length;

		for (int i = 0; i < w; i++) {
			for (int j = h; j >= 0; j--) {
				if (field[j][i] == 0) continue;
				drop(j, i);
			}
		}
	}

	private void drop(int i, int j) {
		int value = field[i][j];
		field[i][j] = 0;
		while (i + 1 < field.length && field[i + 1][j] == 0) {
			i++;
		}
		field[i][j] = value;
	}

	private boolean out(int x, int y) {
		return x < 0 || x >= field.length || y < 0 || y >= field[x].length;
	}
}

class PermutationResolver {

	private final int n;
	private final int r;
	private final int[] used;
	private int[] permutated;
	private List<List<Integer>> result;

	public PermutationResolver(int n, int r) {
		this.n = n;
		this.r = r;
		this.used = new int[n + 1];
		this.permutated = new int[r];
		this.result = new ArrayList<>();
	}

	public List<List<Integer>> getResult() {
		permutate(0);
		return result;
	}

	private void permutate(int idx) {
		if (idx == r) {
			save();
			return;
		}

		for (int i = 0; i < n; i++) {
			if (used[i] == r) continue;
			used[i]++;
			permutated[idx] = i;
			permutate(idx + 1);
			used[i]--;
		}
	}

	private void save() {
		result.add(new ArrayList<>());
		int idx = result.size() - 1;
		for (int p : permutated) {
			result.get(idx).add(p);
		}
	}
}
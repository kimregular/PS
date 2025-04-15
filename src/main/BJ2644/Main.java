package main.BJ2644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		Solution s = new Solution();
		Input ip = readInput();
		System.out.println(s.solution(ip.people, ip.target, ip.relations));
	}

	private Input readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int people = Integer.parseInt(br.readLine());
			int[][] relations = new int[people + 1][people + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] target = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

			int len = Integer.parseInt(br.readLine());

			for (int i = 0; i < len; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				relations[from][to] = 1;
				relations[to][from] = 1;
			}

			return new Input(people, target, relations);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	private static class Input {

		final int people;
		final int[] target;
		final int[][] relations;

		public Input(int people, int[] target, int[][] relations) {
			this.people = people;
			this.target = target;
			this.relations = relations;
		}
	}
}

class Solution {

	private int[] checked;
	private int[][] relations;

	public int solution(int people, int[] target, int[][] relations) {
		init(people, relations);
		calc(target[0]);
		return checked[target[1]] == 0 ? -1 : checked[target[1]];
	}

	private void init(int people, int[][] relations) {
		this.checked = new int[people + 1];
		this.relations = relations;
		Arrays.fill(checked, -1);
	}

	private void calc(int from) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{from, 0});
		checked[from] = 0;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int person = 1; person < relations[cur[0]].length; person++) {
				if(person == cur[0]) continue;
				if(relations[cur[0]][person] == 0) continue;
				if(checked[person] != -1) continue;
				q.offer(new int[]{person, cur[1] + 1});
				checked[person] = cur[1] + 1;
			}
		}
	}
}
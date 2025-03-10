package main.BJ13549;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.Function;

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

	private int[] readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		return new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
	}
}

class Solution {

	private static final int LIMIT = 200_000;

	private int target;
	private boolean[] visited;
	private Queue<int[]> q;

	public int solution(int[] input) {
		init(input);
		return explore();
	}

	private void init(int[] input) {
		this.target = input[1];
		this.visited = new boolean[LIMIT];
		this.q = new ArrayDeque<>();
		q.offer(new int[] {input[0], 0});
		visited[input[0]] = true;
	}

	private int explore() {
		while (!q.isEmpty()) {
			int[] cur = q.poll();

			if (cur[0] == target) {
				return cur[1];
			}

			for (Move move : Move.values()) {
				int nextPosition = move.nextPosition.apply(cur[0]);

				if(isOutOfBounds(nextPosition)) continue;
				if(visited[nextPosition]) continue;

				visited[nextPosition] = true;
				if (move == Move.TELEPORT) {
					q.offer(new int[]{nextPosition, cur[1]});
				} else {
					q.offer(new int[]{nextPosition, cur[1] + 1});
				}
			}
		}
		return -1;
	}

	private boolean isOutOfBounds(int position) {
		return position < 0 || LIMIT <= position;
	}

	enum Move {
		TELEPORT(step -> step * 2),
		BACKWARD(step -> step - 1),
		FORWARD(step -> step + 1);

		final Function<Integer, Integer> nextPosition;

		Move(Function<Integer, Integer> nextPosition) {
			this.nextPosition = nextPosition;
		}
	}
}
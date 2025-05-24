package main.BJ4179;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
        StringBuilder answer = new StringBuilder();
        Solution s = new Solution();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int TEST_CASES = Integer.parseInt(br.readLine());

            for(int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
                answer.append(s.solution(readInput(br))).append("\n");
            }

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

        System.out.println(answer);
	}

	private char[][] readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
        int width = Integer.parseInt(st.nextToken());
		int height = Integer.parseInt(st.nextToken());

		char[][] result = new char[height][width];

		for (int i = 0; i < height; i++) {
			result[i] = br.readLine().toCharArray();
		}
		return result;
	}
}

class Solution {

	private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	private char[][] field;
	private Queue<int[]> fireQ;
	private int[][] fireVisited;
	private Queue<int[]> manQ;
	private int[][] manVisited;

	public String solution(char[][] input) {
		init(input);
		fireBFS();
		return manBFS();
	}

	private void init(char[][] input) {
		this.field = input;
		this.fireQ = new ArrayDeque<>();
		this.fireVisited = new int[field.length][field[0].length];
		this.manQ = new ArrayDeque<>();
		this.manVisited = new int[field.length][field[0].length];
		initQ();
	}

	private void initQ() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j] == '*') {
					fireQ.offer(new int[]{i, j, 1});
					fireVisited[i][j] = 1;
				} else if (field[i][j] == '@') {
					manQ.offer(new int[]{i, j, 1});
					manVisited[i][j] = 1;
				}
			}
		}
	}

	private void fireBFS() {
		while (!fireQ.isEmpty()) {
			int[] cur = fireQ.poll();

			for (int[] direction : DIRECTIONS) {
				int nx = cur[0] + direction[0];
				int ny = cur[1] + direction[1];

				if(isOutOfBounds(nx, ny)) continue;
				if(field[nx][ny] == '#') continue;
				if(fireVisited[nx][ny] != 0) continue;

				fireVisited[nx][ny] = cur[2] + 1;
				fireQ.offer(new int[]{nx, ny, cur[2] + 1});
			}
		}
	}

	private String manBFS() {
		while (!manQ.isEmpty()) {
			int[] cur = manQ.poll();

			for (int[] direction : DIRECTIONS) {
				int nx = cur[0] + direction[0];
				int ny = cur[1] + direction[1];
				int nStep = cur[2] + 1;

				if(isOutOfBounds(nx, ny)) {
					return cur[2] + "";
				};

				if(field[nx][ny] == '#') continue;
				if(slowerThanFire(nx, ny, nStep)) continue;
				if(manVisited[nx][ny] != 0) continue;

				manVisited[nx][ny] = nStep;
				manQ.offer(new int[]{nx, ny, nStep});
			}
		}
		return "IMPOSSIBLE";
	}

	private boolean slowerThanFire(int x, int y, int step) {
		return 1 <= fireVisited[x][y] && fireVisited[x][y] <= step;
	}

	private boolean isOutOfBounds(int x, int y) {
		return x < 0 || x >= field.length || y < 0 || y >= field[x].length;
	}
}



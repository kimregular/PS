package main.BJ16946;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		System.out.println(new Solution().solution(readInput()));
	}

	private char[][] readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int height = Integer.parseInt(st.nextToken());
			int width = Integer.parseInt(st.nextToken());
			char[][] result = new char[height][width];

			for (int i = 0; i < height; i++) {
				result[i] = br.readLine().toCharArray();
			}
			return result;
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
}

class Solution {

	private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
	private static final int WALL = '1';
	private static final int PLAIN = '0';

	private char[][] field;
	private Map<Integer, Integer> groupMap;
	private Queue<int[]> walls;
	private int[][] visited;
	private int[][] result;


	public String solution(char[][] field) {
		init(field);
		explore();
		return getAnswer();
	}

	private void init(char[][] field) {
		this.field = field;
		this.groupMap = new HashMap<>();
		this.walls = new ArrayDeque<>();
		this.visited = new int[field.length][field[0].length];
		this.result = new int[field.length][field[0].length];


		int groupId = 1;
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (field[i][j] == PLAIN && visited[i][j] == 0) {
					groupMap.put(groupId, BFS(i, j, groupId));
					groupId++;
				} else if(field[i][j] == WALL) {
					walls.add(new int[]{i, j});
				}
			}
		}
	}

	private void explore() {
		for(int[] wall : walls) {
			Set<Integer> groupAround = new HashSet<>();
			int x = wall[0];
			int y = wall[1];
			for(int[] d : DIRECTIONS) {
				int nx = x + d[0];
				int ny = y + d[1];
				if(out(nx, ny)) continue;
				if(field[nx][ny] == WALL) continue;

				groupAround.add(visited[nx][ny]);
			}
			int width = 1;
			for (Integer groupId : groupAround) {
				width += groupMap.get(groupId);
			}
			result[x][y] = width % 10;
		}
	}

	private int BFS(int x, int y, int groupId) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{x, y});
		visited[x][y] = groupId;

		int chck = 1;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int[] d : DIRECTIONS) {
				int nx = cur[0] + d[0];
				int ny = cur[1] + d[1];

				if(out(nx, ny)) continue;
				if(field[nx][ny] == WALL) continue;
				if(visited[nx][ny] == groupId) continue;

				visited[nx][ny] = groupId;
				q.offer(new int[]{nx, ny});
				chck++;
			}
		}
		return chck;
	}

	private boolean out(int x, int y) {
		return field.length <= x || x < 0 || field[x].length <= y || y < 0;
	}

	private String getAnswer() {
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				answer.append(result[i][j] % 10);
			}
			answer.append("\n");
		}
		return answer.toString();
	}
}
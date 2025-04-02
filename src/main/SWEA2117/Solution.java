package main.SWEA2117;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	private void run() {
		Resolver resolver = new Resolver();
		StringBuilder answer = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			int testCases = Integer.parseInt(br.readLine());
			for (int testCase = 1; testCase <= testCases; testCase++) {
				Input input = readInput(br);
				int result = resolver.resolve(input.n, input.m, input.city);
				answer.append("#").append(testCase).append(" ").append(result).append("\n");
			}
			System.out.print(answer);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] city = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		return new Input(n, m, city);
	}

	private static class Input {
		final int n;  // 도시 크기
		final int m;  // 집당 지불 비용
		final int[][] city;  // 도시 정보

		public Input(int n, int m, int[][] city) {
			this.n = n;
			this.m = m;
			this.city = city;
		}
	}
}

class Resolver {
	private int n;  // 도시 크기
	private int m;  // 집당 지불 비용
	private int[][] city;  // 도시 정보
	private int maxHouses;  // 서비스 가능한 최대 집의 수

	public int resolve(int n, int m, int[][] city) {
		init(n, m, city);
		findMaxHouses();
		return maxHouses;
	}

	private void init(int n, int m, int[][] city) {
		this.n = n;
		this.m = m;
		this.city = city;
		this.maxHouses = 0;
	}

	private void findMaxHouses() {
		// 모든 가능한 중심점 탐색
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 가능한 모든 K 값 탐색 (1부터 N+1까지, 도시를 벗어날 수 있으므로)
				for (int k = 1; k <= n + 1; k++) {
					int houses = countHouses(i, j, k);
					int cost = k * k + (k - 1) * (k - 1);
					int profit = houses * m - cost;
					// 손해를 보지 않는 경우에만 최대 집의 수 갱신
					if (profit >= 0) {
						maxHouses = Math.max(maxHouses, houses);
					}
				}
			}
		}
	}

	// 중심점 (row, col)에서 서비스 영역 K에 포함된 집의 수 계산
	private int countHouses(int row, int col, int k) {
		int houses = 0;
		// 마름모 영역 탐색
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 맨해튼 거리 계산 (|x1-x2| + |y1-y2|)
				int distance = Math.abs(i - row) + Math.abs(j - col);
				// 거리가 K-1 이하인 경우 마름모 영역에 포함
				if (distance <= k - 1) {
					if (city[i][j] == 1) {
						houses++;
					}
				}
			}
		}
		return houses;
	}
}

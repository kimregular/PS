package main.SWEA2115;

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
				int result = resolver.resolve(input.range, input.limit, input.field);
				answer.append("#").append(testCase).append(" ").append(result).append("\n");
			}
			System.out.print(answer);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int len = Integer.parseInt(st.nextToken());
		int range = Integer.parseInt(st.nextToken());
		int limit = Integer.parseInt(st.nextToken());

		int[][] field = new int[len][len];
		for (int i = 0; i < len; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < len; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		return new Input(range, limit, field);
	}

	private static class Input {
		final int range;
		final int limit;
		final int[][] field;

		public Input(int range, int limit, int[][] field) {
			this.range = range;
			this.limit = limit;
			this.field = field;
		}
	}
}

class Resolver {
	private int range;  // M: 선택할 벌통 수
	private int limit;  // C: 최대 채취량
	private int[][] field;  // 벌통 배열
	private int maxProfit;  // 최대 수익
	private int tempMaxProfit;  // 부분 집합 계산 중 최대 수익 임시 저장

	public int resolve(int range, int limit, int[][] field) {
		init(range, limit, field);
		findMaxProfit();
		return maxProfit;
	}

	private void init(int range, int limit, int[][] field) {
		this.range = range;
		this.limit = limit;
		this.field = field;
		this.maxProfit = 0;
	}

	private void findMaxProfit() {
		int n = field.length;
		// 첫 번째 일꾼의 선택
		for (int r1 = 0; r1 < n; r1++) {
			for (int c1 = 0; c1 <= n - range; c1++) {
				int[] worker1 = getHoney(r1, c1);
				int profit1 = calculateMaxProfit(worker1);

				// 두 번째 일꾼의 선택 (겹치지 않도록)
				for (int r2 = 0; r2 < n; r2++) {
					for (int c2 = 0; c2 <= n - range; c2++) {
						if (isOverlapping(r1, c1, r2, c2)) continue;
						int[] worker2 = getHoney(r2, c2);
						int profit2 = calculateMaxProfit(worker2);
						maxProfit = Math.max(maxProfit, profit1 + profit2);
					}
				}
			}
		}
	}

	// 특정 위치에서 range만큼 연속된 벌통의 꿀을 가져오기
	private int[] getHoney(int row, int col) {
		int[] honey = new int[range];
		for (int i = 0; i < range; i++) {
			honey[i] = field[row][col + i];
		}
		return honey;
	}

	// 두 일꾼의 선택이 겹치는지 확인
	private boolean isOverlapping(int r1, int c1, int r2, int c2) {
		if (r1 != r2) return false;  // 다른 행이면 겹칠 일 없음
		int left1 = c1;
		int right1 = c1 + range - 1;
		int left2 = c2;
		int right2 = c2 + range - 1;
		return left1 <= right2 && left2 <= right1;  // 같은 행에서 범위가 겹치는지 확인
	}

	// 주어진 벌통에서 최대 수익 계산 (재귀를 활용한 부분 집합)
	private int calculateMaxProfit(int[] honey) {
		tempMaxProfit = 0;
		generateSubsets(honey, 0, 0, 0);
		return tempMaxProfit;
	}

	// 재귀를 통해 부분 집합 생성 및 최대 수익 계산
	private void generateSubsets(int[] honey, int index, int sum, int profit) {
		if (sum > limit) return;  // 최대 채취량을 초과하면 종료
		if (index == honey.length) {  // 모든 벌통을 확인한 경우
			tempMaxProfit = Math.max(tempMaxProfit, profit);
			return;
		}

		// 현재 벌통을 선택하지 않는 경우
		generateSubsets(honey, index + 1, sum, profit);

		// 현재 벌통을 선택하는 경우
		int currentHoney = honey[index];
		generateSubsets(honey, index + 1, sum + currentHoney, profit + currentHoney * currentHoney);
	}
}
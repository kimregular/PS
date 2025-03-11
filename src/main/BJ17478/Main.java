package main.BJ17478;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

	private int readInput(BufferedReader br) throws IOException {
		return Integer.parseInt(br.readLine());
	}
}

class Solution {

	private static final String init = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.";

	private static final String line1 = "\"재귀함수가 뭔가요?\"";
	private static final String line2 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.";
	private static final String line3 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.";
	private static final String line4 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"";
	private static final String line5 = "\"재귀함수는 자기 자신을 호출하는 함수라네\"";
	private static final String line6 = "라고 답변하였지.";
	private static final String spliter = "____";

	StringBuilder result = new StringBuilder();

	public String solution(int n) {
		result.append(init).append("\n");
		calc(n, 0);
		return result.toString();
	}

	private void calc(int n, int depth) {
		if (depth == n) {
			result.append(spliter.repeat(depth)).append(line1).append("\n");
			result.append(spliter.repeat(depth)).append(line5).append("\n");
			result.append(spliter.repeat(depth)).append(line6).append("\n");
			return;
		}

		result.append(spliter.repeat(depth)).append(line1).append("\n");
		result.append(spliter.repeat(depth)).append(line2).append("\n");
		result.append(spliter.repeat(depth)).append(line3).append("\n");
		result.append(spliter.repeat(depth)).append(line4).append("\n");
		calc(n, depth + 1);
		result.append(spliter.repeat(depth)).append(line6).append("\n");
	}
}
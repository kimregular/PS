package main.BJ14888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.function.BinaryOperator;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		Solution s = new Solution();
		Input ip = readInput();
		System.out.println(s.solution(ip.nums, ip.opers));
	}

	private Input readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int len = Integer.parseInt(br.readLine());
			int[] nums = new int[len];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < len; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			int[] opers = new int[4];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				opers[i] = Integer.parseInt(st.nextToken());
			}

			return new Input(nums, opers);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	private static class Input {

		final int[] nums;
		final int[] opers;

		public Input(int[] nums, int[] opers) {
			this.nums = nums;
			this.opers = opers;
		}
	}
}

class Solution {

	private int max = Integer.MIN_VALUE;
	private int min = Integer.MAX_VALUE;

	private int[] nums;
	private int[] opers;
	private int limit;

	public String solution(int[] nums, int[] opers) {
		init(nums, opers);
		calc(0, nums[0]);
		return max + "\n" + min;
	}

	private void init(int[] nums, int[] opers) {
		this.nums = nums;
		this.opers = opers;
		this.limit = nums.length - 1;
	}

	private void calc(int idx, int value) {
		if (idx == limit) {
			max = Math.max(max, value);
			min = Math.min(min, value);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (opers[i] > 0) {
				opers[i]--;

				String symbol = SymbolResolver.getSymbolOf(i);
				int next = Calculator.calc(value, nums[idx + 1], symbol);

				calc(idx + 1, next);
				opers[i]++;
			}
		}
	}
}

abstract class SymbolResolver {

	public static String getSymbolOf(int idx) {
		if (idx == 0) return "+";
		if (idx == 1) return "-";
		if (idx == 2) return "*";
		return "/";
	}
}

enum Calculator {

	ADD("+", Integer::sum),
	MINUS("-", (a, b) -> a - b),
	MULTIPLY("*", (a, b) -> a * b),
	DIVIDE("/", (a, b) -> a / b);

	private String symbol;
	private BinaryOperator<Integer> operation;


	Calculator(String symbol, BinaryOperator<Integer> operation) {
		this.symbol = symbol;
		this.operation = operation;
	}

	public static int calc(Integer a, Integer b, String symbol) {
		Calculator o = fromsymbol(symbol);
		return o.operation.apply(a, b);
	}

	private static Calculator fromsymbol(String oper) {
		for (Calculator o : values()) {
			if (oper.equals(o.symbol)) return o;
		}
		return null;
	}
}
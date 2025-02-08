package main.SWE1230;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	private void run() {

		StringBuilder answer = new StringBuilder();
		Resolver r = new Resolver();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int TEST_CASES = 10;
			for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
				Input ip = readInput(br);
				answer.append("#").append(TEST_CASE + 1).append(" ").append(r.resolve(ip.list, ip.orders)).append("\n");
			}

			System.out.println(answer);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		br.readLine();
		LinkedList<Integer> list = new LinkedList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			list.add(Integer.parseInt(st.nextToken()));
		}

		br.readLine();
		StringTokenizer orders = new StringTokenizer(br.readLine());
		return new Input(list, orders);
	}

	private static class Input {

		private final LinkedList<Integer> list;
		private final StringTokenizer orders;

		public Input(LinkedList<Integer> list, StringTokenizer orders) {
			this.list = list;
			this.orders = orders;
		}
	}
}

class Resolver {

	private LinkedList<Integer> list;
	private StringTokenizer orders;

	public String resolve(LinkedList<Integer> list, StringTokenizer orders) {
		init(list, orders);
		calc();
		return getAnswer();
	}

	private void init(LinkedList<Integer> list, StringTokenizer orders) {
		this.list = list;
		this.orders = orders;
	}

	private void calc() {
		while (orders.hasMoreTokens()) {
			String oper = orders.nextToken();

			if (oper.equals("I")) {
				int x = Integer.parseInt(orders.nextToken());
				int y = Integer.parseInt(orders.nextToken());
				insert(x, y);
			} else if (oper.equals("D")) {
				int x = Integer.parseInt(orders.nextToken());
				int y = Integer.parseInt(orders.nextToken());
				delete(x, y);
			} else {
				int y = Integer.parseInt(orders.nextToken());
				add(y);
			}
		}
	}

	private void insert(int from, int numOfOper) {
		for (int i = 0; i < numOfOper; i++) {
			list.add(from++, Integer.parseInt(orders.nextToken()));
		}
	}

	private void delete(int from, int numOfOper) {
		for (int i = 0; i < numOfOper; i++) {
			list.remove(from);
		}
	}

	private void add(int numOfOper) {
		for (int i = 0; i < numOfOper; i++) {
			list.add(Integer.parseInt(orders.nextToken()));
		}
	}

	private String getAnswer() {
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			answer.append(list.get(i)).append(" ");
		}
		return answer.toString();
	}
}
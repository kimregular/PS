package main.SWE1952;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	private void run() {

		StringBuilder result = new StringBuilder();
		Resolver r = new Resolver();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int TEST_CASES = Integer.parseInt(br.readLine());
			for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
				Input ip = readInput(br);
				result.append("#").append(TEST_CASE + 1).append(" ").append(r.resolve(ip.tickets, ip.months)).append("\n");
			}

			System.out.println(result.toString().trim());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		int ticketLen = 4;
		int[] tickets = new int[ticketLen];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < ticketLen; i++) {
			tickets[i] = Integer.parseInt(st.nextToken());
		}

		int monthLen = 12;
		int[] months = new int[monthLen];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < monthLen; i++) {
			months[i] = Integer.parseInt(st.nextToken());
		}
		return new Input(tickets, months);
	}

	private static class Input {

		final int[] tickets;
		final int[] months;

		public Input(int[] tickets, int[] months) {
			this.tickets = tickets;
			this.months = months;
		}
	}
}

class Resolver {

	private Map<String, Integer> tickets;
	private int[] months;
	private int result;

	public int resolve(int[] tickets, int[] months) {
		init(tickets, months);
		calc(0, 0);
		return result;
	}

	private void init(int[] tickets, int[] months) {
		this.tickets = new HashMap<>();
		this.tickets.put("day", tickets[0]);
		this.tickets.put("month", tickets[1]);
		this.tickets.put("quarter", tickets[2]);
		this.tickets.put("year", tickets[3]);
		this.months = new int[13];
		this.result = Integer.MAX_VALUE;
		System.arraycopy(months, 0, this.months, 1, this.months.length - 1);
	}

	private void calc(int month, int price) {
		if(price > result) return;

		if (month >= months.length) {
			if(price < result){
				result = price;
			}
			return;
		}

		for (String period : tickets.keySet()) {
			if (period.equals("day")) {
				calc(month + 1, price + months[month] * tickets.get(period));
			}
			if (period.equals("month")) {
				calc(month + 1, price + tickets.get(period));
			}
			if (period.equals("quarter")) {
				calc(month + 3, price + tickets.get(period));
			}
			if (period.equals("year")) {
				calc(month + 12, price + tickets.get(period));
			}
		}
	}

}


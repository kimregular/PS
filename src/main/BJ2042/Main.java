package main.BJ2042;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		Input ip = readInput();
		System.out.println(new Solution().solution(ip.arr, ip.orders));
	}

	private Input readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
			int orderLen = 0;
			orderLen += Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());

			long[] arr = new long[len + 1];
			long[][] orders = new long[orderLen][3];

			for (int i = 1; i <= len; i++) {
				arr[i] = Long.parseLong(br.readLine());
			}

			for (int i = 0; i < orderLen; i++) {
				st = new StringTokenizer(br.readLine());
				long type = Long.parseLong(st.nextToken());
				long a = Long.parseLong(st.nextToken());
				long b = Long.parseLong(st.nextToken());
				orders[i][0] = type;
				orders[i][1] = a;
				orders[i][2] = b;
			}

			return new Input(arr, orders);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	private static class Input {

		final long[] arr;
		final long[][] orders;

		public Input(long[] arr, long[][] orders) {
			this.arr = arr;
			this.orders = orders;
		}
	}
}

class Solution {

	public String solution(long[] arr, long[][] orders) {
		SegmentTree st = new SegmentTree(arr);
		StringBuilder answer = new StringBuilder();

		for (long[] order : orders) {
			Executer.excute(order, arr, st, answer);
		}

		return answer.toString();
	}
}

enum Executer {

	UPDATE((order, arr, st, sb) -> {
		long diff = order[2] - arr[(int) order[1]];
		arr[(int) order[1]] = order[2];
		st.update((int) order[1], diff);
	}),
	SUM((order, arr, st, sb) -> {
		sb.append(st.sum((int) order[1], (int) order[2])).append("\n");
	});

	private MainLogic mainLogic;

	Executer(MainLogic mainLogic) {
		this.mainLogic = mainLogic;
	}

	public static void excute(long[] order, long[] arr, SegmentTree st, StringBuilder sb) {
		Executer executer = Executer.of(order[0]);
		executer.mainLogic.apply(order, arr, st, sb);
	}

	public static Executer of(long type) {
		return values()[(int) (type - 1)];
	}
}

interface MainLogic {

	void apply(long[] order, long[] arr, SegmentTree st, StringBuilder sb);
}

class SegmentTree {

	private int size;
	private long[] tree;

	public SegmentTree(long[] arr) {
		this.size = arr.length - 1;
		int h = (int) Math.ceil(Math.log(size) / Math.log(2));
		int treeSize = (int) Math.pow(2, (h + 1));
		this.tree = new long[treeSize];
		init(arr, 1, 1, size);
	}

	public long init(long[] arr, int node, int start, int end) {
		if (start == end) return tree[node] = arr[start];

		return tree[node] = init(arr, node * 2, start, (start + end) / 2) + init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
	}

	public void update(int idx, long diff) {
		update(1, 1, size, idx, diff);
	}

	private void update(int node, int start, int end, int idx, long diff) {
		if (end < idx || idx < start) return;

		tree[node] += diff;

		if (start != end) {
			update(node * 2, start, (start + end) / 2, idx, diff);
			update(node * 2 + 1, (start + end) / 2 + 1, end, idx, diff);
		}
	}

	public long sum(int left, int right) {
		return sum(1, 1, size, left, right);
	}

	private long sum(int node, int start, int end, int left, int right) {
		if (end < left || right < start) return 0;
		if (left <= start && end <= right) return tree[node];

		return sum(node * 2, start, (start + end) / 2, left, right) + sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
	}
}


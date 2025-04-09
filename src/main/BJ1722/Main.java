package main.BJ1722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		Solution s = new Solution();
		Input ip = readInput();
		System.out.println(s.solution(ip.len, ip.order));
	}

	private Input readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int len = Integer.parseInt(br.readLine());
			String[] tokens = br.readLine().split(" ");

			BigInteger[] order = Arrays.stream(tokens).map(BigInteger::new).toArray(BigInteger[]::new);

			return new Input(len, order);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	private static class Input {
		final int len;
		final BigInteger[] order;

		public Input(int len, BigInteger[] order) {
			this.len = len;
			this.order = order;
		}
	}
}

class Solution {

	public String solution(int len, BigInteger[] order) {
		int type = order[0].intValue();
		BigInteger[] rest = Arrays.copyOfRange(order, 1, order.length);
		PermutationResolver resolver = PermutationResolver.fromTypeOf(type);
		return resolver.resolve(len, rest);
	}
}

enum PermutationResolver {
	N_TH_PERMUTATION(1, (Integer len, BigInteger[] nth) -> {
		List<Integer> nums = new ArrayList<>(IntStream.rangeClosed(1, len).boxed().collect(Collectors.toList()));
		StringBuilder result = new StringBuilder();
		BigInteger n = nth[0].subtract(BigInteger.ONE); // 수정된 부분

		while (!nums.isEmpty()) {
			int size = nums.size();
			BigInteger f = BigInteger.valueOf(factorial(size - 1));
			BigInteger index = n.divide(f);

			result.append(nums.get(index.intValue())).append(" ");
			nums.remove(index.intValue());
			n = n.mod(f);
		}
		return result.toString().trim();
	}), INDEX_PERMUTATION(2, (Integer len, BigInteger[] field) -> {
		List<Integer> nums = new ArrayList<>(IntStream.rangeClosed(1, len).boxed().collect(Collectors.toList()));
		BigInteger index = BigInteger.ZERO;

		for (int i = 0; i < field.length; i++) {
			BigInteger cur = field[i];
			int pos = nums.indexOf(cur.intValue());

			index = index.add(BigInteger.valueOf(pos).multiply(BigInteger.valueOf(factorial(len - i - 1))));
			nums.remove(pos);
		}

		return index.add(BigInteger.ONE).toString(); // 1-based index로 보정
	});

	private int type;
	private BiFunction<Integer, BigInteger[], String> func;

	PermutationResolver(int type, BiFunction<Integer, BigInteger[], String> func) {
		this.type = type;
		this.func = func;
	}

	public static PermutationResolver fromTypeOf(int type) {
		for (PermutationResolver r : values()) {
			if (type == r.type) return r;
		}
		return null;
	}

	public String resolve(Integer len, BigInteger[] order) {
		return func.apply(len, order);
	}

	private static long factorial(long num) {
		long result = 1;
		for (int i = 2; i <= num; i++) result *= i;
		return result;
	}
}


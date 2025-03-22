package main.BJ15740;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		Solution s = new Solution();
		System.out.println(s.solution(readInput()));
	}

	private String[] readInput() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			return br.readLine().split(" ");

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

class Solution {

	public BigInteger solution(String[] input) {
		BigInteger a = new BigInteger(input[0]);
		BigInteger b = new BigInteger(input[1]);
		return a.add(b);
	}
}
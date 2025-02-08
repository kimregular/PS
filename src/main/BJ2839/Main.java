package main.BJ2839;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	public void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			System.out.println(solution(readInput(br)));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public int solution(int target) {
		int[] field = new int[target + 1];
		Arrays.fill(field, -1);

		if(target >= 3) field[3] = 1;
		if(target >= 5) field[5] = 1;

		for (int i = 6; i <= target; i++) {
			if (field[i - 3] != -1 && field[i - 5] != -1) {
				field[i] = Math.min(field[i - 3], field[i - 5]) + 1;
			} else if (field[i - 3] != -1) {
				field[i] = field[i - 3] + 1;
			} else if (field[i - 5] != -1) {
				field[i] = field[i - 5] + 1;
			}
		}

		return field[target];
	}

	private int readInput(BufferedReader br) throws IOException {
		return Integer.parseInt(br.readLine());
	}
}
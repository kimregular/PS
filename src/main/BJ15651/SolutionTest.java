package main.BJ15651;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

	@ParameterizedTest(name = "{index}")
	@MethodSource("testSource")
	void test(int[] input, String expected) {
		// given
		Solution s = new Solution();

		// when
		// then
		assertEquals(expected, s.solution(input));
	}

	private static Stream<Arguments> testSource() {
		return loadData().stream().map(testCase -> Arguments.of(testCase.input, testCase.output));
	}

	private static List<TestCase> loadData() {
		List<TestCase> result = new ArrayList<>();

		for (int i = 1; i <= 3; i++) {
			String inputFilePath = "src/main/BJ15651/source/input" + i + ".txt";
			String outputFilePath = "src/main/BJ15651/source/output" + i + ".txt";

			result.add(new TestCase(readInput(inputFilePath), readOutput(outputFilePath)));
		}

		return result;
	}

	private static int[] readInput(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			return new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static String readOutput(String filePath) {
		StringBuilder result = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

			String line = "";

			while ((line = br.readLine()) != null) {
				result.append(line + " ").append("\n");
			}

			return result.toString();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static class TestCase {
		final int[] input;
		final String output;

		public TestCase(int[] input, String output) {
			this.input = input;
			this.output = output;
		}
	}
}
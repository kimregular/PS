package main.BJ9095;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

	@ParameterizedTest(name = "input {0} : expected {1}")
	@CsvSource({
			"4, 7",
			"7, 44",
			"10, 274"
	})
	void test(int input, int expected) {

		// given
		Solution s = new Solution();
		int[] expectedArray = {expected}; // Create an array for expected value

		// when
		int[] result = s.solution(new int[]{input});

		// then
		assertArrayEquals(expectedArray, result); // Compare arrays
	}
}
package main.BJ2839;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

	@CsvSource({"18, 4", "4, -1", "6, 2", "9, 3", "11, 3"})
	@ParameterizedTest(name = "{index} - input : {0}, expected : {1}")
	void testWithParameters(int input, int expected) {
		Main m = new Main();
		int result = m.solution(input);

		assertEquals(expected, result);
	}
}

package main.BJ1003;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SolutionTest {

	static Stream<Arguments> testSource() {
		return Stream.of(
				Arguments.of(0, new int[]{1, 0}),
				Arguments.of(1, new int[]{0, 1}),
				Arguments.of(3, new int[]{1, 2})
		);
	}

	@ParameterizedTest(name = "{index} input {0} : expected {1}")
	@MethodSource("testSource")
	void test(int input, int[] expected) {
		// given
		Solution s = new Solution();

		//when
		int[][] result = s.solution(new int[] {input});

		// then
		assertArrayEquals(expected, result[0]);
	}
}
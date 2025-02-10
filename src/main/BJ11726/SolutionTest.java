package main.BJ11726;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

	static Stream<Arguments> testSource() {
		return Stream.of(
				Arguments.of(1, 1),
				Arguments.of(2, 2),
				Arguments.of(9, 55)
		);
	}

	@ParameterizedTest(name = "{index} input {0} : expexted : {1}")
	@MethodSource("testSource")
	void test(int target, int expected) {
		// given
		Solution s = new Solution();
		int input = target;

		// when
		int result = s.solution(input);

		assertEquals(expected, result);
	}
}
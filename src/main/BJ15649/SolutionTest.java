package main.BJ15649;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

	static Stream<Arguments> methodSource() {
		return Stream.of(
				Arguments.of(new int[]{3, 1}, "1 \n2 \n3 "),
				Arguments.of(new int[]{4, 2}, "1 2 \n1 3 \n1 4 \n2 1 \n2 3 \n2 4 \n3 1 \n3 2 \n3 4 \n4 1 \n4 2 \n4 3 ")
		);
	}


	@ParameterizedTest(name = "{index} input {0} : output {1}")
	@MethodSource("methodSource")
	void test(int[] input, String expected) {
		// given
		Solution s = new Solution();

		// when
		// then
		assertLinesMatch(
				List.of(expected.split("\n")),
				List.of(s.solution(input).split("\n"))
		);
	}
}
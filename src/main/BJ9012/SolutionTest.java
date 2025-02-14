package main.BJ9012;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

	@ParameterizedTest(name = "{index}")
	@MethodSource("testSource")
	void test(String target, String expected) {
		// given
		Solution s = new Solution();

		// when
		// then
		assertEquals(expected, s.solution(target));
	}

	private static Stream<Arguments> testSource() {
		return Stream.of(
				Arguments.of("(())())", "NO"),
				Arguments.of("(((()())()", "NO"),
				Arguments.of("(()())((()))", "YES"),
				Arguments.of("((()()(()))(((())))()", "NO"),
				Arguments.of("()()()()(()()())()", "YES"),
				Arguments.of("(()((())()(", "NO")
		);
	}

}






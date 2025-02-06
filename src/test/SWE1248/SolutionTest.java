package SWE1248;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

	private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	// 출력 결과를 확인할 outputstream
	private final PrintStream originalSystemOut = System.out;
	// 현재 출력 나가는 stream

	@BeforeEach
	public void setUp() {
		System.setOut(new PrintStream(outputStream));
		// 현재 출력되는 stream을 바꾼다.
		// 즉, 콘솔에 출력되는 결과를 outputStream으로 리디렉션 한다.
	}

	@Test
	void testFromFile() throws IOException {

		String inputFilePath = "src/test/SWE1248/input.txt";
		String expectedOutputFilePath = "src/test/SWE1248/output.txt";
		// 입력 파일과 예상 출력 파일을 읽어온다.


		String input = new String(Files.readAllBytes(Paths.get(inputFilePath)));
		String expectedOutput = new String(Files.readAllBytes(Paths.get(expectedOutputFilePath)));

		input = input.replaceAll("\r\n", "\n");
		expectedOutput = expectedOutput.replaceAll("\r\n", "\n");
		// 윈도우, 맥 줄바꿈 호환성 신경쓰기



		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		// 입력 파일 내용을 바이트 배열로 변환하여 InputStream으로 변경"
		System.setIn(inputStream);
		// 시스템에 인풋스트림 주입


		new src.main.SWE1248.Solution().run();
		// 이 코드에서는 원래 콘솔에 출력되어야 하는 결과가
		// outputStream으로 리디렉션되므로
		// 콘솔에 출력되지 않고 outputStream에서 결과가 출력된다.


		assertEquals(expectedOutput, outputStream.toString().trim());
		// output.txt 와 코드 결과 비교

		System.setOut(originalSystemOut);
		// 출력 스트림을 원래의 시스템 출력으로 리셋
	}
}
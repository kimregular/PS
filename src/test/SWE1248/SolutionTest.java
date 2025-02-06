package SWE1248;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

	private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	private final PrintStream originalSystemOut = System.out;

	@BeforeEach
	public void setUp() {
		// Redirect System.out to capture the output
		System.setOut(new PrintStream(outputStream));
	}

	@Test
	void testFromFile() throws IOException {
		// Define the file paths for input and output
		String inputFilePath = "src/test/SWE1248/input.txt";
		String expectedOutputFilePath = "src/test/SWE1248/output.txt";

		// Read input and expected output from files
		String input = new String(Files.readAllBytes(Paths.get(inputFilePath)));
		String expectedOutput = new String(Files.readAllBytes(Paths.get(expectedOutputFilePath)));

		input = input.replaceAll("\r\n", "\n");
		expectedOutput = expectedOutput.replaceAll("\r\n", "\n");

		// Run the test
		// Simulate the input stream by writing the input to System.in
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		System.setIn(inputStream);

		// Run the solution
		new src.main.SWE1248.Solution().run();

		// Capture the output and compare it with the expected output
		assertEquals(expectedOutput, outputStream.toString().trim());

		// Reset System.out
		System.setOut(originalSystemOut);
	}
}
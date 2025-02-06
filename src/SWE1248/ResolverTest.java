package src.SWE1248;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static org.junit.jupiter.api.Assertions.*;

class ResolverTest {

	@Test
	@DisplayName("입력 파일과 출력 파일을 비교하는 테스트")
	void testWithFileInputOutput() throws IOException {
		// given
		String inputFilePath = "src/SWE1248/input.txt";  // input.txt 파일 경로
		String outputFilePath = "src/SWE1248/output.txt"; // output.txt 파일 경로

		// when
		String expectedOutput = readFile(outputFilePath); // output.txt에서 기대되는 결과 읽기
		String actualOutput = solveWithFileInput(inputFilePath); // input.txt를 통해 계산한 실제 결과

		// then
		assertEquals(expectedOutput, actualOutput); // 기대값과 실제값 비교
	}

	private String readFile(String filePath) throws IOException {
		StringBuilder content = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				content.append(line).append("\n");
			}
		}
		return content.toString().trim(); // 파일에서 읽은 내용을 반환 (마지막 개행문자 제거)
	}

	private String solveWithFileInput(String inputFilePath) throws IOException {
		StringBuilder output = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
			int TEST_CASES = Integer.parseInt(br.readLine());
			Resolver r = new Resolver();

			for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
				Input ip = readInput(br);
				output.append("#").append(TEST_CASE + 1).append(" ")
						.append(r.resolve(ip.target1, ip.target2, ip.parentGraph, ip.childGraph)).append("\n");
			}
		}
		return output.toString().trim(); // 계산된 결과 반환
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numOfNodes = Integer.parseInt(st.nextToken());
		int numOfLinks = Integer.parseInt(st.nextToken());
		int target1 = Integer.parseInt(st.nextToken());
		int target2 = Integer.parseInt(st.nextToken());

		List<List<Integer>> parentGraph = new ArrayList<>();
		List<List<Integer>> childGraph = new ArrayList<>();
		for (int i = 0; i <= numOfNodes; i++) {
			parentGraph.add(new ArrayList<>());
			childGraph.add(new ArrayList<>());
		}

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < numOfLinks; i++) {
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			parentGraph.get(to).add(from);
			childGraph.get(from).add(to);
		}

		return new Input(target1, target2, parentGraph, childGraph);
	}

	private static class Input {

		final int target1;
		final int target2;
		final List<List<Integer>> parentGraph;
		final List<List<Integer>> childGraph;

		public Input(int target1, int target2, List<List<Integer>> parentGraph, List<List<Integer>> childGraph) {
			this.target1 = target1;
			this.target2 = target2;
			this.parentGraph = parentGraph;
			this.childGraph = childGraph;
		}
	}
}

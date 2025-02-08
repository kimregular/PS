package main.SWE1231;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	private void run() {

		StringBuilder answer = new StringBuilder();
		Resolver r = new Resolver();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int TEST_CASES = 10;
			for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
				answer.append("#").append(TEST_CASE + 1).append(" ").append(r.solution(readInput(br))).append("\n");
			}

			System.out.println(answer);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String[][] readInput(BufferedReader br) throws IOException {
		int len = Integer.parseInt(br.readLine());
		String[][] result = new String[len][4];
		for (int i = 0; i < len; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx = 0;
			while (st.hasMoreTokens()) {
				result[i][idx++] = st.nextToken();
			}
		}
		return result;
	}
}

class Resolver {

	WordTree wordTree;
	StringBuilder answer;

	public String solution(String[][] infos) {
		init(infos);
		inOrderExplore(wordTree);
		return answer.toString();
	}

	private void init(String[][] infos) {
		wordTree = WordTreeFactory.makeWordTree(infos);
		this.answer = new StringBuilder();
	}

	private void inOrderExplore(WordTree wordTree) {
		WordTree leftChild = wordTree.getLeftChild();
		if (leftChild != null) {
			inOrderExplore(leftChild);
		}
		answer.append(wordTree.getValue());
		WordTree rightChild = wordTree.getRightChild();
		if (rightChild != null) {
			inOrderExplore(rightChild);
		}
	}
}

class WordTreeFactory {

	private static WordTree result;

	private WordTreeFactory() {
	}

	public static WordTree makeWordTree(String[][] infos) {
		result = new WordTree(1);
		for (String[] info : infos) {
			result.addChild(info);
		}
		return result;
	}
}

class WordTree {

	private int idx;
	private String value;
	private WordTree leftChild;
	private WordTree rightChild;


	public WordTree(int idx) {
		this.idx = idx;
	}

	public void addChild(String[] info) {
		int idx = Integer.parseInt(info[0]);

		if (this.idx == idx) {
			this.value = info[1];
			if (info[2] != null) {
				this.leftChild = new WordTree(Integer.parseInt(info[2]));
			}
			if (info[3] != null) {
				this.rightChild = new WordTree(Integer.parseInt(info[3]));
			}
		} else {
			if (leftChild != null) {
				leftChild.addChild(info);
			}
			if (rightChild != null) {
				rightChild.addChild(info);
			}
		}
	}

	public String getValue() {
		return value;
	}

	public WordTree getLeftChild() {
		return leftChild;
	}

	public WordTree getRightChild() {
		return rightChild;
	}
}
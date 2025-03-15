package main.BJ3613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Solution s = new Solution();
			System.out.println(s.solution(readInput(br)));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String readInput(BufferedReader br) throws IOException {
		return br.readLine();
	}
}

class Solution {

	public String solution(String target) {
		if(TypeChecker.isCPPStyle(target)) return Converter.CPP_TO_JAVA.convert(target);
		if(TypeChecker.isJavaStyle(target)) return Converter.JAVA_TO_CPP.convert(target);
		return "Error!";
	}
}

enum Converter {

	JAVA_TO_CPP(target -> {
		StringBuilder result = new StringBuilder();
		char[] chars = target.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (Character.isUpperCase(chars[i])) {
				result.append("_").append(Character.toLowerCase(chars[i]));
			} else {
				result.append(chars[i]);
			}
		}
		return result.toString();
	}),

	CPP_TO_JAVA(target -> {
		StringBuilder result = new StringBuilder();
		String[] parts = target.split("_");
		result.append(parts[0]);
		for (int i = 1; i < parts.length; i++) {
			if (parts[i].length() > 0) {
				result.append(Character.toUpperCase(parts[i].charAt(0)))
						.append(parts[i].substring(1));
			}
		}
		return result.toString();
	});

	private final Function<String, String> convert;

	Converter(Function<String, String> convert) {
		this.convert = convert;
	}

	public String convert(String target) {
		return convert.apply(target);
	}
}

abstract class TypeChecker {

	private TypeChecker(){}

	public static boolean isCPPStyle(String target) {
		if (!target.contains("_")) return false;
		if(target.startsWith("_")) return false;
		if(target.endsWith("_")) return false;
		if(target.contains("__")) return false;
		return target.chars().allMatch(ch -> Character.isLowerCase(ch) || ch == '_');
	}

	public static boolean isJavaStyle(String target) {
		if(target.contains("_")) return false;
		if(target.isEmpty()) return false;
		if(Character.isUpperCase(target.charAt(0))) return false;
		return target.chars().allMatch(Character::isLetter);
	}
}
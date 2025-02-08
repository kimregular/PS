package main.BJ1043;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			Input ip = readInput(br);
			Solution s = new Solution();
			System.out.println(s.solution(ip.numOfPeople, ip.detectors, ip.partyInfo));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int numOfPeople = Integer.parseInt(st.nextToken());
		int numOfParties = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int numOfDetector = Integer.parseInt(st.nextToken());
		int[] detectors = new int[numOfDetector];
		for (int i = 0; i < numOfDetector; i++) {
			detectors[i] = Integer.parseInt(st.nextToken());
		}

		int[][] partyInfo = new int[numOfParties][];
		for (int i = 0; i < numOfParties; i++) {
			st = new StringTokenizer(br.readLine());
			int numOfParticipants = Integer.parseInt(st.nextToken());
			partyInfo[i] = new int[numOfParticipants];
			for (int j = 0; j < numOfParticipants; j++) {
				partyInfo[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		return new Input(numOfPeople, detectors, partyInfo);
	}

	private static class Input {
		final int numOfPeople;
		final int[] detectors;
		final int[][] partyInfo;

		public Input(int numOfPeople, int[] detectors, int[][] partyInfo) {
			this.numOfPeople = numOfPeople;
			this.detectors = detectors;
			this.partyInfo = partyInfo;
		}
	}
}

class Solution {

	private int[] people;

	public int solution(int numOfPeople, int[] detectors, int[][] partyInfos) {
		if (detectors.length == 0) return partyInfos.length;

		init(numOfPeople, detectors);

		for (int[] partyInfo : partyInfos) {
			int standardGroup = getGroup(partyInfo[0]);
			for (int i = 1; i < partyInfo.length; i++) {
				connect(standardGroup, partyInfo[i]);
			}
		}
		return getAnswer(partyInfos);
	}

	private void init(int numOfPeople, int[] detectors) {
		this.people = new int[numOfPeople + 1];
		for (int i = 0; i < people.length; i++) {
			people[i] = i;
		}
		for (int detector : detectors) {
			people[detector] = 0;
		}
	}

	private int getGroup(int target) {
		if (target != people[target]) return people[target] = getGroup(people[target]);
		return people[target];
	}

	private void connect(int target1, int target2) {
		int group1 = getGroup(target1);
		int group2 = getGroup(target2);
		if (group1 == group2) return;
		people[Math.max(group1, group2)] = Math.min(group1, group2);
	}

	private int getAnswer(int[][] partiInfos) {
		int result = 0;
		for (int[] partiInfo : partiInfos) {
			if (canILie(partiInfo)) result++;
		}
		return result;
	}

	private boolean canILie(int[] participants) {
		for (int participant : participants) {
			if (getGroup(participant) == 0) return false;
		}
		return true;
	}
}


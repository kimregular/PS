package src.BJ1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

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

    private int[][] readInput(BufferedReader br) throws IOException {
        int len = Integer.parseInt(br.readLine());
        int[][] result = new int[len][2];
        StringTokenizer st;
        for (int i = 0; i < len; i++) {
            st = new StringTokenizer(br.readLine());
            result[i][0] = Integer.parseInt(st.nextToken());
            result[i][1] = Integer.parseInt(st.nextToken());
        }
        return result;
    }
}

class Solution {

    public int solution(int[][] inputs) {
        Meeting[] meetings = getSortedMeetings(inputs);

        int result = 1;
        int prevEndTime = meetings[0].end;

        for (int i = 1; i < meetings.length; i++) {
            if (meetings[i].start < prevEndTime) continue;
            result++;
            prevEndTime = meetings[i].end;
        }

        return result;
    }

    private Meeting[] getSortedMeetings(int[][] inputs) {
        Meeting[] result = new Meeting[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            result[i] = new Meeting(inputs[i][0], inputs[i][1]);
        }
        Arrays.sort(result);
        return result;
    }

    private static class Meeting implements Comparable<Meeting> {

        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            return end == o.end ? Integer.compare(start, o.start) : Integer.compare(end, o.end);
        }
    }
}
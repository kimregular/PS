package main.BJ2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println(new Solution().solution(readInput()));
    }

    private int[] readInput() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int len = Integer.parseInt(br.readLine());
            int[] result = new int[len];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < len; i++) {
                result[i] = Integer.parseInt(st.nextToken());
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private int[] towels;
    private Deque<int[]> stck;
    private int[] result;

    public String solution(int[] towels) {
        init(towels);
        calc();
        return getAnswer();
    }

    private void init(int[] towels) {
        this.towels = towels;
        this.stck = new ArrayDeque<>();
        this.result = new int[towels.length];
    }

    private void calc() {
        for (int i = 0; i < towels.length; i++) {
            int target = towels[i];

            while (!stck.isEmpty() && stck.peekLast()[0] < target)
                stck.pollLast();

            result[i] = stck.isEmpty() ? 0 : stck.peekLast()[1];
            stck.offerLast(new int[] { target, i + 1 });
        }
    }

    private String getAnswer() {
        StringBuilder answer = new StringBuilder();
        for (int i : result)
            answer.append(i).append(" ");
        return answer.toString();
    }
}
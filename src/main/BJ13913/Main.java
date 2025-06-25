package main.BJ13913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println(new Solution().solution(readInput()));
    }

    private int[] readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            return new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private static final int INF = 1_000_001;

    private int startPosition;
    private int targetPosition;

    private int[] field;
    private int[] trails;

    public String solution(int[] input) {
        init(input);
        calc();
        return getAnswer();
    }

    private void init(int[] input) {
        this.startPosition = input[0];
        this.targetPosition = input[1];
        this.field = new int[INF];
        this.trails = new int[INF];
        Arrays.fill(field, INF);
        Arrays.fill(trails, -1);
    }

    private void calc() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {startPosition, 0}); // position, step
        field[startPosition] = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            if(cur[0] == targetPosition) {
                return;
            }

            int nextPosition;
            int nextStep = cur[1] + 1;

            nextPosition = cur[0] - 1;
            move(q, cur[0], cur[1], nextPosition, nextStep);

            nextPosition = cur[0] + 1;
            move(q, cur[0], cur[1], nextPosition, nextStep);

            nextPosition = cur[0] * 2;
            move(q, cur[0], cur[1], nextPosition, nextStep);
        }
    }

    private void move(Queue<int[]> q, int currentPosition, int currentStep, int nextPosition, int nextStep) {
        if(out(nextPosition)) return;
        if(field[nextPosition] <= nextStep) return;

        field[nextPosition] = nextStep;
        trails[nextPosition] = currentPosition;

        q.offer(new int[] {nextPosition, nextStep, currentPosition});
    }

    private boolean out(int x) {
        return field.length <= x ||  x < 0;
    }

    private String getAnswer() {
        StringBuilder answer = new StringBuilder();
        answer.append(field[targetPosition]).append("\n");

        int step = targetPosition;

        Deque<Integer> q = new ArrayDeque<>();

        while(step != -1) {
            q.offer(step);
            step = trails[step];
        }

        while(!q.isEmpty()) {
            answer.append(q.pollLast()).append(" ");
        }

        return answer.toString();
    }
}
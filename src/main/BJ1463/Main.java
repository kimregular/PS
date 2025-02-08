package main.BJ1463;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

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

    private int readInput(BufferedReader br) throws IOException {
        return Integer.parseInt(br.readLine());
    }
}

class Solution {

    private static final int LIMIT = 1_000_001;

    public int solution(int target) {
        boolean[] field = new boolean[LIMIT];
        field[0] = true;

        Queue<Step> q = new ArrayDeque<>();
        field[target] = true;
        q.offer(new Step(target, 0));

        while (!q.isEmpty()) {
            Step cur = q.poll();

            if(cur.location == 1) return cur.step;


            for (int nextStep : getNextSteps(cur)) {
                if(nextStep == -1) continue;
                if(isOutOfBounds(nextStep)) continue;
                if(field[nextStep]) continue;

                field[nextStep] = true;
                q.offer(new Step(nextStep, cur.step + 1));
            }
        }

        return -1;
    }

    private int[] getNextSteps(Step cur) {
        int next1 = cur.location % 3 == 0 ? cur.location / 3 : -1;
        int next2 = cur.location % 2 == 0 ? cur.location / 2 : -1;
        int next3 = cur.location - 1;
        return new int[]{next1, next2, next3};
    }

    private boolean isOutOfBounds(int location) {
        return location < 1 || location >= LIMIT;
    }

    private static class Step {
        int location;
        int step;

        public Step(int location, int step) {
            this.location = location;
            this.step = step;
        }
    }
}
package src.BJ1697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
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

    private int[] readInput(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        return new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
    }
}

class Solution {

    private int start;
    private int target;
    private boolean[] field = new boolean[200_001];

    public int solution(int[] input) {
        init(input);
        return explore();
    }

    private void init(int[] input) {
        this.start = input[0];
        this.target = input[1];
    }

    private int explore() {
        Queue<Location> q = new ArrayDeque<>();
        q.offer(new Location(start, 0));
        field[start] = true;

        while (!q.isEmpty()) {
            Location cur = q.poll();

            if(cur.position == target) return cur.step;

            move(q, cur);
        }
        return -1;
    }

    private void move(Queue<Location> q, Location cur) {
        int[] nextSteps = {cur.position + 1, cur.position - 1, cur.position * 2};

        for (int nextStep : nextSteps) {
            if(isOutOfField(nextStep)) continue;
            if(field[nextStep]) continue;

            q.offer(new Location(nextStep, cur.step + 1));
            field[nextStep] = true;
        }
    }

    private boolean isOutOfField(int x) {
        return x < 0 || x >= field.length;
    }

    private static class Location {
        private int position;
        private int step;

        public Location(int position, int step) {
            this.position = position;
            this.step = step;
        }
    }
}
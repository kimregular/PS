package main.BJ7562;

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
        StringBuilder answer = new StringBuilder();
        Solution s = new Solution();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int TEST_CASES = Integer.parseInt(br.readLine());
            for (int i = 0; i < TEST_CASES; i++) {
                Input ip = readInput(br);
                answer.append(s.solution(ip.len, ip.start, ip.target)).append("\n");
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(answer);
    }

    private Input readInput(BufferedReader br) throws IOException {
        int len = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] start = new int[2];
        start[0] = Integer.parseInt(st.nextToken());
        start[1] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] target = new int[2];
        target[0] = Integer.parseInt(st.nextToken());
        target[1] = Integer.parseInt(st.nextToken());

        return new Input(len, start, target);
    }

    private static class Input {

        final int len;
        final int[] start;
        final int[] target;

        public Input(int len, int[] start, int[] target) {
            this.len = len;
            this.start = start;
            this.target = target;
        }
    }
}

class Solution {

    private static final int[][] DIRECTIONS = {{-2, 1}, {-1, 2}, {2, 1}, {1, 2}, {2, -1}, {1, -2}, {-2, -1}, {-1, -2}};

    private int[][] field;
    private boolean[][] visited;
    private int[] start;
    private int[] target;

    public int solution(int len, int[] start, int[] target) {
        init(len, start, target);
        return calc();
    }

    private void init(int len, int[] start, int[] target) {
        this.field = new int[len][len];
        this.visited = new boolean[len][len];
        this.start = start;
        this.target = target;
    }

    private int calc() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {start[0], start[1], 0});
        visited[start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if(cur[0] == target[0] && cur[1] == target[1]) {
                return cur[2];
            }

            for (int[] direction : DIRECTIONS) {
                int nx = cur[0] + direction[0];
                int ny = cur[1] + direction[1];

                if(out(nx, ny)) continue;
                if(visited[nx][ny]) continue;

                q.offer(new int[] {nx, ny, cur[2] + 1});
                visited[nx][ny] = true;
            }
        }

        return -1;
    }

    private boolean out(int x, int y) {
        return x < 0 || x >= field.length || y < 0 || y >= field[x].length;
    }
}
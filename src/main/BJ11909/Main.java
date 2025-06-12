package main.BJ11909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println(new Solution().solution(readInput()));
    }

    private int[][] readInput() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(br.readLine());

            int[][] result = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    result[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            return result;

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private static final int INF = Integer.MAX_VALUE;
    private static final int[][] ds = { { 0, 1 }, { 1, 0 } };

    private int[][] field;
    private int[][] dist;

    public int solution(int[][] field) {
        init(field);
        calc();
        return getAnswer();
    }

    private void init(int[][] field) {
        this.field = field;
        this.dist = new int[field.length][field[0].length];

        for (int[] d : dist) {
            Arrays.fill(d, INF);
        }
    }

    private void calc() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        pq.offer(new int[] { 0, 0, 0 }); // x, y, cost
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0], y = cur[1], cost = cur[2];

            if (dist[x][y] < cost)
                continue;

            for (int[] d : ds) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (out(nx, ny))
                    continue;

                int nextCost = cost;
                if (field[nx][ny] >= field[x][y]) {
                    nextCost += field[nx][ny] - field[x][y] + 1;
                }

                if (dist[nx][ny] > nextCost) {
                    dist[nx][ny] = nextCost;
                    pq.offer(new int[] { nx, ny, nextCost });
                }
            }
        }
    }

    private boolean out(int x, int y) {
        return field.length <= x || x < 0 || field[x].length <= y || y < 0;
    }

    private int getAnswer() {
        int n = field.length - 1;
        return dist[n][n];
    }
}
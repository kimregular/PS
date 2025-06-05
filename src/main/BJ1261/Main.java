package main.BJ1261;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int width = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            int[][] result = new int[height][width];

            for(int i = 0; i < height; i++) {
                char[] tokens = br.readLine().toCharArray();
                for(int j = 0; j < width; j++) {
                    result[i][j] = tokens[j] - '0';
                }
            }

            return result;
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private static final int[][] ds = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static final int INF = 1_000;

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

        for(int[] d : dist) {
            Arrays.fill(d, INF);
        }
    }

    private void calc() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        pq.offer(new int[] {0, 0, 0}); // height, width, weight
        dist[0][0] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(dist[cur[0]][cur[1]] < cur[2]) continue;

            for(int[] d : ds) {
                int nx = cur[0] + d[0];
                int ny = cur[1] + d[1];

                if(out(nx, ny)) continue;

                int nextCost = field[nx][ny] + cur[2];

                if(nextCost >= dist[nx][ny]) continue;

                dist[nx][ny] = nextCost;
                pq.offer(new int[] {nx, ny, nextCost});
            }
        }
    }

    private boolean out(int x, int y) {
        return field.length <= x || x < 0 || field[x].length <= y || y < 0;
    }

    private int getAnswer() {
        int targetHeight = field.length - 1;
        int targetWidth = field[0].length - 1;
        return dist[targetHeight][targetWidth];
    }
}
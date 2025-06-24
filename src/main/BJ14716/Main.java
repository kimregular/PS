package main.BJ14716;

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
        System.out.println(new Solution().solution(readInput()));
    }

    private int[][] readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());
            int[][] result = new int[height][width];

            for(int i = 0; i < height; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < width; j++) {
                    result[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            return result;
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private static final int[][] ds = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};

    private int[][] field;
    private boolean[][] visited;
    private int result;

    public int solution(int[][] field) {
        init(field);
        calc();
        return result;
    }

    private void init(int[][] field) {
        this.field = field;
        this.visited = new boolean[field.length][field[0].length];
        this.result = 0;
    }

    private void calc() {
        int height = field.length;
        int width = field[0].length;
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(field[i][j] == 0) continue;
                if(visited[i][j]) continue;
                check(i, j);
                result++;
            }
        }
    }

    private void check(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {x, y});
        visited[x][y] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int[] d : ds) {
                int nx = cur[0] + d[0];
                int ny = cur[1] + d[1];

                if(out(nx, ny)) continue;
                if(visited[nx][ny]) continue;
                if(field[nx][ny] == 0) continue;

                q.offer(new int[] {nx, ny});
                visited[nx][ny] = true;
            }
        }
    }

    private boolean out(int x, int y) {
        return field.length <= x || x < 0 || field[x].length <= y || y < 0;
    }
}
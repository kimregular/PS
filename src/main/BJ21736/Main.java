package main.BJ21736;

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

    private char[][] readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int height = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());

            char[][] result = new char[height][width];

            for(int i = 0; i < height; i++) {
                result[i] = br.readLine().toCharArray();
            }

            return result;
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private static final int[][] ds = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private static final char WALL = 'X';

    private char[][] field;
    private boolean[][] visited;
    private int result;

    private int[] startPosition;

    public String solution(char[][] field) {
        init(field);
        calc();
        return result == 0 ? "TT" : result + "";
    }

    private void init(char[][] field) {
        this.field = field;
        this.visited = new boolean[field.length][field[0].length];
        this.result = 0;

        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[i].length; j++) {
                if(field[i][j] == 'I') {
                    startPosition = new int[] {i, j};
                    return;
                }
            }
        }
    }

    private void calc() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(startPosition);
        visited[startPosition[0]][startPosition[1]] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            if(field[cur[0]][cur[1]] == 'P') result++;

            for(int[] d : ds) {
                int nx = cur[0] + d[0];
                int ny = cur[1] + d[1];

                if(out(nx, ny)) continue;
                if(field[nx][ny] == WALL) continue;
                if(visited[nx][ny]) continue;

                q.offer(new int[] {nx, ny});
                visited[nx][ny] = true;
            }
        }
    }

    private boolean out(int x, int y) {
        return field.length <= x || x < 0 || field[x].length <= y || y < 0;
    }
}
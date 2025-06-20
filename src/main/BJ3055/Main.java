package main.BJ3055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
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

            char[][] field = new char[height][width];

            for(int i = 0; i < height; i++) {
                field[i] = br.readLine().toCharArray();
            }
            return field;
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private static final int UNREACHED = Integer.MAX_VALUE;
    private static final int[][] ds = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    private static final char ROCK = 'X';
    private static final char TARGET = 'D';
    private static final char START = 'S';
    private static final char WATER = '*';

    private char[][] field;
    private Queue<int[]> waters;

    private int[][] waterTiming;

    private int[] startPosition;
    private int[] targetPosition;

    

    public String solution(char[][] field) {
        init(field);
        flood();
        return escape();
    }

    private void init(char[][] field) {
        this.field = field;
        this.waters = new ArrayDeque<>();

        this.waterTiming = new int[field.length][field[0].length];

        for(int[] w : waterTiming) {
            Arrays.fill(w, UNREACHED);
        }
        
        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[i].length; j++) {
                if(field[i][j] == START) {
                    startPosition = new int[] {i, j};
                } else if (field[i][j] == TARGET) {
                    targetPosition = new int[] {i, j};
                } else if (field[i][j] == WATER) {
                    waters.add(new int[] {i, j, 0});
                    waterTiming[i][j] = 0;
                }
            }
        }
    }

    private void flood() {
        while(!waters.isEmpty()) {
            int[] cur = waters.poll();

            for(int[] d : ds) {
                int nx = cur[0] + d[0];
                int ny = cur[1] + d[1];

                if(out(nx, ny)) continue;
                if(field[nx][ny] == ROCK) continue;
                if(field[nx][ny] == TARGET) continue;
                if(waterTiming[nx][ny] != UNREACHED) continue;

                waterTiming[nx][ny] = cur[2] + 1;
                waters.add(new int[] {nx, ny, cur[2] + 1});
            }
        }
    }

    private String escape() {
        boolean[][] visited = new boolean[field.length][field[0].length];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {startPosition[0], startPosition[1], 0});
        visited[startPosition[0]][startPosition[1]] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            if(targetReached(cur)) return cur[2] + "";

            for(int[] d : ds) {
                int nx = cur[0] + d[0];
                int ny = cur[1] + d[1];

                if(out(nx, ny)) continue;
                if(field[nx][ny] == ROCK) continue;
                if(visited[nx][ny]) continue;
                if(cur[2] >= waterTiming[nx][ny] - 1) continue;
                

                visited[nx][ny] = true;
                q.offer(new int[] {nx, ny, cur[2] + 1});
            }
        }
        return "KAKTUS";
    }

    private boolean out(int x, int y) {
        return field.length <= x || x < 0 || field[x].length <= y || y < 0;
    }

    private boolean targetReached(int[] position) {
        return position[0] == targetPosition[0] && position[1] == targetPosition[1];
    }
}
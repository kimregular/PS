package main.BJ1743;

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
        Input ip = readInput();
        System.out.println(new Solution().solution(ip.height, ip.width, ip.foods));
    }

    private Input readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());
            int numOfFood = Integer.parseInt(st.nextToken());

            int[][] foods = new int[numOfFood][2];

            for(int i = 0; i < foods.length; i++) {
                st = new StringTokenizer(br.readLine());
                foods[i][0] = Integer.parseInt(st.nextToken());
                foods[i][1] = Integer.parseInt(st.nextToken());
            }

            return new Input(height, width, foods);
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }

    private static class Input {

        final int height;
        final int width;
        final int[][] foods;

        public Input(int height, int width, int[][] foods) {
            this.height = height;
            this.width = width;
            this.foods = foods;
        }
    }
}

class Solution {

    private static final int[][] ds = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    private static final char FOOD = '#';
    private static final char EMPTY = '\u0000';

    private char[][] field;
    private int result;

    public int solution(int height, int width, int[][] foods) {
        init(height, width, foods);
        calc();
        return result;
    }

    private void init(int height, int width, int[][] foods) {
        this.field = new char[height][width];

        for(int[] food : foods) {
            field[food[0] - 1][food[1] - 1] = FOOD;
        }
    }

    private void calc() {
        boolean[][] check = new boolean[field.length][field[0].length];
        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[i].length; j++) {
                if(field[i][j] == EMPTY) continue;
                if(check[i][j]) continue;
                result = Math.max(result, BFS(i, j, check));
            }
        }
    }

    private int BFS(int x, int y, boolean[][] check) {
        check[x][y] = true;
        int width = 1;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {x, y});

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int[] d : ds) {
                int nx = cur[0] + d[0];
                int ny = cur[1] + d[1];

                if(out(nx, ny)) continue;
                if(field[nx][ny] == EMPTY) continue;
                if(check[nx][ny]) continue;

                width++;
                q.offer(new int[] {nx, ny });
                check[nx][ny] = true;
            }
        }
        return width;
    }

    private boolean out(int x, int y) {
        return field.length <= x || x < 0 || field[x].length <= y || y < 0;
    }
}
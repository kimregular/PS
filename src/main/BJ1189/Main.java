package main.BJ1189;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Input ip = readInput();
        System.out.println(new Solution().solution(ip.field, ip.threshold));
    }

    private Input readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int width = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int threshold = Integer.parseInt(st.nextToken());

            char[][] field = new char[width][height];

            for(int i = 0; i < width; i++) {
                field[i] = br.readLine().toCharArray();
            }

            return new Input(field, threshold);
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }

    private static class Input {

        final char[][] field;
        final int threshold;

        public Input(char[][] field, int threshold) {
            this.field = field;
            this.threshold = threshold;
        }
    }
}

class Solution {

    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    private static final char WALL = 'T';


    private char[][] field;
    private boolean[][] visited;
    private int threshold;
    private int result;

    public int solution(char[][] field, int threshold) {
        init(field, threshold);
        calc();
        return result;
    }

    private void init(char[][] field, int threshold) {
        this.field = field;
        this.visited = new boolean[field.length][field[0].length];
        this.threshold = threshold;
        this.result = 0;
    }

    private void calc() {
        DFS(field.length - 1, 0, 1);
    }

    private void DFS(int height, int width, int step) {
        if(height == 0 && width == field[0].length - 1) {
            if(step == threshold) result++;
            return;
        }

        for(int[] d : DIRECTIONS) {
            int nx = height + d[0];
            int ny = width + d[1];

            if(out(nx, ny)) continue;
            if(field[nx][ny] == WALL) continue;
            if(visited[nx][ny]) continue;

            visited[nx][ny] = true;
            DFS(nx, ny, step + 1);
            visited[nx][ny] = false;
        }

    }

    private boolean out(int x, int y) {
        return field.length <= x || x < 0 || field[x].length <= y || y < 0;
    }
}
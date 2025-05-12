package main.BJ2638;

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

            int[][] field = new int[height][width];

            for(int i = 0; i < height; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < width; j++) {
                    field[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            return field;
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    private int[][] field;
    private boolean[][] isOutside;
    private Queue<int[]> cheeseQ;
    private Queue<int[]> meltTargets;
    private int result;

    public int solution(int[][] field) {
        init(field);
        calc();
        return result;
    }

    private void init(int[][] field) {
        this.field = field;
        this.isOutside = new boolean[field.length][field[0].length];
        this.cheeseQ = new ArrayDeque<>();
        this.result = 0;

        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[i].length; j++) {
                if(field[i][j] == 0) continue;
                cheeseQ.offer(new int[] {i, j});
            }
        }
    }

    private void calc() {
        while(!cheeseQ.isEmpty()) {
            checkOutside();
            searchMeltCheeses();
            melt();
            result++;
        }
    }

    private void checkOutside() {
        this.isOutside = new boolean[field.length][field[0].length];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        isOutside[0][0] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int[] d : DIRECTIONS) {
                int nx = cur[0] + d[0];
                int ny = cur[1] + d[1];

                if(out(nx, ny)) continue;
                if(field[nx][ny] != 0) continue;
                if(isOutside[nx][ny]) continue;

                isOutside[nx][ny] = true;
                q.offer(new int[] {nx, ny});
            }
        }
    }

    private void searchMeltCheeses() {
        Queue<int[]> meltCandidate = new ArrayDeque<>();
        Queue<int[]> newCheeses = new ArrayDeque<>();

        while(!cheeseQ.isEmpty()) {
            int[] cheese = cheeseQ.poll();

            if(isMeltTarget(cheese)) meltCandidate.offer(cheese);
            else newCheeses.offer(cheese);
        }

        this.meltTargets = meltCandidate;
        this.cheeseQ = newCheeses;
    }

    private void melt() {
        while(!meltTargets.isEmpty()) {
            int[] cur = meltTargets.poll();
            field[cur[0]][cur[1]] = 0;
        }
    }


    private boolean out(int x, int y) {
        return field.length <= x || x < 0 || field[x].length <= y || y < 0;
    }

    private boolean isMeltTarget(int[] cheese) {
        int cnt = 0;
        for(int[] d : DIRECTIONS) {
            int nx = cheese[0] + d[0];
            int ny = cheese[1] + d[1];

            if(out(nx, ny)) continue;
            if(isOutside[nx][ny]) cnt++;
        }

        return cnt >= 2;
    }
}
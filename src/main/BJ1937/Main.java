package main.BJ1937;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

            int len = Integer.parseInt(br.readLine());
            int[][] result = new int[len][len];

            for(int i = 0; i < len; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < len; j++) {
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

    private static final int[][] ds = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    private int[][] field;
    private int[][] dp;

    public int solution(int[][] field) {
        init(field);
        calc();
        return getAnswer();
    }

    private void init(int[][] field) {
        this.field = field;
        this.dp = new int[field.length][field.length];
    }

    private void calc() {
        int n = field.length;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                dp[i][j] = dfs(i, j);
            }
        }
    }

    private int dfs(int x, int y) {
        if(dp[x][y] != 0) return dp[x][y];

        dp[x][y] = 1; // 자기 칸은 항상 1칸임

        for(int[] d : ds) {
            int nx = x + d[0];
            int ny = y + d[1];

            if(out(nx, ny)) continue;
            if(field[nx][ny] <= field[x][y]) continue;
            dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
        }
        return dp[x][y];
    }

    private boolean out(int x, int y) {
        return field.length <= x || x < 0 || field.length <= y || y < 0;
    }

    private int getAnswer() {
        int result = 0;
        int n = field.length;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                result = Math.max(result, dp[i][j]);
            }
        }
        return result;
    }
}
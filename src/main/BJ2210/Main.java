package main.BJ2210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
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

            int[][] result = new int[5][5];

            for(int i = 0; i < 5; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 5; j++) {
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

    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    private int[][] field;
    private Set<Integer> set;

    public int solution(int[][] field) {
        init(field);
        calc();
        return set.size();
    }

    private void init(int[][] field) {
        this.field = field;
        this.set = new HashSet<>();
    }

    private void calc() {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                explore(i, j, 0, field[i][j]);
            }
        }
    }

    private void explore(int x, int y, int cnt, int num) {
        if(cnt == 5) {
            set.add(num);
            return;
        }

        for(int[] d : DIRECTIONS) {
            int nx = x + d[0];
            int ny = y + d[1];

            if(out(nx, ny)) continue;
            explore(nx, ny, cnt + 1, (num * 10) + field[nx][ny]);
        }
    }

    private boolean out(int nx, int ny) {
        return nx < 0 || nx >= 5 || ny < 0 || ny >= 5;
    }
}
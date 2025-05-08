package main.BJ17070;

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

    private int[][] field;
    private int result;

    public int solution(int[][] field) {
        init(field);
        calc(0, 1, 0);
        return result;
    }

    private void init(int[][] field) {
        this.field = field;
    }

    private void calc(int x, int y, int direction) {
        if(x == field.length - 1 && y == field.length - 1) {
            result++;
            return;
        }

        if(direction == 0) {
            if(y + 1 < field.length && field[x][y + 1] == 0) {
                calc(x, y + 1, 0);
            }
        } else if (direction == 1) {
            if(x + 1 < field.length && field[x + 1][y] == 0) {
                calc(x + 1, y, 1);
            }
        } else if (direction == 2) {
            if(y + 1 < field.length && field[x][y + 1] == 0) {
                calc(x, y + 1, 0);
            }

            if(x + 1 < field.length && field[x + 1][y] == 0) {
                calc(x + 1, y, 1);
            }
        }

        if(x + 1 < field.length && y + 1 < field.length && field[x + 1][y] == 0 && field[x][y + 1] == 0 && field[x + 1][y + 1] == 0) {
            calc(x + 1, y + 1, 2);
        }
    }
}
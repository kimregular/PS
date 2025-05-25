package main.BJ17265;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println(new Solution().solution(readInput()));
    }

    private char[][] readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int len = Integer.parseInt(br.readLine());
            char[][] field = new char[len][len];

            for(int i = 0; i < len; i++) {
                String[] tokens = br.readLine().split(" ");
                for(int j = 0; j < len; j++) {
                    field[i][j] = tokens[j].charAt(0);
                }
            }

            return field;
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private static final int[][] DS = {{0, 1}, {1, 0}};

    private int len;
    private char[][] field;
    private int max;
    private int min;

    public String solution(char[][] field) {
        init(field);
        calc(0, 0, Character.getNumericValue(field[0][0]), ' ');
        return max + " " + min;
    }

    private void init(char[][] field) {
        this.len =  field.length;
        this.field = field;
        this.max = Integer.MIN_VALUE;
        this.min = Integer.MAX_VALUE;
    }

    private void calc(int x, int y, int value, char oper) {
        if(x == len - 1 && y == len - 1) {
            max = Math.max(max, value);
            min = Math.min(min, value);
            return;
        }

        for(int[] d : DS) {
            int nx = x + d[0];
            int ny = y + d[1];

            if(out(nx, ny)) continue;
            if(Character.isDigit(field[nx][ny])) {
                int num = Character.getNumericValue(field[nx][ny]);
                calc(nx, ny, oper(value, oper, num), ' ');
            } else {
                calc(nx, ny, value, field[nx][ny]);
            }
        }

    }

    private boolean out(int x, int y) {
        return len <= x || x < 0 || len <= y || y < 0;
    }

    private int oper(int value, char operation, int num) {
        if(operation == '+') return value + num;
        if(operation == '-') return value - num;
        if(operation == '*') return value * num;
        return value;
    }
}
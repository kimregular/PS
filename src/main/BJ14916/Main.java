package main.BJ14916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Solution s = new Solution();
        System.out.println(s.solution(readInput()));
    }

    private int readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            return Integer.parseInt(br.readLine());

        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private static int[] field = new int[100_001];

    static {
        Arrays.fill(field, -1);
        field[0] = 0;
    }

    public int solution(int target) {
        return calc(target);    
    }

    private int calc(int price) {
        if (price < 0) return -1;
        if(field[price] != -1) return field[price];

        int from2 = calc(price - 2);
        int from5 = calc(price - 5);

        if(from2 == -1 && from5 == -1) {
            field[price] = -1;
        }else if (from2 == -1) {
            field[price] = from5 + 1;
        } else if (from5 == -1) {
            field[price] = from2 + 1;
        } else {
            field[price] = Math.min(from2, from5) + 1;
        }
        return field[price];
    }
}
package main.BJ9461;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Solution s = new Solution();
        System.out.println(s.solution(readInput()));
    }

    private int[] readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int len = Integer.parseInt(br.readLine());
            int[] testCases = new int[len];

            for(int i = 0; i < len; i++) {
                testCases[i] = Integer.parseInt(br.readLine());
            }

            return testCases;

        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private static final long[] field = new long[101];
    private static final StringBuilder result = new StringBuilder();

    public String solution(int[] targets) {
        for(int target : targets) {
            result.append(calc(target)).append("\n");
        }
        return result.toString();
    }

    private long calc(int target) {
        if(target <= 0) return 0;
        if(target <= 3) return 1;

        if(field[target] != 0) return field[target];

        field[target] = calc(target - 2) + calc(target - 3);

        return field[target];
    }
}
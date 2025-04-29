package main.BJ2562;

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

    private int[] readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int[] result = new int[9];

            for(int i = 0; i < 9; i++) {
                result[i] = Integer.parseInt(br.readLine());
            }
            return result;
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    public String solution(int[] input) {
        int max = Integer.MIN_VALUE;
        int idx = -1;

        for(int i = 0; i < input.length; i++) {
            if(max < input[i]) {
                max = input[i];
                idx = i;
            }
        }

        return max + "\n" + (idx + 1);
    }
}
package main.BJ32326;

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

            int[] result = new int[3];
            for (int i = 0; i < result.length; i++) {
                result[i] = Integer.parseInt(br.readLine());
            }

            return result;

        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    public int solution(int[] input) {
        int result = 0;
        int dollar = 3;
        
        for(int i = 0; i < input.length; i++) {
            result += (input[i] * dollar++);
        }

        return result;
    }
}
package main.BJ1427;

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

    private int readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            return Integer.parseInt(br.readLine());

        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    public int solution(int target) {
        int[] field = new int[10];

        while(target > 0) {
            field[target % 10]++;
            target /= 10;
        }

        int result = 0;

        for(int i = 9; i >= 0; i--) {
            for(int j = 0; j < field[i]; j++) {
                result *= 10;
                result += i;
            }
        }

        return result;
    }
}
package main.BJ10872;

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
        int result = 1;

        for(int i = 1; i <= target; i++) result *= i;

        return result;
    }
}
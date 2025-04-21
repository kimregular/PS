package main.BJ2439;

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

    public String solution(int len) {
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < len; i++) {
            result.append(" ".repeat(len - i - 1)).append("*".repeat(i + 1)).append("\n");
        }
        return result.toString();
    }
}
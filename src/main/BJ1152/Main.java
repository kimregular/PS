package main.BJ1152;

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

    private String readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            return br.readLine();

        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    public int solution(String target) {
        int result = 0;
        String[] tokens = target.split(" ");
        for(String token : tokens) {
            if(token.equals(" ") || token.isEmpty()) continue;
            result++;
        }
        return result;
    }
}
package main.BJ2744;

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

    public String solution(String target) {
        StringBuilder answer = new StringBuilder();

        for(char c : target.toCharArray()) {
            if(Character.isLowerCase(c)) {
                answer.append(Character.toUpperCase(c));
            } else {
                answer.append(Character.toLowerCase(c));
            }
        }
        return answer.toString();
    }
}
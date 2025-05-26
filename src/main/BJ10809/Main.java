package main.BJ10809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

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

    private int[] field;

    public String solution(String target) {
        init();
        
        for(int i = 0; i < target.length(); i++) {
            int t = target.charAt(i) - 'a';

            if(field[t] != -1) continue;
            else field[t] = i;
        }

        return getAnswer();
    }

    private void init() {
        this.field = new int[26];
        Arrays.fill(field, -1);
    }

    private String getAnswer() {
        StringBuilder result = new StringBuilder();
        for(int i : field) result.append(i).append(" ");
        return result.toString();
    }
}
package main.BJ2739;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            Solution s = new Solution();
            System.out.println(s.solution(readInput(br)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int readInput(BufferedReader br) throws IOException {
        return Integer.parseInt(br.readLine());
    }
}

class Solution {

    public String solution(int target) {
        StringBuilder answer = new StringBuilder();

        for (int i = 1; i <= 9; i++) {
            answer.append(target).append(" * ").append(i)
                  .append(" = ").append(target * i).append("\n");
        }
        return answer.toString();
    }
}
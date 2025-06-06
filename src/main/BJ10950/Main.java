package main.BJ10950;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println(new Solution().solution(readInput()));
    }

    private int[][] readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int len = Integer.parseInt(br.readLine());

            int[][] result = new int[len][2];

            for(int i = 0; i < len; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                result[i][0] = Integer.parseInt(st.nextToken());
                result[i][1] = Integer.parseInt(st.nextToken());
            }

            return result;

        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    public String solution(int[][] testCases) {
        StringBuilder answer = new StringBuilder();

        for(int[] testCase : testCases) {
            answer.append(add(testCase)).append("\n");
        }

        return answer.toString();
    }

    private int add(int[] target) {
        return target[0] + target[1];
    }
}
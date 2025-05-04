package main.BJ2503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
            int[][] result = new int[len][3];
            for(int i = 0; i < len; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                result[i][0] = Integer.parseInt(st.nextToken());
                result[i][1] = Integer.parseInt(st.nextToken());
                result[i][2] = Integer.parseInt(st.nextToken());
            }
            return result;
        }catch (IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    public int solution(int[][] inputs) {
        int result = 0;

        for(int i = 1; i <= 9; i++) {
            for(int j = 1; j <= 9; j++) {
                for(int k = 1; k <= 9; k++) {
                    if(i == j || j == k || i == k) continue;

                    int counter = 0;
                    
                    for(int[] input : inputs) {
                        
                        int target = input[0];
                        int strike = input[1];
                        int ball = input[2];

                        int strikeCount = 0;
                        int ballCount = 0;

                        // 스트라이크 판별
                        if(i == target / 100) strikeCount++;
                        if(j == target % 100 / 10) strikeCount++;
                        if(k == target % 100 % 10) strikeCount++;

                        // 볼 판별
                        if(i == target % 100 / 10 || i == target % 100 % 10) ballCount++;
                        if(j == target / 100 || j == target % 100 % 10) ballCount++;
                        if(k == target / 100 || k == target % 100 / 10) ballCount++;

                        if(strike != strikeCount) break;
                        if(ball != ballCount) break;

                        counter++;
                    }

                    if(counter == inputs.length) result++;
                }
            }
        }
        return result;
    }
}


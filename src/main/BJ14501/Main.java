package main.BJ14501;

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

    private int[][] schedules;
    private int result;

    public int solution(int[][] schedules) {
        init(schedules);
        calc(0, 0);
        return result;
    }

    private void init(int[][] schedules) {
        this.schedules = schedules;
        this.result = 0;
    }

    private void calc(int idx, int price) {
        if(idx > schedules.length) return;

        if(idx == schedules.length) {
            if(result < price) {
                result = price;
            }
            return;
        }

        calc(idx + 1, price);
        calc(idx + schedules[idx][0], price + schedules[idx][1]);
    }
}
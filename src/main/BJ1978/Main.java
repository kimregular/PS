package main.BJ1978;

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

    private int[] readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int len = Integer.parseInt(br.readLine());
            int[] result = new int[len];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < len; i++) {
                result[i] = Integer.parseInt(st.nextToken());
            }
            return result;
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Solution {

    public int solution(int[] numbers) {
        int result = 0;
        for(int number : numbers) {
            if(isPrime(number)) result++;
        }
        return result;
    }

    private boolean isPrime(int number) {
        if(number == 1) return false;
        for(int i = 2; i <= Math.sqrt(number); i++) {
            if(number % i == 0) { return false; }
        }
        return true;
    }
}
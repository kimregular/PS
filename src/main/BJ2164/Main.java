package main.BJ2164;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

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

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Solution {

    public int solution(int num) {
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i = 1; i <= num; i++) {
            dq.offer(i);
        }

        while(dq.size() > 1) {
            dq.poll();
            dq.offerLast(dq.poll());
        }

        return dq.peek();
    }
}
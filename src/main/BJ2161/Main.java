package main.BJ2161;

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

        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private Deque<Integer> dq;
    private StringBuilder answer;

    public String solution(int target) {
        init(target);
        calc();
        return answer.toString();
    }

    private void init(int target) {
        this.dq = new ArrayDeque<>();
        this.answer = new StringBuilder();

        for(int i = 1; i <= target; i++) {
            dq.offer(i);
        }
    }

    private void calc() {
        while(dq.size() > 1) {
            answer.append(dq.poll()).append(" ");
            dq.offer(dq.poll());
        }
        answer.append(dq.poll());
    }
}
package main.BJ1927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

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

    private int[] readInput(BufferedReader br) throws IOException {
        int len = Integer.parseInt(br.readLine());
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = Integer.parseInt(br.readLine());
        }
        return result;
    }
}

class Solution {

    private PriorityQueue<Integer> q = new PriorityQueue<>();

    public String solution(int[] orders) {
        StringBuilder answer = new StringBuilder();

        for (int order : orders) {
            if (order != 0) {
                q.offer(order);
            } else {
                answer.append(q.isEmpty() ? 0 : q.poll()).append("\n");
            }
        }

        return answer.toString();
    }
}
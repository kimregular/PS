package main.BJ5430;

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
        StringBuilder answer = new StringBuilder();
        Solution s = new Solution();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int TEST_CASES = Integer.parseInt(br.readLine());

            for(int i = 0; i < TEST_CASES; i++) {
                Input ip = readInput(br);
                answer.append(s.solution(ip.orders, ip.lst)).append("\n");
            }

        }catch(IOException e) {
            throw new RuntimeException();
        }

        System.out.println(answer);
    }

    private Input readInput(BufferedReader br) throws IOException {
        char[] orders = br.readLine().toCharArray();
        int len = Integer.parseInt(br.readLine());
        Deque<Integer> lst = new ArrayDeque<>();
        String target = br.readLine();
        target = target.substring(1);
        target = target.substring(0, target.length() - 1);
        String[] st = target.split(",");
        for (int i = 0; i < len; i++) {
            lst.add(Integer.parseInt(st[i]));
        }
        return new Input(orders, lst);
    }

    private static class Input {

        final char[] orders;
        final Deque<Integer> lst;

        public Input(char[] orders, Deque<Integer> lst) {
            this.orders = orders;
            this.lst = lst;
        }
    }
}

class Solution {

    public String solution(char[] orders, Deque<Integer> lst) {
        boolean reversed = false;
        for(char order : orders) {
            if(order == 'R') {
                // R
                reversed = !reversed;
            } else {
                // D
                if(lst.isEmpty()) return "error";
                if(reversed) lst.pollLast();
                else lst.pollFirst();
            }
        }
        return getAnswer(lst, reversed);
    }

    private String getAnswer(Deque<Integer> lst, boolean reversed) {
        if(lst.isEmpty()) return "[]";
        StringBuilder result = new StringBuilder();
        result.append("[");
        while(!lst.isEmpty()) {
            result.append(reversed ? lst.pollLast() : lst.pollFirst()).append(',');
        }
        result.setLength(result.length() - 1);
        result.append("]");
        return result.toString(); 
    }
}
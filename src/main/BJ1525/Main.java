package main.BJ1525;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println(new Solution().solution(readInput()));
    }

    private String readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringBuilder result = new StringBuilder();

            for(int i = 0; i < 3; i++) {
                String[] tokens = br.readLine().split(" ");
                for(int j = 0; j < 3; j++) {
                    result.append(tokens[j]);
                }
            }

            return result.toString();

        } catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private static final String target = "123456780";
    private static final int[] ds = {-3, 3, -1, 1};

    private Set<String> set;

    public int solution(String input) {
        init();
        return calc(input);
    }

    private void init() {
        this.set = new HashSet<>();
    }

    private int calc(String input) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(input, 0));
        set.add(new String(input));

        while(!q.isEmpty()) {
            Node cur = q.poll();

            if(cur.status.equals(target)) {
                return cur.step;
            }

            int idx = cur.status.indexOf("0");

            for(int d : ds) {
                int nextDs = idx + d;

                if(out(nextDs, idx)) continue;
                Node newNode = swap(cur, idx, nextDs);
                if(set.contains(newNode.status)) continue;
                q.offer(newNode);
                set.add(newNode.status);
            }
        }

        return -1;
    }

    private boolean out(int nextIdx, int curIdx) {
        if(8 < nextIdx || nextIdx < 0) return true;
        if(curIdx % 3 == 0 && (nextIdx == -1 || nextIdx == 2 || nextIdx == 5)) return true;
        if(nextIdx % 3 == 0 && (curIdx == 2 || curIdx == 5 || curIdx == 8)) return true;
        return false;
    }

    private Node swap(Node cur, int curIdx, int newIdx) {
        char[] status = cur.status.toCharArray();
        int step = cur.step;
        
        char temp = status[curIdx];
        status[curIdx] = status[newIdx];
        status[newIdx] = temp;
        return new Node(new String(status), step + 1);
    }

    private static class Node {

        final String status;
        final int step;

        public Node(String status, int step) {
            this.status = status;
            this.step = step;
        }
    }
}
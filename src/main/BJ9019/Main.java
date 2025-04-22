package main.BJ9019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        StringBuilder answer = new StringBuilder();
        Solution s = new Solution();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int len = Integer.parseInt(br.readLine());
        

            for(int i = 0; i < len; i++) {
                s.init(readInput(br));
                answer.append(s.solution()).append("\n");
            }


        }catch(IOException e) {
            throw new RuntimeException();
        }

        System.out.println(answer);
    }

    private int[] readInput(BufferedReader br) throws IOException {
            int[] result = new int[2];

            StringTokenizer st = new StringTokenizer(br.readLine());
            result[0] = Integer.parseInt(st.nextToken());
            result[1] = Integer.parseInt(st.nextToken());

            return result;
    }
}

class Solution {

    private int start;
    private int target;
    private boolean[] visited;
    private Queue<Node> q;
    

    public void init(int[] testCase) {
        this.start = testCase[0];
        this.target = testCase[1];
        this.visited = new boolean[100_001];
        this.q = new ArrayDeque<>();
    }

    public String solution() {
        q.offer(new Node(start, new StringBuilder()));
        visited[start] = true;

        while(!q.isEmpty()) {
            Node cur = q.poll();

            if(cur.isSameWith(target)) {
                return cur.history.toString();
            }

            int nextNum;
            // D
            nextNum = (cur.num * 2) % 10_000;
            if(!visited[nextNum]) {
                visited[nextNum] = true;
                q.offer(new Node(nextNum, new StringBuilder(cur.history).append("D")));
            }
            // S
            nextNum = cur.num == 0 ? 9999 : cur.num - 1;
            if(!visited[nextNum]) {
                visited[nextNum] = true;
                q.offer(new Node(nextNum, new StringBuilder(cur.history).append("S")));
            }

            // L
            nextNum = (cur.num % 1000) * 10 + (cur.num / 1000);
            if(!visited[nextNum]) {
                visited[nextNum] = true;
                q.offer(new Node(nextNum, new StringBuilder(cur.history).append("L")));
            }

            // R
            nextNum = (cur.num % 10) * 1000 + (cur.num / 10);
            if(!visited[nextNum]) {
                visited[nextNum] = true;
                q.offer(new Node(nextNum, new StringBuilder(cur.history).append("R")));
            }
        }
        return null;
    }
    
    private static class Node {

        int num;
        StringBuilder history;

        public Node(int num, StringBuilder history) {
            this.num = num;
            this.history = history;
        }

        public boolean isSameWith(int target) {
            return num == target;
        }
    }
}
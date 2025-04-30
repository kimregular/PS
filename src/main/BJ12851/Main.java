package main.BJ12851;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
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
            StringTokenizer st = new StringTokenizer(br.readLine());
            int me = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            return new int[] {me, target};
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private int[] visited;
    private Queue<int[]> q;
    private int target;
    private int requiredTime;
    private int num;

    public String solution(int[] input) {
        init(input);
        calc();
        return requiredTime + "\n" + num;
    }

    private void init(int[] input) {
        this.visited = new int[100_001];
        this.q = new ArrayDeque<>();
        this.target = input[1];
        this.requiredTime = Integer.MAX_VALUE;
        this.num = 0;
        Arrays.fill(visited, Integer.MAX_VALUE);
        q.offer(new int[] {input[0], 0});
    }

    private void calc() {
        

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            if(cur[0] == target) {
                if(cur[1] == requiredTime) {
                    num++;
                } else if (cur[1] < requiredTime) {
                    num = 1;
                    requiredTime = cur[1];
                }
            }

            int nx;

            nx = cur[0] + 1;
            if(in(nx) && cur[1] + 1 <= visited[nx]) {
                q.offer(new int[] {nx, cur[1] + 1});
                visited[nx] = cur[1] + 1;
            }

            nx = cur[0] - 1;
            if(in(nx) && cur[1] + 1 <= visited[nx]) {
                q.offer(new int[] {nx, cur[1] + 1});
                visited[nx] = cur[1] + 1;
            }

            nx = cur[0] * 2;
            if(in(nx) && cur[1] + 1 <= visited[nx]) {
                q.offer(new int[] {nx, cur[1] + 1});
                visited[nx] = cur[1] + 1;
            }
        }
    }

    private boolean in(int x) {
        return 0 <= x && x < visited.length;
    }
}
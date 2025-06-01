package main.BJ12852;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

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

    private static final int MAX = 1_000_001;

    private int[] field;
    private int[] prev;
    private int required;

    public String solution(int target) {
        init();
        calc(target);
        return getAnswer();
    }

    private void init() {
        this.field = new int[MAX];
        this.prev = new int[MAX];
        this.required = MAX;

        Arrays.fill(field, -1);
        Arrays.fill(prev, -1);
    }

    private void calc(int target) {
        Queue<int[]> q = new ArrayDeque<>();
        field[target] = 0;
        q.offer(new int[] {target, 0, - 1}); // location, step, prev

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            prev[cur[0]] = cur[2];

            if(cur[0] == 1) {
                required = Math.min(required, cur[1]);
                continue;
            }

            int nx;

            if(cur[0] % 3 == 0) {
                nx = cur[0] / 3;
                if(isReachable(nx, cur[1]) && withinField(nx)) {
                    field[nx] = cur[1];
                    q.offer(new int[] {nx, cur[1] + 1, cur[0]});
                }
            }

            if(cur[0] % 2 == 0) {
                nx = cur[0] / 2;
                if(isReachable(nx, cur[1]) && withinField(nx)) {
                    field[nx] = cur[1];
                    q.offer(new int[] {nx, cur[1] + 1, cur[0]});
                } 
            }

            nx = cur[0] - 1;
            if(isReachable(nx, cur[1]) && withinField(nx)) {
                field[nx] = cur[1];
                q.offer(new int[] {nx, cur[1] + 1, cur[0]});
            }
        }
    }

    private boolean isReachable(int target, int step) {
        return field[target] == -1 ||  field[target] > step;
    }

    private boolean withinField(int num) {
        return 0 < num && num < MAX;
    }

    private String getAnswer() {
        List<Integer> stck = new ArrayList<>();
        stck.add(1);

        int step = prev[1];
        while(step != -1) {
            stck.add(step);
            step = prev[step];
        }
        
        StringBuilder answer = new StringBuilder();
        answer.append(required).append("\n");

        while(!stck.isEmpty()) {
            answer.append(stck.get(stck.size() - 1)).append(" ");
            stck.remove(stck.size() - 1);
        }

        return answer.toString();
    }
}
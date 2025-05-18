package main.BJ17298;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
            for(int i = 0; i < len; i++) {
                result[i] = Integer.parseInt(st.nextToken());
            }
            return result;
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private int[] targets;
    private int[] result;

    public String solution(int[] targets) {
        init(targets);
        calc();
        return getAnswer();
    }

    private void init(int[] targets) {
        this.targets = targets;
        this.result = new int[targets.length];
    }

    private void calc() {
        List<Integer> stck = new ArrayList<>();

        for(int i = targets.length - 1; i >= 0; i--) {
            int target = targets[i];
            if(stck.isEmpty()) {
                result[i] = -1;
            } else if(stck.get(stck.size() - 1) <= target) {
                while(!stck.isEmpty() && stck.get(stck.size() - 1) <= target) stck.remove(stck.size() - 1);
                if(stck.isEmpty()){
                    result[i] = -1;
                } else {
                    result[i] = stck.get(stck.size() - 1);
                }
            } else {
                result[i] = stck.get(stck.size() - 1);
            }
            stck.add(target);
        }
    }

    private String getAnswer() {
        StringBuilder answer = new StringBuilder();
        for(int i : result) answer.append(i).append(" ");
        return answer.toString();
    }
}
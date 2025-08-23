package main.BJ20437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static StringBuilder answer = new StringBuilder();
    static Map<Character, List<Integer>> map;
    public static void main(String[] args) throws IOException {    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TEST_CASES = Integer.parseInt(br.readLine());

        for(int i = 0; i < TEST_CASES; i++) {
            answer.append(solve(br)).append("\n");
        }
        System.out.println(answer);
        br.close();
    }

    static String solve(BufferedReader br) throws IOException {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        boolean redFlag = true;

        map = new HashMap<>();
        String w = br.readLine();
        int k = Integer.parseInt(br.readLine());

        for(int i = 0; i < w.length(); i++) {
            map.putIfAbsent(w.charAt(i), new ArrayList<>());
            map.get(w.charAt(i)).add(i);
        }

        for(Character key : map.keySet()) {
            List<Integer> list = map.get(key);
            if(list.size() >= k) {
                redFlag = false;
                Set<Integer> targets = getSizes(list, k);
                for(int target : targets) {
                    min = Math.min(min, target);
                    max = Math.max(max, target);
                }
            }
        }

        if (redFlag) return "-1";
        return min + " " + max;
    }

    static Set<Integer> getSizes(List<Integer> list, int k) {
        Set<Integer> result  = new HashSet<>();
        int left = 0;
        int right = k - 1;
        for(; right < list.size(); right++) {
            result.add(list.get(right) - list.get(left) + 1);
            left++;
        }
        return result;
    } 
}
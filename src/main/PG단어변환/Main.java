package main.PG단어변환;

import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        Map<String, Boolean> used = new HashMap<>();
        for(String word : words) {
            used.put(word, false);
        }
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(begin, 0));
        while(!q.isEmpty()) {
            Pair cur = q.poll();
            if(cur.word.equals(target)) {
                return cur.step;
            }
            
            for(String word : words) {
                if(used.get(word)) continue;
                if(!isOneLetterDiff(cur.word, word)) continue;
                q.offer(new Pair(word, cur.step + 1));
                used.put(word, true);
            }
        }
        return 0;
    }
    
    boolean isOneLetterDiff(String word1, String word2) {
    int diff = 0;
    for (int i = 0; i < word1.length(); i++) {
        if (word1.charAt(i) != word2.charAt(i)) {
            diff++;
            if (diff > 1) return false;
        }
    }
    return diff == 1;
}
    
    static class Pair {
        
        String word;
        int step;
        
        public Pair(String word, int step){
            this.word = word;
            this.step = step;
        }
    }
}
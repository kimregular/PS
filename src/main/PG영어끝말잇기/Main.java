import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        Set<String> used = new HashSet<>();
        
        char prevChar = words[0].charAt(words[0].length() - 1);
        used.add(words[0]);
        
        for(int i = 1; i < words.length; i++) {
            String target = words[i];
            
            if(used.contains(target)) {
                int person = (i % n) + 1;
                int turn = (i / n) + 1;
                return new int[] {person, turn};
            }
            used.add(target);
            if(target.charAt(0) != prevChar) {
                int person = (i % n) + 1;
                int turn = (i / n) + 1;
                return new int[] {person, turn};
            }
            prevChar = target.charAt(target.length() - 1);
        }
    
        return new int[] {0, 0};
    }
}
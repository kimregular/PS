package main.BJ17413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    
    static Deque<Character> brackets = new ArrayDeque<>();
    static Deque<Character> chars = new ArrayDeque<>();
    static boolean bracketFlag = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String target = br.readLine();

        StringBuilder answer = new StringBuilder();

        for(char c : target.toCharArray()) {
            if(c == '<') {
                bracketFlag = true;
                brackets.add(c);

                while(!chars.isEmpty()) {
                    answer.append(chars.pollLast());
                }
            } else if (c == '>') {
                bracketFlag = false;
                brackets.add(c);

                while(!brackets.isEmpty()) {
                    answer.append(brackets.pollFirst());
                }
            } else {
                if(bracketFlag) {
                    brackets.add(c);
                }else {
                    if(c == ' ') {
                        while(!chars.isEmpty()) {
                            answer.append(chars.pollLast());
                        }
                        answer.append(c);
                    } else {
                        chars.add(c);
                    }
                    
                }
            }
        }

        if(!chars.isEmpty()) {
            while(!chars.isEmpty()) {
                answer.append(chars.pollLast());
            }
        }

        System.out.println(answer);

        br.close();
    }
}

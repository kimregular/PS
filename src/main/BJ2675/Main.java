package main.BJ2675;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringBuilder answer = new StringBuilder();

            int testCases = Integer.parseInt(br.readLine());

            for(int i = 0; i < testCases; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int num = Integer.parseInt(st.nextToken());

                for(char c : st.nextToken().toCharArray()) {
                    for(int j = 0; j < num; j++) {
                        answer.append(c);
                    }
                }
                answer.append("\n");
            }

            System.out.println(answer);
            
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

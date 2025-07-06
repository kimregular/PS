package main.BJ15552;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder answer = new StringBuilder();

        int len = Integer.parseInt(br.readLine());

        for(int i = 0; i < len; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            answer.append(num1 + num2).append("\n");
        }

        System.out.println(answer);
        
        br.close();
    }
}

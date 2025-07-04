package main.BJ10952;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringBuilder answer = new StringBuilder();

            while(true) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int num1 = Integer.parseInt(st.nextToken());
                int num2 = Integer.parseInt(st.nextToken());

                if(num1 == 0 && num2 == 0) break;

                answer.append(num1 + num2).append("\n");
            }

            System.out.println(answer);
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

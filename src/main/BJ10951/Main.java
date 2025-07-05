package main.BJ10951;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringBuilder answer = new StringBuilder();

            while(true) {
                String input = br.readLine();

                if(input == null) break;

                String[] nums = input.split(" ");

                int num1 = Integer.parseInt(nums[0]);
                int num2 = Integer.parseInt(nums[1]);

                answer.append(num1 + num2).append("\n");
            }

            System.out.println(answer);
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}


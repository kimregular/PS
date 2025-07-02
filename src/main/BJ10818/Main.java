package main.BJ10818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            br.readLine(); // dump
            StringTokenizer st = new StringTokenizer(br.readLine());

            while(st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());

                min = Math.min(min, num);
                max = Math.max(max, num);
            }

            System.out.println(min + " " + max);
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

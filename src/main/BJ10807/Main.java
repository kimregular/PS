package main.BJ10807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static int[] field;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int len = Integer.parseInt(br.readLine());
        field = new int[len];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < len; i++) {
            field[i] = Integer.parseInt(st.nextToken());
        }

        int target = Integer.parseInt(br.readLine());
        
        int result = 0;
        for(int i = 0; i < len; i++) {
            if(field[i] == target) result++;
        }

        System.out.println(result);

        br.close();
    }
}

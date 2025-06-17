package main.BJ10871;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int len = Integer.parseInt(st.nextToken());
        int threshold = Integer.parseInt(st.nextToken());

        int[] field = new int[len];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < len; i++) {
            field[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder answer = new StringBuilder();

        for(int i = 0; i < len; i++) {
            if(field[i] < threshold) answer.append(field[i]).append(" ");
        }

        System.out.println(answer);

        br.close();
    }
}

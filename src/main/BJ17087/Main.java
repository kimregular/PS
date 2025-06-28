package main.BJ17087;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Input ip = readInput();
        System.out.println(new Solution().solution(ip.startLocation, ip.siblings));
    }

    private Input readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int numOfSiblings = Integer.parseInt(st.nextToken());
            int startLocation = Integer.parseInt(st.nextToken());

            int[] siblings = new int[numOfSiblings];

            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < numOfSiblings; i++) {
                siblings[i] = Integer.parseInt(st.nextToken());
            }

            return new Input(startLocation, siblings);

        }catch(IOException e) {
            throw new RuntimeException();
        }
    }

    private static class Input {

        final int startLocation;
        final int[] siblings;

        public Input(int startLocation, int[] siblings) {
            this.startLocation = startLocation;
            this.siblings = siblings;
        }
    }
}

class Solution {

    public int solution(int startLocation, int[] siblings) {
        for(int i = 0; i < siblings.length; i++) {
            siblings[i] = Math.abs(siblings[i] - startLocation);
        }

        if(siblings.length < 2) return siblings[0];

        int curGDC = siblings[0];

        for(int i = 1; i < siblings.length; i++) {
            curGDC = getGDC(curGDC, siblings[i]);
        }
        return curGDC;
    }

    private int getGDC(int a, int b) {
        if(b == 0) return a;
        return getGDC(b, a % b);
    }
}
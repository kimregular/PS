package main.BJ2563;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println(new Solution().solution(readInput()));
    }

    private int[][] readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int len = Integer.parseInt(br.readLine());

            int[][] papers = new int[len][2];

            for(int i = 0; i < len; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                papers[i][0] = Integer.parseInt(st.nextToken());
                papers[i][1] = Integer.parseInt(st.nextToken());
            }

            return papers;

        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private boolean[][] field;
    private int result;

    public int solution(int[][] papers) {
        init();
        calc(papers);
        return result;
    }

    private void init() {
        this.field = new boolean[101][101];
        this.result = 0;
    }

    private void calc(int[][] papers) {
        for(int[] paper : papers) {
            draw(paper);
        }
    }

    private void draw(int[] paper) {
        for(int i = paper[0]; i < paper[0] + 10; i++) {
            for(int j = paper[1]; j < paper[1] + 10; j++) {
                if(field[i][j]) continue;
                field[i][j] = true;
                result++;
            }
        }
    }
}
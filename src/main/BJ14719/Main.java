package main.BJ14719;

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
        System.out.println(new Solution().solution(ip.height, ip.width, ip.blocks));
    }

    public Input readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());

            int[] blocks = new int[width];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < width; i++) {
                blocks[i] = Integer.parseInt(st.nextToken());
            }

            return new Input(height, width, blocks);
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }

    private static class Input {

        final int height;
        final int width;
        final int[] blocks;

        public Input(int height, int width, int[] blocks) {
            this.height = height;
            this.width = width;
            this.blocks = blocks;
        }
    }
}

class Solution {

    private boolean[][] field;
    private int result;

    public int solution(int height, int width, int[] blocks) {
        init(height, width, blocks);
        calc();
        return result;
    }

    private void init(int height, int width, int[] blocks) {
        this.field = new boolean[height][width];

        for(int i = 0; i < field[0].length; i++) {
            int block = blocks[i];

            for(int j = field.length - 1; block > 0; j--) {
                field[j][i] = true;
                block--;
            }
        }
    }

    private void calc() {
        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[i].length; j++) {
                if(field[i][j]) continue;
                if(unClosed(i, j)) continue;
                result++;
            }
        }
    }

    private boolean unClosed(int x, int y) {
        boolean isRightClosed = false;
        boolean isLeftClosed = false;

        int ny;

        ny = y;
        while(ny >= 0) {
            if(field[x][ny]){
                isLeftClosed = true;
                break;
            }
            ny--;
        }

        ny = y;
        while(ny < field[0].length) {
            if(field[x][ny]) {
                isRightClosed = true;
                break;
            }
            ny++;
        }

        return !isLeftClosed || !isRightClosed;
    }
}
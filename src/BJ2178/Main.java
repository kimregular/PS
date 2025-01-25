package src.BJ2178;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            Solution s = new Solution();
            System.out.println(s.solution(readInput(br)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private char[][] readInput(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        char[][] result = new char[height][width];

        for (int i = 0; i < height; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < width; j++) {
                result[i][j] = temp[j];
            }
        }

        return result;
    }
}

class Solution {

    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private char[][] field;
    private boolean[][] isVisited;
    private int targetHeight;
    private int targetWidth;

    public int solution(char[][] field) {
        init(field);
        return explore();
    }

    private void init(char[][] field) {
        this.field = field;
        this.targetHeight = field.length - 1;
        this.targetWidth = field[0].length - 1;
        this.isVisited = new boolean[field.length][field[0].length];
    }

    private int explore() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 1});
        isVisited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == targetHeight && cur[1] == targetWidth) {
                return cur[2];
            }

            for (int[] direction : DIRECTIONS) {
                int nx = cur[0] + direction[0];
                int ny = cur[1] + direction[1];

                if(isOutOfBounds(nx, ny)) continue;
                if(isVisited[nx][ny]) continue;
                if(field[nx][ny] == '0') continue;

                isVisited[nx][ny] = true;
                q.offer(new int[]{nx, ny, cur[2] + 1});
            }
        }
        return -1;
    }

    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= field.length || y < 0 || y >= field[x].length;
    }
}
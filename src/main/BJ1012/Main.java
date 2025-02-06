package src.main.BJ1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {

        StringBuilder answer = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int testCases = Integer.parseInt(br.readLine());
            while (testCases-- > 0) {
                Input ip = readInput(br);
                Solution s = new Solution();
                answer.append(s.solution(ip.height, ip.width, ip.points)).append("\n");
            }
            System.out.println(answer);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Input readInput(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        int numOfPoints = Integer.parseInt(st.nextToken());

        int[][] points = new int[numOfPoints][2];
        for (int i = 0; i < numOfPoints; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i][1] = x;
            points[i][0] = y;
        }

        return new Input(height, width, points);
    }

    private static class Input {

        private final int height;
        private final int width;
        private final int[][] points;

        public Input(int height, int width, int[][] points) {
            this.height = height;
            this.width = width;
            this.points = points;
        }
    }
}

class Solution {

    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private int[][] field;
    private boolean[][] isVisited;

    public int solution(int height, int width, int[][] points) {
        init(height, width, points);
        return explore();
    }

    private void init(int height, int width, int[][] points) {
        this.field = new int[height][width];
        this.isVisited = new boolean[height][width];
        for (int[] point : points) {
            int x = point[0];
            int y = point[1];
            field[x][y] = 1;
        }
    }

    private int explore() {
        int result = 0;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if(field[i][j] == 0) continue;
                if(isVisited[i][j]) continue;

                result++;
                calc(i, j);
            }
        }

        return result;
    }

    private void calc(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        isVisited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int[] direction : DIRECTIONS) {
                int nx = cur[0] + direction[0];
                int ny = cur[1] + direction[1];

                if(isOutOfBounds(nx, ny)) continue;
                if(isVisited[nx][ny]) continue;
                if(field[nx][ny] == 0) continue;

                isVisited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
    }

    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= field.length || y < 0 || y >= field[0].length;
    }
}

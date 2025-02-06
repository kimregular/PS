package src.main.BJ7576;

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

    private int[][] readInput(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        int[][] result = new int[height][width];
        for (int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < width; j++) {
                result[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        return result;
    }
}

class Solution {

    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private int[][] field;
    private boolean[][] isVisited;

    private int result = 0;
    private boolean isEverythingGood = false;

    private Queue<int[]> q = new ArrayDeque<>();

    public int solution(int[][] field) {
        init(field);
        explore();
        return isEverythingGood ? result : -1;
    }

    private void init(int[][] field) {
        this.field = field;
        this.isVisited = new boolean[field.length][field[0].length];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 1) {
                    q.offer(new int[]{i, j});
                    isVisited[i][j] = true;
                }
            }
        }
    }

    private void explore() {
        int days = -1;

        while (!q.isEmpty()) {
            int len = q.size();

            for (int i = 0; i < len; i++) {
                int[] cur = q.poll();

                for (int[] direction : DIRECTIONS){
                    int nx = cur[0] + direction[0];
                    int ny = cur[1] + direction[1];

                    if(isOutOfBound(nx, ny)) continue;
                    if(isVisited[nx][ny]) continue;
                    if(field[nx][ny] == -1 || field[nx][ny] == 1) continue;

                    isVisited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
            days++;
        }
        result = days;
        check();
    }

    private boolean isOutOfBound(int x, int y) {
        return x < 0 || x >= field.length || y < 0 || y >= field[0].length;
    }

    private void check() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == 0 && !isVisited[i][j]) {
                    return;
                }
            }
        }
        isEverythingGood = true;
    }
}
package main.BJ18405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Input ip = readInput();
        System.out.println(new Solution().solution(ip.field, ip.targetInfos));
    }

    private Input readInput() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int typeLen = Integer.parseInt(st.nextToken());

            int[][] field = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    field[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            st = new StringTokenizer(br.readLine());

            int seconds = Integer.parseInt(st.nextToken());
            int targetX = Integer.parseInt(st.nextToken());
            int targetY = Integer.parseInt(st.nextToken());

            return new Input(field, new int[] { seconds, targetX, targetY });

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static class Input {

        final int[][] field;
        final int[] targetInfos;

        public Input(int[][] field, int[] targetInfos) {
            this.field = field;
            this.targetInfos = targetInfos;
        }
    }
}

class Solution {

    private static int[][] DIRECTIONS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    private int[][] field;
    private PriorityQueue<Virus> pq;

    public int solution(int[][] field, int[] targetInfos) {
        init(field);
        calc(targetInfos[0]);
        return field[targetInfos[1] - 1][targetInfos[2] - 1];
    }

    private void init(int[][] field) {
        this.field = field;
        this.pq = new PriorityQueue<>();

        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field.length; j++) {
                if(field[i][j] == 0) continue;
                pq.offer(new Virus(field[i][j], i, j));
            }
        }
    }

    private void calc(int seconds) {
        while(seconds-- > 0) {
            List<Virus> newViruses = new ArrayList<>();

            while(!pq.isEmpty()) {
                Virus v = pq.poll();

                for(int[] d : DIRECTIONS) {
                    int nx = v.x + d[0];
                    int ny = v.y + d[1];

                    if(out(nx, ny)) continue;
                    if(field[nx][ny] != 0) continue;

                    field[nx][ny] = v.type;
                    newViruses.add(new Virus(v.type, nx, ny));
                }
            }
            pq = new PriorityQueue<>(newViruses);
        }
    }

    private boolean out(int x, int y) {
        return field.length <= x || x < 0 || field.length <= y || y < 0;
    }

    private static class Virus implements Comparable<Virus> {

        final int type;
        final int x;
        final int y;

        public Virus(int type, int x, int y) {
            this.type=  type;
            this.x = x;
            this.y = y;
        }

        public int compareTo(Virus o) {
            return Integer.compare(type, o.type);
        }
    }
}
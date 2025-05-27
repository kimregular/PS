package main.BJ16173;

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
        System.out.println(new Solution().solution(readInput()));
    }

    private int[][] readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int len = Integer.parseInt(br.readLine());

            int[][] result = new int[len][len];

            for(int i = 0; i < len; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < len; j++) {
                    result[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            return result;

        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private int len;
    private int[][] field;
    private boolean[][] visited;

    public String solution(int[][] field) {
        init(field);
        bfs();
        return visited[len - 1][len - 1] ? "HaruHaru" : "Hing" ;
    }

    private void init(int[][] field) {
        this.len = field.length;
        this.field = field;
        this.visited = new boolean[len][len];
    }

    private void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        visited[0][0] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            int nextStep = field[x][y];

            int nx, ny;

            nx = x + nextStep;
            ny = y;
            if(!out(nx, ny) && !visited[nx][ny]) {
                q.offer(new int[] {nx, ny});
                visited[nx][ny] = true;
            }

            nx = x;
            ny = y + nextStep;
            if(!out(nx, ny) && !visited[nx][ny]) {
                q.offer(new int[] {nx, ny});
                visited[nx][ny] = true;
            }            
        }
    }

    private boolean out(int x, int y) {
        return field.length <= x || x < 0 || field.length <= y || y < 0;
    }
}
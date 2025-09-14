package main.BJ2589;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final int[][] ds = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static final char WATER = 'W';
    static final char LAND = 'L';
    
    static char[][] field;
    
    static int ans = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        field = new char[height][width];

        for(int i = 0; i < height; i++) {
            field[i] = br.readLine().toCharArray();
        }

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(field[i][j] == WATER) continue;
                ans = Math.max(ans, explore(i, j));
            }
        }

        System.out.println(ans);

        br.close();
    }

    static int explore(int x, int y) {
        boolean[][] checked = new boolean[field.length][field[0].length];
        int result = Integer.MIN_VALUE;
        
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y, 0}); // x, y, step
        checked[x][y] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            result = Math.max(result, cur[2]);

            for(int[] d : ds) {
                int nx = cur[0] + d[0];
                int ny = cur[1] + d[1];

                if(nx < 0 || nx >= field.length || ny < 0 || ny >= field[nx].length) continue;
                if(field[nx][ny] == WATER) continue;
                if(checked[nx][ny]) continue;
                
                q.offer(new int[] {nx, ny, cur[2] + 1});
                checked[nx][ny] = true;
            }
        }

        return result;
    }
}

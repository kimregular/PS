package main.BJ2917;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println(new Solution().solution(readInput()));
    }

    private char[][] readInput() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());

            char[][] field = new char[height][width];

            for(int i = 0; i < height; i++) {
                field[i] = br.readLine().toCharArray();
            }

            return field;

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private static final int INF = Integer.MAX_VALUE;
    private static final int[][] ds = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    private static final char TREE = '+';

    private char[][] field;
    private List<int[]> trees;

    private int[][] treeDist;
    private int[] startPosition;
    private int[] targetPosition;

    public int solution(char[][] field) {
        init(field);
        calcTreeDist();
        return explore();
    }

    private void init(char[][] field) {
        this.field = field;
        this.trees = new ArrayList<>();
        this.treeDist = new int[field.length][field[0].length];

        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[i].length; j++) {
                if(field[i][j] == TREE) {
                    trees.add(new int[] {i, j});
                } else if (field[i][j] == 'V') {
                    this.startPosition = new int[] {i, j};
                } else if (field[i][j] == 'J') {
                    this.targetPosition = new int[] {i, j};
                }
            }
        }

        for(int[] d : treeDist) {
            Arrays.fill(d, INF);
        }
    }

    private void calcTreeDist() {
        Queue<int[]> q = new ArrayDeque<>();
        for(int[] tree : trees) {
            q.offer(new int[] {tree[0], tree[1]}); // x, y
            treeDist[tree[0]][tree[1]] = 0;
        }

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int cost = treeDist[x][y];

            for(int[] d : ds) {
                int nx = x + d[0];
                int ny = y + d[1];

                if(out(nx, ny)) continue;
                if(treeDist[nx][ny] <= cost + 1) continue;

                q.offer(new int[] {nx, ny});
                treeDist[nx][ny] = cost + 1;
            }
        }
    }

    private int explore() {
        int result = 0;
        int lp = 0;
        int rp = field.length + field[0].length - 1;

        while(lp <= rp) {
            int mid = (lp + rp) / 2;

            if(bfs(mid)) {
                result = mid;
                lp = mid + 1;
            } else {
                rp = mid - 1;
            }
        }

        return result;
    }

    private boolean bfs(int threshold) {
        if(treeDist[startPosition[0]][startPosition[1]] < threshold) return false;
        if(treeDist[targetPosition[0]][targetPosition[1]] < threshold) return false;

        boolean[][] visited = new boolean[field.length][field[0].length];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {startPosition[0], startPosition[1]});
        visited[startPosition[0]][startPosition[1]] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            if(isTargetPosition(cur)) {
                return true;
            }

            for(int[] d : ds) {
                int nx = cur[0] + d[0];
                int ny = cur[1] + d[1];

                if(out(nx, ny)) continue;
                if(treeDist[nx][ny] < threshold) continue;
                if(visited[nx][ny]) continue;

                q.offer(new int[] {nx, ny});
                visited[nx][ny] = true;
            }
        }
        return false;
    }

    private boolean out(int x, int y) {
        return field.length <= x || x < 0 || field[x].length <= y || y < 0;
    }

    private boolean isTargetPosition(int[] position) {
        return position[0] == targetPosition[0] && position[1] == targetPosition[1];
    }
}
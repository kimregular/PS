package src.main.BJ6593;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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

    private List<char[][][]> readInput(BufferedReader br) throws IOException {
        List<char[][][]> result = new ArrayList<>();
        while (true) {
            String[] tokens = br.readLine().split(" ");
            int z = Integer.parseInt(tokens[0]);
            int x = Integer.parseInt(tokens[1]);
            int y = Integer.parseInt(tokens[2]);

            if(z == 0 && x == 0 && y == 0) break;

            char[][][] building = new char[z][x][y];

            for (int i = 0; i < z; i++) {
                for (int j = 0; j < x; j++) {
                    building[i][j] = br.readLine().toCharArray();
                }
                br.readLine();
            }

            result.add(building);
        }
        return result;
    }
}

class Solution {

    public String solution(List<char[][][]> buildings) {
        StringBuilder answer = new StringBuilder();
        for (char[][][] building : buildings) {
            Simulator s = new Simulator(building);
            answer.append(s.getResult()).append("\n");
        }
        return answer.toString();
    }
}

class Simulator {

    private static int[][] DIRECTIONS = {{0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}, {1, 0, 0}, {-1, 0, 0}};

    private char[][][] building;
    private boolean[][][] isVisited;
    private int[] startLocation;
    private int[] targetLocation;

    public Simulator(char[][][] building) {
        this.building = building;
        this.isVisited = new boolean[building.length][building[0].length][building[0][0].length];
        init();
    }

    private void init() {
        for (int i = 0; i < building.length; i++) {
            for (int j = 0; j < building[i].length; j++) {
                for (int k = 0; k < building[i][j].length; k++) {
                    if (startLocation != null && targetLocation != null) {
                        return;
                    }

                    if(building[i][j][k] == 'S') {
                        startLocation = new int[]{i, j, k};
                    } else if (building[i][j][k] == 'E') {
                        targetLocation = new int[]{i, j, k};
                    }
                }
            }
        }
    }

    public String getResult() {
        Queue<Location> q = new ArrayDeque<>();
        q.offer(new Location(startLocation));
        isVisited[startLocation[0]][startLocation[1]][startLocation[2]] = true;

        while (!q.isEmpty()) {
            Location cur = q.poll();

            if (isTargetAchieved(cur)) {
                return "Escaped in " + cur.step + " minute(s).";
            }

            for (int[] direction : DIRECTIONS) {
                int nz = cur.z + direction[0];
                int nx = cur.x + direction[1];
                int ny = cur.y + direction[2];

                if(isOutOfBounds(nz, nx, ny)) continue;
                if(building[nz][nx][ny] == '#') continue;
                if(isVisited[nz][nx][ny]) continue;

                q.offer(new Location(nz, nx, ny, cur.step + 1));
                isVisited[nz][nx][ny] = true;
            }
        }
        return "Trapped!";
    }

    private boolean isTargetAchieved(Location location) {
        return location.z == targetLocation[0] && location.x == targetLocation[1] && location.y == targetLocation[2];
    }

    private boolean isOutOfBounds(int z, int x, int y) {
        return z < 0 || z >= building.length || x < 0 || x >= building[z].length || y < 0 || y >= building[z][x].length;
    }

    private static class Location {
        int z;
        int x;
        int y;
        int step;

        public Location(int z, int x, int y, int step) {
            this.z = z;
            this.x = x;
            this.y = y;
            this.step = step;
        }

        public Location(int[] startLocation) {
            this.z = startLocation[0];
            this.x = startLocation[1];
            this.y = startLocation[2];
            this.step = 0;
        }
    }
}
package src.main.BJ16928;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            Input ip = readInput(br);
            Solution s = new Solution();
            System.out.println(s.solution(ip.ladders, ip.snakes));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Input readInput(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfLadders = Integer.parseInt(st.nextToken());
        int numOfSnakes = Integer.parseInt(st.nextToken());

        int[][] ladders = takeInputs(br, numOfLadders);
        int[][] snakes = takeInputs(br, numOfSnakes);

        return new Input(ladders, snakes);
    }

    private int[][] takeInputs(BufferedReader br, int len) throws IOException {
        StringTokenizer st;
        int[][] result = new int[len][2];
        for (int i = 0; i < len; i++) {
            st = new StringTokenizer(br.readLine());
            result[i][0] = Integer.parseInt(st.nextToken());
            result[i][1] = Integer.parseInt(st.nextToken());
        }
        return result;
    }

    private static class Input {
        private final int[][] ladders;
        private final int[][] snakes;

        public Input(int[][] ladders, int[][] snakes) {
            this.ladders = ladders;
            this.snakes = snakes;
        }
    }
}

class Solution {

    private Map<Integer, Integer> teleport;
    private boolean[] isVisited = new boolean[101];

    public int solution(int[][] ladders, int[][] snakes) {
        init(ladders, snakes);
        return play();
    }

    private void init(int[][] ladders, int[][] snakes) {
        teleport = new HashMap<>();
        for (int[] ladder : ladders) {
            teleport.put(ladder[0], ladder[1]);
        }
        for (int[] snake : snakes) {
            teleport.put(snake[0], snake[1]);
        }
    }

    private int play() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{1, 0});
        isVisited[1] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == 100) {
                return cur[1];
            }

            for (int i = 1; i <= 6; i++) {
                int next = cur[0] + i;

                if(next > 100) continue;
                if(isVisited[next]) continue;

                if (teleport.containsKey(next)) {
                    int nx = teleport.get(next);
                    q.offer(new int[]{nx, cur[1] + 1});
                    isVisited[nx] = true;
                } else {
                    q.offer(new int[]{next, cur[1] + 1});
                    isVisited[next] = true;
                }
            }
        }
        return -1;
    }
}

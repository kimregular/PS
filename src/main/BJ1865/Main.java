package main.BJ1865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        StringBuilder answer = new StringBuilder();
        Solution s = new Solution();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int TEST_CASES = Integer.parseInt(br.readLine());
            for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
                Input ip = readInput(br);
                answer.append(s.solution(ip.numOfNodes, ip.roadInfos, ip.wormholeInfos)).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }

        System.out.println(answer);
    }

    private Input readInput(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfNodes = Integer.parseInt(st.nextToken());
        int numOfRoadInfos = Integer.parseInt(st.nextToken());
        int numOfWormholeInfos = Integer.parseInt(st.nextToken());

        int[][] roadInfos = new int[numOfRoadInfos][3];
        int[][] wormholeInfos = new int[numOfWormholeInfos][3];

        for (int i = 0; i < numOfRoadInfos; i++) {
            st = new StringTokenizer(br.readLine());
            roadInfos[i][0] = Integer.parseInt(st.nextToken());
            roadInfos[i][1] = Integer.parseInt(st.nextToken());
            roadInfos[i][2] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < numOfWormholeInfos; i++) {
            st = new StringTokenizer(br.readLine());
            wormholeInfos[i][0] = Integer.parseInt(st.nextToken());
            wormholeInfos[i][1] = Integer.parseInt(st.nextToken());
            wormholeInfos[i][2] = Integer.parseInt(st.nextToken());
        }

        return new Input(numOfNodes, roadInfos, wormholeInfos);
    }

    private static class Input {

        final int numOfNodes;
        final int[][] roadInfos;
        final int[][] wormholeInfos;

        public Input(int numOfNodes, int[][] roadInfos, int[][] wormholeInfos) {
            this.numOfNodes = numOfNodes;
            this.roadInfos = roadInfos;
            this.wormholeInfos = wormholeInfos;
        }
    }
}

class Solution {

    private static final long INF = Long.MAX_VALUE;

    private int numOfNodes;
    private long[] dist;
    private List<Connection> edges;
    private boolean hasNegativeCycle;

    public String solution(int numOfNodes, int[][] roadInfos, int[][] wormholeInfos) {
        init(numOfNodes, roadInfos, wormholeInfos);
        calc();
        return hasNegativeCycle ? "YES" : "NO";
    }

    private void init(int numOfNodes, int[][] roadInfos, int[][] wormholeInfos) {
        this.numOfNodes = numOfNodes;
        this.edges = new ArrayList<>();
        this.hasNegativeCycle = false;

        for (int[] roadInfo : roadInfos) {
            edges.add(new Connection(roadInfo[0], roadInfo[1], roadInfo[2]));
            edges.add(new Connection(roadInfo[1], roadInfo[0], roadInfo[2]));
        }

        for (int[] wormholeInfo : wormholeInfos) {
            edges.add(new Connection(wormholeInfo[0], wormholeInfo[1], -wormholeInfo[2]));
        }

        for (int i = 1; i <= numOfNodes; i++) {
            edges.add(new Connection(0, i, 0));
        }
    }

    private void calc() {
        this.dist = new long[numOfNodes + 1];
        Arrays.fill(dist, INF);
        dist[0] = 0;

        for (int i = 1; i <= numOfNodes; i++) {
            for (Connection edge : edges) {
                if (dist[edge.from] != INF && dist[edge.from] + edge.cost < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.cost;
                }
            }
        }

        for (Connection edge : edges) {
            if (dist[edge.from] != INF && dist[edge.from] + edge.cost < dist[edge.to]) {
                hasNegativeCycle = true;
                return;
            }
        }
    }

    private static class Connection implements Comparable<Connection> {

        final int from;
        final int to;
        final long cost;

        public Connection(int from, int to, long cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Connection o) {
            return Long.compare(cost, o.cost);
        }
    }
}
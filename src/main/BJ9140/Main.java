package main.BJ9140;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        StringBuilder answer = new StringBuilder();
        Solution s = new Solution();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            while(true) {
                Input ip = readInput(br);
                if(ip.numOfNodes == 0 && ip.edges.length == 0 && ip.startNode == 0 && ip.targetNode == 0) break;
                answer.append(s.solution(ip.numOfNodes, ip.edges, ip.startNode, ip.targetNode)).append("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException();
        }

        System.out.println(answer);
    }

    private Input readInput(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfNodes = Integer.parseInt(st.nextToken());
        int numOfEdges = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken());
        int targetNode = Integer.parseInt(st.nextToken());

        int[][] edges = new int[numOfEdges][3];

        for (int i = 0; i < numOfEdges; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        return new Input(numOfNodes, edges, startNode, targetNode);
    }

    private static class Input {

        final int numOfNodes;
        final int[][] edges;
        final int startNode;
        final int targetNode;

        public Input(int numOfNodes, int[][] edges, int startNode, int targetNode) {
            this.numOfNodes = numOfNodes;
            this.edges = edges;
            this.startNode = startNode;
            this.targetNode = targetNode;
        }
    }
}

class Solution {

    private static final int INF = Integer.MAX_VALUE;

    private List<List<int[]>> graph;
    private int[] dist;

    public int solution(int numOfNodes, int[][] edges, int startNode, int targetNode) {
        init(numOfNodes, edges);
        calc(startNode);
        return getAnswer(targetNode);
    }

    private void init(int numOfNodes, int[][] edges) {
        this.graph = new ArrayList<>();
        this.dist = new int[numOfNodes + 1];

        for (int i = 0; i <= numOfNodes; i++)
            graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            graph.get(edge[0]).add(new int[] { edge[1], edge[2] });
        }

        Arrays.fill(dist, INF);
    }

    private void calc(int startNode) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[] { startNode, 0 });
        dist[startNode] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (dist[cur[0]] < cur[1])
                continue;

            for (int[] node : graph.get(cur[0])) {
                int next = node[0];
                int cost = node[1];
                int nextCost = cur[1] + cost;

                if (dist[next] <= nextCost)
                    continue;

                pq.offer(new int[] { next, nextCost });
                dist[next] = nextCost;
            }
        }
    }

    private int getAnswer(int targetNode) {
        return dist[targetNode];
    }
}
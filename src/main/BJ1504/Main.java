package main.BJ1504;

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
        Input ip = readInput();
        System.out.println(new Solution().solution(ip.numOfNodes, ip.edges, ip.v1, ip.v2));
    }

    private Input readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int numOfNodes = Integer.parseInt(st.nextToken());
            int numOfEdges = Integer.parseInt(st.nextToken());

            int[][] edges = new int[numOfEdges][3];

            for(int i = 0; i < numOfEdges; i++) {
                st = new StringTokenizer(br.readLine());
                edges[i][0] = Integer.parseInt(st.nextToken());
                edges[i][1] = Integer.parseInt(st.nextToken());
                edges[i][2] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            return new Input(numOfNodes, edges, v1, v2);

        }catch(IOException e) {
            throw new RuntimeException();
        }
    }

    private static class Input {

        final int numOfNodes;
        final int[][] edges;
        final int v1;
        final int v2;

        public Input(int numOfNodes, int[][] edges, int v1, int v2) {
            this.numOfNodes = numOfNodes;
            this.edges = edges;
            this.v1 = v1;
            this.v2 = v2;
        }
    }
}

class Solution {

    private static final int INF = Integer.MAX_VALUE;

    private List<List<int[]>> graph;
    private int[] distFrom1;
    private int[] distFromV1;
    private int[] distFromV2;

    private int v1;
    private int v2;


    public long solution(int numOfNodes, int[][] edges, int v1, int v2) {
        init(numOfNodes, edges, v1, v2);
        calc();
        return getAnswer();
    }

    private void init(int numOfNodes, int[][] edges, int v1, int v2) {
        this.graph = new ArrayList<>();
        this.distFrom1 = new int[numOfNodes + 1];
        this.distFromV1 = new int[numOfNodes + 1];
        this.distFromV2 = new int[numOfNodes + 1];

        this.v1 = v1;
        this.v2 = v2;

        for(int i = 0; i <= numOfNodes; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] edge : edges) {
            graph.get(edge[0]).add(new int[] {edge[1], edge[2]});
            graph.get(edge[1]).add(new int[] {edge[0], edge[2]});
        }

        Arrays.fill(distFrom1, INF);
        Arrays.fill(distFromV1, INF);
        Arrays.fill(distFromV2, INF);
    }

    private void calc() {
        calc(graph, distFrom1, 1);
        calc(graph, distFromV1, v1);
        calc(graph, distFromV2, v2);
    }

    private void calc(List<List<int[]>> graph, int[] dist, int startNode) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[] {startNode, 0});
        dist[startNode] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(cur[1] > dist[cur[0]]) continue;

            for(int[] node : graph.get(cur[0])) {
                int next = node[0];
                int weight = node[1];
                int nextCost = cur[1] + weight;

                if(nextCost > dist[next]) continue;
                dist[next] = nextCost;
                pq.offer(new int[] {next, nextCost});
            }
        }
    }

    private long getAnswer() {
        // 1 -> v1 -> v2 -> n
        long candidate1 = (long) distFrom1[v1] + distFromV1[v2] + distFromV2[graph.size() - 1];
        // 1 -> v2 -> v1 -> n
        long candidate2 = (long) distFrom1[v2] + distFromV2[v1] + distFromV1[graph.size() - 1];

        long result = Math.min(candidate1, candidate2);

        return (candidate1 >= INF || candidate2 >= INF) ? -1 : result;
    }
}
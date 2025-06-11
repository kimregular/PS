package main.BJ5996;

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
        System.out.println(new Solution().solution(ip.numOfNodes, ip.edges, ip.startNode, ip.targetNode));
    }

    private Input readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int numOfNodes = Integer.parseInt(st.nextToken());
            int numOfEdges = Integer.parseInt(st.nextToken());
            int startNode = Integer.parseInt(st.nextToken());
            int targetNode = Integer.parseInt(st.nextToken());


            int[][] edges = new int[numOfEdges][3];

            for(int i = 0; i < numOfEdges; i++) {
                st = new StringTokenizer(br.readLine());
                edges[i][0] = Integer.parseInt(st.nextToken());
                edges[i][1] = Integer.parseInt(st.nextToken());
                edges[i][2] = Integer.parseInt(st.nextToken());
            }

            return new Input(numOfNodes, edges, startNode, targetNode);

        }catch(IOException e) {
            throw new RuntimeException();
        }
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

        for(int i = 0; i <= numOfNodes; i++) graph.add(new ArrayList<>());

        for(int[] edge : edges) {
            graph.get(edge[0]).add(new int[] {edge[1], edge[2]});
            graph.get(edge[1]).add(new int[] {edge[0], edge[2]});
        }

        Arrays.fill(dist, INF);
    }

    private void calc(int startNode) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[] {startNode, 0});
        dist[startNode] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(dist[cur[0]] < cur[1]) continue;

            for(int[] node : graph.get(cur[0])) {
                int next = node[0];
                int weight = node[1];

                int nextWeight = weight + cur[1];

                if(dist[next] <= nextWeight) continue;
                pq.offer(new int[] {next, nextWeight});
                dist[next] = nextWeight;
            }
        }
    }

    private int getAnswer(int targetNode) {
        return dist[targetNode];
    }
}
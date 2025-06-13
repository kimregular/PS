package main.BJ17396;

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
        System.out.println(new Solution().solution(ip.numOfNodes, ip.edges, ip.watch));
    }

    private Input readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int numOfNodes = Integer.parseInt(st.nextToken());
            int numOfEdges = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            boolean[] watch = new boolean[numOfNodes];
            for(int i = 0; i < numOfNodes; i++) {
                String t = st.nextToken();
                if(t.equals("0")) continue;
                watch[i] = true;
            }

            int[][] edges = new int[numOfEdges][3];

            for(int i = 0; i < numOfEdges; i++) {
                st = new StringTokenizer(br.readLine());
                edges[i][0] = Integer.parseInt(st.nextToken());
                edges[i][1] = Integer.parseInt(st.nextToken());
                edges[i][2] = Integer.parseInt(st.nextToken());
            }

            return new Input(numOfNodes, edges, watch);
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }

    private static class Input {

        final int numOfNodes;
        final int[][] edges;
        final boolean[] watch;

        public Input(int numOfNodes, int[][] edges, boolean[] watch) {
            this.numOfNodes = numOfNodes;
            this.edges = edges;
            this.watch = watch;
        }
    }
}

class Solution {

    private static final long INF = Long.MAX_VALUE;

    private List<List<int[]>> graph;
    private long[] dist;
    private boolean[] watch;

    public long solution(int numOfNodes, int[][] edges, boolean[] watch) {
        init(numOfNodes, edges, watch);
        calc();
        return getAnswer();
    }

    private void init(int numOfNodes, int[][] edges, boolean[] watch) {
        this.graph = new ArrayList<>();
        this.dist = new long[numOfNodes];
        this.watch = watch;

        for(int i = 0; i < numOfNodes; i++) graph.add(new ArrayList<>());

        for(int[] edge : edges) {
            graph.get(edge[0]).add(new int[] {edge[1], edge[2]});
            graph.get(edge[1]).add(new int[] {edge[0], edge[2]});
        }

        Arrays.fill(dist, INF);
    }

    private void calc() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));
        dist[0] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(dist[cur.node] < cur.cost) continue;

            for(int[] node : graph.get(cur.node)) {
                int next = node[0];
                int weight = node[1];
                long nextWeight = cur.cost + weight;

                if(unreachable(next)) continue;
                if(dist[next] <= nextWeight) continue;
                dist[next] = nextWeight;
                pq.offer(new Node(next, nextWeight));
            }
        }
    }

    private boolean unreachable(int n) {
        return n != dist.length - 1 &&  watch[n];
    }

    private long getAnswer() {
        if(dist[dist.length - 1] == INF) return -1;
        return dist[dist.length - 1];
    }

    private static class Node implements Comparable<Node> {

        final int node;
        final long cost;

        public Node(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(cost, o.cost);
        }
    }
}
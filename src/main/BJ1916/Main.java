package main.BJ1916;

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
        System.out.println(new Solution().solution(ip.cities, ip.edges, ip.targets));
    }

    private Input readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int cities = Integer.parseInt(br.readLine());
            int lenOfEdges = Integer.parseInt(br.readLine());
            int[][] edges = new int[lenOfEdges][3];

            for(int i = 0; i < lenOfEdges; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edges[i][0] = from;
                edges[i][1] = to;
                edges[i][2] = cost;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] targets = new int[2];
            targets[0] = Integer.parseInt(st.nextToken());
            targets[1] = Integer.parseInt(st.nextToken());

            return new Input(cities, edges, targets);

        }catch(IOException e) {
            throw new RuntimeException();
        }
    }

    private static class Input {

        final int cities;
        final int[][] edges;
        final int[] targets;

        public Input(int cities, int[][] edges, int[] targets) {
            this.cities = cities;
            this.edges = edges;
            this.targets = targets;
        }
    }
}


class Solution {

    private static final long MAX = 1_000_000_000;

    private long[] dist;
    private boolean[] visited;
    private List<List<Node>> graph;
    
    public long solution(int cities, int[][] edges, int[] targets) {
        init(cities, edges);
        calc(targets[0]);
        return dist[targets[1]];
    }

    private void init(int cities, int[][] edges) {
        this.dist = new long[cities + 1];
        this.visited = new boolean[cities + 1];
        this.graph = new ArrayList<>();

        Arrays.fill(dist, MAX);

        for(int i = 0; i <= cities; i++) graph.add(new ArrayList<>());

        for(int[] edge : edges) {
            graph.get(edge[0]).add(new Node(edge[1], edge[2]));
        }
    }

    private void calc(int start) {
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(visited[cur.to]) continue;
            visited[cur.to] = true;

            for(Node nextNode : graph.get(cur.to)) {
                if(dist[nextNode.to] > cur.cost + nextNode.cost) {
                    dist[nextNode.to] = cur.cost + nextNode.cost;
                    pq.offer(new Node(nextNode.to, cur.cost + nextNode.cost));
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {

        final int to;
        final int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(cost, o.cost);
        }
    }
}
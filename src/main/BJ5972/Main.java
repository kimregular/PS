package main.BJ5972;

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
        System.out.println(new Solution().solution(ip.numOfNodes, ip.edges));
    }

    private Input readInput() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int numOfNodes = Integer.parseInt(st.nextToken());
            int numOfEdges = Integer.parseInt(st.nextToken());

            int[][] edges = new int[numOfEdges][3];

            for (int i = 0; i < numOfEdges; i++) {
                st = new StringTokenizer(br.readLine());
                edges[i][0] = Integer.parseInt(st.nextToken());
                edges[i][1] = Integer.parseInt(st.nextToken());
                edges[i][2] = Integer.parseInt(st.nextToken());
            }

            return new Input(numOfNodes, edges);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static class Input {

        final int numOfNodes;
        final int[][] edges;

        public Input(int numOfNodes, int[][] edges) {
            this.numOfNodes = numOfNodes;
            this.edges = edges;
        }
    }
}

class Solution {

    private static final int INF = 600_000_000;

    private int[] dist;
    private boolean[] visited;
    private List<List<Node>> graph;

    public int solution(int numOfNodes, int[][] edges) {
        init(numOfNodes, edges);
        calc();
        return dist[numOfNodes];
    }

    private void init(int numOfNodes, int[][] edges) {
        this.dist = new int[numOfNodes + 1];
        this.visited = new boolean[numOfNodes + 1];
        this.graph = new ArrayList<>();

        Arrays.fill(dist, INF);

        for (int i = 0; i <= numOfNodes; i++)
            graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            graph.get(edge[0]).add(new Node(edge[1], edge[2]));
            graph.get(edge[1]).add(new Node(edge[0], edge[2]));
        }
    }

    private void calc() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        dist[1] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.to])
                continue;
            visited[cur.to] = true;

            for (Node next : graph.get(cur.to)) {
                if (dist[next.to] < cur.cost + next.cost) continue;
                dist[next.to] = cur.cost + next.cost;
                pq.offer(new Node(next.to, cur.cost + next.cost));
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
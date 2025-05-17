package main.BJ14284;

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
        System.out.println(new Solution().solution(ip.numOfNodes, ip.edges, ip.targetInfos));
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
            int[] targetInfos = new int[2];
            targetInfos[0] = Integer.parseInt(st.nextToken());
            targetInfos[1] = Integer.parseInt(st.nextToken());

            return new Input(numOfNodes, edges, targetInfos);
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }

    private static class Input {

        final int numOfNodes;
        final int[][] edges;
        final int[] targetInfos;

        public Input(int numOfNodes, int[][] edges, int[] targetInfos) {
            this.numOfNodes = numOfNodes;
            this.edges = edges;
            this.targetInfos = targetInfos;
        }
    }
}

class Solution {

    private static final int INF = Integer.MAX_VALUE;

    private List<List<Node>> graph;
    private boolean[] visited;
    private int[] dist;
    private int[] targetInfos;

    public int solution(int numOfNodes, int[][] edges, int[] targetInfos) {
        init(numOfNodes, edges, targetInfos);
        calc();
        return dist[targetInfos[1]];
    }

    private void init(int numOfNodes, int[][] edges, int[] targetInfos) {
        this.graph = new ArrayList<>();
        this.visited = new boolean[numOfNodes + 1];
        this.dist = new int[numOfNodes + 1];
        this.targetInfos = targetInfos;

        for(int i = 0; i <= numOfNodes; i++) graph.add(new ArrayList<>());

        for(int[] edge : edges) {
            graph.get(edge[0]).add(new Node(edge[1], edge[2]));
            graph.get(edge[1]).add(new Node(edge[0], edge[2]));
        }

        Arrays.fill(dist, INF);

    }

    private void calc() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        int start = targetInfos[0];

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            
            Node now = pq.poll();

            if(visited[now.to]) continue;
            visited[now.to] = true;
            
            for(Node nextNode : graph.get(now.to)) {
                if(dist[nextNode.to] < now.cost + nextNode.cost) continue;
                dist[nextNode.to] = now.cost + nextNode.cost;
                pq.offer(new Node(nextNode.to, now.cost + nextNode.cost));
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
package main.BJ13424;

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

            int TEST_CASES = Integer.parseInt(br.readLine());
            for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
                Input ip = readInput(br);
                answer.append(s.solution(ip.numOfNodes, ip.edges, ip.friends)).append("\n");
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

        int[][] edges = new int[numOfEdges][3];

        for (int i = 0; i < numOfEdges; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        int numOfFriends = Integer.parseInt(br.readLine());
        int[] friends = new int[numOfFriends];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < numOfFriends; i++) {
            friends[i] = Integer.parseInt(st.nextToken());
        }

        return new Input(numOfNodes, edges, friends);

    }

    private static class Input {

        final int numOfNodes;
        final int[][] edges;
        final int[] friends;

        public Input(int numOfNodes, int[][] edges, int[] friends) {
            this.numOfNodes = numOfNodes;
            this.edges = edges;
            this.friends = friends;
        }
    }
}

class Solution {

    private static final int INF = Integer.MAX_VALUE;

    private List<List<Node>> graph;
    private int[] friends;
    private int prevCost;
    private int result;

    public int solution(int numOfNodes, int[][] edges, int[] friends) {
        init(numOfNodes, edges, friends);
        calc();
        return result;
    }

    private void init(int numOfNodes, int[][] edges, int[] friends) {
        this.graph = new ArrayList<>();
        this.friends = friends;
        this.prevCost = INF;
        this.result = INF;

        for (int i = 0; i <= numOfNodes; i++)
            graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            graph.get(edge[0]).add(new Node(edge[1], edge[2]));
            graph.get(edge[1]).add(new Node(edge[0], edge[2]));
        }
    }

    private void calc() {
        for(int i = 1; i < graph.size(); i++) {
            int[] dist = initDist();
            calc(i, dist);
            updateResult(i, dist);
        }
    }

    private int[] initDist() {
        int[] dist = new int[graph.size()];
        Arrays.fill(dist, INF);
        return dist;
    }

    private void calc(int startNode, int[] dist) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(startNode, 0));
        dist[startNode] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.vex] < cur.cost)
                continue;

            for (Node node : graph.get(cur.vex)) {
                int next = node.vex;
                int cost = node.cost;
                int nextCost = cur.cost + cost;

                if (dist[next] <= nextCost)
                    continue;
                dist[next] = nextCost;
                pq.offer(new Node(next, nextCost));
            }
        }
    }

    private void updateResult(int node, int[] dist) {
        int temp = 0;

        for(int i : friends) {
            temp += dist[i];
        }

        if(prevCost <= temp) return;

        prevCost = temp;
        result = node;
    }

    private static class Node implements Comparable<Node> {

        final int vex;
        final int cost;

        public Node(int vex, int cost) {
            this.vex = vex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(cost, o.cost);
        }
    }
}
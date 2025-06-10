package main.BJ14938;

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
        System.out.println(new Solution().solution(ip.numOfNodes, ip.edges, ip.range, ip.itemsAtNodeOf));
    }

    private Input readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int numOfNodes = Integer.parseInt(st.nextToken());
            int range = Integer.parseInt(st.nextToken());
            int numOfEdges = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] itemsAtNodeOf = new int[numOfNodes + 1];

            for(int i = 1; i < itemsAtNodeOf.length; i++) {
                itemsAtNodeOf[i] = Integer.parseInt(st.nextToken());
            }

            int[][] edges = new int[numOfEdges][3];

            for(int i = 0; i < numOfEdges; i++) {
                st = new StringTokenizer(br.readLine());
                edges[i][0] = Integer.parseInt(st.nextToken());
                edges[i][1] = Integer.parseInt(st.nextToken());
                edges[i][2] = Integer.parseInt(st.nextToken());
            }

            return new Input(numOfNodes, edges, range, itemsAtNodeOf);
        } catch(IOException e) {
            throw new RuntimeException();
        }
    }

    private static class Input {

        final int numOfNodes;
        final int[][] edges;
        final int range;
        final int[] itemsAtNodeOf;

        public Input(int numOfNodes, int[][] edges, int range, int[] itemsAtNodeOf) {
            this.numOfNodes = numOfNodes;
            this.edges = edges;
            this.range = range;
            this.itemsAtNodeOf = itemsAtNodeOf;
        }
    }
}

class Solution {

    private static final int INF = Integer.MAX_VALUE;

    private int numOfNodes;
    private List<List<int[]>> graph;
    private int[] itemsAtNodeOf;
    private int range;
    private int result;

    public int solution(int numOfNodes, int[][] edges, int range, int[] itemsAtNodeOf) {
        init(numOfNodes, edges, range, itemsAtNodeOf);
        calc();
        return result;
    }

    private void init(int numOfNodes, int[][] edges, int range, int[] itemsAtNodeOf) {
        this.numOfNodes = numOfNodes;
        this.graph = new ArrayList<>();
        this.itemsAtNodeOf = itemsAtNodeOf;
        this.range = range;
        this.result = 0;

        for(int i = 0; i <= numOfNodes; i++) graph.add(new ArrayList<>());

        for(int[] edge : edges) {
            graph.get(edge[0]).add(new int[] {edge[1], edge[2]});
            graph.get(edge[1]).add(new int[] {edge[0], edge[2]});
        }
    }

    private void calc() {
        for(int i = 1; i < numOfNodes; i++) {
            int[] dist = new int[numOfNodes + 1];
            Arrays.fill(dist, INF);
            calc(i, dist);
            check(dist);
        }
    }

    private void calc(int startNode, int[] dist) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[] {startNode, 0});
        dist[startNode] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(dist[cur[0]] < cur[1]) continue;

            for(int[] node : graph.get(cur[0])) {
                int next = node[0];
                int weight = node[1];
                int nextWeight = cur[1] + weight;

                if(dist[next] <= nextWeight) continue;
                pq.offer(new int[] {next, nextWeight});
                dist[next] = nextWeight;
            }
        }
    }

    private void check(int[] dist) {
        int temp = 0;
        for(int i = 1; i < itemsAtNodeOf.length; i++) {
            if(range < dist[i]) continue;
            temp += itemsAtNodeOf[i];
        }

        result = Math.max(result, temp);
    }
}
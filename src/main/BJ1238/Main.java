package main.BJ1238;

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
        System.out.println(new Solution().solution(ip.numOfNodes, ip.startNode, ip.edges));
    }

    private Input readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int numOfNodes = Integer.parseInt(st.nextToken());
            int numOfEdges = Integer.parseInt(st.nextToken());
            int startNode = Integer.parseInt(st.nextToken());

            int[][] edges = new int[numOfEdges][3];

            for(int i = 0; i < numOfEdges; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                edges[i][0] = from;
                edges[i][1] = to;
                edges[i][2] = cost;
            }

            return new Input(numOfNodes, startNode, edges);
            
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }

    private static class Input {

        final int numOfNodes;
        final int startNode;
        final int[][] edges;

        public Input(int numOfNodes, int startNode, int[][] edges) {
            this.numOfNodes = numOfNodes;
            this.startNode = startNode;
            this.edges = edges;
        }
    }
}


class Solution {

    private static final int max = Integer.MAX_VALUE;

    private List<List<int[]>> goParty;
    private List<List<int[]>> backParty;

    private int[] goPartyCost;
    private int[] backPartyCost;

    public int solution(int numOfNodes, int startNode, int[][] edges) {
        init(numOfNodes, edges);
        calc(startNode);
        return getAnswer();
    }

    private void init(int numOfNodes, int[][] edges) {
        this.goParty = new ArrayList<>();
        this.backParty = new ArrayList<>();
        this.goPartyCost = new int[numOfNodes + 1];
        this.backPartyCost = new int[numOfNodes + 1];

        for(int i = 0; i <= numOfNodes; i++) {
            goParty.add(new ArrayList<>());
            backParty.add(new ArrayList<>());
        }

        for(int[] edge : edges) {
            goParty.get(edge[0]).add(new int[] {edge[1], edge[2]});
            backParty.get(edge[1]).add(new int[] {edge[0], edge[2]});
        }

        Arrays.fill(goPartyCost, max);
        Arrays.fill(backPartyCost, max);
    }

    private void calc(int startNode) {
        calc(goParty, goPartyCost, startNode);
        calc(backParty, backPartyCost, startNode);
    }

    private void calc(List<List<int[]>> graph, int[] dist, int startNode) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[] {startNode, 0});
        dist[startNode] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(cur[1] > dist[cur[0]]) continue;

            for(int[] nextNode : graph.get(cur[0])) {
                if(dist[nextNode[0]] < nextNode[1] + dist[cur[0]]) continue;
                dist[nextNode[0]] = nextNode[1] + dist[cur[0]];
                pq.offer(new int[] {nextNode[0], nextNode[1] + dist[cur[0]]});
            }
        }
    }

    private int getAnswer() {
        int result = -1;

        for(int i = 1; i < goPartyCost.length; i++) {
            result = Math.max(result, goPartyCost[i] + backPartyCost[i]);
        }

        return result;
    }
}
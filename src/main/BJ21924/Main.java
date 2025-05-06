package main.BJ21924;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Input ip = readInput();
        System.out.println(new Solution().solution(ip.numOfNodes, ip.edges, ip.costSum));
    }

    private Input readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int numOfBuildings = Integer.parseInt(st.nextToken());
            int numOfEdges = Integer.parseInt(st.nextToken());

            int[][] edges = new int[numOfEdges][3];
            long costSum = 0;

            for(int i = 0; i < numOfEdges; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                edges[i][0] = from;
                edges[i][1] = to;
                edges[i][2] = cost;

                costSum += cost;
            }

            return new Input(numOfBuildings, edges, costSum);
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }

    private static class Input {

        final int numOfNodes;
        final int[][] edges;
        final long costSum;

        public Input(int numOfNodes, int[][] edges, long costSum) {
            this.numOfNodes = numOfNodes;
            this.edges = edges;
            this.costSum = costSum;
        }
    }
}

class Solution {

    private List<List<int[]>> graph;
    private boolean[] connected;
    private long result;
    private PriorityQueue<int[]> pq;

    public long solution(int numOfNodes, int[][] edges, long costSum) {
        init(numOfNodes, edges);
        calc();
        return isAllConnected() ? costSum - result : -1;
    }

    private void init(int numOfNodes, int[][] edges) {
        this.graph = new ArrayList<>();
        this.connected = new boolean[numOfNodes + 1];
        this.result = 0;
        this.pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        for(int i = 0; i <= numOfNodes; i++) graph.add(new ArrayList<>());

        for(int[] edge : edges){
            int from = edge[0];
            int to = edge[1];
            int cost = edge[2];

            graph.get(from).add(new int[] {to, cost});
            graph.get(to).add(new int[] {from, cost});
        }
    }

    private void calc() {
        pq.offer(new int[] {1, 0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            if(connected[cur[0]]) continue;

            connected[cur[0]] = true;
            result += cur[1];

            for(int[] nextNodes : graph.get(cur[0])) {
                if(connected[nextNodes[0]]) continue;;
                pq.offer(nextNodes);
            }
        }
    }

    private boolean isAllConnected() {
        for(int i = 1; i < connected.length; i++) {
            if(!connected[i]) return false;
        }
        return true;
    }
}
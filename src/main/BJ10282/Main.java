package main.BJ10282;

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

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int TEST_CASES = Integer.parseInt(br.readLine());
            for(int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
                Input ip = readInput(br);
                answer.append(s.solution(ip.numOfNodes, ip.edges, ip.startNode)).append("\n");
            }
        }catch(IOException e) {
            throw new RuntimeException();
        }

        System.out.println(answer);
    }

    private Input readInput(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfNodes = Integer.parseInt(st.nextToken());
        int numOfEdges = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken());

        int[][] edges = new int[numOfEdges][3];

        for(int i = 0; i < numOfEdges; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        return new Input(numOfNodes, edges, startNode);
    }

    private static class Input {

        final int numOfNodes;
        final int[][] edges;
        final int startNode;

        public Input(int numOfNodes, int[][] edges, int startNode) {
            this.numOfNodes = numOfNodes;
            this.edges = edges;
            this.startNode = startNode;
        }
    }
}

class Solution {

    private static final int INF = Integer.MAX_VALUE;

    private List<List<int[]>> graph;
    private int[] dist;


    public String solution(int numOfNodes, int[][] edges, int startNode) {
        init(numOfNodes, edges);
        calc(startNode);
        return getAnswer();
    }

    private void init(int numOfNodes, int[][] edges) {
        this.graph = new ArrayList<>();
        this.dist = new int[numOfNodes + 1];

        for(int i = 0; i <= numOfNodes; i++) graph.add(new ArrayList<>());

        for(int[] edge : edges) {
            graph.get(edge[1]).add(new int[] {edge[0], edge[2]});
        }

        Arrays.fill(dist, INF);
    }

    private void calc(int startNode) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[] {startNode, 0}); // node, weight
        dist[startNode] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            int curNode = cur[0];
            int curWeight = cur[1];

            if(dist[curNode] < curWeight) continue;

            for(int[] node : graph.get(cur[0])) {
                int nextNode = node[0];
                int weight = node[1];

                int nextWeight = dist[curNode] + weight;

                if(dist[nextNode] <= nextWeight) continue;
                dist[nextNode] = nextWeight;
                pq.offer(new int[] {nextNode, nextWeight});
            }
        }
    }

    private String getAnswer() {
        int numOfInfected = 0;
        int required = -1;

        for(int i = 1; i < dist.length; i++) {
            if(dist[i] == INF) continue;
            required = Math.max(required, dist[i]);
            numOfInfected++;
        }
        return numOfInfected + " " + required;
    }
}

package main.BJ6497;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

            while(true) {
                int costSum = 0;
                StringTokenizer st = new StringTokenizer(br.readLine());
                int numOfHouses = Integer.parseInt(st.nextToken());
                int numOfEdges = Integer.parseInt(st.nextToken());

                if(numOfHouses == 0 && numOfEdges == 0) {
                    break;
                }

                int[][] edges = new int[numOfEdges][3];

                for(int i = 0; i < numOfEdges; i++) {
                    st = new StringTokenizer(br.readLine());
                    edges[i][0] = Integer.parseInt(st.nextToken()); // from
                    edges[i][1] = Integer.parseInt(st.nextToken()); // to
                    int cost = Integer.parseInt(st.nextToken());
                    costSum += cost;
                    edges[i][2] = cost; // cost
                }

                answer.append(s.solution(numOfHouses, edges, costSum)).append("\n");
            }
        }catch(IOException e) {
            throw new RuntimeException();
        }

        System.out.println(answer);
    }
}

class Solution {

    private int[] groupOf;
    private int cnt;
    private PriorityQueue<int[]> pq;
    private int result;

    public int solution(int numOfHouses, int[][] edges, int costSum) {
        init(numOfHouses, edges);
        calc();
        return costSum - result;
    }

    private void init(int numOfHouses, int[][] edges) {
        this.groupOf = new int[numOfHouses];
        this.cnt = numOfHouses;
        this.pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        this.result = 0;
        for(int i = 0; i < groupOf.length; i++) groupOf[i] = i;
        for(int[] edge : edges) pq.offer(edge);
    }

    private void calc() {

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();

            int from = cur[0];
            int to = cur[1];

            if(connected(from, to)) continue;

            connect(from, to);
            result += cur[2];
            cnt--;

            if(cnt == 0) break;
        }
    }

    private boolean connected(int node1, int node2) {
        return getGroupOf(node1) == getGroupOf(node2);
    }

    private int getGroupOf(int node) {
        if(node != groupOf[node]) return groupOf[node] = getGroupOf(groupOf[node]);
        return node;
    }

    private void connect(int node1, int node2) {
        int group1 = getGroupOf(node1);
        int group2 = getGroupOf(node2);

        groupOf[Math.max(group1, group2)] = Math.min(group1, group2);
    }
}
package main.BJ7511;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
                answer.append("Scenario ")
                .append(TEST_CASE + 1)
                .append(":")
                .append("\n")
                .append(s.solution(ip.numOfPeople, ip.edges, ip.targetCases))
                .append("\n");
            }

        }catch(IOException e) {
            throw new RuntimeException();
        }

        System.out.println(answer);
    }

    private Input readInput(BufferedReader br) throws IOException {
        int numOfPeople = Integer.parseInt(br.readLine());
        int numOfEdges = Integer.parseInt(br.readLine());

        int[][] edges = new int[numOfEdges][2];

        for(int i = 0; i < numOfEdges; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
        }

        int numOfTargetCases = Integer.parseInt(br.readLine());

        int[][] targetCases = new int[numOfTargetCases][2];

        for(int i = 0; i < numOfTargetCases; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            targetCases[i][0] = Integer.parseInt(st.nextToken());
            targetCases[i][1] = Integer.parseInt(st.nextToken());
        }

        return new Input(numOfPeople, edges, targetCases);
    }

    private static class Input {

        final int numOfPeople;
        final int[][] edges;
        final int[][] targetCases;

        public Input(int numOfPeople, int[][] edges, int[][] targetCases) {
            this.numOfPeople = numOfPeople;
            this.edges = edges;
            this.targetCases = targetCases;
        }
    }
}

class Solution {

    private int[] groupOf;
    private StringBuilder result;

    public String solution(int numOfPeople, int[][] edges, int[][] targetCases) {
        init(numOfPeople);
        postInit(edges);
        calc(targetCases);
        return result.toString();
    }

    private void init(int numOfPeople) {
        this.groupOf = new int[numOfPeople];
        this.result = new StringBuilder();

        for(int i = 0; i < groupOf.length; i++) groupOf[i] = i;
    }

    private void postInit(int[][] edges) {
        for(int[] edge : edges) {

            int from = edge[0];
            int to = edge[1];

            if(connected(from, to)) continue;

            connect(from, to);
        }
    }

    private boolean connected(int node1, int node2) {
        return getGroupOf(node1) == getGroupOf(node2);
    }

    private int getGroupOf(int node) {
        if(node != groupOf[node]) return groupOf[node] = getGroupOf(groupOf[node]);
        return groupOf[node];
    }

    private void connect(int node1, int node2) {
        int group1 = getGroupOf(node1);
        int group2 = getGroupOf(node2);

        groupOf[Math.max(group1, group2)] = Math.min(group1, group2);
    }

    private void calc(int[][] targetCases) {
        for(int[] targetCase : targetCases) {
            int from = targetCase[0];
            int to = targetCase[1];

            result.append(connected(from, to) ? 1 : 0).append("\n");
        }
    }
}
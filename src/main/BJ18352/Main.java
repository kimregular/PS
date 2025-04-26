package main.BJ18352;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Input ip = readInput();
        System.out.println(new Solution().solution(ip.cities, ip.edges, ip.targetDistance, ip.start));
    }

    private Input readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int cities = Integer.parseInt(st.nextToken());
            int numOfEdges = Integer.parseInt(st.nextToken());
            int targetDistance = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            int[][] edges = new int[numOfEdges][2];
            for (int i = 0; i < numOfEdges; i++) {
                st = new StringTokenizer(br.readLine());
                edges[i][0] = Integer.parseInt(st.nextToken());
                edges[i][1] = Integer.parseInt(st.nextToken());
            }

            return new Input(cities, edges, targetDistance, start);

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class Input {

        final int cities;
        final int[][] edges;
        final int targetDistance;
        final int start;

        public Input(int cities, int[][] edges, int targetDistance, int start) {
            this.cities = cities;
            this.edges = edges;
            this.targetDistance = targetDistance;
            this.start = start;
        }
    }
}

class Solution {

    private List<List<Integer>> graph;
    private int[] distance;
    private int targetDistance;


    public String solution(int cities, int[][] edges, int targetDistance, int start) {
        init(cities, edges, targetDistance);
        calc(start);
        return getAnswer();
    }

    private void init(int cities, int[][] edges, int targetDistance) {
        this.graph = new ArrayList<>();
        this.distance = new int[cities + 1];
        this.targetDistance = targetDistance;
        for(int i = 0; i <= cities; i++) {graph.add(new ArrayList<>());}
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }
        Arrays.fill(distance, Integer.MAX_VALUE);
    }

    private void calc(int start) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {start, 0});
        distance[start] = 0;

        while(!q.isEmpty()) {
            int[] curr = q.poll();

            for(int next : graph.get(curr[0])) {
                if(distance[next] == Integer.MAX_VALUE) {
                    q.offer(new int[] {next, curr[1] + 1});
                    distance[next] = curr[1] + 1;
                }
            }
        }
    }

    private String getAnswer() {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < distance.length; i++) {
            if(distance[i] == targetDistance) {
                sb.append(i).append("\n");
            }
        }
        return sb.length() == 0 ? "-1" : sb.toString();
    }
}
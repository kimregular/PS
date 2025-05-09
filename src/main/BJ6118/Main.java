package main.BJ6118;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Input ip = readInput();
        System.out.println(new Solution().solution(ip.nodes, ip.edges));
    }

    private Input readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int nodes = Integer.parseInt(st.nextToken());
            int numOfEdges = Integer.parseInt(st.nextToken());
            int[][] edges = new int[numOfEdges][2];

            for(int i = 0; i < numOfEdges; i++) {
                st = new StringTokenizer(br.readLine());
                edges[i][0] = Integer.parseInt(st.nextToken());
                edges[i][1] = Integer.parseInt(st.nextToken());
            }

            return new Input(nodes, edges);
        }catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static class Input {

        final int nodes;
        final int[][] edges;

        public Input(int nodes, int[][] edges) {
            this.nodes = nodes;
            this.edges = edges;
        }
    }
}

class Solution {

    private List<List<Integer>> graph;
    private int[] visited;

    public String solution(int nodes, int[][] edges) {
        init(nodes, edges);
        calc();
        return getAnswer();
    }

    private void init(int nodes, int[][] edges) {
        this.graph = new ArrayList<>();
        this.visited = new int[nodes + 1];
        Arrays.fill(visited, -1);

        for(int i = 0; i <= nodes; i++) graph.add(new ArrayList<>());

        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
    }

    private void calc() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {1, 0}); // location, step
        visited[1] = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int nextNode : graph.get(cur[0])) {
                if(visited[nextNode] != -1) continue;
                q.offer(new int[] {nextNode, cur[1] + 1});
                visited[nextNode] = cur[1] + 1;
            }
        }
    }

    private String getAnswer() {
        int targetLocation = -1;
        int targetDist = -1;
        int instead = -1;

        for(int i = 1; i < visited.length; i++) {
            if(targetDist < visited[i]) {
                targetLocation = i;
                targetDist = visited[i];
                instead = 1;
            } else if (targetDist == visited[i]) {
                instead++;
            }
        }

        return targetLocation + " " + targetDist + " " + instead;
    }
}
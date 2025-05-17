package main.BJ13023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
        }catch(IOException e) {
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

    private boolean[] visited;
    private List<List<Integer>> graph;
    private boolean flag;

    public int solution(int nodes, int[][] edges) {
        init(nodes, edges);
        return calc() ? 1 : 0;
    }

    private void init(int nodes, int[][] edges) {
        this.visited = new boolean[nodes];
        this.graph = new ArrayList<>();
        this.flag = false;

        for(int i = 0; i < nodes; i++) graph.add(new ArrayList<>());
        for(int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
    }

    private boolean calc() {
        for(int i = 0; i < visited.length; i++) {
            visited[i] = true;
            DFS(0, i);
            visited[i] = false;
            if(flag) break;
        }
        return flag;
    }

    private void DFS(int depth, int node) {
        if(depth >= 4) {
            flag = true;
            return;
        }

        for(int nextNode : graph.get(node)) {
            if(visited[nextNode]) continue;
            visited[nextNode] = true;
            DFS(depth + 1, nextNode);
            visited[nextNode] = false;
        }
    }
}
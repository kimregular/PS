package main.BJ1967;

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
        System.out.println(new Solution().solution(readInput()));
    }

    private int[][] readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int len = Integer.parseInt(br.readLine());
            int[][] result = new int[len][3];

            for(int i = 0; i < len - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                result[i][0] = from;
                result[i][1] = to;
                result[i][2] = cost;
            }

            return result;
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private List<List<int[]>> graph;
    private int[] visited;
    private int result;
    

    public int solution(int[][] edges) {
        init(edges);
        calc();
        return visited[result];
    }

    private void init(int[][] edges) {
        this.graph = new ArrayList<>();
        this.visited = new int[edges.length + 1];
        this.result = 0;

        for(int i = 0; i < edges.length + 1; i++) graph.add(new ArrayList<>());

        for(int[] edge : edges) {
            graph.get(edge[0]).add(new int[] {edge[1], edge[2]});
            graph.get(edge[1]).add(new int[] {edge[0], edge[2]});
        }
    }

    private void calc() {
        int farNode = bfs(1);
        result = bfs(farNode);
    }    

    private int bfs(int startNode) {
        Arrays.fill(visited, -1);

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {startNode, 0});
        visited[startNode] = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int[] nextNode : graph.get(cur[0])) {
                if(visited[nextNode[0]] != -1) continue;
                visited[nextNode[0]] = cur[1] + nextNode[1];
                q.offer(new int[] {nextNode[0], cur[1] + nextNode[1]});
            }
        }

        return getFarNode(visited);
    }

    private int getFarNode(int[] dist) {
        int cost = -1;
        int node = -1;

        for(int i = 1; i < dist.length; i++) {
            if(dist[i] > cost) {
                cost = dist[i];
                node = i;
            }
        }
        return node;
    }
}
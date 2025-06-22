package main.BJ1167;

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

    private List<List<int[]>> readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int len = Integer.parseInt(br.readLine());
            List<List<int[]>> result = new ArrayList<>();

            for(int i = 0; i <= len; i++) result.add(new ArrayList<>());

            for(int i = 0; i < len; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                
                while(true) {
                    int to = Integer.parseInt(st.nextToken());
                    if(to == -1) break;

                    int cost = Integer.parseInt(st.nextToken());
                    result.get(from).add(new int[] {to, cost});
                    result.get(to).add(new int[] {from, cost});
                }
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
    

    public int solution(List<List<int[]>> graph) {
        init(graph);
        calc();
        return visited[result];
    }

    private void init(List<List<int[]>> graph) {
        this.graph = graph;
        this.visited = new int[graph.size()];
        this.result = 0;
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

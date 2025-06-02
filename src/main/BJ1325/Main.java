package main.BJ1325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Input ip = readInput();
        System.out.println(new Solution().solution(ip.numOfNodes, ip.edges));
    }

    private Input readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){

            StringTokenizer st = new StringTokenizer(br.readLine());
            int numOfNodes = Integer.parseInt(st.nextToken());
            int numOfEdges = Integer.parseInt(st.nextToken());

            int[][] edges = new int[numOfEdges][2];

            for(int i = 0; i < numOfEdges; i++) {
                st = new StringTokenizer(br.readLine());

                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());

                edges[i][0] = from;
                edges[i][1] = to;
            }

            return new Input(numOfNodes, edges);

        }catch(IOException e) { 
            throw new RuntimeException();
        }
    }

    private static class Input {

        final int numOfNodes;
        final int[][] edges;

        public Input(int numOfNodes, int[][] edges) {
            this.numOfNodes = numOfNodes;
            this.edges = edges;
        }
    }
}

class Solution {


    private int[] affected;
    private List<List<Integer>> graph;

    public String solution(int numOfNodes, int[][] edges) {
        init(numOfNodes, edges);
        calc();
        return getAnswer();
    }

    private void init(int numOfNodes, int[][] edges) {
        this.affected = new int[numOfNodes + 1];
        this.graph = new ArrayList<>();

        for(int i = 0; i <= numOfNodes; i++) graph.add(new ArrayList<>());

        for(int[] edge : edges) graph.get(edge[0]).add(edge[1]);
    }

    private void calc() {
        for(int i = 1; i < graph.size(); i++) {
            affected[i] = BFS(i);
        }
    }

    private int BFS(int startNode) {
        boolean[] visited = new boolean[affected.length];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(startNode);
        visited[startNode] = true;

        int result = 0;

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int nextNode : graph.get(cur)) {
                if(visited[nextNode]) continue;
                visited[nextNode] = true;
                q.offer(nextNode);
                result++;
            }
        }
        return result;
    }

    private String getAnswer() {
        StringBuilder answer = new StringBuilder();

        int prevMax = -1;
        for(int i = 1; i < affected.length; i++) {
            if(affected[i] == prevMax) {
                answer.append(i).append(" ");
            } else if (affected[i] > prevMax) {
                prevMax = affected[i];
                answer = new StringBuilder();
                answer.append(i).append(" ");
            }
        }
        return answer.toString();
    }
}
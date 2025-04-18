package main.BJ2252;

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
        System.out.println(new Solution().solution(ip.numOfPeople, ip.edges));
    }

    private Input readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int numOfPeople = Integer.parseInt(st.nextToken());
            int numOfEdges = Integer.parseInt(st.nextToken());

            int[][] edges = new int[numOfEdges][2];
            for(int i = 0; i < numOfEdges; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                edges[i][0] = from;
                edges[i][1] = to;
            }

            return new Input(numOfPeople, edges);
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }

    private static class Input {

        final int numOfPeople;
        final int[][] edges;

        public Input(int numOfPeople, int[][] edges) {
            this.numOfPeople = numOfPeople;
            this.edges = edges;
        }
    }
}

class Solution {

    private int[] indegreesOf;
    private List<List<Integer>> graph;
    private Queue<Integer> q;
    private StringBuilder result;

    public String solution(int numOfPeople, int[][] edges) {
        init(numOfPeople, edges);
        calc();
        return result.toString();
    }

    private void init(int numOfPeople, int[][] edges) {
        this.indegreesOf = new int[numOfPeople + 1];
        this.graph = new ArrayList<>();
        this.q = new ArrayDeque<>();
        this.result = new StringBuilder();

        for(int i = 0; i <= numOfPeople; i++) graph.add(new ArrayList<>());

        for(int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            indegreesOf[to]++;
        }

        for(int i = 1; i < indegreesOf.length; i++) {
            if(indegreesOf[i] != 0) continue;
            q.offer(i);
        }
    }

    private void calc() {
        while(!q.isEmpty()) {
            int cur = q.poll();

            result.append(cur).append(" ");

            for(int nextNode : graph.get(cur)) {
                indegreesOf[nextNode]--;
                if(indegreesOf[nextNode] == 0) q.offer(nextNode);
            }
        }
    }
}
package main.BJ14950;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Input ip = readInput();
        System.out.println(new Solution().solution(ip.cities, ip.edges, ip.taxRate));
    }

    private Input readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int cities = Integer.parseInt(st.nextToken());
            int numOfEdges = Integer.parseInt(st.nextToken());
            int taxRate = Integer.parseInt(st.nextToken());

            int[][] edges = new int[numOfEdges][3];

            for(int i = 0; i < numOfEdges; i++) {
                st = new StringTokenizer(br.readLine());
                edges[i][0] = Integer.parseInt(st.nextToken());
                edges[i][1] = Integer.parseInt(st.nextToken());
                edges[i][2] = Integer.parseInt(st.nextToken());
            }

            return new Input(cities, edges, taxRate);
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }

    private static class Input {

        final int cities;
        final int[][] edges;
        final int taxRate;

        public Input(int cities, int[][] edges, int taxRate) {
            this.cities = cities;
            this.edges = edges;
            this.taxRate = taxRate;
        }
    }
}

class Solution {

    private List<List<Road>> graph;
    private PriorityQueue<Road> pq;
    private boolean[] conquered;
    private int taxRate;
    private int result;

    public int solution(int cities, int[][] edges, int taxRate) {
        init(cities, edges, taxRate);
        calc();
        return result;
    }

    private void init(int cities, int[][] edges, int taxRate) {
        this.graph = new ArrayList<>();
        this.pq = new PriorityQueue<>();
        this.conquered = new boolean[cities + 1];
        this.taxRate = taxRate;
        this.result = 0;

        for(int i = 0; i <= cities; i++) graph.add(new ArrayList<>());

        for(int[] edge : edges) {
            graph.get(edge[0]).add(new Road(edge[1], edge[2]));
            graph.get(edge[1]).add(new Road(edge[0], edge[2]));
        }

        pq.offer(new Road(1, 0));
    }

    private void calc() {
        int tax = 0;
        int cnt = 0;
        while(!pq.isEmpty()) {
            Road cur = pq.poll();

            if(conquered[cur.to]) continue;

            conquered[cur.to] = true;
            result += (tax + cur.cost);
            if(cnt > 0) tax += taxRate;

            cnt++;

            for(Road next : graph.get(cur.to)) {
                if(conquered[next.to]) continue;
                pq.offer(next);
            }
        }
    }

    private static class Road implements Comparable<Road> {

        final int to;
        final int cost;

        public Road(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Road o) {
            return Integer.compare(cost, o.cost);
        }
    }
}
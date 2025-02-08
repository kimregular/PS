package main.BJ1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            Input ip = readInput(br);
            Solution s = new Solution();
            System.out.println(s.solution(ip.graph, ip.startNode));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Input readInput(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfNodes = Integer.parseInt(st.nextToken());
        int numOfLinks = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= numOfNodes; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < numOfLinks; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        for (List<Integer> integers : graph) {
            integers.sort(null);
        }

        return new Input(graph, startNode);
    }

    private static class Input {
        private final List<List<Integer>> graph;
        private final int startNode;

        public Input(List<List<Integer>> graph, int startNode) {
            this.graph = graph;
            this.startNode = startNode;
        }
    }
}

class Solution {

    private List<List<Integer>> graph;
    private boolean[] isVisitedDFS;
    private boolean[] isVisitedBFS;

    StringBuilder result = new StringBuilder();

    public String solution(List<List<Integer>> graph, int startNode) {
        init(graph);
        explore(startNode);
        return result.toString();
    }

    private void init(List<List<Integer>> graph) {
        this.graph = graph;
        this.isVisitedDFS = new boolean[graph.size()];
        this.isVisitedBFS = new boolean[graph.size()];
    }

    private void explore(int startNode) {
        DFS(startNode);
        result.append("\n");
        BFS(startNode);
    }

    private void DFS(int node) {
        result.append(node).append(" ");
        isVisitedDFS[node] = true;
        for (Integer next : graph.get(node)) {
            if (isVisitedDFS[next]) continue;
            isVisitedDFS[next] = true;
            DFS(next);
        }
    }

    private void BFS(int startNode) {
        Queue<Integer> q = new ArrayDeque<>();
        isVisitedBFS[startNode] = true;
        q.offer(startNode);

        while (!q.isEmpty()) {
            int cur = q.poll();
            result.append(cur).append(" ");

            for (Integer next : graph.get(cur)) {
                if (isVisitedBFS[next]) continue;
                isVisitedBFS[next] = true;
                q.offer(next);
            }
        }
    }
}
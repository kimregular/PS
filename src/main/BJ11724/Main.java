package main.BJ11724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            Input ip = readInput(br);
            Solution s = new Solution();
            System.out.println(s.solution(ip.numOfNodes, ip.connections));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Input readInput(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfNodes = Integer.parseInt(st.nextToken());
        int numOfLinks = Integer.parseInt(st.nextToken());

        int[][] connections = new int[numOfLinks][2];
        for (int i = 0; i < numOfLinks; i++) {
            st = new StringTokenizer(br.readLine());
            connections[i][0] = Integer.parseInt(st.nextToken());
            connections[i][1] = Integer.parseInt(st.nextToken());
        }

        return new Input(numOfNodes, connections);
    }

    private static class Input {
        private int numOfNodes;
        private int[][] connections;

        public Input(int numOfNodes, int[][] connections) {
            this.numOfNodes = numOfNodes;
            this.connections = connections;
        }
    }
}

class Solution {

    private int[][] nodes;
    private int[] groups;

    public int solution(int numOfNodes, int[][] connections) {
        init(numOfNodes, connections);

        for (int[] node : nodes) {
            int from = node[0];
            int to = node[1];

            if(isGroup(from, to)) continue;

            connect(from, to);
        }

        return getAnswer();
    }

    private void init(int numOfNodes, int[][] connections) {
        this.nodes = connections;
        this.groups = new int[numOfNodes + 1];
        for (int i = 0; i < groups.length; i++) {
            groups[i] = i;
        }
    }

    private boolean isGroup(int from, int to) {
        return getGroup(from) == getGroup(to);
    }

    private int getGroup(int target) {
        if (groups[target] != target) {
            groups[target] = getGroup(groups[target]);
        }
        return groups[target];
    }

    private void connect(int from, int to) {
        int groupOfFrom = getGroup(from);
        int groupOfTo = getGroup(to);
        if (groupOfFrom == groupOfTo) return;
        groups[Math.max(groupOfFrom, groupOfTo)] = Math.min(groupOfFrom, groupOfTo);
    }

    private int getAnswer() {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i < groups.length; i++) {
            set.add(getGroup(i));
        }
        return set.size();
    }
}
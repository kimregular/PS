package main.BJ11657;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Input ip = readInput();
        System.out.println(new Solution().solution(ip.numOfNodes, ip.busInfos));
    }

    private Input readInput() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int numOfNodes = Integer.parseInt(st.nextToken());
            int numOfBusInfos = Integer.parseInt(st.nextToken());

            long[][] busInfos = new long[numOfBusInfos][3];

            for (int i = 0; i < numOfBusInfos; i++) {
                st = new StringTokenizer(br.readLine());
                busInfos[i][0] = Long.parseLong(st.nextToken());
                busInfos[i][1] = Long.parseLong(st.nextToken());
                busInfos[i][2] = Long.parseLong(st.nextToken());
            }

            return new Input(numOfNodes, busInfos);

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static class Input {

        final int numOfNodes;
        final long[][] busInfos;

        public Input(int numOfNodes, long[][] busInfos) {
            this.numOfNodes = numOfNodes;
            this.busInfos = busInfos;
        }
    }
}

class Solution {

    private static final long INF = Integer.MAX_VALUE;

    private long[] dist;
    private long[][] busLines;
    private boolean hasNegativeCycle;

    public String solution(int numOfNodes, long[][] busLines) {
        init(numOfNodes, busLines);
        calc();
        return getAnswer();
    }

    private void init(int numOfNodes, long[][] busLines) {
        this.dist = new long[numOfNodes + 1];
        this.busLines = busLines;
        this.hasNegativeCycle = false;

        Arrays.fill(dist, INF);
    }

    private void calc() {
        dist[1] = 0;
        for (int i = 0; i < dist.length - 1; i++) {
            for (long[] busLine : busLines) {
                if (dist[(int) busLine[0]] != INF && dist[(int) busLine[0]] + busLine[2] < dist[(int) busLine[1]]) {
                    dist[(int) busLine[1]] = dist[(int) busLine[0]] + busLine[2];
                }
            }
        }

        for (long[] busLine : busLines) {
            if (dist[(int) busLine[0]] != INF && dist[(int) busLine[0]] + busLine[2] < dist[(int) busLine[1]]) {
                hasNegativeCycle = true;
                break;
            }
        }
    }

    private String getAnswer() {
        if(hasNegativeCycle) return "-1";

        StringBuilder result = new StringBuilder();
        for(int i = 2; i < dist.length; i++) {
            result.append(dist[i] == INF ? "-1" : dist[i]).append("\n");
        }
        return result.toString();
    }
}
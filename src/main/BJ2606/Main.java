package src.main.BJ2606;

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

            Solution s = new Solution();
            System.out.println(s.solution(readInput(br)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<List<Integer>> readInput(BufferedReader br) throws IOException {
        int numOfComputers = Integer.parseInt(br.readLine());
        int numOfNodes = Integer.parseInt(br.readLine());

        List<List<Integer>> network = new ArrayList<>();

        for (int i = 0; i <= numOfComputers; i++) {
            network.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < numOfNodes; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            network.get(from).add(to);
            network.get(to).add(from);
        }

        return network;
    }
}

class Solution {

    private List<List<Integer>> network;
    private int[] computers;

    public int solution(List<List<Integer>> network) {
        init(network);
        return explore();
    }

    private void init(List<List<Integer>> network) {
        this.network = network;
        this.computers = new int[network.size() + 1];
    }

    private int explore() {
        int result = 0;
        int start = 1;
        computers[start] = 1;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : network.get(cur)) {
                if(computers[next] == 1) continue;
                computers[next] = 1;
                result++;
                q.offer(next);
            }
        }
        return result;
    }
}
package main.BJ30892;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Input ip = readInput();
        System.out.println(new Solution().solution(ip.targets, ip.limit, ip.initSize));
    }

    private Input readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int numOfTargets = Integer.parseInt(st.nextToken());
            int limit = Integer.parseInt(st.nextToken());
            long initSize = Long.parseLong(st.nextToken());

            long[] targets = new long[numOfTargets];

            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < numOfTargets; i++) {
                targets[i] = Long.parseLong(st.nextToken());
            }

            return new Input(targets, limit, initSize);
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }

    private static class Input {

        final long[] targets;
        final int limit;
        final long initSize;

        public Input(long[] targets, int limit, long initSize) {
            this.targets = targets;
            this.limit = limit;
            this.initSize = initSize;
        }
    }
}

class Solution {

    private PriorityQueue<Long> edible;
    private PriorityQueue<Long> biggers;
    private int limit;
    private long size;

    public long solution(long[] targets, int limit, long initSize) {
        init(targets, limit, initSize);
        calc();
        return size;
    }

    private void init(long[] targets, int limit, long initSize) {
        this.edible = new PriorityQueue<>(Collections.reverseOrder());
        this.biggers = new PriorityQueue<>();
        this.limit = limit;
        this.size = initSize;

        for(long target : targets) {
            if(target < initSize) edible.offer(target);
            else biggers.offer(target);
        }
    }

    private void calc() {
        while(limit-- > 0) {
            while(!biggers.isEmpty() && biggers.peek() < size) edible.offer(biggers.poll());
            if(!edible.isEmpty()) size += edible.poll();
        }
    }
}
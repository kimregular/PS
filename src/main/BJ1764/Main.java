package main.BJ1764;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            Input ip = readInput(br);
            Solution s = new Solution();
            System.out.println(s.solution(ip.noHeard, ip.noSaw));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Input readInput(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int noHeardLen = Integer.parseInt(st.nextToken());
        int noSawLen = Integer.parseInt(st.nextToken());
        String[] noHeard = new String[noHeardLen];
        for (int i = 0; i < noHeardLen; i++) {
            noHeard[i] = br.readLine();
        }

        String[] noSaw = new String[noSawLen];
        for (int i = 0; i < noSawLen; i++) {
            noSaw[i] = br.readLine();
        }
        return new Input(noHeard, noSaw);
    }

    private static class Input {
        private String[] noHeard;
        private String[] noSaw;

        public Input(String[] noHeard, String[] noSaw) {
            this.noHeard = noHeard;
            this.noSaw = noSaw;
        }
    }
}

class Solution {

    public String solution(String[] noHeard, String[] noSaw) {
        Set<String> noHeardSet = Arrays.stream(noHeard).collect(Collectors.toSet());

        List<String> names = new ArrayList<>();
        for (String s : noSaw) {
            if(!noHeardSet.contains(s)) continue;
            names.add(s);
        }

        names.sort(String::compareTo);

        return getAnswer(names);
    }

    private String getAnswer(List<String> names) {
        StringBuilder answer = new StringBuilder();
        answer.append(names.size()).append("\n");
        for (String name : names) {
            answer.append(name).append("\n");
        }
        return answer.toString();
    }
}
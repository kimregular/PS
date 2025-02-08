package main.BJ17219;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            Input ip = readInput(br);
            Solution s = new Solution();
            System.out.println(s.solution(ip.inputs, ip.targets));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Input readInput(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfInputs = Integer.parseInt(st.nextToken());
        int numOfTargets = Integer.parseInt(st.nextToken());

        String[][] inputs = new String[numOfInputs][2];
        for (int i = 0; i < numOfInputs; i++) {
            st = new StringTokenizer(br.readLine());
            inputs[i][0] = st.nextToken();
            inputs[i][1] = st.nextToken();
        }

        String[] targets = new String[numOfTargets];
        for (int i = 0; i < numOfTargets; i++) {
            targets[i] = br.readLine();
        }
        return new Input(inputs, targets);
    }

    private static class Input {
        private String[][] inputs;
        private String[] targets;

        public Input(String[][] inputs, String[] targets) {
            this.inputs = inputs;
            this.targets = targets;
        }
    }
}

class Solution {

    public String solution(String[][] inputs, String[] targets) {
        Map<String, String> storedPasswords = Arrays
                .stream(inputs)
                .collect(Collectors.toMap(i -> i[0], i -> i[1]));

        return Arrays.stream(targets)
                     .map(storedPasswords::get)
                     .collect(Collectors.joining("\n"));
    }
}
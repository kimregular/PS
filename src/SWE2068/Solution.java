package src.SWE2068;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        new Solution().run();
    }

    private void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            Resolver resolver = new Resolver();
            System.out.println(resolver.resolve(readInput(br)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int[][] readInput(BufferedReader br) throws IOException {
        int len = Integer.parseInt(br.readLine());
        int[][] result = new int[len][10];
        for (int i = 0; i < len; i++) {
            result[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        return result;
    }
}

class Resolver {

    public String resolve(int[][] testCases) {
        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < testCases.length; i++) {
            answer.append("#").append(i + 1).append(" ").append(getMax(testCases[i])).append("\n");
        }
        return answer.toString();
    }

    private int getMax(int[] target) {
        return Arrays.stream(target).reduce(0, Math::max);
    }
}


package src.SWE5215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) {
        new Solution().run();
    }

    private void run() {

        StringBuilder answer = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int numOfTestCases = Integer.parseInt(br.readLine());
            for (int i = 0; i < numOfTestCases; i++) {
                Input ip = readInput(br);
                Resolver r = new Resolver();
                answer.append("#").append(i + 1).append(" ")
                      .append(r.resolve(ip.limit, ip.ingredients)).append("\n");
            }

            System.out.println(answer);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Input readInput(BufferedReader br) throws IOException {
        String[] temp = br.readLine().split(" ");
        int numOfIngredients = Integer.parseInt(temp[0]);
        int limit = Integer.parseInt(temp[1]);

        StringTokenizer st;
        int[][] ingredients = new int[numOfIngredients][2];
        for (int i = 0; i < numOfIngredients; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                ingredients[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        return new Input(limit, ingredients);
    }

    private static class Input {

        private final int limit;
        private final int[][] ingredients;

        public Input(int limit, int[][] ingredients) {
            this.limit = limit;
            this.ingredients = ingredients;
        }
    }
}

class Resolver {

    private int limit;
    private int[][] ingredients;
    private int maxedFavor = 0;

    public int resolve(int limit, int[][] ingredients) {
        init(limit, ingredients);
        calc(0, 0, 0);
        return maxedFavor;
    }

    private void init(int limit, int[][] ingredients) {
        this.limit = limit;
        this.ingredients = ingredients;
    }

    private void calc(int idx, int accumulatedFavor, int accumulatedCal) {
        if (accumulatedCal > limit) {
            return;
        }
        if (idx == ingredients.length) {
            if (maxedFavor < accumulatedFavor) {
                maxedFavor = accumulatedFavor;
            }
            return;
        }

        calc(idx + 1, accumulatedFavor, accumulatedCal);
        calc(idx + 1, accumulatedFavor + ingredients[idx][0], accumulatedCal + ingredients[idx][1]);
    }
}


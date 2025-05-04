package main.BJ19942;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Input ip = readInput();
        System.out.println(new Solution().solution(ip.minStandards, ip.ingredients));
    }

    private Input readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int len = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] minStandards = new int[4];
            for(int i = 0; i < 4; i++) {
                minStandards[i] = Integer.parseInt(st.nextToken());
            }

            int[][] ingredients = new int[len][5];
            for(int i = 0; i < len; i++) {
                st = new StringTokenizer(br.readLine());
                ingredients[i][0] = Integer.parseInt(st.nextToken());
                ingredients[i][1] = Integer.parseInt(st.nextToken());
                ingredients[i][2] = Integer.parseInt(st.nextToken());
                ingredients[i][3] = Integer.parseInt(st.nextToken());
                ingredients[i][4] = Integer.parseInt(st.nextToken());
            }

            return new Input(minStandards, ingredients);

        }catch(IOException e) {
            throw new RuntimeException();
        }
    }

    private static class Input {

        final int[] minStandards;
        final int[][] ingredients;

        public Input(int[] minStandards, int[][] ingredients) {
            this.minStandards = minStandards;
            this.ingredients = ingredients;
        }
    }
}

class Solution {

    private int[] minStandards;
    private int[][] ingredients;
    private int costResult;
    private String ingsResult;

    public String solution(int[] minStandards, int[][] ingredients) {
        init(minStandards, ingredients);
        calc(0, 0, 0, 0, 0, 0, new ArrayList<>());
        return costResult == Integer.MAX_VALUE ? "-1" : costResult + "\n" + ingsResult;
    }

    private void init(int[] minStandards, int[][] ingredients) {
        this.minStandards = minStandards;
        this.ingredients = ingredients;
        this.costResult = Integer.MAX_VALUE;
        this.ingsResult = "";
    }

    private void calc(int idx, int dan, int ji, int tan, int vi, int cost, List<Integer> ings) {
        if(idx == ingredients.length) {
            if(isNutrientsGood(dan, ji, tan, vi)) {
                if(cost < costResult) {
                    costResult = cost;
                    ingsResult = getSortedString(ings);
                }else if (cost == costResult) {
                    String sortedString = getSortedString(ings);
                    if(sortedString.compareTo(ingsResult) < 0) {
                        ingsResult = sortedString;
                    }
                }
            }
            return;
        }

        calc(idx + 1, dan, ji, tan, vi, cost, ings);
        List<Integer> newIngs = new ArrayList<>(ings);
        newIngs.add(idx + 1);
        calc(idx + 1, 
        dan + ingredients[idx][0], 
        ji + ingredients[idx][1], 
        tan + ingredients[idx][2], 
        vi + ingredients[idx][3], 
        cost + ingredients[idx][4], 
        newIngs);
    }

    private boolean isNutrientsGood(int dan, int ji, int tan, int vi) {
        return dan >= minStandards[0] && 
        ji >= minStandards[1] && 
        tan >= minStandards[2] && 
        vi >= minStandards[3];
    }

    private String getSortedString(List<Integer> ings) {
        List<Integer> temp = new ArrayList<>(ings);
        temp.sort(null);
        StringBuilder result = new StringBuilder();
        for(int i : temp) result.append(i).append(" ");
        return result.toString();
    }
}
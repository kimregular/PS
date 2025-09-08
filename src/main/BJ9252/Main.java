package main.BJ9252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static String str1;
    static String str2;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str1 = br.readLine();
        str2 = br.readLine();
        dp = new int[str1.length() + 1][str2.length() + 1];
        for(int[] d : dp) Arrays.fill(d, -1);

        int result = calc(str1.length(), str2.length());

        System.out.println(result);
        System.out.println(compose());

        br.close();;
    }

    static int calc(int i, int j) {
        if(i == 0 || j == 0) {
            return 0;
        } else if (dp[i][j] != -1) {
            return dp[i][j];
        } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
            dp[i][j] = calc(i - 1, j - 1) + 1;
            return dp[i][j];
        } else {
            dp[i][j] = Math.max(calc(i - 1, j), calc(i, j - 1));
            return dp[i][j];
        }
    }

    static String compose() {
        StringBuilder answer = new StringBuilder();
        int i = str1.length();
        int j = str2.length();

        while(i > 0 && j > 0) {
            if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
                answer.append(str1.charAt(i - 1));
                i--;
                j--;
            } else {
                if(dp[i - 1][j] >= dp[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }
        return answer.reverse().toString();
    }
}
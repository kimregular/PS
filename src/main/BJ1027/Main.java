package main.BJ1027;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] buildings;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int len = Integer.parseInt(br.readLine());
        buildings = new int[len];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < len; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;

        for(int i = 0; i < buildings.length; i++) {
            result = Math.max(result, visibleCount(i));
        }

        System.out.println(result);

        br.close();
    }

    static int visibleCount(int idx) {
        int result = 0;

        double SLOPE = Double.POSITIVE_INFINITY;
        for(int i = idx - 1; i >= 0; i--) {
            double newSlope = getSlope(idx, i);
            if(newSlope < SLOPE) {
                SLOPE = newSlope;
                result++;
            }
        }

        SLOPE = Double.NEGATIVE_INFINITY;
        for(int i = idx + 1; i < buildings.length; i++) {
            double newSlope = getSlope(idx, i);
            if (newSlope > SLOPE) {
                SLOPE = newSlope;
                result++;
            }
        }

        return result;
    }

    static double getSlope(int i, int j) {
        return (double) (buildings[j] - buildings[i]) / (j - i);
    }
}
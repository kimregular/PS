package main.BJ1074;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        Input ip = readInput();
        System.out.println(new Solution().solution(ip.n, ip.r, ip.c));
    }

    private Input readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            return new Input(n, r, c);

        }catch(IOException e) {
            throw new RuntimeException();
        }
    }

    private static class Input {

        final int n;
        final int r;
        final int c;

        public Input(int n, int r, int c) {
            this.n = n;
            this.r = r;
            this.c = c;
        }
    }
}

class Solution {

    public int solution(int n, int r, int c) {
        int N = (int) Math.pow(2, n);
        return calc(N, r, c, 0);
    }

    private int calc(int n, int r, int c, int cnt) {
        if(n == 1) {
            return cnt;
        }

        int half = n / 2;
        int area = half * half;

        if(r < half && c < half) {
            // 2
            return calc(half, r, c, cnt);
        } else if (r < half && c >= half) {
            // 1
            return calc(half, r, c - half, cnt + area);
        } else if (r >= half && c < half) {
            // 3
            return calc(half, r - half, c, cnt + 2 * area);
        } else {
            // 4
            return calc(half, r - half, c - half, cnt + 3 * area);
        }
    }
}
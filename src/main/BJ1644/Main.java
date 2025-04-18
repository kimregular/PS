package main.BJ1644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println(new Solution().solution(readInput()));
    }

    private int readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            return Integer.parseInt(br.readLine());

        } catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private List<Integer> primes;
    private int target;
    private int result;

    public int solution(int target) {
        init(target);
        calc();
        return result;
    }

    private void init(int target) {
        this.primes = Siever.getSieved();
        this.target = target;
        this.result = 0;
    }

    private void calc() {
        int lp = 0;
        int rp = 0;

        int sum = primes.get(rp++);
        while(lp < rp && rp < primes.size()) {
            if(sum < target) {
                sum += primes.get(rp++);
            } else {
                if (sum == target) {
                    result++;
                }
                sum -= primes.get(lp++);
            }
        }

        while(lp < primes.size()) {
            sum -= primes.get(lp++);
            if(sum == target) result++;
        }
    }
}

abstract class Siever {

    private static List<Integer> sieved;

    public static List<Integer> getSieved() {
        sieved  = new ArrayList<>();
        sieve();
        return sieved;
    }

    private static void sieve() {
        boolean[] field = new boolean[4_000_001];
        for(int i = 2; i < field.length; i++) {
            if(field[i]) continue;
            sieved.add(i);
            for(int j = i * 2; j < field.length; j += i) {
                field[j] = true;
            }
        }
    }
}
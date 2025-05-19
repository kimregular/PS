package main.BJ1715;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println(new Solution().solution(readInput()));
    }

    private int[] readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int len = Integer.parseInt(br.readLine());
            int[] result = new int[len];

            for(int i = 0; i < len; i++) {
                result[i] = Integer.parseInt(br.readLine());
            }

            return result;
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private PriorityQueue<Integer> cardQ;
    private int result;

    public int solution(int[] cards) {
        init(cards);
        calc();
        return result;
    }

    private void init(int[] cards) {
        this.cardQ = new PriorityQueue<>();
        this.result = 0;

        for(int card : cards) cardQ.offer(card);
    }

    private void calc() {
        while(!cardQ.isEmpty() && cardQ.size() >= 2) {
            int card1 = cardQ.poll();
            int card2 = cardQ.poll();
            
            int newCard = card1 + card2;
            result += newCard;
            cardQ.offer(newCard);
        }
    }
}
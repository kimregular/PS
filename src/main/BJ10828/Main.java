package main.BJ10828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Main {
    
    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println(new Solution().solution(readInput()));
    }

    private String[][] readInput() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int len = Integer.parseInt(br.readLine());

            String[][] result = new String[len][];

            for(int i = 0; i < len; i++) {
                result[i] = br.readLine().split(" ");
            }
            return result;
        }catch(IOException e) {
            throw new RuntimeException();
        }
    }
}

class Solution {

    private Deque<Integer> stck;
    private StringBuilder result;

    public String solution(String[][] orders) {
        init();
        for(String[] order : orders) {
            execute(order);
        }
        return result.toString();
    }

    private void init() {
        this.stck = new ArrayDeque<>();
        this.result = new StringBuilder();
    }

    private void execute(String[] order) {
        Executer e = Executer.of(order[0]);
        e.processing(stck, result, order);
    }
}

enum Executer {

    PUSH("push", (stck, strs, orders) -> {
        stck.offerLast(Integer.parseInt(orders[1]));
    }), 
    POP("pop", (stck, strs, orders) -> {
        strs.append(stck.isEmpty() ? "-1" : stck.pollLast()).append("\n");
    }), 
    SIZE("size", (stck, strs, orders) -> {
        strs.append(stck.size()).append("\n");
    }), 
    EMPTY("empty", (stck, strs, orders) -> {
        strs.append(stck.isEmpty() ? 1 : 0).append("\n");
    }), 
    TOP("top", (stck, strs, orders) -> {
        strs.append(stck.isEmpty() ? -1 : stck.peekLast()).append("\n");
    });

    private final String orderType;
    private final Func func;

    private Executer(String orderType, Func func) {
        this.orderType = orderType;
        this.func = func;
    }

    private static final Map<String, Executer> map;

    static {
        map = new HashMap<>();
        for(Executer e : values()) {
            map.put(e.orderType, e);
        }
    }

    public static Executer of(String type) {
        return map.get(type);
    }

    public void processing(Deque<Integer> stck, StringBuilder strs, String[] orders) {
        this.func.calc(stck, strs, orders);
    }
}

interface Func {

    void calc(Deque<Integer> stck, StringBuilder strs, String[] orders);
}
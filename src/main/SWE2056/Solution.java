package main.SWE2056;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        new Solution().run();
    }

    private void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            Resolver r = new Resolver();
            System.out.println(r.resolve(readInput(br)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String[] readInput(BufferedReader br) throws IOException {
        int len = Integer.parseInt(br.readLine());
        String[] result = new String[len];

        for (int i = 0; i < len; i++) {
            result[i] = br.readLine();
        }
        return result;
    }
}

class Resolver {

    private static final Set<String> monthSet;

    static {
        Set<String> tempSet = new HashSet<>();
        tempSet.add("01");
        tempSet.add("02");
        tempSet.add("03");
        tempSet.add("04");
        tempSet.add("05");
        tempSet.add("06");
        tempSet.add("07");
        tempSet.add("08");
        tempSet.add("09");
        tempSet.add("10");
        tempSet.add("11");
        tempSet.add("12");
        monthSet = Collections.unmodifiableSet(tempSet);
    }
    private static final String[] days = {"31", "28", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31"};

    public String resolve(String[] testCases) {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < testCases.length; i++) {
            answer.append("#").append(i + 1).append(" ").append(isValid(testCases[i])).append("\n");
        }
        return answer.toString();
    }

    private String isValid(String target) {
        StringBuilder result = new StringBuilder();
        result.append(target.substring(0, 4));
        String month = target.substring(4, 6);
        if (monthSet.contains(month)) {
            result.append("/").append(month);
        } else {
            return "-1";
        }

        String day = target.substring(6);

        int monthIndex = Integer.parseInt(month) - 1;
        int maxDay = Integer.parseInt(days[monthIndex]);

        if (day.length() == 2) {
            int dayNum = Integer.parseInt(day);
            if (dayNum >= 1 && dayNum <= maxDay) {
                result.append("/").append(day);
                return result.toString();
            }
        }
        return "-1";
    }
}


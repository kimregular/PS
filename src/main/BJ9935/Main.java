package main.BJ9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    
    static String target;
    static String bomb;
    static char[] stck;
    static int pointer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        target = br.readLine();
        bomb = br.readLine();

        stck = new char[target.length()];

        for(int i = 0; i < target.length(); i++) {
            char cur = target.charAt(i);
            stck[pointer++] = cur;
            if(cur == bomb.charAt(bomb.length() - 1) && pointer >= bomb.length()) {
                boolean flag = true;
                for(int j = 0; j < bomb.length(); j++) {
                    if(stck[pointer - 1 - j] != bomb.charAt(bomb.length() - 1 - j)) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    pointer -= bomb.length();
                }
            }
        }

        if(pointer == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(new String(stck, 0, pointer));
        }

        br.close();
    }
}

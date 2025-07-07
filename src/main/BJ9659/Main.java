package main.BJ9659;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BigInteger len = new BigInteger(br.readLine());

        // s, c
        // 1 -> s
        // 2 -> s, c
        // 3 -> s
        // 4 -> s, c
        // 5 -> s, c, s
        
        if(len.mod(BigInteger.TWO) != BigInteger.ZERO) System.out.println("SK");
        else System.out.println("CY");

        br.close();
    }
}

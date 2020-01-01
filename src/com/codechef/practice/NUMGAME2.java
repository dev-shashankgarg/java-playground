package com.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

class NUMGAME2 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int testCases = Integer.parseInt(reader.readLine());
            IntStream.range(0, testCases).forEach(testCase -> {
                try {
                    String number = reader.readLine();
                    long a = Long.parseLong(number);
                    System.out.println(solve(a));
                } catch (IOException ignored) {
                }
            });
        } catch (Exception ignored) {
        }
    }

    private static String solve(long a) {
        return (a % 4 == 1) ? "ALICE" : "BOB";
    }
}

package com.codechef.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

class RESQ {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int testCases = Integer.parseInt(reader.readLine());
            IntStream.range(0, testCases).forEach(testCase -> {
                try {
                    long input = Long.parseLong(reader.readLine());
                    System.out.println(solve(input));
                } catch (Exception ignored) {
                }
            });
        } catch (Exception ignored) {
        }
    }

    private static long solve(long input) {
        long answer = input;

        for (int i = 1; i <= Math.sqrt(input); i++) {
            if (input % i == 0) {
                answer = (input / i) - i;
            }
        }

        return answer;
    }
}

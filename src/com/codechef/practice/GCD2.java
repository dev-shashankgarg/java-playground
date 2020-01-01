package com.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class GCD2 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int testCases = Integer.parseInt(reader.readLine());
            IntStream.range(0, testCases).forEach(testCase -> {
                try {
                    String[] numbers = reader.readLine().split(" ");
                    long a = Long.parseLong(numbers[0]);
                    long b = Long.parseLong(numbers[1]);
                    System.out.println(solve(a, b));
                } catch (IOException ignored) {
                }
            });
        } catch (Exception ignored) {
        }
    }

    private static long solve(long a, long b) {
        return gcd(a, b);
    }

    private static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}

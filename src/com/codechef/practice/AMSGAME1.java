package com.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

class AMSGAME1 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int testCases = Integer.parseInt(reader.readLine());
            IntStream.range(0, testCases).forEach(testCase -> {
                try {
                    int arrLen = Integer.parseInt(reader.readLine());
                    String[] numStrings = reader.readLine().split(" ");
                    long[] arr = new long[arrLen];
                    IntStream.range(0, arrLen).forEach(index -> arr[index] = Long.parseLong(numStrings[index]));

                    System.out.println(solve(arr, arrLen));
                } catch (IOException ignored) {
                }
            });
        } catch (Exception ignored) {
        }
    }

    private static long solve(long[] arr, int arrLen) {
        if (arrLen == 1) return arr[0];
        long gcdVal = gcd(arr[0], arr[1]);
        for (int i = 2; i < arrLen; i++) {
            gcdVal = gcd(gcdVal, arr[i]);
        }
        return gcdVal;
    }

    private static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}

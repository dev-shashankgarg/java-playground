package com.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

class RRCOPY {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int testCases = Integer.parseInt(reader.readLine());
            IntStream.range(0, testCases).forEach(testCase -> {
                try {
                    int arrLen = Integer.parseInt(reader.readLine());
                    String[] numStrings = reader.readLine().split(" ");
                    int[] arr = new int[arrLen];
                    IntStream.range(0, arrLen).forEach(index -> arr[index] = Integer.parseInt(numStrings[index]));

                    System.out.println(solve(arr, arrLen));
                } catch (IOException ignored) {
                }
            });
        } catch (Exception ignored) {
        }
    }

    private static long solve(int[] arr, int arrLen) {
        return Arrays.stream(arr).distinct().count();
    }
}

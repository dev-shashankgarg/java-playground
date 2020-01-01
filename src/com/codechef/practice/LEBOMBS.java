package com.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

class LEBOMBS {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int testCases = Integer.parseInt(reader.readLine());
            IntStream.range(0, testCases).forEach(testCase -> {
                try {
                    int arrLen = Integer.parseInt(reader.readLine());
                    String[] numStrings = reader.readLine().split("");
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
        boolean[] willExplode = new boolean[arrLen];
        Arrays.fill(willExplode, false);

        for (int i = 0; i < arrLen; i++) {
            if (arr[i] == 1) {
                willExplode[i] = true;
                if (i - 1 >= 0) {
                    willExplode[i - 1] = true;
                }
                if (i + 1 < arrLen) {
                    willExplode[i + 1] = true;
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < arrLen; i++) {
            if (!willExplode[i]) answer++;
        }
        return answer;
    }
}

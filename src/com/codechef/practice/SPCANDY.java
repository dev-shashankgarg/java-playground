package com.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

class SPCANDY {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int testCases = Integer.parseInt(reader.readLine());
            IntStream.range(0, testCases).forEach(testCase -> {
                try {
                    String[] input = reader.readLine().split(" ");
                    long left = Long.parseLong(input[0]);
                    long right = Long.parseLong(input[1]);
                    System.out.println(solve(left, right));
                } catch (IOException ignored) {
                }

            });
        } catch (Exception ignored) {
        }
    }

    private static String solve(long left, long right) {
        long distribution = 0;
        long teacherLeftOver = left;

        if (right > 0 && left > 0) {
            distribution = left / right;
            teacherLeftOver = left % right;
        }

        return distribution + " " + teacherLeftOver;
    }
}

package com.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

class CSUB {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int testCases = Integer.parseInt(reader.readLine());
            IntStream.range(0, testCases).forEach(testCase -> {
                try {
                    int strLen = Integer.parseInt(reader.readLine());
                    long count = Arrays.stream(reader.readLine().split("")).filter(str -> str.equals("1")).count();
                    System.out.println((count * (count + 1)) / 2);
                } catch (IOException ignored) {
                }

            });
        } catch (Exception ignored) {
        }
    }
}

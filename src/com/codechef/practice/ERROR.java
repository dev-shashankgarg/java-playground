package com.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

class ERROR {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int testCases = Integer.parseInt(reader.readLine());
            IntStream.range(0, testCases).forEach(testCase -> {
                try {
                    String str = reader.readLine();
                    System.out.println((str.contains("101") || str.contains("010") ? "Good" : "Bad"));
                } catch (IOException ignored) {
                }
            });
        } catch (Exception ignored) {
        }
    }
}

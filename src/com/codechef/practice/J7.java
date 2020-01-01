package com.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

class J7 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int testCases = Integer.parseInt(reader.readLine());
            IntStream.range(0, testCases).forEach(testCase -> {
                try {
                    String[] numbers = reader.readLine().split(" ");
                    long a = Long.parseLong(numbers[0]);
                    long b = Long.parseLong(numbers[1]);
                    System.out.format("%.2f", solve(a, b));
                    System.out.println();
                } catch (IOException ignored) {
                }
            });
        } catch (Exception ignored) {
        }
    }

    private static double solve(long perimeter, long area) {
        double length = (double) perimeter / 12.0 - (Math.sqrt(Math.pow(perimeter, 2) - 24.0 * area)) / 12.0;

        double volume = Math.pow(length, 3) + ((area / 2.0) * length) - ((perimeter / 4.0) * Math.pow(length, 2));
        return Math.round(volume * 100) / 100.0;
    }
}

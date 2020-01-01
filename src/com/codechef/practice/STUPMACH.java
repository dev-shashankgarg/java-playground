package com.codechef.practice;

import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

class STUPMACH {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            long test = scanner.nextInt();
            LongStream.range(0, test).forEach(index -> {
                int n = scanner.nextInt();
                long[] arr = new long[n];
                for (int i = 0; i < n; i++) {
                    arr[i] = scanner.nextInt();
                }
                System.out.println(solve(arr, n, 0));
            });

        } catch (Exception e) {
        }
    }

    private static long solve(long[] arr, int length, long modifier) {

        if (length <= 0) return 0;

        int minValIndex = IntStream.range(0, length).reduce(0, (a, b) -> (arr[a] <= arr[b]) ? a : b);
        long value = arr[minValIndex];

        return (value - modifier) * length + solve(arr, minValIndex, value);
    }
}

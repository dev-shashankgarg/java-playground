package com.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

class LEPERMUT {
    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)))) {
            int testCases = Integer.parseInt(reader.readLine());
            IntStream.range(0, testCases).forEach(testCase -> {
                try {
                    int arrLen = Integer.parseInt(reader.readLine());
                    long[] arr = new long[arrLen];

                    String[] numStrings = reader.readLine().split(" ");
                    IntStream.range(0, arrLen).forEach(index ->
                            arr[index] = Integer.parseInt(numStrings[index])
                    );
                    System.out.println(solve(arr));
                } catch (IOException ignored) {
                }
            });
        } catch (IOException ignored) {
        }
    }

    private static String solve(long[] arr) {
        if (arr.length == 1) return "YES";
        String direction = "U";
        long MAXIMA = arr[0];
        long SECOND_MAXIMA = Integer.MIN_VALUE;

        for (int it = 1; it < arr.length; it++) {

            long currentVal = arr[it];

            if (direction.equals("U")) {
                if (currentVal > MAXIMA) {
                    SECOND_MAXIMA = MAXIMA;
                    MAXIMA = currentVal;
                } else if (currentVal < MAXIMA && currentVal > SECOND_MAXIMA) {
                    direction = "D";
                } else {
                    return "NO";
                }
            } else {
                if (currentVal > MAXIMA) {
                    SECOND_MAXIMA = MAXIMA;
                    MAXIMA = currentVal;
                    direction = "U";
                } else {
                    return "NO";
                }
            }

        }
        return "YES";
    }
}

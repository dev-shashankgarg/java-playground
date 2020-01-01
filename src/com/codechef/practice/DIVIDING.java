package com.codechef.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

class DIVIDING {

    private static long sum = 0;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            long arrLen = Long.parseLong(reader.readLine());
            String[] numStrings = reader.readLine().split(" ");
            sum = 0;
            IntStream.range(0, (int) arrLen).forEach(index -> sum += Long.parseLong(numStrings[index]));
            System.out.println(solve(sum, arrLen));

        } catch (Exception ignored) {
        }
    }

    private static String solve(long sum, long length) {

        long sumOfN = ((length) * (length + 1)) / 2;
        return (sumOfN == sum) ? "YES" : "NO";
    }
}

package com.codechef.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CommonTemplates {

    public static void main(String[] args) {

        //basic template
        try (Scanner scanner = new Scanner(System.in)) {
            int testCases = scanner.nextInt();
            IntStream.range(0, testCases).forEach(testCase -> {

            });
        } catch (Exception ignored) {
        }

        //array template
        try (Scanner scanner = new Scanner(System.in)) {
            int testCases = scanner.nextInt();
            IntStream.range(0, testCases).forEach(testCase -> {

                int arrLen = scanner.nextInt();
                long[] arr = new long[arrLen];
                IntStream.range(0, arrLen).forEach(index -> arr[index] = scanner.nextLong());
            });
        } catch (Exception ignored) {
        }


        //BUFFERED READER BASIC TEMPLATE
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int testCases = Integer.parseInt(reader.readLine());
            IntStream.range(0, testCases).forEach(testCase -> {

            });
        } catch (Exception ignored) {
        }

        //BUFFERED READER ARRAY TEMPLATE
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int testCases = Integer.parseInt(reader.readLine());
            IntStream.range(0, testCases).forEach(testCase -> {
                try {
                    int arrLen = Integer.parseInt(reader.readLine());
                    String[] numStrings = reader.readLine().split(" ");
                    int[] arr = new int[arrLen];
                    IntStream.range(0, arrLen).forEach(index -> arr[index] = Integer.parseInt(numStrings[index]));

                } catch (IOException ignored) {
                }
            });
        } catch (Exception ignored) {
        }

    }

}

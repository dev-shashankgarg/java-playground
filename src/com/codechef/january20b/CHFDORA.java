package com.codechef.january20b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

class CHFDORA {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int testCases = Integer.parseInt(reader.readLine());
            IntStream.range(0, testCases).forEach(test -> {
                try {
                    String[] input = reader.readLine().split(" ");
                    int width = Integer.parseInt(input[0]);
                    int length = Integer.parseInt(input[1]);

                    int[][] arr = new int[width][length];
                    int[][] count = new int[width][length];

                    for (int i = 0; i < width; i++) {
                        String[] line = reader.readLine().split(" ");
                        for (int j = 0; j < length; j++) {
                            arr[i][j] = Integer.parseInt(line[j]);
                        }
                    }
                    Arrays.stream(count).forEach(subArray -> Arrays.fill(subArray, 1));
                    System.out.println(solve(width, length, arr, count));
                } catch (Exception ignored) {
                }
            });
        } catch (Exception ignored) {
        }
    }

    private static int solve(int width, int length, int[][] arr, int[][] count) {
        int totalRounds = (int) Math.ceil(Math.min(width, length) / 2.0) - 1;
        IntStream.rangeClosed(1, totalRounds).forEach(round -> evaluate(round, length, width, arr, count));

        return summation(count);
    }

    private static void evaluate(int round, int length, int width, int[][] arr, int[][] count) {
        for (int i = round; i < width - round; i++) {
            for (int j = round; j < length - round; j++) {
                if (count[i][j] == round) {
                    if ((arr[i - round][j] == arr[i + round][j]) && (arr[i][j - round] == arr[i][j + round])) {
                        count[i][j] += 1;
                    }
                }
            }
        }
    }

    private static int summation(int[][] count) {
        return Arrays.stream(count).map(subArray -> Arrays.stream(subArray)
                .reduce(0, Integer::sum)).reduce(0, Integer::sum);
    }
}

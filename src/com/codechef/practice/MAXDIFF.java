package com.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class MAXDIFF {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int testCases = Integer.parseInt(reader.readLine());
            IntStream.range(0, testCases).forEach(testCase -> {
                try {
                    String[] quantStr = reader.readLine().split(" ");
                    int arrLen = Integer.parseInt(quantStr[0]);
                    int k = Integer.parseInt(quantStr[1]);
                    String[] numStrings = reader.readLine().split(" ");
                    int[] arr = new int[arrLen];
                    IntStream.range(0, arrLen).forEach(index -> arr[index] = Integer.parseInt(numStrings[index]));
                    System.out.println(solve(arr, k));
                } catch (IOException ignored) {
                }
            });
        } catch (Exception ignored) {
        }
    }

    private static int solve(int[] arr, int k) {
        List<Integer> weights = Arrays.stream(arr).boxed().sorted().collect(Collectors.toList());
        Integer totalWeight = weights.stream().reduce(0, Integer::sum);
        int lightestKWeight = IntStream.range(0, k).map(weights::get).reduce(0, Integer::sum);
        int heaviestKWeight = IntStream.range(weights.size() - k, weights.size()).map(weights::get).reduce(0, Integer::sum);

        return Math.max(Math.abs(totalWeight - (2 * lightestKWeight)), Math.abs(totalWeight - (2 * heaviestKWeight)));
    }
}

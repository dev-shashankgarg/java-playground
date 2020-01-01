package com.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SALARY {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int testCases = Integer.parseInt(reader.readLine());
            IntStream.range(0, testCases).forEach(testCase -> {
                try {
                    int arrLen = Integer.parseInt(reader.readLine());
                    String[] numStrings = reader.readLine().split(" ");
                    int[] arr = new int[arrLen];
                    IntStream.range(0, arrLen).forEach(index -> arr[index] = Integer.parseInt(numStrings[index]));
                    System.out.println(solve(arr));
                } catch (IOException ignored) {
                }
            });
        } catch (Exception ignored) {
        }

    }

    private static int solve(int[] arr) {

        List<Integer> salaries = Arrays.stream(arr).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        int ops = 0;
        int maxSalary = salaries.get(0);
        for (int it = 1; it < salaries.size(); it++) {
            if ((ops + salaries.get(it)) != maxSalary) {
                int newMaxSalary = maxSalary + ((it - 1) * (maxSalary - (ops + salaries.get(it))));
                int opsRequired = newMaxSalary - (ops + salaries.get(it));
                maxSalary = newMaxSalary;
                ops += opsRequired;
            }
        }
        return ops;
    }
}

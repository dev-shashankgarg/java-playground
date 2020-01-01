package com.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class TACHSTCK {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            try {
                String[] input = reader.readLine().split(" ");
                int arrLen = Integer.parseInt(input[0]);
                long d = Long.parseLong(input[1]);
                int[] arr = new int[arrLen];
                IntStream.range(0, arrLen).forEach(index -> {
                    try {
                        arr[index] = Integer.parseInt(reader.readLine());
                    } catch (IOException ignored) {
                    }
                });
                System.out.println(solve(arr, arrLen, d));
            } catch (IOException ignored) {
            }
        } catch (Exception ignored) {
        }
    }

    private static long solve(int[] arr, int arrLen, long diff) {
        long answer = 0;
        if (arrLen == 1) return answer;

        List<Integer> lengths = Arrays.stream(arr).boxed().sorted().collect(Collectors.toList());

        boolean[] selected = new boolean[arrLen];
        Arrays.fill(selected, false);

        for (int it = 1; it < arrLen; it++) {
            if (!selected[it - 1]) {
                if (lengths.get(it) - lengths.get(it - 1) <= diff) {
                    selected[it] = true;
                    selected[it - 1] = true;
                    answer++;
                }
            }
        }
        return answer;
    }
}

package com.codechef.january20b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

class BRKBKS {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int testCases = Integer.parseInt(reader.readLine());
            IntStream.range(0, testCases).forEach(test -> {
                try {
                    String[] input = reader.readLine().split(" ");
                    System.out.println(solve(
                            Integer.parseInt(input[0]),
                            Integer.parseInt(input[1]),
                            Integer.parseInt(input[2]),
                            Integer.parseInt(input[3]))
                    );
                } catch (Exception ignored) {
                }
            });
        } catch (Exception ignored) {
        }
    }

    private static int solve(int s, int w1, int w2, int w3) {
        int answer = 1;
        if (s >= (w1 + w2 + w3)) return answer;

        if (w1 > w3) {
            w1 = w3;
        }

        if (s >= (w1 + w2)) return answer + 1;

        return answer + 2;
    }
}

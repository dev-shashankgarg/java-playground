package com.codechef.practice;

import java.util.Scanner;
import java.util.stream.IntStream;

class CHEALG {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            int testCases = scanner.nextInt();
            IntStream.range(0, testCases).forEach(test -> {
                System.out.println(solve(scanner.next()));
            });
        } catch (Exception e) {
        }
    }

    private static String solve(String input) {
        char current = 0;
        int charCount = 0;
        String result = "";
        for (int it = 0; it < input.length(); it++) {
            if (it == 0) {
                current = input.charAt(it);
                charCount = 1;
            } else if (input.charAt(it) == current) {
                charCount++;
            } else {
                result = result.concat("" + current).concat("" + charCount);
                current = input.charAt(it);
                charCount = 1;
            }

            if (it == input.length() - 1) {
                result = result.concat("" + current).concat("" + charCount);
            }
        }
        return (input.length() > result.length()) ? "YES" : "NO";
    }


}

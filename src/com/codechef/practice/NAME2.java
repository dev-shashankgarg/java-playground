package com.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

class NAME2 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int testCases = Integer.parseInt(reader.readLine());
            IntStream.range(0, testCases).forEach(testCase -> {
                try {
                    String[] input = reader.readLine().split(" ");
                    System.out.println(solve(input[0], input[1]));
                } catch (IOException ignored) {
                }

            });
        } catch (Exception ignored) {
        }
    }

    private static String solve(String name1, String name2) {
        return (isSubsequence(name1, name2) || isSubsequence(name2, name1)) ? "YES" : "NO";
    }

    private static boolean isSubsequence(String name1, String name2) {
        char[] matchName = name2.toCharArray();
        int matchIndex = 0;
        int matchCount = 0;
        char[] charArray = name1.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char a = charArray[i];
            for (int j = matchIndex; j < matchName.length; j++) {
                if (matchName[j] == a) {
                    matchCount++;
                    matchIndex++;
                    break;
                }
                matchIndex++;
            }
            if (matchIndex >= matchName.length && name1.length() != matchCount) return false;
        }
        return true;
    }
}

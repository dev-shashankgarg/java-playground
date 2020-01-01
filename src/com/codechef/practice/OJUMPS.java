package com.codechef.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class OJUMPS {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            long input = Long.parseLong(reader.readLine());
            System.out.println(solve(input));
        } catch (Exception ignored) {
        }
    }

    private static String solve(long input) {
        return (input % 6 == 0 || (input - 1) % 6 == 0 || (input - 3) % 6 == 0) ? "yes" : "no";
    }
}

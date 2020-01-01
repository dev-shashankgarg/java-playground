package com.codechef.practice;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

class PRIME1 {

    private static final int UPPER_LIMIT = 1000000007;
    private static boolean[] prime;

    public static void main(String[] args) {

        //generateSieve();

        try (Scanner scanner = new Scanner(System.in)) {
            int testCases = scanner.nextInt();
            IntStream.range(0, testCases).forEach(testCase -> {
                int left = scanner.nextInt();
                int right = scanner.nextInt();
                IntStream.rangeClosed(left, right).filter(PRIME1::checkPrime).forEach(System.out::println);
                System.out.println();
            });
        } catch (Exception ignored) {
        }

    }

    private static boolean checkPrime(int num) {
        if (num == 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private static void generateSieve() {
        prime = new boolean[UPPER_LIMIT + 1];
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;
        IntStream.rangeClosed(2, UPPER_LIMIT).forEach(number -> {
            if (prime[number] && number <= Math.sqrt(UPPER_LIMIT)) {
                for (long it = number * number; it <= UPPER_LIMIT; it += number) {
                    prime[(int) it] = false;
                }
            }
        });

    }
}

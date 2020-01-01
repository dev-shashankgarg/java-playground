package com.codechef.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class GUESSNUM {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            int testCases = scanner.nextInt();
            IntStream.range(0, testCases).forEach(index -> {
                long a = scanner.nextLong();
                long m = scanner.nextLong();
                List<Long> numbers = solve(a, m);
                System.out.println(numbers.size());
                numbers.forEach(num -> System.out.print(num + " "));
                System.out.println();
            });

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private static List<Long> solve(long a, long m) {
        return findFactors(m).stream().filter(factor -> (factor - 1) % a == 0 && factor - 1 != 0).map(factor -> {
            long d = m / (factor);
            long q = (factor - 1) / a;
            return d * q;
        }).sorted().collect(Collectors.toList());
    }


    private static List<Long> findFactors(long number) {
        List<Long> factors = new ArrayList<>();
        for (long i = 1; i < Math.sqrt(number); i++) {
            if (number % i == 0) {
                factors.add(i);
                if (i != number / i) {
                    factors.add(number / i);
                }
            }
        }
        return factors;
    }
}

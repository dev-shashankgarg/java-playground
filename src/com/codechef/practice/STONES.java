package com.codechef.practice;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class STONES {

  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      int testCases = scanner.nextInt();
      IntStream.rangeClosed(1, testCases).forEach(test -> {
        String a = scanner.next();
        String b = scanner.next();
        System.out.println(solve(a, b));
      });
    }
  }

  private static int solve(String a, String b) {
    Map<String, Long> aStones = Arrays.stream(a.split(""))
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    Map<String, Long> bStones = Arrays.stream(b.split(""))
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

//    return (int) aStones.keySet().stream().filter(bStones::containsKey).map(key ->
////        Math.min(aStones.get(key), bStones.get(key))
////    ).reduce(0L, Long::sum).longValue();

    return (int) Arrays.stream(b.split("")).filter(aStones.keySet()::contains).count();
  }

}

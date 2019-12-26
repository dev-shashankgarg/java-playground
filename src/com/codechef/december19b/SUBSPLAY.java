package com.codechef.december19b;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

class SUBSPLAY {

  private static String text = "";

  private static int func(String text) {

    List<Integer> characters = text.chars().boxed().collect(Collectors.toList());

    Map<Integer, List<Integer>> occurrenceList = IntStream.range(0, characters.size()).boxed()
        .collect(Collectors.groupingBy(characters::get, Collectors.mapping(
            Function.identity(), Collectors.toList())));

    List<Integer> allDuplicateAlphabets = occurrenceList.keySet().stream()
        .filter(key -> occurrenceList.get(key).size() > 1)
        .collect(Collectors.toList());

    if (allDuplicateAlphabets.size() == 0) {
      return 0;
    } else {

      Integer value1 = allDuplicateAlphabets.stream()
          .map(key -> {
            List<Integer> integers = occurrenceList.get(key);
            int diff = Integer.MAX_VALUE;
            for (int i = 1; i < integers.size(); i++) {
              if (integers.get(i) - integers.get(i - 1) < diff) {
                diff = integers.get(i) - integers.get(i - 1);
              }
            }
            return diff;
          })
          .reduce((a, b) -> (a < b) ? a : b).orElse(0);
      return text.length() - value1;
    }
  }

  public static void main(String[] args) {

    try (Scanner intScanner = new Scanner(System.in)) {
      long testCases = intScanner.nextLong();
      LongStream.range(0, testCases).forEach(index -> {
        long stringSize = intScanner.nextLong();
        text = intScanner.next();
        System.out.println(func(text));
      });
    } catch (Exception ignored) {
    }

  }

}

package com.codechef.december19b;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

class WATSCORE {

  public static void main(String[] args) {

    try (Scanner intScanner = new Scanner(System.in)) {

      int testCases = intScanner.nextInt();

      IntStream.range(0, testCases).forEach(index -> {

        int numSubmissions = intScanner.nextInt();
        Map<Integer, Integer> scoreMap = new HashMap<>();

        IntStream.range(0, numSubmissions).forEach(index1 -> {
          int probNum = intScanner.nextInt();
          int score = intScanner.nextInt();
          if (scoreMap.get(probNum) == null) {
            scoreMap.put(probNum, score);
          } else if (scoreMap.get(probNum) < score) {
            scoreMap.put(probNum, score);
          }
        });

        int totalScore = IntStream.rangeClosed(1, 8)
            .filter(scoreMap::containsKey)
            .reduce(0, (a, b) -> a + scoreMap.get(b));

        System.out.println(totalScore);

      });

    } catch (Exception ignored) {
    }

  }

}

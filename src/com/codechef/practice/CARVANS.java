package com.codechef.practice;

import java.util.Scanner;
import java.util.stream.IntStream;

class CARVANS {

  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      int testCases = scanner.nextInt();
      IntStream.rangeClosed(1, testCases).forEach(test -> {
        int arrSize = scanner.nextInt();
        int[] arr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
          arr[i] = scanner.nextInt();
        }
        System.out.println(solve(arr));

      });
    }
  }

  private static int solve(int[] arr) {
    int answer = 0;
    int current_max = Integer.MAX_VALUE;

    for (int x : arr) {
      if (x <= current_max) {
        current_max = x;
        answer++;
      }
    }
    return answer;
  }

}

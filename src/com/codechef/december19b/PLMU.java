package com.codechef.december19b;

import java.util.Scanner;
import java.util.stream.LongStream;

class PLMU {

  private static long num2s = 0;
  private static long num0s = 0;

  public static void main(String[] args) {

    try (Scanner intScanner = new Scanner(System.in)) {

      long testCases = intScanner.nextLong();

      LongStream.range(0, testCases).forEach(index -> {

        num2s = 0;
        num0s = 0;

        long arraySize = intScanner.nextLong();

        LongStream.range(0, arraySize).forEach(index1 -> {
          long value = intScanner.nextLong();
          if (value == 2) {
            increment2();
          } else if (value == 0) {
            increment0();
          }
        });

        System.out.println((num2s * ( num2s - 1)) / 2 + (num0s * (num0s - 1)) / 2);

      });

    } catch (Exception ignored) {
    }

  }

  private static void increment0() {
    num0s += 1;
  }

  private static void increment2() {
    num2s += 1;
  }

}

package com.playing.java8.exercise2;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class FibonacciTuples {

  public static void main(String[] args) {

    usingStreamIterate();
    usingStreamGenerate();

  }

  private static void usingStreamGenerate() {

    Supplier<Integer> intSupplier = new Supplier<>() {

      private int previous = 0;
      private int current = 1;

      @Override
      public Integer get() {
        int toReturn = previous;
        previous = current;
        current += toReturn;
        return toReturn;
      }
    };

    Stream.generate(intSupplier).limit(10).forEach(System.out::println);

  }

  private static void usingStreamIterate() {
    Stream.iterate(new Integer[]{0, 1}, arr -> new Integer[]{arr[1], arr[0] + arr[1]}).limit(10)
        .forEach(arr -> System.out.println("(" + arr[0] + "," + arr[1] + ")"));
  }

}

package com.playing.java8.exercise2;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PythagoreanTriplets {

  public static void main(String[] args) {

    Stream<Integer[]> triplets = IntStream.rangeClosed(1, 100).boxed()
        .flatMap(a -> IntStream.rangeClosed(a, 100).filter(b -> Math.sqrt(a * a + b * b) % 1.0 == 0)
            .mapToObj(b -> new Integer[]{a, b, (int) Math.sqrt(a * a + b * b)}));

    triplets.limit(10).forEach(
        triple -> System.out.println("(" + triple[0] + "," + triple[1] + "," + triple[2] + ")"));

  }

}

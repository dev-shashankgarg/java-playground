package com.playing.java8;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MapAndFlatMap {

  public static void main(String[] args) {
    example1();
  }

  /**
   * Given two lists of numbers, how would you return all pairs of numbers? For example, given a
   * list [1, 2, 3] and a list [3, 4] you should return [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3,
   * 4)]. For simplicity, you can represent a pair as an array with two elements.
   *
   * How would you extend the previous example to return only pairs whose sum is divisible by 3?
   */
  private static void example1() {

    List<Integer> list1 = Arrays.asList(1, 2, 3);
    List<Integer> list2 = Arrays.asList(3, 4);

    //sol1
    list1.stream().map(
        val1 -> list2.stream().map(val2 -> new Integer[]{val1, val2}).collect(Collectors.toList()))
        .flatMap(
            Collection::stream).forEach(s -> System.out.println(s[0] + "," + s[1]));

    System.out.println("------------------------");

    //sol2
    list1.stream().flatMap(val -> list2.stream().map(val2 -> new Integer[]{val, val2}))
        .forEach(s -> System.out.println(s[0] + "," + s[1]));

    System.out.println("------------------------");

    //sol1 -part2
    list1.stream().map(
        val1 -> list2.stream().map(val2 -> new Integer[]{val1, val2}).collect(Collectors.toList()))
        .flatMap(
            Collection::stream).filter(arr -> (arr[0] + arr[1]) % 3 == 0)
        .forEach(s -> System.out.println(s[0] + "," + s[1]));

    System.out.println("------------------------");

    //sol2 -part2
    list1.stream().flatMap(val -> list2.stream().map(val2 -> new Integer[]{val, val2}))
        .filter(arr -> (arr[0] + arr[1]) % 3 == 0)
        .forEach(s -> System.out.println(s[0] + "," + s[1]));

  }

}

package com.algorithms.sorting;

import java.util.Arrays;

public class SelectionSort {

  public static <T extends Number> void sort(T[] data) {

    for (int iteration = 1; iteration <= data.length; iteration++) {
      int minIndex = iteration - 1;
      T minSoFar = data[minIndex];
      for (int j = minIndex + 1; j < data.length; j++) {
        if (data[j].longValue() < minSoFar.longValue()) {
          minIndex = j;
          minSoFar = data[minIndex];
        }
      }
      swap(data, minIndex, iteration - 1);
    }
  }

  private static <T extends Number> void swap(T[] data, int j, int i) {
    T temp = data[j];
    data[j] = data[i];
    data[i] = temp;
  }

  public static void main(String[] args) {
    SelectionSort ob = new SelectionSort();
    Integer arr[] = {64, 25, 12, 22, 11};
    sort(arr);
    System.out.println("Sorted array");
    System.out.println(Arrays.toString(arr));
  }


}



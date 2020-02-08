package com.algorithms.sorting;

import java.util.Arrays;

public class BubbleSort {

  public static void sortRecursive(int[] data, int n) {
    if (n != 0) {
      int maxSoFar = 0;
      for (int i = 1; i < n; i++) {
        if (data[i] > data[maxSoFar]) {
          maxSoFar = i;
        }
      }
      swap(data, maxSoFar, n - 1);
      sortRecursive(data, n - 1);
    }
  }

  public static void sort(int[] data) {
    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < data.length - i - 1; j++) {
        if (data[j + 1] < data[j]) {
          swap(data, j + 1, j);
        }
      }
    }
  }

  private static void swap(int[] data, int i, int j) {
    if (i != j) {
      data[i] = data[i] + data[j];
      data[j] = data[i] - data[j];
      data[i] = data[i] - data[j];
    }
  }

  public static void main(String[] args) {
    BubbleSort ob = new BubbleSort();
    int arr[] = {64, 25, 12, 22, 11};
    sortRecursive(arr, arr.length);
    System.out.println("Sorted array");
    System.out.println(Arrays.toString(arr));
  }

}

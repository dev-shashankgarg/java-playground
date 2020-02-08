package com.algorithms.sorting;

import java.util.Arrays;

public class InsertionSort {

  public static void sortRecursive(int[] data, int startingIndex) {
    if (startingIndex != data.length - 1) {
      if (data[startingIndex + 1] < data[startingIndex]) {
        int toBeInserted = data[startingIndex + 1];
        int j = startingIndex;
        while (j >= 0 && data[j] > toBeInserted) {
          data[j + 1] = data[j];
          j--;
        }
        data[j + 1] = toBeInserted;
      }
      sortRecursive(data, startingIndex + 1);
    }
  }


  public static void sort(int[] data) {
    for (int i = 1; i < data.length; i++) {
      if (data[i] > data[i - 1]) {
        continue;
      }
      int toBeInserted = data[i];
      int j = i - 1;
      while (j >= 0 && data[j] > toBeInserted) {
        data[j + 1] = data[j];
        j--;
      }
      data[j + 1] = toBeInserted;
    }
  }

  public static void main(String[] args) {
    InsertionSort ob = new InsertionSort();
    int arr[] = {64, 25, 12, 22, 11};
    sortRecursive(arr, 0);
    System.out.println("Sorted array");
    System.out.println(Arrays.toString(arr));
  }

}

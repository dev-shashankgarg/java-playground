package com.algorithms.sorting;

import java.util.Arrays;

public class QuickSort {

  public static void sort(int[] data, int leftIndex, int rightIndex) {
    if (leftIndex < rightIndex) {
      int partitionIndex = partition(data, leftIndex, rightIndex);
      sort(data, leftIndex, partitionIndex - 1);
      sort(data, partitionIndex + 1, rightIndex);
    }
  }

  private static int partition(int[] data, int leftIndex, int rightIndex) {
    int pivot = data[rightIndex];
    for (int i = rightIndex - 1; i >= leftIndex; i--) {
      if (data[i] > pivot) {

      }
    }
    return pivot;
  }

  private static void swap(int[] data, int pivot, int i) {
    int temp = data[pivot];
    data[pivot] = data[i];
    data[i] = temp;
  }

  public static void main(String[] args) {
    QuickSort ob = new QuickSort();
    int[] arr = {64, 25, 12, 22, 11};
    sort(arr, 0, arr.length - 1);
    System.out.println("Sorted array");
    System.out.println(Arrays.toString(arr));
  }

}

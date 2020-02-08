package com.algorithms.sorting;

import java.util.Arrays;

public class MergeSort {

  public static void sort(int[] data, int leftIndex, int rightIndex) {

    if (leftIndex != rightIndex) {
      int middleIndex = (leftIndex + rightIndex) / 2;
      sort(data, leftIndex, middleIndex);
      sort(data, middleIndex + 1, rightIndex);

      merge(data, leftIndex, middleIndex, rightIndex);
    }

  }

  private static void merge(int[] data, int leftIndex, int middleIndex, int rightIndex) {

    int[] aux = new int[rightIndex - leftIndex + 1];

    int pointer = 0;
    int leftPointer = leftIndex;
    int rightPointer = middleIndex + 1;

    while (pointer < (rightIndex - leftIndex + 1)) {
      if (leftPointer <= middleIndex && rightPointer <= rightIndex) {
        if (data[leftPointer] <= data[rightPointer]) {
          aux[pointer] = data[leftPointer];
          leftPointer++;
        } else {
          aux[pointer] = data[rightPointer];
          rightPointer++;
        }
        pointer++;
      }

      if (leftPointer <= middleIndex) {
        aux[pointer] = data[leftPointer];
        leftPointer++;
        pointer++;
      }

      if (rightPointer <= rightIndex) {
        aux[pointer] = data[rightPointer];
        rightPointer++;
        pointer++;
      }
    }

    for (int i = 0; i < (rightIndex - leftIndex + 1); i++) {
      data[leftIndex + i] = aux[i];
    }
  }

  public static void main(String[] args) {
    MergeSort ob = new MergeSort();
    int[] arr = {64, 25, 12, 22, 11};
    sort(arr, 0, arr.length - 1);
    System.out.println("Sorted array");
    System.out.println(Arrays.toString(arr));
  }

}

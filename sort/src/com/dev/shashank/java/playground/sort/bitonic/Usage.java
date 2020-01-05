package com.dev.shashank.java.playground.sort.bitonic;

public class Usage {
    public static void main(String[] args) {
        int[] numbers = new int[]{3, 7, 4, 8, 6, 2, 1, 5};
        BitonicSort.sort(numbers, 0, (numbers.length / 2) - 1, 1);
        BitonicSort.sort(numbers, (numbers.length / 2), (numbers.length / 2) - 1, 0);
    }
}

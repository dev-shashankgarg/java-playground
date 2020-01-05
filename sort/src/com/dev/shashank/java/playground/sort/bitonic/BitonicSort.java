package com.dev.shashank.java.playground.sort.bitonic;

public class BitonicSort {

    public static void sort(int[] numbers, int left, int stepSize, int direction) {

        if (stepSize > 1) {
            int step = stepSize / 2;
            sort(numbers, left, step - 1, direction);
            sort(numbers, step, step, alt(direction));
            merge(numbers , left , stepSize ,direction);
        }

    }

    private static void merge(int[] numbers, int left, int stepSize, int direction) {

    }

    private static int alt(int direction) {
        return (direction == 1) ? 0 : 1;
    }
}

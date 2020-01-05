package com.codechef.january20b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

class ISBIAS {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] input = reader.readLine().split(" ");
            int arrLen = Integer.parseInt(input[0]);
            int queries = Integer.parseInt(input[1]);

            String[] arrStrings = reader.readLine().split(" ");
            int[] data = new int[arrLen];
            for (int i = 0; i < arrLen; i++) {
                data[i] = Integer.parseInt(arrStrings[i]);
            }

            SegmentTree tree = solve(data, arrLen);
            IntStream.range(0, queries).forEach(query -> {
                try {
                    String[] queryString = reader.readLine().split(" ");
                    SegmentTree.Node node = tree.query(Integer.parseInt(queryString[0]) - 1,
                            Integer.parseInt(queryString[1]) - 1);
                    System.out.println((node.decreasingSubsequenceCount == node.increasingSubsequenceCount) ? "YES" : "NO");
                } catch (IOException ignored) {
                }
            });
        } catch (Exception ignored) {
        }
    }

    private static SegmentTree solve(int[] data, int arrLen) {
        return new SegmentTree(data);
    }

    static class SegmentTree {

        private Node[] vertices;

        SegmentTree(int[] data) {
            vertices = new Node[4 * data.length];
            build(data, 1, 0, data.length - 1);
        }

        private void build(int[] data, int current, int left, int right) {
            if (left == right) {
                vertices[current] = create(data[left]);
            } else {
                int middle = (left + right) / 2;
                build(data, 2 * current, left, middle);
                build(data, 2 * current + 1, middle + 1, right);

                vertices[current] = combine(vertices[2 * current], vertices[2 * current + 1]);
            }
        }

        Node query(int left, int right) {
            return queryTree(1, left, right, 0, vertices.length / 4 - 1);
        }

        private Node queryTree(int current, int left, int right, int lRange, int rRange) {
            if (left == lRange && right == rRange) return vertices[current];
            int middle = (lRange + rRange) / 2;
            //GO TO LEFT TREE
            if (middle >= right) {
                return queryTree(2 * current, left, right, lRange, middle);
            }
            //GO TO RIGHT TREE
            if (middle < left) {
                return queryTree(2 * current + 1, left, right, middle + 1, rRange);
            }
            return combine(queryTree(2 * current, left, middle, lRange, middle),
                    queryTree(2 * current + 1, middle + 1, right, middle + 1, rRange));
        }

        private Node combine(Node left, Node right) {
            Node node = new Node();
            node.isSeq = true;

            // both are single elements
            if (!left.isSeq && !right.isSeq) {
                if (left.rangeSuffix[1] > right.rangePrefix[0]) {
                    node.decreasingSubsequenceCount += 1;
                } else {
                    node.increasingSubsequenceCount += 1;
                }
                node.rangePrefix[0] = left.rangeSuffix[1];
                node.rangePrefix[1] = right.rangePrefix[0];
                node.rangeSuffix[0] = node.rangePrefix[0];
                node.rangeSuffix[1] = node.rangePrefix[1];
            } else if (!left.isSeq) { // only left is single element
                if (isIncreasing(right.rangePrefix)) {

                    if (left.rangePrefix[0] < right.rangePrefix[0]) {
                        node.decreasingSubsequenceCount = right.decreasingSubsequenceCount;
                        node.increasingSubsequenceCount = right.increasingSubsequenceCount;
                    } else {
                        node.decreasingSubsequenceCount = right.decreasingSubsequenceCount + 1;
                        node.increasingSubsequenceCount = right.increasingSubsequenceCount;
                    }
                } else {

                    if (left.rangePrefix[0] < right.rangePrefix[0]) {
                        node.decreasingSubsequenceCount = right.decreasingSubsequenceCount;
                        node.increasingSubsequenceCount = right.increasingSubsequenceCount + 1;
                    } else {
                        node.decreasingSubsequenceCount = right.decreasingSubsequenceCount;
                        node.increasingSubsequenceCount = right.increasingSubsequenceCount;
                    }
                }

                node.rangePrefix[1] = right.rangePrefix[0];
                node.rangePrefix[0] = left.rangePrefix[0];

                node.rangeSuffix[0] = right.rangeSuffix[0];
                node.rangeSuffix[1] = right.rangeSuffix[1];

            } else if (!right.isSeq) { // only right is single element

                if (isIncreasing(left.rangeSuffix)) {
                    if (right.rangePrefix[0] > left.rangeSuffix[1]) {
                        node.decreasingSubsequenceCount = left.decreasingSubsequenceCount;
                        node.increasingSubsequenceCount = left.increasingSubsequenceCount;
                    } else {
                        node.decreasingSubsequenceCount = left.decreasingSubsequenceCount + 1;
                        node.increasingSubsequenceCount = left.increasingSubsequenceCount;
                    }

                } else {
                    if (right.rangePrefix[0] > left.rangeSuffix[1]) {
                        node.decreasingSubsequenceCount = left.decreasingSubsequenceCount;
                        node.increasingSubsequenceCount = left.increasingSubsequenceCount + 1;
                    } else {
                        node.decreasingSubsequenceCount = left.decreasingSubsequenceCount;
                        node.increasingSubsequenceCount = left.increasingSubsequenceCount;
                    }
                }

                node.rangePrefix[0] = left.rangePrefix[0];
                node.rangePrefix[1] = left.rangePrefix[1];

                node.rangeSuffix[0] = left.rangeSuffix[1];
                node.rangeSuffix[1] = right.rangePrefix[0];

            } else { // both are sequences

                if (isIncreasing(left.rangeSuffix) && isIncreasing(right.rangePrefix)) {

                    if (right.rangePrefix[0] > left.rangeSuffix[1]) {
                        node.decreasingSubsequenceCount = left.decreasingSubsequenceCount + right.decreasingSubsequenceCount;
                        node.increasingSubsequenceCount = left.increasingSubsequenceCount + right.increasingSubsequenceCount - 1;
                    } else {
                        node.decreasingSubsequenceCount = left.decreasingSubsequenceCount + right.decreasingSubsequenceCount + 1;
                        node.increasingSubsequenceCount = left.increasingSubsequenceCount + right.increasingSubsequenceCount;
                    }
                } else if (isIncreasing(left.rangeSuffix) && !isIncreasing(right.rangePrefix)) {
                    node.decreasingSubsequenceCount = left.decreasingSubsequenceCount + right.decreasingSubsequenceCount;
                    node.increasingSubsequenceCount = left.increasingSubsequenceCount + right.increasingSubsequenceCount;
                } else if (!isIncreasing(left.rangeSuffix) && isIncreasing(right.rangePrefix)) {
                    node.decreasingSubsequenceCount = left.decreasingSubsequenceCount + right.decreasingSubsequenceCount;
                    node.increasingSubsequenceCount = left.increasingSubsequenceCount + right.increasingSubsequenceCount;
                } else if (!isIncreasing(left.rangeSuffix) && !isIncreasing(right.rangePrefix)) {
                    if (right.rangePrefix[0] < left.rangeSuffix[1]) {
                        node.decreasingSubsequenceCount = left.decreasingSubsequenceCount + right.decreasingSubsequenceCount - 1;
                        node.increasingSubsequenceCount = left.increasingSubsequenceCount + right.increasingSubsequenceCount;
                    } else {
                        node.decreasingSubsequenceCount = left.decreasingSubsequenceCount + right.decreasingSubsequenceCount;
                        node.increasingSubsequenceCount = left.increasingSubsequenceCount + right.increasingSubsequenceCount + 1;
                    }
                }

                node.rangePrefix[0] = left.rangePrefix[0];
                node.rangePrefix[1] = left.rangePrefix[1];

                node.rangeSuffix[0] = right.rangeSuffix[0];
                node.rangeSuffix[1] = right.rangeSuffix[1];

            }

            return node;
        }

        private boolean isIncreasing(int[] arr) {
            return arr[1] > arr[0];
        }

        private Node create(int value) {
            Node node = new Node();

            node.isSeq = false;
            node.decreasingSubsequenceCount = 0;
            node.increasingSubsequenceCount = 0;
            node.rangePrefix[0] = value;
            node.rangeSuffix[1] = value;

            return node;
        }

        static class Node {
            boolean isSeq;
            int increasingSubsequenceCount;
            int decreasingSubsequenceCount;
            int[] rangePrefix = new int[2];
            int[] rangeSuffix = new int[2];
        }
    }
}

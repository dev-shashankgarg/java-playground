package com.datastructure.segmenttree;

public class SegmentTree {

  private int[] vertices;

  public SegmentTree(int[] data) {
    vertices = new int[4 * data.length];
    build(data, 1, 0, data.length - 1);

  }

  private void build(int[] data, int currentIndex, int start, int end) {

    if (start == end) {
      vertices[currentIndex] = data[start];
    } else {
      int midIndex = (start + end) / 2;

      build(data, 2 * currentIndex, start, midIndex);
      build(data, 2 * currentIndex + 1, midIndex + 1, end);

      vertices[currentIndex] = vertices[2 * currentIndex] + vertices[2 * currentIndex + 1];
    }
  }

  public int findSum(int leftIndex, int rightIndex) {
    return sum(leftIndex, rightIndex, 1, 0, vertices.length / 4 - 1);
  }

  private int sum(int leftIndex, int rightIndex, int currentIndex, int l, int r) {

    if (leftIndex > rightIndex) {
      return 0;
    }
    if (leftIndex == l && rightIndex == r) {
      return vertices[currentIndex];
    }
    int midIndex = (l + r) / 2;

    return sum(leftIndex, Math.min(rightIndex, midIndex), 2 * currentIndex, l, midIndex) + sum(
        Math.max(midIndex + 1, leftIndex), rightIndex, 2 * currentIndex + 1, midIndex + 1, r);
  }


  public void update(int position, int value) {
    updateTree(position, value, 1, 0, vertices.length / 4 - 1);
  }

  private void updateTree(int position, int value, int currentIndex, int leftIndex,
      int rightIndex) {
    if (leftIndex == rightIndex) {
      vertices[currentIndex] = value;
    } else {
      int midIndex = (leftIndex + rightIndex) / 2;
      if (position <= midIndex) {
        updateTree(position, value, 2 * currentIndex, leftIndex, midIndex);
      } else {
        updateTree(position, value, 2 * currentIndex + 1, midIndex + 1, rightIndex);
      }
      vertices[currentIndex] = vertices[2 * currentIndex] + vertices[2 * currentIndex + 1];
    }
  }


}

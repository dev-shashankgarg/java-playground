package com.datastructure.segmenttree;

public class RangeAvgSegmentTree {

  int[] vertices;

  public RangeAvgSegmentTree(int[] data) {
    vertices = new int[data.length * 4];
    build(data, 1, 0, data.length - 1);
  }

  private void build(int[] data, int currentIndex, int left, int right) {
    if (left == right) {
      vertices[currentIndex] = data[left];
    } else {
      int middle = (left + right) / 2;
      build(data, 2 * currentIndex, left, middle);
      build(data, 2 * currentIndex + 1, middle + 1, right);

      vertices[currentIndex] = vertices[2 * currentIndex] + vertices[2 * currentIndex + 1];
    }
  }

  public int query(int left, int right) {
    return queryTree(1, left, right, 0, vertices.length / 4 - 1);
  }

  public void update(int index, int value) {
    updateTree(index, value, 1, 0, vertices.length / 4 - 1);
  }

  private int queryTree(int current, int left, int right, int lLimit, int rLimit) {

    if (left > right) {
      return 0;
    }

    if (left == lLimit && right == rLimit) {
      return vertices[current];
    }

    int middleIndex = (lLimit + rLimit) / 2;

    return queryTree(2 * current, left, Math.min(right, middleIndex), lLimit, middleIndex) +
        queryTree(2 * current + 1, Math.max(middleIndex + 1, left), right, middleIndex + 1, rLimit);
  }

  private void updateTree(int index, int value, int currentIndex, int lLimit, int rLimit) {

    if (index == lLimit && index == rLimit) {
      vertices[currentIndex] = value;
    }
    int midIndex = (lLimit + rLimit) / 2;

    if (index <= midIndex) {
      updateTree(index, value, 2 * currentIndex, lLimit, midIndex);
    } else {
      updateTree(index, value, 2 * currentIndex + 1, midIndex + 1, rLimit);
    }

    vertices[currentIndex] = vertices[2 * currentIndex] + vertices[2 * currentIndex + 1];

  }


}

package com.datastructure.segmenttree.query;

import java.util.Scanner;

/**
 * https://codeforces.com/contest/474/problem/F
 */
public class AntColony {

  public static void main(String[] args) {

    try (Scanner intScanner = new Scanner(System.in)) {

      int len = intScanner.nextInt();
      int[] data = new int[len];

      for (int i = 0; i < len; i++) {
        data[i] = intScanner.nextInt();
      }

      SegmentTree st = new SegmentTree(data);

      int queryLen = intScanner.nextInt();

      for (int i = 0; i < queryLen; i++) {
        int left = intScanner.nextInt();
        int right = intScanner.nextInt();
        System.out.println(st.query(left - 1, right - 1));
      }


    } catch (Exception ignored) {
    }

  }

  static class SegmentTree {

    private Node[] vertices;

    SegmentTree(int[] data) {
      this.vertices = new Node[4 * data.length];
      build(data, 1, 0, data.length - 1);
    }

    public int query(int left, int right) {
      Node result = queryTree(1, 0, vertices.length / 4 - 1, left, right);

      return right - left + 1 - result.getGcdMinCount();
    }

    private Node queryTree(int current, int left, int right, int l, int r) {

      if (left == l && right == r) {
        return vertices[current];
      }

      int middle = (left + right) / 2;

      if (l <= middle && r <= middle) {
        return queryTree(2 * current, left, middle, l, r);
      }
      if (l >= middle + 1 && r >= middle + 1) {
        return queryTree(2 * current + 1, middle + 1, right, l, r);
      }
      return combine(queryTree(2 * current, left, middle, l, Math.min(r, middle)),
          queryTree(2 * current + 1, middle + 1, right, Math.max(l, middle + 1), r));
    }

    private void build(int[] data, int current, int left, int right) {

      if (left == right) {
        vertices[current] = createNode(data[left]);
      } else {
        int middle = (left + right) / 2;
        build(data, 2 * current, left, middle);
        build(data, 2 * current + 1, middle + 1, right);
        vertices[current] = combine(vertices[2 * current], vertices[2 * current + 1]);
      }
    }

    private Node createNode(int value) {
      return new Node(value, value, 1);
    }

    private Node combine(Node left, Node right) {

      int min = Math.min(left.getMin(), right.getMin());
      int gcd = findGcd(left.getGcd(), right.getGcd());
      int gcdMinCount = 0;
      if (gcd == min) {
        if (min == left.getMin()) {
          gcdMinCount += left.getGcdMinCount();
        }
        if (min == right.getMin()) {
          gcdMinCount += right.getGcdMinCount();
        }
      }
      return new Node(gcd, min, gcdMinCount);
    }

    private int findGcd(int a, int b) {
      if (a == 0) {
        return b;
      }
      return findGcd(b % a, a);
    }
  }

  static class Node {

    private int gcd;
    private int min;
    private int gcdMinCount;

    Node(int gcd, int min, int gcdMinCount) {
      this.gcd = gcd;
      this.min = min;
      this.gcdMinCount = gcdMinCount;
    }

    int getGcd() {
      return gcd;
    }

    int getMin() {
      return min;
    }

    int getGcdMinCount() {
      return gcdMinCount;
    }
  }


}



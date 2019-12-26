package com.datastructure.segmenttree.query;

import com.datastructure.segmenttree.SegmentTree;

public class RangeSumQuery {

  public static void main(String[] args) {

    int[] a = new int[]{1, 3, -2, 8, -7};

    SegmentTree st = new SegmentTree(a);
    System.out.println(st.findSum(0, 4));

    st.update(2,10);

    System.out.println(st.findSum(2, 2));

  }

}

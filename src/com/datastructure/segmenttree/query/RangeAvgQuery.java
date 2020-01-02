package com.datastructure.segmenttree.query;

import com.datastructure.segmenttree.RangeAvgSegmentTree;
import com.datastructure.segmenttree.SegmentTree;

public class RangeAvgQuery {

  public static void main(String[] args) {
    int[] a = new int[]{1, 3, -2, 8, -7};

    RangeAvgSegmentTree st = new RangeAvgSegmentTree(a);
    System.out.println(st.query(0, 4));

    //st.update(2,10);

    System.out.println(st.query(2, 3));
  }

}

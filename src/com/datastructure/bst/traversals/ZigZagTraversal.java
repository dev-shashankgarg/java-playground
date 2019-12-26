package com.datastructure.bst.traversals;

import com.datastructure.bst.BinarySearchTree;

public class ZigZagTraversal {

  public static void main(String[] args) {

    BinarySearchTree bst = new BinarySearchTree();
    bst.add(6);
    bst.add(4);
    bst.add(8);
    bst.add(3);
    bst.add(5);
    bst.add(7);
    bst.add(9);

    bst.prettyPrint();

    bst.zigZagTraversal();

  }

}

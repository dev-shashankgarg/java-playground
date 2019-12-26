package com.datastructure.bst.search;

import com.datastructure.bst.BinarySearchTree;
import com.datastructure.bst.Node;

public class LeastCommonAncestor {

  public static void main(String[] args) {

    BinarySearchTree bst1 = new BinarySearchTree();
    bst1.add(50);

    bst1.add(25);
    bst1.add(75);

    bst1.add(12);
    bst1.add(37);
    bst1.add(62);
    bst1.add(87);

    bst1.add(6);
    bst1.add(18);
    bst1.add(30);
    bst1.add(42);
    bst1.add(55);
    bst1.add(67);
    bst1.add(82);
    bst1.add(92);

    bst1.add(4);
    bst1.add(8);
    bst1.add(16);
    bst1.add(20);
    bst1.add(28);
    bst1.add(32);
    bst1.add(40);
    bst1.add(44);
    bst1.add(53);
    bst1.add(57);
    bst1.add(65);
    bst1.add(69);
    bst1.add(80);
    bst1.add(84);
    bst1.add(90);
    bst1.add(94);

    bst1.prettyPrint();

    int a = 53;
    int b = 57;
    Node node = bst1.leastCommonAncestor(a, b);
    if (node != null) {
      System.out.println(
          "Least Common Ancestor (LCA) of : " + a + ", " + b + " is -> " + node.getValue());
    }

     a = 53;
     b = 69;
     node = bst1.leastCommonAncestor(a, b);
    if (node != null) {
      System.out.println(
          "Least Common Ancestor (LCA) of : " + a + ", " + b + " is -> " + node.getValue());
    }

    a = 53;
    b = 87;
    node = bst1.leastCommonAncestor(a, b);
    if (node != null) {
      System.out.println(
          "Least Common Ancestor (LCA) of : " + a + ", " + b + " is -> " + node.getValue());
    }

    a = 94;
    b = 8;
    node = bst1.leastCommonAncestor(a, b);
    if (node != null) {
      System.out.println(
          "Least Common Ancestor (LCA) of : " + a + ", " + b + " is -> " + node.getValue());
    }

    a = 42;
    b = 37;
    node = bst1.leastCommonAncestor(a, b);
    if (node != null) {
      System.out.println(
          "Least Common Ancestor (LCA) of : " + a + ", " + b + " is -> " + node.getValue());
    }


  }

}

package com.datastructure.avl.travesral;

import com.datastructure.avl.AvlTree;

public class LevelTraversal {

  public static void main(String[] args) {

    AvlTree avlTree = new AvlTree();

    avlTree.insert(12);
    avlTree.insert(8);
    avlTree.insert(18);

    avlTree.insert(5);
    avlTree.insert(11);
    avlTree.insert(17);
    avlTree.insert(4);
    avlTree.insert(7);
//
   avlTree.insert(2);
   avlTree.insert(1);
   avlTree.insert(6);

    avlTree.prettyPrint();


//    avlTree.delete(11);
//
//
//    avlTree.prettyPrint();


  }

}

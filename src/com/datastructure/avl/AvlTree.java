package com.datastructure.avl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class AvlTree {

  private String[] prevArray = null;
  private Node root;

  public void insert(int value) {
    root = insertNode(root, value);
  }

  private Node insertNode(Node root, int value) {

    if (root == null) {
      root = new Node();
      root.setValue(value);
    } else if (value < root.getValue()) {
      root.setLeftNode(insertNode(root.getLeftNode(), value));
    } else if (value > root.getValue()) {
      root.setRightNode(insertNode(root.getRightNode(), value));
    } else {
      return root; // duplicate key insertion
    }

    int balance = checkBalance(root);

    if (balance > 1 && value < root.getLeftNode().getValue()) {
      root = rightRotate(root);
    }

    if (balance > 1 && value > root.getLeftNode().getValue()) {
      Node rotatedChild = leftRotate(root.getLeftNode());

      root.setLeftNode(rotatedChild);
      root = rightRotate(root);
    }

    if (balance < -1 && value < root.getRightNode().getValue()) {
      Node rotatedChild = rightRotate(root.getRightNode());

      root.setRightNode(rotatedChild);
      root = leftRotate(root);
    }

    if (balance < -1 && value > root.getRightNode().getValue()) {
      root = leftRotate(root);
    }

    root.setHeight(1 + Math.max(getHeight(root.getLeftNode()), getHeight(root.getRightNode())));
    return root;
  }

  public void delete(int value) {
    root = deleteNode(root, value);
  }

  private Node deleteNode(Node node, int value) {
    if (node == null) {
      return null;
    }
    if (node.getValue() == value) {
      if (node.getRightNode() == null && node.getLeftNode() == null) {
        node = null;
      } else if (node.getRightNode() == null) {
        node = node.getLeftNode();
      } else if (node.getLeftNode() == null) {
        node = node.getRightNode();
      } else {
        Node smallestNextChild = findSmallestValue(node.getRightNode());
        node.setValue(smallestNextChild.getValue());
        node.setRightNode(deleteNode(node.getRightNode(), smallestNextChild.getValue()));
      }
    } else if (node.getValue() > value) {
      node.setLeftNode(deleteNode(node.getLeftNode(), value));
    } else {
      node.setRightNode(deleteNode(node.getRightNode(), value));
    }

    //balance node;
    if (node != null) {

      int balance = getHeight(node.getLeftNode()) - getHeight(node.getRightNode());

      //left left
      if (balance > 1 && checkBalance(node.getLeftNode()) >= 0) {
        node = rightRotate(node);
      }
      // left right
      if (balance > 1 && checkBalance(node.getLeftNode()) < 0) {
        node.setLeftNode(leftRotate(node.getLeftNode()));
        node = rightRotate(node);
      }
      //right right
      if (balance < -1 && checkBalance(node.getRightNode()) <= 0) {
        node = leftRotate(node);
      }
      // right left
      if (balance < -1 && checkBalance(node.getRightNode()) > 0) {
        node.setRightNode(rightRotate(node.getRightNode()));
        node = leftRotate(node);
      }

      node.setHeight(1 + Math.max(getHeight(node.getLeftNode()), getHeight(node.getRightNode())));
    }
    return node;
  }

  private Node findSmallestValue(Node node) {
    if (node.getLeftNode() == null) {
      return node;
    } else {
      return findSmallestValue(node.getLeftNode());
    }
  }

  private Node leftRotate(Node node) {
    Node rightChild = node.getRightNode();
    Node rightChildLeftTree = rightChild.getLeftNode();

    rightChild.setLeftNode(node);
    node.setRightNode(rightChildLeftTree);

    return rightChild;
  }

  private Node rightRotate(Node node) {
    Node leftChild = node.getLeftNode();
    Node leftChildRightTree = leftChild.getRightNode();

    leftChild.setRightNode(node);
    node.setLeftNode(leftChildRightTree);

    return leftChild;
  }

  private int checkBalance(Node root) {
    int leftHeight = (root.getLeftNode() == null) ? 0 : root.getLeftNode().getHeight();
    int rightHeight = (root.getRightNode() == null) ? 0 : root.getRightNode().getHeight();

    return leftHeight - rightHeight;
  }

  private int getHeight(Node node) {
    if (node == null) {
      return 0;
    } else {
      return 1 + Math.max(getHeight(node.getLeftNode()), getHeight(node.getRightNode()));
    }
  }

  /**
   * Below is helper functions for pretty printing a tree. Nothing AVL specific
   */
  public void prettyPrint() {

    int height = calculateHeight(root);
    int arraySize = (int) Math.pow(2, height) + 1;

    for (int i = 0; i < height; i++) {
      List<Node> levelNodes = new ArrayList<>();
      getTreeLevel(levelNodes, 1, root, i + 1);

      String[] a = new String[arraySize];
      Arrays.fill(a, "\t");
      if (i == 0) {
        System.out.println(levelNodes.get(0).getValue() + "->" + levelNodes.get(0).getHeight());
        a[arraySize / 2] = pad("" + levelNodes.get(0).getValue());
        printLine(a);
        // System.out.println(Arrays.toString(a));
        prevArray = Arrays.copyOf(a, a.length);
      } else {
        String[] slashes = new String[arraySize];
        String[] values = new String[arraySize];

        Arrays.fill(slashes, "\t");
        Arrays.fill(values, "\t");

        for (Node node : levelNodes) {
          int parentValue = findParentValue(null, root, node.getValue());
          int parentIndex = findIndexForParent(pad("" + parentValue), prevArray);

          if (node.getValue() < parentValue) {
            slashes[parentIndex - (int) Math.pow(2, height - i - 1)] = ("/\t");
            values[parentIndex - (int) Math.pow(2, height - i - 1)] = pad(
                "" + node.getValue());
            System.out.println(node.getValue() + "->" + node.getHeight());

          } else {
            slashes[parentIndex + (int) Math.pow(2, height - i - 1)] = ("\\\t");
            values[parentIndex + (int) Math.pow(2, height - i - 1)] = pad(
                "" + node.getValue());
            System.out.println(node.getValue() + "->" + node.getHeight());
          }

        }

        printLine(slashes);
        printLine(values);

        //System.out.println(Arrays.toString(slashes));
        //System.out.println(Arrays.toString(values));

        prevArray = Arrays.copyOf(values, values.length);
      }
    }

  }

  private String pad(String s) {
    if (s.length() == 1) {
      return s + " ";
    }
    return s;
  }

  private void printLine(String[] a) {
    for (int i = 0; i < a.length; i++) {
      System.out.print(a[i]);
    }
    System.out.println("");
  }

  private int findIndexForParent(String val, String[] a) {
    return IntStream.range(0, a.length).filter(index -> a[index].equals(val)).findFirst()
        .orElse(-1);
  }

  private void getTreeLevel(List<Node> levelNodes, int currentLevel, Node currentNode, int level) {
    if (currentNode == null || currentLevel > level) {

    } else if (level > currentLevel) {
      getTreeLevel(levelNodes, currentLevel + 1, currentNode.getLeftNode(), level);
      getTreeLevel(levelNodes, currentLevel + 1, currentNode.getRightNode(), level);
    } else {
      levelNodes.add(currentNode);
    }
  }

  private int findParentValue(Node parent, Node root, int childValue) {
    if (root == null) {
      return Integer.MIN_VALUE;
    }
    if (childValue == root.getValue()) {
      return parent.getValue();
    }
    if (childValue < root.getValue()) {
      return findParentValue(root, root.getLeftNode(), childValue);
    } else {
      return findParentValue(root, root.getRightNode(), childValue);
    }
  }

  private int calculateHeight(Node root) {
    return (root == null) ? 0 : Math
        .max(1 + calculateHeight(root.getLeftNode()), 1 + calculateHeight(root.getRightNode()));
  }


}

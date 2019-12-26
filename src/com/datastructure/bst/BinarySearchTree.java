package com.datastructure.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class BinarySearchTree {

  private String[] prevArray = null;
  private Node root;

  public Node getRoot() {
    return root;
  }

  public void add(Integer value) {
    root = addNode(root, value);
  }

  private Node addNode(Node current, Integer value) {
    if (current == null) {
      current = new Node(value);
    } else {
      if (value < current.getValue()) {
        current.setLeftNode(addNode(current.getLeftNode(), value));
      } else if (value > current.getValue()) {
        current.setRightNode(addNode(current.getRightNode(), value));
      }
    }
    return current;
  }

  public Boolean contains(Integer value) {
    return containsNode(root, value);
  }

  private Boolean containsNode(Node current, Integer value) {
    if (current == null) {
      return false;
    } else {
      if (value.equals(current.getValue())) {
        return true;
      }
      if (value < current.getValue()) {
        return containsNode(current.getLeftNode(), value);
      } else {
        return containsNode(current.getRightNode(), value);
      }
    }
  }

  public void delete(Integer value) {
    root = deleteNode(root, value);
  }

  private Node deleteNode(Node current, Integer value) {
    if (current != null) {
      if (value.equals(current.getValue())) {
        if (current.getLeftNode() == null && current.getRightNode() == null) {
          return null;
        } else if (current.getLeftNode() == null) {
          return current.getRightNode();
        } else if (current.getRightNode() == null) {
          return current.getLeftNode();
        } else {
          Node smallestNode = findSmallestNode(current.getRightNode());
          current.setValue(smallestNode.getValue());
          deleteNode(current.getRightNode(), smallestNode.getValue());
          return current;
        }
      } else if (value < current.getValue()) {
        current.setLeftNode(deleteNode(current.getLeftNode(), value));
      } else {
        current.setRightNode(deleteNode(current.getRightNode(), value));
      }
    }
    return current;
  }

  private Node findSmallestNode(Node rightNode) {

    if (rightNode == null) {
      return null;
    }
    return (rightNode.getLeftNode() == null) ? rightNode
        : findSmallestNode(rightNode.getLeftNode());

  }

  public void postOrderTraversal(Node root) {
    if (root != null) {
      postOrderTraversal(root.getLeftNode());
      postOrderTraversal(root.getRightNode());
      System.out.print(root.getValue());
    }
  }

  public void inOrderTraversal(Node root) {
    if (root != null) {
      inOrderTraversal(root.getLeftNode());
      System.out.print(root.getValue());
      inOrderTraversal(root.getRightNode());
    }
  }

  public void preOrderTraversal(Node root) {
    if (root != null) {
      System.out.print(root.getValue());
      preOrderTraversal(root.getLeftNode());
      preOrderTraversal(root.getRightNode());
    }
  }

  public void prettyPrint() {

    int height = calculateHeight(root);
    int arraySize = (int) Math.pow(2, height) + 1;

    for (int i = 0; i < height; i++) {
      List<Node> levelNodes = new ArrayList<>();
      getTreeLevel(levelNodes, 1, root, i + 1);

      String[] a = new String[arraySize];
      Arrays.fill(a, "\t");
      if (i == 0) {
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
          } else {
            slashes[parentIndex + (int) Math.pow(2, height - i - 1)] = ("\\\t");
            values[parentIndex + (int) Math.pow(2, height - i - 1)] = pad(
                "" + node.getValue());
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

  public Node leastCommonAncestor(int a, int b) {
    return evaluateLCA(root, null, a, b);
  }

  private Node evaluateLCA(Node root, Node parent, int a, int b) {

    if (root == null) {
      return null;
    }

    if (root.getValue().equals(a) || root.getValue().equals(b)) {
      return parent;
    }

    Node leftNode = evaluateLCA(root.getLeftNode(), root, a, b);
    Node rightNode = evaluateLCA(root.getRightNode(), root, a, b);

    if (leftNode != null && rightNode != null) {
      return root;
    }

    if (leftNode == null && rightNode == null) {
      return null;
    }

    if (leftNode == null) {
      return rightNode;
    }
    return leftNode;
  }

  public void boundaryTraversal() {
    if (root != null) {
      System.out.println("Boundary level traversal is: ");

      printBoundaryLeft(root);
      printBoundaryLeaf(root);
      if (root.getRightNode() != null) {
        printBoundaryRight(root.getRightNode());
      }
    }
  }

  private void printBoundaryLeaf(Node root) {
    if (root == null) {
      return;
    }

    if (root.getRightNode() == null && root.getLeftNode() == null) {
      System.out.print(root.getValue() + " ");
    }
    printBoundaryLeaf(root.getLeftNode());
    printBoundaryLeaf(root.getRightNode());
  }

  private void printBoundaryRight(Node root) {
    if (root == null) {
      return;
    }
    if (root.getRightNode() != null || root.getLeftNode() != null) {
      printBoundaryRight(root.getRightNode());
      System.out.print(root.getValue() + " ");
    }
  }

  private void printBoundaryLeft(Node root) {
    if (root == null) {
      return;
    }
    if (root.getLeftNode() != null || root.getRightNode() != null) {
      System.out.print(root.getValue() + " ");
      printBoundaryLeft(root.getLeftNode());
    }
  }

  public void zigZagTraversal() {
    if (root != null) {
      System.out.println("ZigZag traversal is: ");

      int height = calculateHeight(root);
      for (int i = 1; i <= height; i++) {
        printZigZag(root, 1, i, i % 2);
        System.out.println("");
      }


    }
  }

  private void printZigZag(Node root, int currentLevel, int level, int mode) {

    if (root == null || currentLevel > level) {
      return;
    }

    if (currentLevel == level) {
      System.out.print(root.getValue() + " ");
    }

    if (mode == 0) {
      printZigZag(root.getRightNode(), currentLevel + 1, level, mode);
      printZigZag(root.getLeftNode(), currentLevel + 1, level, mode);
    } else {
      printZigZag(root.getLeftNode(), currentLevel + 1, level, mode);
      printZigZag(root.getRightNode(), currentLevel + 1, level, mode);
    }

  }
}

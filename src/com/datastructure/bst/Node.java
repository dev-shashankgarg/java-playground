package com.datastructure.bst;

import java.util.StringJoiner;

public class Node {

  private Integer value;
  private Node leftNode;
  private Node rightNode;

  public Node(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }

  public Node getLeftNode() {
    return leftNode;
  }

  public Node getRightNode() {
    return rightNode;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public void setLeftNode(Node leftNode) {
    this.leftNode = leftNode;
  }

  public void setRightNode(Node rightNode) {
    this.rightNode = rightNode;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
        .add("value=" + value)
        .toString();
  }
}

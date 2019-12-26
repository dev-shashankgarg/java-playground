package com.datastructure.avl;

import java.util.StringJoiner;

public class Node {

  private Integer value;
  private Integer height;
  private Node leftNode;
  private Node rightNode;

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public Node getLeftNode() {
    return leftNode;
  }

  public void setLeftNode(Node leftNode) {
    this.leftNode = leftNode;
  }

  public Node getRightNode() {
    return rightNode;
  }

  public void setRightNode(Node rightNode) {
    this.rightNode = rightNode;
  }

  @Override
  public String  toString() {
    return new StringJoiner(", ", Node.class.getSimpleName() + "[", "]")
        .add("value=" + value)
        .add("height=" + height)
        .toString();
  }
}

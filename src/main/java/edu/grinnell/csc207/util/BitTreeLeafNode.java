package edu.grinnell.csc207.util;

/**
 * This class represents a leaf node of a bit tree, i.e. a node
 * with no children and with a value.
 *
 * @author David William Stroud
 */
public class BitTreeLeafNode implements BitTreeNode {
  /**
   * The value stored by this leaf node.
   */
  String value;

  /**
   * Create a leaf node that stores the given value.
   * @param storedValue The value to store.
   */
  public BitTreeLeafNode(String storedValue) {
    this.value = storedValue;
  } // BitTreeLeafNode(String)

  /**
   * Returns the value stored by this node.
   * @return The value stored by this node.
   */
  public String getValue() {
    return this.value;
  } // getValue()

  /**
   * Returns this node.
   * @return This node, but as a "BitTreeLeafNode" value.
   */
  @Override
  public BitTreeLeafNode toLeafNode() {
    return this;
  } // toLeafNode()

  /**
   * Returns null.
   * @return Null.
   */
  @Override
  public BitTreeParentNode toParentNode() {
    return null;
  } // toParentNode()
} // class BitTreeLeafNode

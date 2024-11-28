package edu.grinnell.csc207.util;

/**
 * This class represents a bit tree parent node, i.e. a node
 * with children and no value.
 *
 * @author David William Stroud
 */
public class BitTreeParentNode implements BitTreeNode {
  /**
   * The child of this node used when the next bit is "0".
   */
  BitTreeNode zero;
  /**
   * The child of this node used when the next bit is "1".
   */
  BitTreeNode one;

  /**
   * Sets the child representing the zero bit to the given node.
   *
   * @param child The node that should represent the zero bit.
   */
  public void setZero(BitTreeNode child) {
    this.zero = child;
  } // setZero()

  /**
   * Sets the child representing the one bit to the given node.
   *
   * @param child The node that should represent the one bit.
   */
  public void setOne(BitTreeNode child) {
    this.one = child;
  } // setOne()

  /**
   * Gets the node used when the next bit is "0".
   * @return The node used when the next bit is "0".
   */
  public BitTreeNode getZero() {
    return this.zero;
  } // getZero()

  /**
   * Gets the node used when the next bit is "1".
   * @return The node used when the next bit is "1".
   */
  public BitTreeNode getOne() {
    return this.one;
  } // getOne()

  /**
   * Returns null.
   * @return Null.
   */
  public BitTreeLeafNode toLeafNode() {
    return null;
  } // toLeafNode()

  /**
   * Returns this node.
   * @return This node, but as a "BitTreeParentNode" value.
   */
  public BitTreeParentNode toParentNode() {
    return this;
  } // toParentNode()
} // class BitTreeParentNode

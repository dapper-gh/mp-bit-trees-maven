package edu.grinnell.csc207.util;

/**
 * This interface serves as a marker for a class, indicating that it is a bit tree node.
 * At least one of the methods in this interface must return something other than "null".
 *
 * @author David William Stroud
 */
public interface BitTreeNode {
  /**
   * Returns this node as a leaf node, if possible.
   * @return A leaf node representing this node, or null if this node is not a leaf node.
   */
  BitTreeLeafNode toLeafNode();

  /**
   * Returns this node as a parent node, if possible.
   * @return A parent node representing this node, or null if this node is not a parent node.
   */
  BitTreeParentNode toParentNode();
} // interface BitTreeNode

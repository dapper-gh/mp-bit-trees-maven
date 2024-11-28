package edu.grinnell.csc207.util;

import java.io.*;

/**
 * Trees intended to be used in storing mappings between fixed-length 
 * sequences of bits and corresponding values.
 *
 * @author David William Stroud
 */
public class BitTree {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The root node of the bit tree. Its children represent the first bit
   * of the key.
   */
  BitTreeNode root = new BitTreeParentNode();
  /**
   * The size of the bit strings used as keys.
   */
  int bitLength;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Creates a bit tree that handles bit strings of size n.
   *
   * @param n The size of the bit strings used as keys.
   */
  public BitTree(int n) {
    this.bitLength = n;
  } // BitTree(int)

  // +---------------+-----------------------------------------------
  // | Local helpers |
  // +---------------+

  /**
   * Dumps the given node to a PrintWriter.
   *
   * @param pen The PrintWriter to which to dump.
   * @param prefix The prefix to use for bit strings from this node.
   * @param node The node to dump.
   */
  static void dumpRecursively(
      PrintWriter pen,
      String prefix,
      BitTreeNode node
  ) {
    BitTreeLeafNode leaf = node.toLeafNode();
    if (leaf != null) {
      pen.printf("%s,%s\n", prefix, leaf.getValue());
    } // if

    BitTreeParentNode parent = node.toParentNode();
    if (parent != null) {
      BitTreeNode zero = parent.getZero();
      if (zero != null) {
        BitTree.dumpRecursively(
            pen,
            prefix + "0",
            zero
        );
      } // if

      BitTreeNode one = parent.getOne();
      if (one != null) {
        BitTree.dumpRecursively(
            pen,
            prefix + "1",
            one
        );
      } // if
    } // if
  } // dumpRecursively(PrintWriter, String, BitTreeNode)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sets the given bit string's associated value to the given value.
   *
   * @param bits The bit string whose value should be set.
   * @param value The value to use when setting.
   *
   * @throws IndexOutOfBoundsException
   *   If the bit string is not valid, either due to length or character content.
   */
  public void set(String bits, String value) throws IndexOutOfBoundsException {
    if (bits.length() != this.bitLength) {
      throw new IndexOutOfBoundsException("The bit string given to .set is the wrong length");
    } // if

    BitTreeParentNode current = this.root.toParentNode();
    for (int i = 0; i < bits.length(); i++) {
      boolean atEnd = i == bits.length() - 1;
      BitTreeNode possibleChild = atEnd ? new BitTreeLeafNode(value) : new BitTreeParentNode();

      switch (bits.charAt(i)) {
        case '0':
          if (current.getZero() == null || atEnd) {
            current.setZero(possibleChild);
          } // if
          current = current.getZero().toParentNode();
          break;
        case '1':
          if (current.getOne() == null || atEnd) {
            current.setOne(possibleChild);
          } // if
          current = current.getOne().toParentNode();
          break;
        default:
          throw new IndexOutOfBoundsException(
              "The bit string given to .set is not composed only of 0 and 1"
          );
      } // switch
    } // for
  } // set(String, String)

  /**
   * Gets the value associated with the given bit string.
   *
   * @param bits The bit string to look up.
   *
   * @throws IndexOutOfBoundsException
   *   If the bit string cannot be found or
   *   the bit string is invalid, either due to length
   *   or character content.
   */
  public String get(String bits) throws IndexOutOfBoundsException {
    if (bits.length() != this.bitLength) {
      throw new IndexOutOfBoundsException("The bit string given to .get is the wrong length");
    } // if

    BitTreeNode current = this.root;
    for (char bit : bits.toCharArray()) {
      if (current == null) {
        throw new IndexOutOfBoundsException("Bit string does not exist in tree");
      } // if
      switch (bit) {
        case '0':
          current = current.toParentNode().getZero();
          break;
        case '1':
          current = current.toParentNode().getOne();
          break;
        default:
          throw new IndexOutOfBoundsException("Bit string is not composed only of 0 or 1");
      } // switch
    } // while

    if (current == null) {
      throw new IndexOutOfBoundsException("Bit string does not exist in tree");
    } // if
    return current.toLeafNode().getValue();
  } // get(String, String)

  /**
   * Writes this tree into a PrintWriter in CSV format.
   *
   * @param pen The PrintWriter to which to write.
   */
  public void dump(PrintWriter pen) {
    BitTree.dumpRecursively(
        pen,
        "",
        this.root
    );
  } // dump(PrintWriter)

  /**
   * Loads an input stream with data of the form "bits,value" into this tree.
   *
   * @param source The InputStream from which to read.
   */
  public void load(InputStream source) {
    BufferedReader br = new BufferedReader(new InputStreamReader(source));
    String line;
    try {
      while ((line = br.readLine()) != null && !line.isEmpty()) {
        String[] parts = line.split(",", 2);
        this.set(parts[0], parts[1]);
      } // for
    } catch (IOException err) {
      // Ideally this would be thrown,
      // but the method signature doesn't allow it,
      // so we do nothing.
    } // try-catch
  } // load(InputStream)

} // class BitTree

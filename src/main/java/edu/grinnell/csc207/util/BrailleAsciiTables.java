package edu.grinnell.csc207.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;

/**
 * A utility class with static methods used to convert between Unicode, Braille, and ASCII.
 *
 * @author David William Stroud
 * @author Samuel A. Rebelsky
 */
public class BrailleAsciiTables {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * Conversions from ASCII to braille.
   */
  static final String ASCII_TO_BRAILE_CSV =
      "01000001,100000\n"
      + "01000010,110000\n"
      + "01000011,100100\n"
      + "01000100,100110\n"
      + "01000101,100010\n"
      + "01000110,110100\n"
      + "01000111,110110\n"
      + "01001000,110010\n"
      + "01001001,010100\n"
      + "01001010,010110\n"
      + "01001011,101000\n"
      + "01001100,111000\n"
      + "01001101,101100\n"
      + "01001110,101110\n"
      + "01001111,101010\n"
      + "01010000,111100\n"
      + "01010001,111110\n"
      + "01010010,111010\n"
      + "01010011,011100\n"
      + "01010100,011110\n"
      + "01010101,101001\n"
      + "01010110,111001\n"
      + "01010111,010111\n"
      + "01011000,101101\n"
      + "01011001,101111\n"
      + "01011010,101011\n"
      + "01100001,100000\n"
      + "01100010,110000\n"
      + "01100011,100100\n"
      + "01100100,100110\n"
      + "01100101,100010\n"
      + "01100110,110100\n"
      + "01100111,110110\n"
      + "01101000,110010\n"
      + "01101001,010100\n"
      + "01101010,010110\n"
      + "01101011,101000\n"
      + "01101100,111000\n"
      + "01101101,101100\n"
      + "01101110,101110\n"
      + "01101111,101010\n"
      + "01110000,111100\n"
      + "01110001,111110\n"
      + "01110010,111010\n"
      + "01110011,011100\n"
      + "01110100,011110\n"
      + "01110101,101001\n"
      + "01110110,111001\n"
      + "01110111,010111\n"
      + "01111000,101101\n"
      + "01111001,101111\n"
      + "01111010,101011\n"
      + "00100000,000000\n";

  /**
   * Conversions from braille to ASCII.
   */
  static final String BRAILLE_TO_ASCII_CSV =
      "100000,A\n"
      + "110000,B\n"
      + "100100,C\n"
      + "100110,D\n"
      + "100010,E\n"
      + "110100,F\n"
      + "110110,G\n"
      + "110010,H\n"
      + "010100,I\n"
      + "010110,J\n"
      + "101000,K\n"
      + "111000,L\n"
      + "101100,M\n"
      + "101110,N\n"
      + "101010,O\n"
      + "111100,P\n"
      + "111110,Q\n"
      + "111010,R\n"
      + "011100,S\n"
      + "011110,T\n"
      + "101001,U\n"
      + "111001,V\n"
      + "101101,X\n"
      + "101111,Y\n"
      + "101011,Z\n"
      + "010111,W\n"
      + "000000, \n";

  /**
   * Conversions from braille to unicode.
   */
  static final String BRAILLE_TO_UNICODE_CSV =
      "000000,2800\n"
      + "100000,2801\n"
      + "010000,2802\n"
      + "110000,2803\n"
      + "001000,2804\n"
      + "101000,2805\n"
      + "011000,2806\n"
      + "111000,2807\n"
      + "000100,2808\n"
      + "100100,2809\n"
      + "010100,280A\n"
      + "110100,280B\n"
      + "001100,280C\n"
      + "101100,280D\n"
      + "011100,280E\n"
      + "111100,280F\n"
      + "000010,2810\n"
      + "100010,2811\n"
      + "010010,2812\n"
      + "110010,2813\n"
      + "001010,2814\n"
      + "101010,2815\n"
      + "011010,2816\n"
      + "111010,2817\n"
      + "000110,2818\n"
      + "100110,2819\n"
      + "010110,281A\n"
      + "110110,281B\n"
      + "001110,281C\n"
      + "101110,281D\n"
      + "011110,281E\n"
      + "111110,281F\n"
      + "000001,2820\n"
      + "100001,2821\n"
      + "010001,2822\n"
      + "110001,2823\n"
      + "001001,2824\n"
      + "101001,2825\n"
      + "011001,2826\n"
      + "111001,2827\n"
      + "000101,2828\n"
      + "100101,2829\n"
      + "010101,282A\n"
      + "110101,282B\n"
      + "001101,282C\n"
      + "101101,282D\n"
      + "011101,282E\n"
      + "111101,282F\n"
      + "000011,2830\n"
      + "100011,2831\n"
      + "010011,2832\n"
      + "110011,2833\n"
      + "001011,2834\n"
      + "101011,2835\n"
      + "011011,2836\n"
      + "111011,2837\n"
      + "000111,2838\n"
      + "100111,2839\n"
      + "010111,283A\n"
      + "110111,283B\n"
      + "001111,283C\n"
      + "101111,283D\n"
      + "011111,283E\n"
      + "111111,283F\n";

  // +---------------+-----------------------------------------------
  // | Static fields |
  // +---------------+

  /**
   * The BitTree representing conversions from ASCII (as a bit string) to Braille.
   */
  static BitTree a2bTree = null;

  /**
   * The BitTree representing conversions from Braille (as a bit string) to ASCII.
   */
  static BitTree b2aTree = null;

  /**
   * The BitTree representing conversions from Braille (as a bit string)
   * to Unicode (as a hex string).
   */
  static BitTree b2uTree = null;

  // +-----------------------+---------------------------------------
  // | Static helper methods |
  // +-----------------------+

  /**
   * Load the given CSV data into the given tree.
   * @param tree The tree into which to load the data.
   * @param csv The CSV data to load.
   */
  private static void loadTree(BitTree tree, String csv) {
    InputStream stream = new ByteArrayInputStream(csv.getBytes());
    tree.load(stream);
    try {
      stream.close();
    } catch (IOException e) {
      // We don't care if we can't close the stream.
    } // try/catch
  } // loadTree(BitTree, String)

  // +----------------+----------------------------------------------
  // | Static methods |
  // +----------------+

  /**
   * Converts a character into its Braille binary representation.
   *
   * @param letter The letter to convert to Braille.
   *
   * @return
   *   The binary representation of the Braille associated
   *   with the given character.
   */
  public static String toBraille(char letter) {
    // Make sure we've loaded the ASCII-to-braille tree.
    if (BrailleAsciiTables.a2bTree == null) {
      BrailleAsciiTables.a2bTree = new BitTree(8);
      BrailleAsciiTables.loadTree(
          BrailleAsciiTables.a2bTree,
          BrailleAsciiTables.ASCII_TO_BRAILE_CSV
      );
    } // if
    int asciiValue = (int) letter;
    char[] bits = new char[8];
    for (int i = 0; i < bits.length; i++) {
      int power = 1 << (7 - i);
      if (asciiValue >= power) {
        asciiValue -= power;
        bits[i] = '1';
      } else {
        bits[i] = '0';
      } // if-else
    } // for
    return BrailleAsciiTables.a2bTree.get(new String(bits));
  } // toBraille(char)

  /**
   * Converts a Braille bit string to its equivalent ASCII.
   *
   * @param bits The Braille bit string to convert.
   *
   * @return The ASCII associated with that Braille bit string.
   */
  public static String toAscii(String bits) {
    // Make sure we've loaded the braille-to-ASCII tree.
    if (BrailleAsciiTables.b2aTree == null) {
      BrailleAsciiTables.b2aTree = new BitTree(6);
      BrailleAsciiTables.loadTree(
          BrailleAsciiTables.b2aTree,
          BrailleAsciiTables.BRAILLE_TO_ASCII_CSV
      );
    } // if
    return BrailleAsciiTables.b2aTree.get(bits);
  } // toAscii(String)

  /**
   * Converts a Braille bit string to its equivalent Unicode.
   *
   * @param bits The Braille bit string to convert.
   *
   * @return The hex code of the Unicode of the Braille character given.
   */
  public static String toUnicode(String bits) {
    // Make sure we've loaded the Unicode-to-braille tree.
    if (BrailleAsciiTables.b2uTree == null) {
      BrailleAsciiTables.b2uTree = new BitTree(6);
      BrailleAsciiTables.loadTree(
          BrailleAsciiTables.b2uTree,
          BrailleAsciiTables.BRAILLE_TO_UNICODE_CSV
      );
    } // if
    return BrailleAsciiTables.b2uTree.get(bits);
  } // toUnicode(String)
} // BrailleAsciiTables

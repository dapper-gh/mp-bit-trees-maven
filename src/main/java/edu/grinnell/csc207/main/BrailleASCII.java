package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BrailleAsciiTables;

import java.io.PrintWriter;
import java.util.HexFormat;

/**
 * A program that provides conversions between Braille, ASCII, and Unicode.
 *
 * @author David William Stroud
 */
public class BrailleASCII {
  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Executes this program.
   *
   * @param args The command-line arguments given to this program.
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);

    if (args.length != 2) {
      pen.printf("This program takes two arguments. You gave %d arguments.\n", args.length);
      return;
    } // if

    String mode = args[0];
    String arg = args[1];
    if (mode.equals("braille")) {
      BrailleASCII.execBraille(pen, arg);
    } else if (mode.equals("ascii")) {
      BrailleASCII.execASCII(pen, arg);
    } else if (mode.equals("unicode")) {
      BrailleASCII.execUnicode(pen, arg);
    } else {
      pen.printf(
          "This program has three commands: 'braille', 'ascii', and 'unicode'."
              + "You provided '%s'.\n",
          mode
      );
    } // if-else
    pen.close();
  } // main(String[])

  /**
   * Converts a regular ASCII string to a Braille bit string.
   * @param pen The PrintWriter to use when displaying output.
   * @param arg The ASCII string to convert.
   */
  private static void execBraille(PrintWriter pen, String arg) {
    try {
      for (char c : arg.toCharArray()) {
        pen.print(Character.isWhitespace(c) ? c : BrailleAsciiTables.toBraille(c));
      } // for
      pen.println();
    } catch (Exception e) {
      pen.printf("\nTrouble translating because %s\n", e.getMessage());
    } // try-catch
  } // execBraille(PrintWriter, String)

  /**
   * Converts a Braille bit string to regular ASCII.
   * @param pen The PrintWriter to use when displaying output.
   * @param arg The Braille bit string to convert.
   */
  private static void execASCII(PrintWriter pen, String arg) {
    try {
      if (arg.length() % 6 != 0) {
        pen.println("Invalid length of bit string");
        return;
      } // if

      for (int i = 0; i < arg.length(); i += 6) {
        pen.print(BrailleAsciiTables.toAscii(arg.substring(i, i + 6)));
      } // for
      pen.println();
    } catch (Exception e) {
      pen.printf("\nTrouble translating because %s\n", e.getMessage());
    } // try-catch
  } // execASCII(PrintWriter, String)

  /**
   * Converts a regular ASCII string to Braille Unicode characters.
   * @param pen The PrintWriter to use when displaying output.
   * @param arg The ASCII string to convert.
   */
  private static void execUnicode(PrintWriter pen, String arg) {
    try {
      for (char c : arg.toCharArray()) {
        if (Character.isWhitespace(c)) {
          pen.print(c);
        } else {
          String brailleBitString = BrailleAsciiTables.toBraille(c);
          String unicodeHex = BrailleAsciiTables.toUnicode(brailleBitString);
          pen.print((char) HexFormat.fromHexDigitsToLong(unicodeHex));
        } // if-else
      } // for
      pen.println();
    } catch (Exception e) {
      pen.printf("\nTrouble translating because %s\n", e.getMessage());
    } // try-catch
  } // execUnicode(PrintWriter, String)
} // class BrailleASCII

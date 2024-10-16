package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

/**
 * A command-line quick calculator for BigFraction objects.
 * Takes expressions from command-line arguments and evaluates them.
 *
 * @author Princess Alexander
 */
public class QuickCalculator {
  /**
  * Constant to avoid magic numbers.
  */
  private static final int STORE_COMMAND_POSITION = 6;

  /**
   * Main method for the QuickCalculator.
   * Processes command-line arguments and evaluates expressions.
   *
   * @param args Command-line arguments containing the expressions to evaluate.
   */
  public static void main(String[] args) {
    BFCalculator calc = new BFCalculator();
    BFRegisterSet registers = new BFRegisterSet();
    BigFraction result = null;  // Track the last computed result

    for (String arg : args) {
      if (arg.startsWith("STORE")) {
        // Extract the register and store the last result
        char reg = arg.charAt(STORE_COMMAND_POSITION);
        registers.store(reg, calc.get()); // Store the last calculated value
        printResult("STORE " + reg + " = " + calc.get()); //Replace System.out.println with a method
      } else {
        // Process the expression and update the calculator
        String[] parts = arg.split(" ");
        result = InteractiveCalculator.parseExpression(parts, calc, registers);
        printResult(arg + " = " + result); // Replace System.out.println with a method
      } //else
    } //for
  } //main

  /**
   * Prints the result of an operation to the console.
   *
   * @param message The message to print.
   */
  private static void printResult(String message) {
    System.out.println(message); // Print the result
  } // printResult
} //QuickCalculator

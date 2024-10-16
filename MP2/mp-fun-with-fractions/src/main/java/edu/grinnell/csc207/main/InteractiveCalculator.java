package edu.grinnell.csc207.main;

import java.util.Scanner;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;

/**
 * A command-line interactive calculator for BigFraction objects.
 * Allows users to perform operations with the calculator.
 *
 * @author Princess Alexander
 */
public class InteractiveCalculator {

  /** Constant for quit command. */
  private static final String QUIT_COMMAND = "QUIT";

  /** Constant for store command. */
  private static final String STORE_COMMAND = "STORE ";

  /** Constant for register position in the input string. */
  private static final int REGISTER_POSITION = 6; // Avoid magic numbers

  /**
   * Main method for the InteractiveCalculator.
   * Reads input from the user and evaluates expressions.
   *
   * @param args Command-line arguments.
   */
  public static void main(String[] args) {
    // Try-with-resources block to handle the Scanner resource
    try (Scanner sc = new Scanner(System.in)) {
      BFCalculator calc = new BFCalculator();
      BFRegisterSet registers = new BFRegisterSet();

      while (true) {
        printMessage("> ");
        String input = sc.nextLine();

        if (input.equalsIgnoreCase(QUIT_COMMAND)) {
          break; // Exit if QUIT is entered
        } else if (input.startsWith(STORE_COMMAND)) {
          char reg = input.charAt(REGISTER_POSITION);
          registers.store(reg, calc.get()); // Store the last result in the specified register
          printMessage("Stored value in register " + reg + ": " + calc.get());
        } else {
          String[] parts = input.split(" ");
          BigFraction result = parseExpression(parts, calc, registers);
          if (result != null) { // Only print if result is valid
            printMessage(result.toString());
          } // if
        } // else
      } // while
    } // try-with-resources automatically closes the scanner
  } // main

  /**
   * Parses an expression and evaluates it using the calculator and registers.
   *
   * @param parts The parts of the expression.
   * @param calc The calculator.
   * @param registers The register set.
   * @return The result of the expression evaluation.
   */
  public static BigFraction parseExpression(String[] parts, BFCalculator calc, BFRegisterSet registers) {
    BigFraction result = null;
    try {
      for (int i = 0; i < parts.length; i++) {
        String part = parts[i];
        if (isOperator(part)) {
          BigFraction nextFraction = getNextFraction(parts[++i], registers);
          result = applyOperation(result, nextFraction, part);
        } else {
          result = getNextFraction(part, registers);
        } // else
      } // for
      calc.clear(); // Reset calculator before the next operation
      calc.add(result); // Set the result as the last value
      return result; // Return the final result
    } catch (Exception e) {
      printMessage("Error: Invalid input. Please try again.");
      return null;
    } // catch
  } // parseExpression

  /**
   * Checks if the input string is an operator.
   *
   * @param part The string to check.
   * @return true if the string is an operator, false otherwise.
   */
  private static boolean isOperator(String part) {
    return part.equals("+") || part.equals("-") || part.equals("*") || part.equals("/"); // return
  } // isOperator

  /**
   * Retrieves the next fraction from the input, either from a register or by parsing a string.
   *
   * @param part The input string.
   * @param registers The register set.
   * @return The parsed or retrieved BigFraction.
   */
  private static BigFraction getNextFraction(String part, BFRegisterSet registers) {
    if (Character.isLetter(part.charAt(0))) {
      BigFraction value = registers.get(part.charAt(0));
      if (value.equals(new BigFraction(0, 1))) {
        printMessage("Error: Invalid register or uninitialized value.");
      } // if
      return value; // Retrieve value from register
    } else {
      try {
        return new BigFraction(part); // Parse the string as a fraction
      } catch (Exception e) {
        printMessage("Error: Invalid fraction input. Please enter a valid fraction.");
        return new BigFraction(0, 1); // Default return
      } // catch
    } // else
  } // getNextFraction

  /**
   * Applies an operation between two BigFraction objects using a switch expression.
   *
   * @param result The current result.
   * @param nextFraction The next fraction in the operation.
   * @param operator The operator to apply.
   * @return The result of applying the operator.
   */
  private static BigFraction applyOperation(BigFraction result, BigFraction nextFraction, String operator) {
    if (result == null) {
      return nextFraction; // First value
    } // if
    return switch (operator) {
      case "+" -> result.add(nextFraction);
      case "-" -> result.subtract(nextFraction);
      case "*" -> result.multiply(nextFraction);
      case "/" -> result.divide(nextFraction);
      default -> {
        printMessage("Error: Invalid operator.");
        yield result;
      } // default
    }; // switch
  } // applyOperation

  /**
   * Utility method for printing messages.
   *
   * @param message The message to print.
   */
  private static void printMessage(String message) {
    System.out.println(message); // Output the message
  } // printMessage
} // InteractiveCalculator

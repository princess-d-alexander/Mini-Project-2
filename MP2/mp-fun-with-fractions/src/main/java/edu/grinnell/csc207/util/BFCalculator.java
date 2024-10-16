package edu.grinnell.csc207.util;

/**
 * A calculator that handles arithmetic operations on BigFraction objects.
 *
 * @author Princess Alexander
 */
public final class BFCalculator {

  /** The last calculated value. */
  private BigFraction lastValue;

  /**
   * Constructor for the BFCalculator.
   * Initializes the last value to 0.
   */
  public BFCalculator() {
    this.lastValue = new BigFraction(0, 1); // Initialize with 0/1
  } // BFCalculator

  /**
   * Returns the last calculated value.
   *
   * @return The last calculated BigFraction.
   */
  public BigFraction get() {
    return this.lastValue; // return
  } // get

  /**
   * Adds a BigFraction to the last calculated value.
   *
   * @param val The BigFraction to add.
   */
  public void add(BigFraction val) {
    this.lastValue = this.lastValue.add(val); // Update lastValue
  } // add

  /**
   * Subtracts a BigFraction from the last calculated value.
   *
   * @param val The BigFraction to subtract.
   */
  public void subtract(BigFraction val) {
    this.lastValue = this.lastValue.subtract(val); // Update lastValue
  } // subtract

  /**
   * Multiplies the last calculated value by a BigFraction.
   *
   * @param val The BigFraction to multiply.
   */
  public void multiply(BigFraction val) {
    this.lastValue = this.lastValue.multiply(val); // Update lastValue
  } // multiply

  /**
   * Divides the last calculated value by a BigFraction.
   *
   * @param val The BigFraction to divide by.
   */
  public void divide(BigFraction val) {
    this.lastValue = this.lastValue.divide(val); // Update lastValue
  } // divide

  /**
   * Resets the last calculated value to 0.
   */
  public void clear() {
    this.lastValue = new BigFraction(0, 1); // Reset to 0
  } // clear
} // BFCalculator

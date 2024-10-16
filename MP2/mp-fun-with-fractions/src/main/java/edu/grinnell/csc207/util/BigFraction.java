package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * A class to represent fractions using BigInteger for precision.
 * The fractions are always stored in their simplest form.
 *
 * @author Princess Alexander
 */
public class BigFraction {
  /** The numerator of the fraction. */
  private BigInteger numerator;

  /** The denominator of the fraction. */
  private BigInteger denominator;

  /**
   * Constructor for string input (e.g., "3/4" or "5").
   *
   * @param fraction A string representation of the fraction.
   */
  public BigFraction(String fraction) {
    if (fraction.contains("/")) {
      String[] parts = fraction.split("/");
      this.numerator = new BigInteger(parts[0].trim());
      this.denominator = new BigInteger(parts[1].trim());
    } else {
      this.numerator = new BigInteger(fraction.trim());
      this.denominator = BigInteger.ONE;
    } // if
    this.reduce(); // Simplify the fraction
  } // BigFraction

  /**
   * Constructor for two BigInteger values (numerator and denominator).
   *
   * @param num The numerator for the fraction.
   * @param den The denominator for the fraction.
   */
  public BigFraction(BigInteger num, BigInteger den) {
    this.numerator = num; // Avoid hidden field
    this.denominator = den; // Avoid hidden field
    this.reduce(); // Simplify the fraction
  } // BigFraction

  /**
   * Constructor for int numerator and denominator.
   *
   * @param num The numerator for the fraction.
   * @param den The denominator for the fraction.
   */
  public BigFraction(int num, int den) {
    this.numerator = BigInteger.valueOf(num); // Avoid hidden field
    this.denominator = BigInteger.valueOf(den); // Avoid hidden field
    this.reduce(); // Simplify the fraction
  } // BigFraction

  /**
   * Reduces the fraction to its simplest form.
   */
  private void reduce() {
    BigInteger gcd = numerator.gcd(denominator);
    this.numerator = this.numerator.divide(gcd);
    this.denominator = this.denominator.divide(gcd);
    // Ensure denominator is positive
    if (this.denominator.compareTo(BigInteger.ZERO) < 0) {
      this.numerator = this.numerator.negate();
      this.denominator = this.denominator.negate();
    } // if
  } // reduce

  /**
   * Adds two BigFraction objects.
   *
   * @param other Another BigFraction to add.
   * @return The result of the addition.
   */
  public BigFraction add(BigFraction other) {
    BigInteger newNumerator = this.numerator.multiply(other.denominator)
                              .add(other.numerator.multiply(this.denominator));
    BigInteger newDenominator = this.denominator.multiply(other.denominator);
    return new BigFraction(newNumerator, newDenominator); // return
  } // add

  /**
   * Subtracts another BigFraction from this one.
   *
   * @param other The BigFraction to subtract.
   * @return The result of the subtraction.
   */
  public BigFraction subtract(BigFraction other) {
    BigInteger newNumerator = this.numerator.multiply(other.denominator)
                              .subtract(other.numerator.multiply(this.denominator));
    BigInteger newDenominator = this.denominator.multiply(other.denominator);
    return new BigFraction(newNumerator, newDenominator); // return
  } // subtract

  /**
   * Multiplies two BigFraction objects.
   *
   * @param other Another BigFraction to multiply.
   * @return The result of the multiplication.
   */
  public BigFraction multiply(BigFraction other) {
    BigInteger newNumerator = this.numerator.multiply(other.numerator);
    BigInteger newDenominator = this.denominator.multiply(other.denominator);
    return new BigFraction(newNumerator, newDenominator); // return
  } // multiply

  /**
   * Divides this BigFraction by another one.
   *
   * @param other The BigFraction to divide by.
   * @return The result of the division.
   */
  public BigFraction divide(BigFraction other) {
    BigInteger newNumerator = this.numerator.multiply(other.denominator);
    BigInteger newDenominator = this.denominator.multiply(other.numerator);
    return new BigFraction(newNumerator, newDenominator); // return
  } // divide

  /**
   * Returns the numerator.
   *
   * @return The numerator as a BigInteger.
   */
  public BigInteger numerator() {
    return this.numerator; // return
  } // numerator

  /**
   * Returns the denominator.
   *
   * @return The denominator as a BigInteger.
   */
  public BigInteger denominator() {
    return this.denominator; // return
  } // denominator

  /**
   * Converts the fraction to a string representation.
   *
   * @return The string representation of the fraction.
   */
  @Override
  public String toString() {
    if (denominator.equals(BigInteger.ONE)) {
      return numerator.toString(); // return
    } else {
      return numerator + "/" + denominator; // return
    } // if
  } // toString
} // BigFraction

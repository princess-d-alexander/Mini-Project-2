package edu.grinnell.csc207.util;

/**
 * A set of registers to store BigFraction objects. Each register corresponds to
 * a letter from 'a' to 'z'.
 *
 * @author Princess Alexander
 */
public class BFRegisterSet {
  /** The number of registers, one for each letter from 'a' to 'z'. */
  private static final int NUM_REGISTERS = 26;

  /** The array of BigFraction values stored in each register. */
  private BigFraction[] registers;

  /**
   * Constructor for the BFRegisterSet. Initializes all registers to 0.
   */
  public BFRegisterSet() {
    registers = new BigFraction[NUM_REGISTERS];
    for (int i = 0; i < registers.length; i++) {
      registers[i] = new BigFraction(0, 1); // Initialize with 0/1
    } // for
  } // BFRegisterSet

  /**
   * Stores a BigFraction in the specified register.
   *
   * @param register The register (character) to store the value in.
   * @param val The BigFraction value to store.
   */
  public void store(char register, BigFraction val) {
    if (register >= 'a' && register <= 'z') {
      registers[register - 'a'] = val;
    } // if
  } // store

  /**
   * Retrieves the value from the specified register.
   *
   * @param register The register (character) to retrieve the value from.
   * @return The BigFraction value in the specified register.
   */
  public BigFraction get(char register) {
    if (register >= 'a' && register <= 'z') {
      return registers[register - 'a']; // return
    } else {
      return new BigFraction(0, 1); // Default return 0 if invalid register
    } // if
  } // get

  /**
   * Getter for the registers array. This method provides access to the registers if needed.
   *
   * @return The array of registers.
   */
  public BigFraction[] getRegisters() {
    return registers;
  } // getRegisters
} // BFRegisterSet


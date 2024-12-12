package edu.umn.cs.csci3081w.project.model;

/**
 * Strategy for determining the type of bus to create during the day.
 * The {@code BusStrategyDay} cycles through a predefined sequence of bus types,
 * alternating between large and small buses in a specific pattern.
 */
public class BusStrategyDay implements BusStrategy {
  /**
   * Index that tracks the current bus type.
   */
  private int index = 0;
  /**
   * Sequence of bus types for daytime.
   */
  private final String[] buses = {"large", "large", "small", "large", "large", "small"};

  /**
   * Constructs a {@code BusStrategyDay} with the default bus sequence.
   */
  public BusStrategyDay() {
    //Default constructor
  }

  @Override
  public String getNextVehicle() {
    String temp = buses[index];
    index = (index + 1) % buses.length;
    return temp;
  }
}
package edu.umn.cs.csci3081w.project.model;

/**
 * Strategy for determining the type of bus to create during the night.
 * The {@code BusStrategyNight} cycles through a predefined sequence of bus types,
 * with a focus on smaller buses and occasional large buses.
 */
public class BusStrategyNight implements BusStrategy {
  /**
   * Index that tracks the current bus type.
   */
  private int index = 0;
  /**
   * Sequence of bus types for nighttime.
   */
  private final String[] buses = {"small", "small", "small", "large",
      "small", "small", "small", "large"};

  /**
   * Constructs a {@code BusStrategyNight} with the default bus sequence.
   */
  public BusStrategyNight() {
    //Default constructor
  }

  @Override
  public String getNextVehicle() {
    String temp = buses[index];
    index = (index + 1) % buses.length;
    return temp;
  }
}
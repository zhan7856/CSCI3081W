package edu.umn.cs.csci3081w.project.model;

/**
 * Represents a large bus in the simulation.
 * A {@code LargeBus} operates on a specified route with a higher capacity
 * than regular buses, making it suitable for high-demand routes.
 * This class extends the {@code Bus} class, inheriting its functionality.
 */
public class LargeBus extends Bus {
  /**
   * Constructor for a bus.
   *
   * @param id    bus identifier
   * @param line  route of in/out bound
   * @param speed speed of bus
   */
  public LargeBus(int id, Line line, double speed) {
    super(id, line, 80, speed);
  }

  /**
   * Gets co2 consumption for a bus.
   *
   * @return The current co2 consumption value
   */
  public int getCurrentCO2Emission() {
    return (2 * getPassengers().size()) + 5;
  }

}

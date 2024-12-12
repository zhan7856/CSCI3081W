package edu.umn.cs.csci3081w.project.model;

/**
 * Represents a small bus in the simulation.
 * A {@code SmallBus} operates on a specified route with a smaller capacity
 * than regular buses, making it suitable for low-demand routes.
 * This class extends the {@code Bus} class, inheriting its core functionality.
 */
public class SmallBus extends Bus {

  /**
   * Constructor for a bus.
   *
   * @param id    bus identifier
   * @param line  route of in/out bound
   * @param speed speed of bus
   */
  public SmallBus(int id, Line line, double speed) {
    super(id, line, 20, speed);
  }

  /**
   * Gets co2 consumption for a bus.
   *
   * @return The current co2 consumption value
   */
  public int getCurrentCO2Emission() {
    return (2 * getPassengers().size()) + 3;
  }

}

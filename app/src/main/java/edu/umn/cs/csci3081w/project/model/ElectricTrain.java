package edu.umn.cs.csci3081w.project.model;

/**
 * Represents an electric-powered train in the simulation.
 * An {@code ElectricTrain} operates on a specified route with a defined capacity and speed.
 * Electric trains produce no direct CO2 emissions, making them environmentally friendly.
 * This class extends the {@code Train} class, inheriting its basic functionality.
 */
public class ElectricTrain extends Train {

  /**
   * Constructor for a train.
   *
   * @param id       train identifier
   * @param line     route of in/out bound
   * @param capacity capacity of the train
   * @param speed    speed of the train
   */
  public ElectricTrain(int id, Line line, int capacity, double speed) {
    super(id, line, capacity, speed);
  }

  /**
   * Gets co2 consumption for a train.
   *
   * @return The current co2 consumption value
   */
  public int getCurrentCO2Emission() {
    return 0;
  }
}

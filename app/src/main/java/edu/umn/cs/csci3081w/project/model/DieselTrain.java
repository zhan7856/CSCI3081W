package edu.umn.cs.csci3081w.project.model;

/**
 * Represents a diesel-powered train in the simulation.
 * A {@code DieselTrain} operates on a specified route with a defined capacity and speed.
 * This class extends the {@code Train} class, inheriting its basic functionality.
 */
public class DieselTrain extends Train {
  /**
   * Constructor for a train.
   *
   * @param id       train identifier
   * @param line     route of in/out bound
   * @param capacity capacity of the train
   * @param speed    speed of the train
   */
  public DieselTrain(int id, Line line, int capacity, double speed) {
    super(id, line, capacity, speed);
  }
}

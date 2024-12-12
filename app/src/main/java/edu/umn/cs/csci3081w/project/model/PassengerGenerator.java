package edu.umn.cs.csci3081w.project.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for generating passengers in the simulation.
 * The {@code PassengerGenerator} class defines a framework for creating passengers
 * at specific stops with associated probabilities.
 */
public abstract class PassengerGenerator {
  private List<Stop> stops;
  private List<Double> probabilities;

  /**
   * Constructor for Abstract class.
   *
   * @param stops         list of stops
   * @param probabilities list of probabilities
   */
  public PassengerGenerator(List<Stop> stops, List<Double> probabilities) {
    this.probabilities = new ArrayList<>();
    this.stops = new ArrayList<>();
    for (Stop s : stops) {
      this.stops.add(s);
    }
    for (Double probability : probabilities) {
      this.probabilities.add(probability);
    }
  }

  /**
   * Abstract method to generate passengers.
   * Subclasses must implement this method to define the logic for passenger generation.
   *
   * @return the number of passengers generated
   */
  public abstract int generatePassengers();

  /**
   * Gets the list of stops associated with this generator.
   *
   * @return the list of stops
   */
  public List<Stop> getStops() {
    return stops;
  }

  /**
   * Gets the list of probabilities associated with each stop.
   *
   * @return the list of probabilities
   */
  public List<Double> getProbabilities() {
    return probabilities;
  }

}
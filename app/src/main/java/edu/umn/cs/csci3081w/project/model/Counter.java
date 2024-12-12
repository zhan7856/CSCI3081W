package edu.umn.cs.csci3081w.project.model;

/**
 * Class for managing and incrementing unique identifiers for various entities in the simulation.
 * The {@code Counter} class provides counters for routes, stops, buses, trains, and lines, ensuring
 * that each entity receives a unique ID.
 */
public class Counter {

  /**
   * Counter for route IDs.
   */
  public int routeIdCounter = 10;
  /**
   * Counter for stop IDs.
   */
  public int stopIdCounter = 100;
  /**
   * Counter for bus IDs.
   */
  public int busIdCounter = 1000;
  /**
   * Counter for train IDs.
   */
  public int trainIdCounter = 2000;
  /**
   * Counter for line IDs.
   */
  public int lineIdCounter = 10000;

  /**
   * Constructs a {@code Counter} with initial values for all counters.
   */
  public Counter() {

  }
  /**
   * Gets the current route ID and increments the route ID counter.
   *
   * @return the current route ID before incrementing
   */
  public int getRouteIdCounterAndIncrement() {
    return routeIdCounter++;
  }

  /**
   * Gets the current stop ID and increments the stop ID counter.
   *
   * @return the current stop ID before incrementing
   */
  public int getStopIdCounterAndIncrement() {
    return stopIdCounter++;
  }

  /**
   * Gets the current bus ID and increments the bus ID counter.
   *
   * @return the current bus ID before incrementing
   */
  public int getBusIdCounterAndIncrement() {
    return busIdCounter++;
  }

  /**
   * Gets the current train ID and increments the train ID counter.
   *
   * @return the current train ID before incrementing
   */
  public int getTrainIdCounterAndIncrement() {
    return trainIdCounter++;
  }

  /**
   * Gets the current line ID and increments the line ID counter.
   *
   * @return the current line ID before incrementing
   */
  public int getLineIdCounterAndIncrement() {
    return lineIdCounter++;
  }

}

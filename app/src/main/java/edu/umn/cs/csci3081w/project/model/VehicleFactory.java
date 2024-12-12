package edu.umn.cs.csci3081w.project.model;


import java.time.LocalDateTime;
import java.time.LocalTime;
/**
 * VehicleFactory is an abstract class responsible for creating vehicle-related strategies
 * based on the current time. It determines whether it is day or night and provides
 * a method to create vehicle strategies that must be implemented by subclasses.
 */


/**
 * Abstract factory class for creating vehicles in the simulation.
 * The {@code VehicleFactory} determines whether it is day or night
 * based on the current time and provides a framework for implementing
 * specific strategies for vehicle creation.
 */
public abstract class VehicleFactory {
  /**
   * The current time used to determine if it is day or night.
   */
  LocalTime currTime;
  /**
   * Flag indicating whether it is daytime.
   * {@code true} if the time is between 8:00 AM and 6:00 PM, otherwise {@code false}.
   */
  boolean isDay;
  /**
   * Constructor for VehicleFactory.
   *
   * @param currTime the current time used to determine if it is day or night
   */

  /**
   * Constructs a {@code VehicleFactory} and determines if it is day or night.
   *
   * @param currTime the current time used to determine the time of day
   */
  public VehicleFactory(LocalTime currTime) {
    this.currTime = currTime;
    isDay = currTime.isAfter(LocalTime.of(8, 0))
        && currTime.isBefore(LocalTime.of(18, 0));
  }

  /**
   * Abstract method to create a vehicle strategy.
   * Subclasses must implement this method to define specific vehicle strategies.
   *
   * @return a new instance of a VehicleStrategy
   */
  public abstract VehicleStrategy createVehicleStrategy();
}




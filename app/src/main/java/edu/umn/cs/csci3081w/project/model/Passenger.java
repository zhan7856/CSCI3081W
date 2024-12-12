package edu.umn.cs.csci3081w.project.model;

import java.io.PrintStream;

/**
 * Represents a passenger in the simulation.
 * A {@code Passenger} has a destination stop, tracks their waiting time at a stop,
 * and the time spent on a vehicle.
 */
public class Passenger {

  private String name;
  private int destinationStopId;
  private int waitAtStop;
  private int timeOnVehicle;

  /**
   * Constructor for passenger.
   *
   * @param name              name of passenger
   * @param destinationStopId destination stop id
   */
  public Passenger(int destinationStopId, String name) {
    this.name = name;
    this.destinationStopId = destinationStopId;
    this.waitAtStop = 0;
    this.timeOnVehicle = 0;
  }

  /**
   * Updates time variables for passenger.
   */
  public void pasUpdate() {
    if (isOnVehicle()) {
      timeOnVehicle++;
    } else {
      waitAtStop++;
    }
  }

  /**
   * Sets the passenger's status to indicate they are on a vehicle.
   * Initializes the time on the vehicle to 1.
   */
  public void setOnVehicle() {
    timeOnVehicle = 1;
  }

  /**
   * Checks if the passenger is currently on a vehicle.
   *
   * @return {@code true} if the passenger is on a vehicle, otherwise {@code false}
   */
  public boolean isOnVehicle() {
    return timeOnVehicle > 0;
  }

  /**
   * Returns the destination stop ID for the passenger.
   *
   * @return the destination stop ID
   */
  public int getDestination() {
    return destinationStopId;
  }

  /**
   * Report statistics for passenger.
   *
   * @param out stream for printing
   */
  public void report(PrintStream out) {
    out.println("####Passenger Info Start####");
    out.println("Name: " + name);
    out.println("Destination: " + destinationStopId);
    out.println("Wait at stop: " + waitAtStop);
    out.println("Time on vehicle: " + timeOnVehicle);
    out.println("####Passenger Info End####");
  }

  /**
   * Gets the total time the passenger has waited at the stop.
   *
   * @return the waiting time at the stop
   */
  public int getWaitAtStop() {
    return waitAtStop;
  }

  /**
   * Gets the total time the passenger has spent on a vehicle.
   *
   * @return the time on the vehicle
   */
  public int getTimeOnVehicle() {
    return timeOnVehicle;
  }
}

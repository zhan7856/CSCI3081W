package edu.umn.cs.csci3081w.project.model;

import java.io.PrintStream;

/**
 * Abstract representation of bus in the simulation.
 * This class extends the {@code Vehicle} class.
 */
public abstract class Bus extends Vehicle {
  /**
   * Type identifier for a bus vehicle.
   */
  public static final String BUS_VEHICLE = "BUS_VEHICLE";
  /**
   * Default speed for a bus vehicle.
   */
  public static final double SPEED = 0.5;
  /**
   * Maximum passenger capacity for a bus vehicle.
   */
  public static final int CAPACITY = 60;

  /**
   * Constructor for a bus.
   * Initializes the bus position to the next stop.
   *
   * @param id       bus identifier
   * @param line     route of in/out bound
   * @param capacity capacity of bus
   * @param speed    speed of bus
   */
  public Bus(int id, Line line, int capacity, double speed) {
    super(id, line.getOutboundRoute().getName() + id, line, 0,
        line.getOutboundRoute().getNextStop(), capacity,
        speed, new PassengerLoader(), new PassengerUnloader());
    setPosition(new Position(nextStop.getPosition().getLongitude(),
        nextStop.getPosition().getLatitude()));
  }

  /**
   * Report statistics for the bus.
   *
   * @param out stream for printing
   */
  public void report(PrintStream out) {
    out.println("####Bus Info Start####");
    out.println("ID: " + getId());
    out.println("Name: " + getName());
    out.println("Speed: " + getSpeed());
    out.println("Capacity: " + getCapacity());
    out.println("Position: " + (getPosition().getLatitude() + "," + getPosition().getLongitude()));
    out.println("Distance to next stop: " + distanceRemaining);
    out.println("****Passengers Info Start****");
    out.println("Num of passengers: " + getPassengers().size());
    for (Passenger pass : getPassengers()) {
      pass.report(out);
    }
    out.println("****Passengers Info End****");
    out.println("####Bus Info End####");
  }

  /**
   * Moves the bus on its route.
   */
  public void move() {
    // update passengers FIRST
    // new passengers will get "updated" when getting on the bus
    for (Passenger passenger : getPassengers()) {
      passenger.pasUpdate();
    }
    //actually move
    double speed = updateDistance();
    if (!isTripComplete() && distanceRemaining <= 0) {
      //load & unload
      int passengersHandled = handleBusStop();
      if (passengersHandled >= 0) {
        distanceRemaining = 0;
      }
      //switch to next stop
      toNextStop();
    }

    // Get the correct route and early exit
    Route currentRoute = line.getOutboundRoute();
    if (line.getOutboundRoute().isAtEnd()) {
      if (line.getInboundRoute().isAtEnd()) {
        return;
      }
      currentRoute = line.getInboundRoute();
    }
    Stop prevStop = currentRoute.prevStop();
    Stop nextStop = currentRoute.getNextStop();
    double distanceBetween = currentRoute.getNextStopDistance();
    // the ratio shows us how far from the previous stop are we in a ratio from 0 to 1
    double ratio;
    // check if we are at the first stop
    if (distanceBetween - 0.00001 < 0) {
      ratio = 1;
    } else {
      ratio = distanceRemaining / distanceBetween;
      if (ratio < 0) {
        ratio = 0;
        distanceRemaining = 0;
      }
    }
    double newLongitude = nextStop.getPosition().getLongitude() * (1 - ratio)
        + prevStop.getPosition().getLongitude() * ratio;
    double newLatitude = nextStop.getPosition().getLatitude() * (1 - ratio)
        + prevStop.getPosition().getLatitude() * ratio;
    setPosition(new Position(newLongitude, newLatitude));
  }

  /**
   * Gets co2 consumption for a bus.
   *
   * @return The current co2 consumption value
   */
  public int getCurrentCO2Emission() {
    return (2 * getPassengers().size()) + 4;
  }

  /**
   * Handles passenger loading and unloading at the current bus stop.
   * Updates the number of passengers and distance remaining if passengers are handled.
   * @return the total number of passengers handled
   */
  private int handleBusStop() {
    // This function handles arrival at a bus stop
    int passengersHandled = 0;
    // unloading passengers
    passengersHandled += unloadPassengers();
    // loading passengers
    passengersHandled += nextStop.loadPassengers(this);
    if (passengersHandled != 0) {
      distanceRemaining = 0;
    }
    return passengersHandled;
  }

}
package edu.umn.cs.csci3081w.project.model;

import java.io.PrintStream;

/**
 * Abstract representation of a train in the simulation.
 * This class extends the {@code Vehicle} class and provides core functionality
 * specific to trains, including movement, reporting, and CO2 emission calculations.
 */
public abstract class Train extends Vehicle {
  /**
   * Constant representing the type identifier for a train vehicle.
   */
  public static final String TRAIN_VEHICLE = "TRAIN_VEHICLE";
  /**
   * Default speed for a train vehicle.
   */
  public static final double SPEED = 1;
  /**
   * Default capacity for a train vehicle.
   */
  public static final int CAPACITY = 120;

  /**
   * Constructor for a train.
   *
   * @param id       train identifier
   * @param line     route of in/out bound
   * @param capacity capacity of the train
   * @param speed    speed of the train
   */
  public Train(int id, Line line, int capacity, double speed) {
    super(id, line.getOutboundRoute().getName() + id, line, 0,
        line.getOutboundRoute().getNextStop(), capacity,
        speed, new PassengerLoader(), new PassengerUnloader());
    setPosition(new Position(nextStop.getPosition().getLongitude(),
        nextStop.getPosition().getLatitude()));
  }

  /**
   * Report statistics for the train.
   *
   * @param out stream for printing
   */
  public void report(PrintStream out) {
    out.println("####Train Info Start####");
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
    out.println("####Train Info End####");
  }

  /**
   * Moves the train on its route.
   */
  public void move() {
    // update passengers FIRST
    // new passengers will get "updated" when getting on the train
    for (Passenger passenger : getPassengers()) {
      passenger.pasUpdate();
    }
    // actually move
    double speed = updateDistance();
    if (!isTripComplete() && distanceRemaining <= 0) {
      // load & unload
      int passengersHandled = handleTrainStop();
      if (passengersHandled >= 0) {
        distanceRemaining = 0;
      }
      // switch to next stop
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
   * Gets co2 consumption for a train.
   *
   * @return The current co2 consumption value
   */
  public int getCurrentCO2Emission() {
    return (3 * getPassengers().size()) + 6;
  }

  /**
   * Handles passengers at the current stop.
   * Unloads passengers whose destination is the current stop and loads new passengers.
   *
   * @return the total number of passengers handled (loaded and unloaded)
   */
  private int handleTrainStop() {
    // This function handles arrival at a train stop
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

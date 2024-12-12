package edu.umn.cs.csci3081w.project.model;


import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * The {@code Vehicle} class represents a generic vehicle in the transit simulation.
 * It provides functionality for managing passengers, movement, emissions, and trip progression.
 * This is an abstract class that must be extended to define specific vehicle behavior.
 */
public abstract class Vehicle implements VehicleObserver {
  private int id;
  private int capacity;
  /**
   * The line (route) the vehicle operates on.
   */
  protected Line line;
  /**
   * Distance remaining to the next stop.
   */
  protected double distanceRemaining;
  /**
   * The next stop the vehicle is headed to.
   */
  protected Stop nextStop;
  //the speed is in distance over a time unit
  private double speed;
  private PassengerLoader loader;
  private PassengerUnloader unloader;
  private List<Passenger> passengers;
  private String name;
  private Position position;
  private LinkedList<Integer> co2History = new LinkedList<>();


  /**
   * Constructs a {@code Vehicle} with the specified attributes.
   *
   * @param id                the unique identifier for the vehicle
   * @param name              the name of the vehicle
   * @param line              the line (route) the vehicle operates on
   * @param distanceRemaining distance to the next stop
   * @param nextStop          the next stop the vehicle is headed to
   * @param capacity          the maximum passenger capacity
   * @param speed             the speed of the vehicle
   * @param loader            the passenger loader for the vehicle
   * @param unloader          the passenger unloader for the vehicle
   */
  public Vehicle(int id, String name, Line line, double distanceRemaining,
                 Stop nextStop, int capacity,
                 double speed, PassengerLoader loader,
                 PassengerUnloader unloader) {
    this.id = id;
    this.name = name;
    this.line = line;
    this.capacity = capacity;
    this.distanceRemaining = distanceRemaining;
    this.nextStop = nextStop;
    this.speed = speed;
    this.loader = loader;
    this.unloader = unloader;
    this.passengers = new ArrayList<Passenger>();
  }

  /**
   * Generates a report of the vehicle's status and writes it to the provided output stream.
   *
   * @param out the output stream to write the report to
   */
  public abstract void report(PrintStream out);

  /**
   * Moves the vehicle according to its current state and updates its position.
   */
  public abstract void move();

  /**
   * Retrieves the current CO2 emissions for the vehicle.
   *
   * @return the current CO2 emission value
   */
  public abstract int getCurrentCO2Emission();

  /**
   * Loads a passenger onto the vehicle if there is available capacity.
   *
   * @param newPassenger the passenger to be loaded
   * @return 1 if the passenger is successfully loaded, 0 otherwise
   */
  public int loadPassenger(Passenger newPassenger) {
    return getPassengerLoader().loadPassenger(newPassenger, getCapacity(), getPassengers());
  }

  /**
   * Checks if the vehicle has completed its trip.
   *
   * @return {@code true} if the trip is complete, {@code false} otherwise
   */
  public boolean isTripComplete() {
    return line.getOutboundRoute().isAtEnd() && line.getInboundRoute().isAtEnd();
  }

  /**
   * Unloads passengers at the current stop.
   *
   * @return the number of passengers unloaded
   */
  protected int unloadPassengers() {
    return getPassengerUnloader().unloadPassengers(getPassengers(), nextStop);
  }

  /**
   * Advances the vehicle to the next stop on the route.
   */
  protected void toNextStop() {
    //current stop
    currentRoute().nextStop();
    if (!isTripComplete()) {
      // it's important we call currentRoute() again,
      // as nextStop() may have caused it to change.
      nextStop = currentRoute().getNextStop();
      distanceRemaining +=
          currentRoute().getNextStopDistance();
      // note, if distanceRemaining was negative because we
      // had extra time left over, that extra time is
      // effectively counted towards the next stop
    } else {
      nextStop = null;
      distanceRemaining = 999;
    }
  }

  /**
   * Updates the distance remaining for the vehicle to reach the next stop.
   *
   * @return the effective speed of the vehicle (distance moved in this update)
   */
  protected double updateDistance() {
    // Updates the distance remaining and returns the effective speed of the bus
    // Bus does not move if speed is negative or bus is at end of route
    if (isTripComplete()) {
      return 0;
    }
    if (getSpeed() < 0) {
      return 0;
    }
    distanceRemaining -= getSpeed();
    return getSpeed();
  }


  /**
   * Update the simulation state for this train.
   */
  public void update() {
    move();
    updateCO2();
  }


  public int getId() {
    return id;
  }

  /**
   * Retrieves the current route of the vehicle (outbound or inbound).
   *
   * @return the current route
   */
  private Route currentRoute() {
    // Figure out if we're on the outgoing or incoming route
    if (!line.getOutboundRoute().isAtEnd()) {
      return line.getOutboundRoute();
    }
    return line.getInboundRoute();
  }

  /**
   * Updates the CO2 emission history of the vehicle.
   * Keeps the history to the last 5 emissions.
   */
  public void updateCO2() {
    co2History.add(getCurrentCO2Emission());
    if (co2History.size() > 5) {
      co2History.removeFirst();
    }
  }

  /**
   * Retrieves the vehicle's CO2 emission history.
   *
   * @return a list of CO2 emission values
   */
  public List<Integer> getCO2History() {
    return co2History;
  }

  /**
   * Retrieves the vehicle's information
   *
   * @return String of vehicle information
   */
  public String getVehicleInfo() {
    StringBuilder info = new StringBuilder();
    info.append("Vehicle ID: ").append(getId()).append("\n")
        .append("-----------------------------\n")
        .append(" * Type: ").append(this instanceof Bus ? "BUS" : "TRAIN").append("\n")
        .append(" * Position: (").append(getPosition().getLongitude()).append(", ")
        .append(getPosition().getLatitude()).append(")\n")
        .append(" * Passengers: ").append(getPassengers().size()).append("\n")
        .append(" * CO2: ");
    List<Integer> co2History = getCO2History();
    for (int i = co2History.size() - 1; i >= 0; i--) {
      info.append(co2History.get(i));
      if (i > 0) {
        info.append(", ");
      }
    }
    return info.toString();
  }

  /**
   * Retrieves the vehicle's current capacity.
   *
   * @return the maximum capacity of the vehicle
   */
  public int getCapacity() {
    return capacity;
  }

  /**
   * Retrieves the vehicle's speed.
   *
   * @return the speed of the vehicle
   */
  public double getSpeed() {
    return speed;
  }

  /**
   * Retrieves the passenger loader for the vehicle.
   *
   * @return the passenger loader
   */
  public PassengerLoader getPassengerLoader() {
    return loader;
  }

  /**
   * Retrieves the passenger unloader for the vehicle.
   *
   * @return the passenger unloader
   */
  public PassengerUnloader getPassengerUnloader() {
    return unloader;
  }

  /**
   * Retrieves the list of passengers currently on the vehicle.
   *
   * @return a list of passengers
   */
  public List<Passenger> getPassengers() {
    return passengers;
  }

  /**
   * Retrieves the name of the vehicle.
   *
   * @return the name of the vehicle
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the vehicle.
   *
   * @param name the new name for the vehicle
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Retrieves the current position of the vehicle.
   *
   * @return the position of the vehicle
   */
  public Position getPosition() {
    return position;
  }

  /**
   * Sets the position of the vehicle.
   *
   * @param position the new position for the vehicle
   */
  public void setPosition(Position position) {
    this.position = position;
  }

  /**
   * Retrieves the line (route) the vehicle is operating on.
   *
   * @return the vehicle's line
   */
  public Line getLine() {
    return line;
  }

  /**
   * Retrieves the next stop for the vehicle.
   *
   * @return the next stop on the route
   */
  public Stop getNextStop() {
    return nextStop;
  }
}






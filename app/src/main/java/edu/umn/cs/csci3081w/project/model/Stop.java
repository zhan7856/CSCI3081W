package edu.umn.cs.csci3081w.project.model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a stop in the simulation.
 * A {@code Stop} is associated with a position and a list of passengers waiting at the stop.
 * It facilitates loading passengers onto vehicles and manages passenger updates.
 */
public class Stop {
  /**
   * Unique identifier for the stop.
   */
  private int id;
  /**
   * Name of the stop.
   */
  private String name;
  /**
   * Geographical position of the stop.
   */
  private Position position;
  /**
   * List of passengers waiting at the stop.
   */
  private List<Passenger> passengers;

  /**
   * Constructor for a stop.
   *
   * @param id       stop id
   * @param name     name for the stop
   * @param position location of the stop
   */
  public Stop(int id, String name, Position position) {
    this.id = id;
    this.name = name;
    this.position = position;
    passengers = new ArrayList<>();

  }

  /**
   * Loads passengers on a vehicle.
   *
   * @param vehicle Vehicle to be loaded to
   * @return number of loaded passengers
   */
  public int loadPassengers(Vehicle vehicle) {
    int passengersAdded = 0;
    while (!passengers.isEmpty() && vehicle.loadPassenger(passengers.get(0)) > 0) {
      // passenger is on the vehicle and passenger have left the stop
      passengers.remove(0);
      passengersAdded++;
    }
    return passengersAdded;
  }

  /**
   * Adds passenger to passengers list at stop.
   *
   * @param pass to be added
   * @return number of added passengers
   */
  public int addPassengers(Passenger pass) {
    // we're using int here to aid potential future work:
    //  we may modify this to allow more multiple adds
    //  in a single call. For the moment, the var is
    //  used as a flag: 0 - fail; 1 - pass
    int passengersAddedToStop = 0;
    passengers.add(pass);
    passengersAddedToStop++;
    return passengersAddedToStop;
  }

  /**
   * Updates stop.
   */
  public void update() {
    Iterator<Passenger> passIter = passengers.iterator();
    while (passIter.hasNext()) {
      passIter.next().pasUpdate();
    }
  }

  /**
   * Report function for stop.
   *
   * @param out printstream object
   */
  public void report(PrintStream out) {
    out.println("####Stop Info Start####");
    out.println("ID: " + id);
    out.println("Name: " + name);
    out.println("Position: " + (getPosition().getLatitude() + "," + getPosition().getLongitude()));
    out.println("****Passengers Info Start****");
    out.println("Num passengers waiting: " + passengers.size());
    Iterator<Passenger> passIter = passengers.iterator();
    while (passIter.hasNext()) {
      passIter.next().report(out);
    }
    out.println("****Passengers Info End****");
    out.println("####Stop Info End####");
  }

  /**
   * Gets the unique identifier of the stop.
   *
   * @return the stop ID
   */
  public int getId() {
    return id;
  }

  /**
   * Gets the name of the stop.
   *
   * @return the stop name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the geographical position of the stop.
   *
   * @return the position of the stop
   */
  public Position getPosition() {
    return position;
  }

  /**
   * Gets the list of passengers waiting at the stop.
   *
   * @return the list of waiting passengers
   */
  public List<Passenger> getPassengers() {
    return passengers;
  }
}

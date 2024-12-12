package edu.umn.cs.csci3081w.project.model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a route in the simulation.
 * A {@code Route} consists of a series of stops and distances between them,
 * along with a passenger generator to generate passengers at the stops.
 */
public class Route {
  /**
   * Unique identifier for the route.
   */
  private int id;
  /**
   * Name of the route.
   */
  private String name;
  /**
   * List of stops on the route.
   */
  private List<Stop> stops = new ArrayList<Stop>();
  /**
   * List of distances between consecutive stops.
   */
  private List<Double> distances = new ArrayList<Double>();
  /**
   * Index of the next stop on the route.
   */
  private int nextStopIndex;
  /**
   * The next stop on the route.
   */
  private Stop nextStop;
  /**
   * The passenger generator associated with the route.
   */
  private PassengerGenerator generator;

  /**
   * Route constructor.
   *
   * @param id  identifier for route
   * @param name      name of route
   * @param stops     stops on this route
   * @param distances distances between subsequent stops of this route
   * @param generator Passenger generating object
   */
  public Route(int id, String name, List<Stop> stops,
               List<Double> distances, PassengerGenerator generator) {
    this.id = id;
    this.name = name;
    for (int i = 0; i < stops.size(); i++) {
      this.stops.add(stops.get(i));
    }
    for (int i = 0; i < stops.size() - 1; i++) {
      this.distances.add(distances.get(i));
    }
    this.generator = generator;
    this.nextStopIndex = 0;
    this.nextStop = stops.get(0);
  }

  /**
   * Shallow copies a route.
   * This method shallow copies a route as stops are shared
   * across copied routes
   *
   * @return Copy of route
   */
  public Route shallowCopy() {
    List<Stop> stops = new ArrayList<Stop>();
    for (int i = 0; i < this.stops.size(); i++) {
      stops.add(this.stops.get(i));
    }
    List<Double> distances = new ArrayList<Double>();
    for (int i = 0; i < this.stops.size() - 1; i++) {
      distances.add(this.distances.get(i));
    }
    Route shallowCopy = new Route(this.id, this.name, stops,
        distances, this.generator);
    return shallowCopy;
  }

  /**
   * Updates and generates passengers on the route.
   */
  public void update() {
    generateNewPassengers();
    Iterator<Stop> stopIter = this.stops.iterator();
    while (stopIter.hasNext()) {
      stopIter.next().update();
    }
  }

  /**
   * Report statistics for the route.
   *
   * @param out stream for printing
   */
  public void report(PrintStream out) {
    out.println("####Route Info Start####");
    out.println("ID: " + this.id);
    out.println("Name: " + this.name);
    out.println("Num stops: " + this.stops.size());
    int stopCounter = 0;
    Iterator<Stop> stopIter = this.stops.iterator();
    out.println("****Stops Info Start****");
    //calling all Stop's report methods
    while (stopIter.hasNext()) {
      if (stopCounter == this.nextStopIndex) {
        out.println("++++Next Stop Info Start++++");
      }
      stopIter.next().report(out);
      if (stopCounter == this.nextStopIndex) {
        out.println("++++Next Stop Info End++++");
      }
      stopCounter++;
    }
    out.println("****Stops Info End****");
    out.println("####Route Info End####");
  }

  /**
   * Checks if the route has reached its final stop.
   *
   * @return {@code true} if the route is at the end, otherwise {@code false}
   */
  public boolean isAtEnd() {
    return nextStopIndex >= stops.size();
  }

  /**
   * Returns previous stop.
   *
   * @return previous stop
   */
  public Stop prevStop() {
    if (nextStopIndex == 0) {
      return this.stops.get(0);
    } else if (nextStopIndex < stops.size()) {
      return this.stops.get(nextStopIndex - 1);
    } else {
      return this.stops.get(stops.size() - 1);
    }
  }

  /**
   * Updates nextStop to next stop.
   */
  public void nextStop() {
    nextStopIndex++;
    if (nextStopIndex < stops.size()) {
      nextStop = stops.get(nextStopIndex);
    } else {
      nextStop = stops.get(stops.size() - 1);
    }
  }

  /**
   * Returns next stop.
   *
   * @return next Stop
   */
  public Stop getNextStop() {
    return nextStop;
  }

  /**
   * Computes distance to next stop.
   *
   * @return distance
   */
  public Double getNextStopDistance() {
    if (nextStopIndex > 0) {
      return distances.get(nextStopIndex - 1);
    } else {
      return 0.0;
    }
  }

  /**
   * Returns and generates passengers.
   *
   * @return number of generated passengers
   */
  public int generateNewPassengers() {
    // returning number of passengers added by generator
    return this.generator.generatePassengers();
  }

  /**
   * Gets the unique identifier of the route.
   *
   * @return the route ID
   */
  public int getId() {
    return id;
  }

  /**
   * Gets the name of the route.
   *
   * @return the route name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the list of stops on the route.
   *
   * @return the list of stops
   */
  public List<Stop> getStops() {
    return stops;
  }

  /**
   * Gets the index of the next stop.
   *
   * @return the index of the next stop
   */
  public int getNextStopIndex() {
    return nextStopIndex;
  }
}

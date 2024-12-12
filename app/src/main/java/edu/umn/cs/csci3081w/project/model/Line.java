package edu.umn.cs.csci3081w.project.model;

import java.io.PrintStream;

/**
 * Represents a transportation line in the simulation.
 * A {@code Line} consists of an outbound and inbound route, and can be associated
 * with either buses or trains depending on its type.
 */
public class Line {

  /**
   * Constant for a bus line type.
   */
  public static final String BUS_LINE = "BUS_LINE";
  /**
   * Constant for a train line type.
   */
  public static final String TRAIN_LINE = "TRAIN_LINE";
  private int id;
  private String name;
  private String type;
  private Route outboundRoute;
  private Route inboundRoute;

  /**
   * Constructor for a Line.
   *
   * @param id       line identifier
   * @param name     line name
   * @param type     line type
   * @param outboundRoute outbound route
   * @param inboundRoute    inbound route
   */
  public Line(int id, String name, String type, Route outboundRoute, Route inboundRoute) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.outboundRoute = outboundRoute;
    this.inboundRoute = inboundRoute;
  }

  /**
   * Report statistics for the line.
   *
   * @param out stream for printing
   */
  public void report(PrintStream out) {
    out.println("====Line Info Start====");
    out.println("ID: " + id);
    out.println("Name: " + name);
    out.println("Type: " + type);
    outboundRoute.report(out);
    inboundRoute.report(out);
    out.println("====Line Info End====");
  }

  /**
   * Shallow copies a line.
   * This method shallow copies a line as routes are shared
   * across copied lines
   *
   * @return Copy of line
   */
  public Line shallowCopy() {
    Line shallowCopy = new Line(this.id, this.name, this.type,
        outboundRoute.shallowCopy(), inboundRoute.shallowCopy());
    return shallowCopy;
  }

  /**
   * Gets the unique identifier of the line.
   *
   * @return the line ID
   */
  public int getId() {
    return id;
  }

  /**
   * Gets the name of the line.
   *
   * @return the line name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the type of the line.
   * The type is typically either {@code BUS_LINE} or {@code TRAIN_LINE}.
   *
   * @return the line type
   */
  public String getType() {
    return type;
  }

  /**
   * Gets the outbound route of the line.
   *
   * @return the outbound route
   */
  public Route getOutboundRoute() {
    return this.outboundRoute;
  }

  /**
   * Gets the inbound route of the line.
   *
   * @return the inbound route
   */
  public Route getInboundRoute() {
    return this.inboundRoute;
  }
}

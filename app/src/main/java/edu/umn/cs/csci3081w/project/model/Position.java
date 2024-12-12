package edu.umn.cs.csci3081w.project.model;

/**
 * Represents a geographical position with longitude and latitude coordinates.
 * The {@code Position} class is used to track the location of entities
 * in the simulation, such as vehicles or stops.
 */
public class Position {

  private double longitude;
  private double latitude;

  /**
   * Constructs a {@code Position} with specified longitude and latitude.
   *
   * @param longitude the longitude of the position
   * @param latitude  the latitude of the position
   */
  public Position(double longitude, double latitude) {
    this.longitude = longitude;
    this.latitude = latitude;
  }

  /**
   * Returns the longitude of the position.
   *
   * @return the longitude
   */
  public double getLongitude() {
    return longitude;
  }

  /**
   * Returns the latitude of the position.
   *
   * @return the latitude
   */
  public double getLatitude() {
    return latitude;
  }

}

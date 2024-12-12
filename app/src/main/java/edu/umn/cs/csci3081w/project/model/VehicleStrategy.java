package edu.umn.cs.csci3081w.project.model;

/**
 * Interface representing a strategy for determining the next type of vehicle to create.
 * The {@code VehicleStrategy} is used in conjunction with factories to dynamically
 * decide the type of vehicle based on specific conditions or sequences.
 */
public interface VehicleStrategy {
  /**
   * Determines the type of the next vehicle to be created.
   *
   * @return the type of the next vehicle as a {@code String} (e.g., "electric", "diesel")
   */
  String getNextVehicle();
}

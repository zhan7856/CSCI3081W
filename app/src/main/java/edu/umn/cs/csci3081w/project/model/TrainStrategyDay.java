package edu.umn.cs.csci3081w.project.model;

/**
 * Interface representing the strategy for determining the type of train to create.
 * The {@code TrainStrategy} extends the {@code VehicleStrategy}, allowing for
 * flexibility in defining specific behaviors for train creation.
 */
public class TrainStrategyDay implements TrainStrategy {
  private int index = 0;
  private final String[] trains = {"electric", "diesel", "electric", "diesel"};

  @Override
  public String getNextVehicle() {
    String temp = trains[index];
    index = (index + 1) % trains.length;
    return temp;
  }
}

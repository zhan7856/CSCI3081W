package edu.umn.cs.csci3081w.project.model;

/**
 * Strategy for determining the type of train to create during the night.
 * The {@code TrainStrategyNight} favors electric trains with occasional diesel trains
 * based on a predefined sequence.
 */
public class TrainStrategyNight implements TrainStrategy {
  private int index = 0;
  private final String[] trains = {"electric", "electric", "electric", "diesel",
      "electric", "electric", "electric", "diesel"};

  @Override
  public String getNextVehicle() {
    String temp = trains[index];
    index = (index + 1) % trains.length;
    return temp;
  }
}

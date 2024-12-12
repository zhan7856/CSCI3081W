package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TrainStrategyNightTest {

  @Test
  void getNextVehicle() {
    TrainStrategyNight trainStrategyNight = new TrainStrategyNight();
    assertEquals("electric", trainStrategyNight.getNextVehicle());
    assertEquals("electric", trainStrategyNight.getNextVehicle());
    assertEquals("electric", trainStrategyNight.getNextVehicle());
    assertEquals("diesel", trainStrategyNight.getNextVehicle());
    assertEquals("electric", trainStrategyNight.getNextVehicle());
    assertEquals("electric", trainStrategyNight.getNextVehicle());
    assertEquals("electric", trainStrategyNight.getNextVehicle());
    assertEquals("diesel", trainStrategyNight.getNextVehicle());
    assertEquals("electric", trainStrategyNight.getNextVehicle());
  }
}
package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TrainStrategyDayTest {

  @Test
  void getNextVehicle() {
    TrainStrategyDay trainStrategyNight = new TrainStrategyDay();
    assertEquals("electric", trainStrategyNight.getNextVehicle());
    assertEquals("diesel", trainStrategyNight.getNextVehicle());
    assertEquals("electric", trainStrategyNight.getNextVehicle());
    assertEquals("diesel", trainStrategyNight.getNextVehicle());
    assertEquals("electric", trainStrategyNight.getNextVehicle());
  }
}
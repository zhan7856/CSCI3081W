package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import org.junit.jupiter.api.Test;

class TrainFactoryTest {

  @Test
  void createVehicleStrategyDay() {
    LocalTime dayTime = LocalTime.of(10, 0);
    TrainFactory trainFactory = new TrainFactory(dayTime);
    TrainStrategy strategy = trainFactory.createVehicleStrategy();
    assertTrue(strategy instanceof TrainStrategyDay, "should be daytime strategy");

  }

  @Test
  void createVehicleStrategyNight() {
    LocalTime nightTime = LocalTime.of(20, 0);
    TrainFactory trainFactory = new TrainFactory(nightTime);
    TrainStrategy strategy = trainFactory.createVehicleStrategy();
    assertTrue(strategy instanceof TrainStrategyNight, "shout be daytime strategy");

  }

}
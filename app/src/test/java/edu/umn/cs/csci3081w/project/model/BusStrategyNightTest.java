package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BusStrategyNightTest {

  private BusStrategyNight busStrategyNight;

  @BeforeEach
  public void setUp() {
    busStrategyNight = new BusStrategyNight();
  }

  @Test
  public void testGetNextVehicle() {
    String[] expectedOrder = {"small", "small", "small", "large",
        "small", "small", "small", "large"};
    for (int i = 0; i < expectedOrder.length; i++) {
      assertEquals(expectedOrder[i], busStrategyNight.getNextVehicle(),
          "Vehicle order mismatch at index " + i);
    }
  }

  @Test
  public void testGetNextVehicleCycles() {
    String[] expectedOrder = {"small", "small", "small", "large", "small",
        "small", "small", "large"};
    for (int i = 0; i < expectedOrder.length * 2; i++) { // Loop twice to verify cycling
      assertEquals(expectedOrder[i % expectedOrder.length], busStrategyNight.getNextVehicle(),
          "Vehicle order mismatch at index " + (i % expectedOrder.length));
    }
  }
}

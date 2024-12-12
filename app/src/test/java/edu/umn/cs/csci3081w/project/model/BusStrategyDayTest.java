package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BusStrategyDayTest {

  private BusStrategyDay busStrategyDay;

  @BeforeEach
  public void setUp() {
    busStrategyDay = new BusStrategyDay();
  }

  @Test
  public void testGetNextVehicle() {
    String[] expectedOrder = {"large", "large", "small", "large", "large", "small"};
    for (int i = 0; i < expectedOrder.length; i++) {
      assertEquals(expectedOrder[i], busStrategyDay.getNextVehicle(),
          "Vehicle order mismatch at index " + i);
    }
  }

  @Test
  public void testGetNextVehicleCycles() {
    String[] expectedOrder = {"large", "large", "small", "large", "large", "small"};
    for (int i = 0; i < expectedOrder.length * 2; i++) { // Loop twice to verify cycling
      assertEquals(expectedOrder[i % expectedOrder.length], busStrategyDay.getNextVehicle(),
          "Vehicle order mismatch at index " + (i % expectedOrder.length));
    }
  }
}

package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BusFactoryTest {

  private BusFactory busFactoryDay;
  private BusFactory busFactoryNight;


  /**
   * Initializes two instances of BusFactory, one for day time and one for night time.
   */
  @BeforeEach
  public void setUp() {
    LocalTime dayTime = LocalTime.of(10, 0);
    LocalTime nightTime = LocalTime.of(22, 0);

    busFactoryDay = new BusFactory(dayTime);
    busFactoryNight = new BusFactory(nightTime);
  }

  @Test
  public void testConstructor() {
    LocalTime testTime = LocalTime.of(12, 0);
    BusFactory testBusFactory = new BusFactory(testTime);
    assertTrue(testBusFactory instanceof BusFactory);
  }

  @Test
  public void testCreateVehicleStrategyDay() {
    BusStrategy strategy = busFactoryDay.createVehicleStrategy();
    assertTrue(strategy instanceof BusStrategyDay);
  }

  @Test
  public void testCreateVehicleStrategyNight() {
    BusStrategy strategy = busFactoryNight.createVehicleStrategy();
    assertTrue(strategy instanceof BusStrategyNight);
  }
}

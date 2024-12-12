package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CounterTest {

  private Counter counter;

  @BeforeEach
  public void setUp() {
    counter = new Counter();
  }

  @Test
  public void testGetRouteIdCounterAndIncrement() {
    int initialRouteId = 10; // Expected initial value
    assertEquals(initialRouteId, counter.getRouteIdCounterAndIncrement());
    assertEquals(initialRouteId + 1, counter.getRouteIdCounterAndIncrement());
  }

  @Test
  public void testGetStopIdCounterAndIncrement() {
    int initialStopId = 100; // Expected initial value
    assertEquals(initialStopId, counter.getStopIdCounterAndIncrement());
    assertEquals(initialStopId + 1, counter.getStopIdCounterAndIncrement());
  }

  @Test
  public void testGetBusIdCounterAndIncrement() {
    int initialBusId = 1000; // Expected initial value
    assertEquals(initialBusId, counter.getBusIdCounterAndIncrement());
    assertEquals(initialBusId + 1, counter.getBusIdCounterAndIncrement());
  }

  @Test
  public void testGetTrainIdCounterAndIncrement() {
    int initialTrainId = 2000; // Expected initial value
    assertEquals(initialTrainId, counter.getTrainIdCounterAndIncrement());
    assertEquals(initialTrainId + 1, counter.getTrainIdCounterAndIncrement());
  }

  @Test
  public void testGetLineIdCounterAndIncrement() {
    int initialLineId = 10000; // Expected initial value
    assertEquals(initialLineId, counter.getLineIdCounterAndIncrement());
    assertEquals(initialLineId + 1, counter.getLineIdCounterAndIncrement());
  }
}

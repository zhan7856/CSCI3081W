package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SmallBusTest {

  private SmallBus smallBus;
  private Route testRouteIn;
  private Route testRouteOut;

  /**
   * Sets up Small Bus instance for tests.
   */
  @BeforeEach
  public void setUp() {
    // Create mock routes
    List<Stop> stopsIn = new ArrayList<>();
    stopsIn.add(new Stop(0, "test stop 1", new Position(-93.243774, 44.972392)));
    stopsIn.add(new Stop(1, "test stop 2", new Position(-93.235071, 44.973580)));

    List<Double> distancesIn = new ArrayList<>();
    distancesIn.add(0.843774422231134);

    List<Double> probabilitiesIn = new ArrayList<>();
    probabilitiesIn.add(0.025);
    probabilitiesIn.add(0.3);

    PassengerGenerator generatorIn = new RandomPassengerGenerator(stopsIn, probabilitiesIn);
    testRouteIn = new Route(0, "testRouteIn", stopsIn, distancesIn, generatorIn);

    List<Stop> stopsOut = new ArrayList<>();
    stopsOut.add(new Stop(1, "test stop 2", new Position(-93.235071, 44.973580)));
    stopsOut.add(new Stop(0, "test stop 1", new Position(-93.243774, 44.972392)));

    List<Double> distancesOut = new ArrayList<>();
    distancesOut.add(0.843774422231134);

    List<Double> probabilitiesOut = new ArrayList<>();
    probabilitiesOut.add(0.3);
    probabilitiesOut.add(0.025);

    PassengerGenerator generatorOut = new RandomPassengerGenerator(stopsOut, probabilitiesOut);
    testRouteOut = new Route(1, "testRouteOut", stopsOut, distancesOut, generatorOut);

    Line testLine = new Line(1000, "testLine", "BUS", testRouteOut, testRouteIn);
    smallBus = new SmallBus(1, testLine, 40.0);
  }

  @Test
  public void testConstructor() {
    assertEquals(1, smallBus.getId());
    assertEquals("testLine", smallBus.getLine().getName());
    assertEquals(20, smallBus.getCapacity()); // SmallBus has a fixed capacity of 20
    assertEquals(40.0, smallBus.getSpeed());
  }

  @Test
  public void testGetCurrentCO2Emission() {
    // Initially, there are no passengers
    assertEquals(3, smallBus.getCurrentCO2Emission());

    // Add passengers and check CO2 emission
    Passenger passenger1 = new Passenger(3, "Passenger1");
    Passenger passenger2 = new Passenger(2, "Passenger2");
    smallBus.loadPassenger(passenger1);
    smallBus.loadPassenger(passenger2);

    // CO2 emission: (2 * passengers) + 3
    assertEquals(7, smallBus.getCurrentCO2Emission());
  }

  @Test
  public void testBusRoutes() {
    assertEquals(testRouteIn, smallBus.getLine().getInboundRoute());
    assertEquals(testRouteOut, smallBus.getLine().getOutboundRoute());
  }
}

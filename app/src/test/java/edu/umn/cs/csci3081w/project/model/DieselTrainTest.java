package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DieselTrainTest {

  private DieselTrain dieselTrain;
  private Route testRouteIn;
  private Route testRouteOut;

  /**
   * Sets up Diesel Train instance for tests.
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

    Line testLine = new Line(1000, "testLine", "TRAIN", testRouteOut, testRouteIn);
    dieselTrain = new DieselTrain(1, testLine, 100, 50.0);
  }

  @Test
  public void testConstructor() {
    assertEquals(1, dieselTrain.getId());
    assertEquals("testLine", dieselTrain.getLine().getName());
    assertEquals(100, dieselTrain.getCapacity());
    assertEquals(50.0, dieselTrain.getSpeed());
  }

  @Test
  public void testTrainRoutes() {
    assertEquals(testRouteIn, dieselTrain.getLine().getInboundRoute());
    assertEquals(testRouteOut, dieselTrain.getLine().getOutboundRoute());
  }

  @Test
  public void testCapacityAndSpeed() {
    assertEquals(100, dieselTrain.getCapacity());
    assertEquals(50.0, dieselTrain.getSpeed());
  }
}

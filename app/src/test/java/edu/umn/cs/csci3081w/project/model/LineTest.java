package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LineTest {

  private Line line;
  private Route outboundRoute;
  private Route inboundRoute;

  /**
   * Sets up Line instance for tests.
   */
  @BeforeEach
  public void setUp() {
    // Create mock routes
    List<Stop> stopsOutbound = new ArrayList<>();
    stopsOutbound.add(new Stop(0, "Outbound Stop 1",
        new Position(-93.243774, 44.972392)));
    stopsOutbound.add(new Stop(1, "Outbound Stop 2",
        new Position(-93.235071, 44.973580)));

    List<Double> distancesOutbound = new ArrayList<>();
    distancesOutbound.add(0.843774422231134);

    List<Double> probabilitiesOutbound = new ArrayList<>();
    probabilitiesOutbound.add(0.025);
    probabilitiesOutbound.add(0.3);

    PassengerGenerator generatorOutbound =
        new RandomPassengerGenerator(stopsOutbound, probabilitiesOutbound);
    outboundRoute = new Route(0, "Outbound Route", stopsOutbound,
        distancesOutbound, generatorOutbound);

    List<Stop> stopsInbound = new ArrayList<>();
    stopsInbound.add(new Stop(1, "Inbound Stop 1",
        new Position(-93.235071, 44.973580)));
    stopsInbound.add(new Stop(0, "Inbound Stop 2",
        new Position(-93.243774, 44.972392)));

    List<Double> distancesInbound = new ArrayList<>();
    distancesInbound.add(0.843774422231134);

    List<Double> probabilitiesInbound = new ArrayList<>();
    probabilitiesInbound.add(0.3);
    probabilitiesInbound.add(0.025);

    PassengerGenerator generatorInbound =
        new RandomPassengerGenerator(stopsInbound, probabilitiesInbound);
    inboundRoute =
        new Route(1, "Inbound Route", stopsInbound, distancesInbound, generatorInbound);

    line = new Line(1000, "Test Line", Line.BUS_LINE, outboundRoute, inboundRoute);
  }

  @Test
  public void testConstructor() {
    assertEquals(1000, line.getId());
    assertEquals("Test Line", line.getName());
    assertEquals(Line.BUS_LINE, line.getType());
    assertEquals(outboundRoute, line.getOutboundRoute());
    assertEquals(inboundRoute, line.getInboundRoute());
  }

  @Test
  public void testReport() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream testStream = new PrintStream(outputStream, true, StandardCharsets.UTF_8);

    line.report(testStream);

    String report = outputStream.toString(StandardCharsets.UTF_8);
    testStream.close();

    // Check for key elements in the report
    assertEquals(true, report.contains("====Line Info Start===="));
    assertEquals(true, report.contains("ID: 1000"));
    assertEquals(true, report.contains("Name: Test Line"));
    assertEquals(true, report.contains("Type: BUS_LINE"));
    assertEquals(true, report.contains("Outbound Route"));
    assertEquals(true, report.contains("Inbound Route"));
    assertEquals(true, report.contains("====Line Info End===="));
  }

  @Test
  public void testShallowCopy() {
    Line shallowCopy = line.shallowCopy();

    // Ensure the copy is a different object
    assertNotSame(line, shallowCopy);

    // Verify copied values are the same
    assertEquals(line.getId(), shallowCopy.getId());
    assertEquals(line.getName(), shallowCopy.getName());
    assertEquals(line.getType(), shallowCopy.getType());

    // Verify routes are shallow-copied
    assertNotSame(line.getOutboundRoute(), shallowCopy.getOutboundRoute());
    assertNotSame(line.getInboundRoute(), shallowCopy.getInboundRoute());
  }
}

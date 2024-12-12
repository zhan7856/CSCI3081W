package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the RandomPassengerGenerator class.
 */
public class RandomPassengerGeneratorTest {

  private RandomPassengerGenerator randomPassengerGenerator;

  /**
   * Initializes a RandomPassengerGenerator with a list of stops and their
   * corresponding passenger generation probabilities.
   */
  @BeforeEach
  public void setUp() {
    List<Stop> stops = new ArrayList<>();
    stops.add(new Stop(0, "Stop 1", new Position(-93.243774, 44.972392)));
    stops.add(new Stop(1, "Stop 2", new Position(-93.235071, 44.973580)));

    List<Double> probabilities = Arrays.asList(0.5, 0.3);

    randomPassengerGenerator = new RandomPassengerGenerator(stops, probabilities);
  }

  @Test
  public void testGeneratePassengersDeterministic() {
    RandomPassengerGenerator.DETERMINISTIC = true;
    int passengersGenerated = randomPassengerGenerator.generatePassengers();
    assertEquals(3, passengersGenerated,
        "Deterministic mode should generate exactly 3 passengers.");
  }

  @Test
  public void testGeneratePassengersNonDeterministic() {
    RandomPassengerGenerator.DETERMINISTIC = false;
    int passengersGenerated = randomPassengerGenerator.generatePassengers();
    assertEquals(true, passengersGenerated >= 0,
        "Non-deterministic mode should generate non-negative passengers.");
  }

  @Test
  public void testConstructor() {
    assertEquals(2, randomPassengerGenerator.getStops().size(),
        "Stops list size should be 2.");
    assertEquals(2, randomPassengerGenerator.getProbabilities().size(),
        "Probabilities list size should be 2.");
    assertEquals("Stop 1", randomPassengerGenerator.getStops().get(0).getName(),
        "First stop name should match.");
    assertEquals(0.5, randomPassengerGenerator.getProbabilities().get(0),
        "First probability should match.");
  }
}

package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassengerFactoryTest {

  /**
   * Sets up PassengerFactory for tests.
   */
  @BeforeEach
  public void setUp() {
    PassengerFactory.DETERMINISTIC = false; // Reset to default behavior
    PassengerFactory.DETERMINISTIC_NAMES_COUNT = 0; // Reset deterministic counters
    PassengerFactory.DETERMINISTIC_DESTINATION_COUNT = 0;
  }

  @Test
  public void testGeneratePassengerNonDeterministic() {
    Passenger passenger = PassengerFactory.generate(1, 5);

    // Verify the generated passenger has a valid destination
    assertTrue(passenger.getDestination() >= 2 && passenger.getDestination() <= 5,
        "Destination should be within valid range.");

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream testStream = new PrintStream(outputStream, true, StandardCharsets.UTF_8);
    passenger.report(testStream);
    String reportOutput = outputStream.toString(StandardCharsets.UTF_8);
    assertTrue(reportOutput.contains("####Passenger Info Start####"),
        "Report should include passenger info.");
  }


  @Test
  public void testNameGenerationNonDeterministic() {
    PassengerFactory.DETERMINISTIC = false; // Ensure non-deterministic mode

    String name = PassengerFactory.nameGeneration();
    assertTrue(name != null && !name.isEmpty(), "Generated name should not be null or empty.");
    assertTrue(Character.isUpperCase(name.charAt(0)), "Generated name should be capitalized.");
  }

  @Test
  public void testNameGenerationDeterministic() {
    PassengerFactory.DETERMINISTIC = true;

    // Generate names in order of deterministic names
    assertEquals("Goldy", PassengerFactory.nameGeneration(),
        "Expected deterministic name: Goldy.");
    assertEquals("President", PassengerFactory.nameGeneration(),
        "Expected deterministic name: President.");
    assertEquals("Coach", PassengerFactory.nameGeneration(),
        "Expected deterministic name: Coach.");

    // Loop back to the first deterministic name
    assertEquals("Goldy", PassengerFactory.nameGeneration(),
        "Expected deterministic name: Goldy.");
  }

  /**
   * Helper method to validate passenger information via report.
   */
  private void validatePassengerReport(Passenger passenger,
                                       String expectedName, int expectedDestination) {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream testStream = new PrintStream(outputStream, true, StandardCharsets.UTF_8);
    passenger.report(testStream);
    String reportOutput = outputStream.toString(StandardCharsets.UTF_8);

    // Validate the name and destination from the report
    assertTrue(reportOutput.contains("Name: " + expectedName),
        "Report should include name: " + expectedName);
    assertTrue(reportOutput.contains("Destination: " + expectedDestination),
        "Report should include destination: " + expectedDestination);
  }
}

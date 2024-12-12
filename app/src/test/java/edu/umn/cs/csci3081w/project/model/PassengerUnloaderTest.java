package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassengerUnloaderTest {

  private PassengerUnloader passengerUnloader;
  private List<Passenger> passengers;
  private Stop currentStop;

  /**
   * Initializes a PassengerUnloader instance, a list of passengers, and a current stop.
   */
  @BeforeEach
  public void setUp() {
    passengerUnloader = new PassengerUnloader();
    passengers = new ArrayList<>();

    // Create a mock current stop
    currentStop = new Stop(1, "Stop 1", new Position(-93.243774, 44.972392));

    // Add mock passengers
    passengers.add(new Passenger(1, "Passenger 1")); // Destination matches current stop
    passengers.add(new Passenger(2, "Passenger 2")); // Destination does not match
    passengers.add(new Passenger(1, "Passenger 3")); // Destination matches current stop
  }

  @Test
  public void testUnloadPassengers() {
    // Unload passengers at the current stop
    int unloadedCount = passengerUnloader.unloadPassengers(passengers, currentStop);

    // Verify the correct number of passengers were unloaded
    assertEquals(2, unloadedCount, "Two passengers should be unloaded.");

    // Verify the remaining passengers
    assertEquals(1, passengers.size(), "One passenger should remain.");
    assertEquals(2, passengers.get(0).getDestination(),
        "Remaining passenger should have destination 2.");
  }

  @Test
  public void testUnloadPassengersNoMatch() {
    // Set a stop ID that doesn't match any passenger destination
    Stop noMatchStop = new Stop(3, "Stop 3", new Position(-93.243774, 44.972392));

    // Attempt to unload passengers at a stop with no matches
    int unloadedCount = passengerUnloader.unloadPassengers(passengers, noMatchStop);

    // Verify no passengers were unloaded
    assertEquals(0, unloadedCount, "No passengers should be unloaded.");
    assertEquals(3, passengers.size(), "All passengers should remain.");
  }

  @Test
  public void testUnloadPassengersEmptyList() {
    // Clear passengers list to test edge case
    passengers.clear();

    // Attempt to unload passengers from an empty list
    int unloadedCount = passengerUnloader.unloadPassengers(passengers, currentStop);

    // Verify no passengers were unloaded
    assertEquals(0, unloadedCount, "No passengers should be unloaded.");
    assertEquals(0, passengers.size(), "Passenger list should remain empty.");
  }
}

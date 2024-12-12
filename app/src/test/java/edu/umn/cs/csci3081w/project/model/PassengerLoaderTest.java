package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassengerLoaderTest {

  private PassengerLoader passengerLoader;
  private List<Passenger> passengers;

  @BeforeEach
  public void setUp() {
    passengerLoader = new PassengerLoader();
    passengers = new ArrayList<>();
  }

  @Test
  public void testLoadPassengerSuccessfully() {
    Passenger passenger = new Passenger(5, "Test Passenger");

    // Maximum capacity is greater than the current number of passengers
    int result = passengerLoader.loadPassenger(passenger, 3, passengers);

    assertEquals(1, result, "Passenger should be successfully loaded.");
    assertEquals(1, passengers.size(), "Passenger list size should be 1.");
    assertEquals(passenger, passengers.get(0), "Passenger should be in the list.");
    assertEquals(true, passenger.isOnVehicle(), "Passenger should be marked as on vehicle.");
  }

  @Test
  public void testLoadPassengerFailDueToCapacity() {
    // Fill the passenger list to max capacity
    passengers.add(new Passenger(3, "Passenger 1"));
    passengers.add(new Passenger(4, "Passenger 2"));
    passengers.add(new Passenger(5, "Passenger 3"));

    Passenger newPassenger = new Passenger(6, "New Passenger");

    // Attempt to load a passenger when at max capacity
    int result = passengerLoader.loadPassenger(newPassenger, 3, passengers);

    assertEquals(0, result, "Passenger should not be loaded due to capacity limit.");
    assertEquals(3, passengers.size(), "Passenger list size should remain 3.");
    assertEquals(false, newPassenger.isOnVehicle(),
        "New passenger should not be marked as on vehicle.");
  }

  @Test
  public void testLoadPassengerEdgeCaseEmptyList() {
    Passenger passenger = new Passenger(5, "Edge Case Passenger");

    // Attempt to load passenger into an empty list
    int result = passengerLoader.loadPassenger(passenger, 1, passengers);

    assertEquals(1, result, "Passenger should be successfully loaded.");
    assertEquals(1, passengers.size(), "Passenger list size should be 1.");
    assertEquals(passenger, passengers.get(0), "Passenger should be in the list.");
    assertEquals(true, passenger.isOnVehicle(), "Passenger should be marked as on vehicle.");
  }
}

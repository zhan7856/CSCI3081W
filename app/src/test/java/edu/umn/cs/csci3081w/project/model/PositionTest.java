package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositionTest {

  private Position position;

  @BeforeEach
  public void setUp() {
    position = new Position(-93.243774, 44.972392); // Initialize with mock coordinates
  }

  @Test
  public void testConstructor() {
    // Verify that the constructor initializes the longitude and latitude correctly
    assertEquals(-93.243774, position.getLongitude(), 0.000001,
        "Longitude should match the initialized value.");
    assertEquals(44.972392, position.getLatitude(), 0.000001,
        "Latitude should match the initialized value.");
  }

  @Test
  public void testGetLongitude() {
    // Verify the getter for longitude
    assertEquals(-93.243774, position.getLongitude(), 0.000001,
        "Longitude should match the initialized value.");
  }

  @Test
  public void testGetLatitude() {
    // Verify the getter for latitude
    assertEquals(44.972392, position.getLatitude(), 0.000001,
        "Latitude should match the initialized value.");
  }
}

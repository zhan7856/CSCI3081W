package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VehicleSubjectTest {
  private VehicleSubject vehicleSubject;
  private VehicleObserver observer1;
  private VehicleObserver observer2;


  /**
   * Initializes a VehicleSubject and adds two VehicleObserver instances.
   */
  @BeforeEach
  public void setUp() {
    vehicleSubject = new VehicleSubject();
    observer1 = () -> "Vehicle 1 Info";
    observer2 = () -> "Vehicle 2 Info";

    vehicleSubject.addVehicleObserver(observer1);
    vehicleSubject.addVehicleObserver(observer2);
  }

  @Test
  public void testAddVehicleObserver() {
    assertEquals(2, vehicleSubject.getObservers().size());
  }

  @Test
  public void testRemoveVehicleObserver() {
    vehicleSubject.removeVehicleObserver(observer1);

    assertEquals(1, vehicleSubject.getObservers().size());
  }

  @Test
  public void testNotifyObservers() {
    assertDoesNotThrow(() -> vehicleSubject.notifyObservers(),
        "should not throw any exception");
  }
}

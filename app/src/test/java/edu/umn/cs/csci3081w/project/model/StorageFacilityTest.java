package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StorageFacilityTest {

  private StorageFacility storageFacility;

  @BeforeEach
  void setUp() {
    storageFacility = new StorageFacility(10, 5, 8, 3);
  }

  @Test
  void decrementSmallBusesNum() {
    storageFacility.decrementSmallBusesNum();
    assertEquals(9, storageFacility.getSmallBusesNum(),
        "Small buses number should decrement by 1");
  }

  @Test
  void decrementLargeBusesNum() {
    storageFacility.decrementLargeBusesNum();
    assertEquals(4, storageFacility.getLargeBusesNum(),
        "Large buses number should decrement by 1.");
  }

  @Test
  void decrementElectricTrainNum() {
    storageFacility.decrementElectricTrainNum();
    assertEquals(7, storageFacility.getElectricTrainsNum(),
        "Electric trains number should decrement by 1.");
  }

  @Test
  void decrementDieselTrainNum() {
    storageFacility.decrementDieselTrainNum();
    assertEquals(2, storageFacility.getDieselTrainsNum(),
        "Diesel trains number should decrement by 1.");
  }

  @Test
  void incrementSmallBusesNum() {
    storageFacility.incrementSmallBusesNum();
    assertEquals(11, storageFacility.getSmallBusesNum(),
        "Small buses number should increment by 1.");
  }

  @Test
  void incrementLargeBusesNum() {
    storageFacility.incrementLargeBusesNum();
    assertEquals(6, storageFacility.getLargeBusesNum(),
        "Large buses number should increment by 1.");
  }

  @Test
  void incrementElectricTrainNum() {
    storageFacility.incrementElectricTrainNum();
    assertEquals(9, storageFacility.getElectricTrainsNum(),
        "Electric trains number should increment by 1.");
  }

  @Test
  void incrementDieselTrainNum() {
    storageFacility.incrementDieselTrainNum();
    assertEquals(4, storageFacility.getDieselTrainsNum(),
        "Diesel trains number should increment by 1.");
  }

  @Test
  void testDefaultConstructor() {
    StorageFacility defaultFacility = new StorageFacility();
    assertEquals(0, defaultFacility.getSmallBusesNum(),
        "Default small buses number should be 0.");
    assertEquals(0, defaultFacility.getLargeBusesNum(),
        "Default large buses number should be 0.");
    assertEquals(0, defaultFacility.getElectricTrainsNum(),
        "Default electric trains number should be 0.");
    assertEquals(0, defaultFacility.getDieselTrainsNum(),
        "Default diesel trains number should be 0.");
  }
}

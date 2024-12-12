package edu.umn.cs.csci3081w.project.model;

import java.time.LocalTime;

/**
 * BusFactory class for creating bus vehicles in the simulation.
 * Uses {@code BusStrategy} to determine if a small bus or large bus
 * is created based on the current time of day.
 */
public class BusFactory extends VehicleFactory {
  /**
   * Strategy used to determine the type of bus to create.
   */
  private BusStrategy busStrategy;

  /**
   * Constructs a {@code BusFactory} with current time to determine the bus strategy.
   *
   * @param currTime the current time
   */
  public BusFactory(LocalTime currTime) {
    super(currTime);
    this.busStrategy = createVehicleStrategy();
  }

  @Override
  public BusStrategy createVehicleStrategy() {
    if (isDay) {
      return new BusStrategyDay();
    } else {
      return new BusStrategyNight();
    }
  }

  /**
   * Creates the next bus to be used in the simulation.
   * Determines the type of bus based on the BusStrategy.
   * Ensures the bus can only be created if the required type is available in the storage facility.
   *
   * @param storageFacility the facility that stores available small and large buses
   * @param line            the "route" that the bus will operate on
   * @param counter         the counter for generating unique bus IDs
   * @return a new {@code SmallBus} or {@code LargeBus} if available and {@code null} if not
   */
  public Bus getNextVehicle(StorageFacility storageFacility, Line line, Counter counter) {
    String busType = busStrategy.getNextVehicle();
    if (busType.equals("small") && storageFacility.getSmallBusesNum() > 0) {
      storageFacility.decrementSmallBusesNum();
      return new SmallBus(counter.getBusIdCounterAndIncrement(), line.shallowCopy(), Bus.SPEED);

    }
    if (busType.equals("large") && storageFacility.getSmallBusesNum() > 0) {
      storageFacility.decrementLargeBusesNum();
      return new LargeBus(counter.getBusIdCounterAndIncrement(), line.shallowCopy(), Bus.SPEED);
    }
    return null;
  }

}

package edu.umn.cs.csci3081w.project.model;

import java.time.LocalTime;

/**
 * Factory class for creating train vehicles in the simulation.
 * The {@code TrainFactory} determines whether to create an electric or diesel train
 * based on the current time of day using a {@code TrainStrategy}.
 */
public class TrainFactory extends VehicleFactory {
  private TrainStrategy trainStrategy;

  /**
   * Constructs a {@code TrainFactory} with the current time.
   * Initializes the appropriate train strategy (day or night) based on the time.
   *
   * @param currTime the current time
   */
  public TrainFactory(LocalTime currTime) {
    super(currTime);
    this.trainStrategy = createVehicleStrategy();
  }

  @Override
  public TrainStrategy createVehicleStrategy() {
    if (isDay) {
      return new TrainStrategyDay();
    } else {
      return new TrainStrategyNight();
    }
  }

  /**
   * Creates the next train to be used in the simulation.
   * Determines the type of train (electric or diesel) based on the {@code TrainStrategy}.
   * Ensures the train can only be created if the required type is available in the storage facility.
   *
   * @param storageFacility the facility that stores available electric and diesel trains
   * @param line            the route that the train will operate on
   * @param counter         the counter for generating unique train IDs
   * @return a new {@code ElectricTrain} or {@code DieselTrain} if available, or {@code null} if not
   */
  public Train getNextVehicle(StorageFacility storageFacility, Line line, Counter counter) {
    String busType = trainStrategy.getNextVehicle();
    if (busType.equals("electric") && storageFacility.getSmallBusesNum() > 0) {
      storageFacility.decrementElectricTrainNum();
      return new ElectricTrain(counter.getBusIdCounterAndIncrement(),
          line.shallowCopy(), Train.CAPACITY, Train.SPEED);

    }
    if (busType.equals("diesel") && storageFacility.getSmallBusesNum() > 0) {
      storageFacility.decrementDieselTrainNum();
      return new DieselTrain(counter.getBusIdCounterAndIncrement(),
          line.shallowCopy(), Train.CAPACITY, Train.SPEED);
    }
    return null;
  }
}

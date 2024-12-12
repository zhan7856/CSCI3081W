package edu.umn.cs.csci3081w.project.model;

/**
 * Interface representing the subject in the observer design pattern for vehicles.
 * The {@code VehicleSubjectInterface} allows observers to register, deregister,
 * and be notified of changes in the subject (e.g., a vehicle).
 */
public interface VehicleSubjectInterface {

  /**
   * Adds an observer to the vehicle subject.
   *
   * @param observer the observer to be added
   */
  void addVehicleObserver(VehicleObserver observer);

  /**
   * Removes an observer from the vehicle subject.
   *
   * @param observer the observer to be removed
   */
  void removeVehicleObserver(VehicleObserver observer);

  /**
   * Notifies all registered observers about a change in the vehicle subject.
   */
  void notifyObservers();
}

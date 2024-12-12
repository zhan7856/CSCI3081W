package edu.umn.cs.csci3081w.project.model;


import java.util.ArrayList;
import java.util.List;


public class VehicleSubject implements VehicleSubjectInterface {
  private List<VehicleObserver> observers;

  /**
   * Constructor for VehicleSubject.
   * Initializes an empty list of observers.
   */
  public VehicleSubject() {
    this.observers = new ArrayList<>();
  }

  /**
   * Adds a VehicleObserver to the list of observers.
   *
   * @param observer the VehicleObserver to be added
   */
  public void addVehicleObserver(VehicleObserver observer) {
    observers.add(observer);
  }

  /**
   * Removes a VehicleObserver from the list of observers.
   *
   * @param observer the VehicleObserver to be removed
   */
  public void removeVehicleObserver(VehicleObserver observer) {
    observers.remove(observer);
  }

  /**
   * Notifies all observers by calling their getVehicleInfo method.
   */
  public void notifyObservers() {
    for (VehicleObserver observer : observers) {
      observer.getVehicleInfo();
    }
  }

  /**
   * Retrieves the list of current VehicleObservers.
   *
   * @return the list of VehicleObservers
   */
  public List<VehicleObserver> getObservers() {
    return observers;
  }

}
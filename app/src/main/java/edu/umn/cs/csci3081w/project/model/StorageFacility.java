package edu.umn.cs.csci3081w.project.model;

/**
 * Represents a storage facility for buses and trains.
 * The {@code StorageFacility} manages the inventory of small and large buses,
 * as well as electric and diesel trains.
 */
public class StorageFacility {
  private int smallBusesNum;
  private int largeBusesNum;
  private int electricTrainsNum;
  private int dieselTrainsNum;

  /**
   * Constructs an empty {@code StorageFacility}.
   * Initializes all inventory counts to zero.
   */
  public StorageFacility() {
    smallBusesNum = 0;
    largeBusesNum = 0;
    electricTrainsNum = 0;
    dieselTrainsNum = 0;
  }

  /**
   * Constructs a {@code StorageFacility} with specified inventory counts.
   *
   * @param smallBusesNum    the number of small buses
   * @param largeBusesNum    the number of large buses
   * @param electricTrainsNum the number of electric trains
   * @param dieselTrainsNum   the number of diesel trains
   */
  public StorageFacility(int smallBusesNum, int largeBusesNum,
                         int electricTrainsNum, int dieselTrainsNum) {
    this.smallBusesNum = smallBusesNum;
    this.largeBusesNum = largeBusesNum;
    this.electricTrainsNum = electricTrainsNum;
    this.dieselTrainsNum = dieselTrainsNum;
  }

  /**
   * Gets the number of small buses available.
   *
   * @return the number of small buses
   */
  public int getSmallBusesNum() {
    return smallBusesNum;
  }

  /**
   * Sets the number of small buses.
   *
   * @param smallBusesNum the number of small buses to set
   */
  public void setSmallBusesNum(int smallBusesNum) {
    this.smallBusesNum = smallBusesNum;
  }

  /**
   * Gets the number of large buses available.
   *
   * @return the number of large buses
   */
  public int getLargeBusesNum() {
    return largeBusesNum;
  }

  /**
   * Sets the number of large buses.
   *
   * @param largeBusesNum the number of large buses to set
   */
  public void setLargeBusesNum(int largeBusesNum) {
    this.largeBusesNum = largeBusesNum;
  }

  /**
   * Gets the number of electric trains available.
   *
   * @return the number of electric trains
   */
  public int getElectricTrainsNum() {
    return electricTrainsNum;
  }

  /**
   * Sets the number of electric trains.
   *
   * @param electricTrainsNum the number of electric trains to set
   */
  public void setElectricTrainsNum(int electricTrainsNum) {
    this.electricTrainsNum = electricTrainsNum;
  }

  /**
   * Gets the number of diesel trains available.
   * @return the number of diesel trains
   */
  public int getDieselTrainsNum() {
    return dieselTrainsNum;
  }

  /**
   * Sets the number of diesel trains.
   *
   * @param dieselTrainsNum the number of diesel trains to set
   */
  public void setDieselTrainsNum(int dieselTrainsNum) {
    this.dieselTrainsNum = dieselTrainsNum;
  }

  /**
   * Decrements the count of small buses by one.
   */
  public void decrementSmallBusesNum() {
    smallBusesNum--;
  }

  /**
   * Decrements the count of large buses by one.
   */
  public void decrementLargeBusesNum() {
    largeBusesNum--;
  }

  /**
   * Decrements the count of electric trains by one.
   */
  public void decrementElectricTrainNum() {
    electricTrainsNum--;
  }

  /**
   * Decrements the count of diesel trains by one.
   */
  public void decrementDieselTrainNum() {
    dieselTrainsNum--;
  }

  /**
   * Increments the count of small buses by one.
   */
  public void incrementSmallBusesNum() {
    smallBusesNum++;
  }

  /**
   * Increments the count of large buses by one.
   */
  public void incrementLargeBusesNum() {
    largeBusesNum++;
  }

  /**
   * Increments the count of electric trains by one.
   */
  public void incrementElectricTrainNum() {
    electricTrainsNum++;
  }

  /**
   * Increments the count of diesel trains by one.
   */
  public void incrementDieselTrainNum() {
    dieselTrainsNum++;
  }

}

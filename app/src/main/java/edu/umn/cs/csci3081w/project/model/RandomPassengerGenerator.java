package edu.umn.cs.csci3081w.project.model;

import java.util.List;
import java.util.Random;

/**
 * Generates passengers randomly based on probabilities assigned to stops.
 * The {@code RandomPassengerGenerator} class uses probabilistic logic to determine
 * the number of passengers arriving at each stop. It supports deterministic behavior for testing.
 */
public class RandomPassengerGenerator extends PassengerGenerator {

  /**
   * Flag to enable deterministic testing.
   * If {@code true}, the generator uses predefined deterministic values for passenger generation.
   */
  public static boolean DETERMINISTIC = false;
  /**
   * Deterministic probability value used during testing.
   */
  public static final double DETERMINISTIC_VALUE = 0.1;

  /**
   * Constructs a {@code RandomPassengerGenerator} with a list of stops and their associated probabilities.
   *
   * @param stops         the list of stops for passenger generation
   * @param probabilities the list of probabilities for generating passengers at each stop
   */
  public RandomPassengerGenerator(List<Stop> stops, List<Double> probabilities) {
    super(stops, probabilities);
  }

  /**
   * Generates Passengers.
   * This method uses the route's passenger generation probabilities
   * per stop to determine how many passengers to create.
   * Each probability is a double, i.e., .90 for a 90% probability of
   * a passenger arriving at a stop for this vehicle route.
   * While the probability is greater than .0001 (.01%), we attempt to generate passengers.
   * Each time, we multiply the probability by itself, redoing the likelihood
   * of additional passengers each time.
   * This controls for multiple passengers arriving at a given time.
   * Once the probability drops below .0001, we end our generation cycle for the stop.
   *
   * @return number of generated passengers
   */
  public int generatePassengers() {
    int passengersAdded = 0;
    int probSize = getProbabilities().size();
    int stopSize = this.getStops().size();
    int stopIndex = this.getStops().get(0).getId();
    int lastStopIndex = this.getStops().get(this.getStops().size() - 1).getId();
    int probCount = 0;
    int stopCount = 0;
    while (probCount < probSize && stopCount < stopSize) {
      // Get this stop's probability
      double initialGenerationProbability = getProbabilities().get(probCount);
      double currentGenerationProbability = initialGenerationProbability;
      // while there is still a (>.01%) chance of generating a passenger, try
      while (currentGenerationProbability > 0.0001 && stopIndex != lastStopIndex) {
        // Generate a random double value
        Random rand = new Random();
        double generationValue = 0;
        if (RandomPassengerGenerator.DETERMINISTIC) {
          generationValue = RandomPassengerGenerator.DETERMINISTIC_VALUE;
        } else {
          generationValue = rand.nextDouble();
        }
        if (generationValue < currentGenerationProbability) {
          Passenger p = PassengerFactory.generate(stopIndex, lastStopIndex);
          passengersAdded += this.getStops().get(stopCount).addPassengers(p);
        }
        currentGenerationProbability *= initialGenerationProbability;
      }
      stopIndex++;
      probCount++;
      stopCount++;
    }
    return passengersAdded;
  }
}
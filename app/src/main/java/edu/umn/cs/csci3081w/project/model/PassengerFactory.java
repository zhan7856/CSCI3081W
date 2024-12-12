package edu.umn.cs.csci3081w.project.model;

import java.util.Random;

/**
 * PassengerFactory class for creating passengers in the simulation.
 * The {@code PassengerFactory} generates passengers with randomized or deterministic names
 * and destinations based on the current stop and last stop.
 */
public class PassengerFactory {
  /**
   * Array of prefixes used for generating random names.
   */
  private static final String[] namePrefix;
  /**
   * Array of suffixes used for generating random names.
   */
  private static final String[] nameSuffix;
  /**
   * Array of stems used for generating random names.
   */
  private static final String[] nameStemsArray;
  /**
   * Random number generator for creating names and destinations.
   */
  private static Random rand;
  /**
   * Flag to enable deterministic testing.
   */
  public static boolean DETERMINISTIC = false;
  /**
   * Deterministic set of names for testing purposes.
   */
  public static final String[] DETERMINISTIC_NAMES = {"Goldy", "President", "Coach"};
  /**
   * Counter to track the number of deterministic names used.
   */
  public static int DETERMINISTIC_NAMES_COUNT = 0;
  /**
   * Counter to track the number of deterministic destinations used.
   */
  public static int DETERMINISTIC_DESTINATION_COUNT = 0;

  static {
    namePrefix = new String[]{
        "",  // who said we need to add a prefix?
        "bel",  // lets say that means "the good"
        "nar",  // "The not so good as Bel"
        "xan",  //" The evil"
        "bell",  // "the good"
        "natr",  // "the neutral/natral"
        "ev",  // Man am I original
    };
    nameSuffix = new String[]{
        "", "us", "ix", "ox", "ith",
        "ath", "um", "ator", "or", "axia",
        "imus", "ais", "itur", "orex", "o",
        "y"
    };
    nameStemsArray = new String[]{
        "adur", "aes", "anim", "apoll", "imac",
        "educ", "equis", "extr", "guius", "hann",
        "equi", "amora", "hum", "iace", "ille",
        "inept", "iuv", "obe", "ocul", "orbis"
    };
    rand = new Random();
  }

  /**
   * Creates a passenger.
   *
   * @param currStop current stop
   * @param lastStop Last stop
   * @return Generated passenger
   */
  public static Passenger generate(int currStop, int lastStop) {
    String newName = nameGeneration();
    int destination = 0;
    if (PassengerFactory.DETERMINISTIC) {
      destination =
          ((PassengerFactory.DETERMINISTIC_DESTINATION_COUNT + 1) % (lastStop - currStop))
              + currStop + 1;
      PassengerFactory.DETERMINISTIC_DESTINATION_COUNT++;
    } else {
      destination = ((rand.nextInt(1000) + 1) % (lastStop - currStop)) + currStop + 1;
    }
    return new Passenger(destination, newName);
  }

  /**
   * Generates a name for the passenger.
   *
   * @return Name
   */
  public static String nameGeneration() {
    String str = "";
    if (PassengerFactory.DETERMINISTIC) {
      int nameIndex =
          PassengerFactory.DETERMINISTIC_NAMES_COUNT % PassengerFactory.DETERMINISTIC_NAMES.length;
      str = PassengerFactory.DETERMINISTIC_NAMES[nameIndex];
      PassengerFactory.DETERMINISTIC_NAMES_COUNT++;
    } else {
      str = namePrefix[(rand.nextInt(1000) + 1) % 7]
          + nameStemsArray[(rand.nextInt(1000) + 1) % 20]
          + nameSuffix[(rand.nextInt(1000) + 1) % 16];
    }
    // Capitalize the string
    String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
    return cap;
  }
}

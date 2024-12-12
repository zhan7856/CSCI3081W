package edu.umn.cs.csci3081w.project.webserver;

import edu.umn.cs.csci3081w.project.model.Counter;
import edu.umn.cs.csci3081w.project.model.Line;
import edu.umn.cs.csci3081w.project.model.Position;
import edu.umn.cs.csci3081w.project.model.RandomPassengerGenerator;
import edu.umn.cs.csci3081w.project.model.Route;
import edu.umn.cs.csci3081w.project.model.Stop;
import edu.umn.cs.csci3081w.project.model.StorageFacility;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.apache.commons.io.FileUtils;

/**
 * The ConfigManager class handles the configuration file, parsing it to create
 * lines, routes, stops, and a storage facility. It supports reading a configuration
 * file and storing the resulting data into appropriate data structures.
 */
public class ConfigManager {
  private static final String LINE_START = "LINE_START";
  private static final String LINE_END = "LINE_END";
  private static final String ROUTE_START = "ROUTE_START";
  private static final String ROUTE_END = "ROUTE_END";
  private static final String BUS_LINE = "BUS_LINE";
  private static final String TRAIN_LINE = "TRAIN_LINE";
  private static final String STORAGE_FACILITY_START = "STORAGE_FACILITY_START";
  private static final String SMALL_BUSES = "SMALL_BUSES";
  private static final String LARGE_BUSES = "LARGE_BUSES";
  private static final String ELECTRIC_TRAINS = "ELECTRIC_TRAINS";
  private static final String DIESEL_TRAINS = "DIESEL_TRAINS";
  private static final String STOP = "STOP";

  private List<Line> lines = new ArrayList<Line>();
  private List<Route> routes = new ArrayList<Route>();
  private StorageFacility storageFacility;

  public ConfigManager() {
    storageFacility = null;
  }

  /**
   * This method reads the configuration file, which contains information
   * about lines, routes, and stops.
   *
   * @param counter counter for identifiers
   * @param fileName the file name of the configuration file
   */
  public void readConfig(Counter counter, String fileName) {
    File configFile = FileUtils.getFile(fileName);
    try {
      String currLineName = "";
      String currLineType = "";
      String currRouteName = "";
      List<Stop> stops = new ArrayList<Stop>();
      List<Double> probabilities = new ArrayList<Double>();
      Scanner scanner = new Scanner(configFile);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] splits = line.split(",");
        String chunk = splits[0].trim();
        if (chunk.equals(ConfigManager.LINE_START)) {
          currLineType = "";
          String lineTypeFromConfig = splits[1].trim();
          if (lineTypeFromConfig.equals(ConfigManager.BUS_LINE)) {
            currLineType = Line.BUS_LINE;
          } else if (lineTypeFromConfig.equals(ConfigManager.TRAIN_LINE)) {
            currLineType = Line.TRAIN_LINE;
          }
          currLineName = splits[2].trim();
        } else if (chunk.equals(ConfigManager.LINE_END)) {
          lines.add(new Line(counter.getLineIdCounterAndIncrement(),
              currLineName, currLineType,
              routes.get(routes.size() - 2), routes.get(routes.size() - 1)));
          currLineType = "";
          currLineName = "";
        } else if (chunk.equals(ConfigManager.ROUTE_START)) {
          currRouteName = splits[1].trim();
        } else if (chunk.equals(ConfigManager.ROUTE_END)) {
          if (stops.size() > 0) {
            List<Double> distances = new ArrayList<Double>();
            for (int stopIndex = 1; stopIndex < stops.size(); ++stopIndex) {
              Stop prevStop = stops.get(stopIndex - 1);
              Stop currStop = stops.get(stopIndex);
              // Need to turn latitude and longitude into real-world distances.
              // Going one speed in a simulation step moves 0.5 mile.
              // We multiply latitude and longitude by 2 so that a speed of one moves 0.5 mile
              double prevLatitude = prevStop.getPosition().getLatitude() * 69 * 2;
              double prevLongitude = prevStop.getPosition().getLongitude() * 55 * 2;
              double currLatitude = currStop.getPosition().getLatitude() * 69 * 2;
              double currLongitude = currStop.getPosition().getLongitude() * 55 * 2;
              double dist = Math.sqrt((currLatitude - prevLatitude) * (currLatitude - prevLatitude)
                  + (currLongitude - prevLongitude) * (currLongitude - prevLongitude));
              distances.add(dist);
            }
            routes.add(
                new Route(counter.getRouteIdCounterAndIncrement(),
                    currRouteName, stops, distances,
                    new RandomPassengerGenerator(stops, probabilities)));
            currRouteName = "";
            stops.clear();
            probabilities.clear();
          }
        } else if (chunk.equals(ConfigManager.STOP)) {
          String currStopName = splits[1].trim();
          double currStopLatitude = Double.valueOf(splits[2].trim());
          double currStopLongitude = Double.valueOf(splits[3].trim());
          double probability = Double.valueOf(splits[4].trim());
          probabilities.add(probability);
          stops.add(new Stop(counter.getStopIdCounterAndIncrement(), currStopName,
              new Position(currStopLongitude, currStopLatitude)));
        } else if (chunk.equals(ConfigManager.STORAGE_FACILITY_START)) {
          this.storageFacility = new StorageFacility();
        } else if (chunk.equals(ConfigManager.SMALL_BUSES)) {
          int numSmallBuses = Integer.parseInt(splits[1].trim());
          this.storageFacility.setSmallBusesNum(numSmallBuses);
        } else if (chunk.equals(ConfigManager.LARGE_BUSES)) {
          int numLargeBuses = Integer.parseInt(splits[1].trim());
          this.storageFacility.setLargeBusesNum(numLargeBuses);
        } else if (chunk.equals(ConfigManager.ELECTRIC_TRAINS)) {
          int numElectricTrains = Integer.parseInt(splits[1].trim());
          this.storageFacility.setElectricTrainsNum(numElectricTrains);
        } else if (chunk.equals(ConfigManager.DIESEL_TRAINS)) {
          int numDieselTrains = Integer.parseInt(splits[1].trim());
          this.storageFacility.setDieselTrainsNum(numDieselTrains);
        }

      }
      scanner.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets the list of lines created from the configuration file.
   *
   * @return a list of line objects.
   */
  public List<Line> getLines() {
    return lines;
  }

  /**
   * Gets the list of routes created from the configuration file.
   *
   * @return a list of route objects.
   */
  public List<Route> getRoutes() {
    return routes;
  }

  /**
   * Gets the storage facility created from the configuration file.
   *
   * @return a StorageFacility object.
   */
  public StorageFacility getStorageFacility() {
    return storageFacility;
  }
}
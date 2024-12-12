package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonObject;
import edu.umn.cs.csci3081w.project.model.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisualTransitSimulator {

  private static boolean LOGGING = false;
  private int numTimeSteps = 0;
  private int simulationTimeElapsed = 0;
  private Counter counter;
  private List<Line> lines;
  private List<Vehicle> activeVehicles;
  private List<Vehicle> completedTripVehicles;
  private List<Integer> vehicleStartTimings;
  private List<Integer> timeSinceLastVehicle;
  private StorageFacility storageFacility;
  private WebServerSession webServerSession;
  private LocalTime currTime = LocalDateTime.now().toLocalTime();
  private BusFactory busFactory;
  private TrainFactory trainFactory;
  private boolean paused = false;
  private Map<Integer, Integer> pauseLines = new HashMap<>();
  private VehicleSubject vehicleSubject;

  /**
   * Constructor for Simulation.
   *
   * @param configFile       file containing the simulation configuration
   * @param webServerSession session associated with the simulation
   */
  public VisualTransitSimulator(String configFile, WebServerSession webServerSession) {
    this.webServerSession = webServerSession;
    this.counter = new Counter();
    ConfigManager configManager = new ConfigManager();
    configManager.readConfig(counter, configFile);
    this.lines = configManager.getLines();
    this.activeVehicles = new ArrayList<Vehicle>();
    this.completedTripVehicles = new ArrayList<Vehicle>();
    this.vehicleStartTimings = new ArrayList<Integer>();
    this.timeSinceLastVehicle = new ArrayList<Integer>();
    this.storageFacility = configManager.getStorageFacility();
    this.busFactory = new BusFactory(currTime);
    this.trainFactory = new TrainFactory(currTime);
    this.vehicleSubject = new VehicleSubject();
    if (this.storageFacility == null) {
      this.storageFacility = new StorageFacility(Integer.MAX_VALUE, Integer.MAX_VALUE,
          Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    if (VisualTransitSimulator.LOGGING) {
      System.out.println("////Simulation Routes////");
      for (int i = 0; i < lines.size(); i++) {
        lines.get(i).getInboundRoute().report(System.out);
        lines.get(i).getOutboundRoute().report(System.out);
      }
    }
  }

  /**
   * Starts the simulation.
   *
   * @param vehicleStartTimings start timings of bus
   * @param numTimeSteps        number of time steps
   */
  public void start(List<Integer> vehicleStartTimings, int numTimeSteps) {
    this.vehicleStartTimings = vehicleStartTimings;
    this.numTimeSteps = numTimeSteps;
    for (int i = 0; i < vehicleStartTimings.size(); i++) {
      this.timeSinceLastVehicle.add(i, 0);
    }
    simulationTimeElapsed = 0;
  }

  /**
   * Toggles the pause state of the simulation.
   */
  public void togglePause() {
    paused = !paused;
  }

  /**
   * Updates the simulation at each step.
   */
  public void update() {
    if (!paused) {
      simulationTimeElapsed++;
      if (simulationTimeElapsed > numTimeSteps) {
        return;
      }
      System.out.println("~~~~The simulation time is now at time step "
          + simulationTimeElapsed + "~~~~");
      // generate vehicles
      for (int i = 0; i < timeSinceLastVehicle.size(); i++) {
        if (timeSinceLastVehicle.get(i) <= 0) {
          Route outbound = lines.get(i).getOutboundRoute();
          Line line = findLineBasedOnRoute(outbound);
          Vehicle nextVehicle = null;
          if (line.getType().equals(Line.BUS_LINE)) {
            nextVehicle = busFactory.getNextVehicle(storageFacility, line.shallowCopy(), counter);
          } else if (line.getType().equals(Line.TRAIN_LINE)) {
            nextVehicle = trainFactory.getNextVehicle(storageFacility, line.shallowCopy(), counter);
          }
          if (nextVehicle != null) {
            activeVehicles.add(nextVehicle);

            timeSinceLastVehicle.set(i, vehicleStartTimings.get(i));
            timeSinceLastVehicle.set(i, timeSinceLastVehicle.get(i) - 1);
          }
        } else {
          timeSinceLastVehicle.set(i, timeSinceLastVehicle.get(i) - 1);
        }
      }
      // update vehicles
      for (int i = activeVehicles.size() - 1; i >= 0; i--) {
        Vehicle currVehicle = activeVehicles.get(i);

        // Check if the vehicle's line has an issue
        Line vehicleLine = currVehicle.getLine();
        if (pauseLines.containsKey(vehicleLine.getId())) {
          if (pauseLines.get(vehicleLine.getId()) > 0) {
            if (VisualTransitSimulator.LOGGING) {
              System.out.println("Vehicle " + currVehicle.getId()
                  + " on line " + vehicleLine.getId()
                  + " is paused due to a line issue.");
            }
            continue;
          } else {
            pauseLines.remove(vehicleLine.getId());
            if (VisualTransitSimulator.LOGGING) {
              System.out.println("Line " + vehicleLine.getId() + " issue has been resolved.");
            }
          }
        }

        currVehicle.update();
        vehicleSubject.notifyObservers();
        if (vehicleSubject.getObservers().contains(currVehicle)) {
          JsonObject data = new JsonObject();
          data.addProperty("command", "observedVehicle");
          data.addProperty("text", currVehicle.getVehicleInfo());
          webServerSession.sendJson(data);
        }
        if (currVehicle.isTripComplete()) {
          Vehicle completedTripVehicle = activeVehicles.remove(i);
          completedTripVehicles.add(completedTripVehicle);
          if (completedTripVehicle instanceof SmallBus) {
            storageFacility.incrementSmallBusesNum();
          } else if (completedTripVehicle instanceof LargeBus) {
            storageFacility.incrementLargeBusesNum();
          } else if (completedTripVehicle instanceof ElectricTrain) {
            storageFacility.incrementElectricTrainNum();
          } else if (completedTripVehicle instanceof DieselTrain) {
            storageFacility.incrementDieselTrainNum();
          }
        } else {
          if (VisualTransitSimulator.LOGGING) {
            currVehicle.report(System.out);
          }
        }
      }
      // update routes
      for (int i = 0; i < lines.size(); i++) {
        Route outbound = lines.get(i).getOutboundRoute();
        Route inbound = lines.get(i).getInboundRoute();
        inbound.update();
        outbound.update();
        if (VisualTransitSimulator.LOGGING) {
          inbound.report(System.out);
          outbound.report(System.out);
        }
      }
    }
  }

  /**
   * Method to find the line of a route.
   *
   * @param route route to search
   * @return Line containing route
   */
  public Line findLineBasedOnRoute(Route route) {
    for (Line line : lines) {
      if (line.getOutboundRoute().getId() == route.getId()
          || line.getInboundRoute().getId() == route.getId()) {
        return line;
      }
    }
    throw new RuntimeException("Could not find the line of the route");
  }

  public List<Line> getLines() {
    return lines;
  }

  public List<Vehicle> getActiveVehicles() {
    return activeVehicles;
  }

  /**
   * simulates line issue
   * @param lineId id of the line
   * @param durationPause how long to pause
   */
  public void simulateLineIssue(int lineId, int durationPause) {
    pauseLines.put(lineId, durationPause);
  }

  public Vehicle findVehicleWithId(int vehicleId) {
    for (Vehicle vehicle : activeVehicles) {
      if (vehicle.getId() == vehicleId) {
        return vehicle;
      }
    }
    return null;
  }
}
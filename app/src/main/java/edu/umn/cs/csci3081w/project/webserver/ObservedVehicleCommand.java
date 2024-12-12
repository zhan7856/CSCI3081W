package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonObject;
import edu.umn.cs.csci3081w.project.model.Vehicle;

public class ObservedVehicleCommand extends SimulatorCommand {

  private VisualTransitSimulator simulator;

  public ObservedVehicleCommand(VisualTransitSimulator simulator) {
    this.simulator = simulator;
  }

  @Override
  public void execute(WebServerSession session, JsonObject command) {
    int vehicleId = command.get("id").getAsInt();
    Vehicle observedVehicle = simulator.findVehicleWithId(vehicleId);

    if (observedVehicle != null) {
      JsonObject data = new JsonObject();
      data.addProperty("command", "observedVehicle");

      data.addProperty("text", observedVehicle.getVehicleInfo());

      session.sendJson(data);
    } else {
      JsonObject errorData = new JsonObject();
      errorData.addProperty("command", "observedVehicle");
      errorData.addProperty("text", "vehicle with ID " + vehicleId + " not found");
      session.sendJson(errorData);
    }
  }
}

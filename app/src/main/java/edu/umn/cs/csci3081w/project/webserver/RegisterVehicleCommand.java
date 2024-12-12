package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonObject;

public class RegisterVehicleCommand extends SimulatorCommand {

  private ObservedVehicleCommand observedVehicleCommand;

  public RegisterVehicleCommand(VisualTransitSimulator simulator) {
    this.observedVehicleCommand = new ObservedVehicleCommand(simulator);
  }

  @Override
  public void execute(WebServerSession session, JsonObject command) {
    observedVehicleCommand.execute(session, command);
  }
}
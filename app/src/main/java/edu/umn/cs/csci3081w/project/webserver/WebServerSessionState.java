package edu.umn.cs.csci3081w.project.webserver;

import java.util.HashMap;
import java.util.Map;

/**
 * The WebServerSessionState class maintains the state of the web server session.
 * It contains a mapping of commands associated with the simulator.
 */
public class WebServerSessionState {

  /**
   * A map storing commands associated with their respective string identifiers.
   */
  private Map<String, SimulatorCommand> commands;

  /**
   * Constructs a new WebServerSessionState object with an empty command map.
   */
  public WebServerSessionState() {
    this.commands = new HashMap<String, SimulatorCommand>();
  }

  /**
   * Retrieves the map of commands stored in this session state.
   *
   * @return a map where the keys are string identifiers, and the values are {@link SimulatorCommand} objects.
   */
  public Map<String, SimulatorCommand> getCommands() {
    return commands;
  }
}

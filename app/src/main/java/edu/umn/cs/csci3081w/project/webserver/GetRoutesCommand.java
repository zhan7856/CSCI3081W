package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import edu.umn.cs.csci3081w.project.model.Line;
import java.util.HashSet;
import java.util.List;

public class GetRoutesCommand extends SimulatorCommand {

  private VisualTransitSimulator simulator;

  public GetRoutesCommand(VisualTransitSimulator simulator) {
    this.simulator = simulator;
  }

  /**
   * Retrieves routes information from the simulation.
   *
   * @param session current simulation session
   * @param command the get routes command content
   */
  @Override
  public void execute(WebServerSession session, JsonObject command) {
    List<Line> lines = simulator.getLines();
    JsonObject data = new JsonObject();
    data.addProperty("command", "updateRoutes");
    JsonArray routesArray = new JsonArray();
    for (int i = 0; i < lines.size(); i++) {
      JsonObject r = new JsonObject();
      r.addProperty("id", lines.get(i).getId());
      JsonArray stopArray = new JsonArray();
      HashSet<Integer> set = new HashSet<Integer>();
      for (int j = 0; j < (lines.get(i).getOutboundRoute().getStops().size()); j++) {
        JsonObject stopStruct = new JsonObject();
        set.add(lines.get(i).getOutboundRoute().getStops().get(j).getId());
        stopStruct.addProperty("id", lines.get(i).getOutboundRoute().getStops().get(j).getId());
        stopStruct.addProperty("numPeople",
            lines.get(i).getOutboundRoute().getStops().get(j).getPassengers().size());
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("longitude",
            lines.get(i).getOutboundRoute().getStops().get(j).getPosition().getLongitude());
        jsonObj.addProperty("latitude",
            lines.get(i).getOutboundRoute().getStops().get(j).getPosition().getLatitude());
        stopStruct.add("position", jsonObj);
        stopArray.add(stopStruct);
      }
      for (int j = 0; j < (lines.get(i).getInboundRoute().getStops().size()); j++) {
        JsonObject stopStruct = new JsonObject();
        if (set.contains(lines.get(i).getInboundRoute().getStops().get(j).getId())) {
          continue;
        }
        stopStruct.addProperty("id", lines.get(i).getInboundRoute().getStops().get(j).getId());
        stopStruct.addProperty("numPeople",
            lines.get(i).getInboundRoute().getStops().get(j).getPassengers().size());
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("longitude",
            lines.get(i).getInboundRoute().getStops().get(j).getPosition().getLongitude());
        jsonObj.addProperty("latitude",
            lines.get(i).getInboundRoute().getStops().get(j).getPosition().getLatitude());
        stopStruct.add("position", jsonObj);
        stopArray.add(stopStruct);
      }
      r.add("stops", stopArray);
      routesArray.add(r);
    }
    data.add("routes", routesArray);
    session.sendJson(data);
  }
}

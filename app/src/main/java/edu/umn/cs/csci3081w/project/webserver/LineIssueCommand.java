package edu.umn.cs.csci3081w.project.webserver;

import com.google.gson.JsonObject;

public class LineIssueCommand extends SimulatorCommand {
  private VisualTransitSimulator visSim;

  public LineIssueCommand(VisualTransitSimulator visSim) {
    this.visSim = visSim;
  }

  @Override
  public void execute(WebServerSession session, JsonObject command) {
    int lineId = command.get("id").getAsInt();
    visSim.simulateLineIssue(lineId, 10);

    JsonObject obj = new JsonObject();
    obj.addProperty("lineId", lineId);

    session.sendJson(obj);
  }
}
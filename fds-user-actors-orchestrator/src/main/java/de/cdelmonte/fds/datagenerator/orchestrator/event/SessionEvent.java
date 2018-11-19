package de.cdelmonte.fds.datagenerator.orchestrator.event;

import de.cdelmonte.fds.datagenerator.orchestrator.model.EventModel;
import de.cdelmonte.fds.datagenerator.orchestrator.model.Session;


public class SessionEvent implements Event {

  private Session session;

  public SessionEvent(Session session) {
    this.session = session;
  }

  @Override
  public EventModel getModel() {
    return session;
  }
}

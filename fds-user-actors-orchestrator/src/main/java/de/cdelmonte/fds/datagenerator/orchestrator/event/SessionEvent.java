package de.cdelmonte.fds.datagenerator.orchestrator.event;

import java.util.UUID;

public class SessionEvent implements Event {

  private UUID sessionId;

  public SessionEvent() {
    sessionId = UUID.randomUUID();
  }

  public UUID getSessionId() {
    return sessionId;
  }

  public void addClick(ClickEvent click) {

  }

}

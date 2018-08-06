package de.cdelmonte.fds.datagenerator.orchestrator.observer;

import java.io.Serializable;

import de.cdelmonte.fds.datagenerator.orchestrator.event.Event;


public interface Observer extends Serializable {
  public void update(Event event);
}

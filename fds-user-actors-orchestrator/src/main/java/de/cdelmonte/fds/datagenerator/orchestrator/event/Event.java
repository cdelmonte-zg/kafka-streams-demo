package de.cdelmonte.fds.datagenerator.orchestrator.event;

import de.cdelmonte.fds.datagenerator.orchestrator.model.EventModel;

public class Event<T extends EventModel> {
  private T model;

  public Event(T model) {
    this.model = model;
  }

  public T getModel() {
    return model;
  }
}

package de.cdelmonte.fds.datagenerator.orchestrator.observer;

import java.io.Serializable;

import de.cdelmonte.fds.datagenerator.orchestrator.event.Event;


public interface Observable extends Serializable {
  public void addObserver(ObservableEventType e, Observer o);

  public void removeObserver(ObservableEventType e, Observer o);

  public void notifyObservers(ObservableEventType e, Event a);
}

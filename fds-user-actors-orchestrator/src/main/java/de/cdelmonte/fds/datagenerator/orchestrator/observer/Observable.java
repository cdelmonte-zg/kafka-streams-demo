package de.cdelmonte.fds.datagenerator.orchestrator.observer;

import java.io.Serializable;

import de.cdelmonte.fds.datagenerator.orchestrator.event.Event;
import de.cdelmonte.fds.datagenerator.orchestrator.model.EventModel;


public interface Observable<T extends EventModel> extends Serializable {
  public void addObserver(ObservableEventType e, Observer<T> o);

  public void removeObserver(ObservableEventType e, Observer<T> o);

  public void notifyObservers(ObservableEventType e, Event<T> a);
}

package de.cdelmonte.fds.datagenerator.orchestrator.observer;

import de.cdelmonte.fds.datagenerator.orchestrator.event.Event;
import de.cdelmonte.fds.datagenerator.orchestrator.model.EventModel;


public interface Observer<T extends EventModel> {

  void update(Event<T> event);

}

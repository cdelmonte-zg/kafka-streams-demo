package de.cdelmonte.fds.datagenerator.orchestrator.model.world;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import de.cdelmonte.fds.datagenerator.orchestrator.event.Event;
import de.cdelmonte.fds.datagenerator.orchestrator.model.EventModel;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.ObservableEventType;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.Observer;


public class Website<T extends EventModel> implements World<T> {
  private static final long serialVersionUID = 1L;
  private List<Actor> actors = new ArrayList<>();
  protected Map<ObservableEventType, Observer<T>> observers =
      new EnumMap<>(ObservableEventType.class);

  @Override
  public void add(Actor a) {
    actors.add(a);
  }

  @Override
  public void remove(Actor a) {
    a.interrupt();
    actors.remove(a);
  }

  @Override
  public List<Actor> getList() {
    return actors;
  }

  @Override
  public boolean exists(Actor actor) {
    return actors.contains(actor);
  }

  public void notifyObservers(ObservableEventType e, Event<T> a) {
    observers.get(e).update(a);
  }

  @Override
  public void addObserver(ObservableEventType e, Observer<T> o) {
    observers.put(e, o);

  }

  @Override
  public void removeObserver(ObservableEventType e, Observer<T> o) {
    observers.remove(e);
  }
}

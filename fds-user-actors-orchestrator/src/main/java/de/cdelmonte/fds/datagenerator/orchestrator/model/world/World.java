package de.cdelmonte.fds.datagenerator.orchestrator.model.world;

import java.io.Serializable;
import java.util.List;

import de.cdelmonte.fds.datagenerator.orchestrator.model.EventModel;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.Observable;


public interface World<T extends EventModel> extends Serializable, Observable<T> {

  public List<Actor> getList();

  public void add(Actor a);

  public void remove(Actor a);

  public boolean exists(Actor actor);
}

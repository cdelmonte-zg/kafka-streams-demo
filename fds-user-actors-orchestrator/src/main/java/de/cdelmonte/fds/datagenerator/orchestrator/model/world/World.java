package de.cdelmonte.fds.datagenerator.orchestrator.model.world;

import java.io.Serializable;
import java.util.List;

import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.Observable;


public interface World extends Serializable, Observable {
  public List<Actor> getList();

  public void add(Actor a);

  public void remove(Actor a);

  public boolean exists(Actor actor);
}

package de.cdelmonte.fds.datagenerator.orchestrator.model.world;

import java.io.Serializable;
import java.util.List;

import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;


public interface World extends Serializable {
  public List<Actor> getList();

  public void add(Actor a);

  public void remove(Actor a);

  public boolean exists(Actor actor);
}

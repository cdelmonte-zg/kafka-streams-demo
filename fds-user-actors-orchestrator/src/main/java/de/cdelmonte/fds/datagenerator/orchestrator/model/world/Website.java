package de.cdelmonte.fds.datagenerator.orchestrator.model.world;

import java.util.ArrayList;
import java.util.List;

import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;


public class Website implements World {
  private static final long serialVersionUID = 1L;
  private List<Actor> actors = new ArrayList<>();

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
}

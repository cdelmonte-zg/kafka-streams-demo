package de.cdelmonte.fds.datagenerator.orchestrator.event;

import de.cdelmonte.fds.datagenerator.orchestrator.model.EventModel;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;


public class ActorEvent implements Event {
  private Actor actor;

  public ActorEvent(Actor actor) {
    this.actor = actor;
  }

  @Override
  public EventModel getModel() {
    return actor;
  }

}

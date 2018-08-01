package de.cdelmonte.fds.datagenerator.orchestrator.model.actor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import de.cdelmonte.fds.datagenerator.orchestrator.behaviors.Behavior;


public class ActorFactory {
  static final Map<ActorType, Supplier<ActorBuilder>> supplierMap = new HashMap<>();

  static {
    supplierMap.put(ActorType.BAD_USER, BadUser.builder());
    supplierMap.put(ActorType.GOOD_USER, GoodUser.builder());
  }

  public Actor createActor(ActorType actorType) throws IllegalArgumentException {
    Supplier<ActorBuilder> actorBuilder = supplierMap.get(actorType);
    if (actorBuilder != null) {
      return actorBuilder.get().build();
    }
    throw new IllegalArgumentException("No such actor type: " + actorType.toString());
  }

  public Actor createActor(ActorType actorType, Behavior actorBehavior)
      throws IllegalArgumentException {
    Supplier<ActorBuilder> actorBuilder = supplierMap.get(actorType);
    if (actorBuilder != null) {
      return actorBuilder.get().build().setBehavior(actorBehavior);
    }
    throw new IllegalArgumentException("No such actor type: " + actorType.toString());
  }
}

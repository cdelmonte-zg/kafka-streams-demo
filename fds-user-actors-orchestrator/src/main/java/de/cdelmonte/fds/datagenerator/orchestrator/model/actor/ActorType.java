package de.cdelmonte.fds.datagenerator.orchestrator.model.actor;

public enum ActorType {
  GOOD_USER("good user"), BAD_USER("bad user");

  private String name;

  private ActorType(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}

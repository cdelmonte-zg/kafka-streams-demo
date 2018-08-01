package de.cdelmonte.fds.datagenerator.orchestrator.controller;

public enum ActorDialogCommand {
  CLOSE("close"), RESET("reset"), SAVE(""), SELECT_TYPE("select_type");

  private String value;

  private ActorDialogCommand(String s) {
    value = s;
  }

  public String toString() {
    return value;
  }
}

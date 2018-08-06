package de.cdelmonte.fds.datagenerator.orchestrator.interpreter;

public enum CommandName {
  DO_CLICKS("do_clicks"), DO_TRANSACTIONS("do_transactions"), START_SESSION(
      "start_session"), CLOSE_SESSION("close_session"), SAY_HELLO("say_hello");

  private String name;

  private CommandName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}

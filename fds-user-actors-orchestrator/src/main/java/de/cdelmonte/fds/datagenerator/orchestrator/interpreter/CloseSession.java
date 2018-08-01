package de.cdelmonte.fds.datagenerator.orchestrator.interpreter;

public class CloseSession implements Command {

  @Override
  public void interpret(Context co) {
    co.getActor().getBehavior().closeSession();
  }
}

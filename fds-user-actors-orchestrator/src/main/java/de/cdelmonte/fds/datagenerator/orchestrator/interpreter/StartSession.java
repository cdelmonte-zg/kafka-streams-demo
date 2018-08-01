package de.cdelmonte.fds.datagenerator.orchestrator.interpreter;

public class StartSession implements Command {

  @Override
  public void interpret(Context co) {
    co.getActor().getBehavior().startSession();
  }
}

package de.cdelmonte.fds.datagenerator.orchestrator.interpreter;

public class DoClick implements Command {

  @Override
  public void interpret(Context co) {
    co.getActor().getBehavior().doClicks();
  }
}

package de.cdelmonte.fds.datagenerator.orchestrator.interpreter;

public class DoTransaction implements Command {

  @Override
  public void interpret(Context co) {
    co.getActor().getBehavior().doTransactions();
  }
}

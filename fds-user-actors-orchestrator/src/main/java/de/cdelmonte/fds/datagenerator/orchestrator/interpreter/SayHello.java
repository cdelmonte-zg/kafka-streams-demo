package de.cdelmonte.fds.datagenerator.orchestrator.interpreter;

import de.cdelmonte.fds.datagenerator.orchestrator.util.logging.Logger;


public class SayHello implements Command {

  @Override
  public void interpret(Context co) {
    Logger
        .log("Hey, here is " + co.getActor().getFancyName() + ", I'm a " + co.getActor().getType());
  }
}

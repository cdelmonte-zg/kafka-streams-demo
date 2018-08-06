package de.cdelmonte.fds.datagenerator.orchestrator.interpreter;

import de.cdelmonte.fds.datagenerator.orchestrator.util.logging.Logger;


public class StartSession implements Command {

  @Override
  public void interpret(Context co) {
    Logger.log("Sarting session");
  }
}

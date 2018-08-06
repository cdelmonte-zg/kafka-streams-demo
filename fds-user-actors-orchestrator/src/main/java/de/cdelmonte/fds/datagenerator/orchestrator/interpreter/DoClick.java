package de.cdelmonte.fds.datagenerator.orchestrator.interpreter;

import de.cdelmonte.fds.datagenerator.orchestrator.event.ClickEvent;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.ObservableEventType;
import de.cdelmonte.fds.datagenerator.orchestrator.util.logging.Logger;


public class DoClick implements Command {

  @Override
  public void interpret(Context co) {
    Logger.log("Doing clicks");
    co.notifyObservers(ObservableEventType.CLICK, new ClickEvent(co.getActor()));
  }
}

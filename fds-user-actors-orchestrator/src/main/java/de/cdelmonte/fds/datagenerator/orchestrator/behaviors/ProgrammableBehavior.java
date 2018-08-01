package de.cdelmonte.fds.datagenerator.orchestrator.behaviors;

import de.cdelmonte.fds.datagenerator.orchestrator.event.ClickEvent;
import de.cdelmonte.fds.datagenerator.orchestrator.event.TransactionEvent;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.ClickObserver;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.ObservableEventType;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.TransactionObserver;
import de.cdelmonte.fds.datagenerator.orchestrator.util.logging.Logger;


public class ProgrammableBehavior extends Behavior {

  public ProgrammableBehavior() {
    addObserver(ObservableEventType.CLICK, new ClickObserver());
    addObserver(ObservableEventType.TRANSACTION, new TransactionObserver());
  }

  @Override
  public void doClicks() {
    Logger.log("Doing good clicks");
    notifyObservers(ObservableEventType.CLICK, new ClickEvent());
  }

  @Override
  public void doTransactions() {
    Logger.log("Doing good transactions");
    notifyObservers(ObservableEventType.TRANSACTION, new TransactionEvent());
  }
}

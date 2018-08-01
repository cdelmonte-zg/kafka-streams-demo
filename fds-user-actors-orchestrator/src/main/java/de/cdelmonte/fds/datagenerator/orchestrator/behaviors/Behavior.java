package de.cdelmonte.fds.datagenerator.orchestrator.behaviors;

import java.util.HashMap;
import java.util.Map;

import de.cdelmonte.fds.datagenerator.orchestrator.event.Event;
import de.cdelmonte.fds.datagenerator.orchestrator.event.SessionEvent;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.Observable;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.ObservableEventType;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.Observer;


public abstract class Behavior implements Observable {

  protected Map<ObservableEventType, Observer> observers = new HashMap<>();
  protected Event session;
  private String program;

  public void executeAlgorithm() {
    startSession();
    doClicks();
    doTransactions();
    closeSession();
  }

  public void startSession() {
    session = new SessionEvent();
  }

  public void closeSession() {
    session = null;
  }

  public abstract void doClicks();

  public abstract void doTransactions();

  @Override
  public void addObserver(ObservableEventType e, Observer o) {
    observers.put(e, o);
  }

  @Override
  public void removeObserver(ObservableEventType e, Observer o) {
    observers.remove(e);
  }

  @Override
  public void notifyObservers(ObservableEventType e, Event a) {
    observers.get(e).update(a);
  }

  public void setProgram(String program) {
    this.program = program;
  }

  public String getProgram() {
    return program;
  }
}

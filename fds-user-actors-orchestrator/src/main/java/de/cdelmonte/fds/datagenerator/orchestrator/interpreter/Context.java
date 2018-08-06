package de.cdelmonte.fds.datagenerator.orchestrator.interpreter;

import java.util.HashMap;
import java.util.Map;

import de.cdelmonte.fds.datagenerator.orchestrator.event.Event;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.Observable;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.ObservableEventType;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.Observer;


public class Context implements Observable {
  private Actor actor;
  private int numberOfClicks;
  private int numberOfTransactions;
  protected Map<ObservableEventType, Observer> observers = new HashMap<>();

  public void setActor(Actor actor) {
    this.actor = actor;
  }

  public Actor getActor() {
    return actor;
  }

  public void setNumberOfClicks(int n) {
    this.numberOfClicks = n;
  }

  public int getNumberOfClicks() {
    return numberOfClicks;
  }

  public void setNumberOfTransactions(int n) {
    this.numberOfTransactions = n;
  }

  public int getNumberOfTransactions() {
    return numberOfTransactions;
  }

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
}

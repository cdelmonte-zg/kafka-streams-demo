package de.cdelmonte.fds.datagenerator.orchestrator.interpreter;

import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;


public class Context {
  private Actor actor;
  private int numberOfClicks;
  private int numberOfTransactions;

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
}

package de.cdelmonte.fds.datagenerator.orchestrator.gui.observer;


public interface Observable {
  public void addObserver(Observer observer);

  public void removeObserver(Observer observer);

  public void notifyObservers();
}

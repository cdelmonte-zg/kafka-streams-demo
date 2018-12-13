package de.cdelmonte.fds.datagenerator.orchestrator.observer;

import de.cdelmonte.fds.datagenerator.orchestrator.event.Event;
import de.cdelmonte.fds.datagenerator.orchestrator.model.Click;
import de.cdelmonte.fds.datagenerator.orchestrator.model.EventModel;
import de.cdelmonte.fds.datagenerator.orchestrator.model.Session;
import de.cdelmonte.fds.datagenerator.orchestrator.model.Transaction;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;
import de.cdelmonte.fds.datagenerator.orchestrator.service.MessageService;


public class ObserverFactory {
  private static final MessageService notifier = new MessageService();

  public Observer<Click> getClickObserver() {
    return new ClickObserver();
  }

  public Observer<Actor> getActorObserver() {
    return new ActorObserver();
  }

  public Observer<Transaction> getTransactionObserver() {
    return new TransactionObserver();
  }

  public Observer<Session> getSessionObserver() {
    return new SessionObserver();
  }

  final class ActorObserver extends ObserverWrapper<Actor> {
    public ActorObserver() {
      super();
    }
  }

  final class TransactionObserver extends ObserverWrapper<Transaction> {
    public TransactionObserver() {
      super();
    }
  }

  final class SessionObserver extends ObserverWrapper<Session> {
    public SessionObserver() {
      super();
    }
  }

  final class ClickObserver extends ObserverWrapper<Click> {
    public ClickObserver() {
      super();
    }
  }

  class ObserverWrapper<T extends EventModel> implements Observer<T> {
    public void update(Event<T> event) {
      ObserverFactory.notifier.sendTo(event.getModel());
    }
  }
}

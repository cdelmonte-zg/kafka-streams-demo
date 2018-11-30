package de.cdelmonte.fds.datagenerator.orchestrator.observer;

import de.cdelmonte.fds.datagenerator.orchestrator.event.Event;
import de.cdelmonte.fds.datagenerator.orchestrator.model.Click;
import de.cdelmonte.fds.datagenerator.orchestrator.model.EventModel;
import de.cdelmonte.fds.datagenerator.orchestrator.model.Session;
import de.cdelmonte.fds.datagenerator.orchestrator.model.Transaction;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;
import de.cdelmonte.fds.datagenerator.orchestrator.service.MessageService;


public class ObserverFactory {
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
      super(new MessageService<Actor>());
    }
  }

  final class TransactionObserver extends ObserverWrapper<Transaction> {
    public TransactionObserver() {
      super(new MessageService<Transaction>());
    }
  }

  final class SessionObserver extends ObserverWrapper<Session> {
    public SessionObserver() {
      super(new MessageService<Session>());
    }
  }

  final class ClickObserver extends ObserverWrapper<Click> {
    public ClickObserver() {
      super(new MessageService<Click>());
    }
  }

  class ObserverWrapper<T extends EventModel> implements Observer<T> {
    private MessageService<T> notifier;

    ObserverWrapper(MessageService<T> notifier) {
      this.notifier = notifier;
    }

    public void update(Event<T> event) {
      notifier.sendToKafka(event);
    }
  }
}

package de.cdelmonte.fds.datagenerator.orchestrator.interpreter;

import de.cdelmonte.fds.datagenerator.orchestrator.event.Event;
import de.cdelmonte.fds.datagenerator.orchestrator.model.Session;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.ObservableEventType;
import de.cdelmonte.fds.datagenerator.orchestrator.util.logging.Logger;
import net.andreinc.mockneat.MockNeat;


public class StartSession implements Command {

  @Override
  public void interpret(Context co) {
    Session session = new Session.Builder(MockNeat.threadLocal()).build();
    co.setSession(session);
    co.notifyObservers(ObservableEventType.SESSION, new Event<Session>(session));

    Logger.log("Session started");
  }
}

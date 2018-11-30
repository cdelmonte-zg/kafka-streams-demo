package de.cdelmonte.fds.datagenerator.orchestrator.interpreter;

import de.cdelmonte.fds.datagenerator.orchestrator.event.Event;
import de.cdelmonte.fds.datagenerator.orchestrator.model.Transaction;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.ObservableEventType;
import de.cdelmonte.fds.datagenerator.orchestrator.util.logging.Logger;
import net.andreinc.mockneat.MockNeat;


public class DoTransaction implements Command {

  @Override
  public void interpret(Context co) {
    Transaction transaction =
        new Transaction.Builder(MockNeat.threadLocal(), 1, co.getSession().getRandClick()).build();
    co.notifyObservers(ObservableEventType.TRANSACTION, new Event<Transaction>(transaction));
    Logger.log("Doing transaction");
  }
}

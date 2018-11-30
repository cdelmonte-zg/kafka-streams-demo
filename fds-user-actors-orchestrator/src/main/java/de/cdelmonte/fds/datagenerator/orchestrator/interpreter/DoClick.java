package de.cdelmonte.fds.datagenerator.orchestrator.interpreter;

import de.cdelmonte.fds.datagenerator.orchestrator.event.Event;
import de.cdelmonte.fds.datagenerator.orchestrator.model.Click;
import de.cdelmonte.fds.datagenerator.orchestrator.model.ClickSource;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.ObservableEventType;
import de.cdelmonte.fds.datagenerator.orchestrator.util.logging.Logger;
import net.andreinc.mockneat.MockNeat;


public class DoClick implements Command {

  @Override
  public void interpret(Context co) {
    Click click = new Click.Builder(MockNeat.threadLocal(), co.getActor(), co.getSession(),
        ClickSource.SIMULATOR, 0).build();
    co.getSession().addClick(click);
    co.notifyObservers(ObservableEventType.CLICK, new Event<Click>(click));

    Logger.log("Doing clicks");
  }
}

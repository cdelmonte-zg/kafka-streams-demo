package de.cdelmonte.fds.datagenerator.orchestrator.event;

import de.cdelmonte.fds.datagenerator.orchestrator.model.Click;
import de.cdelmonte.fds.datagenerator.orchestrator.model.EventModel;


public class ClickEvent implements Event {
  Click click;

  public ClickEvent(Click click) {
    this.click = click;
  }

  @Override
  public EventModel getModel() {
    return click;
  }
}

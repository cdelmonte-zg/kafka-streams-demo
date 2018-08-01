package de.cdelmonte.fds.datagenerator.orchestrator.controller;

import de.cdelmonte.fds.datagenerator.orchestrator.model.view.LoggingTextAreaModel;
import de.cdelmonte.fds.datagenerator.orchestrator.util.logging.Output;
import de.cdelmonte.fds.datagenerator.orchestrator.view.LoggingTextArea;

public class LoggingTextAreaController implements Output {
  LoggingTextArea view;
  LoggingTextAreaModel model;

  public LoggingTextAreaController(LoggingTextAreaModel model, LoggingTextArea view) {
    this.model = model;
    this.view = view;
  }

  public void reset() {
    model.reset();
    view.reset();
  }

  @Override
  public void print(String s) {
    model.append(s);
    if (model.getSize() > 10) {
      view.refresh();
    }
  }
}

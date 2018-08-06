package de.cdelmonte.fds.datagenerator.orchestrator.gui.controller;

import de.cdelmonte.fds.datagenerator.orchestrator.gui.model.LoggingTextAreaModel;
import de.cdelmonte.fds.datagenerator.orchestrator.gui.view.LoggingTextArea;
import de.cdelmonte.fds.datagenerator.orchestrator.util.logging.Output;

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

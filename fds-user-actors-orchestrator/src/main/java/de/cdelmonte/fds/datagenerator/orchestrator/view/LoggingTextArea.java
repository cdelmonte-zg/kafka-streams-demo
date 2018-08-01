package de.cdelmonte.fds.datagenerator.orchestrator.view;

import java.awt.Color;

import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import de.cdelmonte.fds.datagenerator.orchestrator.model.view.LoggingTextAreaModel;


public class LoggingTextArea extends JTextArea {
  private LoggingTextAreaModel model;

  public LoggingTextArea(LoggingTextAreaModel model) {
    super();
    this.model = model;
    setForeground(Color.GREEN);
    DefaultCaret caret = (DefaultCaret) getCaret();
    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
  }

  public void refresh() {
    append(model.flush());
  }

  public void reset() {
    setText(model.flush());
  }
}

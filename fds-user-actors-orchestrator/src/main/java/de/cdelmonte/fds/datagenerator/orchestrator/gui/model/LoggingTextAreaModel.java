package de.cdelmonte.fds.datagenerator.orchestrator.gui.model;

import java.util.ArrayList;
import java.util.List;

public class LoggingTextAreaModel {
  private List<String> lines = new ArrayList<>();

  public String flush() {
    StringBuffer out = new StringBuffer();

    for (String line : lines) {
      out.append(line + "\n");
    }
    reset();

    return out.toString();
  }

  public void reset() {
    lines = new ArrayList<>();
  }

  public int getSize() {
    return lines.size();
  }

  public void append(String t) {
    lines.add(t);
  }
}

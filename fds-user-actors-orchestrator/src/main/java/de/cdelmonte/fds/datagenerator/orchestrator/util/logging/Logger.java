package de.cdelmonte.fds.datagenerator.orchestrator.util.logging;

import java.util.ArrayList;
import java.util.List;

public class Logger {
  static List<Output> outputs = new ArrayList<>();

  public static synchronized void addOutput(Output output) {
    outputs.add(output);
  }

  public static synchronized void log(String s) {
    for (Output out : outputs) {
      out.print(s);
    }
  }
}

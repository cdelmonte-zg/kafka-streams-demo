package de.cdelmonte.fds.datagenerator.orchestrator.model.actor;

import de.cdelmonte.fds.datagenerator.orchestrator.interpreter.Command;
import de.cdelmonte.fds.datagenerator.orchestrator.interpreter.Context;
import de.cdelmonte.fds.datagenerator.orchestrator.util.logging.Logger;


public class Ghost extends Thread {
  private boolean paused;
  private Command program;
  private Context context;

  public Ghost() {
    paused = true;
  }

  @Override
  public void run() {
    while (!isInterrupted()) {
      if (!paused) {
        program.interpret(context);
        yield();
      }

      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
        interrupt();
        Logger.log("bye");
        return;
      }
    }
  }

  public void pause() {
    this.paused = !paused;
  }

  public boolean isPaused() {
    return paused;
  }

  public void setProgram(Context context, Command program) {
    this.program = program;
    this.context = context;
  }
}

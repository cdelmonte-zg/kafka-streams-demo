package de.cdelmonte.fds.datagenerator.orchestrator.behaviors;

import java.io.Serializable;


public class Behavior implements Serializable {
  private static final long serialVersionUID = 1L;
  private String program;

  public void setProgram(String program) {
    this.program = program;
  }

  public String getProgram() {
    return program;
  }
}

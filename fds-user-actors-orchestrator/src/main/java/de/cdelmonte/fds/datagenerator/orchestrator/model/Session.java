package de.cdelmonte.fds.datagenerator.orchestrator.model;

import net.andreinc.mockneat.MockNeat;


public class Session {
  private String sessionId;

  public Session(Builder builder) {
    sessionId = builder.sessionId;
  }

  public String getSessionId() {
    return sessionId;
  }

  public static class Builder {
    private MockNeat mock;

    private String sessionId;

    public Builder(MockNeat mock) {
      this.mock = mock;
      sessionId = mock.uuids().val();
    }

    public Session build() {
      return new Session(this);
    }
  }
}

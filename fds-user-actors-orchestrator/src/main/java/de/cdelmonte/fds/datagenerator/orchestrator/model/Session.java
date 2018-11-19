package de.cdelmonte.fds.datagenerator.orchestrator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.andreinc.mockneat.MockNeat;


public class Session implements EventModel {
  private String sessionId;
  private Random rand = new Random();

  private List<Click> clicks = new ArrayList<>();

  public Session(Builder builder) {
    sessionId = builder.sessionId;
  }


  public List<Click> getClicks() {
    return clicks;
  }

  public void setClicks(List<Click> clicks) {
    this.clicks = clicks;
  }

  public void addClick(Click click) {
    this.clicks.add(click);
  }

  public void removeClick(Click click) {
    this.clicks.remove(click);
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

  public Click getRandClick() {
    return clicks.get(rand.nextInt(clicks.size()));
  }

  @Override
  public String getJSON() {
    return null;
  }
}

package de.cdelmonte.fds.datagenerator.orchestrator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;
import de.cdelmonte.fds.datagenerator.orchestrator.view.StartPauseActorButton;


public class ActorStandByButtonController implements ActionListener {
  Actor model;
  StartPauseActorButton view;

  public ActorStandByButtonController(Actor model, StartPauseActorButton view) {
    this.model = model;
    this.view = view;
  }

  public void update() {

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    model.pause();
    view.refresh();
  }
}

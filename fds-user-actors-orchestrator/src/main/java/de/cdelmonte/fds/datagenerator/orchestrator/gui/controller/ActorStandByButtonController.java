package de.cdelmonte.fds.datagenerator.orchestrator.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.cdelmonte.fds.datagenerator.orchestrator.gui.view.StartPauseActorButton;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;


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

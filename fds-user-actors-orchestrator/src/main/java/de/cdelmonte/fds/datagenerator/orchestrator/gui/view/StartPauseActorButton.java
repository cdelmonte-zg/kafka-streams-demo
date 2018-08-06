package de.cdelmonte.fds.datagenerator.orchestrator.gui.view;

import java.awt.Color;

import javax.swing.JButton;

import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;


public class StartPauseActorButton extends JButton {
  public static Color color = Color.darkGray;
  private Actor actor;

  public StartPauseActorButton(Actor actor) {
    this.actor = actor;
    setButtonLabel();
  }

  private void setButtonLabel() {
    setForeground(actor.isPaused() ? new Color(255, 255, 255) : new Color(255, 255, 255));
    setBackground(actor.isPaused() ? new Color(0, 117, 3) : new Color(209, 0, 0));
    setText(actor.isPaused() ? "Start Actor" : "Stop Actor");
    setOpaque(true);
    setBorderPainted(false);
  }

  public void refresh() {
    setButtonLabel();
    repaint();
  }
}

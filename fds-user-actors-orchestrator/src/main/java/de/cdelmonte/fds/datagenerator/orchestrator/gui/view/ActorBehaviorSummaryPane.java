package de.cdelmonte.fds.datagenerator.orchestrator.gui.view;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import de.cdelmonte.fds.datagenerator.orchestrator.gui.observer.Observer;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;


public class ActorBehaviorSummaryPane extends JScrollPane implements Observer {
  JTextArea output;
  private Actor actor;

  public ActorBehaviorSummaryPane() {
    output = new JTextArea();
    output.setEditable(false);
    setViewportView(output);
  }

  public void setText(String t) {
    output.setText(t);
  }

  public void append(String t) {
    output.append(t);
  }

  public void setModel(Actor actor) {
    this.actor = actor;
  }

  @Override
  public void update() {
    if (actor != null) {
      setText(actor.getBehavior().getProgram());
    }
  }

  public void empty() {
    this.actor = null;
    setText("");
    setCaretPosition(0);
  }

  private void setCaretPosition(int i) {
    output.setCaretPosition(i);
  }
}

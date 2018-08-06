package de.cdelmonte.fds.datagenerator.orchestrator.gui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import de.cdelmonte.fds.datagenerator.orchestrator.gui.observer.Observer;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;


public class ActorAttributesSummaryPane extends JScrollPane implements Observer {
  private static final String NEW_LINE = "\n";
  JTextArea output;
  private Object model;
  private Actor actor;
  private JLabel avatarLabel;

  public ActorAttributesSummaryPane() {
    JPanel pane = new JPanel(new BorderLayout());
    avatarLabel = new JLabel();
    avatarLabel.setPreferredSize(new Dimension(120, 120));
    pane.add(avatarLabel, BorderLayout.NORTH);
    output = new JTextArea();
    output.setEditable(false);
    pane.add(output, BorderLayout.SOUTH);
    setViewportView(pane);
  }

  public void append(String t) {
    output.append(t);
    output.update(output.getGraphics());
  }

  public void setCaretPosition(int i) {
    output.setCaretPosition(i);
  }

  public void setText(String t) {
    output.setText(t);
    output.update(output.getGraphics());
  }

  public void validate() {
    output.update(output.getGraphics());
  }

  @Override
  public void update() {
    if (actor != null) {
      setText(actor.toString());
      append(NEW_LINE);
      setCaretPosition(0);

      setAvatar(actor.getIcon());
    }
  }

  private void setAvatar(Icon avatar) {
    avatarLabel.setIcon(avatar);
  }

  public void setModel(Actor actor) {
    this.actor = actor;
  }

  public void empty() {
    setAvatar(null);

    this.actor = null;
    setText("");
    setCaretPosition(0);
  }
}

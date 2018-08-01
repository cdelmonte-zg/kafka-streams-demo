package de.cdelmonte.fds.datagenerator.orchestrator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import javax.swing.JComboBox;

import de.cdelmonte.fds.datagenerator.orchestrator.behaviors.BadBehavior;
import de.cdelmonte.fds.datagenerator.orchestrator.behaviors.Behavior;
import de.cdelmonte.fds.datagenerator.orchestrator.behaviors.GoodBehavior;
import de.cdelmonte.fds.datagenerator.orchestrator.behaviors.ProgrammableBehavior;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.ActorFactory;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.ActorType;
import de.cdelmonte.fds.datagenerator.orchestrator.model.world.World;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.ClickObserver;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.ObservableEventType;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.TransactionObserver;
import de.cdelmonte.fds.datagenerator.orchestrator.view.ActorDialogWindow;


public class ActorDialogController implements ActionListener, Observable {

  private ActorDialogWindow view;
  private Actor actor;
  private World world;

  private List<Observer> observers = new ArrayList<>();


  public ActorDialogController(World world) {
    this.world = world;
  }

  public ActorDialogController(World world, Actor actor) {
    this.world = world;
    this.actor = actor;
  }

  public void setView(ActorDialogWindow view) {
    this.view = view;

    if (actor != null) {
      view.loadFields();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("hello" + e.getActionCommand() + ActorDialogCommand.CLOSE);

    if (e.getActionCommand() == ActorDialogCommand.CLOSE.toString()) {
      view.setActor(null);
      view.dispose();
    } else if (e.getActionCommand().equals(ActorDialogCommand.RESET.toString())) {
      reset();
    } else if (e.getActionCommand().equals(ActorDialogCommand.SAVE.toString())) {
      saveActor();
      view.dispose();
    } else if (e.getActionCommand().equals(ActorDialogCommand.SELECT_TYPE.toString())) {
      JComboBox<ActorType> combo = (JComboBox<ActorType>) e.getSource();
      System.out.println(combo.getSelectedItem());

      actor = createActor((ActorType) combo.getSelectedItem());
      view.setActor(actor);
      view.loadFields();
      view.validate();
    }
  }

  private Actor createActor(ActorType type) {
    Behavior behavior = (type == ActorType.GOOD_USER) ? new GoodBehavior() : new BadBehavior();
    behavior.setProgram("start do_clicks do_transactions");
    behavior.addObserver(ObservableEventType.CLICK, new ClickObserver());
    behavior.addObserver(ObservableEventType.TRANSACTION, new TransactionObserver());
    Supplier<ActorFactory> actorFactory = ActorFactory::new;

    return actorFactory.get().createActor(type);
  }

  public void updateActor() {
    actor.setFancyName(view.getTfFancyName().getText());
    actor.setUsername(view.getTfUsername().getText());
    actor.setEmail(view.getTfEmail().getText());
    actor.setLastCountry(view.getTfLastCountry().getText());
    actor.setLastIp(view.getTfLastIp().getText());
    actor.setLastCid(view.getTfLastCid().getText());
    actor.setLanguages(view.getTfLanguages().getText());
    actor.setUserAgent(view.getTfUserAgent().getText());

    actor.setBirthdate(view.getModelBirthdate().getValue());
    actor.setRegistrationDate(view.getModelRegistration().getValue());
    actor.setLastLoginDate(view.getModelLastLoginDate().getValue());


    actor.setSuspect(view.getCbSuspect().isSelected());
    actor.setBlocked(view.getCbBlocked().isSelected());
    actor.setDoNotPay(view.getCbDoNotPay().isSelected());
    actor.setEmailVerified(view.getCbEmailVerified().isSelected());
    actor.setPaymentsBlocked(view.getCbPaymentsBlocked().isSelected());

    Behavior behavior = new ProgrammableBehavior();
    behavior.setProgram(view.getEditorBehaviorArea().getText());
    actor.setBehavior(behavior);
    actor.setIcon(view.getImageLabel().getIcon());
  }

  public void saveActor() {
    if (actor != null) {
      updateActor();

      if (!world.exists(actor)) {
        world.add(actor);
        actor.start();
      }
      notifyObservers();
    }
  }

  public void reset() {
    view.loadFields();
  }

  @Override
  public void addObserver(Observer observer) {
    observers.add(observer);

  }

  @Override
  public void removeObserver(Observer observer) {
    observers.remove(observer);

  }

  @Override
  public void notifyObservers() {
    if (observers != null) {
      for (Observer o : observers) {
        o.update();
      }
    }
  }
}

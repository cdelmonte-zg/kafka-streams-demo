package de.cdelmonte.fds.datagenerator.orchestrator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import de.cdelmonte.fds.datagenerator.orchestrator.behaviors.BadBehavior;
import de.cdelmonte.fds.datagenerator.orchestrator.behaviors.Behavior;
import de.cdelmonte.fds.datagenerator.orchestrator.behaviors.GoodBehavior;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.ActorFactory;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.ActorType;
import de.cdelmonte.fds.datagenerator.orchestrator.model.world.Website;
import de.cdelmonte.fds.datagenerator.orchestrator.model.world.World;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.ClickObserver;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.ObservableEventType;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.TransactionObserver;
import de.cdelmonte.fds.datagenerator.orchestrator.view.WorldFrame;


public class Client {
  Random rand = new Random();

  /**
   * Main Program.
   * 
   * @param argv Command line parameters
   */
  public static void main(String[] argv) {
    Client app = new Client();
    World world = app.createWorld();


    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        app.createAndShowGui(world);
      }
    });
  }

  World createWorld() {
    Supplier<ActorFactory> actorFactory = ActorFactory::new;
    List<Actor> actors = new ArrayList<>();
    for (int i = 0, j = rand.nextInt(10 - 3) + 3; i < j; i++) {
      if (i % 2 == 0) {
        Behavior goodBehavior = new GoodBehavior();
        goodBehavior.setProgram("start do_clicks do_transactions");
        goodBehavior.addObserver(ObservableEventType.CLICK, new ClickObserver());
        goodBehavior.addObserver(ObservableEventType.TRANSACTION, new TransactionObserver());
        actors.add(actorFactory.get().createActor(ActorType.GOOD_USER, goodBehavior));
      } else {
        Behavior badBehavior = new BadBehavior();
        badBehavior.setProgram("start do_clicks do_transactions");
        badBehavior.addObserver(ObservableEventType.CLICK, new ClickObserver());
        badBehavior.addObserver(ObservableEventType.TRANSACTION, new TransactionObserver());
        actors.add(actorFactory.get().createActor(ActorType.BAD_USER, badBehavior));
      }
    }

    World website = new Website();
    // actors.forEach(a -> website.add(a));
    // actors.forEach(a -> a.start());

    return website;
  }

  void createAndShowGui(World world) {
    JFrame frame = new WorldFrame(world);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setUndecorated(true);
    frame.pack();
    frame.setVisible(true);
  }
}

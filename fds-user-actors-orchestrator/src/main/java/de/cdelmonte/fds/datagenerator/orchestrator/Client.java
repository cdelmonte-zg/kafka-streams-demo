package de.cdelmonte.fds.datagenerator.orchestrator;

import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import de.cdelmonte.fds.datagenerator.orchestrator.gui.view.WorldFrame;
import de.cdelmonte.fds.datagenerator.orchestrator.model.world.Website;
import de.cdelmonte.fds.datagenerator.orchestrator.model.world.World;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.ObservableEventType;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.ObserverFactory;
import de.cdelmonte.fds.datagenerator.orchestrator.service.WorldInOutService;


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
    World website = null;
    try {
      website = WorldInOutService.readWorld();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (website == null) {
      website = new Website();
      website.addObserver(ObservableEventType.ACTOR, new ObserverFactory().getActorObserver());
    }

    return website;
  }

  void createAndShowGui(World world) {
    JFrame frame = new WorldFrame(world);
    frame.setUndecorated(true);
    frame.pack();
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setVisible(true);
  }
}

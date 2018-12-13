package de.cdelmonte.fds.datagenerator.orchestrator.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.cdelmonte.fds.datagenerator.orchestrator.gui.view.ActorAttributesSummaryPane;
import de.cdelmonte.fds.datagenerator.orchestrator.gui.view.ActorBehaviorSummaryPane;
import de.cdelmonte.fds.datagenerator.orchestrator.gui.view.ActorButtonsSummaryPane;
import de.cdelmonte.fds.datagenerator.orchestrator.gui.view.ActorDialogWindow;
import de.cdelmonte.fds.datagenerator.orchestrator.gui.view.StartPauseActorButton;
import de.cdelmonte.fds.datagenerator.orchestrator.gui.view.WorldFrame;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;
import de.cdelmonte.fds.datagenerator.orchestrator.model.world.World;


public class ListPanelController implements ListSelectionListener {
  private ActorBehaviorSummaryPane behaviorView;
  private ActorAttributesSummaryPane attributesView;
  private World world;
  private ActorButtonsSummaryPane buttonsView;
  private WorldFrame mainFrame;
  private ActorPropertiesPaneTableController actorPropertiesPaneTableController;


  public ListPanelController(World world, WorldFrame mainFrame,
      ActorPropertiesPaneTableController actorPropertiesPaneTableController,
      ActorAttributesSummaryPane attributesView, ActorBehaviorSummaryPane behaviorView,
      ActorButtonsSummaryPane buttonsView) {
    this.world = world;
    this.attributesView = attributesView;
    this.behaviorView = behaviorView;
    this.buttonsView = buttonsView;
    this.mainFrame = mainFrame;
    this.actorPropertiesPaneTableController = actorPropertiesPaneTableController;
  }

  public void valueChanged(ListSelectionEvent e) {
    ListSelectionModel lsm = (ListSelectionModel) e.getSource();
    if (!e.getValueIsAdjusting()) {
      if (!lsm.isSelectionEmpty()) {
        int i = lsm.getMinSelectionIndex();
        if (i > -1) {

          int indModel = actorPropertiesPaneTableController.getIndexModel(i);
          Actor actor = (Actor) world.getList().get(indModel);

          attributesView.setModel(actor);
          attributesView.update();

          behaviorView.setModel(actor);
          behaviorView.update();
          buttonsView.removeAll();

          StartPauseActorButton startStopActorButton = new StartPauseActorButton(actor);
          ActorStandByButtonController controller =
              new ActorStandByButtonController(actor, startStopActorButton);
          startStopActorButton.addActionListener(controller);

          JButton modifyButton = new JButton("Modify");
          modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              ActorDialogController controller = new ActorDialogController(world, actor);
              ActorDialogWindow newActorDialogW =
                  new ActorDialogWindow(mainFrame, controller, actor);
              controller.setView(newActorDialogW);
              controller.addObserver(actorPropertiesPaneTableController);
              controller.addObserver(attributesView);
              controller.addObserver(behaviorView);
              newActorDialogW.setVisible(true);

              newActorDialogW.validate();
              attributesView.validate();
              buttonsView.validate();
              mainFrame.validate();
            }
          });

          JButton deleteActorButton = new JButton("Delete");
          deleteActorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              int i = lsm.getMinSelectionIndex();

              if (i > -1) {
                int indModel = actorPropertiesPaneTableController.getIndexModel(i);
                Actor actor = (Actor) world.getList().get(indModel);
                if (actor != null) {
                  world.remove(actor);
                  lsm.clearSelection();
                  actorPropertiesPaneTableController.update();
                  attributesView.empty();
                  behaviorView.empty();

                  startStopActorButton.setVisible(false);
                  modifyButton.setVisible(false);
                  deleteActorButton.setVisible(false);

                  attributesView.validate();
                  buttonsView.validate();
                  mainFrame.validate();
                }
              }
            }
          });

          buttonsView.add(startStopActorButton);
          buttonsView.add(modifyButton);
          buttonsView.add(deleteActorButton);
        }
      }
    }

    attributesView.validate();
    buttonsView.validate();
    mainFrame.validate();
  }
}

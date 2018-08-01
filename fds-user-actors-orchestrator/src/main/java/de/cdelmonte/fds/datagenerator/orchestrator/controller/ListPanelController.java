package de.cdelmonte.fds.datagenerator.orchestrator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;
import de.cdelmonte.fds.datagenerator.orchestrator.model.world.World;
import de.cdelmonte.fds.datagenerator.orchestrator.view.ActorAttributesSummaryPane;
import de.cdelmonte.fds.datagenerator.orchestrator.view.ActorBehaviorSummaryPane;
import de.cdelmonte.fds.datagenerator.orchestrator.view.ActorButtonsSummaryPane;
import de.cdelmonte.fds.datagenerator.orchestrator.view.ActorDialogWindow;
import de.cdelmonte.fds.datagenerator.orchestrator.view.StartPauseActorButton;
import de.cdelmonte.fds.datagenerator.orchestrator.view.WorldFrame;


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

        Actor actor = world.getList().get(i);

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
            ActorDialogWindow newActorDialogW = new ActorDialogWindow(mainFrame, controller, actor);
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
            world.remove(world.getList().get(i));
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
        });
        buttonsView.add(startStopActorButton);
        buttonsView.add(modifyButton);
        buttonsView.add(deleteActorButton);
      }
    }

    attributesView.validate();
    buttonsView.validate();
    mainFrame.validate();
  }
}

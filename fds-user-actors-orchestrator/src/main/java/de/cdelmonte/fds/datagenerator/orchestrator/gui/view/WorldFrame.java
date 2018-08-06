package de.cdelmonte.fds.datagenerator.orchestrator.gui.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import de.cdelmonte.fds.datagenerator.orchestrator.gui.controller.ActorDialogController;
import de.cdelmonte.fds.datagenerator.orchestrator.gui.controller.ActorPropertiesPaneTableController;
import de.cdelmonte.fds.datagenerator.orchestrator.gui.controller.ListPanelController;
import de.cdelmonte.fds.datagenerator.orchestrator.gui.controller.LoggingTextAreaController;
import de.cdelmonte.fds.datagenerator.orchestrator.gui.model.ActorPropertiesModel;
import de.cdelmonte.fds.datagenerator.orchestrator.gui.model.LoggingTextAreaModel;
import de.cdelmonte.fds.datagenerator.orchestrator.model.world.World;
import de.cdelmonte.fds.datagenerator.orchestrator.service.WorldInOutService;
import de.cdelmonte.fds.datagenerator.orchestrator.util.logging.Logger;


public class WorldFrame extends BaseFrame {
  private World world;

  /**
   * @param world
   */
  public WorldFrame(World world) {
    super();
    this.world = world;


    ActorPropertiesModel actorsPropertyModel = new ActorPropertiesModel();
    ActorTableView actorPropertiesPane = new ActorTableView(actorsPropertyModel);

    ActorPropertiesPaneTableController actorPropertiesPaneTableController =
        new ActorPropertiesPaneTableController(world, actorsPropertyModel, actorPropertiesPane);

    actorPropertiesPane.getTable().getModel()
        .addTableModelListener(actorPropertiesPaneTableController);

    ActorAttributesSummaryPane outputScrollPane = new ActorAttributesSummaryPane();
    ActorBehaviorSummaryPane behaviorScrollPane = new ActorBehaviorSummaryPane();
    ActorButtonsSummaryPane controlButtonsPane = new ActorButtonsSummaryPane();

    actorPropertiesPane.getTable().getSelectionModel().addListSelectionListener(
        new ListPanelController(world, this, actorPropertiesPaneTableController, outputScrollPane,
            behaviorScrollPane, controlButtonsPane));

    GridBagConstraints gbc1 = new GridBagConstraints();
    gbc1.gridx = 0;
    gbc1.gridy = 0;
    gbc1.weightx = 0.5;
    gbc1.weighty = 0.5;
    gbc1.gridwidth = 1;
    gbc1.gridheight = 4;
    gbc1.insets = new Insets(1, 1, 1, 1);
    gbc1.fill = GridBagConstraints.BOTH;
    gbc1.anchor = GridBagConstraints.NORTH;


    GridBagConstraints gbc2 = new GridBagConstraints();
    gbc2.gridx = 1;
    gbc2.gridy = 1;
    gbc2.gridwidth = 1;
    gbc2.gridheight = 1;
    gbc2.weightx = 0.5;
    gbc2.weighty = 0.5;
    gbc2.fill = GridBagConstraints.BOTH;
    gbc2.insets = new Insets(1, 1, 1, 1);
    gbc2.anchor = GridBagConstraints.NORTH;

    GridBagLayout gbl = new GridBagLayout();
    gbl.setConstraints(actorPropertiesPane, gbc1);



    Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

    TitledBorder titleOutputScrollPane =
        BorderFactory.createTitledBorder(loweredetched, "Attributes");
    titleOutputScrollPane.setTitleJustification(TitledBorder.RIGHT);
    outputScrollPane.setBorder(titleOutputScrollPane);
    gbl.setConstraints(outputScrollPane, gbc2);



    GridBagConstraints gbc3 = new GridBagConstraints();
    gbc3.gridx = 1;
    gbc3.gridy = 3;
    gbc3.gridwidth = 1;
    gbc3.gridheight = 1;
    gbc3.weightx = 0.5;
    gbc3.weighty = 0;
    gbc3.fill = GridBagConstraints.HORIZONTAL;
    gbc3.insets = new Insets(1, 1, 1, 1);
    gbc3.anchor = GridBagConstraints.SOUTH;
    gbl.setConstraints(controlButtonsPane, gbc3);


    GridBagConstraints gb4 = new GridBagConstraints();
    gb4.gridx = 1;
    gb4.gridy = 2;
    gb4.gridwidth = 1;
    gb4.gridheight = 1;
    gb4.weightx = 0.5;
    gb4.weighty = 0.5;
    gb4.fill = GridBagConstraints.BOTH;
    gb4.insets = new Insets(1, 1, 1, 1);
    gb4.anchor = GridBagConstraints.NORTH;



    TitledBorder titleBehaviorScrollPane =
        BorderFactory.createTitledBorder(loweredetched, "Behavior");
    titleBehaviorScrollPane.setTitleJustification(TitledBorder.RIGHT);
    behaviorScrollPane.setBorder(titleBehaviorScrollPane);
    gbl.setConstraints(behaviorScrollPane, gb4);

    JPanel centerPane = new JPanel();
    centerPane.setLayout(gbl);
    centerPane.setBorder(LineBorder.createBlackLineBorder());
    centerPane.add(actorPropertiesPane);
    centerPane.add(outputScrollPane);
    centerPane.add(behaviorScrollPane);
    centerPane.add(controlButtonsPane);

    actorPropertiesPane.setPreferredSize(new Dimension(300, 300));
    outputScrollPane.setPreferredSize(new Dimension(290, 150));
    behaviorScrollPane.setPreferredSize(new Dimension(290, 100));
    controlButtonsPane.setPreferredSize(new Dimension(290, 40));

    add(centerPane, BorderLayout.CENTER);



    Button newActorButton = new Button("Create new Actor");
    newActorButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ActorDialogController controller = new ActorDialogController(world);
        ActorDialogWindow newActorButton = new ActorDialogWindow(WorldFrame.this, controller);
        controller.setView(newActorButton);
        controller.addObserver(actorPropertiesPaneTableController);
        controller.addObserver(behaviorScrollPane);
        controller.addObserver(outputScrollPane);
        newActorButton.setVisible(true);
      }
    });

    Button closeButton = new Button("Close");
    closeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    LoggingTextAreaModel textAreaModel = new LoggingTextAreaModel();
    LoggingTextArea textAreaView = new LoggingTextArea(textAreaModel);
    LoggingTextAreaController textAreaController =
        new LoggingTextAreaController(textAreaModel, textAreaView);
    Logger.addOutput(textAreaController);
    Logger.log("hello world");

    Button resetTextAreaButton = new Button("Reset");
    resetTextAreaButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        textAreaController.reset();
      }
    });


    JButton saveButton = new JButton("Save");
    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          WorldInOutService.saveWorld(world);
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    });

    JScrollPane textAreaScrollPane = new JScrollPane(textAreaView);
    textAreaScrollPane.setPreferredSize(new Dimension(450, 110));
    JPanel textAreaPane = new JPanel();
    textAreaPane.setBackground(Color.DARK_GRAY);
    textAreaPane.setLayout(new BorderLayout(10, 10));
    textAreaPane.add(textAreaScrollPane, BorderLayout.CENTER);

    Panel buttonsPanel = new Panel();
    buttonsPanel.add(resetTextAreaButton);
    buttonsPanel.add(closeButton);
    buttonsPanel.add(newActorButton);
    buttonsPanel.add(saveButton);



    JPanel southPane = new JPanel();
    southPane.setLayout(new BorderLayout());
    southPane.add(textAreaPane, BorderLayout.NORTH);
    southPane.add(buttonsPanel, BorderLayout.SOUTH);
    add(southPane, BorderLayout.SOUTH);
  }

  public World getWorld() {
    return world;
  }

  public void setWorld(World world) {
    this.world = world;
  }
}

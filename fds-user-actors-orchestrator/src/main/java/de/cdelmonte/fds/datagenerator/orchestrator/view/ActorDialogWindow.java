package de.cdelmonte.fds.datagenerator.orchestrator.view;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.cdelmonte.fds.datagenerator.orchestrator.controller.ActorDialogCommand;
import de.cdelmonte.fds.datagenerator.orchestrator.controller.ActorDialogController;
import de.cdelmonte.fds.datagenerator.orchestrator.interpreter.CommandName;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.ActorType;
import de.cdelmonte.fds.datagenerator.orchestrator.util.ViewUtils;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;


public class ActorDialogWindow extends Dialog {
  private Actor actor;
  private WorldFrame parent;
  private ActorDialogController controller;
  private Map<String, String> cmds = new LinkedHashMap<>();

  Label labActorType;
  Label labid;
  JComboBox<ActorType> selActorType;

  JTextArea editorBehaviorArea;
  JTextField tfFancyName;
  JTextField tfUsername;
  JTextField tfEmail;
  JTextField tfLastCountry;
  JTextField tfLastIp;
  JTextField tfLastCid;
  JTextField tfLanguages;
  JTextField tfUserAgent;

  UtilDateModel modelBirthdate;
  UtilDateModel modelRegistration;
  UtilDateModel modelLastLoginDate;

  JCheckBox cbSuspect;
  JCheckBox cbBlocked;
  JCheckBox cbDoNotPay;
  JCheckBox cbEmailVerified;
  JCheckBox cbPaymentsBlocked;

  private JLabel imageLabel;


  public ActorDialogWindow(WorldFrame parent, ActorDialogController controller) {
    super(parent, true);

    this.parent = parent;
    this.controller = controller;
    init();

  }

  public ActorDialogWindow(WorldFrame parent, ActorDialogController controller, Actor actor) {
    super(parent, true);

    this.parent = parent;
    this.controller = controller;
    this.actor = actor;

    init();
  }

  private void init() {
    worker.execute();

    try {
      cmds = worker.get();
    } catch (InterruptedException | ExecutionException e1) {
      e1.printStackTrace();
    }

    add(buildMainPane());
    setResizable(true);
    setMinimumSize(new Dimension(650, 700));

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(WindowEvent e) {
        emptyFields();
      }
    });

    validate();
    setLocationRelativeTo(parent);
  }

  SwingWorker<Map<String, String>, Void> worker = new SwingWorker<>() {
    @Override
    public Map<String, String> doInBackground() {
      final Map<String, String> inCmds = new LinkedHashMap<>();

      inCmds.put("start session", CommandName.START_SESSION.toString());
      inCmds.put("do clicks", CommandName.DO_CLICKS.toString());
      inCmds.put("do transactions", CommandName.DO_TRANSACTIONS.toString());
      inCmds.put("close session", CommandName.CLOSE_SESSION.toString());

      return inCmds;
    }
  };

  private JPanel buildMainPane() {
    JPanel mainPane = new JPanel();
    mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));

    if (actor == null) {
      mainPane.add(buildActorTypePane());
    }

    JTabbedPane contentsPane = buildTabbedPane();
    contentsPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    mainPane.add(contentsPane);

    JPanel buttonsPane = buildButtons();
    mainPane.add(buttonsPane);

    return mainPane;
  }

  JPanel buildActorTypePane() {
    JPanel actorTypePane = new JPanel(new FlowLayout(FlowLayout.CENTER));
    Label labActorType = new Label("Type of Actor");
    actorTypePane.add(labActorType);
    selActorType = new JComboBox<>(ActorType.values());
    selActorType.setSelectedIndex(-1);
    actorTypePane.add(selActorType);
    selActorType.setActionCommand(ActorDialogCommand.SELECT_TYPE.toString());
    selActorType.addActionListener(controller);

    return actorTypePane;
  }


  JTabbedPane buildTabbedPane() {
    JTabbedPane tabbedPane = new JTabbedPane();
    URL inputAvatar = ActorDialogWindow.class.getResource("/images/duke-01.png");
    ImageIcon duke = new ImageIcon(getScaledImage(new ImageIcon(inputAvatar).getImage(), 28, 20));

    tabbedPane.addTab("Attributes", duke, buildContentsPane());
    tabbedPane.addTab("Behavior", duke, buildBehaviorPane());

    return tabbedPane;
  }


  JSplitPane buildBehaviorPane() {
    JSplitPane splitPane =
        new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getCommandsPane(), getBehaviorEditorPane());
    splitPane.setOneTouchExpandable(true);
    splitPane.setDividerLocation(150);

    return splitPane;
  }

  private JScrollPane getBehaviorEditorPane() {
    JScrollPane mainPane = new JScrollPane();
    editorBehaviorArea = new JTextArea();
    mainPane.setViewportView(editorBehaviorArea);

    return mainPane;
  }

  JScrollPane getCommandsPane() {
    DefaultListModel<String> data = new DefaultListModel<>();

    cmds.forEach((k, v) -> {
      data.addElement(k);
    });

    JList<String> list = new JList<>(data);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    list.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
          editorBehaviorArea.append(cmds.get(data.getElementAt(list.getSelectedIndex())) + "\n");
        }
      }
    });

    JScrollPane mainPane = new JScrollPane();
    mainPane.setViewportView(list);

    return mainPane;
  }

  JPanel buildContentsPane() {
    JPanel framePane = new JPanel();
    GridBagLayout gbl = new GridBagLayout();

    framePane.setLayout(gbl);

    labActorType = new Label();
    labid = new Label();
    JPanel infoPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
    infoPane.setPreferredSize(new Dimension(100, 50));
    infoPane.add(labActorType);
    infoPane.add(labid);

    GridBagConstraints gbc;
    gbc = makegbc(0, 0, 4, 1);
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbl.setConstraints(infoPane, gbc);
    framePane.add(infoPane);

    gbc = makegbc(0, 1, 1, 1);
    gbc.weightx = 0;
    gbc.weighty = 0;
    gbc.anchor = GridBagConstraints.NORTHWEST;
    Label labFancyName = new Label("Name");
    gbl.setConstraints(labFancyName, gbc);
    framePane.add(labFancyName);

    gbc = makegbc(1, 1, 3, 1);
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    tfFancyName = new JTextField();
    gbl.setConstraints(tfFancyName, gbc);
    framePane.add(tfFancyName);

    gbc = makegbc(0, 2, 1, 1);
    gbc.anchor = GridBagConstraints.NORTHWEST;
    Label labUsername = new Label("Username");
    gbl.setConstraints(labUsername, gbc);
    framePane.add(labUsername);

    gbc = makegbc(1, 2, 3, 1);
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    tfUsername = new JTextField();
    gbl.setConstraints(tfUsername, gbc);
    framePane.add(tfUsername);

    gbc = makegbc(0, 3, 1, 1);
    gbc.anchor = GridBagConstraints.NORTHWEST;
    Label labEmail = new Label("Email");
    gbl.setConstraints(labEmail, gbc);
    framePane.add(labEmail);

    gbc = makegbc(1, 3, 3, 1);
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    tfEmail = new JTextField();
    gbl.setConstraints(tfEmail, gbc);
    framePane.add(tfEmail);

    gbc = makegbc(0, 4, 1, 1);
    gbc.anchor = GridBagConstraints.NORTHWEST;
    Label labLastCountry = new Label("Last country");
    gbl.setConstraints(labLastCountry, gbc);
    framePane.add(labLastCountry);

    gbc = makegbc(1, 4, 3, 1);
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    tfLastCountry = new JTextField();
    gbl.setConstraints(tfLastCountry, gbc);
    framePane.add(tfLastCountry);

    gbc = makegbc(0, 5, 1, 1);
    gbc.anchor = GridBagConstraints.NORTHWEST;
    Label labLastIp = new Label("Last Ip");
    gbl.setConstraints(labLastIp, gbc);
    framePane.add(labLastIp);

    gbc = makegbc(1, 5, 3, 1);
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    tfLastIp = new JTextField();
    gbl.setConstraints(tfLastIp, gbc);
    framePane.add(tfLastIp);

    gbc = makegbc(0, 6, 1, 1);
    gbc.anchor = GridBagConstraints.NORTHWEST;
    Label labLastCid = new Label("Last CID");
    gbl.setConstraints(labLastCid, gbc);
    framePane.add(labLastCid);

    gbc = makegbc(1, 6, 3, 1);
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    tfLastCid = new JTextField();
    gbl.setConstraints(tfLastCid, gbc);
    framePane.add(tfLastCid);

    gbc = makegbc(0, 7, 1, 1);
    gbc.fill = GridBagConstraints.NONE;
    Label labLanguages = new Label("Languages");
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbl.setConstraints(labLanguages, gbc);
    framePane.add(labLanguages);

    gbc = makegbc(1, 7, 3, 1);
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    tfLanguages = new JTextField();
    gbl.setConstraints(tfLanguages, gbc);
    framePane.add(tfLanguages);

    gbc = makegbc(0, 8, 1, 1);
    gbc.fill = GridBagConstraints.NONE;
    Label labUserAgent = new Label("User agent");
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbl.setConstraints(labUserAgent, gbc);
    framePane.add(labUserAgent);

    gbc = makegbc(1, 8, 3, 1);
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    tfUserAgent = new JTextField();
    gbl.setConstraints(tfUserAgent, gbc);
    framePane.add(tfUserAgent);

    gbc = makegbc(0, 9, 1, 1);
    gbc.fill = GridBagConstraints.NONE;
    Label labBirthdate = new Label("Birthdate");
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbl.setConstraints(labBirthdate, gbc);
    framePane.add(labBirthdate);

    gbc = makegbc(1, 9, 3, 1);
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    modelBirthdate = new UtilDateModel();
    JDatePanelImpl datePanelBirthdate = new JDatePanelImpl(modelBirthdate);
    JDatePickerImpl datePickerBirthdate = new JDatePickerImpl(datePanelBirthdate);
    gbl.setConstraints(datePickerBirthdate, gbc);
    framePane.add(datePickerBirthdate);

    gbc = makegbc(0, 10, 1, 1);
    gbc.fill = GridBagConstraints.NONE;
    Label labRegistration = new Label("Registration date");
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbl.setConstraints(labRegistration, gbc);
    framePane.add(labRegistration);

    gbc = makegbc(1, 10, 3, 1);
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    modelRegistration = new UtilDateModel();
    JDatePanelImpl datePanelRegistration = new JDatePanelImpl(modelRegistration);
    JDatePickerImpl datePickerRegistration = new JDatePickerImpl(datePanelRegistration);
    gbl.setConstraints(datePickerRegistration, gbc);
    framePane.add(datePickerRegistration);

    gbc = makegbc(0, 11, 1, 1);
    gbc.fill = GridBagConstraints.NONE;
    Label labLastLoginDate = new Label("Last Login date");
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbl.setConstraints(labLastLoginDate, gbc);
    framePane.add(labLastLoginDate);

    gbc = makegbc(1, 11, 3, 1);
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    modelLastLoginDate = new UtilDateModel();
    JDatePanelImpl datePanelLastLoginDate = new JDatePanelImpl(modelLastLoginDate);
    JDatePickerImpl datePickerLastLoginDate = new JDatePickerImpl(datePanelLastLoginDate);
    gbl.setConstraints(datePickerLastLoginDate, gbc);
    framePane.add(datePickerLastLoginDate);

    Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);

    TitledBorder titleBehaviorScrollPane = BorderFactory.createTitledBorder(loweredetched, "");
    titleBehaviorScrollPane.setTitleJustification(TitledBorder.RIGHT);

    JPanel checkBoxesPane = new JPanel(new GridLayout(2, 3, 5, 5));
    checkBoxesPane.setBorder(titleBehaviorScrollPane);

    Label labEmailVerified = new Label("Email verified");
    cbEmailVerified = new JCheckBox();
    JPanel emailVerifiedPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
    emailVerifiedPane.add(cbEmailVerified);
    emailVerifiedPane.add(labEmailVerified);
    checkBoxesPane.add(emailVerifiedPane);


    Label labPaymentsBlocked = new Label("Payments blocked");
    cbPaymentsBlocked = new JCheckBox();
    JPanel paymentsBlockedPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
    paymentsBlockedPane.add(cbPaymentsBlocked);
    paymentsBlockedPane.add(labPaymentsBlocked);
    checkBoxesPane.add(paymentsBlockedPane);


    Label labBlocked = new Label("Actor blocked");
    cbBlocked = new JCheckBox();
    JPanel blockedPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
    blockedPane.add(cbBlocked);
    blockedPane.add(labBlocked);
    checkBoxesPane.add(blockedPane);


    Label labDoNotPay = new Label("Do not pay");
    cbDoNotPay = new JCheckBox();
    JPanel doNotPayPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
    doNotPayPane.add(cbDoNotPay);
    doNotPayPane.add(labDoNotPay);
    checkBoxesPane.add(doNotPayPane);

    Label labSuspect = new Label("Suspect");
    cbSuspect = new JCheckBox();
    JPanel suspectPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
    suspectPane.add(cbSuspect);
    suspectPane.add(labSuspect);
    checkBoxesPane.add(suspectPane);

    gbc = makegbc(1, 12, 3, 1);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbl.setConstraints(checkBoxesPane, gbc);
    framePane.add(checkBoxesPane);


    JPanel imagePane = new JPanel();

    imageLabel = new JLabel();
    imagePane.add(getImageLabel());

    JButton fileChooserButton = new JButton("Choose the Actor's avatar");

    fileChooserButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        if (f != null) {
          createImageLabel(f);
        }
      }

      private void createImageLabel(File f) {
        try {
          ImageIcon ii = new ImageIcon(ViewUtils.scaleImage(120, 120, ImageIO.read(f)));
          getImageLabel().setIcon(ii);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });

    imagePane.add(fileChooserButton);


    gbc = makegbc(1, 13, 3, 1);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbl.setConstraints(imagePane, gbc);
    framePane.add(imagePane);



    // numOfTransactions=0
    // numberOfSessions=0
    // numOfClaims=-1877129635
    //


    // balance=null
    //
    //
    // bitcoinAccount=BitcoinAccount [address=sMRA6wKkcby7Xb1x8Hgh5UqO]
    // paypalAccount=PaypalAccount [address=shedmarshall@mac.com, accountHolder=Eldon Boutchyard]
    // bankAccount=BankAccount [iban=DE81163439738752716029, bic=DEUTDEFF500, accountHolder=Courtney
    // Eriquez]
    //

    return framePane;
  }



  private JPanel buildButtons() {
    Button resetButton = new Button("Restore Defaults");
    resetButton.setActionCommand(ActorDialogCommand.RESET.toString());
    resetButton.addActionListener(controller);

    Button saveButton = new Button("Apply and Close");
    saveButton.setActionCommand(ActorDialogCommand.SAVE.toString());
    saveButton.addActionListener(controller);

    Button closeButton = new Button("Close");
    closeButton.setActionCommand(ActorDialogCommand.CLOSE.toString());
    closeButton.addActionListener(controller);

    JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
    buttons.add(resetButton);
    buttons.add(saveButton);
    buttons.add(closeButton);

    return buttons;
  }

  private void emptyFields() {
    labActorType.setText("");
    labid.setText("");

    tfFancyName.setText("");
    tfUsername.setText("");
    tfEmail.setText("");
    tfLastCountry.setText("");
    tfLastIp.setText("");
    tfLastCid.setText("");
    tfLanguages.setText("");
    tfUserAgent.setText("");

    modelBirthdate.setValue(null);
    modelRegistration.setValue(null);
    modelLastLoginDate.setValue(null);

    cbSuspect.setSelected(false);
    cbBlocked.setSelected(false);
    cbDoNotPay.setSelected(false);
    cbEmailVerified.setSelected(false);
    cbPaymentsBlocked.setSelected(false);
    editorBehaviorArea.setText("");
    imageLabel.setIcon(null);
  }

  public void loadFields() {
    labActorType.setText("Actor Type: " + actor.getType());
    labid.setText("id: " + actor.getActorId());

    tfFancyName.setText(actor.getFancyName());
    tfUsername.setText(actor.getUsername());
    tfEmail.setText(actor.getEmail());
    tfLastCountry.setText(actor.getLastCountry());
    tfLastIp.setText(actor.getLastIp());
    tfLastCid.setText(actor.getLastCid());
    tfLanguages.setText(actor.getLanguages());
    tfUserAgent.setText(actor.getUserAgent());

    modelBirthdate.setValue(actor.getBirthdate());
    modelRegistration.setValue(actor.getRegistrationDate());
    modelLastLoginDate.setValue(actor.getLastLoginDate());

    cbSuspect.setSelected(actor.isSuspect());
    cbBlocked.setSelected(actor.isBlocked());
    cbDoNotPay.setSelected(actor.isDoNotPay());
    cbEmailVerified.setSelected(actor.isEmailVerified());
    cbPaymentsBlocked.setSelected(actor.isPaymentsBlocked());

    if (actor.getBehavior() != null) {
      editorBehaviorArea.setText(actor.getBehavior().getProgram());
    }

    getImageLabel().setIcon(actor.getIcon());
  }

  private GridBagConstraints makegbc(int x, int y, int width, int height) {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = x;
    gbc.gridy = y;
    gbc.weighty = 1.0;
    gbc.gridwidth = width;
    gbc.gridheight = height;
    gbc.insets = new Insets(0, 0, 0, 0);
    gbc.ipadx = 0;
    gbc.ipady = 0;

    return gbc;
  }

  private Image getScaledImage(Image srcImg, int w, int h) {
    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = resizedImg.createGraphics();

    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.drawImage(srcImg, 0, 0, w, h, null);
    g2.dispose();

    return resizedImg;
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(500, 400);
  }

  public Actor getActor() {
    return actor;
  }

  public void setActor(Actor actor) {
    this.actor = actor;
  }

  public Label getLabActorType() {
    return labActorType;
  }

  public Label getLabid() {
    return labid;
  }

  public JComboBox<ActorType> getSelActorType() {
    return selActorType;
  }

  public JTextField getTfFancyName() {
    return tfFancyName;
  }

  public JTextField getTfUsername() {
    return tfUsername;
  }

  public JTextField getTfEmail() {
    return tfEmail;
  }

  public JTextField getTfLastCountry() {
    return tfLastCountry;
  }

  public JTextField getTfLastIp() {
    return tfLastIp;
  }

  public JTextField getTfLastCid() {
    return tfLastCid;
  }

  public JTextField getTfLanguages() {
    return tfLanguages;
  }

  public JTextField getTfUserAgent() {
    return tfUserAgent;
  }

  public UtilDateModel getModelBirthdate() {
    return modelBirthdate;
  }

  public UtilDateModel getModelRegistration() {
    return modelRegistration;
  }

  public UtilDateModel getModelLastLoginDate() {
    return modelLastLoginDate;
  }

  public JCheckBox getCbSuspect() {
    return cbSuspect;
  }

  public JCheckBox getCbBlocked() {
    return cbBlocked;
  }

  public JCheckBox getCbDoNotPay() {
    return cbDoNotPay;
  }

  public JCheckBox getCbEmailVerified() {
    return cbEmailVerified;
  }

  public JCheckBox getCbPaymentsBlocked() {
    return cbPaymentsBlocked;
  }

  public JTextArea getEditorBehaviorArea() {
    return editorBehaviorArea;
  }

  public void setEditorBehaviorArea(JTextArea editorBehaviorArea) {
    this.editorBehaviorArea = editorBehaviorArea;
  }

  public JLabel getImageLabel() {
    return imageLabel;
  }
}

package de.cdelmonte.fds.datagenerator.orchestrator.gui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.lang.ModuleLayer.Controller;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

import de.cdelmonte.fds.datagenerator.orchestrator.gui.model.ActorPropertiesModel;


public class ActorTableView extends JScrollPane {
  private JTable table;
  private ActorPropertiesModel tableModel;
  private Controller controller;
  private JTextField filterText;

  TableRowSorter<ActorPropertiesModel> sorter;

  public ActorTableView(ActorPropertiesModel tableModel) {
    super();

    this.tableModel = tableModel;
    table = new JTable(tableModel);
    table.setAutoCreateRowSorter(true);
    table.setPreferredScrollableViewportSize(new Dimension(500, 70));
    table.setFillsViewportHeight(true);

    sorter = new TableRowSorter<ActorPropertiesModel>(tableModel);
    table.setRowSorter(sorter);

    JPanel tablePane = new JPanel();

    tablePane.setLayout(new BorderLayout());
    tablePane.add(getTable().getTableHeader(), BorderLayout.PAGE_START);
    tablePane.add(getTable(), BorderLayout.CENTER);
    setViewportView(tablePane);

    JPanel selectPane = new JPanel();
    selectPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    FlowLayout flSelectPane = (FlowLayout) selectPane.getLayout();
    flSelectPane.setAlignment(FlowLayout.LEADING);
    setColumnHeaderView(selectPane);

    filterText = new JTextField();
    filterText.setPreferredSize(new Dimension(60, verticalScrollBarPolicy));
    filterText.getDocument().addDocumentListener(new DocumentListener() {
      public void changedUpdate(DocumentEvent e) {
        newFilter(filterText.getText());
      }

      public void insertUpdate(DocumentEvent e) {
        newFilter(filterText.getText());
      }

      public void removeUpdate(DocumentEvent e) {
        newFilter(filterText.getText());
      }
    });

    JLabel l1 = new JLabel("Filter Text:", SwingConstants.TRAILING);
    l1.setLabelFor(filterText);
    selectPane.add(l1);
    selectPane.add(filterText);
  }

  public void newFilter(String s) {
    if (tableModel.getRowCount() < 1) {
      return;
    }

    RowFilter<ActorPropertiesModel, Object> rf = null;
    try {
      rf = RowFilter.regexFilter(s);
    } catch (java.util.regex.PatternSyntaxException e) {
      return;
    }
    sorter.setRowFilter(rf);
    // table.getSelectionModel().clearSelection();
  }

  public ActorPropertiesModel getTableModel() {
    return tableModel;
  }

  public Controller getController() {
    return controller;
  }

  public JTable getTable() {
    return table;
  }

  public void setFilterText(String s) {
    filterText.setText(s);
    repaint();
  }

  public JTextField getFilterText() {
    return filterText;
  }
}

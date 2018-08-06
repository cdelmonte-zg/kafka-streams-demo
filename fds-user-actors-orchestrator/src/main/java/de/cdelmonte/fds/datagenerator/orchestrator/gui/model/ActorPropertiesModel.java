package de.cdelmonte.fds.datagenerator.orchestrator.gui.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;


public class ActorPropertiesModel extends AbstractTableModel {
  private static final long serialVersionUID = 1L;
  private String[] columnNames = {"type", "id", "username", "name", "suspect"};
  private List<List<Object>> data = new ArrayList<>();

  public void addActors(List<Actor> actors) {
    data = new ArrayList<>();
    actors.forEach(a -> {
      addActor(a);
    });
  }

  public void addActor(Actor a) {
    data.add(Arrays.asList(a.getType(), a.getActorId(), a.getUsername(), a.getFancyName(),
        a.isSuspect()));
  }

  public void removeActor(Actor a) {
    data.remove(a);
  }

  @Override
  public String getColumnName(int col) {
    return columnNames[col];
  }

  @Override
  public int getRowCount() {
    return data.size();
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    return data.get(rowIndex).get(columnIndex);
  }

  @Override
  public Class<? extends Object> getColumnClass(int c) {
    return getValueAt(0, c).getClass();
  }

  public List<List<Object>> getData() {
    return data;
  }

  public boolean isCellEditable(int row, int col) {
    return false;
  }

  public void setValueAt(Object value, int rowIndex, int columnIndex) {
    if (data.size() > 0) {
      data.get(rowIndex).set(columnIndex, value);
      fireTableCellUpdated(rowIndex, columnIndex);
    }
  }
}

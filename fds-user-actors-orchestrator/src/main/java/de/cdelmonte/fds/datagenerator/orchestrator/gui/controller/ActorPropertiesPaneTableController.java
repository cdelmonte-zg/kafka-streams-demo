package de.cdelmonte.fds.datagenerator.orchestrator.gui.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import de.cdelmonte.fds.datagenerator.orchestrator.gui.model.ActorPropertiesModel;
import de.cdelmonte.fds.datagenerator.orchestrator.gui.observer.Observer;
import de.cdelmonte.fds.datagenerator.orchestrator.gui.view.ActorTableView;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;
import de.cdelmonte.fds.datagenerator.orchestrator.model.world.World;


public class ActorPropertiesPaneTableController implements TableModelListener, Observer {
  private World world;
  private ActorPropertiesModel tableModel;
  private ActorTableView view;


  public ActorPropertiesPaneTableController(World world, ActorPropertiesModel tableModel,
      ActorTableView view) {

    this.world = world;
    this.tableModel = tableModel;
    this.view = view;
    tableModel.addActors(getActors());
  }

  public int getIndex(int index) {
    if (index > -1) {
      return view.getTable().convertRowIndexToModel(index);
    } else
      return 0;

  }

  @Override
  public void tableChanged(TableModelEvent e) {
    TableModel model = (TableModel) e.getSource();
    int row = e.getFirstRow();
    int column = e.getColumn();
    String columnName = model.getColumnName(column);
    Object data = model.getValueAt(row, column);

    Actor actor = getActors().get(row);
    String methodName =
        ("set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1));
    Method method = null;

    try {
      method = actor.getClass().getMethod(methodName, data.getClass());
    } catch (SecurityException ex) {
      ex.printStackTrace();
    } catch (NoSuchMethodException ex) {
      ex.printStackTrace();
    }

    try {
      method.invoke(actor, data);
    } catch (IllegalArgumentException ex) {
      ex.printStackTrace();
    } catch (IllegalAccessException ex) {
      ex.printStackTrace();
    } catch (InvocationTargetException ex) {
      ex.printStackTrace();
    }

    view.validate();
  }

  private List<Actor> getActors() {
    return world.getList();
  }

  @Override
  public void update() {
    view.requestFocusInWindow();
    tableModel.addActors(getActors());
    view.newFilter(view.getFilterText().getText());
    view.repaint();
  }

  public void rowDeleted(int i) {
    tableModel.fireTableRowsDeleted(i, i);

  }
}

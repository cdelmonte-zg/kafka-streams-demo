package de.cdelmonte.fds.datagenerator.orchestrator.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;
import de.cdelmonte.fds.datagenerator.orchestrator.model.view.ActorPropertiesModel;
import de.cdelmonte.fds.datagenerator.orchestrator.model.world.World;
import de.cdelmonte.fds.datagenerator.orchestrator.view.ActorPropertiesPane;


public class ActorPropertiesPaneTableController implements TableModelListener, Observer {
  private World world;
  private ActorPropertiesModel tableModel;
  private ActorPropertiesPane view;


  public ActorPropertiesPaneTableController(World world, ActorPropertiesModel tableModel,
      ActorPropertiesPane view) {

    this.world = world;
    this.tableModel = tableModel;
    this.view = view;
    tableModel.addActors(getActors());

  }

  @Override
  public void tableChanged(TableModelEvent e) {
    int row = e.getFirstRow();
    int column = e.getColumn();
    // TableModel model = (TableModel) e.getSource();
    String columnName = tableModel.getColumnName(column);
    Object data = tableModel.getValueAt(row, column);

    Actor actor = getActors().get(row);
    String methodName =
        ("set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1));
    Method method = null;

    System.out.println(actor.getFancyName() + " " + methodName + "::" + " = " + data);
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
    tableModel.addActors(getActors());
    view.repaint();
  }
}

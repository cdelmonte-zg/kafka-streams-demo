package de.cdelmonte.fds.datagenerator.orchestrator.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;
import de.cdelmonte.fds.datagenerator.orchestrator.model.world.World;


public class WorldInOutService {
  private static final String CONFIG_DIR = "config/";
  private static final String CONFIG_FILE = "world-config.cfg";

  public static void saveWorld(World world) throws IOException {
    OutputStream os = new FileOutputStream(getConfigFile());
    ObjectOutputStream oos = new ObjectOutputStream(os);
    oos.writeObject(world);
    oos.close();
  }

  public static World readWorld() throws IOException {
    InputStream is = new FileInputStream(getConfigFile());
    ObjectInputStream ois = new ObjectInputStream(is);

    World world = null;
    try {
      world = (World) ois.readObject();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    for (Actor actor : (ArrayList<Actor>) world.getList()) {
      actor.start();
    }

    return world;
  }

  private static File getConfigFile() {
    ClassLoader classLoader = WorldInOutService.class.getClassLoader();

    return new File(classLoader.getResource(CONFIG_DIR + CONFIG_FILE).getFile());
  }
}

package de.cdelmonte.fds.datagenerator.orchestrator.gui.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.SwingWorker;

import de.cdelmonte.fds.datagenerator.orchestrator.interpreter.CommandName;


public class ViewUtils {
  public static BufferedImage scaleImage(int w, int h, BufferedImage img) throws Exception {
    BufferedImage bi;
    bi = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
    Graphics2D g2d = (Graphics2D) bi.createGraphics();
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.addRenderingHints(
        new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
    g2d.drawImage(img, 0, 0, w, h, null);
    g2d.dispose();
    return bi;
  }

  public static SwingWorker<Map<String, String>, Void> worker = new SwingWorker<>() {
    @Override
    public Map<String, String> doInBackground() {
      final Map<String, String> inCmds = new LinkedHashMap<>();

      inCmds.put("say hello", CommandName.SAY_HELLO.toString());
      inCmds.put("start session", CommandName.START_SESSION.toString());
      inCmds.put("do clicks", CommandName.DO_CLICKS.toString());
      inCmds.put("do transactions", CommandName.DO_TRANSACTIONS.toString());
      inCmds.put("close session", CommandName.CLOSE_SESSION.toString());

      return inCmds;
    }
  };
}

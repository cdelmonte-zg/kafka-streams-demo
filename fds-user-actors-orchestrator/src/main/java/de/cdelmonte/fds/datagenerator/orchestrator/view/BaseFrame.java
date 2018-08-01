package de.cdelmonte.fds.datagenerator.orchestrator.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicLookAndFeel;

import com.bulenkov.darcula.DarculaLaf;


public class BaseFrame extends JFrame {
  BaseFrame() {
    super();

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });


    BasicLookAndFeel darcula = new DarculaLaf();
    try {
      UIManager.setLookAndFeel(darcula);
    } catch (UnsupportedLookAndFeelException e1) {
      e1.printStackTrace();
    }

    setSize(600, 400);
    setLocationRelativeTo(null);
  }
}

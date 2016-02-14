package controller;

import java.awt.EventQueue;

import view.OVmainView;

public class Main {

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          OVmainView window = new OVmainView(800, 600);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

}

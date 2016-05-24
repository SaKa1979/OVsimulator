package controller;

import java.awt.EventQueue;

public class Main {

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      @Override
	public void run() {
        try {
          @SuppressWarnings("unused")
          SimController simulator = new SimController();
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

}

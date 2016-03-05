package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import view.ViewManager;
import view.PortSettingPanel;
import view.VehicleButton;

public class SimControler implements Event {

  /**
   * Constructor
   */
  public SimControler(){
    initialize();
  }

  // PUBLIC METHOD
  
  // PRIVATE METHOD
  private void initialize(){
    for (int i = 0; i < 20; i++){
      view.addVehicleButtonAndListener(vehicleSimulationListener);
    }

    view.addProtoListener(protoListener);
    view.addPortSettingEventSubscriber(this);
  }
  
  //LISTENERS and SIGNALS
  /**
   * @brief Is called whenever some action is taken by the callee
   * @param o When the method is called, the callee may pass a object.
   */
    public void signal(Object o){
      PortSettingPanel psp = (PortSettingPanel)o;
      view.writeToFeedback(psp.toString());
    }
  
  /**
   * @brief Action listener for handling the simulation of all vehicleButtons
   *        Action is triggered by a VehicleButton
   */
  ActionListener vehicleSimulationListener = new ActionListener() {   
    @Override
    public void actionPerformed(ActionEvent e) {
      VehicleButton vb = (VehicleButton)e.getSource();
      view.writeToFeedback(vb.toString());
    }
  };
   
  /**
   * @brief Listens to Protocol menu item
   */
  ActionListener protoListener = new ActionListener() {
    
    @Override
    public void actionPerformed(ActionEvent e) {
      JRadioButton rb = (JRadioButton)e.getSource();
      
      if (rb.getText().equals("KAR")){
        view.writeToFeedback(rb.getText());
      }else if (rb.getText().equals("VECOM")){
        view.writeToFeedback(rb.getText());
      }else if (rb.getText().equals("SICS")){
        view.writeToFeedback(rb.getText());
      }
    }
  };
  
  
  // PRIVATE ATTRIBUTES
  ViewManager view = new ViewManager(800, 800);
  
}// end ov class SimControler

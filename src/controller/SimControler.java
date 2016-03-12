package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ViewManager;
import view.PortSettingPanel;
import view.ProtocolPanel;
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

//    view.addProtoListener(protoListener);
    view.addEventSubscriber(this);
  }
  
  //LISTENERS and SIGNALS
  /**
   * @brief Is called whenever some action is taken by the callee
   * @param o When the method is called, the callee may pass an object.
   */
  public void signal(Object o){
    if (o instanceof PortSettingPanel){
      PortSettingPanel psp = (PortSettingPanel)o;
      view.writeToFeedback(psp.toString());
    }else if (o instanceof ProtocolPanel){
      ProtocolPanel pp = (ProtocolPanel)o;
      view.writeToFeedback(pp.toString());
    }
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
  
  // PRIVATE ATTRIBUTES
  ViewManager view = new ViewManager(800, 800);
  
}// end ov class SimControler

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import view.OVmainView;
import view.VehicleButton;

public class SimControler {
  
  // PUBLIC METHOD
  public SimControler(){
    initialize();
  }
  
  // PRIVATE METHOD
  private void initialize(){
    for (int i = 0; i < 20; i++){
      view.addVehicleButtonAndListener(new VehicleSimulationListener());
    }

    view.addAboutListener(aboutMenuListener);
    view.addProtoListener(protoListener);
    view.addPortSettingListener(PortSettingListener);
  }
  //LISTENERS
  /**
   * @brief Action listener for handling the simulation of all vehicleButtons
   *        Action is triggered by a VehicleButton
   */
  class VehicleSimulationListener implements ActionListener {
          public void actionPerformed(ActionEvent e) {
                  VehicleButton vb = (VehicleButton)e.getSource();
                  System.out.println("button index " 
                  + vb.getName()               + " LoopNr "              
                  + vb.getLoopNr()             + " SignalGroupNr "       
                  + vb.getSignalGroupNr()      + " Direction "           
                  + vb.getDirection()          + " Command "             
                  + vb.getCommand()            + " VehicleType "         
                  + vb.getVehicleType()        + " LineNr "              
                  + vb.getLineNr()             + " WagonNr "             
                  + vb.getWagonNr()            + " VehicleId "           
                  + vb.getVehicleId()          + " VehicleLength "       
                  + vb.getVehicleLength()      + " VehicleSpeed "        
                  + vb.getVehicleSpeed()       + " DistanceToStop "      
                  + vb.getDistanceToStop()     + " TimeToStop "          
                  + vb.getTimeToStop()         + " VehicleStatus "       
                  + vb.getVehicleStatus()      + " PriorityClass "       
                  + vb.getPriorityClass()      + " PunctualityClass "    
                  + vb.getPunctualityClass()   + " Punctuality "         
                  + vb.getPunctuality()
                  );      
          }
  }//end inner class VehicleSimulationListener
  
  /**
   * @brief Listens to the About menu item
   */
  MenuListener aboutMenuListener = new MenuListener() {
    
    @Override
    public void menuSelected(MenuEvent e) {
      JOptionPane.showMessageDialog((JMenu)e.getSource(), "Created by Sander",((JMenu)e.getSource()).getName(), JOptionPane.INFORMATION_MESSAGE);
    }
    @Override
    public void menuDeselected(MenuEvent e) {
      // TODO Auto-generated method stub
    }
    @Override
    public void menuCanceled(MenuEvent e) {
      // TODO Auto-generated method stub
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
  
  /**
   * @brief Listens to PortSetting menu item
   */
  ActionListener PortSettingListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
            // TODO what to do with portsetting
    }
  };  
  
  // PRIVATE ATTRIBUTES
  OVmainView view = new OVmainView(800, 800);
  
}// end ov class SimControler

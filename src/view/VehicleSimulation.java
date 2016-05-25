package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.Event;
import lombok.Getter;

public class VehicleSimulation extends JPanel {

  private static final long serialVersionUID = 2L;

  /**
   * Create the panel.
   */
  public VehicleSimulation() {
    setBorder(new LineBorder(new Color(0, 0, 0)));
    setLayout(new GridLayout(10, 2, 2, 2));
    setToolTipText("Press the left mouse button to sent public transport message to the ITC.\r\nPress the right mouse button to configure this button.");
    vbList = new ArrayList<>();
    createButton();
  }

  // PUBLIC METHOD
  /**
   * @brief The subsriber gets a signal when a certain event takes place.
   * @param a_subscriber
   */
  public void addEventSubscriber(Event a_subscriber){
    subscriber  = a_subscriber;
  }
  

  public ActionListener getVehicleSimulationListener() {
    return vehicleSimulationListener;
  }
  public void setVehicleSimulationListener(
      ActionListener vehicleSimulationListener) {
    this.vehicleSimulationListener = vehicleSimulationListener;
  }

  //PRIVATE METHODS
  private void createButton(){
    for (int i = 0; i < 20; i++){
      VehicleButton btnVeh = new VehicleButton();
      btnVeh.addActionListener(vehicleSimulationListener);
      add(btnVeh);
      vbList.add(btnVeh);
    }
  }
  
  // LISTENERS
  ActionListener vehicleSimulationListener = new ActionListener() {   
    @Override
    public void actionPerformed(ActionEvent e) {
      VehicleButton vb = (VehicleButton)e.getSource();
      subscriber.signal(vb, null);
    }
  };

  // PRIVATE ATTRIBUTES
  private Event subscriber;
  @Getter private ArrayList<VehicleButton> vbList;

}// end class VehicleSimulation

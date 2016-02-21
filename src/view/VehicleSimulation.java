package view;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class VehicleSimulation extends JPanel {

  /**
   * Create the panel.
   */
  public VehicleSimulation() {
    setBorder(new LineBorder(new Color(0, 0, 0)));
    setLayout(new GridLayout(10, 2, 2, 2));
    setToolTipText("Press the left mouse button to sent public transport message to the ITC.\r\nPress the right mouse button to configure this button.");

    // add the buttons
    for (int i = 0; i < 20; i++){
      VehicleButton btnVeh = new VehicleButton(""+i);
      add(btnVeh);
      vehicleButtonList.add(btnVeh);
    }

  }
  // public method
  /**
   * @ brief List holding all VehicleBUttons associated with this class.
   * @return The list of associated VehicleButtons
   */
  public ArrayList<VehicleButton> getVehicleButtonList() {
    return vehicleButtonList;
  }
  /**
   * @brief Add a MouseListener to a VehicleButton. 
   * First get the List of VehicleButtons from this class.
   * @param ml The MouseListener
   * @param vb The VehicleButton
   */
  public void addVehicleSimulateListener(ActionListener al, VehicleButton vb){
    vb.addActionListener(al);
  }
  
  public VehicleButton getVehicleButtonByName(String name){
    return vehicleButtonList.get(Integer.parseInt(name));
  }
    

  // private attribute
  ArrayList<VehicleButton> vehicleButtonList = new ArrayList<VehicleButton>();
}

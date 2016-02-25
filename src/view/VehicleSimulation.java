package view;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class VehicleSimulation extends JPanel {

  /**
   * Create the panel.
   */
  public VehicleSimulation() {
    setBorder(new LineBorder(new Color(0, 0, 0)));
    setLayout(new GridLayout(10, 2, 2, 2));
    setToolTipText("Press the left mouse button to sent public transport message to the ITC.\r\nPress the right mouse button to configure this button.");
  }
  
  // PUBLIC METHOD
  
  public VehicleButton createAndAddVehicleButton(ActionListener a_listener){
    VehicleButton btnVeh = new VehicleButton();
    add(btnVeh);
    btnVeh.addActionListener(a_listener);
    return btnVeh;
  }

}// end class VehicleSimulation

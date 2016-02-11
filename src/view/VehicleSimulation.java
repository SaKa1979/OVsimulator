package view;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

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
      VehicleButton btnVeh = new VehicleButton("veh_"+i);
      add(btnVeh);
    }

  }

}

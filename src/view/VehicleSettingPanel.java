package view;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VehicleSettingPanel extends JPanel {
  private JTextField LoopNrTF;

  /**
   * Constructor
   * @param vehicle_button
   */
  public VehicleSettingPanel(VehicleButton a_vehicle_button) {
    vehicle_button = a_vehicle_button;
    setBorder(new TitledBorder(null, "Vehicle Setting", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[]{50, 200};
    gridBagLayout.rowHeights = new int[] {20, 20};
    gridBagLayout.columnWeights = new double[]{0.0, 0.0};
    gridBagLayout.rowWeights = new double[]{0.0, 0.0};
    setLayout(gridBagLayout);
    
    // loop number
    JCheckBox chckbxNewCheckBox = new JCheckBox("Loop nr");
    chckbxNewCheckBox.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
      }
    });
    GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
    gbc_chckbxNewCheckBox.anchor = GridBagConstraints.NORTH;
    gbc_chckbxNewCheckBox.fill = GridBagConstraints.BOTH;
    gbc_chckbxNewCheckBox.gridx = 0;
    gbc_chckbxNewCheckBox.gridy = 0;
    add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
    
    LoopNrTF = new JTextField();
    GridBagConstraints gbc_textField = new GridBagConstraints();
    gbc_textField.anchor = GridBagConstraints.NORTH;
    gbc_textField.fill = GridBagConstraints.BOTH;
    gbc_textField.gridx = 1;
    gbc_textField.gridy = 0;
    add(LoopNrTF, gbc_textField);
    LoopNrTF.setColumns(10);
    

  }
  
  // private attributes
  VehicleButton vehicle_button;

}

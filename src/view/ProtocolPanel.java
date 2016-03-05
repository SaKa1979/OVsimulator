package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import view.VehicleSettingPanel.TextFieldListener;

import javax.swing.JCheckBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ButtonGroup;

public class ProtocolPanel extends JPanel {

  /**
   * Constructor.
   */
  public ProtocolPanel() {
    setToolTipText("Here you can select a protocol and enter the protocols specific settings");
    setBorder(new TitledBorder(null, "Protocol", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    this.setSize(320, 320);
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] {50, 250};
    gridBagLayout.rowHeights = new int[] {100, 100, 100};
    gridBagLayout.columnWeights = new double[]{0.0};
    gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
    setLayout(gridBagLayout);

    initialize();
    loadAllFieldValues();
  }

  // PUBLIC METHODS
  // PRIVATE METHODS
  private void initialize(){
    buttonGroup = new ButtonGroup();
    JCheckBox chckbxKAR = new JCheckBox("KAR");
    buttonGroup.add(chckbxKAR);
    GridBagConstraints gbc_chckbxKAR = new GridBagConstraints();
    gbc_chckbxKAR.insets = new Insets(0, 10, 0, 0);
    gbc_chckbxKAR.fill = GridBagConstraints.BOTH;
    gbc_chckbxKAR.gridx = 0;
    gbc_chckbxKAR.gridy = 0;
    add(chckbxKAR, gbc_chckbxKAR);

    chckbxVecom = new JCheckBox("VECOM");
    buttonGroup.add(chckbxVecom);
    GridBagConstraints gbc_chckbxVecom = new GridBagConstraints();
    gbc_chckbxVecom.insets = new Insets(0, 10, 0, 0);
    gbc_chckbxVecom.fill = GridBagConstraints.BOTH;
    gbc_chckbxVecom.gridx = 0;
    gbc_chckbxVecom.gridy = 1;
    add(chckbxVecom, gbc_chckbxVecom);

    chckbxSics = new JCheckBox("SICS");
    buttonGroup.add(chckbxSics);
    GridBagConstraints gbc_chckbxSics = new GridBagConstraints();
    gbc_chckbxSics.fill = GridBagConstraints.BOTH;
    gbc_chckbxSics.insets = new Insets(0, 10, 0, 0);
    gbc_chckbxSics.gridx = 0;
    gbc_chckbxSics.gridy = 2;
    add(chckbxSics, gbc_chckbxSics);
    
    sidTF = new JTextField();
    sidTF.setEnabled(false);
    sidTF.setName(sidTF_name);
    sidTF.setText("SID");
//    sidTF.addActionListener(new TextFieldListener());
    GridBagConstraints gbc_sidTF = new GridBagConstraints();
    gbc_sidTF.anchor = GridBagConstraints.NORTH;
    gbc_sidTF.fill = GridBagConstraints.BOTH;
    gbc_sidTF.gridx = 1;
    gbc_sidTF.gridy = 0;
    add(sidTF, gbc_sidTF);
    
  }
  
  private void loadAllFieldValues(){
    // TODO
  }

  //INNER CLASSES
  // ENUMS
  // PRIVATE ATTRIBUTES
  private JCheckBox chckbxKAR;
  private JCheckBox chckbxVecom;
  private JCheckBox chckbxSics;
  private ButtonGroup buttonGroup;
  private JTextField sidTF;
  
  private String sidTF_name = "sid";
} //end of class ProtocolPanel

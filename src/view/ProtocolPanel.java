package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;

public class ProtocolPanel extends JPanel {

  private static final long serialVersionUID = 1L;

  /**
   * Constructor.
   */
  public ProtocolPanel() {
    setToolTipText("Here you can select a protocol and enter the protocols specific settings");
    setBorder(new TitledBorder(null, "Protocol", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] {50, 250};
    gridBagLayout.columnWeights = new double[]{0.0};
    gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
    setLayout(gridBagLayout);

    initialize();
    loadAllFieldValues();
  }

  // PUBLIC METHODS
  /**
   * When Panel OK button is pressed all user input is read (again)
   * This allows the user to fill in all the fields without confirming each field individually
   */
  public void handleOK(){
	  loadAllFieldValues();
  }

  public Proto getSelectedProto() {
	  return selectedProto;
  }

  public int getKar_sid() {
	  return kar_sid;
  }

  // PRIVATE METHODS
  private void initialize(){
    fieldsList = new ArrayList<JTextField>();
    buttonGroup = new ButtonGroup();
    
    // KAR
    JCheckBox karCB = new JCheckBox("KAR");
    karCB.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED){
          sidTF.setEnabled(true);
          selectedProto = Proto.KAR;
        }
        else {
          sidTF.setEnabled(false);
        }
      }
    });
    buttonGroup.add(karCB);
    GridBagConstraints gbc_chckbxKAR = new GridBagConstraints();
    gbc_chckbxKAR.anchor = GridBagConstraints.NORTH;
    gbc_chckbxKAR.insets = new Insets(0, 10, 0, 0);
    gbc_chckbxKAR.fill = GridBagConstraints.HORIZONTAL;
    gbc_chckbxKAR.gridx = 0;
    gbc_chckbxKAR.gridy = 0;
    add(karCB, gbc_chckbxKAR);
    
    sidTF = new JTextField();
    sidTF.setDocument(new LengthRestrictedDocument(4));
    sidTF.setEnabled(false);
    sidTF.setName(sidTF_name);
    sidTF.setText("0000");
    sidTF.addActionListener(textFieldListener);
    GridBagConstraints gbc_sidTF = new GridBagConstraints();
    gbc_sidTF.anchor = GridBagConstraints.NORTH;
    gbc_sidTF.fill = GridBagConstraints.BOTH;
    gbc_sidTF.gridx = 1;
    gbc_sidTF.gridy = 0;
    add(sidTF, gbc_sidTF);
    fieldsList.add(sidTF);

    // VECOM
    vecomCB = new JCheckBox("VECOM");
    vecomCB.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED){
          selectedProto = Proto.VECOM;
        }
        else {
          // do nothing
        }
      }
    });
    buttonGroup.add(vecomCB);
    GridBagConstraints gbc_chckbxVecom = new GridBagConstraints();
    gbc_chckbxVecom.anchor = GridBagConstraints.NORTH;
    gbc_chckbxVecom.insets = new Insets(0, 10, 0, 0);
    gbc_chckbxVecom.fill = GridBagConstraints.HORIZONTAL;
    gbc_chckbxVecom.gridx = 0;
    gbc_chckbxVecom.gridy = 1;
    add(vecomCB, gbc_chckbxVecom);

    // SICS
    sicsCB = new JCheckBox("SICS");
    vecomCB.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED){
          selectedProto = Proto.SICS;
        }
        else {
          // do nothing
        }
      }
    });
    sicsCB.setEnabled(false); // TODO future
    buttonGroup.add(sicsCB);
    GridBagConstraints gbc_chckbxSics = new GridBagConstraints();
    gbc_chckbxSics.anchor = GridBagConstraints.NORTH;
    gbc_chckbxSics.fill = GridBagConstraints.HORIZONTAL;
    gbc_chckbxSics.insets = new Insets(0, 10, 0, 0);
    gbc_chckbxSics.gridx = 0;
    gbc_chckbxSics.gridy = 2;
    add(sicsCB, gbc_chckbxSics);
   
  }
  /**
   * Loads the values entered in all the fields. 
   */
  private void loadAllFieldValues(){
    for(JTextField tf : fieldsList){
      readTextField(tf);
    }
  }
  
  /**
   * @brief Reads the value entered into the field and copies it to the same named attribute
   *        The target attribute is decided by textfield name.
   * @throws NumberFormatException
   * @param tf JTextField
   */
  private void readTextField(JTextField tf){
    int number = 0;
    if (!tf.isEnabled()) 
      return;
    try{
      number = Integer.parseInt(tf.getText());
    }catch(NumberFormatException nfe){
      JOptionPane.showMessageDialog(null, nfe.toString(), "Input error", JOptionPane.ERROR_MESSAGE);
      number = 0;
      tf.setText("0");
    }
    finally{
      if (tf.getName().equals(sidTF_name))
        kar_sid = number;
    }
  }
  
  // LISTENERS
  ActionListener textFieldListener = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      JTextField tf = (JTextField)e.getSource();
      readTextField(tf);
    }
  };
  
  //INNER CLASSES
  
  // ENUMS
  public enum Proto {KAR, VECOM, SICS, NONE};
  
  // PRIVATE ATTRIBUTES
  private JCheckBox chckbxKAR;
  private JCheckBox vecomCB;
  private JCheckBox sicsCB;
  private ButtonGroup buttonGroup;
  private JTextField sidTF;
  
  private String sidTF_name = "sid";
  private ArrayList<JTextField> fieldsList; // holds all textfields
  private Proto selectedProto = Proto.NONE;


private int kar_sid;

} //end of class ProtocolPanel

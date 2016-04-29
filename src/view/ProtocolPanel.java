package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class ProtocolPanel extends JPanel {

  private static final long serialVersionUID = 5L;

  /**
   * Constructor.
   */
  public ProtocolPanel() {
    setToolTipText("Here you can select a protocol and enter the protocols specific settings");
    setBorder(new TitledBorder(null, "Protocol", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] {72, 154, 200};
    gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0};
    gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    setLayout(gridBagLayout);
    vcuAddress = 0;
    kar_sid = 0;

    initialize();
    loadAllFieldValues();
  }

  // PUBLIC METHODS
  /**
   * When Panel OK button is pressed all user input is read (again)
   * This allows the user to fill in all the fields without confirming each field individualy
   */
  public void handleOK(){
    loadAllFieldValues();
  }

  public Proto getSelectedProto() {
    return selectedProto;
  }
  public void setSelectedProto(Proto proto){
    selectedProto = proto;
  }

  public int getKar_sid() {
    return kar_sid;
  }

  public byte getVCU_address() {
    return vcuAddress;
  }
  
  public NumericField getSidTF() {
    return sidTF;
  }

  public NumericField getVcuTF() {
    return vcuTF;
  }

  public JTextField getKeyTF() {
    return keyTF;
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
          keyTF.setEnabled(true);
          selectedProto = Proto.KAR;
        }
        else {
          sidTF.setEnabled(false);
          keyTF.setEnabled(false);
        }
      }
    });
    buttonGroup.add(karCB);
    GridBagConstraints gbc_chckbxKAR = new GridBagConstraints();
    gbc_chckbxKAR.insets = new Insets(0, 0, 5, 5);
    gbc_chckbxKAR.anchor = GridBagConstraints.NORTH;
    gbc_chckbxKAR.fill = GridBagConstraints.HORIZONTAL;
    gbc_chckbxKAR.gridx = 0;
    gbc_chckbxKAR.gridy = 0;
    add(karCB, gbc_chckbxKAR);

    sidTF = new NumericField(4, NumericField.DECIMAL);
    sidTF.setAllowNegative(false);
    sidTF.setToolTipText("Identification number");
    sidTF.setEnabled(false);
    sidTF.setName(sidTF_name);
    sidTF.setText("0000");
    sidTF.addActionListener(textFieldListener);
    GridBagConstraints gbc_sidTF = new GridBagConstraints();
    gbc_sidTF.insets = new Insets(0, 0, 5, 5);
    gbc_sidTF.anchor = GridBagConstraints.NORTH;
    gbc_sidTF.fill = GridBagConstraints.BOTH;
    gbc_sidTF.gridx = 1;
    gbc_sidTF.gridy = 0;
    add(sidTF, gbc_sidTF);
    fieldsList.add(sidTF);
    
    lblSystemIdentificationNumber = new JLabel("System Identification Number");
    GridBagConstraints gbc_lblSystemIdentificationNumber = new GridBagConstraints();
    gbc_lblSystemIdentificationNumber.insets = new Insets(0, 5, 5, 0);
    gbc_lblSystemIdentificationNumber.anchor = GridBagConstraints.WEST;
    gbc_lblSystemIdentificationNumber.gridx = 2;
    gbc_lblSystemIdentificationNumber.gridy = 0;
    add(lblSystemIdentificationNumber, gbc_lblSystemIdentificationNumber);
    
    keyTF = new NumericField();
    keyTF.setEnabled(false);
    keyTF.setToolTipText("Enter the secret key");
    keyTF.setText("0");
    GridBagConstraints gbc_keyTF = new GridBagConstraints();
    gbc_keyTF.insets = new Insets(0, 0, 5, 5);
    gbc_keyTF.fill = GridBagConstraints.HORIZONTAL;
    gbc_keyTF.gridx = 1;
    gbc_keyTF.gridy = 1;
    add(keyTF, gbc_keyTF);
    keyTF.setColumns(10);
    
    lblKey = new JLabel("Key");
    lblKey.setHorizontalAlignment(SwingConstants.LEFT);
    GridBagConstraints gbc_lblKey = new GridBagConstraints();
    gbc_lblKey.anchor = GridBagConstraints.WEST;
    gbc_lblKey.insets = new Insets(0, 5, 5, 0);
    gbc_lblKey.gridx = 2;
    gbc_lblKey.gridy = 1;
    add(lblKey, gbc_lblKey);
    
    // VECOM
    vecomCB = new JCheckBox("VECOM");
    vecomCB.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED){
          selectedProto = Proto.VECOM;
          vcuTF.setEnabled(true);
        }
        else {
          vcuTF.setEnabled(false);
        }
      }
    });
    
    vcuTF = new NumericField(1, NumericField.DECIMAL);
    vcuTF.setAllowNegative(false);
    vcuTF.setToolTipText("VCU number 1-9");
    vcuTF.setEnabled(false);
    vcuTF.setName(vcuTF_name);
    vcuTF.setText("1");
    vcuTF.addActionListener(textFieldListener);
    
    separator = new JSeparator();
    GridBagConstraints gbc_separator = new GridBagConstraints();
    gbc_separator.gridwidth = 3;
    gbc_separator.insets = new Insets(0, 0, 5, 0);
    gbc_separator.gridx = 0;
    gbc_separator.gridy = 2;
    add(separator, gbc_separator);
    GridBagConstraints gbc_vcuTF = new GridBagConstraints();
    gbc_vcuTF.insets = new Insets(0, 0, 5, 5);
    gbc_vcuTF.anchor = GridBagConstraints.NORTH;
    gbc_vcuTF.fill = GridBagConstraints.BOTH;
    gbc_vcuTF.gridx = 1;
    gbc_vcuTF.gridy = 3;
    add(vcuTF, gbc_vcuTF);
    fieldsList.add(vcuTF);
    
    buttonGroup.add(vecomCB);
    GridBagConstraints gbc_chckbxVecom = new GridBagConstraints();
    gbc_chckbxVecom.insets = new Insets(0, 0, 5, 5);
    gbc_chckbxVecom.anchor = GridBagConstraints.NORTH;
    gbc_chckbxVecom.fill = GridBagConstraints.HORIZONTAL;
    gbc_chckbxVecom.gridx = 0;
    gbc_chckbxVecom.gridy = 3;
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
    
    lblVcuNumber = new JLabel("VCU number 1 to 8");
    GridBagConstraints gbc_lblVcuNumber = new GridBagConstraints();
    gbc_lblVcuNumber.insets = new Insets(0, 5, 5, 0);
    gbc_lblVcuNumber.anchor = GridBagConstraints.WEST;
    gbc_lblVcuNumber.gridx = 2;
    gbc_lblVcuNumber.gridy = 3;
    add(lblVcuNumber, gbc_lblVcuNumber);
    
    separator_1 = new JSeparator();
    GridBagConstraints gbc_separator_1 = new GridBagConstraints();
    gbc_separator_1.gridwidth = 3;
    gbc_separator_1.insets = new Insets(0, 0, 5, 5);
    gbc_separator_1.gridx = 0;
    gbc_separator_1.gridy = 4;
    add(separator_1, gbc_separator_1);
    sicsCB.setEnabled(false); // TODO future
    buttonGroup.add(sicsCB);
    GridBagConstraints gbc_chckbxSics = new GridBagConstraints();
    gbc_chckbxSics.insets = new Insets(0, 0, 0, 5);
    gbc_chckbxSics.anchor = GridBagConstraints.NORTH;
    gbc_chckbxSics.fill = GridBagConstraints.HORIZONTAL;
    gbc_chckbxSics.gridx = 0;
    gbc_chckbxSics.gridy = 5;
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
      else if (tf.getName().equals(vcuTF_name))
        number %= 15;
      if (number >= 0){
        byte numberHigh = (byte) ~(number << 4);
        byte numberLow = (byte) number;
        vcuAddress = (byte) (numberHigh + numberLow );
      }
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
  private NumericField sidTF;
  private NumericField keyTF;
  private NumericField vcuTF;


  private String sidTF_name = "sid";
  private String vcuTF_name = "vcu_address";
  private ArrayList<JTextField> fieldsList; // holds all textfields
  private Proto selectedProto = Proto.NONE;
  
  private int kar_sid;
  private byte vcuAddress;
  private JLabel lblSystemIdentificationNumber;
  private JLabel lblVcuNumber;
  private JLabel lblKey;
  private JSeparator separator;
  private JSeparator separator_1;

} //end of class ProtocolPanel

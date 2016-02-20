package view;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VehicleSettingPanel extends JPanel {

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
    initialize();
    loadValues();
  }

// PUBLIC METHODS
  /**
   * When Panel OK button is pressed all user input is read (again)
   * This allows the user to fill in all the fields without confirming each field individually
   */
  public void handleOK(){
	  readField(loopNrTF);
  }
  // PRIVATE METHODS
  private void initialize(){
	  fieldsList = new ArrayList<JTextField>();
	  
	  // loop number
	  loopNrCB = new JCheckBox("Loop nr");
	  loopNrCB.addItemListener(new ItemListener() {
		  public void itemStateChanged(ItemEvent e) {
			  if (e.getStateChange() == ItemEvent.SELECTED){
				  loopNrTF.setEnabled(true);
			  }
			  else {
				  loopNrTF.setEnabled(false);
			  }
		  }
	  });
	  GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
	  gbc_chckbxNewCheckBox.anchor = GridBagConstraints.NORTH;
	  gbc_chckbxNewCheckBox.fill = GridBagConstraints.BOTH;
	  gbc_chckbxNewCheckBox.gridx = 0;
	  gbc_chckbxNewCheckBox.gridy = 0;
	  add(loopNrCB, gbc_chckbxNewCheckBox);

	  loopNrTF = new JTextField();
	  loopNrTF.setEnabled(false);
	  loopNrTF.setName(loopNr_name);
	  loopNrTF.setText("0");
	  loopNrTF.addActionListener(new VehSettingListener());
	  GridBagConstraints gbc_textField = new GridBagConstraints();
	  gbc_textField.anchor = GridBagConstraints.NORTH;
	  gbc_textField.fill = GridBagConstraints.BOTH;
	  gbc_textField.gridx = 1;
	  gbc_textField.gridy = 0;
	  add(loopNrTF, gbc_textField);
	  fieldsList.add(loopNrTF);
	  
  } 
  private void loadValues() {
	  for(JTextField tf : fieldsList){
		  readField(tf);
	  }		  
  }
  
  /**
   * @brief Reads the value entered into the field and copies it to the named VehicleButton attribute
   * @throws NumberFormatException
   * @param tf
   */
  private void readField(JTextField tf){
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
		  if (tf.getName().equals(loopNr_name))
		  vehicle_button.setLoopNr(number);
	  }
  }
  

  // PRIVATE ATTRIBUTES
  private VehicleButton vehicle_button;
  private JCheckBox loopNrCB;
  private JTextField loopNrTF;
  private ArrayList<JTextField> fieldsList;
  private String loopNr_name = "loopnr";
  
  // INNER CLASSES
  public class VehSettingListener implements ActionListener{

	  @Override
	  public void actionPerformed(ActionEvent e) {
		  JTextField tf = (JTextField)e.getSource();
			  readField(tf);
			  tf.setFocusable(false);
	  }

  } //end of inner class VehSettingListener

}// end of class




package view;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import view.VehicleButton.VehicleTypes;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
    loadTextFieldValues();
  }

// PUBLIC METHODS
  /**
   * When Panel OK button is pressed all user input is read (again)
   * This allows the user to fill in all the fields without confirming each field individually
   */
  public void handleOK(){
	  readTextField(loopNrTF);
	  readTextField(signalGroupNrTF);
	  readTextField(lineNrTF);
	  readTextField(wagonNrTF);
	  readTextField(vehicleIdTF);
	  readTextField(vehicleLengthTF);
	  readTextField(vehicleSpeedTF);
	  readTextField(distanceToStopTF);
	  readTextField(timeToStopTF);
	  readTextField(punctualityTF);
	  
	  readComBoBox(directionComBox);
	  readComBoBox(commandComBox);
	  readComBoBox(vehicleTypeComBox);
	  readComBoBox(vehicleStatusComBox);
	  readComBoBox(priorityClassComBox);
	  readComBoBox(punctualityClassComBox);

  }
  // PRIVATE METHODS
  private void initialize(){
	  fieldsList = new ArrayList<JTextField>();
	  comboBoxList = new ArrayList<JComboBox<String>>();
	  
	  // loop number on y=0
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
	  GridBagConstraints gbc_loopNrCB = new GridBagConstraints();
	  gbc_loopNrCB.anchor = GridBagConstraints.NORTH;
	  gbc_loopNrCB.fill = GridBagConstraints.BOTH;
	  gbc_loopNrCB.gridx = 0;
	  gbc_loopNrCB.gridy = 0;
	  add(loopNrCB, gbc_loopNrCB);

	  loopNrTF = new JTextField();
	  loopNrTF.setEnabled(false);
	  loopNrTF.setName(loopNrTF_name);
	  loopNrTF.setText("0");
	  loopNrTF.addActionListener(new TextFieldListener());
	  GridBagConstraints gbc_loopNrTF = new GridBagConstraints();
	  gbc_loopNrTF.anchor = GridBagConstraints.NORTH;
	  gbc_loopNrTF.fill = GridBagConstraints.BOTH;
	  gbc_loopNrTF.gridx = 1;
	  gbc_loopNrTF.gridy = 0;
	  add(loopNrTF, gbc_loopNrTF);
	  fieldsList.add(loopNrTF);
	  
	  // loop signalGroupNrCB on y=1
	  signalGroupNrCB = new JCheckBox("Signalgroup");
	  signalGroupNrCB.addItemListener(new ItemListener() {
		  public void itemStateChanged(ItemEvent e) {
			  if (e.getStateChange() == ItemEvent.SELECTED){
				  signalGroupNrTF.setEnabled(true);
			  }
			  else {
				  signalGroupNrTF.setEnabled(false);
			  }
		  }
	  });
	  GridBagConstraints gbc_signalGroupNrCB = new GridBagConstraints();
	  gbc_signalGroupNrCB.anchor = GridBagConstraints.NORTH;
	  gbc_signalGroupNrCB.fill = GridBagConstraints.BOTH;
	  gbc_signalGroupNrCB.gridx = 0;
	  gbc_signalGroupNrCB.gridy = 1;
	  add(signalGroupNrCB, gbc_signalGroupNrCB);

	  signalGroupNrTF = new JTextField();
	  signalGroupNrTF.setEnabled(false);
	  signalGroupNrTF.setName(loopNrTF_name);
	  signalGroupNrTF.setText("0");
	  signalGroupNrTF.addActionListener(new TextFieldListener());
	  GridBagConstraints gbc_signalGroupNrTF = new GridBagConstraints();
	  gbc_signalGroupNrTF.anchor = GridBagConstraints.NORTH;
	  gbc_signalGroupNrTF.fill = GridBagConstraints.BOTH;
	  gbc_signalGroupNrTF.gridx = 1;
	  gbc_signalGroupNrTF.gridy = 1;
	  add(signalGroupNrTF, gbc_signalGroupNrTF);
	  fieldsList.add(signalGroupNrTF);
	
	  // direction on y=2
	  directionCB = new JCheckBox("Direction");
	  directionCB.addItemListener(new ItemListener() {
		  public void itemStateChanged(ItemEvent e) {
			  if (e.getStateChange() == ItemEvent.SELECTED){
				  directionComBox.setEnabled(true);
			  }
			  else {
				  directionComBox.setEnabled(false);
			  }
		  }
	  });
	  GridBagConstraints gbc_directionCB = new GridBagConstraints();
	  gbc_directionCB.anchor = GridBagConstraints.NORTH;
	  gbc_directionCB.fill = GridBagConstraints.BOTH;
	  gbc_directionCB.gridx = 0;
	  gbc_directionCB.gridy = 2;
	  add(directionCB, gbc_directionCB);
	  
	  directionArray = vehicle_button.getAllDirections_array();
	  directionComBox = new JComboBox<String>(directionArray);
	  directionComBox.addActionListener(new ComBoListener());
	  directionComBox.setEnabled(false);
	  directionComBox.setName(directionComBox_name);
	  directionComBox.setSelectedIndex(0);
	  directionComBox.addActionListener(new ComBoListener());
	  GridBagConstraints gbc_directionComBox = new GridBagConstraints();
	  gbc_directionComBox.anchor = GridBagConstraints.NORTH;
	  gbc_directionComBox.fill = GridBagConstraints.BOTH;
	  gbc_directionComBox.gridx = 1;
	  gbc_directionComBox.gridy = 2;
	  add(directionComBox, gbc_directionComBox);
	  comboBoxList.add(directionComBox);
	  
	  // command on y=3
	  commandCB = new JCheckBox("Command");
	  commandCB.addItemListener(new ItemListener() {
		  public void itemStateChanged(ItemEvent e) {
			  if (e.getStateChange() == ItemEvent.SELECTED){
				  commandComBox.setEnabled(true);
			  }
			  else {
				  commandComBox.setEnabled(false);
			  }
		  }
	  });
	  GridBagConstraints gbc_commandCB = new GridBagConstraints();
	  gbc_commandCB.anchor = GridBagConstraints.NORTH;
	  gbc_commandCB.fill = GridBagConstraints.BOTH;
	  gbc_commandCB.gridx = 0;
	  gbc_commandCB.gridy = 3;
	  add(commandCB, gbc_commandCB);
	  
	  commandArray = vehicle_button.getAllCommands_array();
	  commandComBox = new JComboBox<String>(commandArray);
	  commandComBox.addActionListener(new ComBoListener());
	  commandComBox.setEnabled(false);
	  commandComBox.setName(commandComBox_name);
	  commandComBox.setSelectedIndex(0);
	  commandComBox.addActionListener(new ComBoListener());
	  GridBagConstraints gbc_commandComBox = new GridBagConstraints();
	  gbc_commandComBox.anchor = GridBagConstraints.NORTH;
	  gbc_commandComBox.fill = GridBagConstraints.BOTH;
	  gbc_commandComBox.gridx = 1;
	  gbc_commandComBox.gridy = 3;
	  add(commandComBox, gbc_commandComBox);
	  comboBoxList.add(commandComBox);
	  
	  // vehicle type on y=4
	  vehicleTypeCB = new JCheckBox("Vehicle type");
	  vehicleTypeCB.addItemListener(new ItemListener() {
		  public void itemStateChanged(ItemEvent e) {
			  if (e.getStateChange() == ItemEvent.SELECTED){
				  vehicleTypeComBox.setEnabled(true);
			  }
			  else {
				  vehicleTypeComBox.setEnabled(false);
			  }
		  }
	  });
	  GridBagConstraints gbc_vehicleTypeCB = new GridBagConstraints();
	  gbc_vehicleTypeCB.anchor = GridBagConstraints.NORTH;
	  gbc_vehicleTypeCB.fill = GridBagConstraints.BOTH;
	  gbc_vehicleTypeCB.gridx = 0;
	  gbc_vehicleTypeCB.gridy = 4;
	  add(vehicleTypeCB, gbc_vehicleTypeCB);
	  
	  vehicleTypesArray = vehicle_button.getAllVehicleTypes_array();
	  vehicleTypeComBox = new JComboBox<String>(vehicleTypesArray);
	  vehicleTypeComBox.addActionListener(new ComBoListener());
	  vehicleTypeComBox.setEnabled(false);
	  vehicleTypeComBox.setName(vehicleTypeComBox_name);
	  vehicleTypeComBox.setSelectedIndex(0);
	  vehicleTypeComBox.addActionListener(new ComBoListener());
	  GridBagConstraints gbc_vehicleTypeComBox = new GridBagConstraints();
	  gbc_vehicleTypeComBox.anchor = GridBagConstraints.NORTH;
	  gbc_vehicleTypeComBox.fill = GridBagConstraints.BOTH;
	  gbc_vehicleTypeComBox.gridx = 1;
	  gbc_vehicleTypeComBox.gridy = 4;
	  add(vehicleTypeComBox, gbc_vehicleTypeComBox);
	  comboBoxList.add(vehicleTypeComBox);
	  
	  // line number on y=5
	  lineNrCB = new JCheckBox("Line nr");
	  lineNrCB.addItemListener(new ItemListener() {
		  public void itemStateChanged(ItemEvent e) {
			  if (e.getStateChange() == ItemEvent.SELECTED){
				  lineNrTF.setEnabled(true);
			  }
			  else {
				  lineNrTF.setEnabled(false);
			  }
		  }
	  });
	  GridBagConstraints gbc_lineNrCB = new GridBagConstraints();
	  gbc_lineNrCB.anchor = GridBagConstraints.NORTH;
	  gbc_lineNrCB.fill = GridBagConstraints.BOTH;
	  gbc_lineNrCB.gridx = 0;
	  gbc_lineNrCB.gridy = 5;
	  add(lineNrCB, gbc_lineNrCB);

	  lineNrTF = new JTextField();
	  lineNrTF.setEnabled(false);
	  lineNrTF.setName(lineNrTF_name);
	  lineNrTF.setText("0");
	  lineNrTF.addActionListener(new TextFieldListener());
	  GridBagConstraints gbc_lineNrTF = new GridBagConstraints();
	  gbc_lineNrTF.anchor = GridBagConstraints.NORTH;
	  gbc_lineNrTF.fill = GridBagConstraints.BOTH;
	  gbc_lineNrTF.gridx = 1;
	  gbc_lineNrTF.gridy = 5;
	  add(lineNrTF, gbc_lineNrTF);
	  fieldsList.add(lineNrTF);
	  
	  // wagon number on y=6
	  wagonNrCB = new JCheckBox("Wagon nr");
	  wagonNrCB.addItemListener(new ItemListener() {
		  public void itemStateChanged(ItemEvent e) {
			  if (e.getStateChange() == ItemEvent.SELECTED){
				  wagonNrTF.setEnabled(true);
			  }
			  else {
				  wagonNrTF.setEnabled(false);
			  }
		  }
	  });
	  GridBagConstraints gbc_wagonNrCB = new GridBagConstraints();
	  gbc_wagonNrCB.anchor = GridBagConstraints.NORTH;
	  gbc_wagonNrCB.fill = GridBagConstraints.BOTH;
	  gbc_wagonNrCB.gridx = 0;
	  gbc_wagonNrCB.gridy = 6;
	  add(wagonNrCB, gbc_wagonNrCB);

	  wagonNrTF = new JTextField();
	  wagonNrTF.setEnabled(false);
	  wagonNrTF.setName(wagonNrTF_name);
	  wagonNrTF.setText("0");
	  wagonNrTF.addActionListener(new TextFieldListener());
	  GridBagConstraints gbc_wagonNrTF = new GridBagConstraints();
	  gbc_wagonNrTF.anchor = GridBagConstraints.NORTH;
	  gbc_wagonNrTF.fill = GridBagConstraints.BOTH;
	  gbc_wagonNrTF.gridx = 1;
	  gbc_wagonNrTF.gridy = 6;
	  add(wagonNrTF, gbc_wagonNrTF);
	  fieldsList.add(wagonNrTF);
	  
	  // vehicle ID on y=7
	  vehicleIdCB = new JCheckBox("Vehicle ID nr");
	  vehicleIdCB.addItemListener(new ItemListener() {
		  public void itemStateChanged(ItemEvent e) {
			  if (e.getStateChange() == ItemEvent.SELECTED){
				  vehicleIdTF.setEnabled(true);
			  }
			  else {
				  vehicleIdTF.setEnabled(false);
			  }
		  }
	  });
	  GridBagConstraints gbc_vehicleIdCB = new GridBagConstraints();
	  gbc_vehicleIdCB.anchor = GridBagConstraints.NORTH;
	  gbc_vehicleIdCB.fill = GridBagConstraints.BOTH;
	  gbc_vehicleIdCB.gridx = 0;
	  gbc_vehicleIdCB.gridy = 7;
	  add(vehicleIdCB, gbc_vehicleIdCB);

	  vehicleIdTF = new JTextField();
	  vehicleIdTF.setEnabled(false);
	  vehicleIdTF.setName(vehicleIdTF_name);
	  vehicleIdTF.setText("0");
	  vehicleIdTF.addActionListener(new TextFieldListener());
	  GridBagConstraints gbc_vehicleIdTF = new GridBagConstraints();
	  gbc_vehicleIdTF.anchor = GridBagConstraints.NORTH;
	  gbc_vehicleIdTF.fill = GridBagConstraints.BOTH;
	  gbc_vehicleIdTF.gridx = 1;
	  gbc_vehicleIdTF.gridy = 7;
	  add(vehicleIdTF, gbc_vehicleIdTF);
	  fieldsList.add(vehicleIdTF);
	  
	  // vehicle length on y=8
	  vehicleLengthCB = new JCheckBox("Vehicle length");
	  vehicleLengthCB.addItemListener(new ItemListener() {
		  public void itemStateChanged(ItemEvent e) {
			  if (e.getStateChange() == ItemEvent.SELECTED){
				  vehicleLengthTF.setEnabled(true);
			  }
			  else {
				  vehicleLengthTF.setEnabled(false);
			  }
		  }
	  });
	  GridBagConstraints gbc_vehicleLengthCB = new GridBagConstraints();
	  gbc_vehicleLengthCB.anchor = GridBagConstraints.NORTH;
	  gbc_vehicleLengthCB.fill = GridBagConstraints.BOTH;
	  gbc_vehicleLengthCB.gridx = 0;
	  gbc_vehicleLengthCB.gridy = 8;
	  add(vehicleLengthCB, gbc_vehicleLengthCB);

	  vehicleLengthTF = new JTextField();
	  vehicleLengthTF.setEnabled(false);
	  vehicleLengthTF.setName(vehicleLengthTF_name);
	  vehicleLengthTF.setText("0");
	  vehicleLengthTF.addActionListener(new TextFieldListener());
	  GridBagConstraints gbc_vehicleLengthTF = new GridBagConstraints();
	  gbc_vehicleLengthTF.anchor = GridBagConstraints.NORTH;
	  gbc_vehicleLengthTF.fill = GridBagConstraints.BOTH;
	  gbc_vehicleLengthTF.gridx = 1;
	  gbc_vehicleLengthTF.gridy = 8;
	  add(vehicleLengthTF, gbc_vehicleLengthTF);
	  fieldsList.add(vehicleLengthTF);
	  
	  // vehicle speed on y=9
	  vehicleSpeedCB = new JCheckBox("Vehicle speed");
	  vehicleSpeedCB.addItemListener(new ItemListener() {
		  public void itemStateChanged(ItemEvent e) {
			  if (e.getStateChange() == ItemEvent.SELECTED){
				  vehicleSpeedTF.setEnabled(true);
			  }
			  else {
				  vehicleSpeedTF.setEnabled(false);
			  }
		  }
	  });
	  GridBagConstraints gbc_vehicleSpeedCB = new GridBagConstraints();
	  gbc_vehicleSpeedCB.anchor = GridBagConstraints.NORTH;
	  gbc_vehicleSpeedCB.fill = GridBagConstraints.BOTH;
	  gbc_vehicleSpeedCB.gridx = 0;
	  gbc_vehicleSpeedCB.gridy = 9;
	  add(vehicleSpeedCB, gbc_vehicleSpeedCB);

	  vehicleSpeedTF = new JTextField();
	  vehicleSpeedTF.setEnabled(false);
	  vehicleSpeedTF.setName(vehicleSpeedTF_name);
	  vehicleSpeedTF.setText("0");
	  vehicleSpeedTF.addActionListener(new TextFieldListener());
	  GridBagConstraints gbc_vehicleSpeedTF = new GridBagConstraints();
	  gbc_vehicleSpeedTF.anchor = GridBagConstraints.NORTH;
	  gbc_vehicleSpeedTF.fill = GridBagConstraints.BOTH;
	  gbc_vehicleSpeedTF.gridx = 1;
	  gbc_vehicleSpeedTF.gridy = 9;
	  add(vehicleSpeedTF, gbc_vehicleSpeedTF);
	  fieldsList.add(vehicleSpeedTF);
	  
	  // distance to stop on y=10
	  distanceToStopCB = new JCheckBox("Distance to stop mark");
	  distanceToStopCB.addItemListener(new ItemListener() {
		  public void itemStateChanged(ItemEvent e) {
			  if (e.getStateChange() == ItemEvent.SELECTED){
				  distanceToStopTF.setEnabled(true);
			  }
			  else {
				  distanceToStopTF.setEnabled(false);
			  }
		  }
	  });
	  GridBagConstraints gbc_distanceToStopCB = new GridBagConstraints();
	  gbc_distanceToStopCB.anchor = GridBagConstraints.NORTH;
	  gbc_distanceToStopCB.fill = GridBagConstraints.BOTH;
	  gbc_distanceToStopCB.gridx = 0;
	  gbc_distanceToStopCB.gridy = 10;
	  add(distanceToStopCB, gbc_distanceToStopCB);

	  distanceToStopTF = new JTextField();
	  distanceToStopTF.setEnabled(false);
	  distanceToStopTF.setName(distanceToStopTF_name);
	  distanceToStopTF.setText("0");
	  distanceToStopTF.addActionListener(new TextFieldListener());
	  GridBagConstraints gbc_distanceToStopTF = new GridBagConstraints();
	  gbc_distanceToStopTF.anchor = GridBagConstraints.NORTH;
	  gbc_distanceToStopTF.fill = GridBagConstraints.BOTH;
	  gbc_distanceToStopTF.gridx = 1;
	  gbc_distanceToStopTF.gridy = 10;
	  add(distanceToStopTF, gbc_distanceToStopTF);
	  fieldsList.add(distanceToStopTF);
	  
	  // time to stop on y=11
	  timeToStopCB = new JCheckBox("Time till stop mark");
	  timeToStopCB.addItemListener(new ItemListener() {
		  public void itemStateChanged(ItemEvent e) {
			  if (e.getStateChange() == ItemEvent.SELECTED){
				  timeToStopTF.setEnabled(true);
			  }
			  else {
				  timeToStopTF.setEnabled(false);
			  }
		  }
	  });
	  GridBagConstraints gbc_timeToStopCB = new GridBagConstraints();
	  gbc_timeToStopCB.anchor = GridBagConstraints.NORTH;
	  gbc_timeToStopCB.fill = GridBagConstraints.BOTH;
	  gbc_timeToStopCB.gridx = 0;
	  gbc_timeToStopCB.gridy = 11;
	  add(timeToStopCB, gbc_timeToStopCB);

	  timeToStopTF = new JTextField();
	  timeToStopTF.setEnabled(false);
	  timeToStopTF.setName(timeToStopTF_name);
	  timeToStopTF.setText("0");
	  timeToStopTF.addActionListener(new TextFieldListener());
	  GridBagConstraints gbc_timeToStopTF = new GridBagConstraints();
	  gbc_timeToStopTF.anchor = GridBagConstraints.NORTH;
	  gbc_timeToStopTF.fill = GridBagConstraints.BOTH;
	  gbc_timeToStopTF.gridx = 1;
	  gbc_timeToStopTF.gridy = 11;
	  add(timeToStopTF, gbc_timeToStopTF);
	  fieldsList.add(timeToStopTF);
	  
	  // vehicle status on y=12
	  vehicleStatusCB = new JCheckBox("Vehicle status");
	  vehicleStatusCB.addItemListener(new ItemListener() {
		  public void itemStateChanged(ItemEvent e) {
			  if (e.getStateChange() == ItemEvent.SELECTED){
				  vehicleStatusComBox.setEnabled(true);
			  }
			  else {
				  vehicleStatusComBox.setEnabled(false);
			  }
		  }
	  });
	  GridBagConstraints gbc_vehicleStatusCB = new GridBagConstraints();
	  gbc_vehicleStatusCB.anchor = GridBagConstraints.NORTH;
	  gbc_vehicleStatusCB.fill = GridBagConstraints.BOTH;
	  gbc_vehicleStatusCB.gridx = 0;
	  gbc_vehicleStatusCB.gridy = 12;
	  add(vehicleStatusCB, gbc_vehicleStatusCB);
	  
	  vehicleStatusArray = vehicle_button.getAllVehicleStatus_array();
	  vehicleStatusComBox = new JComboBox<String>(vehicleStatusArray);
	  vehicleStatusComBox.addActionListener(new ComBoListener());
	  vehicleStatusComBox.setEnabled(false);
	  vehicleStatusComBox.setName(vehicleStatusComBox_name);
	  vehicleStatusComBox.setSelectedIndex(0);
	  vehicleStatusComBox.addActionListener(new ComBoListener());
	  GridBagConstraints gbc_vehicleStatusComBox = new GridBagConstraints();
	  gbc_vehicleStatusComBox.anchor = GridBagConstraints.NORTH;
	  gbc_vehicleStatusComBox.fill = GridBagConstraints.BOTH;
	  gbc_vehicleStatusComBox.gridx = 1;
	  gbc_vehicleStatusComBox.gridy = 12;
	  add(vehicleStatusComBox, gbc_vehicleStatusComBox);
	  comboBoxList.add(vehicleStatusComBox);
	  
	  // priority class on y=13
	  priorityClassCB = new JCheckBox("Priority class");
	  priorityClassCB.addItemListener(new ItemListener() {
		  public void itemStateChanged(ItemEvent e) {
			  if (e.getStateChange() == ItemEvent.SELECTED){
				  priorityClassComBox.setEnabled(true);
			  }
			  else {
				  priorityClassComBox.setEnabled(false);
			  }
		  }
	  });
	  GridBagConstraints gbc_priorityClassCB = new GridBagConstraints();
	  gbc_priorityClassCB.anchor = GridBagConstraints.NORTH;
	  gbc_priorityClassCB.fill = GridBagConstraints.BOTH;
	  gbc_priorityClassCB.gridx = 0;
	  gbc_priorityClassCB.gridy = 13;
	  add(priorityClassCB, gbc_priorityClassCB);
	  
	  priorityClassArray = vehicle_button.getAllPriorityClass_array();
	  priorityClassComBox = new JComboBox<String>(priorityClassArray);
	  priorityClassComBox.addActionListener(new ComBoListener());
	  priorityClassComBox.setEnabled(false);
	  priorityClassComBox.setName(priorityClassComBox_name);
	  priorityClassComBox.setSelectedIndex(0);
	  priorityClassComBox.addActionListener(new ComBoListener());
	  GridBagConstraints gbc_priorityClassComBox = new GridBagConstraints();
	  gbc_priorityClassComBox.anchor = GridBagConstraints.NORTH;
	  gbc_priorityClassComBox.fill = GridBagConstraints.BOTH;
	  gbc_priorityClassComBox.gridx = 1;
	  gbc_priorityClassComBox.gridy = 13;
	  add(priorityClassComBox, gbc_priorityClassComBox);
	  comboBoxList.add(priorityClassComBox);
	  
	  // punctuality class on y=14
	  punctualityClassCB = new JCheckBox("Punctuality class");
	  punctualityClassCB.addItemListener(new ItemListener() {
		  public void itemStateChanged(ItemEvent e) {
			  if (e.getStateChange() == ItemEvent.SELECTED){
				  punctualityClassComBox.setEnabled(true);
			  }
			  else {
				  punctualityClassComBox.setEnabled(false);
			  }
		  }
	  });
	  GridBagConstraints gbc_punctualityClassCB = new GridBagConstraints();
	  gbc_punctualityClassCB.anchor = GridBagConstraints.NORTH;
	  gbc_punctualityClassCB.fill = GridBagConstraints.BOTH;
	  gbc_punctualityClassCB.gridx = 0;
	  gbc_punctualityClassCB.gridy = 14;
	  add(punctualityClassCB, gbc_punctualityClassCB);
	  
	  punctualityClassArray = vehicle_button.getAllPunctualityClass_array();
	  punctualityClassComBox = new JComboBox<String>(priorityClassArray);
	  punctualityClassComBox.addActionListener(new ComBoListener());
	  punctualityClassComBox.setEnabled(false);
	  punctualityClassComBox.setName(punctualityClassComBox_name);
	  punctualityClassComBox.setSelectedIndex(0);
	  punctualityClassComBox.addActionListener(new ComBoListener());
	  GridBagConstraints gbc_punctualityClassComBox = new GridBagConstraints();
	  gbc_punctualityClassComBox.anchor = GridBagConstraints.NORTH;
	  gbc_punctualityClassComBox.fill = GridBagConstraints.BOTH;
	  gbc_punctualityClassComBox.gridx = 1;
	  gbc_punctualityClassComBox.gridy = 14;
	  add(punctualityClassComBox, gbc_punctualityClassComBox);
	  comboBoxList.add(punctualityClassComBox);
	  
	  // punctuality y=15
	  punctualityCB = new JCheckBox("Punctuality");
	  punctualityCB.addItemListener(new ItemListener() {
		  public void itemStateChanged(ItemEvent e) {
			  if (e.getStateChange() == ItemEvent.SELECTED){
				  punctualityTF.setEnabled(true);
			  }
			  else {
				  punctualityTF.setEnabled(false);
			  }
		  }
	  });
	  GridBagConstraints gbc_punctualityCB = new GridBagConstraints();
	  gbc_punctualityCB.anchor = GridBagConstraints.NORTH;
	  gbc_punctualityCB.fill = GridBagConstraints.BOTH;
	  gbc_punctualityCB.gridx = 0;
	  gbc_punctualityCB.gridy = 15;
	  add(punctualityCB, gbc_punctualityCB);
	  
	  punctualityTF = new JTextField();
	  punctualityTF.setEnabled(false);
	  punctualityTF.setName(punctualityTF_name);
	  punctualityTF.setText("0");
	  punctualityTF.addActionListener(new TextFieldListener());
	  GridBagConstraints gbc_punctualityTF = new GridBagConstraints();
	  gbc_punctualityTF.anchor = GridBagConstraints.NORTH;
	  gbc_punctualityTF.fill = GridBagConstraints.BOTH;
	  gbc_punctualityTF.gridx = 1;
	  gbc_punctualityTF.gridy = 15;
	  add(punctualityTF, gbc_punctualityTF);
	  fieldsList.add(punctualityTF);
  } 
  
  /**
   * Loads the values entered in all fields
   */
  private void loadTextFieldValues() {
	  for(JTextField tf : fieldsList){
		  readTextField(tf);
	  }
	  for (JComboBox<String> combobox : comboBoxList){
		  readComBoBox(combobox);
	  }
  }
  
  /**
   * @brief Reads the value entered into the field and copies it to the same named VehicleButton attribute
   * 		The target attribute is decided by textfield name.
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
		  if (tf.getName().equals(loopNrTF_name))
			  vehicle_button.setLoopNr(number);
		  //TODO add the rest
	  }
  }
  /**
   * @brief Reads the current selection and copies it to the named VehicleButton attribute
   * 		The target attribute is decided by combobox name.
   * @param combobox JComboBox<String> 
   */
  private void readComBoBox(JComboBox<String> combobox){
	  if (!combobox.isEnabled())
		  return;
	  if (combobox.getName() == vehicleTypeComBox_name){
		  int index = combobox.getSelectedIndex();
		  VehicleTypes vt = VehicleTypes.getAsType(index);
		  vehicle_button.setVehicleType(vt); // index is the same as enum defined in VehicleButton class
		//TODO add the rest
	  }
  }
  
  // INNER CLASSES
  public class TextFieldListener implements ActionListener{
	  @Override
	  public void actionPerformed(ActionEvent e) {
		  JTextField tf = (JTextField)e.getSource();
		  readTextField(tf);
	  }

  } //end of inner class TextFieldListener
  
  public class ComBoListener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox<String> combobox = (JComboBox<String>)e.getSource();
		readComBoBox(combobox);
	}
	  
  }// end of inner class ComBoListener

  // PRIVATE ATTRIBUTES
  private VehicleButton vehicle_button;
  
  private JCheckBox loopNrCB;
  private JCheckBox signalGroupNrCB;    
  private JCheckBox directionCB;        
  private JCheckBox commandCB;          
  private JCheckBox vehicleTypeCB;      
  private JCheckBox lineNrCB;           
  private JCheckBox wagonNrCB;          
  private JCheckBox vehicleIdCB;        
  private JCheckBox vehicleLengthCB;    
  private JCheckBox vehicleSpeedCB;     
  private JCheckBox distanceToStopCB;   
  private JCheckBox timeToStopCB;       
  private JCheckBox vehicleStatusCB;    
  private JCheckBox priorityClassCB;    
  private JCheckBox punctualityClassCB; 
  private JCheckBox punctualityCB; 
  
  private JTextField loopNrTF;  
  private JTextField signalGroupNrTF;  
  private JComboBox<String> directionComBox;   
  private JComboBox<String> commandComBox;           
  private JComboBox<String> vehicleTypeComBox;      
  private JTextField lineNrTF;           
  private JTextField wagonNrTF;          
  private JTextField vehicleIdTF;        
  private JTextField vehicleLengthTF;    
  private JTextField vehicleSpeedTF;     
  private JTextField distanceToStopTF;   
  private JTextField timeToStopTF; 
  private JComboBox<String> vehicleStatusComBox; 
  private JComboBox<String> priorityClassComBox;
  private JComboBox<String> punctualityClassComBox; 
  private JTextField punctualityTF;     

  private String loopNrTF_name = "loopnr";
  private String signalGroupNrTF_name = "signalgroupnr";        
  private String directionComBox_name = "direction";            
  private String commandComBox_name = "command";              
  private String vehicleTypeComBox_name = "vehicletype";          
  private String lineNrTF_name = "linenr";               
  private String wagonNrTF_name = "wagonnr";              
  private String vehicleIdTF_name = "vehicleid";            
  private String vehicleLengthTF_name = "vehiclelength";        
  private String vehicleSpeedTF_name = "vehiclespeed";         
  private String distanceToStopTF_name = "distancetostop";       
  private String timeToStopTF_name = "timetostop";           
  private String vehicleStatusComBox_name = "vehiclestatus";        
  private String priorityClassComBox_name = "priorityclass";
  private String punctualityClassComBox_name = "punctualityclass";     
  private String punctualityTF_name = "punctuality"; 
  
  private ArrayList<JTextField> fieldsList; // holds all textfields
  private ArrayList<JComboBox<String>> comboBoxList; // holds all comboboxes

  private String[] directionArray;
  private String[] commandArray;
  private String[] vehicleTypesArray;
  private String[] vehicleStatusArray;
  private String[] priorityClassArray;
  private String[] punctualityClassArray;
		  
}// end of class




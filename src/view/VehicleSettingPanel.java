package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.InputVerifier;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import lombok.Getter;
import model.CVNAttribute;
import model.CVNField;
import model.CVNMessage;
import view.ProtocolPanel.Proto;
import view.VehicleButton.Commands;
import view.VehicleButton.Direction;
import view.VehicleButton.JourneyType;
import view.VehicleButton.ManualControl;
import view.VehicleButton.PriorityClass;
import view.VehicleButton.PunctualityClass;
import view.VehicleButton.VehicleStatus;
import view.VehicleButton.VehicleType;

public class VehicleSettingPanel extends JPanel {

  private static final long serialVersionUID = 3L;

  /**
   * Constructor for VehicleSettingPanel.
   * @param vehicle_button
   */
  public VehicleSettingPanel(VehicleButton a_vehicle_button) {
    calendar = new GregorianCalendar();
    setBorder(new TitledBorder(null, "Vehicle Setting", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] {25, 50, 100, 50, 25, 25, 25, 25, 25};
    gridBagLayout.rowHeights = new int[] {};
    gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    gridBagLayout.rowWeights = new double[]{0.0, 0.0};
    setLayout(gridBagLayout);
    initialize();
  }

  // PUBLIC METHODS
  public void setProto(Proto a_proto) {
    switch(a_proto){
      case KAR:
        setBorder(new TitledBorder(null, "Vehicle Setting for KAR", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        loopNrTF.setEnabled(true);
        lblLoop.setForeground(Color.BLACK);
        vehicleTypeComBox.setEnabled(true);
        lblVehtype.setForeground(Color.BLACK);
        lineNrTF.setEnabled(true);
        lblLine.setForeground(Color.BLACK);
        vehServiceNrTF.setEnabled(true);
        lblService.setForeground(Color.BLACK);
        companyNrTF.setEnabled(true);
        lblCompany.setForeground(Color.BLACK);
        vehicleIdTF.setEnabled(true);
        lblVehId.setForeground(Color.BLACK);
        signalgroupNrTF.setEnabled(true);
        lblDirection.setForeground(Color.BLACK);
        manualControlComBox.setEnabled(false);
        lblManualControl.setForeground(Color.GRAY);
        vehicleStatusComBox.setEnabled(true);
        lblVehStatus.setForeground(Color.BLACK);
        priorityClassComBox.setEnabled(true);
        lblPrioClass.setForeground(Color.BLACK);
        punctualityClassComBox.setEnabled(true);
        lblPunctClass.setForeground(Color.BLACK);
        punctualityTF.setEnabled(true);
        lblPunctuality.setForeground(Color.BLACK);
        vehicleLengthTF.setEnabled(true);
        lblVehLength.setForeground(Color.BLACK);
        vehicleSpeedTF.setEnabled(true);
        lblVehSpeed.setForeground(Color.BLACK);
        distanceToStopTF.setEnabled(true);
        lblDistToStop.setForeground(Color.BLACK);
        timeToStopTF.setEnabled(true);
        lblTimeToStop.setForeground(Color.BLACK);
        journeyNrTF.setEnabled(true);
        lblJourneyNr.setForeground(Color.BLACK);
        journeyTypeComBox.setEnabled(true);
        lblJourneyType.setForeground(Color.BLACK);
        routeTF.setEnabled(true);
        lblRoute.setForeground(Color.BLACK);
        DirectionComBox.setEnabled(false);
        lblDirection.setForeground(Color.GRAY);//18 VECOM
        commandComBox.setEnabled(true);
        lblCommand.setForeground(Color.BLACK);
        activationTF.setEnabled(true);
        lblActivation.setForeground(Color.BLACK);
        latDegTF.setEnabled(true);
        lblLatitude.setForeground(Color.BLACK);
        latMinTF.setEnabled(true);
        latSecTF.setEnabled(true);
        latSSecTF.setEnabled(true);
        lblLongitude.setForeground(Color.BLACK);
        longDegTF.setEnabled(true);
        longMinTF.setEnabled(true);
        longSecTF.setEnabled(true);
        longSSecTF.setEnabled(true);
        lblDate.setForeground(Color.BLACK);
        yearTF.setEnabled(true);
        monthTF.setEnabled(true);
        dayTF.setEnabled(true);
        lblTime.setForeground(Color.BLACK);
        hourTF.setEnabled(true);
        minuteTF.setEnabled(true);
        secondTF.setEnabled(true);
        lblReserve_1.setForeground(Color.BLACK);
        reserve1TF.setEnabled(true);
        lblReserve_2.setForeground(Color.BLACK);
        reserve2TF.setEnabled(true);
        break;
      case VECOM:
        setBorder(new TitledBorder(null, "Vehicle Setting for VECOM", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        loopNrTF.setEnabled(true);
        lblLoop.setForeground(Color.BLACK);//1
        vehicleTypeComBox.setEnabled(true);
        lblVehtype.setForeground(Color.BLACK);//2
        lineNrTF.setEnabled(true);
        lblLine.setForeground(Color.BLACK);//3
        vehServiceNrTF.setEnabled(true);
        lblService.setForeground(Color.BLACK);//4
        companyNrTF.setEnabled(false);
        lblCompany.setForeground(Color.GRAY);//5
        vehicleIdTF.setEnabled(true);
        lblVehId.setForeground(Color.BLACK);//6
        signalgroupNrTF.setEnabled(false);
        lblDirection.setForeground(Color.GRAY);//7
        manualControlComBox.setEnabled(true);
        lblManualControl.setForeground(Color.BLACK);//7
        vehicleStatusComBox.setEnabled(false);
        lblVehStatus.setForeground(Color.GRAY);//8
        priorityClassComBox.setEnabled(false);
        lblPrioClass.setForeground(Color.GRAY);//9
        punctualityClassComBox.setEnabled(true);
        lblPunctClass.setForeground(Color.BLACK);//10
        punctualityTF.setEnabled(false);
        lblPunctuality.setForeground(Color.GRAY);//11
        vehicleLengthTF.setEnabled(false);
        lblVehLength.setForeground(Color.GRAY);//12
        vehicleSpeedTF.setEnabled(false);
        lblVehSpeed.setForeground(Color.GRAY);//13
        distanceToStopTF.setEnabled(false);
        lblDistToStop.setForeground(Color.GRAY);//14
        timeToStopTF.setEnabled(false);
        lblTimeToStop.setForeground(Color.GRAY);//15
        journeyNrTF.setEnabled(false);
        lblJourneyNr.setForeground(Color.GRAY);//16
        journeyTypeComBox.setEnabled(true);
        lblJourneyType.setForeground(Color.BLACK);//17
        routeTF.setEnabled(false);
        lblRoute.setForeground(Color.GRAY);//18
        DirectionComBox.setEnabled(true);
        lblDirection.setForeground(Color.BLACK);//18 VECOM
        commandComBox.setEnabled(false);
        lblCommand.setForeground(Color.GRAY);//19
        activationTF.setEnabled(false);
        lblActivation.setForeground(Color.GRAY);//20
        latDegTF.setEnabled(false);
        lblLatitude.setForeground(Color.GRAY);//21
        latMinTF.setEnabled(false);
        latSecTF.setEnabled(false);
        latSSecTF.setEnabled(false);
        lblLongitude.setForeground(Color.GRAY);
        longDegTF.setEnabled(false);
        longMinTF.setEnabled(false);
        longSecTF.setEnabled(false);
        longSSecTF.setEnabled(false);
        lblDate.setForeground(Color.GRAY);//22
        yearTF.setEnabled(false);
        monthTF.setEnabled(false);
        dayTF.setEnabled(false);
        lblTime.setForeground(Color.GRAY);
        hourTF.setEnabled(false);
        minuteTF.setEnabled(false);
        secondTF.setEnabled(false);
        lblReserve_1.setForeground(Color.GRAY);//23
        reserve1TF.setEnabled(false);
        lblReserve_2.setForeground(Color.GRAY);//24
        reserve2TF.setEnabled(false);
        break;
      case SICS:
        //as if
        break;
      default:
        break;
    }

  }

  public String getLoopNr(){
    return loopNrTF.getText();
  }
  public void setLoopNrTF(String a_loopNR) {
    loopNrTF.setText(a_loopNR);
  }

  public VehicleType getVehicleType(){
    return (VehicleType) vehicleTypeComBox.getSelectedItem();
  }
  public void setVehicleTypeComBox(VehicleType a_vehicleType) { 
    this.vehicleTypeComBox.setSelectedItem(a_vehicleType);
  }

  public String getLineNr(){
    return lineNrTF.getText();
  }
  public void setLineNrTF(String a_lineNr) {
    lineNrTF.setText(a_lineNr);
  }

  public String getVehServiceNr(){
    return vehServiceNrTF.getText();
  }
  public void setVehServiceNrTF(String a_vehServiceNr) {
    vehServiceNrTF.setText(a_vehServiceNr);
  }

  public String getCompanyNr(){
    return companyNrTF.getText();
  }
  public void setCompanyNrTF(String a_companyNr) {
    companyNrTF.setText(a_companyNr);
  }

  public String getVehicleId(){
    return vehicleIdTF.getText();
  }
  public void setVehicleIdTF(String a_vehicleId) {
    vehicleIdTF.setText(a_vehicleId);;
  }

  public ManualControl getManualControl(){
    return (ManualControl) manualControlComBox.getSelectedItem();
  }
  public void setManualControlComBox(
      ManualControl a_manualControl) {
    manualControlComBox.setSelectedItem(a_manualControl);
  }

  public Direction getDirection(){
    return (Direction) DirectionComBox.getSelectedItem();
  }
  public void setDirectionComBox(Direction a_direction) {
    DirectionComBox.setSelectedItem(a_direction);  
  }

  public String getSignalGroupNr(){
    return signalgroupNrTF.getText();
  }
  public void setSignalGroupNr(String a_signalgroupNr){
    signalgroupNrTF.setText(a_signalgroupNr);
  }

  public VehicleStatus getVehicleStatus(){
    return (VehicleStatus) vehicleStatusComBox.getSelectedItem();
  }
  public void setVehicleStatusComBox(
      VehicleStatus a_vehicleStatus) {
    vehicleStatusComBox.setSelectedItem(a_vehicleStatus);
  }

  public PriorityClass getPriorityClass(){
    return (PriorityClass) priorityClassComBox.getSelectedItem();
  }
  public void setPriorityClassComBox(
      PriorityClass a_priorityClass) {
    priorityClassComBox.setSelectedItem(a_priorityClass);
  }

  public PunctualityClass getPunctualityClass(){
    return (PunctualityClass) punctualityClassComBox.getSelectedItem();
  }
  public void setPunctualityClassComBox(
      PunctualityClass a_punctualityClass) {
    punctualityClassComBox.setSelectedItem(a_punctualityClass);
  }

  public String getPunctuality(){
    return punctualityTF.getText();
  }
  public void setPunctualityTF(String a_punctuality) {
    punctualityTF.setText(a_punctuality);
  }

  public String getVehicleLength(){
    return vehicleLengthTF.getText();
  }
  public void setVehicleLengthTF(String a_vehicleLength) {
    vehicleLengthTF.setText(a_vehicleLength);
  }

  public String getVehicleSpeed(){
    return vehicleSpeedTF.getText();
  }
  public void setVehicleSpeedTF(String a_vehicleSpeed) {
    vehicleSpeedTF.setText(a_vehicleSpeed);
  }

  public String getDistanceToStop(){
    return distanceToStopTF.getText();
  }
  public void setDistanceToStopTF(String a_distanceToStop) {
    distanceToStopTF.setText(a_distanceToStop);
  }

  public String getTimeToStop(){
    return timeToStopTF.getText();
  }
  public void setTimeToStopTF(String a_timeToStop) {
    timeToStopTF.setText(a_timeToStop);
  }

  public String getJourneyNr(){
    return journeyNrTF.getText();
  }
  public void setJourneyNrTF(String a_journeyNr) {
    journeyNrTF.setText(a_journeyNr);
  }

  public JourneyType getJourneyType(){
    return (JourneyType) journeyTypeComBox.getSelectedItem();
  }
  public void setJourneyTypeComBox(JourneyType a_journeyType) {
    journeyTypeComBox.setSelectedItem(a_journeyType);
  }

  public String getRoute(){
    return routeTF.getText();
  }
  public void setRouteTF(String a_routeTF) {
    routeTF.setText(a_routeTF);
  }

  public Commands getCommands(){
    return (Commands) commandComBox.getSelectedItem();
  }
  public void setCommandComBox(Commands a_command) {
    commandComBox.setSelectedItem(a_command);
  }

  public String getActivation(){
    return activationTF.getText();
  }
  public void setActivationTF(String a_activation) {
    activationTF.setText(a_activation);
  }

  public String getLatDeg(){
    return latDegTF.getText();
  }
  public void setLatDegTF(String a_latDeg) {
    latDegTF.setText(a_latDeg);
  }

  public String getLatMin(){
    return latMinTF.getText();
  }
  public void setLatMinTF(String a_latMin) {
    latMinTF.setText(a_latMin);
  }

  public String getLatSec(){
    return latSecTF.getText();
  }
  public void setLatSecTF(String a_latSec) {
    latSecTF.setText(a_latSec);
  }

  public String getLatSSec(){
    return latSSecTF.getText();
  }
  public void setLatSSecTF(String a_latSSec) {
    latSSecTF.setText(a_latSSec);
  }

  public String getLongDeg(){
    return longDegTF.getText();
  }
  public void setLongDegTF(String a_longDeg) {
    longDegTF.setText(a_longDeg);
  }

  public String getLongMin(){
    return longMinTF.getText();
  }
  public void setLongMinTF(String a_longMin) {
    longMinTF.setText(a_longMin);
  }

  public String getLongSec(){
    return longSecTF.getText();
  }
  public void setLongSecTF(String a_longSec) {
    longSecTF.setText(a_longSec);
  }

  public String getLongSSec(){
    return longSSecTF.getText();
  }
  public void setLongSSecTF(String a_longSSec) {
    longSSecTF.setText(a_longSSec);
  }

  public String getYear(){
    return yearTF.getText();
  }
  public void setYearTF(String a_year) {
    yearTF.setText(a_year);
  }

  public String getMonth(){
    return monthTF.getText();
  }
  public void setMonthTF(String a_month) {
    monthTF.setText(a_month);
  }

  public String getDay(){
    return dayTF.getText();
  }
  public void setDayTF(String a_day) {
    dayTF.setText(a_day);
  }

  public String getHour(){
    return hourTF.getText();
  }
  public void setHourTF(String a_hour) {
    hourTF.setText(a_hour);
  }

  public String getMinute(){
    return minuteTF.getText();
  }
  public void setMinuteTF(String a_minute) {
    minuteTF.setText(a_minute);
  }

  public String getSecond(){
    return secondTF.getText();
  }
  public void setSecondTF(String a_second) {
    secondTF.setText(a_second);
  }

  public String getReserve1(){
    return reserve1TF.getText();
  }
  public void setReserve1TF(String a_reserve1) {
    reserve1TF.setText(a_reserve1);
  }

  public String getReserve2(){
    return reserve2TF.getText();
  }
  public void setReserve2TF(String a_reserve2) {
    reserve2TF.setText(a_reserve2);
  }
  
  private GridBagConstraints createDefaultGridBagConstraints(JComponent component, int row, int column) {
	  	GridBagConstraints gbc = new GridBagConstraints();
	  	gbc.insets = new Insets(0, 0, 5, 5);
	  	gbc.gridx = column;
	  	gbc.gridy = row;
	  	return gbc;
  }
  
  private GridBagConstraints createLabelGridBagConstraints(JComponent component, int row, int column) {
	  	GridBagConstraints gbc = createDefaultGridBagConstraints(component, row, column);
	  	gbc.anchor = GridBagConstraints.WEST;
	  	add(component, gbc);
	  	return gbc;
  }
  
  private GridBagConstraints createCheckBoxGridBagConstraints(JComponent component, int row, int column) {
	  	GridBagConstraints gbc = createDefaultGridBagConstraints(component, row, column);
	  	gbc.anchor = GridBagConstraints.EAST;
	  	add(component, gbc);
	  	return gbc;
}
  
  private GridBagConstraints createInputFieldGridBagConstraints(JComponent component, int row, int column) {
	  	GridBagConstraints gbc = createDefaultGridBagConstraints(component, row, column);
	  	gbc.anchor = GridBagConstraints.NORTH;
	  	gbc.fill = GridBagConstraints.BOTH;
	  	add(component, gbc);
	  	return gbc;
  }
  
  private JComponent createInputField(CVNField field) {
	  NumericField tf= new NumericField(3, NumericField.NUMERIC);
	  tf.setAllowNegative(field.getRange().getMinimum() < 0);
	  tf.setNumber(0);
	  tf.setToolTipText(field.getFieldName() + "[" + field.getRange().toString() + "]");
	  return tf;	
  }
  
  // PRIVATE METHODS
  private void initialize(){
	attributeUsedCheckBoxes = new ArrayList<>();

	CVNMessage message = new CVNMessage();
	CVNAttribute[] attributes = message.getAttributes();
	int row = 0, column = 0;
	for (int cvnNumber = 0; cvnNumber < attributes.length; cvnNumber++) {
		JCheckBox cb = new JCheckBox();
		cb.setToolTipText("Disable CVN: " + (cvnNumber + 1) + "?");
		
		column = cvnNumber < 13 ? 0 : 3;
//		row = cvnNumber % 14;
		createCheckBoxGridBagConstraints(cb, row % 14, column);
		attributeUsedCheckBoxes.add(cb);
		
//		JComponent label = new JLabel(attributes[cvnNumber].getCvnField().get(0).getFieldName() + " (" + attributes[cvnNumber].getCvnNumber() + ")");
//		createLabelGridBagConstraints(label, row, column + 1);
//		
//		JComponent inputField = createInputField(attributes[cvnNumber].getCvnField().get(0));
//		createInputFieldGridBagConstraints(inputField, row, column + 2);
		
		if (cvnNumber == 6 || cvnNumber == 17 || cvnNumber == 20 || cvnNumber == 21) 
			row++;
		row++;
	}
	
    // CVN: 1 loop number
    lblLoop = new JLabel("Loop nr (1)");
    createLabelGridBagConstraints(lblLoop, 0, 1);
    loopNrTF = new NumericField(3, NumericField.DECIMAL);
    loopNrTF.setAllowNegative(false);
    loopNrTF.setText("0");
    loopNrTF.setToolTipText("Loop 0-127");
    createInputFieldGridBagConstraints(loopNrTF, 0, 2);

    // CVN: 2 vehicle type
    lblVehtype = new JLabel("Veh. Type (2)");
    createLabelGridBagConstraints(lblVehtype, 1, 1);
    vehicleTypeComBox = new JComboBox<VehicleType>(VehicleType.values());
    vehicleTypeComBox.setSelectedIndex(0);
    createInputFieldGridBagConstraints(vehicleTypeComBox, 1, 2);

    // CVN: 3 line number
    lblLine = new JLabel("Line nr (3)");
    createLabelGridBagConstraints(lblLine, 2, 1);
    lineNrTF = new NumericField(4, NumericField.DECIMAL);
    lineNrTF.setAllowNegative(false);
    lineNrTF.setText("0");
    lineNrTF.setToolTipText("Line number 0-9999");
    createInputFieldGridBagConstraints(lineNrTF, 2, 2);

    // CVN: 4 vehicle service number
    lblService = new JLabel("Service nr (4)");
    createLabelGridBagConstraints(lblService, 3, 1);
    vehServiceNrTF = new NumericField(4, NumericField.DECIMAL);
    vehServiceNrTF.setAllowNegative(false);
    vehServiceNrTF.setText("0");
    vehServiceNrTF.setToolTipText("Vehicle servie number. Also known as Block number 0-9999");
    createInputFieldGridBagConstraints(vehServiceNrTF, 3, 2);

    // CVN: 5 company number
    lblCompany = new JLabel("Company nr (5)");
    createLabelGridBagConstraints(lblCompany, 4, 1);
    companyNrTF = new NumericField(3, NumericField.DECIMAL);
    companyNrTF.setAllowNegative(false);
    companyNrTF.setText("0");
    companyNrTF.setToolTipText("Company number 0-255");
    createInputFieldGridBagConstraints(companyNrTF, 4, 2);

    // CVN: 6 vehicle ID
    lblVehId = new JLabel("Veh. id (6)");
    createLabelGridBagConstraints(lblVehId, 5, 1);
    vehicleIdTF = new NumericField(5, NumericField.DECIMAL);
    vehicleIdTF.setAllowNegative(false);
    vehicleIdTF.setText("0");
    vehicleIdTF.setToolTipText("Vehicle ID 0-32767");
    createInputFieldGridBagConstraints(vehicleIdTF, 5, 2);

    // CVN: 7 signal group KAR
    lblSignalGroup = new JLabel("SignalGroup (7)");
    createLabelGridBagConstraints(lblSignalGroup, 6, 1);
    signalgroupNrTF = new NumericField(3, NumericField.DECIMAL);
    signalgroupNrTF.setAllowNegative(false);
    signalgroupNrTF.setText("0");
    signalgroupNrTF.setToolTipText("Direction for KAR 0-255");
    createInputFieldGridBagConstraints(signalgroupNrTF, 6, 2);

    // CVN: 7 manual control VECOM
    lblManualControl = new JLabel("Manual control(7)");
    GridBagConstraints gbc_lblManualControl = new GridBagConstraints();
    gbc_lblManualControl.insets = new Insets(0, 0, 5, 5);
    gbc_lblManualControl.anchor = GridBagConstraints.EAST;
    gbc_lblManualControl.gridx = 1;
    gbc_lblManualControl.gridy = 7;
    add(lblManualControl, gbc_lblManualControl);
    manualControlComBox = new JComboBox<ManualControl>(ManualControl.values());
    manualControlComBox.setSelectedIndex(0);
    manualControlComBox.setToolTipText("Manual control number for Vecom");
    GridBagConstraints gbc_ManualControlComBox = new GridBagConstraints();
    gbc_ManualControlComBox.insets = new Insets(0, 0, 5, 5);
    gbc_ManualControlComBox.anchor = GridBagConstraints.NORTH;
    gbc_ManualControlComBox.fill = GridBagConstraints.BOTH;
    gbc_ManualControlComBox.gridx = 2;
    gbc_ManualControlComBox.gridy = 7;
    add(manualControlComBox, gbc_ManualControlComBox);

    // CVN: 8 vehicle status
    lblVehStatus = new JLabel("Veh. Status (8)");
    GridBagConstraints gbc_lblVehStatus = new GridBagConstraints();
    gbc_lblVehStatus.insets = new Insets(0, 0, 5, 5);
    gbc_lblVehStatus.anchor = GridBagConstraints.EAST;
    gbc_lblVehStatus.gridx = 1;
    gbc_lblVehStatus.gridy = 8;
    add(lblVehStatus, gbc_lblVehStatus);
    vehicleStatusComBox = new JComboBox<VehicleStatus>(VehicleStatus.values());
    vehicleStatusComBox.setSelectedIndex(0);
    GridBagConstraints gbc_vehicleStatusComBox = new GridBagConstraints();
    gbc_vehicleStatusComBox.insets = new Insets(0, 0, 5, 5);
    gbc_vehicleStatusComBox.anchor = GridBagConstraints.NORTH;
    gbc_vehicleStatusComBox.fill = GridBagConstraints.BOTH;
    gbc_vehicleStatusComBox.gridx = 2;
    gbc_vehicleStatusComBox.gridy = 8;
    add(vehicleStatusComBox, gbc_vehicleStatusComBox);

    // CVN: 9 priority class
    lblPrioClass = new JLabel("Prio. Class (9)");
    GridBagConstraints gbc_lblPrioClass = new GridBagConstraints();
    gbc_lblPrioClass.insets = new Insets(0, 0, 5, 5);
    gbc_lblPrioClass.anchor = GridBagConstraints.EAST;
    gbc_lblPrioClass.gridx = 1;
    gbc_lblPrioClass.gridy = 9;
    add(lblPrioClass, gbc_lblPrioClass);
    priorityClassComBox = new JComboBox<PriorityClass>(PriorityClass.values());
    priorityClassComBox.setSelectedIndex(0);
    GridBagConstraints gbc_priorityClassComBox = new GridBagConstraints();
    gbc_priorityClassComBox.insets = new Insets(0, 0, 5, 5);
    gbc_priorityClassComBox.anchor = GridBagConstraints.NORTH;
    gbc_priorityClassComBox.fill = GridBagConstraints.BOTH;
    gbc_priorityClassComBox.gridx = 2;
    gbc_priorityClassComBox.gridy = 9;
    add(priorityClassComBox, gbc_priorityClassComBox);

    // CVN: 10 punctuality class
    lblPunctClass = new JLabel("Punct. Class (10)");
    GridBagConstraints gbc_lblPunctClass = new GridBagConstraints();
    gbc_lblPunctClass.insets = new Insets(0, 0, 5, 5);
    gbc_lblPunctClass.anchor = GridBagConstraints.EAST;
    gbc_lblPunctClass.gridx = 1;
    gbc_lblPunctClass.gridy = 10;
    add(lblPunctClass, gbc_lblPunctClass);
    punctualityClassComBox = new JComboBox<PunctualityClass>(PunctualityClass.values());
    punctualityClassComBox.setSelectedIndex(0);
    GridBagConstraints gbc_punctualityClassComBox = new GridBagConstraints();
    gbc_punctualityClassComBox.insets = new Insets(0, 0, 5, 5);
    gbc_punctualityClassComBox.anchor = GridBagConstraints.NORTH;
    gbc_punctualityClassComBox.fill = GridBagConstraints.BOTH;
    gbc_punctualityClassComBox.gridx = 2;
    gbc_punctualityClassComBox.gridy = 10;
    add(punctualityClassComBox, gbc_punctualityClassComBox);

    // CVN: 11 punctuality
    lblPunctuality = new JLabel("Punctuality (11)");
    GridBagConstraints gbc_lblPunctuality = new GridBagConstraints();
    gbc_lblPunctuality.insets = new Insets(0, 0, 5, 5);
    gbc_lblPunctuality.anchor = GridBagConstraints.EAST;
    gbc_lblPunctuality.gridx = 1;
    gbc_lblPunctuality.gridy = 11;
    add(lblPunctuality, gbc_lblPunctuality);
    punctualityTF = new NumericField(2, NumericField.DECIMAL);
    punctualityTF.setAllowNegative(false);
    punctualityTF.setText("0");
    punctualityTF.setToolTipText("Punctuality 0-99 [s]");
    GridBagConstraints gbc_punctualityTF = new GridBagConstraints();
    gbc_punctualityTF.insets = new Insets(0, 0, 5, 5);
    gbc_punctualityTF.anchor = GridBagConstraints.NORTH;
    gbc_punctualityTF.fill = GridBagConstraints.BOTH;
    gbc_punctualityTF.gridx = 2;
    gbc_punctualityTF.gridy = 11;
    add(punctualityTF, gbc_punctualityTF);    

    // CVN: 12 vehicle length
    lblVehLength = new JLabel("Veh. Length (12)");
    GridBagConstraints gbc_lblVehLength = new GridBagConstraints();
    gbc_lblVehLength.insets = new Insets(0, 0, 5, 5);
    gbc_lblVehLength.anchor = GridBagConstraints.EAST;
    gbc_lblVehLength.gridx = 1;
    gbc_lblVehLength.gridy = 12;
    add(lblVehLength, gbc_lblVehLength);
    vehicleLengthTF = new NumericField(3, NumericField.DECIMAL);
    vehicleLengthTF.setAllowNegative(false);
    vehicleLengthTF.setText("0");
    vehicleLengthTF.setToolTipText("Vehicle length 0-255 [m]");
    GridBagConstraints gbc_vehicleLengthTF = new GridBagConstraints();
    gbc_vehicleLengthTF.insets = new Insets(0, 0, 5, 5);
    gbc_vehicleLengthTF.anchor = GridBagConstraints.NORTH;
    gbc_vehicleLengthTF.fill = GridBagConstraints.BOTH;
    gbc_vehicleLengthTF.gridx = 2;
    gbc_vehicleLengthTF.gridy = 12;
    add(vehicleLengthTF, gbc_vehicleLengthTF);

    // CVN: 13 vehicle speed
    lblVehSpeed = new JLabel("Veh. Speed (13)");
    GridBagConstraints gbc_lblVehSpeed = new GridBagConstraints();
    gbc_lblVehSpeed.insets = new Insets(0, 0, 5, 5);
    gbc_lblVehSpeed.anchor = GridBagConstraints.EAST;
    gbc_lblVehSpeed.gridx = 1;
    gbc_lblVehSpeed.gridy = 13;
    add(lblVehSpeed, gbc_lblVehSpeed);
    vehicleSpeedTF = new NumericField(2, NumericField.DECIMAL);
    vehicleSpeedTF.setAllowNegative(false);
    vehicleSpeedTF.setText("0");
    vehicleSpeedTF.setToolTipText("Vehicle speed 0-99 [m/s]");
    GridBagConstraints gbc_vehicleSpeedTF = new GridBagConstraints();
    gbc_vehicleSpeedTF.insets = new Insets(0, 0, 5, 5);
    gbc_vehicleSpeedTF.anchor = GridBagConstraints.NORTH;
    gbc_vehicleSpeedTF.fill = GridBagConstraints.BOTH;
    gbc_vehicleSpeedTF.gridx = 2;
    gbc_vehicleSpeedTF.gridy = 13;
    add(vehicleSpeedTF, gbc_vehicleSpeedTF);

    // CVN: 14 distance to stop
    lblDistToStop = new JLabel("Dist. to Stop (14)");
    GridBagConstraints gbc_lblDistToStop = new GridBagConstraints();
    gbc_lblDistToStop.insets = new Insets(0, 0, 5, 5);
    gbc_lblDistToStop.anchor = GridBagConstraints.EAST;
    gbc_lblDistToStop.gridx = 4;
    gbc_lblDistToStop.gridy = 0;
    add(lblDistToStop, gbc_lblDistToStop);
    distanceToStopTF = new NumericField(4, NumericField.DECIMAL);
    distanceToStopTF.setAllowNegative(false);
    distanceToStopTF.setText("0");
    distanceToStopTF.setToolTipText("Distance to passage stop mark -99-9999 [m]");
    GridBagConstraints gbc_distanceToStopTF = new GridBagConstraints();
    gbc_distanceToStopTF.gridwidth = 4;
    gbc_distanceToStopTF.insets = new Insets(0, 0, 5, 5);
    gbc_distanceToStopTF.anchor = GridBagConstraints.NORTH;
    gbc_distanceToStopTF.fill = GridBagConstraints.BOTH;
    gbc_distanceToStopTF.gridx = 5;
    gbc_distanceToStopTF.gridy = 0;
    add(distanceToStopTF, gbc_distanceToStopTF);    

    // CVN: 15 time to stop
    lblTimeToStop = new JLabel("Time to Stop (15)");
    GridBagConstraints gbc_lblTimeToStop = new GridBagConstraints();
    gbc_lblTimeToStop.insets = new Insets(0, 0, 5, 5);
    gbc_lblTimeToStop.anchor = GridBagConstraints.EAST;
    gbc_lblTimeToStop.gridx = 4;
    gbc_lblTimeToStop.gridy = 1;
    add(lblTimeToStop, gbc_lblTimeToStop);
    timeToStopTF = new NumericField(3, NumericField.DECIMAL);
    timeToStopTF.setAllowNegative(false);
    timeToStopTF.setText("0");
    timeToStopTF.setToolTipText("Time to passage stop mark 0-255 [s]");
    GridBagConstraints gbc_timeToStopTF = new GridBagConstraints();
    gbc_timeToStopTF.gridwidth = 4;
    gbc_timeToStopTF.insets = new Insets(0, 0, 5, 5);
    gbc_timeToStopTF.anchor = GridBagConstraints.NORTH;
    gbc_timeToStopTF.fill = GridBagConstraints.BOTH;
    gbc_timeToStopTF.gridx = 5;
    gbc_timeToStopTF.gridy = 1;
    add(timeToStopTF, gbc_timeToStopTF);

    // CVN: 16 journey number
    lblJourneyNr = new JLabel("Journey nr (16)");
    GridBagConstraints gbc_lblJourneyNr = new GridBagConstraints();
    gbc_lblJourneyNr.insets = new Insets(0, 0, 5, 5);
    gbc_lblJourneyNr.anchor = GridBagConstraints.EAST;
    gbc_lblJourneyNr.gridx = 4;
    gbc_lblJourneyNr.gridy = 2;
    add(lblJourneyNr, gbc_lblJourneyNr);
    journeyNrTF = new NumericField(4, NumericField.DECIMAL);
    journeyNrTF.setAllowNegative(false);
    journeyNrTF.setText("0");
    journeyNrTF.setToolTipText("Journey number 0-9999");
    GridBagConstraints gbc_journeyNrTF = new GridBagConstraints();
    gbc_journeyNrTF.gridwidth = 4;
    gbc_journeyNrTF.insets = new Insets(0, 0, 5, 5);
    gbc_journeyNrTF.anchor = GridBagConstraints.NORTH;
    gbc_journeyNrTF.fill = GridBagConstraints.BOTH;
    gbc_journeyNrTF.gridx = 5;
    gbc_journeyNrTF.gridy = 2;
    add(journeyNrTF, gbc_journeyNrTF); 

    // CVN: 17 journey type
    lblJourneyType = new JLabel("Journey Type (17)");
    GridBagConstraints gbc_lblJourneyType = new GridBagConstraints();
    gbc_lblJourneyType.insets = new Insets(0, 0, 5, 5);
    gbc_lblJourneyType.anchor = GridBagConstraints.EAST;
    gbc_lblJourneyType.gridx = 4;
    gbc_lblJourneyType.gridy = 3;
    add(lblJourneyType, gbc_lblJourneyType);
    journeyTypeComBox = new JComboBox<JourneyType>(JourneyType.values());
    journeyTypeComBox.setSelectedIndex(0);
    GridBagConstraints gbc_journeyTypeComBox = new GridBagConstraints();
    gbc_journeyTypeComBox.gridwidth = 4;
    gbc_journeyTypeComBox.insets = new Insets(0, 0, 5, 5);
    gbc_journeyTypeComBox.anchor = GridBagConstraints.NORTH;
    gbc_journeyTypeComBox.fill = GridBagConstraints.BOTH;
    gbc_journeyTypeComBox.gridx = 5;
    gbc_journeyTypeComBox.gridy = 3;
    add(journeyTypeComBox, gbc_journeyTypeComBox);

    // CVN: 18 route
    lblRoute = new JLabel("Route (18)");
    GridBagConstraints gbc_lblRoute = new GridBagConstraints();
    gbc_lblRoute.insets = new Insets(0, 0, 5, 5);
    gbc_lblRoute.anchor = GridBagConstraints.EAST;
    gbc_lblRoute.gridx = 4;
    gbc_lblRoute.gridy = 4;
    add(lblRoute, gbc_lblRoute);
    routeTF = new NumericField(2, NumericField.DECIMAL);
    routeTF.setAllowNegative(false);
    routeTF.setText("0");
    routeTF.setToolTipText("Route public transport 0-99");
    GridBagConstraints gbc_routeTF = new GridBagConstraints();
    gbc_routeTF.gridwidth = 4;
    gbc_routeTF.insets = new Insets(0, 0, 5, 5);
    gbc_routeTF.anchor = GridBagConstraints.NORTH;
    gbc_routeTF.fill = GridBagConstraints.BOTH;
    gbc_routeTF.gridx = 5;
    gbc_routeTF.gridy = 4;
    add(routeTF, gbc_routeTF);

    // CVN: 18 direction to route VECOM
    lblDirection = new JLabel("Direction(18)");
    GridBagConstraints gbc_lblDirection = new GridBagConstraints();
    gbc_lblDirection.insets = new Insets(0, 0, 5, 5);
    gbc_lblDirection.anchor = GridBagConstraints.EAST;
    gbc_lblDirection.gridx = 4;
    gbc_lblDirection.gridy = 5;
    add(lblDirection, gbc_lblDirection);
    DirectionComBox = new JComboBox<Direction>(Direction.values());
    DirectionComBox.setSelectedIndex(0);
    DirectionComBox.setToolTipText("Direction field for VECOM");
    GridBagConstraints gbc_DirectionComBox = new GridBagConstraints();
    gbc_DirectionComBox.gridwidth = 4;
    gbc_DirectionComBox.insets = new Insets(0, 0, 5, 5);
    gbc_DirectionComBox.anchor = GridBagConstraints.NORTH;
    gbc_DirectionComBox.fill = GridBagConstraints.BOTH;
    gbc_DirectionComBox.gridx = 5;
    gbc_DirectionComBox.gridy = 5;
    add(DirectionComBox, gbc_DirectionComBox);

    // CVN: 19 command on
    lblCommand = new JLabel("Command (19)");
    GridBagConstraints gbc_lblCommand = new GridBagConstraints();
    gbc_lblCommand.insets = new Insets(0, 0, 5, 5);
    gbc_lblCommand.anchor = GridBagConstraints.EAST;
    gbc_lblCommand.gridx = 4;
    gbc_lblCommand.gridy = 6;
    add(lblCommand, gbc_lblCommand);
    commandComBox = new JComboBox<Commands>(Commands.values());
    commandComBox.setSelectedIndex(0);
    commandComBox.setToolTipText("Command type 0-99");
    GridBagConstraints gbc_commandComBox = new GridBagConstraints();
    gbc_commandComBox.gridwidth = 4;
    gbc_commandComBox.insets = new Insets(0, 0, 5, 5);
    gbc_commandComBox.anchor = GridBagConstraints.NORTH;
    gbc_commandComBox.fill = GridBagConstraints.BOTH;
    gbc_commandComBox.gridx = 5;
    gbc_commandComBox.gridy = 6;
    add(commandComBox, gbc_commandComBox);

    // CVN: 20 activation
    lblActivation = new JLabel("Activation (20)");
    GridBagConstraints gbc_lblActivation = new GridBagConstraints();
    gbc_lblActivation.insets = new Insets(0, 0, 5, 5);
    gbc_lblActivation.anchor = GridBagConstraints.EAST;
    gbc_lblActivation.gridx = 4;
    gbc_lblActivation.gridy = 7;
    add(lblActivation, gbc_lblActivation);
    activationTF = new NumericField(5, NumericField.DECIMAL);
    activationTF.setAllowNegative(false);
    activationTF.setText("0");
    activationTF.setToolTipText("Activation point number 0-32767");
    GridBagConstraints gbc_activationTF = new GridBagConstraints();
    gbc_activationTF.gridwidth = 4;
    gbc_activationTF.insets = new Insets(0, 0, 5, 5);
    gbc_activationTF.anchor = GridBagConstraints.NORTH;
    gbc_activationTF.fill = GridBagConstraints.BOTH;
    gbc_activationTF.gridx = 5;
    gbc_activationTF.gridy = 7;
    add(activationTF, gbc_activationTF);

    // CVN: 21 (a) latitude degrees
    lblLatitude = new JLabel("Latitude (21)");
    GridBagConstraints gbc_lblLatitude = new GridBagConstraints();
    gbc_lblLatitude.insets = new Insets(0, 0, 5, 5);
    gbc_lblLatitude.anchor = GridBagConstraints.EAST;
    gbc_lblLatitude.gridx = 4;
    gbc_lblLatitude.gridy = 8;
    add(lblLatitude, gbc_lblLatitude);
    latDegTF = new NumericField(2, NumericField.DECIMAL);
    latDegTF.setAllowNegative(false);
    latDegTF.setToolTipText("Latitude degrees 0-89");
    latDegTF.setText("0");
    latDegTF.addActionListener(new TimeVerifier(89));
    latDegTF.setInputVerifier(new TimeVerifier(89));
    GridBagConstraints gbc_latDegTF = new GridBagConstraints();
    gbc_latDegTF.insets = new Insets(0, 0, 5, 5);
    gbc_latDegTF.anchor = GridBagConstraints.NORTH;
    gbc_latDegTF.fill = GridBagConstraints.BOTH;
    gbc_latDegTF.gridx = 5;
    gbc_latDegTF.gridy = 8;
    add(latDegTF, gbc_latDegTF);

    // CVN: 21 (b) latitude minutes
    latMinTF = new NumericField(2, NumericField.DECIMAL);
    latMinTF.setAllowNegative(false);
    latMinTF.setToolTipText("Latitude minutes 0-59");
    latMinTF.setText("0");
    latMinTF.addActionListener(new TimeVerifier(59));
    latMinTF.setInputVerifier(new TimeVerifier(59));
    GridBagConstraints gbc_latMinTF = new GridBagConstraints();
    gbc_latMinTF.insets = new Insets(0, 0, 5, 5);
    gbc_latMinTF.anchor = GridBagConstraints.NORTH;
    gbc_latMinTF.fill = GridBagConstraints.BOTH;
    gbc_latMinTF.gridx = 6;
    gbc_latMinTF.gridy = 8;
    add(latMinTF, gbc_latMinTF);

    // CVN: 21 (c) latitude seconds
    latSecTF = new NumericField(2, NumericField.DECIMAL);
    latSecTF.setAllowNegative(false);
    latSecTF.setToolTipText("Latitude seconds 0-59");
    latSecTF.setText("0");
    latSecTF.addActionListener(new TimeVerifier(59));
    latSecTF.setInputVerifier(new TimeVerifier(59));
    GridBagConstraints gbc_latSecTF = new GridBagConstraints();
    gbc_latSecTF.insets = new Insets(0, 0, 5, 5);
    gbc_latSecTF.anchor = GridBagConstraints.NORTH;
    gbc_latSecTF.fill = GridBagConstraints.BOTH;
    gbc_latSecTF.gridx = 7;
    gbc_latSecTF.gridy = 8;
    add(latSecTF, gbc_latSecTF);

    // CVN: 21 (d) latitude 100th seconds
    latSSecTF = new NumericField(2, NumericField.DECIMAL);
    latSSecTF.setAllowNegative(false);
    latSSecTF.setToolTipText("100th Seconds 0-99");
    latSSecTF.setText("0");
    GridBagConstraints gbc_latSSecTF = new GridBagConstraints();
    gbc_latSSecTF.insets = new Insets(0, 0, 5, 5);
    gbc_latSSecTF.anchor = GridBagConstraints.NORTH;
    gbc_latSSecTF.fill = GridBagConstraints.BOTH;
    gbc_latSSecTF.gridx = 8;
    gbc_latSSecTF.gridy = 8;
    add(latSSecTF, gbc_latSSecTF);

    // CVN: 21 (e) longitude degrees
    lblLongitude = new JLabel("Longitude (21)");
    GridBagConstraints gbc_lblLongitude = new GridBagConstraints();
    gbc_lblLongitude.insets = new Insets(0, 0, 5, 5);
    gbc_lblLongitude.anchor = GridBagConstraints.EAST;
    gbc_lblLongitude.gridx = 4;
    gbc_lblLongitude.gridy = 9;
    add(lblLongitude, gbc_lblLongitude);
    longDegTF = new NumericField(3, NumericField.DECIMAL);
    longDegTF.setAllowNegative(false);
    longDegTF.setToolTipText("Longitude degrees 0-179");
    longDegTF.setText("0");
    longDegTF.addActionListener(new TimeVerifier(179));
    longDegTF.setInputVerifier(new TimeVerifier(179));
    GridBagConstraints gbc_longDegTF = new GridBagConstraints();
    gbc_longDegTF.insets = new Insets(0, 0, 5, 5);
    gbc_longDegTF.anchor = GridBagConstraints.NORTH;
    gbc_longDegTF.fill = GridBagConstraints.BOTH;
    gbc_longDegTF.gridx = 5;
    gbc_longDegTF.gridy = 9;
    add(longDegTF, gbc_longDegTF);

    // CVN: 21 (f) longitude minutes
    longMinTF = new NumericField(2, NumericField.DECIMAL);
    longMinTF.setAllowNegative(false);
    longMinTF.setToolTipText("Longitude minutes 0-59");
    longMinTF.setText("0");
    longMinTF.addActionListener(new TimeVerifier(59));
    longMinTF.setInputVerifier(new TimeVerifier(59));
    GridBagConstraints gbc_longMinTF = new GridBagConstraints();
    gbc_longMinTF.insets = new Insets(0, 0, 5, 5);
    gbc_longMinTF.anchor = GridBagConstraints.NORTH;
    gbc_longMinTF.fill = GridBagConstraints.BOTH;
    gbc_longMinTF.gridx = 6;
    gbc_longMinTF.gridy = 9;
    add(longMinTF, gbc_longMinTF);

    // CVN: 21 (g) longitude seconds
    longSecTF = new NumericField(2, NumericField.DECIMAL);
    longSecTF.setAllowNegative(false);
    longSecTF.setToolTipText("Longitude seconds 0-59");
    longSecTF.setText("0");
    longSecTF.addActionListener(new TimeVerifier(59));
    longSecTF.setInputVerifier(new TimeVerifier(59));
    GridBagConstraints gbc_longSecTF = new GridBagConstraints();
    gbc_longSecTF.insets = new Insets(0, 0, 5, 5);
    gbc_longSecTF.anchor = GridBagConstraints.NORTH;
    gbc_longSecTF.fill = GridBagConstraints.BOTH;
    gbc_longSecTF.gridx = 7;
    gbc_longSecTF.gridy = 9;
    add(longSecTF, gbc_longSecTF);

    // CVN: 21 (h) longitude 100th seconds
    longSSecTF = new NumericField(2, NumericField.DECIMAL);
    longSSecTF.setAllowNegative(false);
    longSSecTF.setToolTipText("Longitude 100th seconds 0-99");
    longSSecTF.setText("0");
    GridBagConstraints gbc_longSSecTF = new GridBagConstraints();
    gbc_longSSecTF.insets = new Insets(0, 0, 5, 5);
    gbc_longSSecTF.anchor = GridBagConstraints.NORTH;
    gbc_longSSecTF.fill = GridBagConstraints.BOTH;
    gbc_longSSecTF.gridx = 8;
    gbc_longSSecTF.gridy = 9;
    add(longSSecTF, gbc_longSSecTF);

    // CVN: 22 (a) year
    lblDate = new JLabel("Date (22)");
    GridBagConstraints gbc_lblDate = new GridBagConstraints();
    gbc_lblDate.insets = new Insets(0, 0, 5, 5);
    gbc_lblDate.anchor = GridBagConstraints.EAST;
    gbc_lblDate.gridx = 4;
    gbc_lblDate.gridy = 10;
    add(lblDate, gbc_lblDate);
    yearTF = new NumericField(4, NumericField.DECIMAL);
    yearTF.setAllowNegative(false);
    yearTF.setToolTipText("Year 0-9999");
    yearTF.setText(Integer.toString(calendar.get(Calendar.YEAR)));
    GridBagConstraints gbc_yearTF = new GridBagConstraints();
    gbc_yearTF.gridwidth = 2;
    gbc_yearTF.insets = new Insets(0, 0, 5, 5);
    gbc_yearTF.anchor = GridBagConstraints.NORTH;
    gbc_yearTF.fill = GridBagConstraints.BOTH;
    gbc_yearTF.gridx = 5;
    gbc_yearTF.gridy = 10;
    add(yearTF, gbc_yearTF);

    // CVN: 22 (b) month
    monthTF = new NumericField(2, NumericField.DECIMAL);
    monthTF.setAllowNegative(false);
    monthTF.setToolTipText("Month 1-12");
    monthTF.setText(Integer.toString(calendar.get(Calendar.MONTH)));
    monthTF.addActionListener(new TimeVerifier(12));
    monthTF.setInputVerifier(new TimeVerifier(12));
    GridBagConstraints gbc_monthTF = new GridBagConstraints();
    gbc_monthTF.insets = new Insets(0, 0, 5, 5);
    gbc_monthTF.anchor = GridBagConstraints.NORTH;
    gbc_monthTF.fill = GridBagConstraints.BOTH;
    gbc_monthTF.gridx = 7;
    gbc_monthTF.gridy = 10;
    add(monthTF, gbc_monthTF);

    // CVN: 22 (c) day
    dayTF = new NumericField(2, NumericField.DECIMAL);
    dayTF.setAllowNegative(false);
    dayTF.setToolTipText("Day of the month 1-31");
    dayTF.setText(Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)));
    dayTF.addActionListener(new TimeVerifier(31));
    dayTF.setInputVerifier(new TimeVerifier(31));
    GridBagConstraints gbc_dayTF = new GridBagConstraints();
    gbc_dayTF.insets = new Insets(0, 0, 5, 5);
    gbc_dayTF.anchor = GridBagConstraints.NORTH;
    gbc_dayTF.fill = GridBagConstraints.BOTH;
    gbc_dayTF.gridx = 8;
    gbc_dayTF.gridy = 10;
    add(dayTF, gbc_dayTF);

    // CVN: 22 (d) hour
    lblTime = new JLabel("Time (22)");
    GridBagConstraints gbc_lblTime = new GridBagConstraints();
    gbc_lblTime.insets = new Insets(0, 0, 5, 5);
    gbc_lblTime.anchor = GridBagConstraints.EAST;
    gbc_lblTime.gridx = 4;
    gbc_lblTime.gridy = 11;
    add(lblTime, gbc_lblTime);
    hourTF = new NumericField(2, NumericField.DECIMAL);
    hourTF.setAllowNegative(false);
    hourTF.setToolTipText("Hours 0-23");
    hourTF.setText(Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)));
    hourTF.addActionListener(new TimeVerifier(24));
    hourTF.setInputVerifier(new TimeVerifier(24));
    GridBagConstraints gbc_hourTF = new GridBagConstraints();
    gbc_hourTF.insets = new Insets(0, 0, 5, 5);
    gbc_hourTF.anchor = GridBagConstraints.NORTH;
    gbc_hourTF.fill = GridBagConstraints.BOTH;
    gbc_hourTF.gridx = 5;
    gbc_hourTF.gridy = 11;
    add(hourTF, gbc_hourTF);

    // CVN: 22 (d) minute
    minuteTF = new NumericField(2, NumericField.DECIMAL);
    minuteTF.setAllowNegative(false);
    minuteTF.setToolTipText("Minutes 0-59");
    minuteTF.setText(Integer.toString(calendar.get(Calendar.MINUTE)));
    minuteTF.addActionListener(new TimeVerifier(59));
    minuteTF.setInputVerifier(new TimeVerifier(59));
    GridBagConstraints gbc_minuteTF = new GridBagConstraints();
    gbc_minuteTF.insets = new Insets(0, 0, 5, 5);
    gbc_minuteTF.anchor = GridBagConstraints.NORTH;
    gbc_minuteTF.fill = GridBagConstraints.BOTH;
    gbc_minuteTF.gridx = 6;
    gbc_minuteTF.gridy = 11;
    add(minuteTF, gbc_minuteTF);

    // CVN: 22 (d) second
    secondTF = new NumericField(2, NumericField.DECIMAL);
    secondTF.setAllowNegative(false);
    secondTF.setToolTipText("Seconds 0-59");
    secondTF.setText(Integer.toString(calendar.get(Calendar.SECOND)));
    secondTF.setInputVerifier(new TimeVerifier(59));
    secondTF.addActionListener(new TimeVerifier(59));
    GridBagConstraints gbc_secondTF = new GridBagConstraints();
    gbc_secondTF.insets = new Insets(0, 0, 5, 5);
    gbc_secondTF.anchor = GridBagConstraints.NORTH;
    gbc_secondTF.fill = GridBagConstraints.BOTH;
    gbc_secondTF.gridx = 7;
    gbc_secondTF.gridy = 11;
    add(secondTF, gbc_secondTF);

    // CVN: 23 reserve1
    lblReserve_1 = new JLabel("Reserve 1 (23)");
    GridBagConstraints gbc_lblReserve_1 = new GridBagConstraints();
    gbc_lblReserve_1.insets = new Insets(0, 0, 5, 5);
    gbc_lblReserve_1.anchor = GridBagConstraints.EAST;
    gbc_lblReserve_1.gridx = 4;
    gbc_lblReserve_1.gridy = 12;
    add(lblReserve_1, gbc_lblReserve_1);
    reserve1TF = new JTextField();
    reserve1TF.setText("0");
    reserve1TF.setToolTipText("Reserved");
    GridBagConstraints gbc_reserve1TF = new GridBagConstraints();
    gbc_reserve1TF.gridwidth = 4;
    gbc_reserve1TF.insets = new Insets(0, 0, 5, 5);
    gbc_reserve1TF.anchor = GridBagConstraints.NORTH;
    gbc_reserve1TF.fill = GridBagConstraints.BOTH;
    gbc_reserve1TF.gridx = 5;
    gbc_reserve1TF.gridy = 12;
    add(reserve1TF, gbc_reserve1TF);

    // CVN: 24 reserve2
    lblReserve_2 = new JLabel("Reserve 2 (24)");
    GridBagConstraints gbc_lblReserve_2 = new GridBagConstraints();
    gbc_lblReserve_2.insets = new Insets(0, 0, 5, 5);
    gbc_lblReserve_2.anchor = GridBagConstraints.EAST;
    gbc_lblReserve_2.gridx = 4;
    gbc_lblReserve_2.gridy = 13;
    add(lblReserve_2, gbc_lblReserve_2);
    reserve2TF = new JTextField();
    reserve2TF.setText("0");
    reserve2TF.setToolTipText("Reserved");
    GridBagConstraints gbc_reserve2TF = new GridBagConstraints();
    gbc_reserve2TF.gridwidth = 4;
    gbc_reserve2TF.insets = new Insets(0, 0, 5, 5);
    gbc_reserve2TF.anchor = GridBagConstraints.NORTH;
    gbc_reserve2TF.fill = GridBagConstraints.BOTH;
    gbc_reserve2TF.gridx = 5;
    gbc_reserve2TF.gridy = 13;
    add(reserve2TF, gbc_reserve2TF);   
  } 

  //INNER CLASSES
/**
   * @brief     Corrects time related input
   * @author    Sander Kamps
   */
  public class TimeVerifier extends InputVerifier implements ActionListener {

    public TimeVerifier(int a_mod){
      super();
      mod = a_mod;
    }

    public void actionPerformed(ActionEvent e){
      int number;
      if( e.getSource() instanceof JTextField  ){
        JTextField tf = (JTextField) e.getSource();
        number = Integer.parseInt(tf.getText());
        if (number > mod){
          JOptionPane.showMessageDialog(null, "Max value is " + Integer.toString(mod), "Input error", JOptionPane.ERROR_MESSAGE);
          tf.setText(Integer.toString(mod));
        }
      } 
    }
    @Override
    public boolean verify(JComponent input) {
      int number;
      JTextField tf = (JTextField) input;
      number = Integer.parseInt(tf.getText());
      if (number <= mod)
        return true;
      else{
        JOptionPane.showMessageDialog(null, "Max value is " + Integer.toString(mod), "Input error", JOptionPane.ERROR_MESSAGE);
        tf.setText(Integer.toString(mod));
        return true;
      }
    }

    private int mod;
  }// end class TimeVerifier

  private GregorianCalendar calendar;
  @Getter private List<JCheckBox> attributeUsedCheckBoxes;
  private NumericField loopNrTF;
  private JComboBox<VehicleType> vehicleTypeComBox;
  private NumericField lineNrTF;
  private NumericField vehServiceNrTF;
  private NumericField companyNrTF;
  private NumericField vehicleIdTF;
  private NumericField signalgroupNrTF; 
  private JComboBox<ManualControl> manualControlComBox;
  private JComboBox<VehicleStatus> vehicleStatusComBox; 
  private JComboBox<PriorityClass> priorityClassComBox;
  private JComboBox<PunctualityClass> punctualityClassComBox;
  private NumericField punctualityTF;     
  private NumericField vehicleLengthTF;    
  private NumericField vehicleSpeedTF;     
  private NumericField distanceToStopTF; 
  private NumericField timeToStopTF;
  private NumericField journeyNrTF; 
  private JComboBox<JourneyType> journeyTypeComBox; 
  private NumericField routeTF;
  private JComboBox<Direction> DirectionComBox;
  private JComboBox<Commands> commandComBox;
  private NumericField activationTF; 
  private NumericField latDegTF;  
  private NumericField latMinTF;  
  private NumericField latSecTF;  
  private NumericField latSSecTF; 
  private NumericField longDegTF; 
  private NumericField longMinTF; 
  private NumericField longSecTF; 
  private NumericField longSSecTF;
  private NumericField yearTF;    
  private NumericField monthTF;   
  private NumericField dayTF;     
  private NumericField hourTF;    
  private NumericField minuteTF;  
  private NumericField secondTF;  
  private JTextField reserve1TF;
  private JTextField reserve2TF;

  private JLabel lblLoop;
  private JLabel lblVehtype;
  private JLabel lblLine;
  private JLabel lblService;
  private JLabel lblCompany;
  private JLabel lblVehId;
  private JLabel lblSignalGroup;
  private JLabel lblManualControl;
  private JLabel lblVehStatus;
  private JLabel lblPrioClass;
  private JLabel lblPunctClass;
  private JLabel lblPunctuality;
  private JLabel lblVehLength;
  private JLabel lblVehSpeed;
  private JLabel lblDistToStop;
  private JLabel lblTimeToStop;
  private JLabel lblJourneyNr;
  private JLabel lblJourneyType;
  private JLabel lblRoute;
  private JLabel lblDirection;
  private JLabel lblCommand;
  private JLabel lblActivation;
  private JLabel lblLatitude;
  private JLabel lblLongitude;
  private JLabel lblDate;
  private JLabel lblTime;
  private JLabel lblReserve_1;
  private JLabel lblReserve_2;

}// end of class



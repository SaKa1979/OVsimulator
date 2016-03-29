package view;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import view.VehicleButton.Commands;
import view.VehicleButton.Directions;
import view.VehicleButton.JourneyType;
import view.VehicleButton.PriorityClass;
import view.VehicleButton.PunctualityClass;
import view.VehicleButton.VehicleStatus;
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
import java.awt.Insets;
import javax.swing.JLabel;

public class VehicleSettingPanel extends JPanel {

  private static final long serialVersionUID = 1L;

  /**
   * Constructor for VehicleSettingPanel.
   * @param vehicle_button
   */
  public VehicleSettingPanel(VehicleButton a_vehicle_button) {
    vehicle_button = a_vehicle_button;
    setBorder(new TitledBorder(null, "Vehicle Setting", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] {50, 100, 50, 100};
    gridBagLayout.rowHeights = new int[] {};
    gridBagLayout.columnWeights = new double[]{0.0, 0.0};
    gridBagLayout.rowWeights = new double[]{0.0, 0.0};
    setLayout(gridBagLayout);
    initialize();
    //    loadAllFieldValues();
  }

  // PUBLIC METHODS

  public int getLoopNr(){
    return textFieldToInt(loopNrTF);
  }
  public VehicleTypes getVehicleType(){
    return (VehicleTypes) vehicleTypeComBox.getSelectedItem();
  }
  public int getLineNr(){
    return textFieldToInt(lineNrTF);
  }
  public int getVehServiceNr(){
    return textFieldToInt(vehServiceNrTF);
  }
  public int getCompanyNr(){
    return textFieldToInt(companyNrTF);
  }
  public int getVehicleId(){
    return textFieldToInt(vehicleIdTF);
  }
  public int getSignalGroupNr(){
    return textFieldToInt(signalGroupNrTF);
  }
  public VehicleStatus getVehicleStatus(){
    return (VehicleStatus) vehicleStatusComBox.getSelectedItem();
  }
  public PriorityClass getPriorityClass(){
    return (PriorityClass) priorityClassComBox.getSelectedItem();
  }
  public PunctualityClass getPunctualityClass(){
    return (PunctualityClass) punctualityClassComBox.getSelectedItem();
  }
  public int getPunctuality(){
    return textFieldToInt(punctualityTF);
  }
  public int getVehicleLength(){
    return textFieldToInt(vehicleLengthTF);
  }
  public int getVehicleSpeed(){
    return textFieldToInt(vehicleSpeedTF);
  }
  public int getDistanceToStop(){
    return textFieldToInt(distanceToStopTF);
  }
  public int getTimeToStop(){
    return textFieldToInt(timeToStopTF);
  }
  public int getJourneyNr(){
    return textFieldToInt(journeyNrTF);
  }
  public JourneyType getJourneyType(){
    return (JourneyType) journeyTypeComBox.getSelectedItem();
  }
  public int getRoute(){
    return textFieldToInt(routeTF);
  }
  public Commands getCommands(){
    return (Commands) commandComBox.getSelectedItem();
  }
  public int getActivation(){
    return textFieldToInt(activationTF);
  }
  public int getLatitude(){
    return textFieldToInt(latitudeTF);
  }
  public int getLongitude(){
    return textFieldToInt(longitudeTF);
  }
  public int getDate(){
    return textFieldToInt(dateTF);
  }
  public int getTime(){
    return textFieldToInt(timeTF);
  }
  public int getLatDeg(){
    return textFieldToInt(latDegTF);
  }
  public int getLatMin(){
    return textFieldToInt(latMinTF);
  }
  public int getLatSec(){
    return textFieldToInt(latSecTF);
  }
  public int getLatSSec(){
    return textFieldToInt(latSSecTF);
  }
  public int getLongDeg(){
    return textFieldToInt(longDegTF);
  }
  public int getLongMin(){
    return textFieldToInt(longMinTF);
  }
  public int getLongSec(){
    return textFieldToInt(longSecTF);
  }
  public int getLongSSec(){
    return textFieldToInt(longSSecTF);
  }
  public int getYear(){
    return textFieldToInt(yearTF);
  }
  public int getMonth(){
    return textFieldToInt(monthTF);
  }
  public int getDay(){
    return textFieldToInt(dayTF);
  }
  public int getHour(){
    return textFieldToInt(hourTF);
  }
  public int getMinute(){
    return textFieldToInt(minuteTF);
  }
  public int getSecond(){
    return textFieldToInt(secondTF);
  }
  public int getReserve1(){
    return textFieldToInt(reserve1TF);
  }
  public int getReserve2(){
    return textFieldToInt(reserve2TF);
  }
  
  // PRIVATE METHODS
  private void initialize(){

    lblLoop = new JLabel("Loop nr (1)");
    GridBagConstraints gbc_lblLoop = new GridBagConstraints();
    gbc_lblLoop.insets = new Insets(0, 0, 5, 5);
    gbc_lblLoop.anchor = GridBagConstraints.EAST;
    gbc_lblLoop.gridx = 0;
    gbc_lblLoop.gridy = 0;
    add(lblLoop, gbc_lblLoop);

    // CVN: 1 loop number 
    loopNrTF = new JTextField();
    loopNrTF.setText("0");
    GridBagConstraints gbc_loopNrTF = new GridBagConstraints();
    gbc_loopNrTF.insets = new Insets(0, 0, 5, 5);
    gbc_loopNrTF.anchor = GridBagConstraints.NORTH;
    gbc_loopNrTF.fill = GridBagConstraints.BOTH;
    gbc_loopNrTF.gridx = 1;
    gbc_loopNrTF.gridy = 0;
    add(loopNrTF, gbc_loopNrTF);

    // CVN: 2 vehicle type
    lblVehtype = new JLabel("Veh. Type (2)");
    GridBagConstraints gbc_lblVehtype = new GridBagConstraints();
    gbc_lblVehtype.insets = new Insets(0, 0, 5, 5);
    gbc_lblVehtype.anchor = GridBagConstraints.EAST;
    gbc_lblVehtype.gridx = 0;
    gbc_lblVehtype.gridy = 1;
    add(lblVehtype, gbc_lblVehtype);
    vehicleTypeComBox = new JComboBox<VehicleTypes>(VehicleTypes.values());
    vehicleTypeComBox.setSelectedIndex(0);
    GridBagConstraints gbc_vehicleTypeComBox = new GridBagConstraints();
    gbc_vehicleTypeComBox.insets = new Insets(0, 0, 5, 5);
    gbc_vehicleTypeComBox.anchor = GridBagConstraints.NORTH;
    gbc_vehicleTypeComBox.fill = GridBagConstraints.BOTH;
    gbc_vehicleTypeComBox.gridx = 1;
    gbc_vehicleTypeComBox.gridy = 1;
    add(vehicleTypeComBox, gbc_vehicleTypeComBox);

    // CVN: 3 line number
    lblLine = new JLabel("Line nr (3)");
    GridBagConstraints gbc_lblLine = new GridBagConstraints();
    gbc_lblLine.insets = new Insets(0, 0, 5, 5);
    gbc_lblLine.anchor = GridBagConstraints.EAST;
    gbc_lblLine.gridx = 0;
    gbc_lblLine.gridy = 2;
    add(lblLine, gbc_lblLine);
    lineNrTF = new JTextField();
    lineNrTF.setText("0");
    GridBagConstraints gbc_lineNrTF = new GridBagConstraints();
    gbc_lineNrTF.insets = new Insets(0, 0, 5, 5);
    gbc_lineNrTF.anchor = GridBagConstraints.NORTH;
    gbc_lineNrTF.fill = GridBagConstraints.BOTH;
    gbc_lineNrTF.gridx = 1;
    gbc_lineNrTF.gridy = 2;
    add(lineNrTF, gbc_lineNrTF);

    // CVN: 4 vehicle service number
    lblService = new JLabel("Service nr (4)");
    GridBagConstraints gbc_lblService = new GridBagConstraints();
    gbc_lblService.insets = new Insets(0, 0, 5, 5);
    gbc_lblService.anchor = GridBagConstraints.EAST;
    gbc_lblService.gridx = 0;
    gbc_lblService.gridy = 3;
    add(lblService, gbc_lblService);
    vehServiceNrTF = new JTextField();
    vehServiceNrTF.setText("0");
    GridBagConstraints gbc_vehServiceNrTF = new GridBagConstraints();
    gbc_vehServiceNrTF.insets = new Insets(0, 0, 5, 5);
    gbc_vehServiceNrTF.anchor = GridBagConstraints.NORTH;
    gbc_vehServiceNrTF.fill = GridBagConstraints.BOTH;
    gbc_vehServiceNrTF.gridx = 1;
    gbc_vehServiceNrTF.gridy = 3;
    add(vehServiceNrTF, gbc_vehServiceNrTF);

    // CVN: 5 company number
    lblCompany = new JLabel("Company nr (5)");
    GridBagConstraints gbc_lblCompany = new GridBagConstraints();
    gbc_lblCompany.insets = new Insets(0, 0, 5, 5);
    gbc_lblCompany.anchor = GridBagConstraints.EAST;
    gbc_lblCompany.gridx = 0;
    gbc_lblCompany.gridy = 4;
    add(lblCompany, gbc_lblCompany);
    companyNrTF = new JTextField();
    companyNrTF.setText("0");
    GridBagConstraints gbc_companyNrTF = new GridBagConstraints();
    gbc_companyNrTF.insets = new Insets(0, 0, 5, 5);
    gbc_companyNrTF.anchor = GridBagConstraints.NORTH;
    gbc_companyNrTF.fill = GridBagConstraints.BOTH;
    gbc_companyNrTF.gridx = 1;
    gbc_companyNrTF.gridy = 4;
    add(companyNrTF, gbc_companyNrTF);            

    // CVN: 6 vehicle ID
    lblVehId = new JLabel("Veh. id (6)");
    GridBagConstraints gbc_lblVehId = new GridBagConstraints();
    gbc_lblVehId.insets = new Insets(0, 0, 5, 5);
    gbc_lblVehId.anchor = GridBagConstraints.EAST;
    gbc_lblVehId.gridx = 0;
    gbc_lblVehId.gridy = 5;
    add(lblVehId, gbc_lblVehId);
    vehicleIdTF = new JTextField();
    vehicleIdTF.setText("0");
    GridBagConstraints gbc_vehicleIdTF = new GridBagConstraints();
    gbc_vehicleIdTF.insets = new Insets(0, 0, 5, 5);
    gbc_vehicleIdTF.anchor = GridBagConstraints.NORTH;
    gbc_vehicleIdTF.fill = GridBagConstraints.BOTH;
    gbc_vehicleIdTF.gridx = 1;
    gbc_vehicleIdTF.gridy = 5;
    add(vehicleIdTF, gbc_vehicleIdTF);

    // CVN: 7 signal group or direction
    lblSignalgroup = new JLabel("SignalGroup (7)");
    GridBagConstraints gbc_lblSignalgroup = new GridBagConstraints();
    gbc_lblSignalgroup.insets = new Insets(0, 0, 5, 5);
    gbc_lblSignalgroup.anchor = GridBagConstraints.EAST;
    gbc_lblSignalgroup.gridx = 0;
    gbc_lblSignalgroup.gridy = 6;
    add(lblSignalgroup, gbc_lblSignalgroup);
    signalGroupNrTF = new JTextField();
    signalGroupNrTF.setText("0");
    GridBagConstraints gbc_signalGroupNrTF = new GridBagConstraints();
    gbc_signalGroupNrTF.insets = new Insets(0, 0, 5, 5);
    gbc_signalGroupNrTF.anchor = GridBagConstraints.NORTH;
    gbc_signalGroupNrTF.fill = GridBagConstraints.BOTH;
    gbc_signalGroupNrTF.gridx = 1;
    gbc_signalGroupNrTF.gridy = 6;
    add(signalGroupNrTF, gbc_signalGroupNrTF);

    // CVN: 8 vehicle status
    lblVehStatus = new JLabel("Veh. Status (8)");
    GridBagConstraints gbc_lblVehStatus = new GridBagConstraints();
    gbc_lblVehStatus.insets = new Insets(0, 0, 5, 5);
    gbc_lblVehStatus.anchor = GridBagConstraints.EAST;
    gbc_lblVehStatus.gridx = 0;
    gbc_lblVehStatus.gridy = 7;
    add(lblVehStatus, gbc_lblVehStatus);
    vehicleStatusComBox = new JComboBox<VehicleStatus>(VehicleStatus.values());
    vehicleStatusComBox.setSelectedIndex(0);
    GridBagConstraints gbc_vehicleStatusComBox = new GridBagConstraints();
    gbc_vehicleStatusComBox.insets = new Insets(0, 0, 5, 5);
    gbc_vehicleStatusComBox.anchor = GridBagConstraints.NORTH;
    gbc_vehicleStatusComBox.fill = GridBagConstraints.BOTH;
    gbc_vehicleStatusComBox.gridx = 1;
    gbc_vehicleStatusComBox.gridy = 7;
    add(vehicleStatusComBox, gbc_vehicleStatusComBox);

    // CVN: 9 priority class
    lblPrioClass = new JLabel("Prio. Class (9)");
    GridBagConstraints gbc_lblPrioClass = new GridBagConstraints();
    gbc_lblPrioClass.insets = new Insets(0, 0, 5, 5);
    gbc_lblPrioClass.anchor = GridBagConstraints.EAST;
    gbc_lblPrioClass.gridx = 0;
    gbc_lblPrioClass.gridy = 8;
    add(lblPrioClass, gbc_lblPrioClass);
    priorityClassComBox = new JComboBox<PriorityClass>(PriorityClass.values());
    priorityClassComBox.setSelectedIndex(0);
    GridBagConstraints gbc_priorityClassComBox = new GridBagConstraints();
    gbc_priorityClassComBox.insets = new Insets(0, 0, 5, 5);
    gbc_priorityClassComBox.anchor = GridBagConstraints.NORTH;
    gbc_priorityClassComBox.fill = GridBagConstraints.BOTH;
    gbc_priorityClassComBox.gridx = 1;
    gbc_priorityClassComBox.gridy = 8;
    add(priorityClassComBox, gbc_priorityClassComBox);
    // CVN: 11 punctuality
    lblPunctuality = new JLabel("Punctuality (11)");
    GridBagConstraints gbc_lblPunctuality = new GridBagConstraints();
    gbc_lblPunctuality.insets = new Insets(0, 0, 5, 5);
    gbc_lblPunctuality.anchor = GridBagConstraints.EAST;
    gbc_lblPunctuality.gridx = 0;
    gbc_lblPunctuality.gridy = 10;
    add(lblPunctuality, gbc_lblPunctuality);
    punctualityTF = new JTextField();
    punctualityTF.setText("0");
    GridBagConstraints gbc_punctualityTF = new GridBagConstraints();
    gbc_punctualityTF.insets = new Insets(0, 0, 5, 5);
    gbc_punctualityTF.anchor = GridBagConstraints.NORTH;
    gbc_punctualityTF.fill = GridBagConstraints.BOTH;
    gbc_punctualityTF.gridx = 1;
    gbc_punctualityTF.gridy = 10;
    add(punctualityTF, gbc_punctualityTF);    

    // CVN: 12 vehicle length
    lblVehLength = new JLabel("Veh. Length (12)");
    GridBagConstraints gbc_lblVehLength = new GridBagConstraints();
    gbc_lblVehLength.insets = new Insets(0, 0, 5, 5);
    gbc_lblVehLength.anchor = GridBagConstraints.EAST;
    gbc_lblVehLength.gridx = 0;
    gbc_lblVehLength.gridy = 11;
    add(lblVehLength, gbc_lblVehLength);
    vehicleLengthTF = new JTextField();
    vehicleLengthTF.setText("0");
    GridBagConstraints gbc_vehicleLengthTF = new GridBagConstraints();
    gbc_vehicleLengthTF.insets = new Insets(0, 0, 5, 5);
    gbc_vehicleLengthTF.anchor = GridBagConstraints.NORTH;
    gbc_vehicleLengthTF.fill = GridBagConstraints.BOTH;
    gbc_vehicleLengthTF.gridx = 1;
    gbc_vehicleLengthTF.gridy = 11;
    add(vehicleLengthTF, gbc_vehicleLengthTF);

    // CVN: 13 vehicle speed
    lblVehSpeed = new JLabel("Veh. Speed (13)");
    GridBagConstraints gbc_lblVehSpeed = new GridBagConstraints();
    gbc_lblVehSpeed.insets = new Insets(0, 0, 0, 5);
    gbc_lblVehSpeed.anchor = GridBagConstraints.EAST;
    gbc_lblVehSpeed.gridx = 0;
    gbc_lblVehSpeed.gridy = 12;
    add(lblVehSpeed, gbc_lblVehSpeed);
    vehicleSpeedTF = new JTextField();
    vehicleSpeedTF.setText("0");
    GridBagConstraints gbc_vehicleSpeedTF = new GridBagConstraints();
    gbc_vehicleSpeedTF.insets = new Insets(0, 0, 0, 5);
    gbc_vehicleSpeedTF.anchor = GridBagConstraints.NORTH;
    gbc_vehicleSpeedTF.fill = GridBagConstraints.BOTH;
    gbc_vehicleSpeedTF.gridx = 1;
    gbc_vehicleSpeedTF.gridy = 12;
    add(vehicleSpeedTF, gbc_vehicleSpeedTF);   

    // CVN: 14 distance to stop
    lblDistToStop = new JLabel("Dist. to Stop (14)");
    GridBagConstraints gbc_lblDistToStop = new GridBagConstraints();
    gbc_lblDistToStop.insets = new Insets(0, 0, 5, 5);
    gbc_lblDistToStop.anchor = GridBagConstraints.EAST;
    gbc_lblDistToStop.gridx = 2;
    gbc_lblDistToStop.gridy = 0;
    add(lblDistToStop, gbc_lblDistToStop);
    distanceToStopTF = new JTextField();
    distanceToStopTF.setText("0");
    GridBagConstraints gbc_distanceToStopTF = new GridBagConstraints();
    gbc_distanceToStopTF.insets = new Insets(0, 0, 5, 0);
    gbc_distanceToStopTF.anchor = GridBagConstraints.NORTH;
    gbc_distanceToStopTF.fill = GridBagConstraints.BOTH;
    gbc_distanceToStopTF.gridx = 3;
    gbc_distanceToStopTF.gridy = 0;
    add(distanceToStopTF, gbc_distanceToStopTF);    

    // CVN: 15 time to stop
    lblTimeToStop = new JLabel("Time to Stop (15)");
    GridBagConstraints gbc_lblTimeToStop = new GridBagConstraints();
    gbc_lblTimeToStop.insets = new Insets(0, 0, 5, 5);
    gbc_lblTimeToStop.anchor = GridBagConstraints.EAST;
    gbc_lblTimeToStop.gridx = 2;
    gbc_lblTimeToStop.gridy = 1;
    add(lblTimeToStop, gbc_lblTimeToStop);
    timeToStopTF = new JTextField();
    timeToStopTF.setText("0");
    GridBagConstraints gbc_timeToStopTF = new GridBagConstraints();
    gbc_timeToStopTF.insets = new Insets(0, 0, 5, 0);
    gbc_timeToStopTF.anchor = GridBagConstraints.NORTH;
    gbc_timeToStopTF.fill = GridBagConstraints.BOTH;
    gbc_timeToStopTF.gridx = 3;
    gbc_timeToStopTF.gridy = 1;
    add(timeToStopTF, gbc_timeToStopTF);

    // CVN: 16 journey number
    lblJourneyNr = new JLabel("Journey nr (16)");
    GridBagConstraints gbc_lblJourneyNr = new GridBagConstraints();
    gbc_lblJourneyNr.insets = new Insets(0, 0, 5, 5);
    gbc_lblJourneyNr.anchor = GridBagConstraints.EAST;
    gbc_lblJourneyNr.gridx = 2;
    gbc_lblJourneyNr.gridy = 2;
    add(lblJourneyNr, gbc_lblJourneyNr);
    journeyNrTF = new JTextField();
    journeyNrTF.setText("0");
    GridBagConstraints gbc_journeyNrTF = new GridBagConstraints();
    gbc_journeyNrTF.insets = new Insets(0, 0, 5, 0);
    gbc_journeyNrTF.anchor = GridBagConstraints.NORTH;
    gbc_journeyNrTF.fill = GridBagConstraints.BOTH;
    gbc_journeyNrTF.gridx = 3;
    gbc_journeyNrTF.gridy = 2;
    add(journeyNrTF, gbc_journeyNrTF); 

    // CVN: 17 journey type
    lblJourneyType = new JLabel("Journey Type (17)");
    GridBagConstraints gbc_lblJourneyType = new GridBagConstraints();
    gbc_lblJourneyType.insets = new Insets(0, 0, 5, 5);
    gbc_lblJourneyType.anchor = GridBagConstraints.EAST;
    gbc_lblJourneyType.gridx = 2;
    gbc_lblJourneyType.gridy = 3;
    add(lblJourneyType, gbc_lblJourneyType);
    journeyTypeComBox = new JComboBox<JourneyType>(JourneyType.values());
    journeyTypeComBox.setSelectedIndex(0);
    GridBagConstraints gbc_journeyTypeComBox = new GridBagConstraints();
    gbc_journeyTypeComBox.insets = new Insets(0, 0, 5, 0);
    gbc_journeyTypeComBox.anchor = GridBagConstraints.NORTH;
    gbc_journeyTypeComBox.fill = GridBagConstraints.BOTH;
    gbc_journeyTypeComBox.gridx = 3;
    gbc_journeyTypeComBox.gridy = 3;
    add(journeyTypeComBox, gbc_journeyTypeComBox);

    // CVN: 18 route
    lblRoute = new JLabel("Route (18)");
    GridBagConstraints gbc_lblRoute = new GridBagConstraints();
    gbc_lblRoute.insets = new Insets(0, 0, 5, 5);
    gbc_lblRoute.anchor = GridBagConstraints.EAST;
    gbc_lblRoute.gridx = 2;
    gbc_lblRoute.gridy = 4;
    add(lblRoute, gbc_lblRoute);
    routeTF = new JTextField();
    routeTF.setText("0");
    GridBagConstraints gbc_routeTF = new GridBagConstraints();
    gbc_routeTF.insets = new Insets(0, 0, 5, 0);
    gbc_routeTF.anchor = GridBagConstraints.NORTH;
    gbc_routeTF.fill = GridBagConstraints.BOTH;
    gbc_routeTF.gridx = 3;
    gbc_routeTF.gridy = 4;
    add(routeTF, gbc_routeTF); 

    // CVN: 19 command on
    lblCommand = new JLabel("Command (19)");
    GridBagConstraints gbc_lblCommand = new GridBagConstraints();
    gbc_lblCommand.insets = new Insets(0, 0, 5, 5);
    gbc_lblCommand.anchor = GridBagConstraints.EAST;
    gbc_lblCommand.gridx = 2;
    gbc_lblCommand.gridy = 5;
    add(lblCommand, gbc_lblCommand);
    commandComBox = new JComboBox<Commands>(Commands.values());
    commandComBox.setSelectedIndex(0);
    GridBagConstraints gbc_commandComBox = new GridBagConstraints();
    gbc_commandComBox.insets = new Insets(0, 0, 5, 0);
    gbc_commandComBox.anchor = GridBagConstraints.NORTH;
    gbc_commandComBox.fill = GridBagConstraints.BOTH;
    gbc_commandComBox.gridx = 3;
    gbc_commandComBox.gridy = 5;
    add(commandComBox, gbc_commandComBox);

    // CVN: 20 activation
    lblActivation = new JLabel("Activation (20)");
    GridBagConstraints gbc_lblActivation = new GridBagConstraints();
    gbc_lblActivation.insets = new Insets(0, 0, 5, 5);
    gbc_lblActivation.anchor = GridBagConstraints.EAST;
    gbc_lblActivation.gridx = 2;
    gbc_lblActivation.gridy = 6;
    add(lblActivation, gbc_lblActivation);
    activationTF = new JTextField();
    activationTF.setText("0");
    GridBagConstraints gbc_activationTF = new GridBagConstraints();
    gbc_activationTF.insets = new Insets(0, 0, 5, 0);
    gbc_activationTF.anchor = GridBagConstraints.NORTH;
    gbc_activationTF.fill = GridBagConstraints.BOTH;
    gbc_activationTF.gridx = 3;
    gbc_activationTF.gridy = 6;
    add(activationTF, gbc_activationTF);

    // CVN: 21 (a b c d) latitude
    lblLatitude = new JLabel("Latitude (21)");
    GridBagConstraints gbc_lblLatitude = new GridBagConstraints();
    gbc_lblLatitude.insets = new Insets(0, 0, 5, 5);
    gbc_lblLatitude.anchor = GridBagConstraints.EAST;
    gbc_lblLatitude.gridx = 2;
    gbc_lblLatitude.gridy = 7;
    add(lblLatitude, gbc_lblLatitude);
    latitudeTF = new JTextField();
    latitudeTF.setText("0");
    GridBagConstraints gbc_latitudeTF = new GridBagConstraints();
    gbc_latitudeTF.insets = new Insets(0, 0, 5, 0);
    gbc_latitudeTF.anchor = GridBagConstraints.NORTH;
    gbc_latitudeTF.fill = GridBagConstraints.BOTH;
    gbc_latitudeTF.gridx = 3;
    gbc_latitudeTF.gridy = 7;
    add(latitudeTF, gbc_latitudeTF);

    // CVN: 21 (e f g h) longitude
    lblLongitude = new JLabel("Longitude (21)");
    GridBagConstraints gbc_lblLongitude = new GridBagConstraints();
    gbc_lblLongitude.insets = new Insets(0, 0, 5, 5);
    gbc_lblLongitude.anchor = GridBagConstraints.EAST;
    gbc_lblLongitude.gridx = 2;
    gbc_lblLongitude.gridy = 8;
    add(lblLongitude, gbc_lblLongitude);
    longitudeTF = new JTextField();
    longitudeTF.setText("0");
    GridBagConstraints gbc_longitudeTF = new GridBagConstraints();
    gbc_longitudeTF.insets = new Insets(0, 0, 5, 0);
    gbc_longitudeTF.anchor = GridBagConstraints.NORTH;
    gbc_longitudeTF.fill = GridBagConstraints.BOTH;
    gbc_longitudeTF.gridx = 3;
    gbc_longitudeTF.gridy = 8;
    add(longitudeTF, gbc_longitudeTF);

    // CVN: 22 (a b c) date
    lblDate = new JLabel("Date (22)");
    GridBagConstraints gbc_lblDate = new GridBagConstraints();
    gbc_lblDate.insets = new Insets(0, 0, 5, 5);
    gbc_lblDate.anchor = GridBagConstraints.EAST;
    gbc_lblDate.gridx = 2;
    gbc_lblDate.gridy = 9;
    add(lblDate, gbc_lblDate);
    dateTF = new JTextField();
    dateTF.setText("0");
    GridBagConstraints gbc_dateTF = new GridBagConstraints();
    gbc_dateTF.insets = new Insets(0, 0, 5, 0);
    gbc_dateTF.anchor = GridBagConstraints.NORTH;
    gbc_dateTF.fill = GridBagConstraints.BOTH;
    gbc_dateTF.gridx = 3;
    gbc_dateTF.gridy = 9;
    add(dateTF, gbc_dateTF);

    // CVN: 10 punctuality class
    lblPunctClass = new JLabel("Punct. Class (10)");
    GridBagConstraints gbc_lblPunctClass = new GridBagConstraints();
    gbc_lblPunctClass.insets = new Insets(0, 0, 5, 5);
    gbc_lblPunctClass.anchor = GridBagConstraints.EAST;
    gbc_lblPunctClass.gridx = 0;
    gbc_lblPunctClass.gridy = 9;
    add(lblPunctClass, gbc_lblPunctClass);
    punctualityClassComBox = new JComboBox<PunctualityClass>(PunctualityClass.values());
    punctualityClassComBox.setSelectedIndex(0);
    GridBagConstraints gbc_punctualityClassComBox = new GridBagConstraints();
    gbc_punctualityClassComBox.insets = new Insets(0, 0, 5, 5);
    gbc_punctualityClassComBox.anchor = GridBagConstraints.NORTH;
    gbc_punctualityClassComBox.fill = GridBagConstraints.BOTH;
    gbc_punctualityClassComBox.gridx = 1;
    gbc_punctualityClassComBox.gridy = 9;
    add(punctualityClassComBox, gbc_punctualityClassComBox);

    // CVN: 22 (d e f) date
    lblTime = new JLabel("Time (22)");
    GridBagConstraints gbc_lblTime = new GridBagConstraints();
    gbc_lblTime.insets = new Insets(0, 0, 5, 5);
    gbc_lblTime.anchor = GridBagConstraints.EAST;
    gbc_lblTime.gridx = 2;
    gbc_lblTime.gridy = 10;
    add(lblTime, gbc_lblTime);
    timeTF = new JTextField();
    timeTF.setText("0");
    GridBagConstraints gbc_timeTF = new GridBagConstraints();
    gbc_timeTF.insets = new Insets(0, 0, 5, 0);
    gbc_timeTF.anchor = GridBagConstraints.NORTH;
    gbc_timeTF.fill = GridBagConstraints.BOTH;
    gbc_timeTF.gridx = 3;
    gbc_timeTF.gridy = 10;
    add(timeTF, gbc_timeTF);

    // CVN: 23 reserve1
    lblReserve_1 = new JLabel("Reserve 1");
    GridBagConstraints gbc_lblReserve_1 = new GridBagConstraints();
    gbc_lblReserve_1.insets = new Insets(0, 0, 5, 5);
    gbc_lblReserve_1.anchor = GridBagConstraints.EAST;
    gbc_lblReserve_1.gridx = 2;
    gbc_lblReserve_1.gridy = 11;
    add(lblReserve_1, gbc_lblReserve_1);
    reserve1TF = new JTextField();
    reserve1TF.setText("0");
    GridBagConstraints gbc_reserve1TF = new GridBagConstraints();
    gbc_reserve1TF.insets = new Insets(0, 0, 5, 0);
    gbc_reserve1TF.anchor = GridBagConstraints.NORTH;
    gbc_reserve1TF.fill = GridBagConstraints.BOTH;
    gbc_reserve1TF.gridx = 3;
    gbc_reserve1TF.gridy = 11;
    add(reserve1TF, gbc_reserve1TF);

    // CVN: 24 reserve2
    lblReserve_2 = new JLabel("Reserve 2");
    GridBagConstraints gbc_lblReserve_2 = new GridBagConstraints();
    gbc_lblReserve_2.insets = new Insets(0, 0, 0, 5);
    gbc_lblReserve_2.anchor = GridBagConstraints.EAST;
    gbc_lblReserve_2.gridx = 2;
    gbc_lblReserve_2.gridy = 12;
    add(lblReserve_2, gbc_lblReserve_2);
    reserve2TF = new JTextField();
    reserve2TF.setText("0");
    GridBagConstraints gbc_reserve2TF = new GridBagConstraints();
    gbc_reserve2TF.anchor = GridBagConstraints.NORTH;
    gbc_reserve2TF.fill = GridBagConstraints.BOTH;
    gbc_reserve2TF.gridx = 3;
    gbc_reserve2TF.gridy = 12;
    add(reserve2TF, gbc_reserve2TF);
  } 


  /**
   * @brief  reads the value entered into the field and returns an positive int 
   *         when the textfield is enabled
   * @throws NumberFormatException
   * @param  tf JTextField
   * @return converted numeric value
   */
  private int textFieldToInt(JTextField tf){
    int number = 0;
    if (!tf.isEnabled()) 
      return -1;
    try{
      number = Integer.parseInt(tf.getText());
    }catch(NumberFormatException nfe){
      JOptionPane.showMessageDialog(null, nfe.toString(), "Input error", JOptionPane.ERROR_MESSAGE);
      number = 0;
      tf.setText("0");
    }
    return number;
  }

  // PRIVATE ATTRIBUTES
  private VehicleButton vehicle_button;

  private JTextField loopNrTF;
  private JComboBox<VehicleTypes> vehicleTypeComBox;
  private JTextField lineNrTF;
  private JTextField vehServiceNrTF;
  private JTextField companyNrTF;
  private JTextField vehicleIdTF;
  private JTextField signalGroupNrTF;  
  private JComboBox<VehicleStatus> vehicleStatusComBox; 
  private JComboBox<PriorityClass> priorityClassComBox;
  private JComboBox<PunctualityClass> punctualityClassComBox; 
  private JTextField punctualityTF;     
  private JTextField vehicleLengthTF;    
  private JTextField vehicleSpeedTF;     
  private JTextField distanceToStopTF; 
  private JTextField timeToStopTF;
  private JTextField journeyNrTF; 
  private JComboBox<JourneyType> journeyTypeComBox; 
  private JTextField routeTF; 
  private JComboBox<Commands> commandComBox;
  private JTextField activationTF;
  private JTextField latitudeTF; 
  private JTextField longitudeTF; 
  private JTextField dateTF;
  private JTextField timeTF;
  private JTextField latDegTF;  
  private JTextField latMinTF;  
  private JTextField latSecTF;  
  private JTextField latSSecTF; 
  private JTextField longDegTF; 
  private JTextField longMinTF; 
  private JTextField longSecTF; 
  private JTextField longSSecTF;
  private JTextField yearTF;    
  private JTextField monthTF;   
  private JTextField dayTF;     
  private JTextField hourTF;    
  private JTextField minuteTF;  
  private JTextField secondTF;  
  private JTextField reserve1TF;
  private JTextField reserve2TF;
  
  private JLabel lblLoop;
  private JLabel lblVehtype;
  private JLabel lblLine;
  private JLabel lblService;
  private JLabel lblCompany;
  private JLabel lblVehId;
  private JLabel lblSignalgroup;
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
  private JLabel lblCommand;
  private JLabel lblActivation;
  private JLabel lblLatitude;
  private JLabel lblLongitude;
  private JLabel lblDate;
  private JLabel lblTime;
  private JLabel lblReserve_1;
  private JLabel lblReserve_2;

}// end of class




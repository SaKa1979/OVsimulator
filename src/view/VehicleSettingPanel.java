package view;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import view.ProtocolPanel.Proto;
import view.VehicleButton.Commands;
import view.VehicleButton.Direction;
import view.VehicleButton.ManualControl;
import view.VehicleButton.JourneyType;
import view.VehicleButton.PriorityClass;
import view.VehicleButton.PunctualityClass;
import view.VehicleButton.VehicleStatus;
import view.VehicleButton.VehicleType;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
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
    gridBagLayout.columnWidths = new int[] {50, 100, 50, 25, 25, 25, 25};
    gridBagLayout.rowHeights = new int[] {};
    gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    gridBagLayout.rowWeights = new double[]{0.0, 0.0};
    setLayout(gridBagLayout);
    initialize();
  }

  // PUBLIC METHODS
  public void setProto() {
    Proto proto = ViewManager.getInstance().getProtocolPanel().getSelectedProto();
    switch(proto){
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
        timeToStopTF.setEnabled(true);
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
  public int getLoopNr(){
    return textFieldToInt(loopNrTF);
  }
  public VehicleType getVehicleType(){
    return (VehicleType) vehicleTypeComBox.getSelectedItem();
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
  public ManualControl getManualControl(){
    return (ManualControl) manualControlComBox.getSelectedItem();
  }
  public Direction getDirection(){
    return (Direction) DirectionComBox.getSelectedItem();
  }
  public int getSignalGroupNr(){
    return textFieldToInt(signalgroupNrTF);
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

    // CVN: 1 loop number
    lblLoop = new JLabel("Loop nr (1)");
    GridBagConstraints gbc_lblLoop = new GridBagConstraints();
    gbc_lblLoop.insets = new Insets(0, 0, 5, 5);
    gbc_lblLoop.anchor = GridBagConstraints.EAST;
    gbc_lblLoop.gridx = 0;
    gbc_lblLoop.gridy = 0;
    add(lblLoop, gbc_lblLoop);
    loopNrTF = new JTextField();
    loopNrTF.setDocument(new LengthRestrictedDocument(3));
    loopNrTF.setText("0");
    loopNrTF.setToolTipText("Loop 0-127");
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
    vehicleTypeComBox = new JComboBox<VehicleType>(VehicleType.values());
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
    lineNrTF.setDocument(new LengthRestrictedDocument(4));
    lineNrTF.setText("0");
    lineNrTF.setToolTipText("Line number 0-9999");
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
    vehServiceNrTF.setDocument(new LengthRestrictedDocument(4));
    vehServiceNrTF.setText("0");
    vehServiceNrTF.setToolTipText("Vehicle servie number. Also known as Block number 0-9999");
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
    companyNrTF.setDocument(new LengthRestrictedDocument(3));
    companyNrTF.setText("0");
    companyNrTF.setToolTipText("Company number 0-255");
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
    vehicleIdTF.setDocument(new LengthRestrictedDocument(5));
    vehicleIdTF.setText("0");
    vehicleIdTF.setToolTipText("Vehicle ID 0-32767");
    GridBagConstraints gbc_vehicleIdTF = new GridBagConstraints();
    gbc_vehicleIdTF.insets = new Insets(0, 0, 5, 5);
    gbc_vehicleIdTF.anchor = GridBagConstraints.NORTH;
    gbc_vehicleIdTF.fill = GridBagConstraints.BOTH;
    gbc_vehicleIdTF.gridx = 1;
    gbc_vehicleIdTF.gridy = 5;
    add(vehicleIdTF, gbc_vehicleIdTF);

    // CVN: 7 signal group KAR
    lblSignalGroup = new JLabel("SignalGroup (7)");
    GridBagConstraints gbc_lblSignalgroup = new GridBagConstraints();
    gbc_lblSignalgroup.insets = new Insets(0, 0, 5, 5);
    gbc_lblSignalgroup.anchor = GridBagConstraints.EAST;
    gbc_lblSignalgroup.gridx = 0;
    gbc_lblSignalgroup.gridy = 6;
    add(lblSignalGroup, gbc_lblSignalgroup);
    signalgroupNrTF = new JTextField();
    signalgroupNrTF.setDocument(new LengthRestrictedDocument(3));
    signalgroupNrTF.setText("0");
    signalgroupNrTF.setToolTipText("Direction for KAR 0-255");
    GridBagConstraints gbc_signalGroupNrTF = new GridBagConstraints();
    gbc_signalGroupNrTF.insets = new Insets(0, 0, 5, 5);
    gbc_signalGroupNrTF.anchor = GridBagConstraints.NORTH;
    gbc_signalGroupNrTF.fill = GridBagConstraints.BOTH;
    gbc_signalGroupNrTF.gridx = 1;
    gbc_signalGroupNrTF.gridy = 6;
    add(signalgroupNrTF, gbc_signalGroupNrTF);

    // CVN: 7 manual control VECOM
    lblManualControl = new JLabel("Manual control(7)");
    GridBagConstraints gbc_lblManualControl = new GridBagConstraints();
    gbc_lblManualControl.insets = new Insets(0, 0, 5, 5);
    gbc_lblManualControl.anchor = GridBagConstraints.EAST;
    gbc_lblManualControl.gridx = 0;
    gbc_lblManualControl.gridy = 7;
    add(lblManualControl, gbc_lblManualControl);
    manualControlComBox = new JComboBox<ManualControl>(ManualControl.values());
    manualControlComBox.setSelectedIndex(0);
    manualControlComBox.setToolTipText("Manual control number for Vecom");
    GridBagConstraints gbc_ManualControlComBox = new GridBagConstraints();
    gbc_ManualControlComBox.insets = new Insets(0, 0, 5, 5);
    gbc_ManualControlComBox.anchor = GridBagConstraints.NORTH;
    gbc_ManualControlComBox.fill = GridBagConstraints.BOTH;
    gbc_ManualControlComBox.gridx = 1;
    gbc_ManualControlComBox.gridy = 7;
    add(manualControlComBox, gbc_ManualControlComBox);

    // CVN: 8 vehicle status
    lblVehStatus = new JLabel("Veh. Status (8)");
    GridBagConstraints gbc_lblVehStatus = new GridBagConstraints();
    gbc_lblVehStatus.insets = new Insets(0, 0, 5, 5);
    gbc_lblVehStatus.anchor = GridBagConstraints.EAST;
    gbc_lblVehStatus.gridx = 0;
    gbc_lblVehStatus.gridy = 8;
    add(lblVehStatus, gbc_lblVehStatus);
    vehicleStatusComBox = new JComboBox<VehicleStatus>(VehicleStatus.values());
    vehicleStatusComBox.setSelectedIndex(0);
    GridBagConstraints gbc_vehicleStatusComBox = new GridBagConstraints();
    gbc_vehicleStatusComBox.insets = new Insets(0, 0, 5, 5);
    gbc_vehicleStatusComBox.anchor = GridBagConstraints.NORTH;
    gbc_vehicleStatusComBox.fill = GridBagConstraints.BOTH;
    gbc_vehicleStatusComBox.gridx = 1;
    gbc_vehicleStatusComBox.gridy = 8;
    add(vehicleStatusComBox, gbc_vehicleStatusComBox);

    // CVN: 9 priority class
    lblPrioClass = new JLabel("Prio. Class (9)");
    GridBagConstraints gbc_lblPrioClass = new GridBagConstraints();
    gbc_lblPrioClass.insets = new Insets(0, 0, 5, 5);
    gbc_lblPrioClass.anchor = GridBagConstraints.EAST;
    gbc_lblPrioClass.gridx = 0;
    gbc_lblPrioClass.gridy = 9;
    add(lblPrioClass, gbc_lblPrioClass);
    priorityClassComBox = new JComboBox<PriorityClass>(PriorityClass.values());
    priorityClassComBox.setSelectedIndex(0);
    GridBagConstraints gbc_priorityClassComBox = new GridBagConstraints();
    gbc_priorityClassComBox.insets = new Insets(0, 0, 5, 5);
    gbc_priorityClassComBox.anchor = GridBagConstraints.NORTH;
    gbc_priorityClassComBox.fill = GridBagConstraints.BOTH;
    gbc_priorityClassComBox.gridx = 1;
    gbc_priorityClassComBox.gridy = 9;
    add(priorityClassComBox, gbc_priorityClassComBox);

    // CVN: 10 punctuality class
    lblPunctClass = new JLabel("Punct. Class (10)");
    GridBagConstraints gbc_lblPunctClass = new GridBagConstraints();
    gbc_lblPunctClass.insets = new Insets(0, 0, 5, 5);
    gbc_lblPunctClass.anchor = GridBagConstraints.EAST;
    gbc_lblPunctClass.gridx = 0;
    gbc_lblPunctClass.gridy = 10;
    add(lblPunctClass, gbc_lblPunctClass);
    punctualityClassComBox = new JComboBox<PunctualityClass>(PunctualityClass.values());
    punctualityClassComBox.setSelectedIndex(0);
    GridBagConstraints gbc_punctualityClassComBox = new GridBagConstraints();
    gbc_punctualityClassComBox.insets = new Insets(0, 0, 5, 5);
    gbc_punctualityClassComBox.anchor = GridBagConstraints.NORTH;
    gbc_punctualityClassComBox.fill = GridBagConstraints.BOTH;
    gbc_punctualityClassComBox.gridx = 1;
    gbc_punctualityClassComBox.gridy = 10;
    add(punctualityClassComBox, gbc_punctualityClassComBox);

    // CVN: 11 punctuality
    lblPunctuality = new JLabel("Punctuality (11)");
    GridBagConstraints gbc_lblPunctuality = new GridBagConstraints();
    gbc_lblPunctuality.insets = new Insets(0, 0, 5, 5);
    gbc_lblPunctuality.anchor = GridBagConstraints.EAST;
    gbc_lblPunctuality.gridx = 0;
    gbc_lblPunctuality.gridy = 11;
    add(lblPunctuality, gbc_lblPunctuality);
    punctualityTF = new JTextField();
    punctualityTF.setDocument(new LengthRestrictedDocument(2));
    punctualityTF.setText("0");
    punctualityTF.setToolTipText("Punctuality 0-99 [s]");
    GridBagConstraints gbc_punctualityTF = new GridBagConstraints();
    gbc_punctualityTF.insets = new Insets(0, 0, 5, 5);
    gbc_punctualityTF.anchor = GridBagConstraints.NORTH;
    gbc_punctualityTF.fill = GridBagConstraints.BOTH;
    gbc_punctualityTF.gridx = 1;
    gbc_punctualityTF.gridy = 11;
    add(punctualityTF, gbc_punctualityTF);    

    // CVN: 12 vehicle length
    lblVehLength = new JLabel("Veh. Length (12)");
    GridBagConstraints gbc_lblVehLength = new GridBagConstraints();
    gbc_lblVehLength.insets = new Insets(0, 0, 5, 5);
    gbc_lblVehLength.anchor = GridBagConstraints.EAST;
    gbc_lblVehLength.gridx = 0;
    gbc_lblVehLength.gridy = 12;
    add(lblVehLength, gbc_lblVehLength);
    vehicleLengthTF = new JTextField();
    vehicleLengthTF.setDocument(new LengthRestrictedDocument(3));
    vehicleLengthTF.setText("0");
    vehicleLengthTF.setToolTipText("Vehicle length 0-255 [m]");
    GridBagConstraints gbc_vehicleLengthTF = new GridBagConstraints();
    gbc_vehicleLengthTF.insets = new Insets(0, 0, 5, 5);
    gbc_vehicleLengthTF.anchor = GridBagConstraints.NORTH;
    gbc_vehicleLengthTF.fill = GridBagConstraints.BOTH;
    gbc_vehicleLengthTF.gridx = 1;
    gbc_vehicleLengthTF.gridy = 12;
    add(vehicleLengthTF, gbc_vehicleLengthTF);

    // CVN: 13 vehicle speed
    lblVehSpeed = new JLabel("Veh. Speed (13)");
    GridBagConstraints gbc_lblVehSpeed = new GridBagConstraints();
    gbc_lblVehSpeed.insets = new Insets(0, 0, 5, 5);
    gbc_lblVehSpeed.anchor = GridBagConstraints.EAST;
    gbc_lblVehSpeed.gridx = 0;
    gbc_lblVehSpeed.gridy = 13;
    add(lblVehSpeed, gbc_lblVehSpeed);
    vehicleSpeedTF = new JTextField();
    vehicleSpeedTF.setDocument(new LengthRestrictedDocument(2));
    vehicleSpeedTF.setText("0");
    vehicleSpeedTF.setToolTipText("Vehicle speed 0-99 [m/s]");
    GridBagConstraints gbc_vehicleSpeedTF = new GridBagConstraints();
    gbc_vehicleSpeedTF.insets = new Insets(0, 0, 5, 5);
    gbc_vehicleSpeedTF.anchor = GridBagConstraints.NORTH;
    gbc_vehicleSpeedTF.fill = GridBagConstraints.BOTH;
    gbc_vehicleSpeedTF.gridx = 1;
    gbc_vehicleSpeedTF.gridy = 13;
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
    distanceToStopTF.setDocument(new LengthRestrictedDocument(4));
    distanceToStopTF.setText("0");
    distanceToStopTF.setToolTipText("Distance to passage stop mark -99-9999 [m]");
    GridBagConstraints gbc_distanceToStopTF = new GridBagConstraints();
    gbc_distanceToStopTF.gridwidth = 4;
    gbc_distanceToStopTF.insets = new Insets(0, 0, 5, 5);
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
    timeToStopTF.setDocument(new LengthRestrictedDocument(3));
    timeToStopTF.setText("0");
    timeToStopTF.setToolTipText("Time to passage stop mark 0-255 [s]");
    GridBagConstraints gbc_timeToStopTF = new GridBagConstraints();
    gbc_timeToStopTF.gridwidth = 4;
    gbc_timeToStopTF.insets = new Insets(0, 0, 5, 5);
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
    journeyNrTF.setDocument(new LengthRestrictedDocument(4));
    journeyNrTF.setText("0");
    journeyNrTF.setToolTipText("Journey number 0-9999");
    GridBagConstraints gbc_journeyNrTF = new GridBagConstraints();
    gbc_journeyNrTF.gridwidth = 4;
    gbc_journeyNrTF.insets = new Insets(0, 0, 5, 5);
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
    gbc_journeyTypeComBox.gridwidth = 4;
    gbc_journeyTypeComBox.insets = new Insets(0, 0, 5, 5);
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
    routeTF.setDocument(new LengthRestrictedDocument(2));
    routeTF.setText("0");
    routeTF.setToolTipText("Route public transport 0-99");
    GridBagConstraints gbc_routeTF = new GridBagConstraints();
    gbc_routeTF.gridwidth = 4;
    gbc_routeTF.insets = new Insets(0, 0, 5, 5);
    gbc_routeTF.anchor = GridBagConstraints.NORTH;
    gbc_routeTF.fill = GridBagConstraints.BOTH;
    gbc_routeTF.gridx = 3;
    gbc_routeTF.gridy = 4;
    add(routeTF, gbc_routeTF);
    
    // CVN: 18 direction to route VECOM
    lblDirection = new JLabel("Direction(18)");
    GridBagConstraints gbc_lblDirection = new GridBagConstraints();
    gbc_lblDirection.insets = new Insets(0, 0, 5, 5);
    gbc_lblDirection.anchor = GridBagConstraints.EAST;
    gbc_lblDirection.gridx = 2;
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
    gbc_DirectionComBox.gridx = 3;
    gbc_DirectionComBox.gridy = 5;
    add(DirectionComBox, gbc_DirectionComBox);

    // CVN: 19 command on
    lblCommand = new JLabel("Command (19)");
    GridBagConstraints gbc_lblCommand = new GridBagConstraints();
    gbc_lblCommand.insets = new Insets(0, 0, 5, 5);
    gbc_lblCommand.anchor = GridBagConstraints.EAST;
    gbc_lblCommand.gridx = 2;
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
    gbc_commandComBox.gridx = 3;
    gbc_commandComBox.gridy = 6;
    add(commandComBox, gbc_commandComBox);

    // CVN: 20 activation
    lblActivation = new JLabel("Activation (20)");
    GridBagConstraints gbc_lblActivation = new GridBagConstraints();
    gbc_lblActivation.insets = new Insets(0, 0, 5, 5);
    gbc_lblActivation.anchor = GridBagConstraints.EAST;
    gbc_lblActivation.gridx = 2;
    gbc_lblActivation.gridy = 7;
    add(lblActivation, gbc_lblActivation);
    activationTF = new JTextField();
    activationTF.setDocument(new LengthRestrictedDocument(5));
    activationTF.setText("0");
    activationTF.setToolTipText("Activation point number 0-32767");
    GridBagConstraints gbc_activationTF = new GridBagConstraints();
    gbc_activationTF.gridwidth = 4;
    gbc_activationTF.insets = new Insets(0, 0, 5, 5);
    gbc_activationTF.anchor = GridBagConstraints.NORTH;
    gbc_activationTF.fill = GridBagConstraints.BOTH;
    gbc_activationTF.gridx = 3;
    gbc_activationTF.gridy = 7;
    add(activationTF, gbc_activationTF);

    // CVN: 21 (a) latitude degrees
    lblLatitude = new JLabel("Latitude (21)");
    GridBagConstraints gbc_lblLatitude = new GridBagConstraints();
    gbc_lblLatitude.insets = new Insets(0, 0, 5, 5);
    gbc_lblLatitude.anchor = GridBagConstraints.EAST;
    gbc_lblLatitude.gridx = 2;
    gbc_lblLatitude.gridy = 8;
    add(lblLatitude, gbc_lblLatitude);
    latDegTF = new JTextField();
    latDegTF.setDocument(new LengthRestrictedDocument(2));
    latDegTF.setToolTipText("Latitude degrees 0-89");
    latDegTF.setText("0");
    GridBagConstraints gbc_latDegTF = new GridBagConstraints();
    gbc_latDegTF.insets = new Insets(0, 0, 5, 5);
    gbc_latDegTF.anchor = GridBagConstraints.NORTH;
    gbc_latDegTF.fill = GridBagConstraints.BOTH;
    gbc_latDegTF.gridx = 3;
    gbc_latDegTF.gridy = 8;
    add(latDegTF, gbc_latDegTF);

    // CVN: 21 (b) latitude minutes
    latMinTF = new JTextField();
    latMinTF.setDocument(new LengthRestrictedDocument(2));
    latMinTF.setToolTipText("Latitude minutes 0-59");
    latMinTF.setText("0");
    GridBagConstraints gbc_latMinTF = new GridBagConstraints();
    gbc_latMinTF.insets = new Insets(0, 0, 5, 5);
    gbc_latMinTF.anchor = GridBagConstraints.NORTH;
    gbc_latMinTF.fill = GridBagConstraints.BOTH;
    gbc_latMinTF.gridx = 4;
    gbc_latMinTF.gridy = 8;
    add(latMinTF, gbc_latMinTF);

    // CVN: 21 (c) latitude seconds
    latSecTF = new JTextField();
    latSecTF.setDocument(new LengthRestrictedDocument(2));
    latSecTF.setToolTipText("Latitude seconds 0-59");
    latSecTF.setText("0");
    GridBagConstraints gbc_latSecTF = new GridBagConstraints();
    gbc_latSecTF.insets = new Insets(0, 0, 5, 5);
    gbc_latSecTF.anchor = GridBagConstraints.NORTH;
    gbc_latSecTF.fill = GridBagConstraints.BOTH;
    gbc_latSecTF.gridx = 5;
    gbc_latSecTF.gridy = 8;
    add(latSecTF, gbc_latSecTF);

    // CVN: 21 (d) latitude 100th seconds
    latSSecTF = new JTextField();
    latSSecTF.setDocument(new LengthRestrictedDocument(2));
    latSSecTF.setToolTipText("100th Seconds 0-99");
    latSSecTF.setText("0");
    GridBagConstraints gbc_latSSecTF = new GridBagConstraints();
    gbc_latSSecTF.insets = new Insets(0, 0, 5, 5);
    gbc_latSSecTF.anchor = GridBagConstraints.NORTH;
    gbc_latSSecTF.fill = GridBagConstraints.BOTH;
    gbc_latSSecTF.gridx = 6;
    gbc_latSSecTF.gridy = 8;
    add(latSSecTF, gbc_latSSecTF);

    // CVN: 21 (e) longitude degrees
    lblLongitude = new JLabel("Longitude (21)");
    GridBagConstraints gbc_lblLongitude = new GridBagConstraints();
    gbc_lblLongitude.insets = new Insets(0, 0, 5, 5);
    gbc_lblLongitude.anchor = GridBagConstraints.EAST;
    gbc_lblLongitude.gridx = 2;
    gbc_lblLongitude.gridy = 9;
    add(lblLongitude, gbc_lblLongitude);
    longDegTF = new JTextField();
    longDegTF.setDocument(new LengthRestrictedDocument(3));
    longDegTF.setToolTipText("Longitude degrees 0-179");
    longDegTF.setText("0");
    GridBagConstraints gbc_longDegTF = new GridBagConstraints();
    gbc_longDegTF.insets = new Insets(0, 0, 5, 5);
    gbc_longDegTF.anchor = GridBagConstraints.NORTH;
    gbc_longDegTF.fill = GridBagConstraints.BOTH;
    gbc_longDegTF.gridx = 3;
    gbc_longDegTF.gridy = 9;
    add(longDegTF, gbc_longDegTF);

    // CVN: 21 (f) longitude minutes
    longMinTF = new JTextField();
    longMinTF.setDocument(new LengthRestrictedDocument(2));
    longMinTF.setToolTipText("Longitude minutes 0-59");
    longMinTF.setText("0");
    GridBagConstraints gbc_longMinTF = new GridBagConstraints();
    gbc_longMinTF.insets = new Insets(0, 0, 5, 5);
    gbc_longMinTF.anchor = GridBagConstraints.NORTH;
    gbc_longMinTF.fill = GridBagConstraints.BOTH;
    gbc_longMinTF.gridx = 4;
    gbc_longMinTF.gridy = 9;
    add(longMinTF, gbc_longMinTF);

    // CVN: 21 (g) longitude seconds
    longSecTF = new JTextField();
    longSecTF.setDocument(new LengthRestrictedDocument(2));
    longSecTF.setToolTipText("Longitude seconds 0-59");
    longSecTF.setText("0");
    GridBagConstraints gbc_longSecTF = new GridBagConstraints();
    gbc_longSecTF.insets = new Insets(0, 0, 5, 5);
    gbc_longSecTF.anchor = GridBagConstraints.NORTH;
    gbc_longSecTF.fill = GridBagConstraints.BOTH;
    gbc_longSecTF.gridx = 5;
    gbc_longSecTF.gridy = 9;
    add(longSecTF, gbc_longSecTF);

    // CVN: 21 (h) longitude 100th seconds
    longSSecTF = new JTextField();
    longSSecTF.setDocument(new LengthRestrictedDocument(2));
    longSSecTF.setToolTipText("Longitude 100th seconds 0-99");
    longSSecTF.setText("0");
    GridBagConstraints gbc_longSSecTF = new GridBagConstraints();
    gbc_longSSecTF.insets = new Insets(0, 0, 5, 5);
    gbc_longSSecTF.anchor = GridBagConstraints.NORTH;
    gbc_longSSecTF.fill = GridBagConstraints.BOTH;
    gbc_longSSecTF.gridx = 6;
    gbc_longSSecTF.gridy = 9;
    add(longSSecTF, gbc_longSSecTF);

    // CVN: 22 (a) year
    lblDate = new JLabel("Date (22)");
    GridBagConstraints gbc_lblDate = new GridBagConstraints();
    gbc_lblDate.insets = new Insets(0, 0, 5, 5);
    gbc_lblDate.anchor = GridBagConstraints.EAST;
    gbc_lblDate.gridx = 2;
    gbc_lblDate.gridy = 10;
    add(lblDate, gbc_lblDate);
    yearTF = new JTextField();
    yearTF.setDocument(new LengthRestrictedDocument(4));
    yearTF.setToolTipText("Year 0-9999");
    yearTF.setText("0");
    GridBagConstraints gbc_yearTF = new GridBagConstraints();
    gbc_yearTF.gridwidth = 2;
    gbc_yearTF.insets = new Insets(0, 0, 5, 5);
    gbc_yearTF.anchor = GridBagConstraints.NORTH;
    gbc_yearTF.fill = GridBagConstraints.BOTH;
    gbc_yearTF.gridx = 3;
    gbc_yearTF.gridy = 10;
    add(yearTF, gbc_yearTF);

    // CVN: 22 (b) month
    monthTF = new JTextField();
    monthTF.setToolTipText("Month 1-12");
    monthTF.setDocument(new LengthRestrictedDocument(2));
    monthTF.setText("0");
    GridBagConstraints gbc_monthTF = new GridBagConstraints();
    gbc_monthTF.insets = new Insets(0, 0, 5, 5);
    gbc_monthTF.anchor = GridBagConstraints.NORTH;
    gbc_monthTF.fill = GridBagConstraints.BOTH;
    gbc_monthTF.gridx = 5;
    gbc_monthTF.gridy = 10;
    add(monthTF, gbc_monthTF);

    // CVN: 22 (c) day
    dayTF = new JTextField();
    dayTF.setToolTipText("Day of the month 1-31");
    dayTF.setDocument(new LengthRestrictedDocument(2));
    dayTF.setText("0");
    GridBagConstraints gbc_dayTF = new GridBagConstraints();
    gbc_dayTF.insets = new Insets(0, 0, 5, 5);
    gbc_dayTF.anchor = GridBagConstraints.NORTH;
    gbc_dayTF.fill = GridBagConstraints.BOTH;
    gbc_dayTF.gridx = 6;
    gbc_dayTF.gridy = 10;
    add(dayTF, gbc_dayTF);

    // CVN: 22 (d) hour
    lblTime = new JLabel("Time (22)");
    GridBagConstraints gbc_lblTime = new GridBagConstraints();
    gbc_lblTime.insets = new Insets(0, 0, 5, 5);
    gbc_lblTime.anchor = GridBagConstraints.EAST;
    gbc_lblTime.gridx = 2;
    gbc_lblTime.gridy = 11;
    add(lblTime, gbc_lblTime);
    hourTF = new JTextField();
    hourTF.setDocument(new LengthRestrictedDocument(2));
    hourTF.setToolTipText("Hours 0-23");
    hourTF.setText("0");
    GridBagConstraints gbc_hourTF = new GridBagConstraints();
    gbc_hourTF.insets = new Insets(0, 0, 5, 5);
    gbc_hourTF.anchor = GridBagConstraints.NORTH;
    gbc_hourTF.fill = GridBagConstraints.BOTH;
    gbc_hourTF.gridx = 3;
    gbc_hourTF.gridy = 11;
    add(hourTF, gbc_hourTF);

    // CVN: 22 (d) minute
    minuteTF = new JTextField();
    minuteTF.setDocument(new LengthRestrictedDocument(2));
    minuteTF.setToolTipText("Minutes 0-59");
    minuteTF.setText("0");
    GridBagConstraints gbc_minuteTF = new GridBagConstraints();
    gbc_minuteTF.insets = new Insets(0, 0, 5, 5);
    gbc_minuteTF.anchor = GridBagConstraints.NORTH;
    gbc_minuteTF.fill = GridBagConstraints.BOTH;
    gbc_minuteTF.gridx = 4;
    gbc_minuteTF.gridy = 11;
    add(minuteTF, gbc_minuteTF);

    // CVN: 22 (d) second
    secondTF = new JTextField();
    secondTF.setDocument(new LengthRestrictedDocument(2));
    secondTF.setToolTipText("Seconds 0-59");
    secondTF.setText("0");
    GridBagConstraints gbc_secondTF = new GridBagConstraints();
    gbc_secondTF.insets = new Insets(0, 0, 5, 5);
    gbc_secondTF.anchor = GridBagConstraints.NORTH;
    gbc_secondTF.fill = GridBagConstraints.BOTH;
    gbc_secondTF.gridx = 5;
    gbc_secondTF.gridy = 11;
    add(secondTF, gbc_secondTF);

    // CVN: 23 reserve1
    lblReserve_1 = new JLabel("Reserve 1");
    GridBagConstraints gbc_lblReserve_1 = new GridBagConstraints();
    gbc_lblReserve_1.insets = new Insets(0, 0, 5, 5);
    gbc_lblReserve_1.anchor = GridBagConstraints.EAST;
    gbc_lblReserve_1.gridx = 2;
    gbc_lblReserve_1.gridy = 12;
    add(lblReserve_1, gbc_lblReserve_1);
    reserve1TF = new JTextField();
    reserve1TF.setText("0");
    reserve1TF.setToolTipText("Whatever one");
    GridBagConstraints gbc_reserve1TF = new GridBagConstraints();
    gbc_reserve1TF.gridwidth = 4;
    gbc_reserve1TF.insets = new Insets(0, 0, 5, 5);
    gbc_reserve1TF.anchor = GridBagConstraints.NORTH;
    gbc_reserve1TF.fill = GridBagConstraints.BOTH;
    gbc_reserve1TF.gridx = 3;
    gbc_reserve1TF.gridy = 12;
    add(reserve1TF, gbc_reserve1TF);

    // CVN: 24 reserve2
    lblReserve_2 = new JLabel("Reserve 2");
    GridBagConstraints gbc_lblReserve_2 = new GridBagConstraints();
    gbc_lblReserve_2.insets = new Insets(0, 0, 5, 5);
    gbc_lblReserve_2.anchor = GridBagConstraints.EAST;
    gbc_lblReserve_2.gridx = 2;
    gbc_lblReserve_2.gridy = 13;
    add(lblReserve_2, gbc_lblReserve_2);
    reserve2TF = new JTextField();
    reserve2TF.setText("0");
    reserve2TF.setToolTipText("Whatever two");
    GridBagConstraints gbc_reserve2TF = new GridBagConstraints();
    gbc_reserve2TF.gridwidth = 4;
    gbc_reserve2TF.insets = new Insets(0, 0, 5, 5);
    gbc_reserve2TF.anchor = GridBagConstraints.NORTH;
    gbc_reserve2TF.fill = GridBagConstraints.BOTH;
    gbc_reserve2TF.gridx = 3;
    gbc_reserve2TF.gridy = 13;
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

  //INNER CLASSES
  
  // PRIVATE ATTRIBUTES
  private VehicleButton vehicle_button;

  private JTextField loopNrTF;
  private JComboBox<VehicleType> vehicleTypeComBox;
  private JTextField lineNrTF;
  private JTextField vehServiceNrTF;
  private JTextField companyNrTF;
  private JTextField vehicleIdTF;
  private JTextField signalgroupNrTF; 
  private JComboBox<ManualControl> manualControlComBox;
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
  private JComboBox<Direction> DirectionComBox;
  private JComboBox<Commands> commandComBox;
  private JTextField activationTF; 
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


/**
 * @brief Prohobits more than the limit amount of characters to be entered into
 *        a TextField
 * @author Sander
 */
  final class LengthRestrictedDocument extends PlainDocument {
  private static final long serialVersionUID = 1L;
  private final int limit;

  public LengthRestrictedDocument(int limit) {
    super();
    this.limit = limit;
  }

  public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
    if (str == null)
      return;

    if ((getLength() + str.length()) <= limit) {
      super.insertString(offs, str, a);
    }
  }
}

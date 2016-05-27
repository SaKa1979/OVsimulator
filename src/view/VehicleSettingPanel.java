package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import lombok.Getter;
import model.ProtocolMessage;
import model.kar.KarAttribute;
import model.kar.KarMessage;
import model.vecom.VecomAttribute;
import model.vecom.VecomMessage;
import view.ProtocolPanel.Proto;

public class VehicleSettingPanel extends JPanel {

  private static final long serialVersionUID = 3L;

	/**
	 * Constructor for VehicleSettingPanel.
	 * 
	 * @param vehicle_button
	 */
	public VehicleSettingPanel(Proto proto) {
		this.proto = proto;
//		calendar = new GregorianCalendar();
		setBorder(new TitledBorder(null, "Vehicle Setting for " + proto, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(5, 5));
		initialize();
	}

  // PUBLIC METHODS

	public void updateSettingsPanel(ProtocolMessage message) {
		this.protocolMessage = message;
		
  		if (proto == Proto.KAR) {
  			KarMessage karMessage = (KarMessage) message;
  			
  			int row = 0;
  			// Define two columns
  			JPanel columnEntries = new JPanel();
  			JPanel columnEntries2 = new JPanel();
  			columnEntries.setLayout(new BoxLayout(columnEntries, BoxLayout.PAGE_AXIS));
  			columnEntries2.setLayout(new BoxLayout(columnEntries2, BoxLayout.PAGE_AXIS));

  			for (KarAttribute attribute : karMessage.getKarAttributes()) {
  				KarAttributeEntry attributeEntry = new KarAttributeEntry(attribute);

  				if (row < 15) {
  					columnEntries.add(attributeEntry);
  				} else {
  					columnEntries2.add(attributeEntry);
  				}

  				row += ((KarAttribute) attribute).getKarFields().size();
  			}
  			this.add(columnEntries, BorderLayout.WEST);
  			this.add(columnEntries2, BorderLayout.EAST);
  			
  		} else if (proto == Proto.VECOM) {
  			VecomMessage vecomMessage = (VecomMessage) message;
  			
  			// Define two columns
  			JPanel columnEntries = new JPanel();
  			columnEntries.setLayout(new BoxLayout(columnEntries, BoxLayout.PAGE_AXIS));

  			for (VecomAttribute attribute : vecomMessage.getVecomAttributes()) {
  				VecomAttributeEntry attributeEntry = new VecomAttributeEntry(attribute);
				columnEntries.add(attributeEntry);
  			}
  			this.add(columnEntries, BorderLayout.CENTER);
  		} else {
  			// Let the user know a protocol should be selected.
  			return;
  		}
	}
	
  // PRIVATE METHODS
  	private void initialize(){
  		removeAll();
  		if (proto == Proto.KAR) {
  			protocolMessage = new KarMessage();
  		} else if (proto == Proto.VECOM) {
  			protocolMessage = new VecomMessage();
  		} else {
  			// Let the user know a protocol should be selected.
  			return;
  		}
  		updateSettingsPanel(protocolMessage);

//    // CVN: 1 loop number
//    lblLoop = new JLabel("Loop nr (1)");
//    createLabelGridBagConstraints(lblLoop, 0, 1);
//    loopNrTF = new NumericField(3, NumericField.DECIMAL);
//    loopNrTF.setAllowNegative(false);
//    loopNrTF.setText("0");
//    loopNrTF.setToolTipText("Loop 0-127");
//    createInputFieldGridBagConstraints(loopNrTF, 0, 2);
//
//    // CVN: 2 vehicle type
//    lblVehtype = new JLabel("Veh. Type (2)");
//    createLabelGridBagConstraints(lblVehtype, 1, 1);
//    vehicleTypeComBox = new JComboBox<VehicleType>(VehicleType.values());
//    vehicleTypeComBox.setSelectedIndex(0);
//    createInputFieldGridBagConstraints(vehicleTypeComBox, 1, 2);
//
//    // CVN: 3 line number
//    lblLine = new JLabel("Line nr (3)");
//    createLabelGridBagConstraints(lblLine, 2, 1);
//    lineNrTF = new NumericField(4, NumericField.DECIMAL);
//    lineNrTF.setAllowNegative(false);
//    lineNrTF.setText("0");
//    lineNrTF.setToolTipText("Line number 0-9999");
//    createInputFieldGridBagConstraints(lineNrTF, 2, 2);
//
//    // CVN: 4 vehicle service number
//    lblService = new JLabel("Service nr (4)");
//    createLabelGridBagConstraints(lblService, 3, 1);
//    vehServiceNrTF = new NumericField(4, NumericField.DECIMAL);
//    vehServiceNrTF.setAllowNegative(false);
//    vehServiceNrTF.setText("0");
//    vehServiceNrTF.setToolTipText("Vehicle servie number. Also known as Block number 0-9999");
//    createInputFieldGridBagConstraints(vehServiceNrTF, 3, 2);
//
//    // CVN: 5 company number
//    lblCompany = new JLabel("Company nr (5)");
//    createLabelGridBagConstraints(lblCompany, 4, 1);
//    companyNrTF = new NumericField(3, NumericField.DECIMAL);
//    companyNrTF.setAllowNegative(false);
//    companyNrTF.setText("0");
//    companyNrTF.setToolTipText("Company number 0-255");
//    createInputFieldGridBagConstraints(companyNrTF, 4, 2);
//
//    // CVN: 6 vehicle ID
//    lblVehId = new JLabel("Veh. id (6)");
//    createLabelGridBagConstraints(lblVehId, 5, 1);
//    vehicleIdTF = new NumericField(5, NumericField.DECIMAL);
//    vehicleIdTF.setAllowNegative(false);
//    vehicleIdTF.setText("0");
//    vehicleIdTF.setToolTipText("Vehicle ID 0-32767");
//    createInputFieldGridBagConstraints(vehicleIdTF, 5, 2);
//
//    // CVN: 7 signal group KAR
//    lblSignalGroup = new JLabel("SignalGroup (7)");
//    createLabelGridBagConstraints(lblSignalGroup, 6, 1);
//    signalgroupNrTF = new NumericField(3, NumericField.DECIMAL);
//    signalgroupNrTF.setAllowNegative(false);
//    signalgroupNrTF.setText("0");
//    signalgroupNrTF.setToolTipText("Direction for KAR 0-255");
//    createInputFieldGridBagConstraints(signalgroupNrTF, 6, 2);
//
//    // CVN: 7 manual control VECOM
//    lblManualControl = new JLabel("Manual control(7)");
//    GridBagConstraints gbc_lblManualControl = new GridBagConstraints();
//    gbc_lblManualControl.insets = new Insets(0, 0, 5, 5);
//    gbc_lblManualControl.anchor = GridBagConstraints.EAST;
//    gbc_lblManualControl.gridx = 1;
//    gbc_lblManualControl.gridy = 7;
//    add(lblManualControl, gbc_lblManualControl);
//    manualControlComBox = new JComboBox<ManualControl>(ManualControl.values());
//    manualControlComBox.setSelectedIndex(0);
//    manualControlComBox.setToolTipText("Manual control number for Vecom");
//    GridBagConstraints gbc_ManualControlComBox = new GridBagConstraints();
//    gbc_ManualControlComBox.insets = new Insets(0, 0, 5, 5);
//    gbc_ManualControlComBox.anchor = GridBagConstraints.NORTH;
//    gbc_ManualControlComBox.fill = GridBagConstraints.BOTH;
//    gbc_ManualControlComBox.gridx = 2;
//    gbc_ManualControlComBox.gridy = 7;
//    add(manualControlComBox, gbc_ManualControlComBox);
//
//    // CVN: 8 vehicle status
//    lblVehStatus = new JLabel("Veh. Status (8)");
//    GridBagConstraints gbc_lblVehStatus = new GridBagConstraints();
//    gbc_lblVehStatus.insets = new Insets(0, 0, 5, 5);
//    gbc_lblVehStatus.anchor = GridBagConstraints.EAST;
//    gbc_lblVehStatus.gridx = 1;
//    gbc_lblVehStatus.gridy = 8;
//    add(lblVehStatus, gbc_lblVehStatus);
//    vehicleStatusComBox = new JComboBox<VehicleStatus>(VehicleStatus.values());
//    vehicleStatusComBox.setSelectedIndex(0);
//    GridBagConstraints gbc_vehicleStatusComBox = new GridBagConstraints();
//    gbc_vehicleStatusComBox.insets = new Insets(0, 0, 5, 5);
//    gbc_vehicleStatusComBox.anchor = GridBagConstraints.NORTH;
//    gbc_vehicleStatusComBox.fill = GridBagConstraints.BOTH;
//    gbc_vehicleStatusComBox.gridx = 2;
//    gbc_vehicleStatusComBox.gridy = 8;
//    add(vehicleStatusComBox, gbc_vehicleStatusComBox);
//
//    // CVN: 9 priority class
//    lblPrioClass = new JLabel("Prio. Class (9)");
//    GridBagConstraints gbc_lblPrioClass = new GridBagConstraints();
//    gbc_lblPrioClass.insets = new Insets(0, 0, 5, 5);
//    gbc_lblPrioClass.anchor = GridBagConstraints.EAST;
//    gbc_lblPrioClass.gridx = 1;
//    gbc_lblPrioClass.gridy = 9;
//    add(lblPrioClass, gbc_lblPrioClass);
//    priorityClassComBox = new JComboBox<PriorityClass>(PriorityClass.values());
//    priorityClassComBox.setSelectedIndex(0);
//    GridBagConstraints gbc_priorityClassComBox = new GridBagConstraints();
//    gbc_priorityClassComBox.insets = new Insets(0, 0, 5, 5);
//    gbc_priorityClassComBox.anchor = GridBagConstraints.NORTH;
//    gbc_priorityClassComBox.fill = GridBagConstraints.BOTH;
//    gbc_priorityClassComBox.gridx = 2;
//    gbc_priorityClassComBox.gridy = 9;
//    add(priorityClassComBox, gbc_priorityClassComBox);
//
//    // CVN: 10 punctuality class
//    lblPunctClass = new JLabel("Punct. Class (10)");
//    GridBagConstraints gbc_lblPunctClass = new GridBagConstraints();
//    gbc_lblPunctClass.insets = new Insets(0, 0, 5, 5);
//    gbc_lblPunctClass.anchor = GridBagConstraints.EAST;
//    gbc_lblPunctClass.gridx = 1;
//    gbc_lblPunctClass.gridy = 10;
//    add(lblPunctClass, gbc_lblPunctClass);
//    punctualityClassComBox = new JComboBox<PunctualityClass>(PunctualityClass.values());
//    punctualityClassComBox.setSelectedIndex(0);
//    GridBagConstraints gbc_punctualityClassComBox = new GridBagConstraints();
//    gbc_punctualityClassComBox.insets = new Insets(0, 0, 5, 5);
//    gbc_punctualityClassComBox.anchor = GridBagConstraints.NORTH;
//    gbc_punctualityClassComBox.fill = GridBagConstraints.BOTH;
//    gbc_punctualityClassComBox.gridx = 2;
//    gbc_punctualityClassComBox.gridy = 10;
//    add(punctualityClassComBox, gbc_punctualityClassComBox);
//
//    // CVN: 11 punctuality
//    lblPunctuality = new JLabel("Punctuality (11)");
//    GridBagConstraints gbc_lblPunctuality = new GridBagConstraints();
//    gbc_lblPunctuality.insets = new Insets(0, 0, 5, 5);
//    gbc_lblPunctuality.anchor = GridBagConstraints.EAST;
//    gbc_lblPunctuality.gridx = 1;
//    gbc_lblPunctuality.gridy = 11;
//    add(lblPunctuality, gbc_lblPunctuality);
//    punctualityTF = new NumericField(2, NumericField.DECIMAL);
//    punctualityTF.setAllowNegative(false);
//    punctualityTF.setText("0");
//    punctualityTF.setToolTipText("Punctuality 0-99 [s]");
//    GridBagConstraints gbc_punctualityTF = new GridBagConstraints();
//    gbc_punctualityTF.insets = new Insets(0, 0, 5, 5);
//    gbc_punctualityTF.anchor = GridBagConstraints.NORTH;
//    gbc_punctualityTF.fill = GridBagConstraints.BOTH;
//    gbc_punctualityTF.gridx = 2;
//    gbc_punctualityTF.gridy = 11;
//    add(punctualityTF, gbc_punctualityTF);    
//
//    // CVN: 12 vehicle length
//    lblVehLength = new JLabel("Veh. Length (12)");
//    GridBagConstraints gbc_lblVehLength = new GridBagConstraints();
//    gbc_lblVehLength.insets = new Insets(0, 0, 5, 5);
//    gbc_lblVehLength.anchor = GridBagConstraints.EAST;
//    gbc_lblVehLength.gridx = 1;
//    gbc_lblVehLength.gridy = 12;
//    add(lblVehLength, gbc_lblVehLength);
//    vehicleLengthTF = new NumericField(3, NumericField.DECIMAL);
//    vehicleLengthTF.setAllowNegative(false);
//    vehicleLengthTF.setText("0");
//    vehicleLengthTF.setToolTipText("Vehicle length 0-255 [m]");
//    GridBagConstraints gbc_vehicleLengthTF = new GridBagConstraints();
//    gbc_vehicleLengthTF.insets = new Insets(0, 0, 5, 5);
//    gbc_vehicleLengthTF.anchor = GridBagConstraints.NORTH;
//    gbc_vehicleLengthTF.fill = GridBagConstraints.BOTH;
//    gbc_vehicleLengthTF.gridx = 2;
//    gbc_vehicleLengthTF.gridy = 12;
//    add(vehicleLengthTF, gbc_vehicleLengthTF);
//
//    // CVN: 13 vehicle speed
//    lblVehSpeed = new JLabel("Veh. Speed (13)");
//    GridBagConstraints gbc_lblVehSpeed = new GridBagConstraints();
//    gbc_lblVehSpeed.insets = new Insets(0, 0, 5, 5);
//    gbc_lblVehSpeed.anchor = GridBagConstraints.EAST;
//    gbc_lblVehSpeed.gridx = 1;
//    gbc_lblVehSpeed.gridy = 13;
//    add(lblVehSpeed, gbc_lblVehSpeed);
//    vehicleSpeedTF = new NumericField(2, NumericField.DECIMAL);
//    vehicleSpeedTF.setAllowNegative(false);
//    vehicleSpeedTF.setText("0");
//    vehicleSpeedTF.setToolTipText("Vehicle speed 0-99 [m/s]");
//    GridBagConstraints gbc_vehicleSpeedTF = new GridBagConstraints();
//    gbc_vehicleSpeedTF.insets = new Insets(0, 0, 5, 5);
//    gbc_vehicleSpeedTF.anchor = GridBagConstraints.NORTH;
//    gbc_vehicleSpeedTF.fill = GridBagConstraints.BOTH;
//    gbc_vehicleSpeedTF.gridx = 2;
//    gbc_vehicleSpeedTF.gridy = 13;
//    add(vehicleSpeedTF, gbc_vehicleSpeedTF);
//
//    // CVN: 14 distance to stop
//    lblDistToStop = new JLabel("Dist. to Stop (14)");
//    GridBagConstraints gbc_lblDistToStop = new GridBagConstraints();
//    gbc_lblDistToStop.insets = new Insets(0, 0, 5, 5);
//    gbc_lblDistToStop.anchor = GridBagConstraints.EAST;
//    gbc_lblDistToStop.gridx = 4;
//    gbc_lblDistToStop.gridy = 0;
//    add(lblDistToStop, gbc_lblDistToStop);
//    distanceToStopTF = new NumericField(4, NumericField.DECIMAL);
//    distanceToStopTF.setAllowNegative(false);
//    distanceToStopTF.setText("0");
//    distanceToStopTF.setToolTipText("Distance to passage stop mark -99-9999 [m]");
//    GridBagConstraints gbc_distanceToStopTF = new GridBagConstraints();
//    gbc_distanceToStopTF.gridwidth = 4;
//    gbc_distanceToStopTF.insets = new Insets(0, 0, 5, 5);
//    gbc_distanceToStopTF.anchor = GridBagConstraints.NORTH;
//    gbc_distanceToStopTF.fill = GridBagConstraints.BOTH;
//    gbc_distanceToStopTF.gridx = 5;
//    gbc_distanceToStopTF.gridy = 0;
//    add(distanceToStopTF, gbc_distanceToStopTF);    
//
//    // CVN: 15 time to stop
//    lblTimeToStop = new JLabel("Time to Stop (15)");
//    GridBagConstraints gbc_lblTimeToStop = new GridBagConstraints();
//    gbc_lblTimeToStop.insets = new Insets(0, 0, 5, 5);
//    gbc_lblTimeToStop.anchor = GridBagConstraints.EAST;
//    gbc_lblTimeToStop.gridx = 4;
//    gbc_lblTimeToStop.gridy = 1;
//    add(lblTimeToStop, gbc_lblTimeToStop);
//    timeToStopTF = new NumericField(3, NumericField.DECIMAL);
//    timeToStopTF.setAllowNegative(false);
//    timeToStopTF.setText("0");
//    timeToStopTF.setToolTipText("Time to passage stop mark 0-255 [s]");
//    GridBagConstraints gbc_timeToStopTF = new GridBagConstraints();
//    gbc_timeToStopTF.gridwidth = 4;
//    gbc_timeToStopTF.insets = new Insets(0, 0, 5, 5);
//    gbc_timeToStopTF.anchor = GridBagConstraints.NORTH;
//    gbc_timeToStopTF.fill = GridBagConstraints.BOTH;
//    gbc_timeToStopTF.gridx = 5;
//    gbc_timeToStopTF.gridy = 1;
//    add(timeToStopTF, gbc_timeToStopTF);
//
//    // CVN: 16 journey number
//    lblJourneyNr = new JLabel("Journey nr (16)");
//    GridBagConstraints gbc_lblJourneyNr = new GridBagConstraints();
//    gbc_lblJourneyNr.insets = new Insets(0, 0, 5, 5);
//    gbc_lblJourneyNr.anchor = GridBagConstraints.EAST;
//    gbc_lblJourneyNr.gridx = 4;
//    gbc_lblJourneyNr.gridy = 2;
//    add(lblJourneyNr, gbc_lblJourneyNr);
//    journeyNrTF = new NumericField(4, NumericField.DECIMAL);
//    journeyNrTF.setAllowNegative(false);
//    journeyNrTF.setText("0");
//    journeyNrTF.setToolTipText("Journey number 0-9999");
//    GridBagConstraints gbc_journeyNrTF = new GridBagConstraints();
//    gbc_journeyNrTF.gridwidth = 4;
//    gbc_journeyNrTF.insets = new Insets(0, 0, 5, 5);
//    gbc_journeyNrTF.anchor = GridBagConstraints.NORTH;
//    gbc_journeyNrTF.fill = GridBagConstraints.BOTH;
//    gbc_journeyNrTF.gridx = 5;
//    gbc_journeyNrTF.gridy = 2;
//    add(journeyNrTF, gbc_journeyNrTF); 
//
//    // CVN: 17 journey type
//    lblJourneyType = new JLabel("Journey Type (17)");
//    GridBagConstraints gbc_lblJourneyType = new GridBagConstraints();
//    gbc_lblJourneyType.insets = new Insets(0, 0, 5, 5);
//    gbc_lblJourneyType.anchor = GridBagConstraints.EAST;
//    gbc_lblJourneyType.gridx = 4;
//    gbc_lblJourneyType.gridy = 3;
//    add(lblJourneyType, gbc_lblJourneyType);
//    journeyTypeComBox = new JComboBox<JourneyType>(JourneyType.values());
//    journeyTypeComBox.setSelectedIndex(0);
//    GridBagConstraints gbc_journeyTypeComBox = new GridBagConstraints();
//    gbc_journeyTypeComBox.gridwidth = 4;
//    gbc_journeyTypeComBox.insets = new Insets(0, 0, 5, 5);
//    gbc_journeyTypeComBox.anchor = GridBagConstraints.NORTH;
//    gbc_journeyTypeComBox.fill = GridBagConstraints.BOTH;
//    gbc_journeyTypeComBox.gridx = 5;
//    gbc_journeyTypeComBox.gridy = 3;
//    add(journeyTypeComBox, gbc_journeyTypeComBox);
//
//    // CVN: 18 route
//    lblRoute = new JLabel("Route (18)");
//    GridBagConstraints gbc_lblRoute = new GridBagConstraints();
//    gbc_lblRoute.insets = new Insets(0, 0, 5, 5);
//    gbc_lblRoute.anchor = GridBagConstraints.EAST;
//    gbc_lblRoute.gridx = 4;
//    gbc_lblRoute.gridy = 4;
//    add(lblRoute, gbc_lblRoute);
//    routeTF = new NumericField(2, NumericField.DECIMAL);
//    routeTF.setAllowNegative(false);
//    routeTF.setText("0");
//    routeTF.setToolTipText("Route public transport 0-99");
//    GridBagConstraints gbc_routeTF = new GridBagConstraints();
//    gbc_routeTF.gridwidth = 4;
//    gbc_routeTF.insets = new Insets(0, 0, 5, 5);
//    gbc_routeTF.anchor = GridBagConstraints.NORTH;
//    gbc_routeTF.fill = GridBagConstraints.BOTH;
//    gbc_routeTF.gridx = 5;
//    gbc_routeTF.gridy = 4;
//    add(routeTF, gbc_routeTF);
//
//    // CVN: 18 direction to route VECOM
//    lblDirection = new JLabel("Direction(18)");
//    GridBagConstraints gbc_lblDirection = new GridBagConstraints();
//    gbc_lblDirection.insets = new Insets(0, 0, 5, 5);
//    gbc_lblDirection.anchor = GridBagConstraints.EAST;
//    gbc_lblDirection.gridx = 4;
//    gbc_lblDirection.gridy = 5;
//    add(lblDirection, gbc_lblDirection);
//    DirectionComBox = new JComboBox<Direction>(Direction.values());
//    DirectionComBox.setSelectedIndex(0);
//    DirectionComBox.setToolTipText("Direction field for VECOM");
//    GridBagConstraints gbc_DirectionComBox = new GridBagConstraints();
//    gbc_DirectionComBox.gridwidth = 4;
//    gbc_DirectionComBox.insets = new Insets(0, 0, 5, 5);
//    gbc_DirectionComBox.anchor = GridBagConstraints.NORTH;
//    gbc_DirectionComBox.fill = GridBagConstraints.BOTH;
//    gbc_DirectionComBox.gridx = 5;
//    gbc_DirectionComBox.gridy = 5;
//    add(DirectionComBox, gbc_DirectionComBox);
//
//    // CVN: 19 command on
//    lblCommand = new JLabel("Command (19)");
//    GridBagConstraints gbc_lblCommand = new GridBagConstraints();
//    gbc_lblCommand.insets = new Insets(0, 0, 5, 5);
//    gbc_lblCommand.anchor = GridBagConstraints.EAST;
//    gbc_lblCommand.gridx = 4;
//    gbc_lblCommand.gridy = 6;
//    add(lblCommand, gbc_lblCommand);
//    commandComBox = new JComboBox<Commands>(Commands.values());
//    commandComBox.setSelectedIndex(0);
//    commandComBox.setToolTipText("Command type 0-99");
//    GridBagConstraints gbc_commandComBox = new GridBagConstraints();
//    gbc_commandComBox.gridwidth = 4;
//    gbc_commandComBox.insets = new Insets(0, 0, 5, 5);
//    gbc_commandComBox.anchor = GridBagConstraints.NORTH;
//    gbc_commandComBox.fill = GridBagConstraints.BOTH;
//    gbc_commandComBox.gridx = 5;
//    gbc_commandComBox.gridy = 6;
//    add(commandComBox, gbc_commandComBox);
//
//    // CVN: 20 activation
//    lblActivation = new JLabel("Activation (20)");
//    GridBagConstraints gbc_lblActivation = new GridBagConstraints();
//    gbc_lblActivation.insets = new Insets(0, 0, 5, 5);
//    gbc_lblActivation.anchor = GridBagConstraints.EAST;
//    gbc_lblActivation.gridx = 4;
//    gbc_lblActivation.gridy = 7;
//    add(lblActivation, gbc_lblActivation);
//    activationTF = new NumericField(5, NumericField.DECIMAL);
//    activationTF.setAllowNegative(false);
//    activationTF.setText("0");
//    activationTF.setToolTipText("Activation point number 0-32767");
//    GridBagConstraints gbc_activationTF = new GridBagConstraints();
//    gbc_activationTF.gridwidth = 4;
//    gbc_activationTF.insets = new Insets(0, 0, 5, 5);
//    gbc_activationTF.anchor = GridBagConstraints.NORTH;
//    gbc_activationTF.fill = GridBagConstraints.BOTH;
//    gbc_activationTF.gridx = 5;
//    gbc_activationTF.gridy = 7;
//    add(activationTF, gbc_activationTF);
//
//    // CVN: 21 (a) latitude degrees
//    lblLatitude = new JLabel("Latitude (21)");
//    GridBagConstraints gbc_lblLatitude = new GridBagConstraints();
//    gbc_lblLatitude.insets = new Insets(0, 0, 5, 5);
//    gbc_lblLatitude.anchor = GridBagConstraints.EAST;
//    gbc_lblLatitude.gridx = 4;
//    gbc_lblLatitude.gridy = 8;
//    add(lblLatitude, gbc_lblLatitude);
//    latDegTF = new NumericField(2, NumericField.DECIMAL);
//    latDegTF.setAllowNegative(false);
//    latDegTF.setToolTipText("Latitude degrees 0-89");
//    latDegTF.setText("0");
//    latDegTF.addActionListener(new TimeVerifier(89));
//    latDegTF.setInputVerifier(new TimeVerifier(89));
//    GridBagConstraints gbc_latDegTF = new GridBagConstraints();
//    gbc_latDegTF.insets = new Insets(0, 0, 5, 5);
//    gbc_latDegTF.anchor = GridBagConstraints.NORTH;
//    gbc_latDegTF.fill = GridBagConstraints.BOTH;
//    gbc_latDegTF.gridx = 5;
//    gbc_latDegTF.gridy = 8;
//    add(latDegTF, gbc_latDegTF);
//
//    // CVN: 21 (b) latitude minutes
//    latMinTF = new NumericField(2, NumericField.DECIMAL);
//    latMinTF.setAllowNegative(false);
//    latMinTF.setToolTipText("Latitude minutes 0-59");
//    latMinTF.setText("0");
//    latMinTF.addActionListener(new TimeVerifier(59));
//    latMinTF.setInputVerifier(new TimeVerifier(59));
//    GridBagConstraints gbc_latMinTF = new GridBagConstraints();
//    gbc_latMinTF.insets = new Insets(0, 0, 5, 5);
//    gbc_latMinTF.anchor = GridBagConstraints.NORTH;
//    gbc_latMinTF.fill = GridBagConstraints.BOTH;
//    gbc_latMinTF.gridx = 6;
//    gbc_latMinTF.gridy = 8;
//    add(latMinTF, gbc_latMinTF);
//
//    // CVN: 21 (c) latitude seconds
//    latSecTF = new NumericField(2, NumericField.DECIMAL);
//    latSecTF.setAllowNegative(false);
//    latSecTF.setToolTipText("Latitude seconds 0-59");
//    latSecTF.setText("0");
//    latSecTF.addActionListener(new TimeVerifier(59));
//    latSecTF.setInputVerifier(new TimeVerifier(59));
//    GridBagConstraints gbc_latSecTF = new GridBagConstraints();
//    gbc_latSecTF.insets = new Insets(0, 0, 5, 5);
//    gbc_latSecTF.anchor = GridBagConstraints.NORTH;
//    gbc_latSecTF.fill = GridBagConstraints.BOTH;
//    gbc_latSecTF.gridx = 7;
//    gbc_latSecTF.gridy = 8;
//    add(latSecTF, gbc_latSecTF);
//
//    // CVN: 21 (d) latitude 100th seconds
//    latSSecTF = new NumericField(2, NumericField.DECIMAL);
//    latSSecTF.setAllowNegative(false);
//    latSSecTF.setToolTipText("100th Seconds 0-99");
//    latSSecTF.setText("0");
//    GridBagConstraints gbc_latSSecTF = new GridBagConstraints();
//    gbc_latSSecTF.insets = new Insets(0, 0, 5, 5);
//    gbc_latSSecTF.anchor = GridBagConstraints.NORTH;
//    gbc_latSSecTF.fill = GridBagConstraints.BOTH;
//    gbc_latSSecTF.gridx = 8;
//    gbc_latSSecTF.gridy = 8;
//    add(latSSecTF, gbc_latSSecTF);
//
//    // CVN: 21 (e) longitude degrees
//    lblLongitude = new JLabel("Longitude (21)");
//    GridBagConstraints gbc_lblLongitude = new GridBagConstraints();
//    gbc_lblLongitude.insets = new Insets(0, 0, 5, 5);
//    gbc_lblLongitude.anchor = GridBagConstraints.EAST;
//    gbc_lblLongitude.gridx = 4;
//    gbc_lblLongitude.gridy = 9;
//    add(lblLongitude, gbc_lblLongitude);
//    longDegTF = new NumericField(3, NumericField.DECIMAL);
//    longDegTF.setAllowNegative(false);
//    longDegTF.setToolTipText("Longitude degrees 0-179");
//    longDegTF.setText("0");
//    longDegTF.addActionListener(new TimeVerifier(179));
//    longDegTF.setInputVerifier(new TimeVerifier(179));
//    GridBagConstraints gbc_longDegTF = new GridBagConstraints();
//    gbc_longDegTF.insets = new Insets(0, 0, 5, 5);
//    gbc_longDegTF.anchor = GridBagConstraints.NORTH;
//    gbc_longDegTF.fill = GridBagConstraints.BOTH;
//    gbc_longDegTF.gridx = 5;
//    gbc_longDegTF.gridy = 9;
//    add(longDegTF, gbc_longDegTF);
//
//    // CVN: 21 (f) longitude minutes
//    longMinTF = new NumericField(2, NumericField.DECIMAL);
//    longMinTF.setAllowNegative(false);
//    longMinTF.setToolTipText("Longitude minutes 0-59");
//    longMinTF.setText("0");
//    longMinTF.addActionListener(new TimeVerifier(59));
//    longMinTF.setInputVerifier(new TimeVerifier(59));
//    GridBagConstraints gbc_longMinTF = new GridBagConstraints();
//    gbc_longMinTF.insets = new Insets(0, 0, 5, 5);
//    gbc_longMinTF.anchor = GridBagConstraints.NORTH;
//    gbc_longMinTF.fill = GridBagConstraints.BOTH;
//    gbc_longMinTF.gridx = 6;
//    gbc_longMinTF.gridy = 9;
//    add(longMinTF, gbc_longMinTF);
//
//    // CVN: 21 (g) longitude seconds
//    longSecTF = new NumericField(2, NumericField.DECIMAL);
//    longSecTF.setAllowNegative(false);
//    longSecTF.setToolTipText("Longitude seconds 0-59");
//    longSecTF.setText("0");
//    longSecTF.addActionListener(new TimeVerifier(59));
//    longSecTF.setInputVerifier(new TimeVerifier(59));
//    GridBagConstraints gbc_longSecTF = new GridBagConstraints();
//    gbc_longSecTF.insets = new Insets(0, 0, 5, 5);
//    gbc_longSecTF.anchor = GridBagConstraints.NORTH;
//    gbc_longSecTF.fill = GridBagConstraints.BOTH;
//    gbc_longSecTF.gridx = 7;
//    gbc_longSecTF.gridy = 9;
//    add(longSecTF, gbc_longSecTF);
//
//    // CVN: 21 (h) longitude 100th seconds
//    longSSecTF = new NumericField(2, NumericField.DECIMAL);
//    longSSecTF.setAllowNegative(false);
//    longSSecTF.setToolTipText("Longitude 100th seconds 0-99");
//    longSSecTF.setText("0");
//    GridBagConstraints gbc_longSSecTF = new GridBagConstraints();
//    gbc_longSSecTF.insets = new Insets(0, 0, 5, 5);
//    gbc_longSSecTF.anchor = GridBagConstraints.NORTH;
//    gbc_longSSecTF.fill = GridBagConstraints.BOTH;
//    gbc_longSSecTF.gridx = 8;
//    gbc_longSSecTF.gridy = 9;
//    add(longSSecTF, gbc_longSSecTF);
//
//    // CVN: 22 (a) year
//    lblDate = new JLabel("Date (22)");
//    GridBagConstraints gbc_lblDate = new GridBagConstraints();
//    gbc_lblDate.insets = new Insets(0, 0, 5, 5);
//    gbc_lblDate.anchor = GridBagConstraints.EAST;
//    gbc_lblDate.gridx = 4;
//    gbc_lblDate.gridy = 10;
//    add(lblDate, gbc_lblDate);
//    yearTF = new NumericField(4, NumericField.DECIMAL);
//    yearTF.setAllowNegative(false);
//    yearTF.setToolTipText("Year 0-9999");
//    yearTF.setText(Integer.toString(calendar.get(Calendar.YEAR)));
//    GridBagConstraints gbc_yearTF = new GridBagConstraints();
//    gbc_yearTF.gridwidth = 2;
//    gbc_yearTF.insets = new Insets(0, 0, 5, 5);
//    gbc_yearTF.anchor = GridBagConstraints.NORTH;
//    gbc_yearTF.fill = GridBagConstraints.BOTH;
//    gbc_yearTF.gridx = 5;
//    gbc_yearTF.gridy = 10;
//    add(yearTF, gbc_yearTF);
//
//    // CVN: 22 (b) month
//    monthTF = new NumericField(2, NumericField.DECIMAL);
//    monthTF.setAllowNegative(false);
//    monthTF.setToolTipText("Month 1-12");
//    monthTF.setText(Integer.toString(calendar.get(Calendar.MONTH)));
//    monthTF.addActionListener(new TimeVerifier(12));
//    monthTF.setInputVerifier(new TimeVerifier(12));
//    GridBagConstraints gbc_monthTF = new GridBagConstraints();
//    gbc_monthTF.insets = new Insets(0, 0, 5, 5);
//    gbc_monthTF.anchor = GridBagConstraints.NORTH;
//    gbc_monthTF.fill = GridBagConstraints.BOTH;
//    gbc_monthTF.gridx = 7;
//    gbc_monthTF.gridy = 10;
//    add(monthTF, gbc_monthTF);
//
//    // CVN: 22 (c) day
//    dayTF = new NumericField(2, NumericField.DECIMAL);
//    dayTF.setAllowNegative(false);
//    dayTF.setToolTipText("Day of the month 1-31");
//    dayTF.setText(Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)));
//    dayTF.addActionListener(new TimeVerifier(31));
//    dayTF.setInputVerifier(new TimeVerifier(31));
//    GridBagConstraints gbc_dayTF = new GridBagConstraints();
//    gbc_dayTF.insets = new Insets(0, 0, 5, 5);
//    gbc_dayTF.anchor = GridBagConstraints.NORTH;
//    gbc_dayTF.fill = GridBagConstraints.BOTH;
//    gbc_dayTF.gridx = 8;
//    gbc_dayTF.gridy = 10;
//    add(dayTF, gbc_dayTF);
//
//    // CVN: 22 (d) hour
//    lblTime = new JLabel("Time (22)");
//    GridBagConstraints gbc_lblTime = new GridBagConstraints();
//    gbc_lblTime.insets = new Insets(0, 0, 5, 5);
//    gbc_lblTime.anchor = GridBagConstraints.EAST;
//    gbc_lblTime.gridx = 4;
//    gbc_lblTime.gridy = 11;
//    add(lblTime, gbc_lblTime);
//    hourTF = new NumericField(2, NumericField.DECIMAL);
//    hourTF.setAllowNegative(false);
//    hourTF.setToolTipText("Hours 0-23");
//    hourTF.setText(Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)));
//    hourTF.addActionListener(new TimeVerifier(24));
//    hourTF.setInputVerifier(new TimeVerifier(24));
//    GridBagConstraints gbc_hourTF = new GridBagConstraints();
//    gbc_hourTF.insets = new Insets(0, 0, 5, 5);
//    gbc_hourTF.anchor = GridBagConstraints.NORTH;
//    gbc_hourTF.fill = GridBagConstraints.BOTH;
//    gbc_hourTF.gridx = 5;
//    gbc_hourTF.gridy = 11;
//    add(hourTF, gbc_hourTF);
//
//    // CVN: 22 (d) minute
//    minuteTF = new NumericField(2, NumericField.DECIMAL);
//    minuteTF.setAllowNegative(false);
//    minuteTF.setToolTipText("Minutes 0-59");
//    minuteTF.setText(Integer.toString(calendar.get(Calendar.MINUTE)));
//    minuteTF.addActionListener(new TimeVerifier(59));
//    minuteTF.setInputVerifier(new TimeVerifier(59));
//    GridBagConstraints gbc_minuteTF = new GridBagConstraints();
//    gbc_minuteTF.insets = new Insets(0, 0, 5, 5);
//    gbc_minuteTF.anchor = GridBagConstraints.NORTH;
//    gbc_minuteTF.fill = GridBagConstraints.BOTH;
//    gbc_minuteTF.gridx = 6;
//    gbc_minuteTF.gridy = 11;
//    add(minuteTF, gbc_minuteTF);
//
//    // CVN: 22 (d) second
//    secondTF = new NumericField(2, NumericField.DECIMAL);
//    secondTF.setAllowNegative(false);
//    secondTF.setToolTipText("Seconds 0-59");
//    secondTF.setText(Integer.toString(calendar.get(Calendar.SECOND)));
//    secondTF.setInputVerifier(new TimeVerifier(59));
//    secondTF.addActionListener(new TimeVerifier(59));
//    GridBagConstraints gbc_secondTF = new GridBagConstraints();
//    gbc_secondTF.insets = new Insets(0, 0, 5, 5);
//    gbc_secondTF.anchor = GridBagConstraints.NORTH;
//    gbc_secondTF.fill = GridBagConstraints.BOTH;
//    gbc_secondTF.gridx = 7;
//    gbc_secondTF.gridy = 11;
//    add(secondTF, gbc_secondTF);
//
//    // CVN: 23 reserve1
//    lblReserve_1 = new JLabel("Reserve 1 (23)");
//    GridBagConstraints gbc_lblReserve_1 = new GridBagConstraints();
//    gbc_lblReserve_1.insets = new Insets(0, 0, 5, 5);
//    gbc_lblReserve_1.anchor = GridBagConstraints.EAST;
//    gbc_lblReserve_1.gridx = 4;
//    gbc_lblReserve_1.gridy = 12;
//    add(lblReserve_1, gbc_lblReserve_1);
//    reserve1TF = new JTextField();
//    reserve1TF.setText("0");
//    reserve1TF.setToolTipText("Reserved");
//    GridBagConstraints gbc_reserve1TF = new GridBagConstraints();
//    gbc_reserve1TF.gridwidth = 4;
//    gbc_reserve1TF.insets = new Insets(0, 0, 5, 5);
//    gbc_reserve1TF.anchor = GridBagConstraints.NORTH;
//    gbc_reserve1TF.fill = GridBagConstraints.BOTH;
//    gbc_reserve1TF.gridx = 5;
//    gbc_reserve1TF.gridy = 12;
//    add(reserve1TF, gbc_reserve1TF);
//
//    // CVN: 24 reserve2
//    lblReserve_2 = new JLabel("Reserve 2 (24)");
//    GridBagConstraints gbc_lblReserve_2 = new GridBagConstraints();
//    gbc_lblReserve_2.insets = new Insets(0, 0, 5, 5);
//    gbc_lblReserve_2.anchor = GridBagConstraints.EAST;
//    gbc_lblReserve_2.gridx = 4;
//    gbc_lblReserve_2.gridy = 13;
//    add(lblReserve_2, gbc_lblReserve_2);
//    reserve2TF = new JTextField();
//    reserve2TF.setText("0");
//    reserve2TF.setToolTipText("Reserved");
//    GridBagConstraints gbc_reserve2TF = new GridBagConstraints();
//    gbc_reserve2TF.gridwidth = 4;
//    gbc_reserve2TF.insets = new Insets(0, 0, 5, 5);
//    gbc_reserve2TF.anchor = GridBagConstraints.NORTH;
//    gbc_reserve2TF.fill = GridBagConstraints.BOTH;
//    gbc_reserve2TF.gridx = 5;
//    gbc_reserve2TF.gridy = 13;
//    add(reserve2TF, gbc_reserve2TF);   
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

    @Override
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

  @Getter private Proto proto;
//  private GregorianCalendar calendar;
  @Getter private ProtocolMessage protocolMessage;
  @Getter private List<KarAttributeEntry> karAttributeEntries;

}// end of class



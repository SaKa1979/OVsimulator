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
    gridBagLayout.columnWidths = new int[]{50, 200};
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

  // PRIVATE METHODS
  private void initialize(){
    //    fieldsList = new ArrayList<JTextField>();
    //    comboBoxList = new ArrayList<JComboBox<String>>();

    // CVN: 1 loop number 
    loopNrTF = new JTextField();
    loopNrTF.setText("0");
    GridBagConstraints gbc_loopNrTF = new GridBagConstraints();
    gbc_loopNrTF.insets = new Insets(0, 0, 1, 0);
    gbc_loopNrTF.anchor = GridBagConstraints.NORTH;
    gbc_loopNrTF.fill = GridBagConstraints.BOTH;
    gbc_loopNrTF.gridx = 1;
    gbc_loopNrTF.gridy = 0;
    add(loopNrTF, gbc_loopNrTF);

    // CVN: 2 vehicle type
    vehicleTypeComBox = new JComboBox<VehicleTypes>();
    vehicleTypeComBox.setSelectedIndex(0);
    GridBagConstraints gbc_vehicleTypeComBox = new GridBagConstraints();
    gbc_vehicleTypeComBox.insets = new Insets(0, 0, 1, 0);
    gbc_vehicleTypeComBox.anchor = GridBagConstraints.NORTH;
    gbc_vehicleTypeComBox.fill = GridBagConstraints.BOTH;
    gbc_vehicleTypeComBox.gridx = 1;
    gbc_vehicleTypeComBox.gridy = 1;
    add(vehicleTypeComBox, gbc_vehicleTypeComBox);

    // CVN: 3 line number
    lineNrTF = new JTextField();
    lineNrTF.setText("0");
    GridBagConstraints gbc_lineNrTF = new GridBagConstraints();
    gbc_lineNrTF.insets = new Insets(0, 0, 1, 0);
    gbc_lineNrTF.anchor = GridBagConstraints.NORTH;
    gbc_lineNrTF.fill = GridBagConstraints.BOTH;
    gbc_lineNrTF.gridx = 1;
    gbc_lineNrTF.gridy = 2;
    add(lineNrTF, gbc_lineNrTF);

    // CVN: 4 vehicle service number
    vehServiceNrTF = new JTextField();
    vehServiceNrTF.setText("0");
    GridBagConstraints gbc_vehServiceNrTF = new GridBagConstraints();
    gbc_vehServiceNrTF.insets = new Insets(0, 0, 1, 0);
    gbc_vehServiceNrTF.anchor = GridBagConstraints.NORTH;
    gbc_vehServiceNrTF.fill = GridBagConstraints.BOTH;
    gbc_vehServiceNrTF.gridx = 1;
    gbc_vehServiceNrTF.gridy = 3;
    add(vehServiceNrTF, gbc_vehServiceNrTF);

    // CVN: 5 company number
    companyNrTF = new JTextField();
    companyNrTF.setText("0");
    GridBagConstraints gbc_companyNrTF = new GridBagConstraints();
    gbc_companyNrTF.insets = new Insets(0, 0, 1, 0);
    gbc_companyNrTF.anchor = GridBagConstraints.NORTH;
    gbc_companyNrTF.fill = GridBagConstraints.BOTH;
    gbc_companyNrTF.gridx = 1;
    gbc_companyNrTF.gridy = 4;
    add(companyNrTF, gbc_companyNrTF);            

    // CVN: 6 vehicle ID
    vehicleIdTF = new JTextField();
    vehicleIdTF.setText("0");
    GridBagConstraints gbc_vehicleIdTF = new GridBagConstraints();
    gbc_vehicleIdTF.insets = new Insets(0, 0, 1, 0);
    gbc_vehicleIdTF.anchor = GridBagConstraints.NORTH;
    gbc_vehicleIdTF.fill = GridBagConstraints.BOTH;
    gbc_vehicleIdTF.gridx = 1;
    gbc_vehicleIdTF.gridy = 5;
    add(vehicleIdTF, gbc_vehicleIdTF);


    // CVN: 7 signal group or direction
    signalGroupNrTF = new JTextField();
    signalGroupNrTF.setText("0");
    GridBagConstraints gbc_signalGroupNrTF = new GridBagConstraints();
    gbc_signalGroupNrTF.insets = new Insets(0, 0, 1, 0);
    gbc_signalGroupNrTF.anchor = GridBagConstraints.NORTH;
    gbc_signalGroupNrTF.fill = GridBagConstraints.BOTH;
    gbc_signalGroupNrTF.gridx = 1;
    gbc_signalGroupNrTF.gridy = 6;
    add(signalGroupNrTF, gbc_signalGroupNrTF);

    // CVN: 8 vehicle status
    vehicleStatusComBox = new JComboBox<VehicleStatus>();
    vehicleStatusComBox.setSelectedIndex(0);
    GridBagConstraints gbc_vehicleStatusComBox = new GridBagConstraints();
    gbc_vehicleStatusComBox.insets = new Insets(0, 0, 1, 0);
    gbc_vehicleStatusComBox.anchor = GridBagConstraints.NORTH;
    gbc_vehicleStatusComBox.fill = GridBagConstraints.BOTH;
    gbc_vehicleStatusComBox.gridx = 1;
    gbc_vehicleStatusComBox.gridy = 7;
    add(vehicleStatusComBox, gbc_vehicleStatusComBox);

    // CVN: 9 priority class
    priorityClassComBox = new JComboBox<PriorityClass>();
    priorityClassComBox.setSelectedIndex(0);
    GridBagConstraints gbc_priorityClassComBox = new GridBagConstraints();
    gbc_priorityClassComBox.insets = new Insets(0, 0, 1, 0);
    gbc_priorityClassComBox.anchor = GridBagConstraints.NORTH;
    gbc_priorityClassComBox.fill = GridBagConstraints.BOTH;
    gbc_priorityClassComBox.gridx = 1;
    gbc_priorityClassComBox.gridy = 8;
    add(priorityClassComBox, gbc_priorityClassComBox);

    // CVN: 10 punctuality class
    punctualityClassComBox = new JComboBox<PunctualityClass>();
    punctualityClassComBox.setSelectedIndex(0);
    GridBagConstraints gbc_punctualityClassComBox = new GridBagConstraints();
    gbc_punctualityClassComBox.insets = new Insets(0, 0, 1, 0);
    gbc_punctualityClassComBox.anchor = GridBagConstraints.NORTH;
    gbc_punctualityClassComBox.fill = GridBagConstraints.BOTH;
    gbc_punctualityClassComBox.gridx = 1;
    gbc_punctualityClassComBox.gridy = 9;
    add(punctualityClassComBox, gbc_punctualityClassComBox);

    // CVN: 11 punctuality
    punctualityTF = new JTextField();
    punctualityTF.setText("0");
    GridBagConstraints gbc_punctualityTF = new GridBagConstraints();
    gbc_punctualityTF.insets = new Insets(0, 0, 1, 0);
    gbc_punctualityTF.anchor = GridBagConstraints.NORTH;
    gbc_punctualityTF.fill = GridBagConstraints.BOTH;
    gbc_punctualityTF.gridx = 1;
    gbc_punctualityTF.gridy = 10;
    add(punctualityTF, gbc_punctualityTF);    

    // CVN: 12 vehicle length
    vehicleLengthTF = new JTextField();
    vehicleLengthTF.setText("0");
    GridBagConstraints gbc_vehicleLengthTF = new GridBagConstraints();
    gbc_vehicleLengthTF.insets = new Insets(0, 0, 1, 0);
    gbc_vehicleLengthTF.anchor = GridBagConstraints.NORTH;
    gbc_vehicleLengthTF.fill = GridBagConstraints.BOTH;
    gbc_vehicleLengthTF.gridx = 1;
    gbc_vehicleLengthTF.gridy = 11;
    add(vehicleLengthTF, gbc_vehicleLengthTF);

    // CVN: 13 vehicle speed
    vehicleSpeedTF = new JTextField();
    vehicleSpeedTF.setEnabled(false);
    vehicleSpeedTF.setText("0");
    GridBagConstraints gbc_vehicleSpeedTF = new GridBagConstraints();
    gbc_vehicleSpeedTF.insets = new Insets(0, 0, 1, 0);
    gbc_vehicleSpeedTF.anchor = GridBagConstraints.NORTH;
    gbc_vehicleSpeedTF.fill = GridBagConstraints.BOTH;
    gbc_vehicleSpeedTF.gridx = 1;
    gbc_vehicleSpeedTF.gridy = 12;
    add(vehicleSpeedTF, gbc_vehicleSpeedTF);   

    // CVN: 14 distance to stop
    distanceToStopTF = new JTextField();
    distanceToStopTF.setText("0");
    GridBagConstraints gbc_distanceToStopTF = new GridBagConstraints();
    gbc_distanceToStopTF.insets = new Insets(0, 0, 1, 0);
    gbc_distanceToStopTF.anchor = GridBagConstraints.NORTH;
    gbc_distanceToStopTF.fill = GridBagConstraints.BOTH;
    gbc_distanceToStopTF.gridx = 1;
    gbc_distanceToStopTF.gridy = 13;
    add(distanceToStopTF, gbc_distanceToStopTF);    

    // CVN: 15 time to stop
    timeToStopTF = new JTextField();
    timeToStopTF.setText("0");
    GridBagConstraints gbc_timeToStopTF = new GridBagConstraints();
    gbc_timeToStopTF.insets = new Insets(0, 0, 1, 0);
    gbc_timeToStopTF.anchor = GridBagConstraints.NORTH;
    gbc_timeToStopTF.fill = GridBagConstraints.BOTH;
    gbc_timeToStopTF.gridx = 1;
    gbc_timeToStopTF.gridy = 14;
    add(timeToStopTF, gbc_timeToStopTF);

    // CVN: 16 journey number
    journeyNrTF = new JTextField();
    journeyNrTF.setText("0");
    GridBagConstraints gbc_journeyNrTF = new GridBagConstraints();
    gbc_journeyNrTF.insets = new Insets(0, 0, 1, 0);
    gbc_journeyNrTF.anchor = GridBagConstraints.NORTH;
    gbc_journeyNrTF.fill = GridBagConstraints.BOTH;
    gbc_journeyNrTF.gridx = 1;
    gbc_journeyNrTF.gridy = 15;
    add(journeyNrTF, gbc_journeyNrTF); 

    // CVN: 17 journey type
    journeyTypeComBox = new JComboBox<JourneyType>();
    journeyTypeComBox.setSelectedIndex(0);
    GridBagConstraints gbc_journeyTypeComBox = new GridBagConstraints();
    gbc_journeyTypeComBox.insets = new Insets(0, 0, 1, 0);
    gbc_journeyTypeComBox.anchor = GridBagConstraints.NORTH;
    gbc_journeyTypeComBox.fill = GridBagConstraints.BOTH;
    gbc_journeyTypeComBox.gridx = 1;
    gbc_journeyTypeComBox.gridy = 16;
    add(journeyTypeComBox, gbc_journeyTypeComBox);

    // CVN: 18 route
    routeTF = new JTextField();
    routeTF.setText("0");
    GridBagConstraints gbc_routeTF = new GridBagConstraints();
    gbc_routeTF.insets = new Insets(0, 0, 1, 0);
    gbc_routeTF.anchor = GridBagConstraints.NORTH;
    gbc_routeTF.fill = GridBagConstraints.BOTH;
    gbc_routeTF.gridx = 1;
    gbc_routeTF.gridy = 17;
    add(routeTF, gbc_routeTF); 
    
    // CVN: 19 command on
    commandComBox = new JComboBox<Commands>();
    commandComBox.setSelectedIndex(0);
    GridBagConstraints gbc_commandComBox = new GridBagConstraints();
    gbc_commandComBox.insets = new Insets(0, 0, 1, 0);
    gbc_commandComBox.anchor = GridBagConstraints.NORTH;
    gbc_commandComBox.fill = GridBagConstraints.BOTH;
    gbc_commandComBox.gridx = 1;
    gbc_commandComBox.gridy = 18;
    add(commandComBox, gbc_commandComBox);

    // CVN: 20 route
    activationTF = new JTextField();
    activationTF.setText("0");
    GridBagConstraints gbc_activationTF = new GridBagConstraints();
    gbc_activationTF.insets = new Insets(0, 0, 1, 0);
    gbc_activationTF.anchor = GridBagConstraints.NORTH;
    gbc_activationTF.fill = GridBagConstraints.BOTH;
    gbc_activationTF.gridx = 1;
    gbc_activationTF.gridy = 19;
    add(activationTF, gbc_activationTF);
    
    // CVN: 21 (a b c d) latitude
    latitudeTF = new JTextField();
    latitudeTF.setText("0");
    GridBagConstraints gbc_latitudeTF = new GridBagConstraints();
    gbc_latitudeTF.insets = new Insets(0, 0, 1, 0);
    gbc_latitudeTF.anchor = GridBagConstraints.NORTH;
    gbc_latitudeTF.fill = GridBagConstraints.BOTH;
    gbc_latitudeTF.gridx = 1;
    gbc_latitudeTF.gridy = 20;
    add(latitudeTF, gbc_latitudeTF);
  
    // CVN: 21 (e f g h) longitude
    longitudeTF = new JTextField();
    longitudeTF.setText("0");
    GridBagConstraints gbc_longitudeTF = new GridBagConstraints();
    gbc_longitudeTF.insets = new Insets(0, 0, 1, 0);
    gbc_longitudeTF.anchor = GridBagConstraints.NORTH;
    gbc_longitudeTF.fill = GridBagConstraints.BOTH;
    gbc_longitudeTF.gridx = 1;
    gbc_longitudeTF.gridy = 21;
    add(longitudeTF, gbc_longitudeTF);
    
    // CVN: 22 (a b c) date
    dateTF = new JTextField();
    dateTF.setText("0");
    GridBagConstraints gbc_dateTF = new GridBagConstraints();
    gbc_dateTF.insets = new Insets(0, 0, 1, 0);
    gbc_dateTF.anchor = GridBagConstraints.NORTH;
    gbc_dateTF.fill = GridBagConstraints.BOTH;
    gbc_dateTF.gridx = 1;
    gbc_dateTF.gridy = 22;
    add(dateTF, gbc_dateTF);
    
    // CVN: 22 (d e f) date
    timeTF = new JTextField();
    timeTF.setText("0");
    GridBagConstraints gbc_timeTF = new GridBagConstraints();
    gbc_timeTF.insets = new Insets(0, 0, 1, 0);
    gbc_timeTF.anchor = GridBagConstraints.NORTH;
    gbc_timeTF.fill = GridBagConstraints.BOTH;
    gbc_timeTF.gridx = 1;
    gbc_timeTF.gridy = 23;
    add(timeTF, gbc_timeTF);
    
    // CVN: 23 reserve1
    reserve1TF = new JTextField();
    reserve1TF.setText("0");
    GridBagConstraints gbc_reserve1TF = new GridBagConstraints();
    gbc_reserve1TF.insets = new Insets(0, 0, 1, 0);
    gbc_reserve1TF.anchor = GridBagConstraints.NORTH;
    gbc_reserve1TF.fill = GridBagConstraints.BOTH;
    gbc_reserve1TF.gridx = 1;
    gbc_reserve1TF.gridy = 24;
    add(reserve1TF, gbc_reserve1TF);
    
    // CVN: 24 reserve2
    reserve2TF = new JTextField();
    reserve2TF.setText("0");
    GridBagConstraints gbc_reserve2TF = new GridBagConstraints();
    gbc_reserve2TF.insets = new Insets(0, 0, 1, 0);
    gbc_reserve2TF.anchor = GridBagConstraints.NORTH;
    gbc_reserve2TF.fill = GridBagConstraints.BOTH;
    gbc_reserve2TF.gridx = 1;
    gbc_reserve2TF.gridy = 25;
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

  /**
   * @brief Reads the current selection and copies it to the named VehicleButton attribute
   * 		The target attribute is decided by combobox name.
   * @param combobox JComboBox<String> 
   */
  //  private void readComBoBox(JComboBox<String> combobox){
  //    if (!combobox.isEnabled())
  //      return;
  //
  //    if (combobox.getName() == directionComBox_name){
  //      int index = combobox.getSelectedIndex();
  //      Directions d = Directions.getAsType(index);
  //      vehicle_button.setDirection(d);
  //    }else if (combobox.getName() == commandComBox_name){
  //      int index = combobox.getSelectedIndex();
  //      Commands c = Commands.getAsType(index);
  //      vehicle_button.setCommand(c);
  //    }else if (combobox.getName() == vehicleTypeComBox_name){
  //      int index = combobox.getSelectedIndex();
  //      VehicleTypes vt = VehicleTypes.getAsType(index);
  //      vehicle_button.setVehicleType(vt); 
  //    }else if (combobox.getName() == vehicleStatusComBox_name){
  //      int index = combobox.getSelectedIndex();
  //      VehicleStatus vs = VehicleStatus.getAsType(index);
  //      vehicle_button.setVehicleStatus(vs);
  //    }else if (combobox.getName() == priorityClassComBox_name){
  //      int index = combobox.getSelectedIndex();
  //      PriorityClass pc = PriorityClass.getAsType(index);
  //      vehicle_button.setPriorityClass(pc);
  //    }else if (combobox.getName() == punctualityClassComBox_name){
  //      int index = combobox.getSelectedIndex();
  //      PunctualityClass puc = PunctualityClass.getAsType(index);
  //      vehicle_button.setPunctualityClass(puc);
  //    }
  //  }

  // INNER CLASSES
  //  public class TextFieldListener implements ActionListener{
  //    @Override
  //    public void actionPerformed(ActionEvent e) {
  //      JTextField tf = (JTextField)e.getSource();
  //      textFieldToInt(tf);
  //    }
  //  } //end of inner class TextFieldListener
  //
  //  public class ComBoListener implements ActionListener{
  //    @Override
  //    public void actionPerformed(ActionEvent e) {
  //      @SuppressWarnings("unchecked")
  //      JComboBox<String> combobox = (JComboBox<String>)e.getSource();
  //      readComBoBox(combobox);
  //    }  
  //  }// end of inner class ComBoListener

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





  //  private String loopNrTF_name = "loopnr";
  //  private String signalGroupNrTF_name = "signalgroupnr";        
  //  private String commandComBox_name = "command";              
  //  private String vehicleTypeComBox_name = "vehicletype";          
  //  private String lineNrTF_name = "linenr";               
  //  private String wagonNrTF_name = "wagonnr";              
  //  private String vehicleIdTF_name = "vehicleid";            
  //  private String vehicleLengthTF_name = "vehiclelength";        
  //  private String vehicleSpeedTF_name = "vehiclespeed";         
  //  private String distanceToStopTF_name = "distancetostop";       
  //  private String timeToStopTF_name = "timetostop";           
  //  private String vehicleStatusComBox_name = "vehiclestatus";        
  //  private String priorityClassComBox_name = "priorityclass";
  //  private String punctualityClassComBox_name = "punctualityclass";     
  //  private String punctualityTF_name = "punctuality"; 

  //  private ArrayList<JTextField> fieldsList; // holds all textfields
  //  private ArrayList<JComboBox<String>> comboBoxList; // holds all comboboxes

  //  private String[] directionArray;
  //  private String[] commandArray;
  //  private String[] vehicleTypesArray;
  //  private String[] vehicleStatusArray;
  //  private String[] priorityClassArray;
  //  private String[] punctualityClassArray;

}// end of class




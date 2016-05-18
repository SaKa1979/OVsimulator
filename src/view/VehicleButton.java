package view;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import images.ImageFactory;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @brief A button representing a vehicle detection. 
 *        A mouse listener must be added to the class that is using this button. The RMB action should be handled in the calling class
 *        The attribute settings are controlled by a VehicleSetting class which shall be triggered by the RMB
 * @author Sander Kamps
 */
public class VehicleButton extends JButton{

  private static final long serialVersionUID = 4L;

  /**
   *  constructor
   */
  public VehicleButton(){
    super();
    vehicleSettingPanel = new VehicleSettingPanel(this);
    setToolTipText("LMB is action.\r\nRMB is settings");
    initialize();
  }
  public VehicleButton(String a_name) {
    super(a_name);
    name = a_name;
    vehicleSettingPanel = new VehicleSettingPanel(this);
    initialize();
  }


  // PUBLIC METHODS
  public void setButtonText(){
    setText("L" + loopNr + " FC" + signalGroupNr);
    setFont(new Font("MonoSpace", Font.PLAIN, 8));
  } 

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
  public VehicleSettingPanel getVehicleSettingPanel() {
    return vehicleSettingPanel;
  }
  
    public int getLoopNr() {
    return loopNr;
  }
  public void setLoopNr(int a_loopNR) {
    loopNr = a_loopNR;
    vehicleSettingPanel.setLoopNrTF(Integer.toString(a_loopNR));
  }
  public VehicleType getVehicleType() {
    return vehicleType;
  }
  public void setVehicleType(VehicleType a_vehicleType) {
    vehicleType = a_vehicleType;
    vehicleSettingPanel.setVehicleTypeComBox(a_vehicleType);
    setVehicleTypeImage(a_vehicleType);
  }
  public int getLineNr() {
    return lineNr;
  }
  public void setLineNr(int a_lineNr) {
    lineNr = a_lineNr;
    vehicleSettingPanel.setLineNrTF(Integer.toString(a_lineNr));
  }
  public int getVehServiceNr() {
    return vehServiceNr;
  }
  public void setVehServiceNr(int a_vehServiceNr) {
    vehServiceNr = a_vehServiceNr;
    vehicleSettingPanel.setVehServiceNrTF(Integer.toString(a_vehServiceNr));
  }
  public int getCompanyNr() {
    return companyNr;
  }
  public void setCompanyNr(int a_companyNr) {
    companyNr = a_companyNr;
    vehicleSettingPanel.setCompanyNrTF(Integer.toString(a_companyNr));
  }
  public int getVehicleId() {
    return vehicleId;
  }
  public void setVehicleId(int a_vehicleId) {
    vehicleId = a_vehicleId;
    vehicleSettingPanel.setVehicleIdTF(Integer.toString(a_vehicleId));
  }
  public int getSignalGroupNr() {
    return signalGroupNr;
  }
  public void setSignalGroupNr(int a_signalGroupNr) {
    signalGroupNr = a_signalGroupNr;
    vehicleSettingPanel.setSignalGroupNr(Integer.toString(a_signalGroupNr));
  }
  public ManualControl getManualControl() {
    return manualControl;
  }
  public void setManualControl(ManualControl a_manualControl) {
    manualControl = a_manualControl;
    vehicleSettingPanel.setManualControlComBox(a_manualControl);
  }
  public VehicleStatus getVehicleStatus() {
    return vehicleStatus;
  }
  public void setVehicleStatus(VehicleStatus a_vehicleStatus) {
    vehicleStatus = a_vehicleStatus;
    vehicleSettingPanel.setVehicleStatusComBox(a_vehicleStatus);
  }
  public PriorityClass getPriorityClass() {
    return priorityClass;
  }
  public void setPriorityClass(PriorityClass a_priorityClass) {
    priorityClass = a_priorityClass;
    vehicleSettingPanel.setPriorityClassComBox(a_priorityClass);
  }
  public PunctualityClass getPunctualityClass() {
    return punctualityClass;
  }
  public void setPunctualityClass(PunctualityClass a_punctualityClass) {
    punctualityClass = a_punctualityClass;
    vehicleSettingPanel.setPunctualityClassComBox(a_punctualityClass);
  }
  public int getPunctuality() {
    return punctuality;
  }
  public void setPunctuality(int a_punctuality) {
    punctuality = a_punctuality;
    vehicleSettingPanel.setPunctualityTF(Integer.toString(a_punctuality));
  }
  public int getVehicleLength() {
    return vehicleLength;
  }
  public void setVehicleLength(int a_vehicleLength) {
    vehicleLength = a_vehicleLength;
    vehicleSettingPanel.setVehicleLengthTF(Integer.toString(a_vehicleLength));
  }
  public int getVehicleSpeed() {
    return vehicleSpeed;
  }
  public void setVehicleSpeed(int a_vehicleSpeed) {
    vehicleSpeed = a_vehicleSpeed;
    vehicleSettingPanel.setVehicleSpeedTF(Integer.toString(a_vehicleSpeed));
  }
  public int getDistanceToStop() {
    return distanceToStop;
  }
  public void setDistanceToStop(int a_distanceToStop) {
    distanceToStop = a_distanceToStop;
    vehicleSettingPanel.setDistanceToStopTF(Integer.toString(a_distanceToStop));
  }
  public int getTimeToStop() {
    return timeToStop;
  }
  public void setTimeToStop(int a_timeToStop) {
    timeToStop = a_timeToStop;
    vehicleSettingPanel.setTimeToStopTF(Integer.toString(a_timeToStop));
  }
  public int getJourneyNr() {
    return journeyNr;
  }
  public void setJourneyNr(int a_journeyNr) {
    journeyNr = a_journeyNr;
    vehicleSettingPanel.setJourneyNrTF(Integer.toString(a_journeyNr));
  }
  public JourneyType getJourneyType() {
    return journeyType;
  }
  public void setJourneyType(JourneyType a_journeyType) {
    journeyType = a_journeyType;
    vehicleSettingPanel.setJourneyTypeComBox(a_journeyType);
  }
  public int getRoute() {
    return route;
  }
  public void setRoute(int a_routeTF) {
    route = a_routeTF;
    vehicleSettingPanel.setRouteTF(Integer.toString(a_routeTF));
  }
  public Direction getDirection() {
    return direction;
  }
  public void setDirection(Direction a_direction) {
    direction = a_direction;
    vehicleSettingPanel.setDirectionComBox(a_direction);
  }
  public Commands getCommand() {
    return command;
  }
  public void setCommand(Commands a_command) {
    command = a_command;
    vehicleSettingPanel.setCommandComBox(a_command);
  }
  public int getActivation() {
    return activation;
  }
  public void setActivation(int a_activation) {
    activation = a_activation;
    vehicleSettingPanel.setActivationTF(Integer.toString(a_activation));
  }
  public int getLatDeg() {
    return latDeg;
  }
  public void setLatDeg(int a_latDeg) {
    latDeg = a_latDeg;
    vehicleSettingPanel.setLatDegTF(Integer.toString(a_latDeg));
  }
  public int getLatMin() {
    return latMin;
  }
  public void setLatMin(int a_latMin) {
    latMin = a_latMin;
    vehicleSettingPanel.setLatMinTF(Integer.toString(a_latMin));
  }
  public int getLatSec() {
    return latSec;
  }
  public void setLatSec(int a_latSec) {
    latSec = a_latSec;
    vehicleSettingPanel.setLatSecTF(Integer.toString(a_latSec));
  }
  public int getLatSSec() {
    return latSSec;
  }
  public void setLatSSec(int a_latSSec) {
    latSSec = a_latSSec;
    vehicleSettingPanel.setLatSSecTF(Integer.toString(a_latSSec));
  }
  public int getLongDeg() {
    return longDeg;
  }
  public void setLongDeg(int a_longDeg) {
    longDeg = a_longDeg;
    vehicleSettingPanel.setLongDegTF(Integer.toString(a_longDeg));
  }
  public int getLongMin() {
    return longMin;
  }
  public void setLongMin(int a_longMin) {
    longMin = a_longMin;
    vehicleSettingPanel.setLongMinTF(Integer.toString(a_longMin));
  }
  public int getLongSec() {
    return longSec;
  }
  public void setLongSec(int a_longSec) {
    longSec = a_longSec;
    vehicleSettingPanel.setLongSecTF(Integer.toString(a_longSec));
  }
  public int getLongSSec() {
    return longSSec;
  }
  public void setLongSSec(int a_longSSec) {
    longSSec = a_longSSec;
    vehicleSettingPanel.setLongSSecTF(Integer.toString(a_longSSec));
  }
  public int getYear() {
    return year;
  }
  public void setYear(int a_year) {
    year = a_year;
    vehicleSettingPanel.setYearTF(Integer.toString(a_year));
  }
  public int getMonth() {
    return month;
  }
  public void setMonth(int a_month) {
    month = a_month;
    vehicleSettingPanel.setMonthTF(Integer.toString(a_month));
  }
  public int getDay() {
    return day;
  }
  public void setDay(int a_day) {
    day = a_day;
    vehicleSettingPanel.setDayTF(Integer.toString(a_day));
  }
  public int getHour() {
    return hour;
  }
  public void setHour(int a_hour) {
    hour = a_hour;
    vehicleSettingPanel.setHourTF(Integer.toString(a_hour));
  }
  public int getMinute() {
    return minute;
  }
  public void setMinute(int a_minute) {
    minute = a_minute;
    vehicleSettingPanel.setMinuteTF(Integer.toString(a_minute));
  }
  public int getSecond() {
    return second;
  }
  public void setSecond(int a_second) {
    second = a_second;
    vehicleSettingPanel.setSecondTF(Integer.toString(a_second));
  }
  public int getReserve1() {
    return reserve1;
  }
  public void setReserve1(int a_reserve1) {
    reserve1 = a_reserve1;
    vehicleSettingPanel.setReserve1TF(Integer.toString(a_reserve1));
  }
  public int getReserve2() {
    return reserve2;
  }
  public void setReserve2(int a_reserve2) {
    reserve2 = a_reserve2;
    vehicleSettingPanel.setReserve2TF(Integer.toString(a_reserve2));
  }
  // PRIVATE METHODS
  private void setVehicleTypeImage(VehicleType vt){
    switch (vt){
    case BUS_CITY:
    case BUS_REG:
        setIcon(imagefactory.getImageIcon(VehicleType.BUS_CITY._name));
        break;
    case TRAM_CITY:
    case TRAM_REG:
        setIcon(imagefactory.getImageIcon(VehicleType.TRAM_CITY._name));
        break;
    case TAXI:
        setIcon(imagefactory.getImageIcon(VehicleType.TAXI._name));
        break;
    case AMBULANCE:
        setIcon(imagefactory.getImageIcon(VehicleType.AMBULANCE._name));
        break;
    case BRANDWEER:
        setIcon(imagefactory.getImageIcon(VehicleType.BRANDWEER._name));
        break;
    case POLITIE:
    	setIcon(imagefactory.getImageIcon(VehicleType.POLITIE._name));
    	break;
    default:
    	setIcon(imagefactory.getImageIcon(VehicleType.UNKNOWN._name));
    	break;
    }
  }
  
  // ENUMS

  public enum ManualControl {
    NOMANUALCONTROL ("geen info",0), 
    TURNRIGHT ("turn right",1),
    TURNLEFT ("turn left ",2),
    FORWARD ("straight ahead",3),
    READYTOSTART ("ready to start", 5),
    RTS_TR ("ready to start and turn right", 6),
    RTS_TL ("ready to start and turn left", 7),
    RTS_F ("ready to start and forward", 8);

    private String _name;
    private int _nr;
    // constructor
    private ManualControl(String a_Name, int a_Nr){
      _name = a_Name;
      _nr = a_Nr;
    }
    public String getName(){
      return _name;
    }
    public int getNr(){
      return _nr;
    }
  }// end enum ManualControl

  public enum Commands {
    RESERVE ("reserve",0), 
    IN ("imelding",1),
    UIT ("uitmelding",2),
    VOOR ("vooraankondiging",3);

    private String _name;
    private int _nr;
    // constructor
    private Commands(String a_Name, int a_Nr){
      _name = a_Name;
      _nr = a_Nr;
    }
    public  String getName(){
      return _name;
    }
    public int getNr(){
      return _nr;
    }
  }// end enum Commands

  public enum VehicleType {
    BUS_CITY ("Bus",0), 
    TRAM_CITY ("Tram",1),
    BUS_REG ("Bus",2), 
    TRAM_REG ("Tram",3),
    TAXI ("Taxi",4),
    AMBULANCE ("Ambulance",5),
    BRANDWEER ("Brandweer",6),
    POLITIE ("Politie",7),
    UNKNOWN("Unknown",8);

    private String _name;
    private int _nr;
    // constructor
    private VehicleType(String a_Name, int a_Nr){
      _name = a_Name;
      _nr = a_Nr;
    }
    public  String getName(){
      return _name;
    }
    public int getNr(){
      return _nr;
    }
  }// end enum VehicleType

  public enum VehicleStatus {
    GEENINFO ("geen info",0), 
    ONDERWEG ("onderweg",1),
    STOP1 ("stop bij halte",2),
    EINDE ("einde",3),
    STOP2 ("stop niet bij halte",4);

    private String _name;
    private int _nr;
    // constructor
    private VehicleStatus(String a_Name, int a_Nr){
      _name = a_Name;
      _nr = a_Nr;
    }
    public  String getName(){
      return _name;
    }
    public int getNr(){
      return _nr;
    }
  }// end enum VehicleStatus

  public enum PriorityClass {
    GEENINFO ("geen info",0), 
    GEEN ("geen",1),
    CONDITIONEEL ("conditioneel",2),
    ABSOLUUT ("absoluut",3),
    ALARM ("alarm",4);

    private String _name;
    private int _nr;
    // constructor
    private PriorityClass(String a_Name, int a_Nr){
      _name = a_Name;
      _nr = a_Nr;
    }
    public  String getName(){
      return _name;
    }
    public int getNr(){
      return _nr;
    }
  }// end enum PriorityClass

  public enum PunctualityClass {
    GEENINFO ("geen info",0), 
    TELAAT ("te laat",1),
    OPTIJD ("op tijd",2),
    TEVROEG ("te vroeg",3),
    BUITENDIENST ("geen dienst",4),
    NORMAL ("vecom normaal",5),
    SYSTEM ("vecom system",6),
    RESERVE1 ("vecom reseve1",7),
    RESERVE2 ("vecom reseve2",7);

    private String _name;
    private int _nr;
    // constructor
    private PunctualityClass(String a_Name, int a_Nr){
      _name = a_Name;
      _nr = a_Nr;
    }

    public  String getName(){
      return _name;
    }
    public int getNr(){
      return _nr;
    }
  }// end enum PunctualityClass

  public enum JourneyType {
    DIENST ("dienst",10), 
    DEADRUN ("dead run",11),
    REMISE_IN ("remise in",12),
    REMISE_UIT ("remise uit",13),
    RESERVED ("gereserveer",14);

    private String _name;
    private int _nr;
    // constructor
    private JourneyType(String a_Name, int a_Nr){
      _name = a_Name;
      _nr = a_Nr;
    }

    public  String getName(){
      return _name;
    }
    public int getNr(){
      return _nr;
    }
  }// end enum PunctualityClass

  public enum Direction {
    UNKNOWN ("unknown",0), 
    FORWARD ("unknown",1),
    RESERVED ("reserved",2),
    BACKWARDS ("backwards",3);

    private String _name;
    private int _nr;
    // constructor
    private Direction(String a_Name, int a_Nr){
      _name = a_Name;
      _nr = a_Nr;
    }

    public  String getName(){
      return _name;
    }
    public int getNr(){
      return _nr;
    }
  }// end enum Direction
  
  // PRIVATE METHODS
  private void initialize(){
    setIcon(imagefactory.getImageIcon("emptyVehicle"));
    this.setEnabled(false);
    setVerticalTextPosition(JButton.BOTTOM);
    setHorizontalTextPosition(JButton.CENTER);
    // handle the RMB action. This shall spawn a JOption with all the vehicle simulation settings
    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e) || e.isControlDown()){
          vehicleSettingPanel.setProto(ViewManager.getInstance().getProtocolPanel().getSelectedProto());
          int ok = JOptionPane.showConfirmDialog(null,
              vehicleSettingPanel,
              "Vehicle Setting",
              JOptionPane.OK_CANCEL_OPTION,
              JOptionPane.PLAIN_MESSAGE); 
          switch (ok){
            case JOptionPane.OK_OPTION:
              loopNr = Integer.parseUnsignedInt(vehicleSettingPanel.getLoopNr());                         
              vehicleType = vehicleSettingPanel.getVehicleType();
              lineNr = Integer.parseUnsignedInt(vehicleSettingPanel.getLineNr());
              vehServiceNr = Integer.parseUnsignedInt(vehicleSettingPanel.getVehServiceNr());
              companyNr = Integer.parseUnsignedInt(vehicleSettingPanel.getCompanyNr());
              vehicleId = Integer.parseUnsignedInt(vehicleSettingPanel.getVehicleId());
              signalGroupNr = Integer.parseUnsignedInt(vehicleSettingPanel.getSignalGroupNr());
              manualControl = vehicleSettingPanel.getManualControl();
              vehicleStatus = vehicleSettingPanel.getVehicleStatus();
              priorityClass = vehicleSettingPanel.getPriorityClass();
              punctualityClass = vehicleSettingPanel.getPunctualityClass();
              punctuality = Integer.parseUnsignedInt(vehicleSettingPanel.getPunctuality());
              vehicleLength = Integer.parseUnsignedInt(vehicleSettingPanel.getVehicleLength());
              vehicleSpeed = Integer.parseUnsignedInt(vehicleSettingPanel.getVehicleSpeed());
              distanceToStop = Integer.parseUnsignedInt(vehicleSettingPanel.getDistanceToStop());
              timeToStop = Integer.parseUnsignedInt(vehicleSettingPanel.getTimeToStop());
              journeyNr = Integer.parseUnsignedInt(vehicleSettingPanel.getJourneyNr());
              journeyType = vehicleSettingPanel.getJourneyType();
              route = Integer.parseUnsignedInt(vehicleSettingPanel.getRoute());
              direction = vehicleSettingPanel.getDirection();
              command = vehicleSettingPanel.getCommands();
              activation = Integer.parseUnsignedInt(vehicleSettingPanel.getActivation());
              latDeg = Integer.parseUnsignedInt(vehicleSettingPanel.getLatDeg());
              latMin = Integer.parseUnsignedInt(vehicleSettingPanel.getLatMin());
              latSec = Integer.parseUnsignedInt(vehicleSettingPanel.getLatSec());
              latSSec = Integer.parseUnsignedInt(vehicleSettingPanel.getLatSSec());
              longDeg = Integer.parseUnsignedInt(vehicleSettingPanel.getLongDeg());
              longMin = Integer.parseUnsignedInt(vehicleSettingPanel.getLongMin());
              longSec = Integer.parseUnsignedInt(vehicleSettingPanel.getLongSec());
              longSSec = Integer.parseUnsignedInt(vehicleSettingPanel.getLongSSec());
              year = Integer.parseUnsignedInt(vehicleSettingPanel.getYear());
              month = Integer.parseUnsignedInt(vehicleSettingPanel.getMonth());
              day = Integer.parseUnsignedInt(vehicleSettingPanel.getDay());
              hour = Integer.parseUnsignedInt(vehicleSettingPanel.getHour());
              minute = Integer.parseUnsignedInt(vehicleSettingPanel.getMinute());
              second = Integer.parseUnsignedInt(vehicleSettingPanel.getSecond());
              reserve1 = Integer.parseUnsignedInt(vehicleSettingPanel.getReserve1());
              reserve2 = Integer.parseUnsignedInt(vehicleSettingPanel.getReserve2());
              setEnabled(true);
              setButtonText();
              setVehicleTypeImage(vehicleType);
              break;
            default:
          }
        }
      }
    });
  }
  
  // PRIVATE ATTRIBUTE
  private String name;
  private int loopNr;                           // CVN: 1
  private VehicleType vehicleType;              // CVN: 2
  private int lineNr;                           // CVN: 3
  private int vehServiceNr;                     // CVN: 4
  private int companyNr;                        // CVN: 5
  private int vehicleId;                        // CVN: 6
  private int signalGroupNr;                    // CVN: 7 KAR
  private ManualControl manualControl;          // CVN: 7 VECOM
  private VehicleStatus vehicleStatus;          // CVN: 8
  private PriorityClass priorityClass;          // CVN: 9
  private PunctualityClass punctualityClass;    // CVN: 10
  private int punctuality;                      // CVN: 11
  private int vehicleLength;                    // CVN: 12
  private int vehicleSpeed;                     // CVN: 13
  private int distanceToStop;                   // CVN: 14
  private int timeToStop;                       // CVN: 15
  private int journeyNr;                        // CVN: 16 
  private JourneyType journeyType;              // CVN: 17
  private int route;                            // CVN: 18
  private Direction direction;                  // CVN: 18 VECOM
  private Commands command;                     // CVN: 19
  private int activation;                       // CVN: 20
  private int latDeg;                           // CVN: 21a
  private int latMin;                           // CVN: 21b
  private int latSec;                           // CVN: 21c
  private int latSSec;                          // CVN: 21d
  private int longDeg;                          // CVN: 21e
  private int longMin;                          // CVN: 21f
  private int longSec;                          // CVN: 21g
  private int longSSec;                         // CVN: 21h
  private int year;                             // CVN: 22a
  private int month;                            // CVN: 22b
  private int day;                              // CVN: 22c
  private int hour;                             // CVN: 22d
  private int minute;                           // CVN: 22e
  private int second;                           // CVN: 22f
  private int reserve1;                         // CVN: 23
  private int reserve2;                         // CVN: 24

  private VehicleSettingPanel vehicleSettingPanel;
  private ImageFactory imagefactory = new ImageFactory();

}// end class

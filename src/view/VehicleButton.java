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
 *        A mouse listener must be added from the class that is using this button. The RMB action should be handled in the calling class
 *        The attribute settings are controlled by a VehicleSetting class which shall be triggered by the RMB
 * @author Sander Kamps
 */
public class VehicleButton extends JButton{

  private static final long serialVersionUID = 1L;

  /**
   *  constructor
   */
  public VehicleButton(){
    super();
    setToolTipText("LMB is action.\r\nRMB is settings");
    initialize();
  }
  public VehicleButton(String a_name) {
    super(a_name);
    name = a_name;
    initialize();
  }


  // PUBLIC METHODS
  public void setButtonText(){
    setText("L" + loopNr + " FC" + signalGroupNr);
    setFont(new Font("Verdana", Font.PLAIN, 8));
  } 

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  private void setVehicleTypeImage(VehicleTypes vt){
    switch (vt){
    case BUS_CITY:
    case BUS_REG:
        setIcon(imagefactory.getImageIcon(VehicleTypes.BUS_CITY._name));
        break;
    case TRAM_CITY:
    case TRAM_REG:
        setIcon(imagefactory.getImageIcon(VehicleTypes.TRAM_CITY._name));
        break;
    case TAXI:
        setIcon(imagefactory.getImageIcon(VehicleTypes.TAXI._name));
        break;
    case AMBULANCE:
        setIcon(imagefactory.getImageIcon(VehicleTypes.AMBULANCE._name));
        break;
    case BRANDWEER:
        setIcon(imagefactory.getImageIcon(VehicleTypes.BRANDWEER._name));
        break;
    case POLITIE:
    	setIcon(imagefactory.getImageIcon(VehicleTypes.POLITIE._name));
    	break;
    default:
    	setIcon(imagefactory.getImageIcon(VehicleTypes.UNKNOWN._name));
    	break;
    }
  }
  
  public int getLoopNr() {
    return loopNr;
  }
  public VehicleTypes getVehicleType() {
    return vehicleType;
  }
  public int getLineNr() {
    return lineNr;
  }
  public int getVehServiceNr() {
    return vehServiceNr;
  }
  public int getCompanyNr() {
    return companyNr;
  }
  public int getVehicleId() {
    return vehicleId;
  }
  public int getSignalGroupNr() {
    return signalGroupNr;
  }
  public ManualControl getManualControl() {
    return manualControl;
  }
  public VehicleStatus getVehicleStatus() {
    return vehicleStatus;
  }
  public PriorityClass getPriorityClass() {
    return priorityClass;
  }
  public PunctualityClass getPunctualityClass() {
    return punctualityClass;
  }
  public int getPunctuality() {
    return punctuality;
  }
  public int getVehicleLength() {
    return vehicleLength;
  }
  public int getVehicleSpeed() {
    return vehicleSpeed;
  }
  public int getDistanceToStop() {
    return distanceToStop;
  }
  public int getTimeToStop() {
    return timeToStop;
  }
  public int getJourneyNr() {
    return journeyNr;
  }
  public JourneyType getJourneyType() {
    return journeyType;
  }
  public int getRoute() {
    return route;
  }
  public Direction getDir() {
    return dir;
  }
  public Commands getCommand() {
    return command;
  }
  public int getActivation() {
    return activation;
  }
  public int getLatDeg() {
    return latDeg;
  }
  public int getLatMin() {
    return latMin;
  }
  public int getLatSec() {
    return latSec;
  }
  public int getLatSSec() {
    return latSSec;
  }
  public int getLongDeg() {
    return longDeg;
  }
  public int getLongMin() {
    return longMin;
  }
  public int getLongSec() {
    return longSec;
  }
  public int getLongSSec() {
    return longSSec;
  }
  public int getYear() {
    return year;
  }
  public int getMonth() {
    return month;
  }
  public int getDay() {
    return day;
  }
  public int getHour() {
    return hour;
  }
  public int getMinute() {
    return minute;
  }
  public int getSecond() {
    return second;
  }
  public int getReserve1() {
    return reserve1;
  }
  public int getReserve2() {
    return reserve2;
  }
  public VehicleSettingPanel getVehicleSettingPanel() {
    return vehicleSettingPanel;
  }
  public ImageFactory getImagefactory() {
    return imagefactory;
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

  public enum VehicleTypes {
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
    private VehicleTypes(String a_Name, int a_Nr){
      _name = a_Name;
      _nr = a_Nr;
    }
    public  String getName(){
      return _name;
    }
    public int getNr(){
      return _nr;
    }
  }// end enum VehicleTypes

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
          vehicleSettingPanel.setProto();
          int ok = JOptionPane.showConfirmDialog(null,
              vehicleSettingPanel,
              "Vehicle Setting",
              JOptionPane.OK_CANCEL_OPTION,
              JOptionPane.PLAIN_MESSAGE); 
          switch (ok){
            case 0:
              loopNr = vehicleSettingPanel.getLoopNr();                         
              vehicleType = vehicleSettingPanel.getVehicleType();
              lineNr = vehicleSettingPanel.getLineNr();
              vehServiceNr = vehicleSettingPanel.getVehServiceNr();
              companyNr = vehicleSettingPanel.getCompanyNr();
              vehicleId = vehicleSettingPanel.getVehicleId();
              signalGroupNr = vehicleSettingPanel.getSignalGroupNr();
              manualControl = vehicleSettingPanel.getManualControl();
              vehicleStatus = vehicleSettingPanel.getVehicleStatus();
              priorityClass = vehicleSettingPanel.getPriorityClass();
              punctualityClass = vehicleSettingPanel.getPunctualityClass();
              punctuality = vehicleSettingPanel.getPunctuality();
              vehicleLength = vehicleSettingPanel.getVehicleLength();
              vehicleSpeed = vehicleSettingPanel.getVehicleSpeed();
              distanceToStop = vehicleSettingPanel.getDistanceToStop();
              timeToStop = vehicleSettingPanel.getTimeToStop();
              journeyNr = vehicleSettingPanel.getJourneyNr();
              journeyType = vehicleSettingPanel.getJourneyType();
              route = vehicleSettingPanel.getRoute();
              dir = vehicleSettingPanel.getDirection();
              command = vehicleSettingPanel.getCommands();
              activation = vehicleSettingPanel.getActivation();
              latDeg = vehicleSettingPanel.getLatDeg();
              latMin = vehicleSettingPanel.getLatMin();
              latSec = vehicleSettingPanel.getLatSec();
              latSSec = vehicleSettingPanel.getLatSSec();
              longDeg = vehicleSettingPanel.getLongDeg();
              longMin = vehicleSettingPanel.getLongMin();
              longSec = vehicleSettingPanel.getLongSec();
              longSSec = vehicleSettingPanel.getLongSSec();
              year = vehicleSettingPanel.getYear();
              month = vehicleSettingPanel.getMonth();
              day = vehicleSettingPanel.getDay();
              hour = vehicleSettingPanel.getHour();
              minute = vehicleSettingPanel.getMinute();
              second = vehicleSettingPanel.getSecond();
              reserve1 = vehicleSettingPanel.getReserve1();
              reserve2 = vehicleSettingPanel.getReserve2();
              setEnabled(true);
              setButtonText();
              setVehicleTypeImage(vehicleType);
              break;
            case 2:
              // do nothing
              break;
          }
        }
      }
    });
    vehicleSettingPanel = new VehicleSettingPanel(this);
  }
  
  // PRIVATE ATTRIBUTE
  private String name;
  private int loopNr;                           // CVN: 1
  private VehicleTypes vehicleType;             // CVN: 2
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
  private Direction dir;                        // CVN: 18 VECOM
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

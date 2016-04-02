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
    case BUS:
        setIcon(imagefactory.getImageIcon(VehicleTypes.BUS._name));
        break;
    case TRAM:
        setIcon(imagefactory.getImageIcon(VehicleTypes.TRAM._name));
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

  public String toString(){
    String s =               " LoopNr "              
        + loopNr               + " SignalGroupNr "       
        + signalGroupNr        + " Command "             
        + command              + " VehicleType "         
        + vehicleType          + " LineNr "              
        + lineNr               + " VehicleId "           
        + vehicleId            + " VehicleLength "       
        + vehicleLength        + " VehicleSpeed "        
        + vehicleSpeed         + " DistanceToStop "      
        + distanceToStop       + " TimeToStop "          
        + timeToStop           + " VehicleStatus "       
        + vehicleStatus        + " PriorityClass "       
        + priorityClass        + " PunctualityClass "    
        + punctualityClass     + " Punctuality "         
        + punctuality;
    return s;
  }
  // ENUMS

  public enum Directions {
    GEENINFO ("geen info",0), 
    RECHTS ("rechtsaf",1),
    LINKS ("linksaf",2),
    RECHT ("rechtdoor",3);

    private String _name;
    private int _nr;
    // constructor
    private Directions(String a_Name, int a_Nr){
      _name = a_Name;
      _nr = a_Nr;
    }
    public String getName(){
      return _name;
    }
    public int getNr(){
      return _nr;
    }
  }// end enum Directions

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
    BUS ("Bus",0), 
    TRAM ("Tram",1),
    TAXI ("Taxi",2),
    AMBULANCE ("Ambulance",3),
    BRANDWEER ("Brandweer",4),
    POLITIE ("Politie",5),
    UNKNOWN("Unknown",6);

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
    BUITENDIENST ("geen dienst",4);

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
  private Directions direction;                 // CVN: 7 VECOM
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

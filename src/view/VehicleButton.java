package view;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import images.ImageFactory;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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


  // PRIVATE METHODS
  private void initialize(){
    setIcon(imagefactory.getImageIcon("emptyVehicle"));
    setVerticalTextPosition(JButton.BOTTOM);
    setHorizontalTextPosition(JButton.CENTER);
    // handle the RMB action. This shall spawn a JOption with all the vehicle simulation settings
    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e) || e.isControlDown()){
          int ok = JOptionPane.showConfirmDialog(null,
              vehicleSettingPanel,
              "Vehicle Setting",
              JOptionPane.OK_CANCEL_OPTION,
              JOptionPane.PLAIN_MESSAGE); 
          switch (ok){
            case 0:
              loopNr = vehicleSettingPanel.getLoopNr();
              //              vehicleSettingPanel.handleOK(); TODO set all
              break;
            case 2:
              // do nothing
              break;
          }
        }
      }
    });
    /*    
    setLoopNr(0);
    setSignalGroupNr(0);
    setDirection(Directions.GEENINFO);
    setCommand(Commands.IN);
    setVehicleType(VehicleTypes.UNKNOWN);
    setLineNr(0);
    setWagonNr(0);
    setVehicleId(0);
    setVehicleLength(0);
    setVehicleSpeed(0);
    setDistanceToStop(0);
    setTimeToStop(0);
    setVehicleStatus(VehicleStatus.GEENINFO);
    setPriorityClass(PriorityClass.GEENINFO);
    setPunctualityClass(PunctualityClass.GEENINFO);
    setPunctuality(0);
    setButtonText();
     */
    vehicleSettingPanel = new VehicleSettingPanel(this);
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
  /*  
//  public int getLoopNr() {
//    return loopNr;
//  }
//  public void setLoopNr(int loopNr) {
//    this.loopNr = loopNr;
//    setButtonText();
//  }
//  
//  public int getSignalGroupNr() {
//    return signalGroupNr;
//  }
//  public void setSignalGroupNr(int signalGroupNr) {
//    this.signalGroupNr = signalGroupNr;
//    setButtonText();
//  }
//  
//  public int getDirection() {
//    return direction;
//  }
//  public void setDirection(Directions direction) {
//    switch(direction){
//      case GEENINFO:
//        this.direction = Directions.GEENINFO._nr;
//        break;
//      case RECHTS:
//        this.direction =Directions.RECHTS._nr;
//        break;
//      case LINKS:
//        this.direction =Directions.LINKS._nr;
//        break;
//      case RECHT:
//        this.direction =Directions.RECHT._nr;
//        break;
//      default:
//    }
//  }
//  
//  public int getCommand() {
//    return command;
//  }
//  public void setCommand(Commands c) {
//    switch(c){
//      case RESERVE:
//        this.command = Commands.RESERVE._nr;
//        break;
//      case IN:
//        this.command = Commands.IN._nr;
//        break;
//      case UIT:
//        this.command = Commands.UIT._nr;
//        break;
//      case VOOR:
//        this.command = Commands.VOOR._nr;
//        break;
//        default:
//    }
//  }
//  
//  public VehicleTypes getVehicleType() {
//    return vehicleType;
//  }
//  public void setVehicleType(VehicleTypes vt){
//    switch (vt){
//    case BUS:
//        setIcon(imagefactory.getImageIcon(VehicleTypes.BUS._name));
//        this.vehicleType = VehicleTypes.BUS._nr;
//        break;
//    case TRAM:
//        setIcon(imagefactory.getImageIcon(VehicleTypes.TRAM._name));
//        this.vehicleType = VehicleTypes.TRAM._nr;
//        break;
//    case TAXI:
//        setIcon(imagefactory.getImageIcon(VehicleTypes.TAXI._name));
//        this.vehicleType = VehicleTypes.TAXI._nr;
//        break;
//    case AMBULANCE:
//        setIcon(imagefactory.getImageIcon(VehicleTypes.AMBULANCE._name));
//        this.vehicleType = VehicleTypes.AMBULANCE._nr;
//        break;
//    case BRANDWEER:
//        setIcon(imagefactory.getImageIcon(VehicleTypes.BRANDWEER._name));
//        this.vehicleType = VehicleTypes.BRANDWEER._nr;
//        break;
//    case POLITIE:
//    	setIcon(imagefactory.getImageIcon(VehicleTypes.POLITIE._name));
//    	this.vehicleType = VehicleTypes.POLITIE._nr;
//    	break;
//    default:
//    	setIcon(imagefactory.getImageIcon(VehicleTypes.UNKNOWN._name));
//    	this.vehicleType = VehicleTypes.UNKNOWN._nr;
//    	break;
//    }
//  }
//  
//  public int getLineNr() {
//    return lineNr;
//  }
//  public void setLineNr(int lineNr) {
//    this.lineNr = lineNr;
//  }
//  
//  public int getWagonNr() {
//    return wagonNr;
//  }
//  public void setWagonNr(int wagonNr) {
//    this.wagonNr = wagonNr;
//  }
//  
//  public int getVehicleId() {
//    return vehicleId;
//  }
//  public void setVehicleId(int vehicleId) {
//    this.vehicleId = vehicleId;
//  }
//  
//  public int getVehicleLength() {
//    return vehicleLength;
//  }
//  public void setVehicleLength(int vehicleLength) {
//    this.vehicleLength = vehicleLength;
//  }
//  
//  public int getVehicleSpeed() {
//    return vehicleSpeed;
//  }
//  public void setVehicleSpeed(int vehicleSpeed) {
//    this.vehicleSpeed = vehicleSpeed;
//  }
//  
//  public int getDistanceToStop() {
//    return distanceToStop;
//  }
//  public void setDistanceToStop(int distanceToStop) {
//    this.distanceToStop = distanceToStop;
//  }
//  
//  public int getTimeToStop() {
//    return timeToStop;
//  }
//  public void setTimeToStop(int timeToStop) {
//    this.timeToStop = timeToStop;
//  }
//  public int getVehicleStatus() {
//    return vehicleStatus;
//  }
//  
//  public void setVehicleStatus(VehicleStatus vs) {
//    switch(vs){
//      case GEENINFO:
//        this.vehicleStatus = VehicleStatus.GEENINFO._nr;
//        break;
//      case ONDERWEG:
//        this.vehicleStatus = VehicleStatus.ONDERWEG._nr;
//        break;
//      case STOP1:
//        this.vehicleStatus = VehicleStatus.STOP1._nr;
//        break;
//      case EINDE:
//        this.vehicleStatus = VehicleStatus.EINDE._nr;
//        break;
//      case STOP2:
//        this.vehicleStatus = VehicleStatus.STOP2._nr;
//        break;
//      default:
//    }
//  }
//  public int getPriorityClass() {
//    return priorityClass;
//  }
//  
//  public void setPriorityClass(PriorityClass pc) {
//    switch(pc){
//      case GEENINFO:
//        this.priorityClass = PriorityClass.GEENINFO._nr;
//        break;
//      case GEEN:
//        this.priorityClass = PriorityClass.GEEN._nr;
//        break;
//      case CONDITIONEEL:
//        this.priorityClass = PriorityClass.CONDITIONEEL._nr;
//        break;
//      case ABSOLUUT:
//        this.priorityClass = PriorityClass.ABSOLUUT._nr;
//        break;
//      case ALARM:
//        this.priorityClass = PriorityClass.ALARM._nr;
//        break;
//        default:
//    }
//     
//  }
//  public int getPunctualityClass() {
//    return punctualityClass;
//  }
//  
//  public void setPunctualityClass(PunctualityClass puc) {
//    switch(puc){
//      case GEENINFO:
//        this.punctualityClass = PunctualityClass.GEENINFO._nr;
//        break;
//      case TELAAT:
//        this.punctualityClass = PunctualityClass.TELAAT._nr;
//        break;
//      case OPTIJD:
//        this.punctualityClass = PunctualityClass.OPTIJD._nr;
//        break;
//      case TEVROEG:
//        this.punctualityClass = PunctualityClass.TEVROEG._nr;
//        break;
//      case BUITENDIENST:
//        this.punctualityClass = PunctualityClass.BUITENDIENST._nr;
//        break;
//        default:
//    }
//  }
//  
//  public int getPunctuality() {
//    return punctuality;
//  }
//  public void setPunctuality(int punctuality) {
//    this.punctuality = punctuality;
//  }
   */  
  //TODO make generic methods for following methods

  /**
   * @brief Get Array all directions		
   * @return the names of all the direction as Array
   */
  public String[] getAllDirections_array(){
    ArrayList<String> list_al = new ArrayList<String>();	  
    for (Directions vt : Directions.values()){
      list_al.add(vt._name);
    }
    String[] list_a = new String[list_al.size()];

    return list_al.toArray(list_a);
  } 
  /**
   * @brief Get Array all commands		
   * @return the names of all the commands as Array
   */
  public String[] getAllCommands_array(){
    ArrayList<String> list_al = new ArrayList<String>();	  
    for (Commands vt : Commands.values()){
      list_al.add(vt._name);
    }
    String[] list_a = new String[list_al.size()];

    return list_al.toArray(list_a);
  }

  /**
   * @brief Get Array all vehicle types names		
   * @return the names of all the vehicle types as Array
   */
  public String[] getAllVehicleTypes_array(){
    ArrayList<String> list_al = new ArrayList<String>();	  
    for (VehicleTypes vt : VehicleTypes.values()){
      list_al.add(vt._name);
    }
    String[] list_a = new String[list_al.size()];

    return list_al.toArray(list_a);
  }


  /**
   * @brief Get Array all vehicle status		
   * @return the names of all the vehicle status as Array
   */
  public String[] getAllVehicleStatus_array(){
    ArrayList<String> list_al = new ArrayList<String>();	  
    for (VehicleStatus vt : VehicleStatus.values()){
      list_al.add(vt._name);
    }
    String[] list_a = new String[list_al.size()];

    return list_al.toArray(list_a);
  }

  /**
   * @brief Get Array all the priority classes		
   * @return the names of all the priority classes as Array
   */
  public String[] getAllPriorityClass_array(){
    ArrayList<String> list_al = new ArrayList<String>();	  
    for (PriorityClass vt : PriorityClass.values()){
      list_al.add(vt._name);
    }
    String[] list_a = new String[list_al.size()];

    return list_al.toArray(list_a);
  }

  /**
   * @brief Get Array all the punctuality classes		
   * @return the names of all the punctuality classes as Array
   */
  public String[] getAllPunctualityClass_array(){
    ArrayList<String> list_al = new ArrayList<String>();	  
    for (PunctualityClass vt : PunctualityClass.values()){
      list_al.add(vt._name);
    }
    String[] list_a = new String[list_al.size()];

    return list_al.toArray(list_a);
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

  // PRIVATE ATTRIBUTE
  private String name;
  private int loopNr;                           // CVN: 1
  private VehicleTypes vehicleType;             // CVN: 2
  private int lineNr;                           // CVN: 3
  private int vehServiceNr;                     // CVN: 4
  private int companyNr;                        // CVN: 5
  private int vehicleId;                        // CVN: 6
  private int signalGroupNr;                    // CVN: 7 + Direction 200,201,202
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

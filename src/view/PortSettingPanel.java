package view;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class PortSettingPanel extends JPanel {

  /**
   * Constructor
   */
  public PortSettingPanel() {
    setBorder(new TitledBorder(null, "Port settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    setLayout(new GridLayout(6, 1, 2, 2));
    initialize();
    loadAllFieldValues();
  }
  
  // PUBLIC METHODS
  /**
   * When Panel OK button is pressed all user input is read (again)
   * This allows the user to fill in all the fields without confirming each field individually
   */
  public void handleOK(){
    loadAllFieldValues();
  }
  
  public String toString(){
    String s = "com port " + comPort +
        " baud rate " + baudRate +
        " data bits " + dataBits +
        " parity " + parity +
        " stop bits " + stopBits +
        " flow " + flow;
    return s;
  }
  
  public String getComPort() {
    return comPort;
  }

  public void setComPort(String comPort) {
    comComBox.addItem(comPort);
  }

  public int getBaudRate() {
    return baudRate;
  }

  public void setBaudRate(int baudRate) {
    this.baudRate = baudRate;
  }

  public int getDataBits() {
    return dataBits;
  }

  public void setDataBits(int dataBits) {
    this.dataBits = dataBits;
  }
  
  public int getParity() {
    return parity;
  }

  public void setParity(Parity parity) {
    switch(parity){
      case EVEN:
        break;
      case NON:
        break;
      case ODD:
        break;
      default:
        break;
    }
  }
  public int getStopBits() {
    return stopBits;
  }

  public void setStopBits(int stopBits) {
    this.stopBits = stopBits;
  }

  public int getFlow() {
    return flow;
  }

  public void setFlow(Flow flow) {
    switch(flow){
      case HARDWARE:
        break;
      case NON:
        break;
      case XONXOFF:
        break;
      default:
        break;
    }
  }
  
  // PRIVATE METHODS
  private void initialize(){
    comboBoxList = new ArrayList<JComboBox<String>>();
    
//    comComBoxArray = TODO where should I get the port settings? A serial port manager needs to be implemented

//    comComBoxArray = Comport.getAsArray();
    comComBox = new JComboBox<String>(/*comComBoxArray*/);
    comComBox.addActionListener(new ComBoListener());
    comComBox.setName(comComBox_name);
    if (comComBox.getItemAt(1) != null)
      comComBox.setSelectedIndex(1);
    add(comComBox);
    
    baudComBox = new JComboBox<String>(baudComBoxArray);
    baudComBox.addActionListener(new ComBoListener());
    baudComBox.setName(baudComBox_name);
    if (baudComBox.getItemAt(6) != null)
      baudComBox.setSelectedIndex(6);
    add(baudComBox);
    
    dataBitsComBox = new JComboBox<String>(dataBitsComBoxArray);
    dataBitsComBox.addActionListener(new ComBoListener());
    dataBitsComBox.setName(dataBitsComBox_name);
    if (dataBitsComBox.getItemAt(1) != null)
      dataBitsComBox.setSelectedIndex(1);
    add(dataBitsComBox);
    
    parityComBoxArray = Parity.getAsArray();
    parityComBox = new JComboBox<String>(parityComBoxArray);
    parityComBox.addActionListener(new ComBoListener());
    parityComBox.setName(parityComBox_name);
    if (parityComBox.getItemAt(0) != null)
      parityComBox.setSelectedIndex(0);
    add(parityComBox);
    
    stopBitsComBox = new JComboBox<String>(stopBitsComBoxArray);
    stopBitsComBox.addActionListener(new ComBoListener());
    stopBitsComBox.setName(stopBitsComBox_name);
    if (stopBitsComBox.getItemAt(0) != null)
      stopBitsComBox.setSelectedIndex(0);
    add(stopBitsComBox);
    
    flowComboxArray = Flow.getAsArray();
    flowCombox = new JComboBox<String>(flowComboxArray);
    flowCombox.addActionListener(new ComBoListener());
    flowCombox.setName(flowCombox_name);
    if (flowCombox.getItemAt(0) != null)
      flowCombox.setSelectedIndex(0);
    add(flowCombox);
  }
  

  /**
   * Loads the values entered in all the fields. 
   */
  private void loadAllFieldValues() {
          for (JComboBox<String> combobox : comboBoxList){
                  readComBoBox(combobox);
          }    
  }
  
  /**
   * @brief Reads the current selection and copies it to the named VehicleButton attribute
   *            The target attribute is decided by combobox name.
   * @param combobox JComboBox<String> 
   */
  private void readComBoBox(JComboBox<String> combobox){
          if (!combobox.isEnabled())
                  return;
          
          if (combobox.getName() == comComBox_name){
            int index = combobox.getSelectedIndex();
//            Comport c = Comport.getAsType(index);
//            setComPort(c);
          }else if (combobox.getName() == baudComBox_name){
            int baudrate = Integer.parseInt((String) combobox.getSelectedItem());
            setBaudRate(baudrate);
          }else if (combobox.getName() == dataBitsComBox_name){
            int dataBits =  Integer.parseInt((String) combobox.getSelectedItem());
            setDataBits(dataBits);
          }else if (combobox.getName() == parityComBox_name){
            int index = combobox.getSelectedIndex();
            Parity p = Parity.getAsType(index);
            setParity(p);
          }else if (combobox.getName() == stopBitsComBox_name){
            int dataBits =  Integer.parseInt((String) combobox.getSelectedItem());
            setDataBits(dataBits);
          }else if (combobox.getName() == flowCombox_name){
            int index = combobox.getSelectedIndex();
            Flow f = Flow.getAsType(index);
            setFlow(f);
          }
  }
  
  
  // INNER CLASSES
  public class ComBoListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
      JComboBox<String> combobox = (JComboBox<String>)e.getSource();
      readComBoBox(combobox);
    }  
  }// end of inner class ComBoListener
  
  // ENUMS
  public interface portEnums<T>{ // TODO this doenst work with ENUMS.value
    T getAsType(String a_Name);
    T getAsType(int a_Nr);
    String[] getAsArray();
    String getName();
    int getNr();
  }
  
//  public enum Comport/* implements portEnums*/{
//    NON  ("none", 0),
//    COM1 ("COM 1",1),
//    COM2 ("COM 2",2),
//    COM3 ("COM 3",3),
//    COM4 ("COM 4",4);
//
//    private String _name;
//    private int _nr;
//    
//    // constructor
//    private Comport(String a_Name, int a_Nr){
//      _name = a_Name;
//      _nr = a_Nr;
//    }
//    public static Comport getAsType(String a_Name){
//      for (Comport vt:Comport.values()){
//        if(vt._name == a_Name)
//          return vt;
//      }
//      return null;
//    }
//    
//    public static Comport getAsType(int a_Nr){
//      for (Comport vt:Comport.values()){
//        if(vt._nr == a_Nr)
//          return vt;
//      }
//      return null;
//    }
//    
//    public static String[] getAsArray() {
//      ArrayList<String> list_al = new ArrayList<String>();    
//      for (Comport cp : Comport.values()){
//        list_al.add(cp._name);
//      }
//      String[] list_a = new String[list_al.size()];
//
//      return list_al.toArray(list_a);
//    }
//    
//    public String getName(){
//      return _name;
//    }
//    
//    public int getNr(){
//      return _nr;
//    }
//  }

  public enum Parity/* implements portEnums*/{
    NON ("N",0),
    ODD ("O",1),
    EVEN ("E",2);

    private String _name;
    private int _nr;
    
    // constructor
    private Parity(String a_Name, int a_Nr){
      _name = a_Name;
      _nr = a_Nr;
    }
    
    public static Parity getAsType(String a_Name){
      for (Parity p:Parity.values()){
        if(p._name == a_Name)
          return p;
      }
      return null;
    }
    
    public static Parity getAsType(int a_Nr){
      for (Parity p:Parity.values()){
        if(p._nr == a_Nr)
          return p;
      }
      return null;
    }
    
    public static String[] getAsArray() {
      ArrayList<String> list_al = new ArrayList<String>();    
      for (Parity p : Parity.values()){
        list_al.add(p._name);
      }
      String[] list_a = new String[list_al.size()];

      return list_al.toArray(list_a);
    }
    
    public String getName(){
      return _name;
    }
    
    public int getNr(){
      return _nr;
    }
  }
  
  public enum Flow/* implements portEnums*/{
    NON ("Geen",0),
    XONXOFF ("Xon/Xoff",1),
    HARDWARE ("Hardware",2);

    private String _name;
    private int _nr;
    
    // constructor
    private Flow(String a_Name, int a_Nr){
      _name = a_Name;
      _nr = a_Nr;
    }
    public static Flow getAsType(String a_Name){
      for (Flow f:Flow.values()){
        if(f._name == a_Name)
          return f;
      }
      return null;
    }
    
    public static Flow getAsType(int a_Nr){
      for (Flow vt:Flow.values()){
        if(vt._nr == a_Nr)
          return vt;
      }
      return null;
    }
    
    public static String[] getAsArray() {
      ArrayList<String> list_al = new ArrayList<String>();    
      for (Flow f : Flow.values()){
        list_al.add(f._name);
      }
      String[] list_a = new String[list_al.size()];

      return list_al.toArray(list_a);
    }
    
    public String getName(){
      return _name;
    }
    
    public int getNr(){
      return _nr;
    }
  }
  // PRIVATE ATTRIBUTES
  private JComboBox<String> comComBox;
  private JComboBox<String> baudComBox;
  private JComboBox<String> dataBitsComBox;
  private JComboBox<String> parityComBox;
  private JComboBox<String> stopBitsComBox;
  private JComboBox<String> flowCombox;
  
  private String  comComBox_name  = "comport";     
  private String  baudComBox_name = "baudrate";    
  private String  dataBitsComBox_name = "databits";
  private String  parityComBox_name = "parity";  
  private String  stopBitsComBox_name = "stopbits";
  private String  flowCombox_name = "flow";  
  
//  private String[] comComBoxArray;      
  private String[] baudComBoxArray = {"110","300","600","1200","2400","4800","9600","14400","19200","38400"};     
  private String[] dataBitsComBoxArray = {"7","8"};
  private String[] parityComBoxArray;   
  private String[] stopBitsComBoxArray = {"1","1.5","2"}; 
  private String[] flowComboxArray; 
  
  private ArrayList<JComboBox<String>> comboBoxList; // holds all comboboxes

  private String comPort;
  private int baudRate;
  private int dataBits;
  private int parity;
  private int stopBits;
  private int flow;
  
}// end class PortSettingPanel

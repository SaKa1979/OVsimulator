package view;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import view.ProtocolPanel.Proto;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class PortSettingPanel extends JPanel {

  private static final long serialVersionUID = 1L;

  /**
   * Constructor
   */
  public PortSettingPanel() {
    setBorder(new TitledBorder(null, "Port settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    setLayout(new GridLayout(6, 1, 2, 2));
    initialize();
  }
  
  // PUBLIC METHODS
  
  public String getComPort() {
    return (String)comComBox.getSelectedItem();
  }
  // Populated from Communicator.searchForPorts()
  public void setComPort(String cp) {
    comComBox.addItem(cp);
  }

  public Integer getBaudRate() {
    return (Integer)baudComBox.getSelectedItem();
  }

  public DataBit getDataBits() {
    return (DataBit)dataBitsComBox.getSelectedItem();
  }
   
  public Parity getParity() {
    return (Parity)parityComBox.getSelectedItem();
  }
  
  public StopBit getStopBits() {
    return (StopBit)stopBitsComBox.getSelectedItem();
  }

  public Flow getFlow() {
    return (Flow) flowCombox.getSelectedItem();
  }
  
  // PRIVATE METHODS
  private void initialize(){
    
    comComBox = new JComboBox<String>();
    comComBox.setName(comComBox_name);
    if (comComBox.getItemAt(1) != null)
      comComBox.setSelectedIndex(1);
    add(comComBox);
    
    baudComBox = new JComboBox<Integer>(baudComBoxArray);
    baudComBox.setName(baudComBox_name);
    if (baudComBox.getItemAt(6) != null)
      baudComBox.setSelectedIndex(6);
    add(baudComBox);
    
    parityComBox = new JComboBox<Parity>(Parity.values());
    parityComBox.setName(parityComBox_name);
    if (parityComBox.getItemAt(0) != null)
      parityComBox.setSelectedIndex(0);
    add(parityComBox);
  
    dataBitsComBox = new JComboBox<DataBit>(DataBit.values());
    dataBitsComBox.setName(dataBitsComBox_name);
    if (dataBitsComBox.getItemAt(3) != null)
      dataBitsComBox.setSelectedIndex(3);
    add(dataBitsComBox);
    
    stopBitsComBox = new JComboBox<StopBit>(StopBit.values());
    stopBitsComBox.setName(stopBitsComBox_name);
    if (stopBitsComBox.getItemAt(0) != null)
      stopBitsComBox.setSelectedIndex(0);
    add(stopBitsComBox);

    flowCombox = new JComboBox<Flow>(Flow.values());
    flowCombox.setName(flowCombox_name);
    if (flowCombox.getItemAt(0) != null)
      flowCombox.setSelectedIndex(0);
    add(flowCombox);
  }
  
  // INNER CLASSES

  // ENUMS

  public enum DataBit{
	  DATABITS_5 ("5",5),
	  DATABITS_6 ("6",6),
	  DATABITS_7 ("7",7),
	  DATABITS_8 ("8",8);

    private String _name;
    private int _nr;
    
    // constructor
    private DataBit(String a_Name, int a_Nr){
      _name = a_Name;
      _nr = a_Nr;
    }

    public String getName(){
      return _name;
    }
    
    public int getNr(){
      return _nr;
    }
  }
  
  public enum Parity{
	  PARITY_NONE ("N",0),
	  PARITY_ODD ("O",1),
	  PARITY_EVEN ("E",2),
	  PARITY_MARK ("M",3),
	  PARITY_SPACE ("S",4);

    private String _name;
    private int _nr;
    
    // constructor
    private Parity(String a_Name, int a_Nr){
      _name = a_Name;
      _nr = a_Nr;
    }
       
    public String getName(){
      return _name;
    }
    
    public int getNr(){
      return _nr;
    }
  }
  
  public enum StopBit{
	  STOPBITS_1 ("S1",1),
	  STOPBITS_2 ("S2",2),
	  STOPBITS_1_5 ("S1,5",3);

    private String _name;
    private int _nr;
    
    // constructor
    private StopBit(String a_Name, int a_Nr){
      _name = a_Name;
      _nr = a_Nr;
    }

    public String getName(){
      return _name;
    }
    
    public int getNr(){
      return _nr;
    }
  }
  
  public enum Flow{
	  FLOWCONTROL_NONE ("N",0),
	  FLOWCONTROL_RTSCTS_IN ("RTSCTS_IN",1),
	  FLOWCONTROL_RTSCTS_OUT ("RTSCTS_OUT",2),
	  FLOWCONTROL_XONXOFF_IN ("XONXOFF_IN",4),
	  FLOWCONTROL_XONXOFF_OUT ("XONXOFF_OUT",8);

    private String _name;
    private int _nr;
    
    // constructor
    private Flow(String a_Name, int a_Nr){
      _name = a_Name;
      _nr = a_Nr;
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
  private JComboBox<Integer> baudComBox;
  private JComboBox<DataBit> dataBitsComBox;
  private JComboBox<Parity> parityComBox;
  private JComboBox<StopBit> stopBitsComBox;
  private JComboBox<Flow> flowCombox;
  
  private String  comComBox_name  = "comport";     
  private String  baudComBox_name = "baudrate";
  private String  dataBitsComBox_name = "databits";
  private String  parityComBox_name = "parity";  
  private String  stopBitsComBox_name = "stopbits";
  private String  flowCombox_name = "flow";  
    
  private Integer[] baudComBoxArray = {110,300,600,1200,2400,4800,9600,14400,19200,38400};     
  
}// end class PortSettingPanel

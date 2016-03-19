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
  
  public Object getComPort() {
    return comComBox.getSelectedItem();
  }
  // Populated from Communicator
  public void setComPort(String comPort) {
    comComBox.addItem(comPort);
  }

  public Integer getBaudRate() {
    return baudRate;
  }
  public void setBaudRate(Integer br) {
    baudRate = br;
  }

  public DataBit getDataBits() {
    return dataBit;
  }
  public void setDataBits(DataBit dataBit) {
	  switch(dataBit){
	  case DATABITS_5:
		  dataBit = DataBit.DATABITS_5;
		  break;
	  case DATABITS_6:
		  dataBit = DataBit.DATABITS_6;
		  break;
	  case DATABITS_7:
		  dataBit = DataBit.DATABITS_7;
		  break;
	  case DATABITS_8:
		  dataBit = DataBit.DATABITS_8;
		  break;
	  default:
		  dataBit = DataBit.DATABITS_5;
	  }
  }
   
   public Parity getParity() {
    return parity;
  }
  public void setParity(Parity parity) {
    switch(parity){
      case PARITY_NONE:
    	  parity = Parity.PARITY_NONE;
        break;
      case PARITY_ODD:
    	  parity = Parity.PARITY_ODD;
        break;
      case PARITY_EVEN:
    	  parity = Parity.PARITY_EVEN;
        break;
      case PARITY_MARK:
    	  parity = Parity.PARITY_MARK;
          break;
      case PARITY_SPACE:
    	  parity = Parity.PARITY_SPACE;
          break;
      default:
    	  parity = Parity.PARITY_NONE;
        break;
    }
  }
  
  public StopBit getStopBits() {
    return stopBit;
  }
  public void setStopBits(StopBit stopBit) {
	  switch(stopBit){
	  case STOPBITS_1:
		  stopBit = StopBit.STOPBITS_1;
		  break;
	  case STOPBITS_2:
		  stopBit = StopBit.STOPBITS_2;
		  break;
	  case STOPBITS_1_5:
		  stopBit = StopBit.STOPBITS_1_5;
		  break;
	  default:
		  stopBit = StopBit.STOPBITS_1;
	  }
  }

  public Flow getFlow() {
    return flow;
  }
  public void setFlow(Flow flow) {
    switch(flow){
      case FLOWCONTROL_NONE:
    	  flow = Flow.FLOWCONTROL_NONE;
        break;
      case FLOWCONTROL_RTSCTS_IN:
    	  flow = Flow.FLOWCONTROL_RTSCTS_IN;
        break;
      case FLOWCONTROL_RTSCTS_OUT:
    	  flow = Flow.FLOWCONTROL_RTSCTS_OUT;
        break;
      case FLOWCONTROL_XONXOFF_IN:
    	  flow = Flow.FLOWCONTROL_XONXOFF_IN;
          break;
        case FLOWCONTROL_XONXOFF_OUT:
        	flow = Flow.FLOWCONTROL_XONXOFF_OUT;
          break;      
      default:
    	  flow = Flow.FLOWCONTROL_NONE;
        break;	
    }
  }
  
  // PRIVATE METHODS
  private void initialize(){
    comboBoxList = new ArrayList<JComboBox<Object>>();
    
    comComBox = new JComboBox<String>();
    comComBox.addActionListener(new ComBoListener());
    comComBox.setName(comComBox_name);
    if (comComBox.getItemAt(1) != null)
      comComBox.setSelectedIndex(1);
    add(comComBox);
    
    
    baudComBox = new JComboBox<Integer>(baudComBoxArray);
    baudComBox.addActionListener(new ComBoListener());
    baudComBox.setName(baudComBox_name);
    if (baudComBox.getItemAt(6) != null)
      baudComBox.setSelectedIndex(6);
    add(baudComBox);
    
    parityComBox = new JComboBox<Parity>(Parity.values());
    parityComBox.addActionListener(new ComBoListener());
    parityComBox.setName(parityComBox_name);
    if (parityComBox.getItemAt(0) != null)
      parityComBox.setSelectedIndex(0);
    add(parityComBox);
  
    dataBitsComBox = new JComboBox<DataBit>(DataBit.values());
    dataBitsComBox.addActionListener(new ComBoListener());
    dataBitsComBox.setName(dataBitsComBox_name);
    if (dataBitsComBox.getItemAt(1) != null)
      dataBitsComBox.setSelectedIndex(1);
    add(dataBitsComBox);
    
    stopBitsComBox = new JComboBox<StopBit>(StopBit.values());
    stopBitsComBox.addActionListener(new ComBoListener());
    stopBitsComBox.setName(stopBitsComBox_name);
    if (stopBitsComBox.getItemAt(0) != null)
      stopBitsComBox.setSelectedIndex(0);
    add(stopBitsComBox);

    flowCombox = new JComboBox<Flow>(Flow.values());
    flowCombox.addActionListener(new ComBoListener());
    flowCombox.setName(flowCombox_name);
    if (flowCombox.getItemAt(0) != null)
      flowCombox.setSelectedIndex(0);
    add(flowCombox);
    
    initialized = true;
  }
  

  /**
   * Loads the values entered in all the fields. 
   */
  private void loadAllFieldValues() {
          for (JComboBox<Object> combobox : comboBoxList){
                  readComBoBox(combobox);
          }    
  }
  
  /**
   * @brief Reads the current selection and copies it to the approriate attribute
   *            The target attribute is decided by combobox item class.
   * @param combobox JComboBox<Object> 
   */
  private void readComBoBox(JComboBox<Object> combobox){

	  if (!initialized)
		  return;
          
          if (combobox.getClass().equals(baudRate.getClass())){
            setBaudRate((Integer)combobox.getSelectedItem());
          }else if (combobox.getClass().equals(dataBit.getClass())){
            setDataBits((DataBit)combobox.getSelectedItem());
          }else if (combobox.getClass().equals(parity.getClass())){
            setParity((Parity)combobox.getSelectedItem());
          }else if (combobox.getClass().equals(dataBit.getClass())){
            setDataBits((DataBit)combobox.getSelectedItem());
          }else if (combobox.getClass().equals(flow.getClass())){
            setFlow((Flow)combobox.getSelectedItem());
          }
  }
  
  
  // INNER CLASSES
  public class ComBoListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
      JComboBox<Object> combobox = (JComboBox<Object>)e.getSource();
      readComBoBox(combobox);
    }  
  }// end of inner class ComBoListener

  
  // ENUMS

  public enum DataBit{
	  DATABITS_5 ("5",0),
	  DATABITS_6 ("6",1),
	  DATABITS_7 ("7",2),
	  DATABITS_8 ("8",4);

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
  
  private ArrayList<JComboBox<Object>> comboBoxList; // holds all comboboxes

  private Integer baudRate = 9600;
  private DataBit dataBit = DataBit.DATABITS_8;
  private Parity parity = Parity.PARITY_NONE;
  private StopBit stopBit = StopBit.STOPBITS_1;
  private Flow flow = Flow.FLOWCONTROL_NONE;
  
  private Boolean initialized = false;
  
}// end class PortSettingPanel

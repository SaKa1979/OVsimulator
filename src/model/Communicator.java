package model;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Observable;

import gnu.io.*;
import view.ViewManager;

public class Communicator extends Observable implements SerialPortEventListener {

  /**
   * Constructor
   */
  public Communicator(){
  }
  
  // PUBLIC METHOD
  
  public void addView(ViewManager vm){
    this.view = vm;
  }
  /**
   * @brief search for all the serial ports   
   *        pre style="font-size: 11px;": none post: adds all the found ports to a combo box on the GUI
   */
  public void searchForPorts()
  {
      ports = CommPortIdentifier.getPortIdentifiers();

      while (ports.hasMoreElements())
      {
        CommPortIdentifier currentPort = (CommPortIdentifier)ports.nextElement();

        //get only serial ports
        if (currentPort.getPortType() == CommPortIdentifier.PORT_SERIAL)
        {
          String comPort = currentPort.getName();
          portMap.put(currentPort.getName(), currentPort);
          view.getPortSettingPanel().setComPort(comPort);
          setChanged();   
        }
        notifyObservers();
      }
  }
  
  @Override
  public void serialEvent(SerialPortEvent arg0) {
    // TODO Auto-generated method stub
    
  }
  
  // PRIVATE METHODS

  // LISTENERS
  // PRIVATE ATTRIBUTE
  
  // to pass com port variables to the GUI
  ViewManager view;
  //map the port names to CommPortIdentifiers
  private HashMap portMap = new HashMap();
  //this is the object that contains the opened port
  private CommPortIdentifier selectedPortIdentifier = null;
  private SerialPort serialPort = null;
  //input and output streams for sending and receiving data
  private InputStream input = null;
  private OutputStream output = null;
  // is there a connection
  private boolean bConnected = false;
  //the timeout value for connecting with the port
  final static int TIMEOUT = 2000;
  //some ascii values for for certain things
  final static int SPACE_ASCII = 32;
  final static int DASH_ASCII = 45;
  final static int NEW_LINE_ASCII = 10;
  String comLog = "";
  
  // ENUMS
  //for containing the ports that will be found
  private Enumeration ports = null;

}

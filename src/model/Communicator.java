package model;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Observable;
import java.util.TooManyListenersException;

import controller.SimControler;
import gnu.io.*;
import view.PortSettingPanel;
import view.ViewManager;
import view.PortSettingPanel.DataBit;
import view.PortSettingPanel.Flow;
import view.PortSettingPanel.Parity;
import view.PortSettingPanel.StopBit;

public class Communicator extends Observable implements SerialPortEventListener {

  /**
   * Constructor
   */
  public Communicator(){
  }

  // PUBLIC METHODS

  public void addSimController(SimControler sC){
    simControler = sC;
  }

  public void setViewManager(ViewManager vm) {
    viewManager = vm;
  }

  public boolean isbConnected() {
    return bConnected;
  }
  
  /**
   * @brief search for all the serial ports   
   * @pre   adds all the found ports to a combo box of the GUI
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
        viewManager.getPortSettingPanel().setComPort(comPort);
      }
    }
  }

  /**
   * @brief connect to the selected port in the combo box
   * @pre   ports are already found by using the searchForPorts method
   * @post  the connected comm port is stored in commPort, otherwise,
   *        an exception is generated
   */

  public void connect(){
    PortSettingPanel portSettingPanel = viewManager.getPortSettingPanel();
    int baudRate = portSettingPanel.getBaudRate();
    DataBit dataBits = portSettingPanel.getDataBits();  
    Parity parity = portSettingPanel.getParity();    
    StopBit stopBits = portSettingPanel.getStopBits();  
    Flow flow = portSettingPanel.getFlow();
    String selectedPort = portSettingPanel.getComPort();
    selectedPortIdentifier = (CommPortIdentifier)portMap.get(selectedPort);

    CommPort commPort = null;

    try
    {
      //the method below returns an object of type CommPort
      commPort = selectedPortIdentifier.open("TigerControlPanel", TIMEOUT);
      //the CommPort object can be casted to a SerialPort object
      serialPort = (SerialPort)commPort;

      //for controlling GUI elements
      setConnected(true); 
    }
    catch (PortInUseException e)
    {
      comLog = selectedPort + " is in use.";
      viewManager.writeTobottomInfoComStatus(comLog, Color.RED);
    }
    catch (Exception e)
    {
      comLog = "Failed to open." + selectedPort;
      viewManager.writeTobottomInfoComStatus(comLog, Color.RED);
    }

    try {
      serialPort.setSerialPortParams(
          baudRate,
          dataBits.getNr(),
          stopBits.getNr(),
          parity.getNr());
    } catch (UnsupportedCommOperationException ex) {
      System.err.println(ex.getMessage());
    }
    try {
      serialPort.setFlowControlMode(
          flow.getNr());
    } catch (UnsupportedCommOperationException ex) {
      System.err.println(ex.getMessage());
    }

    // write the settings to gui
    comLog = selectedPort + "." 
        + baudRate + "." 
        + dataBits.getName() + "." 
        + parity.getName() + "." 
        + stopBits.getName() 
        + "." + flow.getName();
    viewManager.writeToBottomInfoComSettings(comLog, Color.BLACK);
    viewManager.writeTobottomInfoComStatus("Connected", Color.BLACK);
  }

  /**
   * @brief  open the input and output streams
   * @post:  initialized input and output streams for use to communicate data
   * @return initialization succes
   */
  public boolean initIOStream()
  {
    //return value for whether opening the streams is successful or not
    boolean result = false;

    try {
      //
      input = serialPort.getInputStream();
      output = serialPort.getOutputStream();

      result = true;
      return result;
    }
    catch (IOException e) {
      comLog = "I/O Streams failed to open.";
      viewManager.writeTobottomInfoComStatus(comLog, Color.RED);
      return result;
    }
  }

  /**
   * @brief starts the event listener that knows whenever data is available to be read
   * @pre:  an open serial port
   * @post: an event listener for the serial port that knows when data is recieved
   */
  public void initListener()
  {
    try
    {
      serialPort.addEventListener(this);
      serialPort.notifyOnDataAvailable(true);
    }
    catch (TooManyListenersException e)
    {
      String logText = "Too many listeners.";
      viewManager.writeTobottomInfoComStatus(logText, Color.RED);
    }
  }

  /**
   * @brief write data through the serial port
   * @param data: a byte to send
   */
  public void writeData(Byte singleData){
    try
    {
      viewManager.rxtxIndication(false, true);
      output.write(singleData);
      output.flush();
      //this is a delimiter for the data
      output.write(DASH_ASCII);
      output.flush();
      comLog = new String(new byte[] {singleData});
      viewManager.writeToFeedback(comLog, Color.BLACK, 8); // Just for testing purposes. TODO remove when finished

    }
    catch (Exception e)
    {
      comLog = "Failed to write data.";
      viewManager.writeTobottomInfoComStatus(comLog, Color.RED);
    }
    viewManager.rxtxIndication(false, false);
  }

  /**
   * @brief read data from serial port
   *        serial event is triggered when data is ready to be processed
   * @post: processing on the data it reads
   */
  @Override
  public void serialEvent(SerialPortEvent spe) {
    if (spe.getEventType() == SerialPortEvent.DATA_AVAILABLE)
    {
      try
      {
        viewManager.rxtxIndication(true, false);
        
        byte singleData = (byte)input.read();

        if (singleData != NEW_LINE_ASCII)
        {
          comLog = new String(new byte[] {singleData});
          viewManager.writeToFeedback(comLog, Color.GRAY, 8); // Just for testing purposes. TODO remove when finished
          /*
           * TODO hand over the received byte to the Protocol for further processing
           * Protocol p = simControler.getProtocol();
           * p.processData(singleData); 
           */
        }
        else
        {
          viewManager.writeToFeedback("\n", Color.GRAY, 8);
        }
      }
      catch (Exception e)
      {
        comLog = "Failed to read data.";
        viewManager.writeTobottomInfoComStatus(comLog, Color.RED);
      }
    }
    viewManager.rxtxIndication(false, false);
  }

  /**
   * @brief disconnect the serial port
   * @pre   an open serial port
   * @post  closed serial port
   */
  public void disconnect(){

    if (serialPort == null)
      return;

    try
    {
      serialPort.removeEventListener();
      serialPort.close();
      input.close();
      output.close();
      setConnected(false);

      comLog = "Disconnected.";
      viewManager.writeTobottomInfoComStatus(comLog, Color.BLACK);
    }
    catch (Exception e)
    {
      comLog = "Failed to close " + serialPort.getName();
      viewManager.writeTobottomInfoComStatus(comLog, Color.RED);
    }
  }
  
  // PRIVATE METHODS

  private void setConnected(boolean b) {
    bConnected = b;
  }

  // LISTENERS

  // PRIVATE ATTRIBUTE

  ViewManager viewManager = null;
  private SimControler simControler;
  //map the port names (String) to CommPortIdentifiers
  private HashMap<String, CommPortIdentifier> portMap = new HashMap<String, CommPortIdentifier>();
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
  private Enumeration<?> ports = null;

}

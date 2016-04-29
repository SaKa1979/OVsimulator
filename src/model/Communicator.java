package model;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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

public class Communicator extends Observable implements SerialPortEventListener, Runnable {

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
   * @brief search for all the serial ports in a separate thread
   *        to increase the application startup time
   * @pre   adds all the found ports to a combo box of the GUI
   */
  public void searchForPorts()
  {
    Thread thread = new Thread(this);
    thread.start();
  }

  @Override
  public void run() {
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
  };

  /**
   * @brief connect to the selected port in the combo box
   * @pre   ports are already found by using the searchForPorts method
   * @post  the connected comm port is stored in commPort, otherwise,
   *        an exception is generated
   */
  public boolean connect(){

    if (isbConnected())
      return false;

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

      //for controling GUI elements
      if (commPort != null)
        setConnected(true);
    }
    catch (PortInUseException e)
    {
      comLog = selectedPort + " is in use.";
      setConnected(false);
      viewManager.writeTobottomInfoComStatus(comLog, Color.RED);
    }
    catch (Exception e)
    {
      comLog = "Failed to open." + selectedPort;
      setConnected(false);
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
      setConnected(false);
    }
    try {
      serialPort.setFlowControlMode(
          flow.getNr());
    } catch (UnsupportedCommOperationException ex) {
      System.err.println(ex.getMessage());
      setConnected(false);
    }

    // write the settings to gui
    comLog = selectedPort + "." 
        + baudRate + "." 
        + dataBits.getName() + "." 
        + parity.getName() + "." 
        + stopBits.getName() 
        + "." + flow.getName();
    viewManager.writeToBottomInfoComSettings(comLog, Color.BLACK);

    if (bConnected){
      if(initIOStream()){
        initListener();
      }
    }
    return bConnected;
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
  public void writeData(ArrayList<Byte> data){
    try
    {
      viewManager.rxtxIndication(ComTransmission.TX);

      for(Byte b : data){
        output.write(b);
        
        if (viewManager.isShowComData())
          viewManager.writeToFeedback(0, "0x" + convertDec2HexString(b) + "\n", new Color(0, 230, 0), 12); //green
      }
      output.flush();
    }
    catch (Exception e)
    {
      comLog = "Failed to write data.";
      viewManager.writeTobottomInfoComStatus(comLog, Color.RED);
    }
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
        viewManager.rxtxIndication(ComTransmission.RX);

        byte singleData = (byte)input.read();

        if (singleData != NEW_LINE_ASCII)
        {
          //hand over the received byte to the Protocol for further processing
          Protocol p = simControler.getProtocol();
          if (p != null){
            if (viewManager.isShowComData())
              viewManager.writeToFeedback(0, "0x" + convertDec2HexString(singleData) + "\n", new Color(230, 230, 0), 12); //yellow
            p.processData(singleData);
          }
        }
        else
        {
          //nothing
        }
      }
      catch (Exception e)
      {
        comLog = "Failed to read data.";
        viewManager.writeTobottomInfoComStatus(comLog, Color.RED);
      }
    }
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
    }
    catch (Exception e)
    {
    }
  }

  /**
   * Converts decimal to hexidecimal String value
   * @param given Byte
   * @return the hexadecimal value in String represenation
   */
  public static final String convertDec2HexString(Byte hexByte){
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("%02X", hexByte));

    return sb.toString();
  }

  // PRIVATE METHODS

  private void setConnected(boolean b) {
    bConnected = b;
    if (bConnected)
      viewManager.writeTobottomInfoComStatus("Got " + selectedPortIdentifier.getName(), Color.BLACK);
    else
      viewManager.writeTobottomInfoComStatus("-", Color.BLACK);
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

  public enum ComTransmission {RX,TX,NONE}



}

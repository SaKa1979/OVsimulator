package model;
import java.awt.Color;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Observable;

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

	/**
	 * @brief search for all the serial ports   
	 *        Adds all the found ports to a combo box on the GUI
	 */
	public void searchForPorts()
	{
		ViewManager viewManager = simControler.getViewManager();
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
	 * @brief Connect to the selected serial port
	 */
	public void connect()
	{
		ViewManager viewManager = simControler.getViewManager();
		PortSettingPanel portSettingPanel = viewManager.getPortSettingPanel();
		int baudRate = portSettingPanel.getBaudRate();
		DataBit dataBits = portSettingPanel.getDataBits();  
		Parity parity = portSettingPanel.getParity();    
		StopBit stopBits = portSettingPanel.getStopBits();  
		Flow flow = portSettingPanel.getFlow();
		String selectedPort = (String)simControler.getViewManager().getPortSettingPanel().getComPort();
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
			simControler.getViewManager().connectedIndication(bConnected);
		}
		catch (PortInUseException e)
		{
			String logText = selectedPort + " is in use. (" + e.toString() + ")";
			viewManager.writeTobottomInfoComStatus(logText, Color.RED);
		}
		catch (Exception e)
		{
			String logText = "Failed to open " + selectedPort + "(" + e.toString() + ")";
			viewManager.writeTobottomInfoComStatus(logText, Color.RED);
		}

		try {
			serialPort.setSerialPortParams(
					baudRate,
					dataBits.getNr(),/* SerialPort.DATABITS_8, */
					stopBits.getNr(),/*SerialPort.STOPBITS_1, */
					parity.getNr()); /*SerialPort.PARITY_NONE);*/
		} catch (UnsupportedCommOperationException ex) {
			System.err.println(ex.getMessage());
		}
		try {
			serialPort.setFlowControlMode(
					flow.getNr());  /*SerialPort.FLOWCONTROL_NONE);*/
		} catch (UnsupportedCommOperationException ex) {
			System.err.println(ex.getMessage());
		}
	}


	@Override
	public void serialEvent(SerialPortEvent arg0) {
		// TODO Auto-generated method stub
	}

	// PRIVATE METHODS

	private void setConnected(boolean b) {
		bConnected = b;
	}

	// LISTENERS

	// PRIVATE ATTRIBUTE

	private SimControler simControler;
	//map the port names (String) to CommPortIdentifiers
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

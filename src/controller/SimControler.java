package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Communicator;
import view.ViewManager;
import view.PortSettingPanel;
import view.PortSettingPanel.DataBit;
import view.PortSettingPanel.Flow;
import view.PortSettingPanel.Parity;
import view.PortSettingPanel.StopBit;
import view.ProtocolPanel;
import view.ProtocolPanel.Proto;
import view.VehicleButton;

public class SimControler implements Event {

  /**
   * Constructor
   */
  public SimControler(){
    initialize();
  }

  // PUBLIC METHOD
  public ViewManager getViewManager(){
	  return viewManager;
  }
  
  // PRIVATE METHOD
  private void initialize(){

    viewManager.addEventSubscriber(this);
    communicator.addSimController(this);
    communicator.addObserver(viewManager);
    communicator.searchForPorts();
  }
  
  //LISTENERS and SIGNALS
  /**
   * @brief Is called whenever some action is taken by the callee
   * @param o When the method is called, the callee may pass an object.
   */
  public void signal(Object o){
	  if (o instanceof PortSettingPanel){
		  PortSettingPanel portSettingPanel = (PortSettingPanel)o;
		  String port = (String) portSettingPanel.getComPort();
		  int baudRate = portSettingPanel.getBaudRate();
		  DataBit dataBits = portSettingPanel.getDataBits();  
		  Parity parity = portSettingPanel.getParity();    
		  StopBit stopBits = portSettingPanel.getStopBits();  
		  Flow flow = portSettingPanel.getFlow();
		  String s_portSetting = port + "|" + baudRate + "|" + dataBits.getName() + "|" + parity.getName() + "|" + stopBits.getName() + "|" + flow.getName();
		  viewManager.writeToBottomInfoComSettings(s_portSetting,Color.BLACK);
		  communicator.connect();
	  }else if (o instanceof ProtocolPanel){
		  ProtocolPanel pp = (ProtocolPanel)o;
		  Proto proto = pp.getSelectedProto();
		  viewManager.writeToBottomProto(proto.name());
		  if (proto == Proto.KAR){
			  viewManager.writeToBottomProtoXtraInfo(Integer.toString(pp.getKar_sid()));
		  }

	  }else if (o instanceof VehicleButton){
		  VehicleButton vb = (VehicleButton)o;
		  
		  viewManager.writeToFeedback(vb.toString(), Color.black, 8);
	  }
  }
  
  // PRIVATE ATTRIBUTES
  ViewManager viewManager = new ViewManager(800, 800);
  Communicator communicator = new Communicator();
  
}// end ov class SimControler

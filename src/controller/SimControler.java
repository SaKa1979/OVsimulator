package controller;

import java.awt.Color;
import java.util.ArrayList;

import model.Communicator;
import model.KarProtocol;
import model.Persister;
import model.Protocol;
import model.VecomProtocol;
import view.ViewManager;
import view.PortSettingPanel;
import view.ProtocolPanel;
import view.ProtocolPanel.Proto;
import view.VehicleButton;

public class SimControler implements Event {

  /**
   * Constructor
   */
  public SimControler(){
    viewManager.addEventSubscriber(this);
    viewManager.getVehicleSimulation().addEventSubscriber(this);
    communicator.addEventSubscriber(this);
    communicator.setViewManager(viewManager);
    communicator.searchForPorts();
    persister = new Persister(this);
  }

  // PUBLIC METHOD
  public Protocol getProtocol(){
    return protocol;
  }

  //LISTENERS and SIGNALS
  /**
   * @brief Is called whenever some action is taken by the callee
   * @param a_obj When the method is called, the callee may pass an object.
   * @param a_arg When the method is called, the callee may pass an object.
   * - Signal is called: by the ViewManager when a PortSetting is changed.
   *                     by the ViewManager when a Protocol has changed
   *                     by the VehicleButton when it is pressed
   *                     by the Protocol when a message needs to be passed to the Communicator
   *                     by the Protocol when a message needs to be passed to the ViewManager
   */
  public void signal(Object a_obj, Object a_arg){
    if(a_obj instanceof ViewManager){
      String arg = (String)a_arg;
      if(arg.equals("openEvent")){
        persister.openFile();
      }else if (arg.equals("saveEvent")){
        persister.saveFile();
      }else if(arg.equals("saveAsEvent")){
        persister.saveAsFile();
      }else if (arg.equals("closeWindowEvent")){
        persister.closeNoSave();
      }  
    }else if (a_obj instanceof PortSettingPanel){
      communicator.disconnect();
      communicator.connect();
    }else if (a_obj instanceof ProtocolPanel){
      communicator.disconnect();
      ProtocolPanel pp = (ProtocolPanel)a_obj;
      Proto proto = pp.getSelectedProto();
      viewManager.writeToBottomProto(proto.name(), Color.BLACK);
      switch (proto){
        case KAR:
          protocol = new KarProtocol();
          protocol.addEventSubscriber(this);
          communicator.connect();
          viewManager.writeToBottomProtoXtraInfo("SID :" + pp.getKarSid(), Color.BLACK);
          break;
        case VECOM:
          protocol = new VecomProtocol();
          protocol.addEventSubscriber(this);
          communicator.connect();
          viewManager.writeToBottomProtoXtraInfo("VCU address:" + pp.getVcuAddress(), Color.BLACK);
          break;
        default:
          protocol = null;
      }
    }else if (a_obj instanceof VehicleButton){
      VehicleButton vb = (VehicleButton)a_obj;
      if (protocol != null){
        if (communicator.isbConnected()){
          protocol.createSerialMessage(vb);
          viewManager.writeVehicleButtonSetting(vb, protocol);
        }else{
          viewManager.writeToFeedback(0, "No connection available at the moment.", Color.RED, 10);
        }
      }else{
        viewManager.writeToFeedback(0, "No protocol selected.", Color.RED, 8);
      }
    }else if (a_obj instanceof Protocol){
      Protocol proto = (Protocol)a_obj;
      if (protocol != null){
        if (communicator.isbConnected()){
          if (a_arg instanceof String)
            viewManager.writeToFeedback(0, (String)a_arg, Color.BLACK, 12);
          communicator.writeData(proto.getSendMessage());
        }else{
          viewManager.writeToFeedback(0, "No connection available at the moment.", Color.RED, 8);
        }
      }else{
        viewManager.writeToFeedback(0, "No protocol selected.", Color.RED, 8);
      }
    }else if (a_obj instanceof Communicator){
      if (protocol != null){
        protocol.processData((byte)a_arg);
      }
    }
  }

  public void setPersistentObjects(ProtocolPanel protocolPanel, ArrayList<Object> a_list) {
    protocolPanel.setSelectedProto((Proto)a_list.get(0));
    protocolPanel.setKarSid((String) a_list.get(1));
    protocolPanel.setVcuAddress((String) a_list.get(2));
    protocolPanel.setKarKey((String) a_list.get(3));
  } 

  public ArrayList<Object> getPersistentObjects(ProtocolPanel a_protocolPanel) {
    ArrayList<Object> list = new ArrayList<Object>();
    list.add(a_protocolPanel.getSelectedProto());
    list.add(a_protocolPanel.getKarSid());
    list.add(a_protocolPanel.getVcuAddress());
    list.add(a_protocolPanel.getKarKey());
    return list;
  }

  // PRIVATE ATTRIBUTES
  ViewManager viewManager = ViewManager.getInstance();
  Communicator communicator = new Communicator();
  Protocol protocol;
  Persister persister;

}// end ov class SimControler

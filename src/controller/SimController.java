package controller;

import java.awt.Color;

import model.Communicator;
import model.Persister;
import model.Protocol;
import model.kar.KarProtocol;
import model.vecom.VecomProtocol;
import view.PortSettingPanel;
import view.ProtocolPanel;
import view.ProtocolPanel.Proto;
import view.VehicleButton;
import view.ViewManager;

public class SimController implements Event {

  /**
   * Constructor
   */
  public SimController(){
    viewManager.addEventSubscriber(this);
    viewManager.getVehicleSimulation().getProtocolCard(Proto.KAR).addEventSubscriber(this);
    viewManager.getVehicleSimulation().getProtocolCard(Proto.VECOM).addEventSubscriber(this);
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
  @Override
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
    } else if (a_obj instanceof VehicleButton){
      VehicleButton vb = (VehicleButton)a_obj;
      if (protocol != null){
        if (communicator.isbConnected()){
          protocol.createSerialMessage(vb.getProtocolMessage());
          viewManager.writeVehicleButtonSetting(vb, protocol);
        }else{
          viewManager.writeToFeedback(0, "No connection available at the moment.", Color.RED, 10);
        }
      }else{
        viewManager.writeToFeedback(0, "No protocol selected.", Color.RED, 8);
      }
    } else if (a_obj instanceof Protocol){
      Protocol proto = (Protocol)a_obj;
      if (protocol != null){
        if (communicator.isbConnected()){
          if (a_arg instanceof String)
            viewManager.writeToFeedback(0, (String)a_arg, Color.BLACK, 12);
          communicator.writeData(proto.getSendMessage()); // Got byte(s) from protocol to be send to serial
        }else{
          viewManager.writeToFeedback(0, "No connection available at the moment.", Color.RED, 8);
        }
      }else{
        viewManager.writeToFeedback(0, "No protocol selected.", Color.RED, 8);
      }
    } else if (a_obj instanceof Communicator){
      if (protocol != null){
        protocol.processData((byte)a_arg); // Got byte from serial to be send to protocol
      }
    }
  }

  // PRIVATE ATTRIBUTES
  ViewManager viewManager = ViewManager.getInstance();
  Communicator communicator = new Communicator();
  Protocol protocol;
  Persister persister;

}// end ov class SimControler

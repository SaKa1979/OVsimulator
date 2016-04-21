package controller;

import java.awt.Color;
import static model.Communicator.*;
import model.Communicator;
import model.KarProtocol;
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
    initialize();
  }

  // PUBLIC METHOD
  public ViewManager getViewManager(){
    return viewManager;
  }

  public Protocol getProtocol(){
    return protocol;
  }

  // PRIVATE METHOD
  private void initialize(){
    viewManager.addEventSubscriber(this);
    communicator.addSimController(this);
    communicator.setViewManager(viewManager);
    communicator.searchForPorts();
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
    if (a_obj instanceof PortSettingPanel){
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
          viewManager.writeToBottomProtoXtraInfo("SID :" + Integer.toString(pp.getKar_sid()), Color.BLACK);
          break;
        case VECOM:
          protocol = new VecomProtocol();
          protocol.addEventSubscriber(this);
          communicator.connect();
          viewManager.writeToBottomProtoXtraInfo("VCU address:" + convertDec2HexString(pp.getVCU_address()), Color.BLACK);
          break;
        default:
          protocol = null;
      }
    }else if (a_obj instanceof VehicleButton){
      VehicleButton vb = (VehicleButton)a_obj;
      if (protocol != null){
        if (communicator.isbConnected()){
          protocol.createSerialMessage(vb);
        }else{
          viewManager.writeToFeedback(0, "No connection available at the moment.", Color.RED, 8);
        }
      }else{
        viewManager.writeToFeedback(0, "No protocol selected.", Color.RED, 8);
      }
    }else if (a_obj instanceof Protocol){
      Protocol proto = (Protocol)a_obj;
      if (protocol != null){
        if (communicator.isbConnected()){
          communicator.writeData(proto.getSendMessage());
          if (a_arg instanceof String)
            viewManager.writeToFeedback(0, (String)a_arg, Color.BLACK, 8);
        }else{
          viewManager.writeToFeedback(0, "No connection available at the moment.", Color.RED, 8);
        }
      }else{
        viewManager.writeToFeedback(0, "No protocol selected.", Color.RED, 8);
      }
    }
  }

  // PRIVATE ATTRIBUTES
  ViewManager viewManager = ViewManager.getInstance();
  Communicator communicator = new Communicator();
  Protocol protocol;

}// end ov class SimControler

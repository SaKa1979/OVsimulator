package controller;

import java.awt.Color;
import model.Communicator;
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
   * @param o When the method is called, the callee may pass an object.
   */
  public void signal(Object o){
    if (o instanceof PortSettingPanel){
      if (communicator.isbConnected()){
        communicator.disconnect();
      }
      communicator.connect();
      if(communicator.isbConnected()){
        if(communicator.initIOStream()){
          communicator.initListener();
        }
      }
    }else if (o instanceof ProtocolPanel){
      ProtocolPanel pp = (ProtocolPanel)o;
      Proto proto = pp.getSelectedProto();
      viewManager.writeToBottomProto(proto.name(), Color.BLACK);
      if (proto == Proto.KAR){
        viewManager.writeToBottomProtoXtraInfo(Integer.toString(pp.getKar_sid()), Color.BLACK);
      }
    }else if (o instanceof VehicleButton){
      VehicleButton vb = (VehicleButton)o;
      if (communicator.isbConnected()){
        //TODO serial write
        viewManager.writeToFeedback(vb.toString(), Color.blue, 8);
      }else{
        viewManager.writeToFeedback("No connection available at the moment.", Color.red, 8);
      }
    }
  }

  // PRIVATE ATTRIBUTES
  ViewManager viewManager = ViewManager.getInstance();
  Communicator communicator = new Communicator();

}// end ov class SimControler

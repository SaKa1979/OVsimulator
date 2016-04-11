package model;

import java.util.ArrayList;

import controller.Event;
import view.VehicleButton;

public abstract class Protocol {

  // PUBLIC METHODS  
  /**
   * @brief The subsriber gets a signal when a certain event takes place.
   * @param a_subscriber
   */
  public void addEventSubscriber(Event a_subscriber){
    subscriber  = a_subscriber;
  }

  public void signalSubscriber(){
    subscriber.signal(this);
  }
  
  /**
   * @brief create a message from a given VehicleButton
   * @param vb: The calling VehicleButton
   * @return The message in Bytes
   */
  public abstract ArrayList<Byte> createSerialMessage(VehicleButton vb);
  
  /**
   * @brief recreates the message from given Bytes
   * @param b one Byte of data
   */
  public abstract void processData(Byte b);
  

  // PRIVATE ATTRIBUTES
  private Event subscriber;
} // end of class Protocol

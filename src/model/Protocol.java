package model;

import java.util.ArrayList;

import controller.Event;

public abstract class Protocol {

  // PUBLIC METHODS  
  /**
   * @brief The subsriber gets a signal when a certain event takes place.
   * @param a_subscriber
   */
  public void addEventSubscriber(Event a_subscriber){
    subscriber  = a_subscriber;
  }

  public void signalSubscriber(Object a_arg){
    subscriber.signal(this, a_arg);
  }
  
  public ArrayList<Byte> getSendMessage() {
    return sendMessage;
  }
  
  /**
   * @brief create a message from a given VehicleButton
   * @param vb: The calling VehicleButton
   * @return The message in Bytes
   */
  public abstract ArrayList<Byte> createSerialMessage(KarMessage message);
  
  /**
   * @brief recreates the message from given Bytes
   * @param b one Byte of data
   */
  public abstract void processData(Byte b);
  

  // PRIVATE ATTRIBUTES
  private Event subscriber;
  protected ArrayList<Byte> receivedMessage = null;
  protected ArrayList<Byte> sendMessage = null;
} // end of class Protocol

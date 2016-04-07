package controller;

import java.util.ArrayList;

import model.Protocol;
import view.VehicleButton;

public class VecomProtocol extends Protocol {

  // PUBLIC METHODS
  public VecomProtocol(){
    transmissionCounter = 0;
    overloop = 0; // start with 'arrival at loop' 
  }

  @Override
  public ArrayList<Byte> createSerialMessage(VehicleButton vb) {
    ArrayList<Byte> msg = new ArrayList<Byte>();

    transmissionCounter += 1;
    System.out.println("" + transmissionCounter );
    /** 
     * Byte 0 default 0x29
     */
    msg.add((byte)0x29);

    /** 
     * Byte 1 default 0x0D
     */
    msg.add((byte)0x0D);

    /** 
     * Byte 2 vehicleType 
     */
    {
      switch (vb.getVehicleType()){
        case POLITIE:
          msg.add((byte)0x10); // Police
          break;
        case BRANDWEER:
          msg.add((byte)0x08); // Fire brigade
          break;
        case AMBULANCE:
          msg.add((byte)0x0C); // Ambulance
          break;
        case TAXI:
          msg.add((byte)0x04); // Taxi
          break;
        case TRAM_CITY:
          msg.add((byte)0x80); // City tram
          break;
        case BUS_CITY:
          msg.add((byte)0xA0); // City Bus
          break;
        case TRAM_REG:
          msg.add((byte)0xC0); // Regional tram
          break;
        case BUS_REG:
          msg.add((byte)0xE0); // Regional bus
          break;
        default:
      }
    }

    /**
     * Byte 3 LineNumber lo
     * Sets the given linenumber attribute with max on 0xFF
     */
    {
      msg.add((byte)(vb.getLineNr() % 256));
    }

    /**
     * Byte 4 LineNumber hi (4 MSB) | VehServiceNr lo (4 LSB)
     * -lineNumber 0- 16383
     * -vehSrvNr : vehicle service number 0 - 1023
     */
    {
      int temp_byte = vb.getLineNr() / 256;
      switch(vb.getVehServiceNr() % 4){
        case 0:
          temp_byte |= 0x00; 
          break;
        case 1:
          temp_byte |= 0x40;
          break;
        case 2:
          temp_byte |= 0x80;
          break;
        case 3:
          temp_byte |= 0xC0;
          break;
        default:
      }
      msg.add((byte)temp_byte);
    }

    /**
     * Byte 5 holds the upper byte of the vehicle service number
     * -vehSrvNr : vehicle service number 0 - 1023
     */
    {
      msg.add((byte)(vb.getVehServiceNr() / 4));
    }

    /**
     * Byte 6 journeyType aka cat (b7b6) | punctualityClass aka pun (b5b4)
     * -category
     * -punctuality class
     * -intely 2 bit !NOT USED!
     * -bcd 2 bit for bcd !NOT USED!
     */
    {
      byte temp_byte = 0;

      switch (vb.getJourneyType()){
        case DIENST:
          temp_byte |= 0x00; // Lijndienst
          break;
        case REMISE_UIT:
          temp_byte |= 0x40; // Uitruk
          break;
        case REMISE_IN:
          temp_byte |= 0x80; // Inruk
          break;
        case DEADRUN:
          temp_byte |= 0xC0; // No service
          break;
        default:
          temp_byte |= 0xC0; // No service
      }

      switch (vb.getPunctualityClass()){
        case NORMAL:
          temp_byte |= 0x00; // Normal
          break;
        case SYSTEM:
          temp_byte |= 0x10; // System
          break;
        case RESERVE1:
          temp_byte |= 0x20; // Reserved
          break;
        case RESERVE2:
          temp_byte |= 0x30; // Reserved
          break;
        default:
          temp_byte |= 0x00; // Normal
      }
      msg.add((byte)temp_byte);
    }

    /**Byte 7 staff number lo
     * -staffNr 0 - 16777215 !NOT USED IN CVN!
     */
    msg.add((byte)0);

    /**
     * Byte 8 staff number mo
     * -staffNr 0 - 16777215 !NOT USED IN CVN!
     */
    msg.add((byte)0);

    /**
     * Byte 9 staff number ho
     * -staffNr 0 - 16777215 !NOT USED IN CVN!
     */
    msg.add((byte)0);

    /**
     * Byte 10 vehicleId aka fleetnumber lo
     * -fleetNr 0 - 1048575
     */
    msg.add((byte)(vb.getVehicleId() % 256));

    /**
     * Byte 11 vehicleId aka fleetnumber mo
     * -fleetNr 0 - 1048575
     */
    msg.add((byte)(vb.getVehicleId() / 256));

    /**
     * Byte 12 transmissionCounter (4 msb) | vehicleId aka fleetnumber ho (4 lsb)
     * -fleetNr : 0 - 1048575
     * -transCounter : 1 - 128
     */
    msg.add((byte)(transmissionCounter << 4));
    {
      int temp_byte = vb.getVehicleId() / 65536;
      temp_byte |= transmissionCounter << 4;

      msg.add((byte)temp_byte);    
    }

    /**
     * Byte 13 unknown (b7b6b5) | koppelbit (b4) | manual (4 lsb)
     * -manual 0 - 7
     * -leave !NOT USED IN CVN!
     * -koppel !NOT USED IN CVN!
     * -unknown !NOT USED IN CVN! 
     */
    {
      switch(vb.getManualControl()){
        case NOMANUALCONTROL:
          msg.add((byte) 0x00);
          break;
        case TURNRIGHT:
          msg.add((byte) 0x02);
          break;
        case TURNLEFT:
          msg.add((byte) 0x01);
          break;
        case FORWARD:
          msg.add((byte) 0x03);
          break;
        case READYTOSTART:
        case RTS_F:
        case RTS_TL:
        case RTS_TR:
          msg.add((byte) 0x00);
          break;
        default:
          msg.add((byte) 0x00);
          break;
      }
    }
    /**
     * Byte 14 over loop (b7)| direction (b6b5) | not used (b4) | loop number (4 lsb)
     * overloop : 0 = arrival at loop, 1 = departure at loop
     * direct 2 bits 0 - 3
     * loopNr 1 - 8 : 
     */
    {
      byte temp_byte = 0;
      overloop = (byte) ~overloop; // send message twice to mimmick a full loop ocupation

      temp_byte |= (overloop & 0x80);

      switch(vb.getDir()){
        case BACKWARDS:
          temp_byte |= 0x03;
          break;
        case FORWARD:
          temp_byte |= 0x01;
          break;
        case RESERVED:
          temp_byte |= 0x02;
          break;
        case UNKNOWN:
          temp_byte |= 0x00;
          break;
        default:
          temp_byte |= 0x00;
          break;
      }
      temp_byte |= vb.getLoopNr() & 0xFF;

      msg.add(temp_byte);
    }

    addCRC(msg);
    addDLE(msg);

    // byte ETX
    msg.add((byte) 0x03);

    return msg;
  }

  @Override
  public void processData(Byte b) {
    // TODO Auto-generated method stub
  }

  // PROTECTED METHODS
  @Override
  protected void addCRC(ArrayList<Byte> message) {
    byte temp = 0, crc1 = 0, crc2 = 0;

    for (int i = 0; i < message.size(); i++ ){
      temp = crc1;
      crc1 = (byte) (crc2 ^ message.get(i));
      crc2 = temp;
    }

    message.add(crc1);
    message.add(crc2);
  }

  // PRIVATE METHODS

  /**
   * When the data frame of the given message contains a key word byte
   * than this byte needs to be followed by a DLE value
   * @param message: message containing header-dataframe-CRC
   */
  private void addDLE(ArrayList<Byte> message){
    int numberOfKeyWords = 0;

    // first lets count the number of DLE's we need to insert
    for (int i = 0; i < message.size(); i++ ){
      switch(message.get(i)){
        case (byte) 0xF1:
          numberOfKeyWords += 1;
        break;
        case (byte) 0xF2:
          numberOfKeyWords += 1;
        break;
        case (byte) 0xF3:
          numberOfKeyWords += 1;
        break;
        case (byte) 0xF4:
          numberOfKeyWords += 1;
        break;
      }
    }
    // then insert the DLE at right position
    for (int i = 0; i < message.size()+ numberOfKeyWords; i++ ){
      switch(message.get(i)){
        case (byte) 0xF1:
          message.add(i, (byte) 0x03);
        break;
        case (byte) 0xF2:
          message.add(i, (byte) 0x03);
        break;
        case (byte) 0xF3:
          message.add(i, (byte) 0x03);
        break;
        case (byte) 0xF4:
          message.add(i, (byte) 0x03);
        break;
      }
    }
  }

  // PRIVATE ATTRIBUTES
  int transmissionCounter;
  byte overloop;

}

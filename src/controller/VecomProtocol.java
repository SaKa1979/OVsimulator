package controller;

import java.util.ArrayList;

import model.Protocol;
import view.VehicleButton;

public class VecomProtocol extends Protocol {

  // PUBLIC METHODS
  public VecomProtocol(){
    transmissionCounter = 1;
    overloop = (byte) 0xFF; // start with 'arrival at loop' 
  }

  @Override
  public ArrayList<Byte> createSerialMessage(VehicleButton vb) {
    ArrayList<Byte> dataFrame = new ArrayList<Byte>();

    transmissionCounter += 1;

    /** 
     * Byte 0 default 0x29
     */
    dataFrame.add((byte)0x29);

    /** 
     * Byte 1 default 0x0D
     */
    dataFrame.add((byte)0x0D);

    /** 
     * Byte 2 vehicleType 
     */
    {
      switch (vb.getVehicleType()){
        case POLITIE:
          dataFrame.add((byte)0x10); // Police
          break;
        case BRANDWEER:
          dataFrame.add((byte)0x08); // Fire brigade
          break;
        case AMBULANCE:
          dataFrame.add((byte)0x0C); // Ambulance
          break;
        case TAXI:
          dataFrame.add((byte)0x04); // Taxi
          break;
        case TRAM_CITY:
          dataFrame.add((byte)0x80); // City tram
          break;
        case BUS_CITY:
          dataFrame.add((byte)0xA0); // City Bus
          break;
        case TRAM_REG:
          dataFrame.add((byte)0xC0); // Regional tram
          break;
        case BUS_REG:
          dataFrame.add((byte)0xE0); // Regional bus
          break;
        default:
      }
    }

    /**
     * Byte 3 LineNumber lo
     * Sets the given linenumber attribute with max on 0xFF
     */
    {
      dataFrame.add((byte)(vb.getLineNr() % 256));
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
      dataFrame.add((byte)temp_byte);
    }

    /**
     * Byte 5 holds the upper byte of the vehicle service number
     * -vehSrvNr : vehicle service number 0 - 1023
     */
    {
      dataFrame.add((byte)(vb.getVehServiceNr() / 4));
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
      dataFrame.add((byte)temp_byte);
    }

    /**Byte 7 staff number lo
     * -staffNr 0 - 16777215 !NOT USED IN CVN!
     */
    dataFrame.add((byte)0);

    /**
     * Byte 8 staff number mo
     * -staffNr 0 - 16777215 !NOT USED IN CVN!
     */
    dataFrame.add((byte)0);

    /**
     * Byte 9 staff number ho
     * -staffNr 0 - 16777215 !NOT USED IN CVN!
     */
    dataFrame.add((byte)0);

    /**
     * Byte 10 vehicleId aka fleetnumber lo
     * -fleetNr 0 - 1048575
     */
    dataFrame.add((byte)(vb.getVehicleId() % 256));

    /**
     * Byte 11 vehicleId aka fleetnumber mo
     * -fleetNr 0 - 1048575
     */
    dataFrame.add((byte)(vb.getVehicleId() / 256));

    /**
     * Byte 12 transmissionCounter (4 msb) | vehicleId aka fleetnumber ho (4 lsb)
     * -fleetNr : 0 - 1048575
     * -transCounter : 1 - 128
     */
    {
      int temp_byte = vb.getVehicleId() / 65536;
      temp_byte |= transmissionCounter << 4;

      dataFrame.add((byte)temp_byte);    
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
          dataFrame.add((byte) 0x00);
          break;
        case TURNRIGHT:
          dataFrame.add((byte) 0x02);
          break;
        case TURNLEFT:
          dataFrame.add((byte) 0x01);
          break;
        case FORWARD:
          dataFrame.add((byte) 0x03);
          break;
        case READYTOSTART:
        case RTS_F:
        case RTS_TL:
        case RTS_TR:
          dataFrame.add((byte) 0x00);
          break;
        default:
          dataFrame.add((byte) 0x00);
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

      switch(vb.getDirection()){
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
      temp_byte |= vb.getLoopNr() & 0xF;

      dataFrame.add(temp_byte);
    }

    ArrayList<Byte> header = addHeader();
    ArrayList<Byte> dle = addDLE(dataFrame);
    ArrayList<Byte> crc = addCRC(dataFrame);
    
    ArrayList<Byte> message = new ArrayList<>();
    
    // build complete message
    message.addAll(header);
    message.addAll(dle);
    message.add((byte) 0x03);// ETX
    message.addAll(crc);
    
    return message;
  }

  @Override
  public void processData(Byte b) {
    // TODO Auto-generated method stub
  }

  // PRIVATE METHODS

  private ArrayList<Byte> addHeader(){
    ArrayList<Byte> header = new ArrayList<Byte>();
    /**
     * SOH
     */
    header.add((byte)0x01);

    /**
     * ADDRESS
     */
    header.add((byte)0xF0);

    /**
     * STX
     */
    header.add((byte)0x02);

    return header;
  }
  
  /**
   * calculates CRC over given dataFrame
   * @param dataFrame
   * @return crc: 2 Bytes CRC
   */
  protected ArrayList<Byte> addCRC(ArrayList<Byte> dataFrame) {
    byte temp = 0, crc1 = 0, crc2 = 0;
    ArrayList<Byte> crc = new ArrayList<Byte>();

    for (int i = 0; i < dataFrame.size(); i++ ){
      temp = crc1;
      crc1 = (byte) (crc2 ^ dataFrame.get(i));
      crc2 = temp;
    }
    crc.add(crc1);
    crc.add(crc2);
    return crc;
  }

  /**
   * When the data frame of the given message contains a key word byte
   * than this byte needs to be followed by a DLE value
   * @param message: message containing header-dataframe-CRC
   * @return dle: copy of dataframe with DLE(s) inserted. 
   */
  private ArrayList<Byte> addDLE(ArrayList<Byte> dataFrame){
    ArrayList<Byte> dle = new ArrayList<Byte>();

    for (int i = 0; i < dataFrame.size(); i++ ){
      switch(dataFrame.get(i)){
        case (byte) 0x03:
          dle.add((byte) 0x10);
          dle.add(dataFrame.get(i));
        break;
        case (byte) 0x04:
          dle.add((byte) 0x10);
          dle.add(dataFrame.get(i));
        break;
        case (byte) 0x05:
          dle.add((byte) 0x10);
          dle.add(dataFrame.get(i));
        break;
        case (byte) 0x10:
          dle.add((byte) 0x10);
          dle.add(dataFrame.get(i));
        break;
        default:
          dle.add(dataFrame.get(i));
      }
    }
    return dle;
  }
  

  // PRIVATE ATTRIBUTES
  int transmissionCounter;
  byte overloop;

}

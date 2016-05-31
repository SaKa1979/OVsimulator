package model.vecom;

import java.util.ArrayList;
import java.util.List;

import model.Protocol;
import model.ProtocolMessage;
import model.vecom.VecomAttribute.VECOM;
import view.ViewManager;

public class VecomProtocol extends Protocol {

  // PUBLIC METHODS
  public VecomProtocol(){
    transmissionCounter = 1;
    overloop = (byte) 0xFF; // start with 'arrival at loop' 
    state = State.WAIT_FOR_POLL;
    viewManager = ViewManager.getInstance();
    int number = Integer.parseUnsignedInt(viewManager.getProtocolPanel().getVcuAddress());
    if (number >= 0){
      byte numberHigh = (byte) ~(number << 4);
      byte numberLow = (byte) number;
      vcuAddress = (byte) (numberHigh + numberLow );
    }
  }

  @Override
  public ArrayList<Byte> createSerialMessage(ProtocolMessage protocolMessage) {
	  VecomMessage vecomMessage = (VecomMessage) protocolMessage;
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
    
    // TODO loop over all attributes and assemble message based on the size in bits
//    int bitPositionInByte = 0;
//    byte data;
//    // This loop will add all attributes consecutively based on the bits per attribute.
//    for (VecomAttribute attribute : vecomMessage.getVecomAttributes()) {
//    	int value = attribute.getValue();
//    	int nrOfBits = attribute.getSizeInBits();
//    	int nrOfBitsInByte = Math.max(8, nrOfBits);
//    	
//    	int startPos = bitPositionInByte;
//    	int bitPosInAttribute = 0;
//    	int andWith = (2 << nrOfBits) - 1; // This make 0x0F for 4 bits, etc
//    	bitPositionInByte += nrOfBits;
//    	data |= (byte) ((value & andWith) << startPos);
//    	while (bitPositionInByte > 7) {
//    		bitPositionInByte -= 8;
//    	}
//    }

    /** 
     * Byte 2 vehicleType 
     */
//      switch (vb.getVehicleType()){
//        case POLITIE:
//          dataFrame.add((byte)0x10); // Police
//          break;
//        case BRANDWEER:
//          dataFrame.add((byte)0x08); // Fire brigade
//          break;
//        case AMBULANCE:
//          dataFrame.add((byte)0x0C); // Ambulance
//          break;
//        case TAXI:
//          dataFrame.add((byte)0x04); // Taxi
//          break;
//        case TRAM_CITY:
//          dataFrame.add((byte)0x80); // City tram
//          break;
//        case BUS_CITY:
//          dataFrame.add((byte)0xA0); // City Bus
//          break;
//        case TRAM_REG:
//          dataFrame.add((byte)0xC0); // Regional tram
//          break;
//        case BUS_REG:
//          dataFrame.add((byte)0xE0); // Regional bus
//          break;
//        default:
//      }
    dataFrame.add((byte) vecomMessage.getAttribute(VECOM.VEH_TYPE).getValue());
//
//    /**
//     * Byte 3 LineNumber lo
//     * Sets the given linenumber attribute with max on 0xFF
//     */
//    {
//      dataFrame.add((byte)(vb.getLineNr() % 256));
//    }
//    
//    /**
//     * Byte 4 LineNumber hi (4 MSB) | VehServiceNr lo (4 LSB)
//     * -lineNumber 0- 16383
//     * -vehSrvNr : vehicle service number 0 - 1023
//     */
//    {
//      int temp_byte = vb.getLineNr() / 256;
//      switch(vb.getVehServiceNr() % 4){
//        case 0:
//          temp_byte |= 0x00; 
//          break;
//        case 1:
//          temp_byte |= 0x40;
//          break;
//        case 2:
//          temp_byte |= 0x80;
//          break;
//        case 3:
//          temp_byte |= 0xC0;
//          break;
//        default:
//      }
//      dataFrame.add((byte)temp_byte);
//    }
//
//    /**
//     * Byte 5 holds the upper byte of the vehicle service number
//     * -vehSrvNr : vehicle service number 0 - 1023
//     */
//    {
//      dataFrame.add((byte)(vb.getVehServiceNr() / 4));
//    }
    
    int lineNr = vecomMessage.getAttribute(VECOM.LINE_NR).getValue();
    int vehSrvNr = vecomMessage.getAttribute(VECOM.SERVICE_NR).getValue();
	List<Byte> bytes = new ArrayList<>();
	bytes.add((byte) (lineNr & 0xFF));
	bytes.add((byte) (((lineNr >> 8) & 0x3F) | ((vehSrvNr & 0xC0) << 6)));
	bytes.add((byte) ((vehSrvNr >> 2) & 0xFF));
    dataFrame.addAll(bytes);
    
//    /**
//     * Byte 6 journeyType aka cat (b7b6) | punctualityClass aka pun (b5b4)
//     * -category
//     * -punctuality class
//     * -intely 2 bit !NOT USED!
//     * -bcd 2 bit for bcd !NOT USED!
//     */
//    {
//      byte temp_byte = 0;
//
//      switch (vb.getJourneyType()){
//        case DIENST:
//          temp_byte |= 0x00; // Lijndienst
//          break;
//        case REMISE_UIT:
//          temp_byte |= 0x40; // Uitruk
//          break;
//        case REMISE_IN:
//          temp_byte |= 0x80; // Inruk
//          break;
//        case DEADRUN:
//          temp_byte |= 0xC0; // No service
//          break;
//        default:
//          temp_byte |= 0xC0; // No service
//      }
//
//      switch (vb.getPunctualityClass()){
//        case NORMAL:
//          temp_byte |= 0x00; // Normal
//          break;
//        case SYSTEM:
//          temp_byte |= 0x10; // System
//          break;
//        case RESERVE1:
//          temp_byte |= 0x20; // Reserved
//          break;
//        case RESERVE2:
//          temp_byte |= 0x30; // Reserved
//          break;
//        default:
//          temp_byte |= 0x00; // Normal
//      }
//      dataFrame.add(temp_byte);
//    }
    int category = vecomMessage.getAttribute(VECOM.CATEGORY).getValue();
    int punctuality = vecomMessage.getAttribute(VECOM.PUNCTUALITY).getValue();
    byte toAdd = (byte) ((punctuality & 0x03) << 4 );
    toAdd |= (byte) ((category & 0x3) << 6 ); 
    dataFrame.add(toAdd);
    

    /**Byte 7 staff number lo
     * -staffNr 0 - 16777215 !NOT USED IN CVN!
     */
    dataFrame.add((byte)0);

    /**
     * Byte 8 staff number mo
     * -staffNr 0 - 16777215 !NOT USED IN CVN!
     */
    dataFrame.add((byte)0);
//
    /**
     * Byte 9 staff number ho
     * -staffNr 0 - 16777215 !NOT USED IN CVN!
     */
    dataFrame.add((byte)0);
//
//    /**
//     * Byte 10 vehicleId aka fleetnumber lo
//     * -fleetNr 0 - 1048575
//     */
//    dataFrame.add((byte)(vb.getVehicleId() % 256));
//
//    /**
//     * Byte 11 vehicleId aka fleetnumber mo
//     * -fleetNr 0 - 1048575
//     */
//    dataFrame.add((byte)(vb.getVehicleId() / 256));
//
//    /**
//     * Byte 12 transmissionCounter (4 msb) | vehicleId aka fleetnumber ho (4 lsb)
//     * -fleetNr : 0 - 1048575
//     * -transCounter : 1 - 128
//     */
//    {
//      int temp_byte = vb.getVehicleId() / 65536;
//      temp_byte |= transmissionCounter << 4;
//
//      dataFrame.add((byte)temp_byte);    
//    }
    int fleetNr = vecomMessage.getAttribute(VECOM.FLEET_NR).getValue();
	bytes = new ArrayList<>();
	bytes.add((byte) (fleetNr & 0xFF));
	bytes.add((byte) ((fleetNr >> 8) & 0xFF));
	bytes.add((byte) (((fleetNr >> 16) & 0x03) | ((transmissionCounter & 0x3F) << 2)));
    dataFrame.addAll(bytes);
    
//    /**
//     * Byte 13 unknown (b7b6b5) | koppelbit (b4) | manual (4 lsb)
//     * -manual 0 - 7
//     * -leave !NOT USED IN CVN!
//     * -koppel !NOT USED IN CVN!
//     * -unknown !NOT USED IN CVN! 
//     */
//    {
//      switch(vb.getManualControl()){
//        case NOMANUALCONTROL:
//          dataFrame.add((byte) 0x00);
//          break;
//        case TURNRIGHT:
//          dataFrame.add((byte) 0x02);
//          break;
//        case TURNLEFT:
//          dataFrame.add((byte) 0x01);
//          break;
//        case FORWARD:
//          dataFrame.add((byte) 0x03);
//          break;
//        case READYTOSTART:
//        case RTS_F:
//        case RTS_TL:
//        case RTS_TR:
//          dataFrame.add((byte) 0x00);
//          break;
//        default:
//          dataFrame.add((byte) 0x00);
//          break;
//      }
//    }
	int manual = vecomMessage.getAttribute(VECOM.MANUAL_CONTROL).getValue();
	dataFrame.add((byte) (manual & 0x07));
    
//    /**
//     * Byte 14 over loop (b7)| direction (b6b5) | not used (b4) | loop number (4 lsb)
//     * overloop : 0 = arrival at loop, 1 = departure at loop
//     * direct 2 bits 0 - 3
//     * loopNr 1 - 8 : 
//     */
//    {
//      byte temp_byte = 0;
//      overloop = (byte) ~overloop; // send message twice to mimmick a full loop ocupation
//
//      temp_byte |= (overloop & 0x80);
//
//      switch(vb.getDirection()){
//        case BACKWARDS:
//          temp_byte |= 0x03;
//          break;
//        case FORWARD:
//          temp_byte |= 0x01;
//          break;
//        case RESERVED:
//          temp_byte |= 0x02;
//          break;
//        case UNKNOWN:
//          temp_byte |= 0x00;
//          break;
//        default:
//          temp_byte |= 0x00;
//          break;
//      }
//      temp_byte |= vb.getLoopNr() & 0xF;
//
//      dataFrame.add(temp_byte);
//    }
	int loopNr = vecomMessage.getAttribute(VECOM.LOOP_NR).getValue();
    int direction = vecomMessage.getAttribute(VECOM.DIRECTION).getValue();
    int overLoop = vecomMessage.getAttribute(VECOM.OVER_LOOP).getValue();
    toAdd = (byte) (loopNr & 0x0F); 
    toAdd |= (byte) ((direction & 0x03) << 5 );
    toAdd |= (byte) ((overLoop & 0x01) << 7 );
    dataFrame.add(toAdd);
    

    ArrayList<Byte> header = addHeader();
    ArrayList<Byte> dle = addDLE(dataFrame);
    ArrayList<Byte> crc = addCRC(dataFrame);

    ArrayList<Byte> message = new ArrayList<>();

    // build complete message
    message.addAll(header);
    message.addAll(dle);
    message.add(ETX);
    message.addAll(crc);

    sendMessage = message;
    dataframe_set = true;

    return message;
  }

  @Override
  public void processData(Byte b) {

    switch(state){
      case WAIT_FOR_POLL:
        receivedMessage = new ArrayList<Byte>();
        receivedMessage.add(b);
        if (b == P)                                     // start of POLL message
          state = State.BUILD_POLL;
        break;
      case BUILD_POLL:
        receivedMessage.add(b);
        if (b == ENQ){                                  // end of poll message. We either respond with eot or dataframe message
          if (receivedMessage.get(1) == vcuAddress){   // meant for us
            if (dataframe_set){                         // there is a dataframe ready to send
              signalSubscriber("Send: message\n");      // let the Simcontroler know
              dataframe_set = false;
              state = State.WAIT_FOR_REPLY;
            }else{                                      // send EOT message
              sendMessage = eot();
              signalSubscriber(null);
              state = State.WAIT_FOR_POLL;
            }
          }else{                                        // NOT meant for us
            state = State.WAIT_FOR_POLL;
          }
        }
        break;
      case WAIT_FOR_REPLY:
        receivedMessage = new ArrayList<Byte>();
        receivedMessage.add(b);
        if (b == vcuAddress){
          state = State.BUILD_REPLY;
        }else {
          state = State.BUILD_EOT;
        }
        break;
      case BUILD_REPLY:
        receivedMessage.add(b);
        if (b == ACK){
          sendMessage = eot();
          signalSubscriber("Received: ACK\n");
          state = State.WAIT_FOR_POLL;
        }else if (b == NAK){
          signalSubscriber("Received: NAK\n");
          state = State.WAIT_FOR_REPLY;
        }
        break;
      case BUILD_EOT:
        receivedMessage.add(b);
        if(b == EOT){
          state = State.WAIT_FOR_POLL;
        }
      default:
        state = State.WAIT_FOR_POLL;
        break; 
    }

  }

  // PRIVATE METHODS

  private ArrayList<Byte> addHeader(){
    ArrayList<Byte> header = new ArrayList<Byte>();
    /**
     * SOH
     */
    header.add(SOH);

    /**
     * ADDRESS
     */
    header.add(vcuAddress);

    /**
     * STX
     */
    header.add(STX);

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
        case ETX:
          dle.add(DLE);
          dle.add(dataFrame.get(i));
          break;
        case EOT:
          dle.add(DLE);
          dle.add(dataFrame.get(i));
          break;
        case ENQ:
          dle.add(DLE);
          dle.add(dataFrame.get(i));
          break;
        case DLE:
          dle.add(DLE);
          dle.add(dataFrame.get(i));
          break;
        default:
          dle.add(dataFrame.get(i));
      }
    }
    return dle;
  }

  private ArrayList<Byte> eot(){
    ArrayList<Byte> eot = new ArrayList<Byte>();
    eot.add(NULL);
    eot.add(EOT);
    return eot;
  }

  // ENUMS
  public enum State {WAIT_FOR_POLL,
    BUILD_POLL,
    WAIT_FOR_REPLY,
    BUILD_REPLY,
    BUILD_EOT}

  // PRIVATE ATTRIBUTES
  int transmissionCounter;
  byte vcuAddress;
  byte overloop;
  State state;
  boolean dataframe_set;
  ViewManager viewManager;

  private static final byte SOH = (byte)0x01;
  private static final byte STX = (byte)0x02;
  private static final byte ETX = (byte)0x03;
  private static final byte EOT = (byte)0x04;
  private static final byte ENQ = (byte)0x05;
  private static final byte ACK = (byte)0x06;
  private static final byte DLE = (byte)0x10;
  private static final byte NAK = (byte)0x15;
  private static final byte P   = (byte)0x50;
  private static final byte NULL = (byte)0x00;
}

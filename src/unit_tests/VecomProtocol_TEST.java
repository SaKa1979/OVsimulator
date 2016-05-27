package unit_tests;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.vecom.VecomProtocol;
import view.ProtocolPanel;
import view.ProtocolPanel.Proto;
import view.VehicleButton;
import view.VehicleSettingPanel;
import view.ViewManager;

public class VecomProtocol_TEST {

  @Before
  public void setUp() throws Exception {
    protocolPanel.setSelectedProto(Proto.VECOM);
    VehicleSettingPanel vehicleSettingPanel = vehicleButton.getVehicleSettingPanel();
    
    // set up a default vehicle button configuration
    
//    vehicleButton.setLoopNr(1);
//    vehicleButton.setVehicleType(VehicleType.BUS_CITY);
//    vehicleButton.setLineNr(6);
//    vehicleButton.setVehServiceNr(4);
//    vehicleButton.setVehicleId(65536); //aka vlootnummer
//    vehicleButton.setManualControl(ManualControl.TURNRIGHT);
//    vehicleButton.setPunctualityClass(PunctualityClass.NORMAL);
//    vehicleButton.setJourneyType(JourneyType.DIENST);
//    vehicleButton.setDirection(Direction.UNKNOWN);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void createSerialMessage() {
    ArrayList<Byte> result = new ArrayList<Byte>();
    result.add((byte)0x01); //SOH
    result.add((byte)0xF0); //ADDRESS
    result.add((byte)0x02); //STX
    result.add((byte)0x29); //B0 
    result.add((byte)0x0D); //B1
    result.add((byte)0xA0); //B2 vehicleType
    result.add((byte)0x06); //B3 lineNumber
    result.add((byte)0x00); //B4 lineNumber vehicleServiceNr
    result.add((byte)0x01); //B5 vehicleServiceNr
    result.add((byte)0x00); //B6 journeyType/cat punctuality class
    result.add((byte)0x00); //B7 staffnummer lo 
    result.add((byte)0x00); //B8 staffnummer mo
    result.add((byte)0x00); //B9 staffnummer ho
    result.add((byte)0x00); //B10 vehicleID lo aka fleetnumber
    result.add((byte)0x00); //B11 vehicleID mo
    result.add((byte)0x21); //B12 transmissioncounter vehicleID ho
    result.add((byte)0x02); //B13 manual
    result.add((byte)0x01); //B14 overloop direction loopnumber
    result.add((byte)0x03); //ETX
    result.add((byte)0xA9); //CRC1
    result.add((byte)0x08); //CRC2
    
//    assertEquals(result, vecomProtocol.createSerialMessage(vehicleButton) );
  }
  
  
  
  VecomProtocol vecomProtocol = new VecomProtocol();
  ProtocolPanel protocolPanel = ViewManager.getInstance().getProtocolPanel();
  VehicleButton vehicleButton = new VehicleButton(Proto.VECOM);
  VehicleSettingPanel vehicleSettingPanel = vehicleButton.getVehicleSettingPanel();

}

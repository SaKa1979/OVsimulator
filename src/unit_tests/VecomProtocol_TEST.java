package unit_tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Encodings.CategoryType;
import model.Encodings.Direction;
import model.Encodings.ManualControl;
import model.Encodings.PunctualityClass;
import model.Encodings.VecomVehicleType;
import model.vecom.VecomAttribute.VECOM;
import model.vecom.VecomMessage;
import model.vecom.VecomProtocol;

public class VecomProtocol_TEST {

  @Before
  public void setUp() throws Exception {
    // set up a default vehicle button configuration
    vm = new VecomMessage();
    vm.setAttribute(VECOM.LOOP_NR, 1);
    vm.setAttribute(VECOM.VEH_TYPE, VecomVehicleType.CITY_BUS);
    vm.setAttribute(VECOM.LINE_NR, 6);
    vm.setAttribute(VECOM.SERVICE_NR, 4);
    vm.setAttribute(VECOM.FLEET_NR, 65536);
    vm.setAttribute(VECOM.MANUAL_CONTROL, ManualControl.TURNRIGHT);
    vm.setAttribute(VECOM.PUNCTUALITY, PunctualityClass.GEENINFO);
    vm.setAttribute(VECOM.CATEGORY, CategoryType.LIJN_DIENST);
    vm.setAttribute(VECOM.DIRECTION, Direction.UNKNOWN);
    
    
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
    result.add((byte)0x9);  //B12 transmissioncounter vehicleID ho
    result.add((byte)0x02); //B13 manual
    result.add((byte)0x01); //B14 overloop direction loopnumber
    result.add((byte)0x03); //ETX
    result.add((byte)-127);  //CRC1
    result.add((byte)0x08); //CRC2
    
    assertEquals(result, vecomProtocol.createSerialMessage(vm) );
  }
  
  private VecomProtocol vecomProtocol = new VecomProtocol();
  private VecomMessage vm;

}

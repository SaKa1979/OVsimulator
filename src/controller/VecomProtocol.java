package controller;

import java.util.ArrayList;

import model.Protocol;
import view.VehicleButton;

public class VecomProtocol extends Protocol {

  @Override
  public ArrayList<Byte> createSerialMessage(VehicleButton vb) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void processData(Byte b) {
    // TODO Auto-generated method stub

  }

  @Override
  public ArrayList<Byte> getCRC(ArrayList<Byte> byteList) {
    // TODO Auto-generated method stub
    return null;
  }

}

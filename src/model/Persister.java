package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import controller.Event;
import view.PortSettingPanel;
import view.ProtocolPanel;
import view.VehicleSimulation;
import view.ViewManager;


public class Persister {

  public Persister(){
    viewManager = ViewManager.getInstance();
  }

  // PUBLIC METHODS
  
  public void openFile(){
    JFileChooser fileChooser = new JFileChooser();
    int choice = fileChooser.showDialog(null, "Open file" );
    if ( choice == JFileChooser.APPROVE_OPTION ){
      this.file = fileChooser.getSelectedFile();
      try {
        readObjects(file);
      }
      catch (Exception e) {
        JOptionPane.showMessageDialog(null,
            e,
            "File read Error", 
            JOptionPane.ERROR_MESSAGE); 
      }
    }
  }

  public void saveFile(){
    try {
      writeObjects(file);
    }
    catch (Exception e) {
      JOptionPane.showMessageDialog(null,
          e,
          "File write Error", 
          JOptionPane.ERROR_MESSAGE); 
      e.printStackTrace();
    }
  }

  public void saveAsFile(){
    JFileChooser fileChooser = new JFileChooser();
    int choice = fileChooser.showDialog(null, "Save as" );
    if ( choice == JFileChooser.APPROVE_OPTION ){
      this.file = fileChooser.getSelectedFile();
      try {
        writeObjects(file);
      }
      catch (Exception e) {
        JOptionPane.showMessageDialog(null,
            e,
            "File write Error", 
            JOptionPane.ERROR_MESSAGE); 
        e.printStackTrace();
      }
    }
  }

  public void closeNoSave() {
    int choice = JOptionPane.showConfirmDialog(null,
        "Do you want to save the current settings?",
        "Closing",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.QUESTION_MESSAGE);
    switch (choice){
      case JOptionPane.YES_OPTION:
        saveAsFile();
        break;
      case JOptionPane.NO_OPTION:
        System.exit(0);
      case JOptionPane.CANCEL_OPTION:
        break;
    }
  }

  // PRIVATE METHODS
  public void writeObjects(File a_file) {
    VehicleSimulation vehicleSimulation = viewManager.getVehicleSimulation();
    PortSettingPanel portSettingPanel = viewManager.getPortSettingPanel();
    ProtocolPanel protocolPanel = viewManager.getProtocolPanel();

    DebuggingObjectOutputStream  oos = null;
    try {
      oos = new DebuggingObjectOutputStream ( new FileOutputStream(a_file)); 
//      oos.writeObject(vehicleSimulation);
//      oos.writeObject(portSettingPanel);
      oos.writeObject(protocolPanel);
    }
    catch (IOException e) {
      throw new RuntimeException(
          "Serialization error. Path to bad object: " 
              + oos.getStack(), e);
    }
    finally {
      if (oos != null) {
        try {oos.close();}
        catch (IOException ioe) {}
      }
    }
  }


  public void readObjects(File a_file){
    ObjectInputStream ois  = null;
    try {
      ois = new ObjectInputStream(new FileInputStream(a_file));    
//      viewManager.setVehicleSimulation((VehicleSimulation)ois.readObject());
//      viewManager.setPortSettingPanel((PortSettingPanel)ois.readObject());
      viewManager.setProtocolPanel((ProtocolPanel)ois.readObject());
    } 
    catch (FileNotFoundException e) {
      // Er was nog geen bestand  
    } 
    catch (Exception e) {

    }
    finally {
      if (ois != null) {
        try {ois.close();}
        catch (IOException ioe) {}
      }
    }      
  }

  // PRIVATE ATTRIBUTES
  ViewManager viewManager;
  File file;

}// end of class

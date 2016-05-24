package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.SimController;
import view.PortSettingPanel;
import view.ProtocolPanel;
import view.VehicleSimulation;
import view.ViewManager;


public class Persister {

  public Persister(SimController simControler){
    viewManager = ViewManager.getInstance();
    vehicleSimulation = viewManager.getVehicleSimulation();
    portSettingPanel = viewManager.getPortSettingPanel();
    protocolPanel = viewManager.getProtocolPanel();
    this.simControler = simControler;
  }

  // PUBLIC METHODS
  
  public void openFile(){
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "ovs", "ovs");
    fileChooser.setFileFilter(filter);
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
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "ovs", "ovs");
    fileChooser.setFileFilter(filter);
    int choice = fileChooser.showDialog(null, "Save as" );
    if ( choice == JFileChooser.APPROVE_OPTION ){
      String path = fileChooser.getSelectedFile().getPath();
      
      if (!path.endsWith(".ovs"))
        path.concat(".ovs");
        
      file = new File(path);
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
    DebuggingObjectOutputStream  oos = null;
    try {
      oos = new DebuggingObjectOutputStream ( new FileOutputStream(a_file)); 
      oos.writeObject(simControler.getPersistentObjects(protocolPanel));
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
      @SuppressWarnings("unchecked")
      ArrayList<Object> list = (ArrayList<Object>) ois.readObject();
      simControler.setPersistentObjects(protocolPanel, list);
      simControler.signal(protocolPanel, null);
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
  SimController simControler;
  ViewManager viewManager;
  VehicleSimulation vehicleSimulation;
  PortSettingPanel portSettingPanel;
  ProtocolPanel protocolPanel;
  File file;

}// end of class

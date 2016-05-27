package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.SimController;
import view.PortSettingPanel;
import view.ProtocolCard;
import view.ProtocolPanel;
import view.VehicleButton;
import view.VehicleSimulation;
import view.ViewManager;
import view.ProtocolPanel.Proto;


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
      oos.writeObject(getPersistentObjects(protocolPanel, vehicleSimulation));
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
      setPersistentObjects(protocolPanel, vehicleSimulation, list);
      simControler.signal(protocolPanel, null);
    } 
    catch (FileNotFoundException e) {
    	// Er was nog geen bestand  
    	System.out.println(e.getStackTrace());
    } 
    catch (Exception e) {
    	System.out.println(e.getStackTrace());
    }
    finally {
      if (ois != null) {
        try {ois.close();}
        catch (IOException ioe) {
        	System.out.println(ioe.getStackTrace());
        }
      }
    }      
  }

  public void setPersistentObjects(ProtocolPanel protocolPanel, VehicleSimulation a_vehicleSimulations, ArrayList<Object> a_list) {
    protocolPanel.setSelectedProto((Proto)a_list.get(0));
    protocolPanel.setKarSid((String) a_list.get(1));
    protocolPanel.setVcuAddress((String) a_list.get(2));
    protocolPanel.setKarKey((byte[]) a_list.get(3));
    
    for (int i = 4; i < a_list.size(); ) {
    	Proto proto = (Proto) a_list.get(i++);	// Read the protocol
    	ProtocolCard protoCard = a_vehicleSimulations.getProtocolCard(proto);
    	for (int j = 0; j < protoCard.getVbList().size(); j++) {
	    	ProtocolMessage message = (ProtocolMessage) a_list.get(i++);	// Read the protocol message
	    	boolean enabled = (boolean) a_list.get(i++);		// Read if the button is enabled
	    	protoCard.getVbList().get(j).updateProtocolMessage(message, enabled);
    	}
    }
  } 

  public ArrayList<Serializable> getPersistentObjects(ProtocolPanel a_protocolPanel, VehicleSimulation a_vehicleSimulations) {
    ArrayList<Serializable> list = new ArrayList<Serializable>();
    list.add(a_protocolPanel.getSelectedProto());
    list.add(a_protocolPanel.getKarSid());
    list.add(a_protocolPanel.getVcuAddress());
    list.add(a_protocolPanel.getKarKey());
    
    // save KAR buttons/messages
    list.add(Proto.KAR);	// Write the protocol
    saveProtocolMessages(a_vehicleSimulations.getProtocolCard(Proto.KAR), list);
    
    // save VECOM buttons/messages
    list.add(Proto.VECOM);	// Write the protocol
    saveProtocolMessages(a_vehicleSimulations.getProtocolCard(Proto.VECOM), list);
    
	return list;
  }

	private void saveProtocolMessages(ProtocolCard protocolCard, ArrayList<Serializable> list) {
		List<VehicleButton> vbList = protocolCard.getVbList();
		for (int i = 0; i < vbList.size(); i++) {
			VehicleButton vb = vbList.get(i);
			list.add((Serializable) vb.getProtocolMessage()); // Write the protocol message
			list.add(vb.isEnabled()); // Write if the vehicle button is enabled
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

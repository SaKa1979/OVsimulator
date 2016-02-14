/**
 * 
 */
package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import images.ImageFactory;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Sander
 *
 */
public class VehicleButton extends JButton{

  /**
   * Default constructor
   */
  public VehicleButton(String name) {
    super(name);
    setIcon(imagefactory.getImageIcon("emptyVehicle"));
    setVerticalTextPosition(JButton.BOTTOM);
    setHorizontalTextPosition(JButton.CENTER);
    initialize();
    // handle the RMB action. This shall spawn a JPanel with all the vehicle simulation settings
    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e) || e.isControlDown()){
          System.out.println("RMB");
        }
      }
    });
  }
  // private method
  private void initialize(){
    setLoopNr(1);
    setSignalGroupNr(0);
    setDirection(0);
    setCommand(0);
    setVehicleType(0);
    setLineNr(0);
    setWagonNr(0);
    setVehicleId(0);
    setVehicleLength(0);
    setVehicleSpeed(0);
    setDistanceToStop(0);
    setTimeToStop(0);
    setVehicleStatus(0);
    setPriorityClass(0);
    setPunctualityClass(0);
    setPunctuality(0);
    setButtonText();
  }
  // public method
  public void setButtonText(){
    this.setText(command + " " + direction);
  } 
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getLoopNr() {
    return loopNr;
  }
  public void setLoopNr(int loopNr) {
    this.loopNr = loopNr;
  }
  public int getSignalGroupNr() {
    return signalGroupNr;
  }
  public void setSignalGroupNr(int signalGroupNr) {
    this.signalGroupNr = signalGroupNr;
  }
  public int getDirection() {
    return direction;
  }
  public void setDirection(int direction) {
    this.direction = direction;
  }
  public int getCommand() {
    return command;
  }
  public void setCommand(int command) {
    this.command = command;
  }
  public int getVehicleType() {
    return vehicleType;
  }
  public void setVehicleType(int vehicleType) {
    this.vehicleType = vehicleType;
  }
  public int getLineNr() {
    return lineNr;
  }
  public void setLineNr(int lineNr) {
    this.lineNr = lineNr;
  }
  public int getWagonNr() {
    return wagonNr;
  }
  public void setWagonNr(int wagonNr) {
    this.wagonNr = wagonNr;
  }
  public int getVehicleId() {
    return vehicleId;
  }
  public void setVehicleId(int vehicleId) {
    this.vehicleId = vehicleId;
  }
  public int getVehicleLength() {
    return vehicleLength;
  }
  public void setVehicleLength(int vehicleLength) {
    this.vehicleLength = vehicleLength;
  }
  public int getVehicleSpeed() {
    return vehicleSpeed;
  }
  public void setVehicleSpeed(int vehicleSpeed) {
    this.vehicleSpeed = vehicleSpeed;
  }
  public int getDistanceToStop() {
    return distanceToStop;
  }
  public void setDistanceToStop(int distanceToStop) {
    this.distanceToStop = distanceToStop;
  }
  public int getTimeToStop() {
    return timeToStop;
  }
  public void setTimeToStop(int timeToStop) {
    this.timeToStop = timeToStop;
  }
  public int getVehicleStatus() {
    return vehicleStatus;
  }
  public void setVehicleStatus(int vehicleStatus) {
    this.vehicleStatus = vehicleStatus;
  }
  public int getPriorityClass() {
    return priorityClass;
  }
  public void setPriorityClass(int priorityClass) {
    this.priorityClass = priorityClass;
  }
  public int getPunctualityClass() {
    return punctualityClass;
  }
  public void setPunctualityClass(int punctualityClass) {
    this.punctualityClass = punctualityClass;
  }
  public int getPunctuality() {
    return punctuality;
  }
  public void setPunctuality(int punctuality) {
    this.punctuality = punctuality;
  }
  // private method
  private void setIcon(){
    
  }
  // private attribute
  private String name;
  private int loopNr;
  private int signalGroupNr;
  private int direction;
  private int command;
  private int vehicleType;
  private int lineNr;
  private int wagonNr;
  private int vehicleId;
  private int vehicleLength;
  private int vehicleSpeed;
  private int distanceToStop;
  private int timeToStop;
  private int vehicleStatus;
  private int priorityClass;
  private int punctualityClass;
  private int punctuality;
  private ImageFactory imagefactory = new ImageFactory();

  
}// end class

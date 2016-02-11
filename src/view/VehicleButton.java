/**
 * 
 */
package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;

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
    setIcon(new ImageIcon(VehicleButton.class.getResource("/images/emptyVehicle.jpg")));
  }
  
  private String name;
  private ImageIcon icon;

}

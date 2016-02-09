/**
 * 
 */
package images;

import java.net.URL;
import java.util.Vector;

import javax.swing.ImageIcon;

import gui.OVmain;

/**
 * @author Sander
 * produces ImageIcons
 */
public class ImageFactory {

  /**
   * Default constructor
   */
  public ImageFactory(){
    initialize();
  }
  
  // Initialize standard ImageIcons
  private void initialize(){
    presetIcons = new Vector<ImageIcon>();
    emptyVehicleImage = createImageIcon("/images/emptyVehicle.jpg", "An icon that represents a non configured vehicle");
    presetIcons.add(emptyVehicleImage);
    swarcoLogoImage = createImageIcon("/images/SWARCOLOGO.jpeg", "Main SWARCO logo");
    presetIcons.add(swarcoLogoImage);
  }

  public ImageIcon createImageIcon(String filename, String description) {
    URL link = OVmain.class.getResource(filename); 
    return new ImageIcon(link, description);
  }
    
  // Attributes
  private Vector<ImageIcon> presetIcons;
  private ImageIcon emptyVehicleImage;
  private ImageIcon swarcoLogoImage; 
}

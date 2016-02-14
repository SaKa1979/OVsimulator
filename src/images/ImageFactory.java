package images;

import java.net.URL;
import javax.swing.ImageIcon;

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
    emptyVehicleImage = createImageIcon("/images/emptyVehicle.jpg", "An icon that represents a non configured vehicle");
    swarcoLogoImage = createImageIcon("/images/SWARCOLOGO.jpeg", "Main SWARCO logo");
    ambulanceImage = createImageIcon("/images/ambulance.jpg","ambulance");
    brandweerImage =  createImageIcon("/images/brandweer.jpg","branweer");
    busImage = createImageIcon("/images/bus.jpg","bus");
    politieImage = createImageIcon("/images/politie.jpg","politie");
    taxiImage = createImageIcon("/images/taxi.jpg","politie");
    tramImage = createImageIcon("/images/tram.jpg","politie");
    brokenImage = createImageIcon("/images/broken.jpg","broken");
  }

  public ImageIcon createImageIcon(String filename, String description) {
    URL link = this.getClass().getResource(filename); 
    return new ImageIcon(link, description);
  }
  
  public ImageIcon getImageIcon(String name)
  {
    if (name.equals("emptyVehicle"))
      return emptyVehicleImage;
    else if (name.equals("swarcoLogo"))
      return swarcoLogoImage;
    else if (name.equals("ambulance"))
      return ambulanceImage;
    else if (name.equals("brandweer"))
      return brandweerImage;
    else if (name.equals("bus"))
      return busImage;
    else if (name.equals("politie"))
      return politieImage;
    else if (name.equals("taxi"))
      return taxiImage;
    else if (name.equals("tram"))
      return tramImage;
    else 
      return brokenImage;
  }
    
  // Attributes
  private ImageIcon emptyVehicleImage;
  private ImageIcon swarcoLogoImage;
  private ImageIcon ambulanceImage;
  private ImageIcon brandweerImage;
  private ImageIcon busImage;
  private ImageIcon politieImage;
  private ImageIcon taxiImage;
  private ImageIcon tramImage;
  private ImageIcon brokenImage;
  
}

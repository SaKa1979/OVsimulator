package images;

import java.net.URL;
import javax.swing.ImageIcon;

import view.VehicleButton.VehicleTypes;

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
    unknownImage = createImageIcon("/images/unknown.jpg", "An icon that represents a non configured vehicle");
    swarcoLogoImage = createImageIcon("/images/SWARCOLOGO.jpeg", "Main SWARCO logo");
    ambulanceImage = createImageIcon("/images/ambulance.jpg","ambulance");
    brandweerImage =  createImageIcon("/images/brandweer.jpg","branweer");
    busImage = createImageIcon("/images/bus.jpg","bus");
    politieImage = createImageIcon("/images/politie.jpg","politie");
    taxiImage = createImageIcon("/images/taxi.jpg","politie");
    tramImage = createImageIcon("/images/tram.jpg","politie");
    brokenImage = createImageIcon("/images/broken.jpg","broken");
    ledRedImage = createImageIcon("/images/ledRed.jpg","ledRed");
    ledGreenImage = createImageIcon("/images/ledGreen.jpg","ledGreen");
    ledGrayImage = createImageIcon("/images/ledGray.jpg","ledGray");
    ledYellowImage = createImageIcon("/images/ledYellow.jpg","ledYellow");
  }

  public ImageIcon createImageIcon(String filename, String description) {
    URL link = this.getClass().getResource(filename); 
    return new ImageIcon(link, description);
  }
  
  public ImageIcon getImageIcon(String name)
  {
    if (name.equals(VehicleTypes.BUS_CITY.getName()))
      return busImage;
    else if (name.equals(VehicleTypes.TRAM_CITY.getName()))
      return tramImage;
    else if (name.equals(VehicleTypes.TAXI.getName()))
      return taxiImage;
    else if (name.equals(VehicleTypes.AMBULANCE.getName()))
      return ambulanceImage;
    else if (name.equals(VehicleTypes.BRANDWEER.getName()))
      return brandweerImage;
    else if (name.equals(VehicleTypes.POLITIE.getName()))
      return politieImage;
    else if (name.equals(VehicleTypes.UNKNOWN.getName()))
      return unknownImage;
    else if (name.equals("ledRed"))
        return ledRedImage;
    else if (name.equals("ledGreen"))
        return ledGreenImage;
    else if (name.equals("ledGray"))
      return ledGrayImage;
    else if (name.equals("ledYellow"))
      return ledYellowImage;
    else if (name.equals("swarcoLogo"))
        return swarcoLogoImage;
    else 
      return brokenImage;
  }
    
  // Attributes
  private ImageIcon unknownImage;
  private ImageIcon swarcoLogoImage;
  private ImageIcon ambulanceImage;
  private ImageIcon brandweerImage;
  private ImageIcon busImage;
  private ImageIcon politieImage;
  private ImageIcon taxiImage;
  private ImageIcon tramImage;
  private ImageIcon brokenImage;
  private ImageIcon ledRedImage;
  private ImageIcon ledGreenImage;
  private ImageIcon ledGrayImage;
  private ImageIcon ledYellowImage;
  
}

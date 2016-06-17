<<<<<<< HEAD
package images;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import model.Encodings.KarVehicleType;
import model.Encodings.VecomVehicleType;
import model.Encodings.VehicleType;

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
    swarcoLogoImage = createImageIcon("/images/swarcologo.jpg", "Main SWARCO logo");
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
    Image image = null;;
    try {
      image = ImageIO.read(getClass().getResourceAsStream(filename));
    }
    catch (IOException e) {
      JOptionPane.showMessageDialog(null, "The program has failed to load images from the source package.", "Error Message", JOptionPane.ERROR_MESSAGE);
    }
    return new ImageIcon(image, description);
  }
  
	public ImageIcon getVehicleImageIcon(VehicleType vt) {
		if (vt instanceof KarVehicleType) {
			return getKarVehicleImageIcon((KarVehicleType) vt);
		} else if (vt instanceof VecomVehicleType) {
			return getVecomVehicleImageIcon((VecomVehicleType) vt);
		}
		return brokenImage;
	}

	public ImageIcon getKarVehicleImageIcon(KarVehicleType vt) {
		switch (vt) {
		case BUS:
			return busImage;
		case TRAM:
			return tramImage;
		case POLICE:
			return politieImage;
		case BRANDWEER:
			return brandweerImage;
		case AMBULANCE:
			return ambulanceImage;
		case CVV:
			return unknownImage;
		case TAXI:
			return taxiImage;
		default:
			return brokenImage;
		}
	}
	
	public ImageIcon getVecomVehicleImageIcon(VecomVehicleType vt) {
		switch (vt) {
		case POLICE:
			return politieImage;
		case FIRE_BRIGADE:
			return brandweerImage;
		case AMBULANCE:
			return ambulanceImage;
		case TAXI:
			return taxiImage;
		case CITY_TRAM:
			return tramImage;
		case CITY_BUS:
			return busImage;
		case REGIONAL_TRAM:
			return tramImage;
		case REGIONAL_BUS:
			return busImage;
		default:
			return brokenImage;
		}
	}

	public ImageIcon getImageIcon(String name) {
		if (name.equals("ledRed"))
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
=======
package images;

import java.net.URL;

import javax.swing.ImageIcon;

import model.Encodings.KarVehicleType;
import model.Encodings.VecomVehicleType;
import model.Encodings.VehicleType;

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
    unknownImage = createImageIcon("/images/Unknown.jpg", "An icon that represents a non configured vehicle");
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
  
	public ImageIcon getVehicleImageIcon(VehicleType vt) {
		if (vt instanceof KarVehicleType) {
			return getKarVehicleImageIcon((KarVehicleType) vt);
		} else if (vt instanceof VecomVehicleType) {
			return getVecomVehicleImageIcon((VecomVehicleType) vt);
		}
		return brokenImage;
	}

	public ImageIcon getKarVehicleImageIcon(KarVehicleType vt) {
		switch (vt) {
		case BUS:
			return busImage;
		case TRAM:
			return tramImage;
		case POLICE:
			return politieImage;
		case BRANDWEER:
			return brandweerImage;
		case AMBULANCE:
			return ambulanceImage;
		case CVV:
			return unknownImage;
		case TAXI:
			return taxiImage;
		default:
			return brokenImage;
		}
	}
	
	public ImageIcon getVecomVehicleImageIcon(VecomVehicleType vt) {
		switch (vt) {
		case POLICE:
			return politieImage;
		case FIRE_BRIGADE:
			return brandweerImage;
		case AMBULANCE:
			return ambulanceImage;
		case TAXI:
			return taxiImage;
		case CITY_TRAM:
			return tramImage;
		case CITY_BUS:
			return busImage;
		case REGIONAL_TRAM:
			return tramImage;
		case REGIONAL_BUS:
			return busImage;
		default:
			return brokenImage;
		}
	}

	public ImageIcon getImageIcon(String name) {
		if (name.equals("ledRed"))
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
>>>>>>> refs/remotes/origin/AD_impl_change

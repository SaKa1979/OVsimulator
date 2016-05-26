package view;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import images.ImageFactory;
import lombok.Getter;
import lombok.Setter;
import model.CVNAttribute.VehicleType;
import model.kar.KarMessage;
import model.kar.KarAttribute.KAR;

/**
 * @brief A button representing a vehicle detection. A mouse listener must be
 *        added to the class that is using this button. The RMB action should be
 *        handled in the calling class The attribute settings are controlled by
 *        a VehicleSetting class which shall be triggered by the RMB
 * @author Sander Kamps
 */
public class VehicleButton extends JButton {

	private static final long serialVersionUID = 4L;

	/**
	 * constructor
	 */
	public VehicleButton() {
		super();
		vehicleSettingPanel = new VehicleSettingPanel(this);
		setToolTipText("LMB is action.\r\nRMB is settings");
		initialize();
	}

	public VehicleButton(String a_name) {
		super(a_name);
		name = a_name;
		vehicleSettingPanel = new VehicleSettingPanel(this);
		initialize();
	}

	// PUBLIC METHODS

	public void updateKarMessage(KarMessage karMessage) {
		//TODO why do 2 object keep track of the same karMessage
		this.karMessage = karMessage;
		vehicleSettingPanel.updateSettingsPanel(karMessage);
		setEnabled(true);
		setButtonText();
		setVehicleTypeImage(karMessage.getValue(KAR.VEH_TYPE));
	}

	// PRIVATE METHODS

	private void setButtonText() {
		setText("L" + karMessage.getValue(KAR.LOOP_NR) + " FC" + karMessage.getValue(KAR.DIRECTION));
		setFont(new Font("MonoSpace", Font.PLAIN, 8));
	}

	private void setVehicleTypeImage(int vt) {
		switch (vt) {
		case 1:
			setIcon(imagefactory.getImageIcon(VehicleType.BUS));
			break;
		case 2:
			setIcon(imagefactory.getImageIcon(VehicleType.TRAM));
			break;
		case 3:
			setIcon(imagefactory.getImageIcon(VehicleType.POLICE));
			break;
		case 4:
			setIcon(imagefactory.getImageIcon(VehicleType.BRANDWEER));
			break;
		case 5:
			setIcon(imagefactory.getImageIcon(VehicleType.AMBULANCE));
			break;
		case 7:
			setIcon(imagefactory.getImageIcon(VehicleType.TAXI));
			break;
		case 0:
		case 6:
		default:
			setIcon(imagefactory.getImageIcon(VehicleType.UNKNOWN.getName()));
			break;
		}
	}

	private void initialize() {
		setIcon(imagefactory.getImageIcon("emptyVehicle"));
		this.setEnabled(false);
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setHorizontalTextPosition(SwingConstants.CENTER);
		// handle the RMB action. This shall spawn a JOption with all the
		// vehicle simulation settings
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e) || e.isControlDown()) {
					vehicleSettingPanel.setProto(ViewManager.getInstance().getProtocolPanel().getSelectedProto());
					int ok = JOptionPane.showConfirmDialog(null, vehicleSettingPanel, "Vehicle Setting",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					switch (ok) {
					case JOptionPane.OK_OPTION:
						updateKarMessage(vehicleSettingPanel.getKarMessage());
						break;
					default:
					}
				}
			}
		});
	}

	// PRIVATE ATTRIBUTE
	@Getter
	@Setter
	private String name;
	@Getter
	private KarMessage karMessage;
	@Getter
	private VehicleSettingPanel vehicleSettingPanel;
	private ImageFactory imagefactory = new ImageFactory();

}// end class

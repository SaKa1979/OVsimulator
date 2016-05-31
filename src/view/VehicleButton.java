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
import model.Encodings;
import model.Encodings.KarVehicleType;
import model.Encodings.VecomVehicleType;
import model.ProtocolMessage;
import model.kar.KarAttribute.KAR;
import model.vecom.VecomAttribute.VECOM;
import view.ProtocolPanel.Proto;

/**
 * @brief A button representing a vehicle detection. A mouse listener must be
 *        added to the class that is using this button. The RMB action should be
 *        handled in the calling class The attribute settings are controlled by
 *        a VehicleSetting class which shall be triggered by the RMB
 * @author Sander Kamps
 */
public class VehicleButton extends JButton {

	private static final long serialVersionUID = 4L;
	private Proto proto;

	/**
	 * constructor
	 */
	public VehicleButton(Proto proto) {
		super();
		this.proto = proto;
		vehicleSettingPanel = new VehicleSettingPanel(proto);
		setToolTipText("LMB is action.\r\nRMB is settings");
		initialize();
	}

	// PUBLIC METHODS
	public void updateProtocolMessage(ProtocolMessage protocolMessage, boolean enable) {
		vehicleSettingPanel.updateSettingsPanel(protocolMessage);
		setEnabled(enable);
		setButtonText(protocolMessage);
		// TODO MAKE GENERIC
		if (proto == Proto.KAR) {
			setVehicleTypeImage(protocolMessage.getValue(KAR.VEH_TYPE));
		} else if (proto == Proto.VECOM) {
			setVehicleTypeImage(protocolMessage.getValue(VECOM.VEH_TYPE));
		}
	}
	
	public ProtocolMessage getProtocolMessage() {
		return vehicleSettingPanel.getProtocolMessage();
	}

	// PRIVATE METHODS
	private void setButtonText(ProtocolMessage protocolMessage) {
		// TODO MAKE GENERIC
		if (proto == Proto.KAR) {
			setText("L" + protocolMessage.getValue(KAR.LOOP_NR) + " FC" + protocolMessage.getValue(KAR.DIRECTION));
		} else if (proto == Proto.VECOM) {
			setText("L" + protocolMessage.getValue(VECOM.LOOP_NR) + " FC" + protocolMessage.getValue(VECOM.DIRECTION));
		}
		setFont(new Font("MonoSpace", Font.PLAIN, 8));
	}
	
	private void setVehicleTypeImage(int vt) {
		// TODO MAKE GENERIC
		if (proto == Proto.KAR) {
			setIcon(imagefactory.getKarImageIcon((KarVehicleType) Encodings.getTypeByValue(KarVehicleType.class, vt)));
		} else if (proto == Proto.VECOM) {
			setIcon(imagefactory.getVecomImageIcon((VecomVehicleType) Encodings.getTypeByValue(VecomVehicleType.class, vt)));
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
					int ok = JOptionPane.showConfirmDialog(null, vehicleSettingPanel, "Vehicle Setting",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					switch (ok) {
					case JOptionPane.OK_OPTION:
						updateProtocolMessage(vehicleSettingPanel.getProtocolMessage(), true);
						break;
					default:
					}
				}
			}
		});
	}

	// PRIVATE ATTRIBUTE
	@Getter	private VehicleSettingPanel vehicleSettingPanel;
	private ImageFactory imagefactory = new ImageFactory();

}// end class

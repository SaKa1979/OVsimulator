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
import model.interfaces.ProtocolMessage;
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

	public VehicleButton(Proto proto) {
		super();
		vehicleSettingPanel = new VehicleSettingPanel(proto);
		setToolTipText("LMB is action.\r\nRMB is settings");
		initialize();
	}

	// PUBLIC METHODS
	public void updateVehicleButton(ProtocolMessage protocolMessage, boolean enable) {
		vehicleSettingPanel.updateSettingsPanel(protocolMessage);
		setEnabled(enable);
		if (enable) {
			setText(protocolMessage.toShortString());
			setIcon(imagefactory.getVehicleImageIcon(protocolMessage.getVehicleType()));
			setFont(new Font("MonoSpace", Font.PLAIN, 8));
		} else {
			setText("");
			setIcon(imagefactory.getImageIcon(""));
		}
	}
	
	public ProtocolMessage getProtocolMessage() {
		return vehicleSettingPanel.getProtocolMessage();
	}

	private void initialize() {
		setIcon(imagefactory.getImageIcon("emptyVehicle"));
		setEnabled(false);
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
						updateVehicleButton(getProtocolMessage(), true);
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

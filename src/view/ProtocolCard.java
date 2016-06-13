package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import controler.Event;
import lombok.Getter;
import view.ProtocolPanel.Proto;

public class ProtocolCard extends JPanel {
	private static final long serialVersionUID = -5761238930234735019L;
	private static final int VEHICLE_BUTTONS_PER_PROTOCOL = 20;
	
	@Getter	private final Proto proto;
	private Event subscriber;
	@Getter	private ArrayList<VehicleButton> vbList = new ArrayList<>();
	
	public ProtocolCard(Proto proto) {
		this.proto = proto;
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), proto + " simulation",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(10, 2, 2, 2));
		setToolTipText("Press the left mouse button to sent public transport message to the ITC.\r\nPress the right mouse button to configure this button.");
		createButtons();
	}
	
	// PRIVATE METHODS
	private void createButtons() {
		for (int i = 0; i < VEHICLE_BUTTONS_PER_PROTOCOL; i++) {
			VehicleButton btnVeh = new VehicleButton(proto);
			btnVeh.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VehicleButton vb = (VehicleButton) e.getSource();
					subscriber.signal(vb, null);
				}
			});
			add(btnVeh);
			vbList.add(btnVeh);
		}
	}
	
	/**
	 * @brief The subsriber gets a signal when a certain event takes place.
	 * @param a_subscriber
	 */
	public void addEventSubscriber(Event a_subscriber) {
		subscriber = a_subscriber;
	}
}

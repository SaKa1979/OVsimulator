package view;

import java.awt.CardLayout;

import javax.swing.JPanel;

import view.ProtocolPanel.Proto;

public class VehicleSimulation extends JPanel {
	private static final long serialVersionUID = 2L;

	/**
	 * Create the panel.
	 */
	public VehicleSimulation() {
		JPanel unknownCard = new JPanel();
		karCard = new ProtocolCard(Proto.KAR);
		vecomCard = new ProtocolCard(Proto.VECOM);
		
		setLayout(new CardLayout());
		add(unknownCard, Proto.UNKNOWN.getName());
		add(karCard, Proto.KAR.getName());
		add(vecomCard, Proto.VECOM.getName());
	}
	
	public ProtocolCard getProtocolCard(Proto proto) {
		if (proto == Proto.KAR) {
			return karCard;
		} else if (proto == Proto.VECOM) {
			return vecomCard;
		}
		return null;
	}

	// PUBLIC METHOD
	public void setCurrentProtocolCard(Proto proto) {
		CardLayout cl = (CardLayout) getLayout();
		cl.show(this, proto.getName());
	}

	// PRIVATE METHODS

	// PRIVATE ATTRIBUTES
	private ProtocolCard karCard;
	private ProtocolCard vecomCard;

}// end class VehicleSimulation

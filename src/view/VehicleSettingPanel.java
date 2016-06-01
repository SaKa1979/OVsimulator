package view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import lombok.Getter;
import model.interfaces.AttributeID;
import model.interfaces.ProtocolMessage;
import model.kar.KarAttribute;
import model.kar.KarAttribute.KAR;
import model.kar.KarField;
import model.kar.KarMessage;
import model.vecom.VecomAttribute;
import model.vecom.VecomAttribute.VECOM;
import model.vecom.VecomMessage;
import view.ProtocolPanel.Proto;

public class VehicleSettingPanel extends JPanel {
	private static final long serialVersionUID = 3L;

	@Getter
	private Proto proto;
	private ProtocolMessage protocolMessage;
	private List<AttributeEntry> attributeEntries;

	public VehicleSettingPanel(Proto proto) {
		this.proto = proto;
		setBorder(new TitledBorder(null, "Vehicle Setting for " + proto, TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		setLayout(new BorderLayout(5, 5));
		initialize();
	}

	// PUBLIC METHODS
	public void updateSettingsPanel(ProtocolMessage message) {
		for (AttributeEntry attributeEntry : attributeEntries) {
			AttributeID id = attributeEntry.getAttribute().getId();
			attributeEntry.updateEntry(message.getAttribute(id));
		}
	}
	
	public ProtocolMessage getProtocolMessage() {
		// TODO make generic
		for (AttributeEntry attributeEntry : attributeEntries) {
			if (proto == Proto.KAR) {
				KarMessage karMessage = (KarMessage) protocolMessage;
				KarAttribute karAttribute = (KarAttribute) attributeEntry.getAttribute(); 
				
				KAR id = karAttribute.getId();
				karMessage.getAttribute(id).setEnabled(karAttribute.isEnabled());
				for (KarField karField : karAttribute.getKarFields()) {
					karMessage.setValue(id, karField.getFieldIndex(), karField.getValue());
				}
			} else if (proto == Proto.VECOM) {
				VecomMessage vecomMessage = (VecomMessage) protocolMessage;
				VecomAttribute vecomAttribute = (VecomAttribute) attributeEntry.getAttribute(); 
				
				VECOM id = vecomAttribute.getId();
				vecomMessage.setAttribute(id, vecomAttribute.getValue());
			}
		}
		return protocolMessage;
	}

	// PRIVATE METHODS
	private void initialize() {
		attributeEntries = new ArrayList<>();
		if (proto == Proto.KAR) {
			protocolMessage = new KarMessage();
			createKarAttributeEntries((KarMessage) protocolMessage);
		} else if (proto == Proto.VECOM) {
			protocolMessage = new VecomMessage();
			createVecomAttributeEntries((VecomMessage) protocolMessage);
		}
	}

	private void createVecomAttributeEntries(VecomMessage vecomMessage) {
		// Define two columns
		JPanel columnEntries = new JPanel();
		columnEntries.setLayout(new BoxLayout(columnEntries, BoxLayout.PAGE_AXIS));

		for (VecomAttribute attribute : vecomMessage.getVecomAttributes()) {
			VecomAttributeEntry attributeEntry = new VecomAttributeEntry(attribute);
			columnEntries.add(attributeEntry);
			attributeEntries.add(attributeEntry);
		}
		this.add(columnEntries, BorderLayout.CENTER);
	}

	private void createKarAttributeEntries(KarMessage karMessage) {
		int row = 0;
		// Define two columns
		JPanel columnEntries = new JPanel();
		JPanel columnEntries2 = new JPanel();
		columnEntries.setLayout(new BoxLayout(columnEntries, BoxLayout.PAGE_AXIS));
		columnEntries2.setLayout(new BoxLayout(columnEntries2, BoxLayout.PAGE_AXIS));

		for (KarAttribute attribute : karMessage.getKarAttributes()) {
			KarAttributeEntry attributeEntry = new KarAttributeEntry(attribute);
			attributeEntries.add(attributeEntry);

			if (row < 15) {
				columnEntries.add(attributeEntry);
			} else {
				columnEntries2.add(attributeEntry);
			}

			row += ((KarAttribute) attribute).getKarFields().size();
		}
		this.add(columnEntries, BorderLayout.WEST);
		this.add(columnEntries2, BorderLayout.EAST);
	}
}

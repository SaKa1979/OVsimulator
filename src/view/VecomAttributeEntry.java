package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import lombok.Getter;
import model.Encodings;
import model.Encodings.Encoding;
import model.interfaces.Attribute;
import model.vecom.VecomAttribute;

public class VecomAttributeEntry extends JPanel implements AttributeEntry {
	private static final long serialVersionUID = -3441900928638288607L;
	@Getter private VecomAttribute vecomAttribute;
	private JPanel panel;
	private JComboBox<String> inputField;
	private NumericField numericInputField;

	public VecomAttributeEntry(VecomAttribute vecomAttribute) {
		this.vecomAttribute = vecomAttribute;
		createEntry();
	}
	
	@Override
	public Attribute getAttribute() {
		return vecomAttribute;
	}

	@Override
	public void updateEntry(Attribute attribute) {
		vecomAttribute = (VecomAttribute) attribute;
		int value = vecomAttribute.getValue();
		Class<? extends Encoding> encoding = vecomAttribute.getEncoding();
		if (encoding != null) {
			inputField.setSelectedItem(Encodings.getNameByNr(encoding, value));
		} else {
			numericInputField.setText("" + value);
		}
	}
	
	private void createEntry() {
		panel = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 150, 160 };
		panel.setLayout(gridBagLayout);
		
		createLabel();
		createInputComponent();
		this.add(panel);
	}

	private void createInputComponent() {
		Class<? extends Encoding> encoding = vecomAttribute.getEncoding();
		if (encoding != null) {
			inputField = new JComboBox<>(Encodings.getStringArray(encoding));
			inputField.setSelectedItem(Encodings.getNameByNr(encoding, vecomAttribute.getValue()));
			addComboBoxListener(inputField);
			createInputFieldGridBagConstraints(panel, inputField, 0, 1);
		} else {
			numericInputField = createInputField(vecomAttribute);
			addNumericFieldListener(numericInputField);
			createInputFieldGridBagConstraints(panel, numericInputField, 0, 1);
		}
	}

	private void createLabel() {
		String fieldName = vecomAttribute.getFieldName() + " (" + vecomAttribute.getId().getValue() + ")";
		JComponent label = new JLabel(fieldName);
		createLabelGridBagConstraints(panel, label, 0, 0);
	}

	private void addComboBoxListener(JComboBox<String> inputField) {
		inputField.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String selected = (String) e.getItem();
					vecomAttribute.setValue(selected);
				}
			}
		});
	}

	private void addNumericFieldListener(NumericField inputField) {
		inputField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				changedUpdate();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				changedUpdate();
			}

			public void changedUpdate() {
				try {
					int inputValue = Integer.parseInt(inputField.getText());
					if (vecomAttribute.getRange().contains(inputValue)) {
						vecomAttribute.setValue(inputValue);
					}
				} catch (NumberFormatException e) {
					
				}
			}
		});
	}

	private GridBagConstraints createDefaultGridBagConstraints(JComponent component, int row, int column) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = column;
		gbc.gridy = row;
		return gbc;
	}

	private GridBagConstraints createLabelGridBagConstraints(JPanel panel, JComponent component, int row, int column) {
		GridBagConstraints gbc = createDefaultGridBagConstraints(component, row, column);
		gbc.anchor = GridBagConstraints.WEST;
		panel.add(component, gbc);
		return gbc;
	}

	private GridBagConstraints createInputFieldGridBagConstraints(JPanel panel, JComponent component, int row,
			int column) {
		GridBagConstraints gbc = createDefaultGridBagConstraints(component, row, column);
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.BOTH;
		panel.add(component, gbc);
		return gbc;
	}

	private NumericField createInputField(VecomAttribute attribute) {
		NumericField tf = new NumericField(5, NumericField.NUMERIC);
		tf.setAllowNegative(attribute.getRange().getMinimum() < 0);
		tf.setNumber(0);
		tf.setToolTipText("Range: " + attribute.getRange());
		return tf;
	}
}

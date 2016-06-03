package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JCheckBox;
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
import model.kar.KarAttribute;
import model.kar.KarField;

public class KarAttributeEntry extends JPanel implements AttributeEntry {
	private static final long serialVersionUID = -3441900928638288607L;
	@Getter private KarAttribute karAttribute;
	private JPanel panel;
	private JComboBox<String> comboBoxInputField;
	private NumericField numericInputField;
	private JCheckBox checkbox;

	public KarAttributeEntry(KarAttribute karAttribute) {
		this.karAttribute = karAttribute;
		createEntry();
	}
	
	@Override
	public Attribute getAttribute() {
		return karAttribute;
	}
	
	@Override
	public void updateEntry(Attribute attribute) {
		KarAttribute karAttribute = (KarAttribute) attribute;
		checkbox.setSelected(karAttribute.isEnabled());
		enablePanel(karAttribute.isEnabled());
		for (KarField karField : karAttribute.getKarFields()) {
			setEntryValue(karField);
		}
	}
	
	private void setEntryValue(KarField field) {
		Class<? extends Encoding> encoding = field.getEncoding(); 
		if (encoding != null) {
			comboBoxInputField.setSelectedItem(Encodings.getNameByNr(encoding, field.getValue()));
		} else {
			numericInputField.setText("" + field.getValue());
		}
	}

	private void createEntry() {
		createCheckBox();

		panel = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 150, 150 };
		panel.setLayout(gridBagLayout);
		
		List<KarField> karFields = karAttribute.getKarFields();
		for (int i = 0; i < karFields.size(); i++) {
			KarField karField = karFields.get(i);
			createLabel(karField);
			createInputComponent(karField);
		}
		this.add(panel);
		enablePanel(false);
	}

	private void createInputComponent(KarField karField) {
		Class<? extends Encoding> encoding = karField.getEncoding();
		if (encoding != null) {
			comboBoxInputField = new JComboBox<>(Encodings.getStringArray(encoding));
			addComboBoxListener(karField, comboBoxInputField);
			createInputFieldGridBagConstraints(panel, comboBoxInputField, karField.getFieldIndex(), 1);
		} else {
			numericInputField = createInputField(karField);
			addNumericFieldListener(karField, numericInputField);
			createInputFieldGridBagConstraints(panel, numericInputField, karField.getFieldIndex(), 1);
		}
		setEntryValue(karField);
	}

	private void createLabel(KarField karField) {
		String fieldName = karField.getFieldName() + " (" + karAttribute.getId().getValue() + ")";
		JComponent label = new JLabel(fieldName);
		createLabelGridBagConstraints(panel, label, karField.getFieldIndex(), 0);
	}

	public void createCheckBox() {
		checkbox = new JCheckBox();
		checkbox.setToolTipText("Disable CVN: " + karAttribute.getId() + "?");
		checkbox.setSelected(karAttribute.isEnabled());
		checkbox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				boolean enabled = checkbox.isSelected();
				karAttribute.setEnabled(enabled);
				enablePanel(enabled);
			}
		});
		this.add(checkbox);
	}

	private void addComboBoxListener(KarField karField, JComboBox<String> inputField) {
		inputField.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String selected = (String) e.getItem();
					karField.setValue(selected);
				}
			}
		});
	}

	private void addNumericFieldListener(KarField karField, NumericField inputField) {
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
					if (karField.getRange().contains(inputValue)) {
						karField.setValue(inputValue);
					}
				} catch (NumberFormatException e) {
//					e.printStackTrace();
				}
			}
		});
	}

	public void enablePanel(boolean enabled) {
		Component[] comps = panel.getComponents();
		for (Component comp : comps) {
			if (comp instanceof JComponent) {
				((JComponent) comp).setEnabled(enabled);
				((JComponent) comp).setForeground(enabled ? Color.BLACK : Color.GRAY);
			}
		}
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

	private NumericField createInputField(KarField attribute) {
		NumericField tf = new NumericField(5, NumericField.NUMERIC);
		tf.setAllowNegative(attribute.getRange().getMinimum() < 0);
		tf.setNumber(0);
		tf.setToolTipText("Range: " + attribute.getRange());
		return tf;
	}

}

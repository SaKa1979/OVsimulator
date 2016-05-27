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

import com.google.common.collect.BiMap;

import lombok.Getter;
import model.vecom.VecomAttribute;

public class VecomAttributeEntry extends JPanel {
	private static final long serialVersionUID = -3441900928638288607L;
	@Getter private VecomAttribute vecomAttribute;
	private JPanel panel;

	public VecomAttributeEntry(VecomAttribute vecomAttribute) {
		this.vecomAttribute = vecomAttribute;
		createEntry();
	}

	private void createEntry() {
		panel = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 150, 150 };
		panel.setLayout(gridBagLayout);
		
		createLabel(vecomAttribute);
		createInputComponent(vecomAttribute);
		this.add(panel);
	}

	public void createInputComponent(VecomAttribute vecomAttribute) {
		BiMap<Integer, String> encoding = vecomAttribute.getEncoding();
		if (encoding.size() > 0) {
			JComboBox<String> inputField = new JComboBox<>(encoding.values().toArray(new String[encoding.size()]));
			inputField.setSelectedItem(encoding.get(vecomAttribute.getValue()));
			addComboBoxListener(vecomAttribute, inputField);
			createInputFieldGridBagConstraints(panel, inputField, 0, 1);
		} else {
			NumericField inputField = createInputField(vecomAttribute);
			inputField.setText("" + vecomAttribute.getValue());
			addNumericFieldListener(vecomAttribute, inputField);
			createInputFieldGridBagConstraints(panel, inputField, 0, 1);
		}
	}

	public void createLabel(VecomAttribute vecomAttribute) {
		String fieldName = vecomAttribute.getFieldName() + " (" + vecomAttribute.getId().getValue() + ")";
		JComponent label = new JLabel(fieldName);
		createLabelGridBagConstraints(panel, label, 0, 0);
	}

	private void addComboBoxListener(VecomAttribute vecomAttribute, JComboBox<String> inputField) {
		inputField.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					BiMap<Integer, String> encoding = vecomAttribute.getEncoding();
					String selected = (String) e.getItem();
					vecomAttribute.setValue(encoding.inverse().get(selected));
				}
			}
		});
	}

	private void addNumericFieldListener(VecomAttribute vecomAttribute, NumericField inputField) {
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
		// gbc.insets = new Insets(0, 0, 5, 5);
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
		tf.setToolTipText(attribute.getRange().toString());
		return tf;
	}

}

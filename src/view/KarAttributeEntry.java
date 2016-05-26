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

import com.google.common.collect.BiMap;

import lombok.Getter;
import model.kar.KarAttribute;
import model.kar.KarField;

public class KarAttributeEntry extends JPanel {
	private static final long serialVersionUID = -3441900928638288607L;
	@Getter private KarAttribute karAttribute;
	private JPanel panel;

	public KarAttributeEntry(KarAttribute cvnAttribute) {
		this.karAttribute = cvnAttribute;
		createEntry();
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
			
			createLabel(i, karField);

			createInputComponent(i, karField);
		}
		this.add(panel);
		enablePanel(karAttribute.isEnabled());
	}

	public void createInputComponent(int i, KarField karField) {
		BiMap<Integer, String> encoding = karField.getEncoding();
		if (encoding.size() > 0) {
			JComboBox<String> inputField = new JComboBox<>(encoding.values().toArray(new String[encoding.size()]));
			inputField.setSelectedItem(encoding.get(karField.getValue()));
			addComboBoxListener(karField, inputField);
			createInputFieldGridBagConstraints(panel, inputField, i, 1);
		} else {
			NumericField inputField = createInputField(karField);
			inputField.setText("" + karField.getValue());
			addNumericFieldListener(karField, inputField);
			createInputFieldGridBagConstraints(panel, inputField, i, 1);
		}
	}

	public void createLabel(int i, KarField karField) {
		String fieldName = karField.getFieldName() + " (" + karAttribute.getId().getValue() + ")";
		JComponent label = new JLabel(fieldName);
		createLabelGridBagConstraints(panel, label, i, 0);
	}

	public void createCheckBox() {
		JCheckBox checkbox = new JCheckBox();
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
					BiMap<Integer, String> encoding = karField.getEncoding();
					String selected = (String) e.getItem();
					karField.setValue(encoding.inverse().get(selected));
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
					
				}
			}
		});
	}

	private void enablePanel(boolean enabled) {
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

	private NumericField createInputField(KarField attribute) {
		NumericField tf = new NumericField(5, NumericField.NUMERIC);
		tf.setAllowNegative(attribute.getRange().getMinimum() < 0);
		tf.setNumber(0);
		tf.setToolTipText(attribute.getRange().toString());
		return tf;
	}

}

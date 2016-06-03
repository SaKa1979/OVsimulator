package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class ProtocolPanel extends JPanel {

	private static final long serialVersionUID = 5L;

	/**
	 * Constructor.
	 */
	public ProtocolPanel() {
		setToolTipText("Here you can select a protocol and enter the protocols specific settings");
		setBorder(new TitledBorder(null, "Protocol", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 72, 154, 200 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		setLayout(gridBagLayout);
		vcuAddress = "0";
		karSid = "0";
		karKey = new byte[0];

		initialize();
		loadAllFieldValues();
	}

	// PUBLIC METHODS
	/**
	 * When Panel OK button is pressed all user input is read (again) This
	 * allows the user to fill in all the fields without confirming each field
	 * individualy
	 */
	public void handleOK() {
		loadAllFieldValues();
	}
	
	public void resetToDefault() {
		setKarSid("0000");
		setKarKey(new byte[0]);
		setVcuAddress("1");
	}

	public Proto getSelectedProto() {
		return selectedProto;
	}

	public void setSelectedProto(Proto proto) {
		selectedProto = proto;

		switch (selectedProto) {
		case KAR:
			karButton.setSelected(true);
			break;
		case SICS:
			break;
		case VECOM:
			vecomButton.setSelected(true);
			break;
		default:
			break;
		}
	}

	public String getKarSid() {
		return karSid;
	}

	public void setKarSid(String kar_sid) {
		this.karSid = kar_sid;
		karSidTF.setText(kar_sid);
	}

	public String getVcuAddress() {
		return vcuAddress;
	}

	public void setVcuAddress(String vcuAddress) {
		this.vcuAddress = vcuAddress;
		vcuAddressTF.setText(vcuAddress);
	}

	public byte[] getKarKey() {
		return karKey;
	}

	public void setKarKey(byte[] key) {
		this.karKey = key;
		karKeyFile.setText("Key length: " + key.length);
	}

	// PRIVATE METHODS
	private void initialize() {
		buttonGroup = new ButtonGroup();

		// KAR
		karButton = new JRadioButton("KAR");
		karButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					karSidTF.setEnabled(true);
					karKeyFile.setEnabled(true);
					selectedProto = Proto.KAR;
					karKeyFile.setBackground(Color.white);
					karKeyFile.setForeground(Color.black);
					ViewManager.getInstance().updateVehicleSimulation(selectedProto);
				} else {
					karSidTF.setEnabled(false);
					karKeyFile.setEnabled(false);
					karKeyFile.setBackground(Color.lightGray);
					karKeyFile.setForeground(Color.darkGray);
				}
			}
		});
		buttonGroup.add(karButton);
		GridBagConstraints gbc_chckbxKAR = new GridBagConstraints();
		gbc_chckbxKAR.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxKAR.anchor = GridBagConstraints.NORTH;
		gbc_chckbxKAR.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxKAR.gridx = 0;
		gbc_chckbxKAR.gridy = 0;
		add(karButton, gbc_chckbxKAR);

		karSidTF = new NumericField(4, NumericField.DECIMAL);
		karSidTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				karSid = karSidTF.getText();
			}
		});
		karSidTF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				karSid = karSidTF.getText();
			}
		});
		karSidTF.setAllowNegative(false);
		karSidTF.setToolTipText("Identification number");
		karSidTF.setEnabled(false);
		karSidTF.setName(sidTF_name);
		karSidTF.setText("0000");
		GridBagConstraints gbc_sidTF = new GridBagConstraints();
		gbc_sidTF.insets = new Insets(0, 0, 5, 5);
		gbc_sidTF.anchor = GridBagConstraints.NORTH;
		gbc_sidTF.fill = GridBagConstraints.BOTH;
		gbc_sidTF.gridx = 1;
		gbc_sidTF.gridy = 0;
		add(karSidTF, gbc_sidTF);

		lblSystemIdentificationNumber = new JLabel("System Identification Number");
		GridBagConstraints gbc_lblSystemIdentificationNumber = new GridBagConstraints();
		gbc_lblSystemIdentificationNumber.insets = new Insets(0, 5, 5, 0);
		gbc_lblSystemIdentificationNumber.anchor = GridBagConstraints.WEST;
		gbc_lblSystemIdentificationNumber.gridx = 2;
		gbc_lblSystemIdentificationNumber.gridy = 0;
		add(lblSystemIdentificationNumber, gbc_lblSystemIdentificationNumber);

		karKeyFile = new JButton();
		karKeyFile.setText("Open secret key file");
		karKeyFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int choice = fileChooser.showDialog(null, "Open secret key file");
				if (choice == JFileChooser.APPROVE_OPTION) {
					try {
						setKarKey(Files.readAllBytes(Paths.get(fileChooser.getSelectedFile().getAbsolutePath())));
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1, "File read Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		karKeyFile.setEnabled(false);
		karKeyFile.setToolTipText("Enter the secret kar key");
		karKeyFile.setBackground(Color.lightGray);
		karKeyFile.setForeground(Color.darkGray);
		GridBagConstraints gbc_keyTF = new GridBagConstraints();
		gbc_keyTF.insets = new Insets(0, 0, 5, 5);
		gbc_keyTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_keyTF.gridx = 1;
		gbc_keyTF.gridy = 1;
		add(karKeyFile, gbc_keyTF);

		lblKey = new JLabel("Key");
		lblKey.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblKey = new GridBagConstraints();
		gbc_lblKey.anchor = GridBagConstraints.WEST;
		gbc_lblKey.insets = new Insets(0, 5, 5, 0);
		gbc_lblKey.gridx = 2;
		gbc_lblKey.gridy = 1;
		add(lblKey, gbc_lblKey);

		// VECOM
		vecomButton = new JRadioButton("VECOM");
		vecomButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					selectedProto = Proto.VECOM;
					ViewManager.getInstance().updateVehicleSimulation(selectedProto);
					vcuAddressTF.setEnabled(true);
				} else {
					vcuAddressTF.setEnabled(false);
				}
			}
		});

		vcuAddressTF = new NumericField(1, NumericField.DECIMAL);
		vcuAddressTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				vcuAddress = vcuAddressTF.getText();
			}
		});
		vcuAddressTF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vcuAddress = vcuAddressTF.getText();
			}
		});
		vcuAddressTF.setAllowNegative(false);
		vcuAddressTF.setToolTipText("VCU number 1-9");
		vcuAddressTF.setEnabled(false);
		vcuAddressTF.setName(vcuTF_name);
		vcuAddressTF.setText("1");

		separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 3;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		add(separator, gbc_separator);
		GridBagConstraints gbc_vcuTF = new GridBagConstraints();
		gbc_vcuTF.insets = new Insets(0, 0, 5, 5);
		gbc_vcuTF.anchor = GridBagConstraints.NORTH;
		gbc_vcuTF.fill = GridBagConstraints.BOTH;
		gbc_vcuTF.gridx = 1;
		gbc_vcuTF.gridy = 3;
		add(vcuAddressTF, gbc_vcuTF);

		buttonGroup.add(vecomButton);
		GridBagConstraints gbc_chckbxVecom = new GridBagConstraints();
		gbc_chckbxVecom.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxVecom.anchor = GridBagConstraints.NORTH;
		gbc_chckbxVecom.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxVecom.gridx = 0;
		gbc_chckbxVecom.gridy = 3;
		add(vecomButton, gbc_chckbxVecom);

		// SICS
		sicsButton = new JRadioButton("SICS");
		vecomButton.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					selectedProto = Proto.SICS;
					ViewManager.getInstance().updateVehicleSimulation(selectedProto);
				} else {
					// do nothing
				}
			}
		});

		lblVcuNumber = new JLabel("VCU number 1 to 8");
		GridBagConstraints gbc_lblVcuNumber = new GridBagConstraints();
		gbc_lblVcuNumber.insets = new Insets(0, 5, 5, 0);
		gbc_lblVcuNumber.anchor = GridBagConstraints.WEST;
		gbc_lblVcuNumber.gridx = 2;
		gbc_lblVcuNumber.gridy = 3;
		add(lblVcuNumber, gbc_lblVcuNumber);

		separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridwidth = 3;
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 4;
		add(separator_1, gbc_separator_1);
		sicsButton.setEnabled(false); // TODO future
		buttonGroup.add(sicsButton);
		GridBagConstraints gbc_chckbxSics = new GridBagConstraints();
		gbc_chckbxSics.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxSics.anchor = GridBagConstraints.NORTH;
		gbc_chckbxSics.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxSics.gridx = 0;
		gbc_chckbxSics.gridy = 5;
		add(sicsButton, gbc_chckbxSics);
	}

	/**
	 * Loads the values entered in all the fields.
	 */
	private void loadAllFieldValues() {
		karSid = karSidTF.getText();
		vcuAddress = vcuAddressTF.getText();
//		karButton.setSelected(true);
	}

	// ENUMS
	@RequiredArgsConstructor
	public enum Proto {
		KAR(0, "KAR"), VECOM(1, "VECOM"), SICS(2, "SICS"), UNKNOWN(-1, "UNKNOWN");
		@Getter	private final int value;
		@Getter private final String name;
	};

	// PRIVATE ATTRIBUTES
	private JRadioButton karButton;
	private JRadioButton vecomButton;
	private JRadioButton sicsButton;
	private ButtonGroup buttonGroup;
	private JLabel lblSystemIdentificationNumber;
	private JLabel lblVcuNumber;
	private JLabel lblKey;
	private JSeparator separator;
	private JSeparator separator_1;
	private NumericField karSidTF;
	private JButton karKeyFile;
	private NumericField vcuAddressTF;
	private String sidTF_name = "sid";
	private String vcuTF_name = "vcuAddress";
	private Proto selectedProto;
	private String karSid;
	private String vcuAddress;
	private byte[] karKey;

} // end of class ProtocolPanel

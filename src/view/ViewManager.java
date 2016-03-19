package view;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.border.TitledBorder;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import controller.Event;
import images.ImageFactory;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

/**
 * @author Sander
 * @brief This is the main user interface frame and acts as manager for the panels shown 
 *        on the main view
 */
public class ViewManager extends JFrame implements Observer{

	/**
	 *  Constructor
	 */
	public ViewManager(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		imagefactory = new ImageFactory();
		initialize();
	}

	// PUBLIC METHODS
	/**
	 * @brief Add a VehicleButton to the VehicleSimulation pane and tie it to a
	 *        given ActionListener
	 * @param a_listener
	 */
	public void addVehicleButtonAndListener(ActionListener a_listener){
		vehicleSimulation.createAndAddVehicleButton(a_listener);
	}

	public void addEventSubscriber(Event a_subscriber){
		subscriber  = a_subscriber;
	}

	/**
	 * Writes given text to feedback pane on new line
	 * @param a_text to be appended to the feedback text.
	 */
	public void writeToFeedback(String a_text, Color color, int size){
		MutableAttributeSet attrs = feedbackTxtpn.getInputAttributes();
		StyleConstants.setFontFamily(attrs, "TimesRoman");
		StyleConstants.setFontSize(attrs, size);
		StyledDocument sDoc = feedbackTxtpn.getStyledDocument();
		Style feedbackTxtpnStyle = sDoc.addStyle("feedbackTxtpnStyle", null);;
		StyleConstants.setForeground(feedbackTxtpnStyle, color);

		try{
			sDoc.insertString(sDoc.getLength(), "\n"+ a_text, feedbackTxtpnStyle);
		}
		catch (Exception e){
			System.out.println(e);
		}
	}

	public void writeToBottomInfoVersion(String aText, Color color){
		MutableAttributeSet attrs = bottomInfoVersionTxtpn.getInputAttributes();
		StyleConstants.setFontFamily(attrs, "monospaced");
		StyleConstants.setFontSize(attrs, 6);
		StyledDocument sDoc = bottomInfoVersionTxtpn.getStyledDocument();
		Style bottomInfoVersionTxtpnStyle = sDoc.addStyle("bottomInfoVersionTxtpnStyle", null);;
		StyleConstants.setForeground(bottomInfoVersionTxtpnStyle, color);

		try{
			sDoc.remove(0, sDoc.getLength());
			sDoc.insertString(0, aText, bottomInfoVersionTxtpnStyle);
		}
		catch (Exception e){
			System.out.println(e);
		}
	}

	public void writeToBottomInfoComSettings(String aText, Color color){
		MutableAttributeSet attrs = bottomInfoComSettingTxtpn.getInputAttributes();
		StyleConstants.setFontFamily(attrs, "monospaced");
		StyleConstants.setFontSize(attrs, 6);
		StyledDocument sDoc = bottomInfoComSettingTxtpn.getStyledDocument();
		Style bottomInfoComSettingsTxtpnStyle = sDoc.addStyle("bottomInfoComTxtpnStyle", null);;
		StyleConstants.setForeground(bottomInfoComSettingsTxtpnStyle, color);

		try{
			sDoc.remove(0, sDoc.getLength());
			sDoc.insertString(0, aText, bottomInfoComSettingsTxtpnStyle);
		}
		catch (Exception e){
			System.out.println(e);
		}
	}

	public void writeTobottomInfoComStatus(String aText, Color color){
		MutableAttributeSet attrs = bottomInfoComStatusTxtpn.getInputAttributes();
		StyleConstants.setFontFamily(attrs, "monospaced");
		StyleConstants.setFontSize(attrs, 6);
		StyledDocument sDoc = bottomInfoComStatusTxtpn.getStyledDocument();
		Style bottomInfoComStatusTxtpnStyle = sDoc.addStyle("bottomInfoComTxtpnStyle", null);;
		StyleConstants.setForeground(bottomInfoComStatusTxtpnStyle, color);

		try{
			sDoc.remove(0, sDoc.getLength());
			sDoc.insertString(0, aText, bottomInfoComStatusTxtpnStyle);
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
	
	/**
	 * @brief To indicate the user that there is (no )a connection to a com port.
	 * @param connected.
	 */
	public void connectedIndication(Boolean connected){
		if(connected){
			bottomInfoConnectedLbl.setIcon(imagefactory.getImageIcon("ledGreen"));
		}
		else{
			bottomInfoConnectedLbl.setIcon(imagefactory.getImageIcon("ledRed"));	
		}
	}
	
	public void writeToBottomProto(String aText){
		MutableAttributeSet attrs = bottomInfoProtoTxtpn.getInputAttributes();
		StyleConstants.setFontFamily(attrs, "monospaced");
		StyleConstants.setFontSize(attrs, 6);
		StyledDocument sDoc = bottomInfoProtoTxtpn.getStyledDocument();
		Style bottomInfoProtoTxtpnStyle = sDoc.addStyle("bottomInfoProtoTxtpn", null);;
		StyleConstants.setForeground(bottomInfoProtoTxtpnStyle, Color.BLACK);

		try{
			sDoc.remove(0, sDoc.getLength());
			sDoc.insertString(0, aText, bottomInfoProtoTxtpnStyle);
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
	
	public void writeToBottomProtoXtraInfo(String aText){
		MutableAttributeSet attrs = bottomInfoProtoXtraInfoTxtpn.getInputAttributes();
		StyleConstants.setFontFamily(attrs, "monospaced");
		StyleConstants.setFontSize(attrs, 6);
		StyledDocument sDoc = bottomInfoProtoXtraInfoTxtpn.getStyledDocument();
		Style bottomInfoProtoXtraInfoTxtpnStyle = sDoc.addStyle("bottomInfoProtoTxtpn", null);;
		StyleConstants.setForeground(bottomInfoProtoXtraInfoTxtpnStyle, Color.BLACK);

		try{
			sDoc.remove(0, sDoc.getLength());
			sDoc.insertString(0, aText, bottomInfoProtoXtraInfoTxtpnStyle);
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
	

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		feedbackTxtpn.setText("obervable called me");
	}

	public PortSettingPanel getPortSettingPanel() {
		return portSettingPanel;
	}

	public ProtocolPanel getProtocolPanel() {
		return protocolPanel;
	}


	// PRIVATE METHODS
	private void initialize() {

		this.setResizable(false);
		this.setTitle("OV simulator");
		this.setIconImage(imagefactory.getImageIcon("swarcoLogo").getImage());
		this.setBounds(100, 100, 800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {200, 532};
		gridBagLayout.rowHeights = new int[] {600, 10};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0};
		this.getContentPane().setLayout(gridBagLayout);
		this.setVisible(true);

		// vehicle simulation
		vehicleSimulation = new VehicleSimulation();
		// First create the VehicleButtons and connect the to a Listener
		for (int i = 0; i < 20; i++){
			vehicleSimulation.createAndAddVehicleButton(vehicleSimulationListener);
		}
		vehicleSimulation.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Simulation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_vehicleSimulation = new GridBagConstraints();
		gbc_vehicleSimulation.insets = new Insets(0, 0, 5, 5);
		gbc_vehicleSimulation.fill = GridBagConstraints.BOTH;
		gbc_vehicleSimulation.gridx = 0;
		gbc_vehicleSimulation.gridy = 0;
		getContentPane().add(vehicleSimulation, gbc_vehicleSimulation);

		// feedback panel
		FeedbackPanel = new JPanel();
		FeedbackPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Feedback", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_FeedbackPanel = new GridBagConstraints();
		gbc_FeedbackPanel.insets = new Insets(5, 0, 5, 0);
		gbc_FeedbackPanel.fill = GridBagConstraints.BOTH;
		gbc_FeedbackPanel.gridx = 1;
		gbc_FeedbackPanel.gridy = 0;
		this.getContentPane().add(FeedbackPanel, gbc_FeedbackPanel);
		FeedbackPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		FeedbackPanel.add(scrollPane);

		feedbackTxtpn = new JTextPane();
		feedbackTxtpn.setText("feedback");
		feedbackTxtpn.setBackground(SystemColor.inactiveCaptionBorder);
		scrollPane.setViewportView(feedbackTxtpn);

		// bottom info panel 
		bottomInfoPanel = new JPanel();
		GridBagConstraints gbc_bottomInfoPanel = new GridBagConstraints();
		gbc_bottomInfoPanel.gridheight = 2;
		gbc_bottomInfoPanel.gridwidth = 3;
		gbc_bottomInfoPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_bottomInfoPanel.gridx = 0;
		gbc_bottomInfoPanel.gridy = 1;
		this.getContentPane().add(bottomInfoPanel, gbc_bottomInfoPanel);
		GridBagLayout gbl_bottomInfoPanel = new GridBagLayout();
		gbl_bottomInfoPanel.columnWidths = new int[] {198, 198, 198, 198};
		gbl_bottomInfoPanel.rowHeights = new int[] {30, 0, 30};
		gbl_bottomInfoPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0};
		gbl_bottomInfoPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		bottomInfoPanel.setLayout(gbl_bottomInfoPanel);
		
				// bottom version detail info text pane
				bottomInfoVersionTxtpn = new JTextPane();
				bottomInfoVersionTxtpn.setToolTipText("Information about the build. ");
				bottomInfoVersionTxtpn.setBackground(SystemColor.control);
				GridBagConstraints gbc_bottomInfoVersionTxtpn = new GridBagConstraints();
				gbc_bottomInfoVersionTxtpn.fill = GridBagConstraints.BOTH;
				gbc_bottomInfoVersionTxtpn.insets = new Insets(0, 0, 5, 5);
				gbc_bottomInfoVersionTxtpn.gridx = 0;
				gbc_bottomInfoVersionTxtpn.gridy = 0;
				bottomInfoPanel.add(bottomInfoVersionTxtpn, gbc_bottomInfoVersionTxtpn);
				bottomInfoVersionTxtpn.setText("version 0.0, build 12a34b 20160214-17:44");
		
				// bottom communication settings detail info text pane
				bottomInfoComSettingTxtpn = new JTextPane();
				bottomInfoComSettingTxtpn.setBackground(SystemColor.control);
				GridBagConstraints gbc_bottomInfoComSettingTxtpn = new GridBagConstraints();
				gbc_bottomInfoComSettingTxtpn.fill = GridBagConstraints.BOTH;
				gbc_bottomInfoComSettingTxtpn.insets = new Insets(0, 0, 5, 5);
				gbc_bottomInfoComSettingTxtpn.gridx = 1;
				gbc_bottomInfoComSettingTxtpn.gridy = 0;
				bottomInfoPanel.add(bottomInfoComSettingTxtpn, gbc_bottomInfoComSettingTxtpn);
				bottomInfoComSettingTxtpn.setText("-,-,-,-,-");
				
				// bottom communication settings detail info text pane
				bottomInfoComStatusTxtpn = new JTextPane();
				bottomInfoComStatusTxtpn.setEditable(false);
				bottomInfoComStatusTxtpn.setBackground(SystemColor.control);
				GridBagConstraints gbc_bottomInfoComStatusTxtpn = new GridBagConstraints();
				gbc_bottomInfoComStatusTxtpn.insets = new Insets(0, 0, 0, 5);
				gbc_bottomInfoComStatusTxtpn.fill = GridBagConstraints.BOTH;
				gbc_bottomInfoComStatusTxtpn.gridx = 1;
				gbc_bottomInfoComStatusTxtpn.gridy = 1;
				bottomInfoPanel.add(bottomInfoComStatusTxtpn, gbc_bottomInfoComStatusTxtpn);
		
				// bottom selected protocol info text pane
				bottomInfoProtoTxtpn = new JTextPane();
				bottomInfoProtoTxtpn.setEditable(false);
				bottomInfoProtoTxtpn.setToolTipText("The current selected protocol");
				bottomInfoProtoTxtpn.setBackground(SystemColor.control);
				GridBagConstraints gbc_bottomInfoProtoTxtpn = new GridBagConstraints();
				gbc_bottomInfoProtoTxtpn.fill = GridBagConstraints.BOTH;
				gbc_bottomInfoProtoTxtpn.insets = new Insets(0, 0, 5, 5);
				gbc_bottomInfoProtoTxtpn.gridx = 2;
				gbc_bottomInfoProtoTxtpn.gridy = 0;
				bottomInfoPanel.add(bottomInfoProtoTxtpn, gbc_bottomInfoProtoTxtpn);
				bottomInfoProtoTxtpn.setText("-");
				
				// bottom selected protocol extra info pane
				bottomInfoProtoXtraInfoTxtpn = new JTextPane();
				bottomInfoProtoXtraInfoTxtpn.setEditable(false);
				bottomInfoProtoXtraInfoTxtpn.setBackground(SystemColor.control);
				GridBagConstraints gbc_bottomInfoProtoXtraInfoTxtpn = new GridBagConstraints();
				gbc_bottomInfoProtoXtraInfoTxtpn.insets = new Insets(0, 0, 5, 5);
				gbc_bottomInfoProtoXtraInfoTxtpn.fill = GridBagConstraints.BOTH;
				gbc_bottomInfoProtoXtraInfoTxtpn.gridx = 2;
				gbc_bottomInfoProtoXtraInfoTxtpn.gridy = 1;
				bottomInfoPanel.add(bottomInfoProtoXtraInfoTxtpn, gbc_bottomInfoProtoXtraInfoTxtpn);
		
		// is there a connection
		bottomInfoConnectedLbl = new JLabel("Connected");
		bottomInfoConnectedLbl.setIcon(imagefactory.getImageIcon("ledRed"));
		GridBagConstraints gbc_bottomInfoConnectedLbl = new GridBagConstraints();
		gbc_bottomInfoConnectedLbl.insets = new Insets(0, 0, 5, 0);
		gbc_bottomInfoConnectedLbl.fill = GridBagConstraints.BOTH;
		gbc_bottomInfoConnectedLbl.gridx = 3;
		gbc_bottomInfoConnectedLbl.gridy = 0;
		bottomInfoPanel.add(bottomInfoConnectedLbl, gbc_bottomInfoConnectedLbl);

		// menu bar
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		mnSetting = new JMenu("Setting");
		menuBar.add(mnSetting);

		portSettingPanel = new PortSettingPanel();
		mntmPortSettings = new JMenuItem("Port settings");
		mntmPortSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ok = JOptionPane.showConfirmDialog(null,
						portSettingPanel,
						"Port Setting",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE); 
				switch (ok){
				case 0:
					portSettingPanel.handleOK();
					subscriber.signal(portSettingPanel);
					break;
				case 2:
					// do nothing
					break;
				}
			}
		});
		mnSetting.add(mntmPortSettings);

		protocolPanel = new ProtocolPanel();
		mnProtocol = new JMenuItem("Protocol");
		mnProtocol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ok = JOptionPane.showConfirmDialog(null,
						protocolPanel,
						"Protocol Setting",
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE); 
				switch (ok){
				case 0:
					protocolPanel.handleOK();
					subscriber.signal(protocolPanel);
					break;
				case 2:
					// do nothing
					break;
				}
			}
		});
		mnSetting.add(mnProtocol);
	}

	// LISTENERS

	/**
	 * @brief Action listener for handling the simulation of all vehicleButtons
	 *        Action is triggered by a VehicleButton
	 */
	ActionListener vehicleSimulationListener = new ActionListener() {   
		@Override
		public void actionPerformed(ActionEvent e) {
			VehicleButton vb = (VehicleButton)e.getSource();
			subscriber.signal(vb);
		}
	};

	// PRIVATE ATTRIBUTES
	int sizeX, sizeY;
	// images
	private ImageFactory imagefactory;
	// views and menu
	private VehicleSimulation vehicleSimulation;
	private JPanel FeedbackPanel;
	private JTextPane feedbackTxtpn;
	private JPanel bottomInfoPanel;
	private JTextPane bottomInfoVersionTxtpn;
	private JTextPane bottomInfoComSettingTxtpn;
	private JTextPane bottomInfoComStatusTxtpn;
	private JTextPane bottomInfoProtoTxtpn;
	private JLabel bottomInfoConnectedLbl;

	private JMenuBar menuBar;
	private JMenu mnSetting;
	private JMenuItem mnProtocol;
	private JMenuItem mntmPortSettings;

	// port settings class
	private PortSettingPanel portSettingPanel;
	private ProtocolPanel protocolPanel;
	private Event subscriber ;
	private JTextPane bottomInfoProtoXtraInfoTxtpn;


} // end of Ovmain 

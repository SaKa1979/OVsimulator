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
import model.Communicator;

import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ButtonGroup;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

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
   * @param a_text
   */
  public void writeToFeedback(String a_text){
    MutableAttributeSet attrs = feedbackTxtpn.getInputAttributes();
    StyleConstants.setFontFamily(attrs, "TimesRoman");
    StyleConstants.setFontSize(attrs, 8);
    StyledDocument sDoc = feedbackTxtpn.getStyledDocument();
    Style feedbackTxtpnStyle = sDoc.addStyle("feedbackTxtpnStyle", null);;
    StyleConstants.setForeground(feedbackTxtpnStyle, Color.BLACK);

    try{
      sDoc.insertString(sDoc.getLength(), "\n"+ a_text, feedbackTxtpnStyle);
    }
    catch (Exception e){
      System.out.println(e);
    }
  }

  public void writeToBottomInfoVersion(String a_text){
    MutableAttributeSet attrs = bottomInfoVersionTxtpn.getInputAttributes();
    StyleConstants.setFontFamily(attrs, "monospaced");
    StyleConstants.setFontSize(attrs, 6);
    StyledDocument sDoc = bottomInfoVersionTxtpn.getStyledDocument();
    Style bottomInfoVersionTxtpnStyle = sDoc.addStyle("bottomInfoVersionTxtpnStyle", null);;
    StyleConstants.setForeground(bottomInfoVersionTxtpnStyle, Color.BLACK);

    try{
      sDoc.insertString(0, a_text, bottomInfoVersionTxtpnStyle);
    }
    catch (Exception e){
      System.out.println(e);
    }
  }
  
  public void writeTobottomInfoCom(String a_text){
    MutableAttributeSet attrs = bottomInfoComTxtpn.getInputAttributes();
    StyleConstants.setFontFamily(attrs, "monospaced");
    StyleConstants.setFontSize(attrs, 6);
    StyledDocument sDoc = bottomInfoComTxtpn.getStyledDocument();
    Style bottomInfoComTxtpnStyle = sDoc.addStyle("bottomInfoComTxtpnStyle", null);;
    StyleConstants.setForeground(bottomInfoComTxtpnStyle, Color.BLACK);

    try{
      sDoc.insertString(0, a_text, bottomInfoComTxtpnStyle);
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
    gridBagLayout.columnWidths = new int[] {200, 600};
    gridBagLayout.rowHeights = new int[] {600, 10};
    gridBagLayout.columnWeights = new double[]{1.0, 0.0};
    gridBagLayout.rowWeights = new double[]{1.0, 0.0};
    this.getContentPane().setLayout(gridBagLayout);
    this.setVisible(true);
    
    // vehicle simulation
    vehicleSimulation = new VehicleSimulation();
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
    gbc_bottomInfoPanel.gridwidth = 3;
    gbc_bottomInfoPanel.fill = GridBagConstraints.HORIZONTAL;
    gbc_bottomInfoPanel.gridx = 0;
    gbc_bottomInfoPanel.gridy = 1;
    this.getContentPane().add(bottomInfoPanel, gbc_bottomInfoPanel);
    bottomInfoPanel.setLayout(new GridLayout(0, 2, 0, 0));

    // bottom version detail info text pane
    bottomInfoVersionTxtpn = new JTextPane();
    bottomInfoVersionTxtpn.setBackground(SystemColor.control);
    bottomInfoVersionTxtpn.setEditable(false);
    bottomInfoPanel.add(bottomInfoVersionTxtpn);
    bottomInfoVersionTxtpn.setText("version 0.0, build 12a34b 20160214-17:44");
    
    // bottom communication detail info text pane
    bottomInfoComTxtpn = new JTextPane();
    bottomInfoComTxtpn.setBackground(SystemColor.control);
    bottomInfoComTxtpn.setEditable(false);
    bottomInfoPanel.add(bottomInfoComTxtpn);
    bottomInfoComTxtpn.setText("Com1, 9600,8,N,1");
    
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
  
  // PRIVATE ATTRIBUTES
  int sizeX, sizeY;
  // buttons and groups
  private final ButtonGroup protoButtonGroup = new ButtonGroup();
  // images
  private ImageFactory imagefactory = new ImageFactory();
  // views and menu
  private VehicleSimulation vehicleSimulation;
  private JPanel FeedbackPanel;
  private JPanel bottomInfoPanel;
  private JTextPane bottomInfoVersionTxtpn;
  private JTextPane bottomInfoComTxtpn;
  private JTextPane feedbackTxtpn;
  private JMenuBar menuBar;
  private JMenu mnSetting;
  private JMenuItem mnProtocol;
  private JMenuItem mntmPortSettings;
  
  // port settings class
  private PortSettingPanel portSettingPanel;
  private ProtocolPanel protocolPanel;
  private Event subscriber ;
  
} // end of Ovmain 

package view;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuListener;

import images.ImageFactory;

import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JMenuItem;

/**
 * @author Sander
 * @brief This is the main user interface frame and acts as controler for the panels shown 
 *        on the main view
 */
public class OVmainView extends JFrame implements Observer{

  /**
   *  Constructor
   */
  public OVmainView(int sizeX, int sizeY) {
    this.sizeX = sizeX;
    this.sizeY = sizeY;
    initialize();
  }
  // PUBLIC METHODS
  public void createVehicleButton(int a_amount){
    
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

    mntmPortSettings = new JMenuItem("Port settings");
    mnSetting.add(mntmPortSettings);

    mnProtocol = new JMenu("Protocol");
    menuBar.add(mnProtocol);

    rdbtnKar = new JRadioButton("KAR");
    rdbtnKar.setSelected(true);
    protoButtonGroup.add(rdbtnKar);
    mnProtocol.add(rdbtnKar);

    rdbtnVecom = new JRadioButton("VECOM");
    protoButtonGroup.add(rdbtnVecom);
    mnProtocol.add(rdbtnVecom);

    rdbtnSics = new JRadioButton("SICS");
    protoButtonGroup.add(rdbtnSics);
    mnProtocol.add(rdbtnSics);

    mnAbout = new JMenu("About");
    mnAbout.setName("About");
    menuBar.add(mnAbout);
  }
 
  /**
   * @brief Add a VehicleButton to the VehicleSimulation pane and tie it to a
   *        given ActionListener
   * @param a_listener
   */
  public void addVehicleButtonAndListener(ActionListener a_listener){
    vehicleSimulation.createAndAddVehicleButton(a_listener);
  }
  /**
   * @brief Add a MenuListener to the About menu item
   * @param a_listener
   */
  public void addAboutListener(MenuListener a_listener){
    mnAbout.addMenuListener(a_listener);
  }
  
  //TODO next add actionlistener for radio button (group)
  public void addPortSettingListener(ActionListener a_listener){
    rdbtnKar.addActionListener(a_listener);
    rdbtnVecom.addActionListener(a_listener);
    rdbtnSics.addActionListener(a_listener);
  }
  
  
  public void writeToFeedback(String a_text){
    feedbackTxtpn.setText("");
  }
  
  public void writeToBottomInfoVersion(String a_text){
    feedbackTxtpn.setText("");

  }
  
  public void bottomInfoCom(String a_text){
    feedbackTxtpn.setText("");

  }
  
  public void update(Observable o, Object arg) {
    // TODO Auto-generated method stub
    feedbackTxtpn.setText("obervable called me");
  }
  
  
  // PRIVATE ATTRIBUTES
  int sizeX, sizeY;
  // buttons and groups
  private final ButtonGroup protoButtonGroup = new ButtonGroup();
  // images
  private ImageFactory imagefactory = new ImageFactory();
  // views and menu
  VehicleSimulation vehicleSimulation;
  JPanel FeedbackPanel;
  JPanel bottomInfoPanel;
  JTextPane bottomInfoVersionTxtpn;
  JTextPane bottomInfoComTxtpn;
  JTextPane feedbackTxtpn;
  JMenuBar menuBar;
  JMenu mnSetting;
  JMenu mnAbout;
  JMenu mnProtocol;
  JMenuItem mntmPortSettings;
  
  JRadioButton rdbtnKar;
  JRadioButton rdbtnVecom;
  JRadioButton rdbtnSics;
  
} // end of Ovmain 

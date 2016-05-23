package view;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.border.TitledBorder;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.apache.commons.lang3.StringUtils;

import controller.Event;
import images.ImageFactory;
import model.Communicator.ComTransmission;
import model.KarProtocol;
import model.Protocol;
import model.VecomProtocol;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Timer;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

/**
 * @author Sander
 * @brief This is the main user interface frame and acts as manager for the panels shown 
 *        on the main view
 * @category singleton
 */
public class ViewManager extends JFrame{

  private static ViewManager INSTANCE;
  private static final long serialVersionUID = 1L;

  /**
   *  Constructor
   */
  private ViewManager(int sX, int sY) {
    sizeX = sX;
    sizeY = sY;
    showComData = false;
    setMinimumSize(new Dimension(sizeX, sizeY));
    imagefactory = new ImageFactory();
    initialize();
  }

  // PUBLIC METHODS

  public static ViewManager getInstance(){
    if (INSTANCE == null){
      synchronized(ViewManager.class){
        if(INSTANCE==null)
          INSTANCE=new ViewManager(800, 600);
      }
    }
    return INSTANCE;
  }

  /**
   * @brief The subsriber gets a signal when a certain event takes place.
   * @param a_subscriber
   */
  public void addEventSubscriber(Event a_subscriber){
    subscriber  = a_subscriber;
  }

  public PortSettingPanel getPortSettingPanel() {
    return portSettingPanel;
  }
  public void setPortSettingPanel(PortSettingPanel portSettingPanel) {
    this.portSettingPanel = portSettingPanel;
  }

  public ProtocolPanel getProtocolPanel() {
    return protocolPanel;
  }
  public void setProtocolPanel(ProtocolPanel protocolPanel) {
    this.protocolPanel = protocolPanel;
  }

  public VehicleSimulation getVehicleSimulation() {
    return vehicleSimulation;
  }
  public void setVehicleSimulation(VehicleSimulation vehicleSimulation) {
    this.vehicleSimulation = vehicleSimulation;
  }

  public ViewManager getINSTANCE(){
    return this;
  }

  public boolean isShowComData() {
    return showComData;
  }

  public void clearFeedback(){
    StyledDocument sDoc = feedbackTxtpn.getStyledDocument();
    try{
      sDoc.remove(0, sDoc.getLength());
    }
    catch (Exception e){
    }
  }
  
  /**
   * writes the currently pressed vehicle button settings to the feedback panel
   * @param a_vehiclebutton
   */
  public void writeVehicleButtonSetting(VehicleButton a_vb, Protocol a_proto){
    String string = "";
    int fontSize = 12;
//    if (a_proto instanceof VecomProtocol){
      string = StringUtils.leftPad("\n", 80, '-');
      writeToFeedback(0, string, Color.BLACK,fontSize);
      
      string = StringUtils.rightPad("Loop nr (1)", OFFSET);
      writeToFeedback(0, string, Color.BLACK,fontSize);
      string = StringUtils.rightPad(Integer.toString(a_vb.getLoopNr()), OFFSET);
      writeToFeedback(0, string, Color.BLACK,fontSize);
      string = StringUtils.rightPad("Vehicle type (2)", OFFSET);
      writeToFeedback(0, string, Color.BLACK,fontSize);
      string = StringUtils.rightPad(a_vb.getVehicleType().name(), OFFSET);
      writeToFeedback(0, string + "\n", Color.BLACK,fontSize);
      
      string = StringUtils.rightPad("Line nr (3)", OFFSET);
      writeToFeedback(0, string, Color.BLACK,fontSize);
      string = StringUtils.rightPad(Integer.toString(a_vb.getLineNr()), OFFSET);
      writeToFeedback(0, string, Color.BLACK,fontSize);
      string = StringUtils.rightPad("Service nr (4)", OFFSET);
      writeToFeedback(0, string, Color.BLACK,fontSize);
      string = StringUtils.rightPad(Integer.toString(a_vb.getVehServiceNr()), OFFSET);   
      writeToFeedback(0, string + "\n", Color.BLACK,fontSize);
      
      string = StringUtils.rightPad("Veh. id (6)", OFFSET);
      writeToFeedback(0, string, Color.BLACK,fontSize);
      string = StringUtils.rightPad(Integer.toString(a_vb.getVehicleId()), OFFSET);
      writeToFeedback(0, string, Color.BLACK,fontSize);
      string = StringUtils.rightPad("Manual control(7)", OFFSET);
      writeToFeedback(0, string, Color.BLACK,fontSize);
      string = StringUtils.rightPad(a_vb.getManualControl().name(), OFFSET);
      writeToFeedback(0, string + "\n", Color.BLACK,fontSize);
      
      string = StringUtils.rightPad("Punct. Class (10)", OFFSET);
      writeToFeedback(0, string, Color.BLACK,fontSize);
      string = StringUtils.rightPad(a_vb.getPunctualityClass().name(), OFFSET);
      writeToFeedback(0, string, Color.BLACK,fontSize);
      string = StringUtils.rightPad("Journey Type (17)", OFFSET);
      writeToFeedback(0, string, Color.BLACK,fontSize);
      string = StringUtils.rightPad(a_vb.getJourneyType().name(), OFFSET);
      writeToFeedback(0, string + "\n", Color.BLACK,fontSize);
      
      string = StringUtils.rightPad("Direction(18)", OFFSET);
      writeToFeedback(0, string, Color.BLACK,fontSize);
      string = StringUtils.rightPad(a_vb.getDirection().name(), OFFSET);
      writeToFeedback(0, string + "\n", Color.BLACK,fontSize);
      
      string = StringUtils.leftPad("\n", 80, '-');
      writeToFeedback(0, string, Color.BLACK,fontSize);
      
//    }else if (a_proto instanceof KarProtocol){
//      //TODO implement and get the settings right from the settings panels textfields
//    }

  }

  /**
   * @brief writes given text to FEEDBACK pane on a new line
   * @param aText
   * @param color
   * @param size
   */
  public void writeToFeedback(int offset, String a_text, Color color, int size){
    StyledDocument sDoc = feedbackTxtpn.getStyledDocument();
    Style feedbackTxtpnStyle = sDoc.addStyle("feedbackTxtpnStyle", null);;
    StyleConstants.setForeground(feedbackTxtpnStyle, color);
    StyleConstants.setFontFamily(feedbackTxtpnStyle, "Monospaced");
    StyleConstants.setFontSize(feedbackTxtpnStyle, size);
    
    try{
      sDoc.insertString(sDoc.getLength(), a_text, feedbackTxtpnStyle);
      feedbackTxtpn.setCaretPosition(sDoc.getLength());
    }
    catch (Exception e){
    }
  }

  /**
   * @brief writes given text to INFOVERSION pane
   * @param aText
   * @param color
   */
  public void writeToBottomInfoVersion(String aText, Color color){
    StyledDocument sDoc = bottomInfoVersionTxtpn.getStyledDocument();
    Style bottomInfoVersionTxtpnStyle = sDoc.addStyle("bottomInfoVersionTxtpnStyle", null);;
    StyleConstants.setForeground(bottomInfoVersionTxtpnStyle, color);
    StyleConstants.setFontFamily(bottomInfoVersionTxtpnStyle, "Monospaced");
    StyleConstants.setFontSize(bottomInfoVersionTxtpnStyle, 12);

    try{
      sDoc.remove(0, sDoc.getLength());
      sDoc.insertString(0, aText, bottomInfoVersionTxtpnStyle);
    }
    catch (Exception e){
      System.out.println(e);
    }
  }

  /**
   * @brief writes given text to COMSETTINGS pane
   * @param aText
   * @param color
   */
  public void writeToBottomInfoComSettings(String aText, Color color){
    StyledDocument sDoc = bottomInfoComSettingTxtpn.getStyledDocument();
    Style bottomInfoComSettingsTxtpnStyle = sDoc.addStyle("bottomInfoComSettingsTxtpnStyle", null);;
    StyleConstants.setForeground(bottomInfoComSettingsTxtpnStyle, color);
    StyleConstants.setFontFamily(bottomInfoComSettingsTxtpnStyle, "Monospaced");
    StyleConstants.setFontSize(bottomInfoComSettingsTxtpnStyle, 12);
    
    try{
      sDoc.remove(0, sDoc.getLength());
      sDoc.insertString(0, aText, bottomInfoComSettingsTxtpnStyle);
    }
    catch (Exception e){
      System.out.println(e);
    }
  }

  /**
   * @brief writes given text to COMSTATUS pane
   * @param aText
   * @param color
   */
  public void writeTobottomInfoComStatus(String aText, Color color){
    StyledDocument sDoc = bottomInfoComStatusTxtpn.getStyledDocument();
    Style bottomInfoComStatusTxtpnStyle = sDoc.addStyle("bottomInfoComStatusTxtpnStyle", null);;
    StyleConstants.setForeground(bottomInfoComStatusTxtpnStyle, color);
    StyleConstants.setFontFamily(bottomInfoComStatusTxtpnStyle, "Monospaced");
    StyleConstants.setFontSize(bottomInfoComStatusTxtpnStyle, 12);
    
    try{
      sDoc.remove(0, sDoc.getLength());
      sDoc.insertString(0, aText, bottomInfoComStatusTxtpnStyle);
    }
    catch (Exception e){
      System.out.println(e);
    }
  }

  /**
   * @brief writes given text to PROTO pane
   * @param aText
   * @param color
   */
  public void writeToBottomProto(String aText, Color color){  
    StyledDocument sDoc = bottomInfoProtoTxtpn.getStyledDocument();
    Style bottomInfoProtoTxtpnStyle = sDoc.addStyle("bottomInfoProtoTxtpnStyle", null);;
    StyleConstants.setForeground(bottomInfoProtoTxtpnStyle, color);
    StyleConstants.setFontFamily(bottomInfoProtoTxtpnStyle, "Monospaced");
    StyleConstants.setFontSize(bottomInfoProtoTxtpnStyle, 12);

    try{
      sDoc.remove(0, sDoc.getLength());
      sDoc.insertString(0, aText, bottomInfoProtoTxtpnStyle);
    }
    catch (Exception e){
      System.out.println(e);
    }
  }

  /**
   * @brief writes given text to PROTOEXTRAINFO pane
   * @param aText
   * @param color
   */
  public void writeToBottomProtoXtraInfo(String aText, Color color){ 
    StyledDocument sDoc = bottomInfoProtoXtraInfoTxtpn.getStyledDocument();
    Style bottomInfoProtoXtraInfoTxtpnStyle = sDoc.addStyle("bottomInfoProtoXtraInfoTxtpnStyle", null);;
    StyleConstants.setForeground(bottomInfoProtoXtraInfoTxtpnStyle, color);
    StyleConstants.setFontFamily(bottomInfoProtoXtraInfoTxtpnStyle, "Monospaced");
    StyleConstants.setFontSize(bottomInfoProtoXtraInfoTxtpnStyle, 12);

    try{
      sDoc.remove(0, sDoc.getLength());
      sDoc.insertString(0, aText, bottomInfoProtoXtraInfoTxtpnStyle);
    }
    catch (Exception e){
      System.out.println(e);
    }
  }

  /**
   * @brief to indicate that there is a transmission through a com port.
   * @param connected.
   */
  public void rxtxIndication(ComTransmission rxtx){

    switch (rxtx){
      case RX:
        if(lblRx.getIcon().equals(imagefactory.getImageIcon("ledYellow")) == false){
          lblRx.setIcon(imagefactory.getImageIcon("ledYellow"));
          timer_RX_TO = new Timer(50, new RxTimerActionListener(lblRx));
          timer_RX_TO.start();
        }
        break;
      case TX:
        if(lblTx.getIcon().equals(imagefactory.getImageIcon("ledGreen")) == false){
          lblTx.setIcon(imagefactory.getImageIcon("ledGreen"));
          timer_TX_TO = new Timer(50, new TxTimerActionListener(lblTx));
          timer_TX_TO.start();
        }
        break;
      case NONE:
      default:
        lblRx.setIcon(imagefactory.getImageIcon("ledGray"));
        lblTx.setIcon(imagefactory.getImageIcon("ledGray"));
    }
  }

  // PRIVATE METHODS
  private void initialize() {

    this.setResizable(true);
    this.setTitle("OV simulator");
    this.setIconImage(imagefactory.getImageIcon("swarcoLogo").getImage());
    this.setBounds(25, 25, 685, 490);
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    this.addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent e) {
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure want to exit?", "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        switch(choice){
          case JOptionPane.YES_OPTION:
            closeWindow();
            break;
          case JOptionPane.NO_OPTION:
            break;
          case JOptionPane.CANCEL_OPTION:
            break;
        }
      }
    });
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] {150, 650};
    gridBagLayout.rowHeights = new int[] {590, 60};
    gridBagLayout.columnWeights = new double[]{0.0, 1.0};
    gridBagLayout.rowWeights = new double[]{1.0, 0.0};
    this.getContentPane().setLayout(gridBagLayout);
    this.setVisible(true);

    // vehicle simulation
    vehicleSimulation = new VehicleSimulation();
    vehicleSimulation.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Simulation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    GridBagConstraints gbc_vehicleSimulation = new GridBagConstraints();
    gbc_vehicleSimulation.anchor = GridBagConstraints.LINE_START;
    gbc_vehicleSimulation.insets = new Insets(5, 5, 0, 0);
    gbc_vehicleSimulation.fill = GridBagConstraints.NONE;
    gbc_vehicleSimulation.gridx = 0;
    gbc_vehicleSimulation.gridy = 0;
    getContentPane().add(vehicleSimulation, gbc_vehicleSimulation);

    // feedback panel
    FeedbackPanel = new JPanel();
    FeedbackPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Feedback", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    GridBagConstraints gbc_FeedbackPanel = new GridBagConstraints();
    gbc_FeedbackPanel.weighty = 0.5;
    gbc_FeedbackPanel.weightx = 0.5;
    gbc_FeedbackPanel.insets = new Insets(5, 0, 0, 5);
    gbc_FeedbackPanel.fill = GridBagConstraints.BOTH;
    gbc_FeedbackPanel.gridx = 1;
    gbc_FeedbackPanel.gridy = 0;
    this.getContentPane().add(FeedbackPanel, gbc_FeedbackPanel);
    FeedbackPanel.setLayout(new GridLayout(0, 1, 0, 0));

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setEnabled(true);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    FeedbackPanel.add(scrollPane);

    feedbackTxtpn = new JTextPane();
    feedbackTxtpn.setEnabled(true);
    feedbackTxtpn.setEditable(false);
    feedbackTxtpn.setBackground(SystemColor.inactiveCaptionBorder);
    scrollPane.setViewportView(feedbackTxtpn);

    // bottom info panel
    bottomInfoPanel = new JPanel();
    bottomInfoPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    GridBagConstraints gbc_bottomInfoPanel = new GridBagConstraints();
    gbc_bottomInfoPanel.insets = new Insets(0, 5, 5, 5);
    gbc_bottomInfoPanel.anchor = GridBagConstraints.SOUTH;
    gbc_bottomInfoPanel.weighty = 0.0;
    gbc_bottomInfoPanel.weightx = 0.0;
    gbc_bottomInfoPanel.gridheight = 2;
    gbc_bottomInfoPanel.gridwidth = 3;
    gbc_bottomInfoPanel.fill = GridBagConstraints.BOTH;
    gbc_bottomInfoPanel.gridx = 0;
    gbc_bottomInfoPanel.gridy = 1;
    this.getContentPane().add(bottomInfoPanel, gbc_bottomInfoPanel);
    GridBagLayout gbl_bottomInfoPanel = new GridBagLayout();
    gbl_bottomInfoPanel.columnWidths = new int[] {198, 198, 198, 198};
    gbl_bottomInfoPanel.rowHeights = new int[] {30, 30};
    gbl_bottomInfoPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0};
    gbl_bottomInfoPanel.rowWeights = new double[]{1.0, 1.0};
    bottomInfoPanel.setLayout(gbl_bottomInfoPanel);

    // bottom version detail info text pane
    bottomInfoVersionTxtpn = new JTextPane();
    bottomInfoVersionTxtpn.setEditable(false);
    bottomInfoVersionTxtpn.setToolTipText("Information about the build. ");
    bottomInfoVersionTxtpn.setBackground(SystemColor.control);
    GridBagConstraints gbc_bottomInfoVersionTxtpn = new GridBagConstraints();
    gbc_bottomInfoVersionTxtpn.fill = GridBagConstraints.BOTH;
    gbc_bottomInfoVersionTxtpn.insets = new Insets(0, 5, 5, 5);
    gbc_bottomInfoVersionTxtpn.gridx = 0;
    gbc_bottomInfoVersionTxtpn.gridy = 0;
    bottomInfoPanel.add(bottomInfoVersionTxtpn, gbc_bottomInfoVersionTxtpn);
    bottomInfoVersionTxtpn.setText("");
    bottomInfoVersionTxtpn.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Version", TitledBorder.LEADING, TitledBorder.TOP, null, null));

    // bottom communication settings detail info text pane
    bottomInfoComSettingTxtpn = new JTextPane();
    bottomInfoComSettingTxtpn.setEditable(false);
    bottomInfoComSettingTxtpn.setBackground(SystemColor.control);
    GridBagConstraints gbc_bottomInfoComSettingTxtpn = new GridBagConstraints();
    gbc_bottomInfoComSettingTxtpn.fill = GridBagConstraints.BOTH;
    gbc_bottomInfoComSettingTxtpn.insets = new Insets(0, 0, 5, 5);
    gbc_bottomInfoComSettingTxtpn.gridx = 1;
    gbc_bottomInfoComSettingTxtpn.gridy = 0;
    bottomInfoPanel.add(bottomInfoComSettingTxtpn, gbc_bottomInfoComSettingTxtpn);
    bottomInfoComSettingTxtpn.setText("");
    bottomInfoComSettingTxtpn.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Port settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));


    ComNoticepanel = new JPanel();
    ComNoticepanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    GridBagConstraints gbc_ComNoticepanel = new GridBagConstraints();
    gbc_ComNoticepanel.insets = new Insets(6, 0, 6, 5);
    gbc_ComNoticepanel.gridheight = 2;
    gbc_ComNoticepanel.fill = GridBagConstraints.BOTH;
    gbc_ComNoticepanel.gridx = 3;
    gbc_ComNoticepanel.gridy = 0;
    bottomInfoPanel.add(ComNoticepanel, gbc_ComNoticepanel);
    GridBagLayout gbl_ComNoticepanel = new GridBagLayout();
    gbl_ComNoticepanel.columnWidths = new int[] {50, 100};
    gbl_ComNoticepanel.rowHeights = new int[] {20, 20, 20};
    ComNoticepanel.setLayout(gbl_ComNoticepanel);

    btnClearFeedback = new JButton("Clear screen");
    btnClearFeedback.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        clearFeedback();
      }
    });
    btnClearFeedback.setToolTipText("Clears the feedback screen");
    GridBagConstraints gbc_btnClearFeedback = new GridBagConstraints();
    gbc_btnClearFeedback.fill = GridBagConstraints.HORIZONTAL;
    gbc_btnClearFeedback.gridwidth = 2;
    gbc_btnClearFeedback.insets = new Insets(0, 5, 0, 5);
    gbc_btnClearFeedback.gridx = 0;
    gbc_btnClearFeedback.gridy = 0;
    ComNoticepanel.add(btnClearFeedback, gbc_btnClearFeedback);

    lblTx = new JLabel("TX");
    GridBagConstraints gbc_lblTx = new GridBagConstraints();
    gbc_lblTx.fill = GridBagConstraints.BOTH;
    gbc_lblTx.insets = new Insets(5, 5, 5, 5);
    gbc_lblTx.gridx = 0;
    gbc_lblTx.gridy = 2;
    ComNoticepanel.add(lblTx, gbc_lblTx);
    lblTx.setIcon(imagefactory.getImageIcon("ledGray"));

    lblRx = new JLabel("RX");
    lblRx.setIcon(imagefactory.getImageIcon("ledGray"));
    GridBagConstraints gbc_lblRx = new GridBagConstraints();
    gbc_lblRx.fill = GridBagConstraints.BOTH;
    gbc_lblRx.insets = new Insets(0, 5, 0, 5);
    gbc_lblRx.gridx = 0;
    gbc_lblRx.gridy = 1;
    ComNoticepanel.add(lblRx, gbc_lblRx);

    cbShowCommunication = new JCheckBox("Show protocol");
    cbShowCommunication.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED){
          showComData = true;
        }
        else {
          showComData = false;
        }
      }
    });
    cbShowCommunication.setHorizontalTextPosition(SwingConstants.LEFT);

    cbShowCommunication.setToolTipText("When this checkbox is selected all the protocol communication between the OV-simulator and the host is displayed on the feedback screen. ");
    GridBagConstraints gbc_cbShowCommunication = new GridBagConstraints();
    gbc_cbShowCommunication.anchor = GridBagConstraints.WEST;
    gbc_cbShowCommunication.insets = new Insets(0, 0, 0, 5);
    gbc_cbShowCommunication.gridx = 1;
    gbc_cbShowCommunication.gridy = 1;
    ComNoticepanel.add(cbShowCommunication, gbc_cbShowCommunication);

    // bottom communication status detail info text pane
    bottomInfoComStatusTxtpn = new JTextPane();
    bottomInfoComStatusTxtpn.setEditable(false);
    bottomInfoComStatusTxtpn.setBackground(SystemColor.control);
    GridBagConstraints gbc_bottomInfoComStatusTxtpn = new GridBagConstraints();
    gbc_bottomInfoComStatusTxtpn.insets = new Insets(0, 0, 5, 5);
    gbc_bottomInfoComStatusTxtpn.fill = GridBagConstraints.BOTH;
    gbc_bottomInfoComStatusTxtpn.gridx = 1;
    gbc_bottomInfoComStatusTxtpn.gridy = 1;
    bottomInfoPanel.add(bottomInfoComStatusTxtpn, gbc_bottomInfoComStatusTxtpn);
    bottomInfoComStatusTxtpn.setText("Not connected.");
    bottomInfoComStatusTxtpn.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Port status", TitledBorder.LEADING, TitledBorder.TOP, null, null));

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
    bottomInfoProtoTxtpn.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Protocol", TitledBorder.LEADING, TitledBorder.TOP, null, null));

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
    bottomInfoProtoXtraInfoTxtpn.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Protocol settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));

    // menu bar
    menuBar = new JMenuBar();
    this.setJMenuBar(menuBar);

    mnFile = new JMenu("File");
    menuBar.add(mnFile);

    mntmOpen = new JMenuItem("Open");
    mntmOpen.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        subscriber.signal(ViewManager.getInstance(), "openEvent");
      }
    });
    mnFile.add(mntmOpen);

    mntmSave = new JMenuItem("Save");
    mntmSave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        subscriber.signal(ViewManager.getInstance(), "saveEvent");
      }
    });
    mnFile.add(mntmSave);

    mntmSaveAs = new JMenuItem("Save as");
    mntmSaveAs.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        subscriber.signal(ViewManager.getInstance(), "saveAsEvent");
      }
    });
    mnFile.add(mntmSaveAs);

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
          case JOptionPane.OK_OPTION:
            // signal Communicator for we have some COM settings to update
            subscriber.signal(portSettingPanel, null);
            break;
          default:
            // do nothing
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
          case JOptionPane.OK_OPTION:
            protocolPanel.handleOK();
            subscriber.signal(protocolPanel, null);
            break;
          default:
        }
      }
    });
    mnSetting.add(mnProtocol);
  }

  private void closeWindow(){
    subscriber.signal(this, "closeWindowEvent");
  }

  // LISTENERS
  /**
   * @brief Action listener for a reaction to a rxtx led timeout
   * @author Sander
   * @TODO change this for a timer_RX_TO with callback slot
   */
  public class RxTimerActionListener implements ActionListener {

    public RxTimerActionListener(JLabel label){
      this.label = label;
    };

    public void actionPerformed(ActionEvent e) {
      label.setIcon(imagefactory.getImageIcon("ledGray"));
      timer_RX_TO.stop();
    }
    JLabel label = null;
  }

  public class TxTimerActionListener implements ActionListener {

    public TxTimerActionListener(JLabel label){
      this.label = label;
    };

    public void actionPerformed(ActionEvent e) {
      label.setIcon(imagefactory.getImageIcon("ledGray"));
      timer_TX_TO.stop();
    }
    JLabel label = null;
  }

  // PRIVATE ATTRIBUTES
  int sizeX, sizeY;
  boolean showComData;
  // images
  private ImageFactory imagefactory;
  // views and menu
  private VehicleSimulation vehicleSimulation; //persisent
  private JPanel FeedbackPanel;
  private JTextPane feedbackTxtpn;
  private JPanel bottomInfoPanel;
  private JTextPane bottomInfoVersionTxtpn;
  private JTextPane bottomInfoComSettingTxtpn;
  private JTextPane bottomInfoComStatusTxtpn;
  private JTextPane bottomInfoProtoTxtpn;

  private JMenuBar menuBar;
  private JMenu mnSetting;
  private JMenuItem mnProtocol;
  private JMenuItem mntmPortSettings;

  // port settings class
  private PortSettingPanel portSettingPanel; //persistent
  private ProtocolPanel protocolPanel; //persistent
  private Event subscriber;
  private JTextPane bottomInfoProtoXtraInfoTxtpn;

  private Timer timer_RX_TO = null;
  private Timer timer_TX_TO = null;

  private JPanel ComNoticepanel;
  private JLabel lblRx;
  private JLabel lblTx;
  private JButton btnClearFeedback;
  private JCheckBox cbShowCommunication;
  private JMenu mnFile;
  private JMenuItem mntmOpen;
  private JMenuItem mntmSave;
  private JMenuItem mntmSaveAs;
  private static final int OFFSET = 20;

} // end of Ovmain 

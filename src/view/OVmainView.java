package view;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.sun.accessibility.internal.resources.accessibility;

import images.ImageFactory;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

/**
 * @author Sander
 * @brief This is the main user interface
 */
public class OVmainView extends JFrame{

  /**
   *  Constructor
   */
  public OVmainView(int sizeX, int sizeY) {
    this.sizeX = sizeX;
    this.sizeY = sizeY;
    initialize();
  }

  // private methods
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
    addVehicleSimulateListener(); // LMB action

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

    JTextPane feedbackTxtpn = new JTextPane();
    feedbackTxtpn.setText("Test text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!\r\nTest text is here to stay!!!");
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

    // bottom version detail info tekst pane
    bottomInfoVersionTxtpn = new JTextPane();
    bottomInfoVersionTxtpn.setBackground(SystemColor.control);
    bottomInfoVersionTxtpn.setEditable(false);
    bottomInfoPanel.add(bottomInfoVersionTxtpn);
    bottomInfoVersionTxtpn.setText("version 0.0, build 12a34b 20160214-17:44");
    
    // bottom communication detail info tekst pane
    bottomInfoComTxtpn = new JTextPane();
    bottomInfoComTxtpn.setBackground(SystemColor.control);
    bottomInfoComTxtpn.setEditable(false);
    bottomInfoPanel.add(bottomInfoComTxtpn);
    bottomInfoComTxtpn.setText("Com1, 9600,8,N,1");
    
    // menu bar
    JMenuBar menuBar = new JMenuBar();
    this.setJMenuBar(menuBar);

    JMenu mnSetting = new JMenu("Setting");
    menuBar.add(mnSetting);

    JMenuItem mntmPortSettings = new JMenuItem("Port settings");
    mnSetting.add(mntmPortSettings);

    JMenu mnProtocol = new JMenu("Protocol");
    menuBar.add(mnProtocol);

    JRadioButton rdbtnKar = new JRadioButton("KAR");
    rdbtnKar.setSelected(true);
    protoButtonGroup.add(rdbtnKar);
    mnProtocol.add(rdbtnKar);

    JRadioButton rdbtnVecom = new JRadioButton("VECOM");
    protoButtonGroup.add(rdbtnVecom);
    mnProtocol.add(rdbtnVecom);

    JRadioButton rdbtnSics = new JRadioButton("SICS");
    protoButtonGroup.add(rdbtnSics);
    mnProtocol.add(rdbtnSics);

    JMenu mnAbout = new JMenu("About");
    mnAbout.setName("About");
    mnAbout.addMenuListener(new MenuListener() {	
      @Override
      public void menuSelected(MenuEvent arg0) {
        JOptionPane.showMessageDialog((JMenu)arg0.getSource(), "Created by Sander",((JMenu)arg0.getSource()).getName(), JOptionPane.INFORMATION_MESSAGE);
      }
      @Override
      public void menuCanceled(MenuEvent e) {
      }
      @Override
      public void menuDeselected(MenuEvent e) {
      }
    });
    menuBar.add(mnAbout);
  }
  /**
   * @brief Add actionlisteners to the buttons assosiated with the vehicle simulation view.
   * Only the the simulation is handled here and therefor only the LMB action. The RMB action is handled in the 
   * VehicleButton self
   */
  private void addVehicleSimulateListener(){
    vehicleButtonList = vehicleSimulation.getVehicleButtonList();
    for (VehicleButton vb : vehicleButtonList){
      vb.addActionListener(new VehicleSimulationListener() );
    }
  }
  
  // action listener for handling the simulation of all vehicleButtons
  class VehicleSimulationListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      System.out.println("LMB");
      
    }
}//end inner class VehicleSimulationListener
  
  
  // private attributes
  int sizeX, sizeY;
  // buttons and groups
  private final ButtonGroup protoButtonGroup = new ButtonGroup();
  private ArrayList<VehicleButton> vehicleButtonList;
  // images
  private ImageFactory imagefactory = new ImageFactory();
  // views
  VehicleSimulation vehicleSimulation;
  JPanel FeedbackPanel;
  JPanel bottomInfoPanel;
  JTextPane bottomInfoVersionTxtpn;
  JTextPane bottomInfoComTxtpn;
  
  
} // end of Ovmain 

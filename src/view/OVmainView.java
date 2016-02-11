package view;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

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
import java.net.URL;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;


/**
 * @author Sander
 * @brief This is the main user interface
 */
public class OVmainView extends JFrame{

  // buttons and groups
  private final ButtonGroup protoButtonGroup = new ButtonGroup();
  // images
  ImageIcon emptyVehicleImage;
  ImageIcon swarcoLogoImage;

  /**
   *  Constructor
   */
  public OVmainView() {
    loadImages();
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
//    this = new JFrame();
    this.setResizable(false);
    this.setTitle("OV simulator");
    this.setIconImage(swarcoLogoImage.getImage());
    this.setBounds(100, 100, 800, 600);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] {200, 600};
    gridBagLayout.rowHeights = new int[] {500, 10};
    gridBagLayout.columnWeights = new double[]{1.0, 0.0};
    gridBagLayout.rowWeights = new double[]{1.0, 0.0};
    this.getContentPane().setLayout(gridBagLayout);
    this.setVisible(true);
    
    VehicleSimulation vehicleSimulation = new VehicleSimulation();
    vehicleSimulation.setBorder(new TitledBorder(null, "Simulation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    GridBagConstraints gbc_vehicleSimulation = new GridBagConstraints();
    gbc_vehicleSimulation.insets = new Insets(0, 0, 5, 5);
    gbc_vehicleSimulation.fill = GridBagConstraints.BOTH;
    gbc_vehicleSimulation.gridx = 0;
    gbc_vehicleSimulation.gridy = 0;
    getContentPane().add(vehicleSimulation, gbc_vehicleSimulation);

    JPanel FeedbackPanel = new JPanel();
    FeedbackPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Feedback", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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

    JPanel bottomInfoPanel = new JPanel();
    bottomInfoPanel.setBorder(null);
    FlowLayout flowLayout = (FlowLayout) bottomInfoPanel.getLayout();
    flowLayout.setAlignment(FlowLayout.LEFT);
    GridBagConstraints gbc_bottomInfoPanel = new GridBagConstraints();
    gbc_bottomInfoPanel.fill = GridBagConstraints.HORIZONTAL;
    gbc_bottomInfoPanel.gridx = 1;
    gbc_bottomInfoPanel.gridy = 1;
    this.getContentPane().add(bottomInfoPanel, gbc_bottomInfoPanel);

    JTextPane BottomInfoTxtpn = new JTextPane();
    BottomInfoTxtpn.setEditable(false);
    BottomInfoTxtpn.setSize(600, 10);
    bottomInfoPanel.add(BottomInfoTxtpn);
    BottomInfoTxtpn.setText("Some uber important info here");

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
   * Load all images
   */
  private void loadImages() {
    emptyVehicleImage = loadImageIcon("/images/emptyVehicle.jpg", "An icon that represents a non configured vehicle");
    swarcoLogoImage = loadImageIcon("/images/SWARCOLOGO.jpeg", "Main SWARCO logo");
  }

  private ImageIcon loadImageIcon(String filename, String description) {
    URL link = OVmainView.class.getResource(filename); 
    return new ImageIcon(link, description);
  }
} // end of Ovmain 

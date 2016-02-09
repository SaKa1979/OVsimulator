package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.border.TitledBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

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
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class OVmain {

  // frame
  private JFrame frmOvSimulator;
  // factory
  private ImageFactory the_imageFactory;
  // buttons and groups
  private final ButtonGroup protoButtonGroup = new ButtonGroup();

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          ImageFactory imageFactory = new ImageFactory();
          OVmain window = new OVmain(imageFactory);
          window.frmOvSimulator.setVisible(true);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public OVmain(ImageFactory a_imagefactory) {
    the_imageFactory = a_imagefactory;
//    loadImages();
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmOvSimulator = new JFrame();
    frmOvSimulator.setResizable(false);
    frmOvSimulator.setTitle("OV simulator");
    frmOvSimulator.setIconImage(the_imageFactory.createImageIcon("/images/SWARCOLOGO.jpeg", "Main SWARCO logo").getImage());
    frmOvSimulator.setBounds(100, 100, 800, 600);
    frmOvSimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] {200, 600};
    gridBagLayout.rowHeights = new int[] {500, 10};
    gridBagLayout.columnWeights = new double[]{0.0, 0.0};
    gridBagLayout.rowWeights = new double[]{0.0, 0.0};
    frmOvSimulator.getContentPane().setLayout(gridBagLayout);

    JPanel VehiclePanel = new JPanel();
    VehiclePanel.setToolTipText("Press the left mouse button to sent public transport message to the ITC.\r\nPress the right mouse button to configure this button.");
    VehiclePanel.setBorder(new TitledBorder(null, "Vehicle", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    GridBagConstraints gbc_VehiclePanel = new GridBagConstraints();
    gbc_VehiclePanel.insets = new Insets(5, 5, 5, 5);
    gbc_VehiclePanel.fill = GridBagConstraints.BOTH;
    gbc_VehiclePanel.gridx = 0;
    gbc_VehiclePanel.gridy = 0;
    frmOvSimulator.getContentPane().add(VehiclePanel, gbc_VehiclePanel);
    VehiclePanel.setLayout(new GridLayout(10, 2, 5, 5));
    // vehicle buttons
    for (int i = 0; i < 20; i++){
    JButton btnVeh = new JButton("veh_"+i);
    btnVeh.setIcon(the_imageFactory.createImageIcon("/images/emptyVehicle.jpg", "An icon that represents a non configured vehicle"));
    VehiclePanel.add(btnVeh);
    }

    JPanel FeedbackPanel = new JPanel();
    FeedbackPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Feedback", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
    GridBagConstraints gbc_FeedbackPanel = new GridBagConstraints();
    gbc_FeedbackPanel.insets = new Insets(5, 0, 5, 5);
    gbc_FeedbackPanel.fill = GridBagConstraints.BOTH;
    gbc_FeedbackPanel.gridx = 1;
    gbc_FeedbackPanel.gridy = 0;
    frmOvSimulator.getContentPane().add(FeedbackPanel, gbc_FeedbackPanel);
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
    frmOvSimulator.getContentPane().add(bottomInfoPanel, gbc_bottomInfoPanel);

    JTextPane BottomInfoTxtpn = new JTextPane();
    BottomInfoTxtpn.setEditable(false);
    BottomInfoTxtpn.setSize(600, 10);
    bottomInfoPanel.add(BottomInfoTxtpn);
    BottomInfoTxtpn.setText("Some uber important info here");

    JMenuBar menuBar = new JMenuBar();
    frmOvSimulator.setJMenuBar(menuBar);

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
//  /**
//   * Load all images
//   */
//  private void loadImages() {
//    emptyVehicleImage = loadImageIcon("/images/emptyVehicle.jpg", "An icon that represents a non configured vehicle");
//    swarcoLogoImage = loadImageIcon("/images/SWARCOLOGO.jpeg", "Main SWARCO logo");
//  }
//
//  private ImageIcon loadImageIcon(String filename, String description) {
//    URL link = OVmain.class.getResource(filename); 
//    return new ImageIcon(link, description);
//  }
} // end of Ovmain 

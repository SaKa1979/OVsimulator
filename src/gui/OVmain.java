package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JList;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.SystemColor;
import javax.swing.AbstractListModel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class OVmain {

  private JFrame frmOvSimulator;
  private final ButtonGroup protoButtonGroup = new ButtonGroup();

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          OVmain window = new OVmain();
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
  public OVmain() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmOvSimulator = new JFrame();
    frmOvSimulator.setResizable(false);
    frmOvSimulator.setSize(new Dimension(1024, 768));
    frmOvSimulator.setPreferredSize(new Dimension(1024, 768));
    frmOvSimulator.getContentPane().setPreferredSize(new Dimension(1024, 768));
    frmOvSimulator.getContentPane().setSize(new Dimension(1024, 768));
    frmOvSimulator.setTitle("OV simulator");
    frmOvSimulator.setIconImage(Toolkit.getDefaultToolkit().getImage(OVmain.class.getResource("/images/SWARCOLOGO.jpeg")));
    frmOvSimulator.setBounds(100, 100, 499, 341);
    frmOvSimulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] {100, 0, 200};
    gridBagLayout.rowHeights = new int[]{0, 222, 0};
    gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0};
    gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
    frmOvSimulator.getContentPane().setLayout(gridBagLayout);
    
    JPanel VehiclePanel = new JPanel();
    VehiclePanel.setToolTipText("Press the left mouse button to sent public transport message to the ITC.\r\nPress the right mouse button to configure this button.");
    VehiclePanel.setBorder(new TitledBorder(null, "Vehicle", TitledBorder.LEADING, TitledBorder.TOP, null, null));
    GridBagConstraints gbc_VehiclePanel = new GridBagConstraints();
    gbc_VehiclePanel.insets = new Insets(0, 0, 5, 5);
    gbc_VehiclePanel.fill = GridBagConstraints.BOTH;
    gbc_VehiclePanel.gridx = 0;
    gbc_VehiclePanel.gridy = 0;
    frmOvSimulator.getContentPane().add(VehiclePanel, gbc_VehiclePanel);
    VehiclePanel.setLayout(new GridLayout(1, 0, 0, 0));
    
    JPanel FeedbackPanel = new JPanel();
    FeedbackPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Feedback", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
    GridBagConstraints gbc_FeedbackPanel = new GridBagConstraints();
    gbc_FeedbackPanel.insets = new Insets(0, 0, 5, 0);
    gbc_FeedbackPanel.fill = GridBagConstraints.BOTH;
    gbc_FeedbackPanel.gridx = 2;
    gbc_FeedbackPanel.gridy = 0;
    frmOvSimulator.getContentPane().add(FeedbackPanel, gbc_FeedbackPanel);
    FeedbackPanel.setLayout(new GridLayout(0, 1, 0, 0));
    
    JScrollPane scrollPane = new JScrollPane();
    FeedbackPanel.add(scrollPane);
    
    JTextPane feedbackTxtpn = new JTextPane();
    feedbackTxtpn.setText("Test text is here to stay!!!");
    feedbackTxtpn.setBackground(SystemColor.inactiveCaptionBorder);
    scrollPane.setViewportView(feedbackTxtpn);
    
    JPanel panel = new JPanel();
    GridBagConstraints gbc_panel = new GridBagConstraints();
    gbc_panel.anchor = GridBagConstraints.SOUTH;
    gbc_panel.insets = new Insets(0, 0, 0, 5);
    gbc_panel.gridx = 1;
    gbc_panel.gridy = 1;
    frmOvSimulator.getContentPane().add(panel, gbc_panel);
    
    JTextPane txtpnSomeUberImportant = new JTextPane();
    txtpnSomeUberImportant.setText("Some uber important info here");
    panel.add(txtpnSomeUberImportant);
    
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
  }

  private static void addPopup(Component component, final JPopupMenu popup) {
    component.addMouseListener(new MouseAdapter() {
    	public void mousePressed(MouseEvent e) {
    		if (e.isPopupTrigger()) {
    			showMenu(e);
    		}
    	}
    	public void mouseReleased(MouseEvent e) {
    		if (e.isPopupTrigger()) {
    			showMenu(e);
    		}
    	}
    	private void showMenu(MouseEvent e) {
    		popup.show(e.getComponent(), e.getX(), e.getY());
    	}
    });
  }
}

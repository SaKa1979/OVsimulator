package components;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.beans.*; //Property change stuff
import java.awt.*;
import java.awt.event.*;

/*
 * DialogMain.java requires these files:
 *   CustomDialog.java
 *   images/middle.gif
 */
public class DialogMain extends JPanel {
    JTextArea outputText;
    ImageIcon icon = createImageIcon("/images/SWARCOLOGO.jpeg");
    JFrame frame;
    CustomDialog customDialog;
    ProtoListener protoListener = new ProtoListener();


    /** Creates the GUI shown inside the frame's content pane. */
    public DialogMain(JFrame frame) {
        super(new BorderLayout());
        this.frame = frame;
        if (icon!=null)
        this.frame.setIconImage(icon.getImage());
//        customDialog = new CustomDialog(frame, "geisel", this);
//        customDialog.pack();

        //Create the components.
        JPanel ovSimulatorDialogPanel = createOVsimulatorDialogPanel();
        JPanel serialConnectorDialogPanel = createSerialConnectorDialogPanel();
        JPanel reservePanel = createReservePanel();
        JPanel outputPanel = createOutputPanel();

        //Set them up
        Border padding = BorderFactory.createEmptyBorder(20,20,5,20);
        ovSimulatorDialogPanel.setBorder(padding);
        serialConnectorDialogPanel.setBorder(padding);
        reservePanel.setBorder(padding);
        outputPanel.setBorder(padding);
        ovSimulatorDialogPanel.setMinimumSize(new Dimension(200,200));
        outputPanel.setMinimumSize(new Dimension(200,200)); 
        
        //Lay them out.
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("OV simulator", null,ovSimulatorDialogPanel);
        tabbedPane.addTab("Settings", null,serialConnectorDialogPanel);
        tabbedPane.addTab("reserve", null,reservePanel);

        // Add them to main Dialog
        add(tabbedPane, BorderLayout.WEST);
        add(outputPanel,BorderLayout.CENTER);
    }
    
/*--------------------------------------------------------------------------------------------------*/

    /** Sets the text displayed at the bottom of the frame. */
    void setLabel(String newText) {
        outputText.setText(newText);
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = DialogMain.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

/*--------------------------------------------------------------------------------------------------*/
    
    /** Creates the panel shown by the OVsimulator tab. */
    private JPanel createOVsimulatorDialogPanel() {
        //Create the components.      
        JPanel borderPanel = new JPanel(new BorderLayout());    /* used as foundation to place gridPanel*/
        JPanel protoPanel = new JPanel(new GridLayout(1,3));    /* used for OV protocol radioButtons */
        JPanel buttonsPanel = new JPanel(new GridLayout(15,3)); /* used for the message buttons */
        
        // set them up
        Border padding = BorderFactory.createEmptyBorder(20,20,5,20);
        protoPanel.setBorder(padding);
        buttonsPanel.setBorder(padding);
        
        
        final int numProtos = 3;
        final int numButtons = 15;
        JRadioButton[] protoRadioButtons = new JRadioButton[numProtos];
        JButton[] buttons = new JButton[numButtons];
        final ButtonGroup group = new ButtonGroup();

//        final String kar = "KAR";
//        final String vecom = "VECOM";
//        final String sics = "SICS";

        protoRadioButtons[0] = new JRadioButton("KAR");
        protoRadioButtons[0].setActionCommand("KAR");
        protoRadioButtons[0].addActionListener(protoListener);
        protoRadioButtons[1] = new JRadioButton("VECOM");
        protoRadioButtons[1].setActionCommand("VECOM");
        protoRadioButtons[1].addActionListener(protoListener);
        protoRadioButtons[2] = new JRadioButton("SICS");
        protoRadioButtons[2].setActionCommand("SICS");
        protoRadioButtons[2].addActionListener(protoListener);

        for (int i = 0; i < numProtos; i++) {
            group.add(protoRadioButtons[i]);
            protoRadioButtons[i].setSize(new Dimension(15,50));
            protoPanel.add(protoRadioButtons[i]);
        }
        protoRadioButtons[0].setSelected(true);

        for (int i = 0; i < numButtons; i++) {
          buttons[i] = new JButton("" + i);
          buttons[i].setSize(new Dimension(30,30));
          buttonsPanel.add(buttons[i]);
      }


        borderPanel.add(protoPanel,BorderLayout.WEST);
//        borderPanel.add(send_btn, BorderLayout.SOUTH);
        return borderPanel;
    }
    // protoListener
    public class ProtoListener implements ActionListener{
      String command;
      public void actionPerformed(ActionEvent e){
        
//        if ( e.getSource() instanceof JRadioButton  ){
//          command = group.getSelection().getActionCommand();
//          if (command == "kar") {
//            outputText.insert("KAR selected" ,0);
//          }
//        }
//        
//        if(e.getSource() instanceof JRadioButton  ){
//          command = group.getSelection().getActionCommand();
//          if (command == vecom) {
//            outputText.insert("VECOM selected" ,0);
//          }
//        }
//        
//        if( e.getSource() instanceof JRadioButton  ){
//          command = group.getSelection().getActionCommand();
//          if (command == sics) {
//            outputText.insert("SICS selected" ,0);
//          }
//        } 
      }
    }// end of inner class
/*--------------------------------------------------------------------------------------------------*/

    /** Creates the text output panel tab. */
    private JPanel createOutputPanel() {
        JPanel borderPanel = new JPanel(new FlowLayout());    /* used as foundation to place the outputTextEarea*/
        outputText = new JTextArea("Reply from ITC.");
        JScrollPane scrollpane = new JScrollPane(outputText);
        scrollpane.setBounds(120,20,150,150);
        outputText.setEditable(false);
        outputText.setWrapStyleWord(true);
        outputText.setLineWrap(true);

        borderPanel.add(scrollpane);
        return borderPanel;
    }

/*--------------------------------------------------------------------------------------------------*/

    /**
     * Used by createSimpleDialogBox and createFeatureDialogBox
     * to create a pane containing a description, a single column
     * of radio buttons, and the Show it! button.
     */
    private JPanel createPane(String description,
                              JRadioButton[] radioButtons,
                              JButton showButton) {

        int numChoices = radioButtons.length;
        JPanel box = new JPanel();
        JLabel label = new JLabel(description);

        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
        box.add(label);

        for (int i = 0; i < numChoices; i++) {
            box.add(radioButtons[i]);
        }

        JPanel pane = new JPanel(new BorderLayout());
        pane.add(box, BorderLayout.PAGE_START);
        pane.add(showButton, BorderLayout.PAGE_END);
        return pane;
    }

    /**
     * Like createPane, but creates a pane with 2 columns of radio
     * buttons.  The number of buttons passed in *must* be even.
     */
     private JPanel create2ColPane(String description,
                                  JRadioButton[] radioButtons,
                                  JButton showButton) {
        JLabel label = new JLabel(description);
        int numPerColumn = radioButtons.length/2;

        JPanel grid = new JPanel(new GridLayout(0, 2));
        for (int i = 0; i < numPerColumn; i++) {
            grid.add(radioButtons[i]);
            grid.add(radioButtons[i + numPerColumn]);
        }

        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
        box.add(label);
        grid.setAlignmentX(0.0f);
        box.add(grid);

        JPanel pane = new JPanel(new BorderLayout());
        pane.add(box, BorderLayout.PAGE_START);
        pane.add(showButton, BorderLayout.PAGE_END);

        return pane;
    }


    /** Creates the panel shown by the second tab. */
    private JPanel createSerialConnectorDialogPanel() {
        final int numButtons = 5;
        JRadioButton[] radioButtons = new JRadioButton[numButtons];
        final ButtonGroup group = new ButtonGroup();

        JButton showItButton = null;

        final String pickOneCommand = "pickone";
        final String textEnteredCommand = "textfield";
        final String nonAutoCommand = "nonautooption";
        final String customOptionCommand = "customoption";
        final String nonModalCommand = "nonmodal";

        radioButtons[0] = new JRadioButton("Pick one of several choices");
        radioButtons[0].setActionCommand(pickOneCommand);

        radioButtons[1] = new JRadioButton("Enter some text");
        radioButtons[1].setActionCommand(textEnteredCommand);

        radioButtons[2] = new JRadioButton("Non-auto-closing dialog");
        radioButtons[2].setActionCommand(nonAutoCommand);

        radioButtons[3] = new JRadioButton("Input-validating dialog "
                                           + "(with custom message area)");
        radioButtons[3].setActionCommand(customOptionCommand);

        radioButtons[4] = new JRadioButton("Non-modal dialog");
        radioButtons[4].setActionCommand(nonModalCommand);

        for (int i = 0; i < numButtons; i++) {
            group.add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);

        showItButton = new JButton("Show it!");
        showItButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = group.getSelection().getActionCommand();

                //pick one of many
                if (command == pickOneCommand) {
                    Object[] possibilities = {"ham", "spam", "yam"};
                    String s = (String)JOptionPane.showInputDialog(
                                        frame,
                                        "Complete the sentence:\n"
                                        + "\"Green eggs and...\"",
                                        "Customized Dialog",
                                        JOptionPane.PLAIN_MESSAGE,
                                        icon,
                                        possibilities,
                                        "ham");

                    //If a string was returned, say so.
                    if ((s != null) && (s.length() > 0)) {
                        setLabel("Green eggs and... " + s + "!");
                        return;
                    }

                    //If you're here, the return value was null/empty.
                    setLabel("Come on, finish the sentence!");

                //text input
                } else if (command == textEnteredCommand) {
                    String s = (String)JOptionPane.showInputDialog(
                                        frame,
                                        "Complete the sentence:\n"
                                        + "\"Green eggs and...\"",
                                        "Customized Dialog",
                                        JOptionPane.PLAIN_MESSAGE,
                                        icon,
                                        null,
                                        "ham");

                    //If a string was returned, say so.
                    if ((s != null) && (s.length() > 0)) {
                        setLabel("Green eggs and... " + s + "!");
                        return;
                    }

                    //If you're here, the return value was null/empty.
                    setLabel("Come on, finish the sentence!");

                //non-auto-closing dialog
                } else if (command == nonAutoCommand) {
                    final JOptionPane optionPane = new JOptionPane(
                                    "The only way to close this dialog is by\n"
                                    + "pressing one of the following buttons.\n"
                                    + "Do you understand?",
                                    JOptionPane.QUESTION_MESSAGE,
                                    JOptionPane.YES_NO_OPTION);

                    //You can't use pane.createDialog() because that
                    //method sets up the JDialog with a property change
                    //listener that automatically closes the window
                    //when a button is clicked.
                    final JDialog dialog = new JDialog(frame,
                                                 "Click a button",
                                                 true);
                    dialog.setContentPane(optionPane);
                    dialog.setDefaultCloseOperation(
                        JDialog.DO_NOTHING_ON_CLOSE);
                    dialog.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent we) {
                            setLabel("Thwarted user attempt to close window.");
                        }
                    });
                    optionPane.addPropertyChangeListener(
                        new PropertyChangeListener() {
                            public void propertyChange(PropertyChangeEvent e) {
                                String prop = e.getPropertyName();

                                if (dialog.isVisible()
                                 && (e.getSource() == optionPane)
                                 && (JOptionPane.VALUE_PROPERTY.equals(prop))) {
                                    //If you were going to check something
                                    //before closing the window, you'd do
                                    //it here.
                                    dialog.setVisible(false);
                                }
                            }
                        });
                    dialog.pack();
                    dialog.setLocationRelativeTo(frame);
                    dialog.setVisible(true);

                    int value = ((Integer)optionPane.getValue()).intValue();
                    if (value == JOptionPane.YES_OPTION) {
                        setLabel("Good.");
                    } else if (value == JOptionPane.NO_OPTION) {
                        setLabel("Try using the window decorations "
                                 + "to close the non-auto-closing dialog. "
                                 + "You can't!");
                    } else {
                        setLabel("Window unavoidably closed (ESC?).");
                    }

                //non-auto-closing dialog with custom message area
                //NOTE: if you don't intend to check the input,
                //then just use showInputDialog instead.
                } else if (command == customOptionCommand) {
                    customDialog.setLocationRelativeTo(frame);
                    customDialog.setVisible(true);

                    String s = customDialog.getValidatedText();
                    if (s != null) {
                        //The text is valid.
                        setLabel("Congratulations!  "
                                 + "You entered \""
                                 + s
                                 + "\".");
                    }

                //non-modal dialog
                } else if (command == nonModalCommand) {
                    //Create the dialog.
                    final JDialog dialog = new JDialog(frame,
                                                       "A Non-Modal Dialog");

                    //Add contents to it. It must have a close button,
                    //since some L&Fs (notably Java/Metal) don't provide one
                    //in the window decorations for dialogs.
                    JLabel label = new JLabel("<html><p align=center>"
                        + "This is a non-modal dialog.<br>"
                        + "You can have one or more of these up<br>"
                        + "and still use the main window.");
                    label.setHorizontalAlignment(JLabel.CENTER);
                    Font font = label.getFont();
                    label.setFont(label.getFont().deriveFont(font.PLAIN,
                                                             14.0f));

                    JButton closeButton = new JButton("Close");
                    closeButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            dialog.setVisible(false);
                            dialog.dispose();
                        }
                    });
                    JPanel closePanel = new JPanel();
                    closePanel.setLayout(new BoxLayout(closePanel,
                                                       BoxLayout.LINE_AXIS));
                    closePanel.add(Box.createHorizontalGlue());
                    closePanel.add(closeButton);
                    closePanel.setBorder(BorderFactory.
                        createEmptyBorder(0,0,5,5));

                    JPanel contentPane = new JPanel(new BorderLayout());
                    contentPane.add(label, BorderLayout.CENTER);
                    contentPane.add(closePanel, BorderLayout.PAGE_END);
                    contentPane.setOpaque(true);
                    dialog.setContentPane(contentPane);

                    //Show it.
                    dialog.setSize(new Dimension(300, 150));
                    dialog.setLocationRelativeTo(frame);
                    dialog.setVisible(true);
                }
            }
        });

        return createPane(":",
                          radioButtons,
                          showItButton);
    }

    /*
     * Creates the panel shown by the 3rd tab.
     * These dialogs are implemented using showMessageDialog, but
     * you can specify the icon (using similar code) for any other
     * kind of dialog, as well.
     */
    private JPanel createReservePanel() {
        JButton showItButton = null;

        final int numButtons = 6;
        JRadioButton[] radioButtons = new JRadioButton[numButtons];
        final ButtonGroup group = new ButtonGroup();

        final String plainCommand = "plain";
        final String infoCommand = "info";
        final String questionCommand = "question";
        final String errorCommand = "error";
        final String warningCommand = "warning";
        final String customCommand = "custom";

        radioButtons[0] = new JRadioButton("Plain (no icon)");
        radioButtons[0].setActionCommand(plainCommand);

        radioButtons[1] = new JRadioButton("Information icon");
        radioButtons[1].setActionCommand(infoCommand);

        radioButtons[2] = new JRadioButton("Question icon");
        radioButtons[2].setActionCommand(questionCommand);

        radioButtons[3] = new JRadioButton("Error icon");
        radioButtons[3].setActionCommand(errorCommand);

        radioButtons[4] = new JRadioButton("Warning icon");
        radioButtons[4].setActionCommand(warningCommand);

        radioButtons[5] = new JRadioButton("Custom icon");
        radioButtons[5].setActionCommand(customCommand);

        for (int i = 0; i < numButtons; i++) {
            group.add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);

        showItButton = new JButton("Show it!");
        showItButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = group.getSelection().getActionCommand();

                //no icon
                if (command == plainCommand) {
                    JOptionPane.showMessageDialog(frame,
                                    "Eggs aren't supposed to be green.",
                                    "A plain message",
                                    JOptionPane.PLAIN_MESSAGE);
                //information icon
                } else if (command == infoCommand) {
                    JOptionPane.showMessageDialog(frame,
                                    "Eggs aren't supposed to be green.",
                                    "Inane informational dialog",
                                    JOptionPane.INFORMATION_MESSAGE);

                //question icon
                } else if (command == questionCommand) {
                    JOptionPane.showMessageDialog(frame,
                                    "You shouldn't use a message dialog "
                                    + "(like this)\n"
                                    + "for a question, OK?",
                                    "Inane question",
                                    JOptionPane.QUESTION_MESSAGE);
                //error icon
                } else if (command == errorCommand) {
                    JOptionPane.showMessageDialog(frame,
                                    "Eggs aren't supposed to be green.",
                                    "Inane error",
                                    JOptionPane.ERROR_MESSAGE);
                //warning icon
                } else if (command == warningCommand) {
                    JOptionPane.showMessageDialog(frame,
                                    "Eggs aren't supposed to be green.",
                                    "Inane warning",
                                    JOptionPane.WARNING_MESSAGE);
                //custom icon
                } else if (command == customCommand) {
                    JOptionPane.showMessageDialog(frame,
                                    "Eggs aren't supposed to be green.",
                                    "Inane custom dialog",
                                    JOptionPane.INFORMATION_MESSAGE,
                                    icon);
                }
            }
        });

        return create2ColPane(":",
                              radioButtons,
                              showItButton);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("OV simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        DialogMain newContentPane = new DialogMain(frame);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}

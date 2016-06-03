package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.prefs.Preferences;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DontAskAgainPanel extends JPanel {
	
	private static final long serialVersionUID = -6631154041398396560L;
	
	private static Preferences prefs = Preferences.userNodeForPackage(controller.Main.class);
	private static JCheckBox dontAskMeAgain;
    private static final String DO_NOT_ASK_AGAIN_PREF = "DO_NOT_ASK_AGAIN";
	
    public static int showConfirmDialog(Component parent, Object message, String key) {
    	boolean dontAskAgain = prefs.getBoolean(DO_NOT_ASK_AGAIN_PREF, false);

        int result = JOptionPane.YES_OPTION;
        if (dontAskAgain) {
        	result = JOptionPane.NO_OPTION;
        } else {
            DontAskAgainPanel panel = new DontAskAgainPanel(message);
            result = JOptionPane.showConfirmDialog(parent, panel);
            if (dontAskMeAgain()) {
            	prefs.putBoolean(DO_NOT_ASK_AGAIN_PREF, dontAskMeAgain());
            }
        }
        return result;
    }
	
  	public static void resetPrefs() {
  		prefs.putBoolean(DO_NOT_ASK_AGAIN_PREF, false);
  	}

    private DontAskAgainPanel(Object message) {
        setLayout(new BorderLayout());
        if (message instanceof Component) {
            add((Component) message);
        } else if (message != null) {
            add(new JLabel(message.toString()));
        }
        dontAskMeAgain = new JCheckBox("Don't ask me again");
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(dontAskMeAgain);
        add(panel, BorderLayout.SOUTH);
    }

    private static boolean dontAskMeAgain() {
        return dontAskMeAgain.isSelected();
    }
}
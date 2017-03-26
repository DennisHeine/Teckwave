import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Mon Jun 25 03:05:28 CEST 2007
 */



/**
 * @author Dennis Heine
 */
public class SupportAbschluss extends JFrame {

	String mUsername, mPasswort, mIDSession;

	public SupportAbschluss(String Username, String Passwort, String IDSession) {
		mUsername=Username;
		mPasswort=Passwort; 
		mIDSession=IDSession;
		initComponents();
		
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Dennis Heine
		ResourceBundle bundle = ResourceBundle.getBundle("Support.SupportAbschluss");
		dialogPane = new JPanel();
		bnOK = new JButton();
		cbProblemGeloest = new JCheckBox();
		cbRueckfrage = new JCheckBox();
		scrollPane1 = new JScrollPane();
		textArea1 = new JTextArea();
		label1 = new JLabel();
		bnNaechsteAnfrage = new JCheckBox();
		bnBugreporting = new JButton();

		//======== this ========
		setTitle(bundle.getString("this.title"));
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

			// JFormDesigner evaluation mark
			dialogPane.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			dialogPane.setLayout(null);

			//---- bnOK ----
			bnOK.setText(bundle.getString("bnOK.text"));
			dialogPane.add(bnOK);
			bnOK.setBounds(265, 195, 65, 25);

			//---- cbProblemGeloest ----
			cbProblemGeloest.setText(bundle.getString("cbProblemGeloest.text"));
			dialogPane.add(cbProblemGeloest);
			cbProblemGeloest.setBounds(5, 10, 95, cbProblemGeloest.getPreferredSize().height);

			//---- cbRueckfrage ----
			cbRueckfrage.setText(bundle.getString("cbRueckfrage.text"));
			dialogPane.add(cbRueckfrage);
			cbRueckfrage.setBounds(5, 30, 90, cbRueckfrage.getPreferredSize().height);

			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(textArea1);
			}
			dialogPane.add(scrollPane1);
			scrollPane1.setBounds(10, 75, 320, 115);

			//---- label1 ----
			label1.setText(bundle.getString("label1.text"));
			dialogPane.add(label1);
			label1.setBounds(new Rectangle(new Point(10, 55), label1.getPreferredSize()));

			//---- bnNaechsteAnfrage ----
			bnNaechsteAnfrage.setText(bundle.getString("bnNaechsteAnfrage.text"));
			bnNaechsteAnfrage.setSelected(true);
			dialogPane.add(bnNaechsteAnfrage);
			bnNaechsteAnfrage.setBounds(10, 195, 170, bnNaechsteAnfrage.getPreferredSize().height);

			//---- bnBugreporting ----
			bnBugreporting.setText(bundle.getString("bnBugreporting.text"));
			dialogPane.add(bnBugreporting);
			bnBugreporting.setBounds(225, 30, 103, bnBugreporting.getPreferredSize().height);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < dialogPane.getComponentCount(); i++) {
					Rectangle bounds = dialogPane.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = dialogPane.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				dialogPane.setMinimumSize(preferredSize);
				dialogPane.setPreferredSize(preferredSize);
			}
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Dennis Heine
	private JPanel dialogPane;
	private JButton bnOK;
	private JCheckBox cbProblemGeloest;
	private JCheckBox cbRueckfrage;
	private JScrollPane scrollPane1;
	private JTextArea textArea1;
	private JLabel label1;
	private JCheckBox bnNaechsteAnfrage;
	private JButton bnBugreporting;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}

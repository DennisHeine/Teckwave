package com.teckwave.Status;
import com.teckwave.Status.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Thu Jul 05 21:53:43 CEST 2007
 */



/**
 * @author Dennis Heine
 */
public class Kontakt extends JDialog {
	public Kontakt(Frame owner) {
		super(owner);
		initComponents();
	}

	public Kontakt(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void lblInfoMouseClicked(MouseEvent e) {
		// TODO add your code here
		OpenEmail("mailto:info@teckwave.de");
	}

	private void lblSupportMouseClicked(MouseEvent e) {
		// TODO add your code here
		OpenEmail("mailto:support@teckwave.de");	
	}

	private void lblVerwaltungMouseClicked(MouseEvent e) {
		// TODO add your code here
		OpenEmail("mailto:verwaltung@teckwave.de");
	}

	private void lblVerkaufMouseClicked(MouseEvent e) {
		// TODO add your code here
		OpenEmail("mailto:verkauf@teckwave.de");
	}

	private void OpenEmail(String s)
    	{
        	String s1 = System.getProperty("os.name");
	        if(s1.charAt(0)=='W')
        	try
	        {
        	        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+s);
	        }
        	catch(Exception exception)
	        {
        	        System.out.println(exception.toString());
	        }
	        else      	     
                	JOptionPane.showMessageDialog(this, "E-Mail Programm konnte nicht gefunden werden.\nBitte Senden Sie die E-Mail manuell.");
	            
	}
		public void okButtonActionPerformed()
		{
			this.hide();
		}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Dennis Heine
		ResourceBundle bundle = ResourceBundle.getBundle("lang.Deutsch.Kontakt");
		dialogPane = new JPanel();
		okButton = new JButton();
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		label5 = new JLabel();
		label6 = new JLabel();
		label7 = new JLabel();
		label8 = new JLabel();
		label9 = new JLabel();
		label10 = new JLabel();
		label11 = new JLabel();
		label12 = new JLabel();
		label13 = new JLabel();
		label14 = new JLabel();
		lblInfo = new JLabel();
		lblSupport = new JLabel();
		lblVerwaltung = new JLabel();
		lblVerkauf = new JLabel();
		label19 = new JLabel();
		label20 = new JLabel();
		label21 = new JLabel();
		label22 = new JLabel();

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
					"", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			dialogPane.setLayout(null);

			//---- okButton ----
			okButton.setText(bundle.getString("okButton.text"));
			okButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					okButtonActionPerformed();
				}
			});
			dialogPane.add(okButton);
			okButton.setBounds(430, 270, 70, 25);

			//---- label1 ----
			label1.setText(bundle.getString("label1.text"));
			dialogPane.add(label1);
			label1.setBounds(10, 5, 375, 25);

			//---- label2 ----
			label2.setText(bundle.getString("label2.text"));
			dialogPane.add(label2);
			label2.setBounds(10, 25, 380, label2.getPreferredSize().height);

			//---- label3 ----
			label3.setText(bundle.getString("label3.text"));
			dialogPane.add(label3);
			label3.setBounds(10, 40, 365, label3.getPreferredSize().height);

			//---- label4 ----
			label4.setText(bundle.getString("label4.text"));
			label4.setFont(new Font("Tahoma", Font.BOLD, 11));
			dialogPane.add(label4);
			label4.setBounds(new Rectangle(new Point(10, 70), label4.getPreferredSize()));

			//---- label5 ----
			label5.setText(bundle.getString("label5.text"));
			dialogPane.add(label5);
			label5.setBounds(new Rectangle(new Point(110, 70), label5.getPreferredSize()));

			//---- label6 ----
			label6.setText(bundle.getString("label6.text"));
			dialogPane.add(label6);
			label6.setBounds(new Rectangle(new Point(110, 85), label6.getPreferredSize()));

			//---- label7 ----
			label7.setText(bundle.getString("label7.text"));
			dialogPane.add(label7);
			label7.setBounds(new Rectangle(new Point(110, 100), label7.getPreferredSize()));

			//---- label8 ----
			label8.setText(bundle.getString("label8.text"));
			dialogPane.add(label8);
			label8.setBounds(new Rectangle(new Point(110, 115), label8.getPreferredSize()));

			//---- label9 ----
			label9.setText(bundle.getString("label9.text"));
			dialogPane.add(label9);
			label9.setBounds(new Rectangle(new Point(110, 130), label9.getPreferredSize()));

			//---- label10 ----
			label10.setText(bundle.getString("label10.text"));
			label10.setFont(new Font("Tahoma", Font.BOLD, 11));
			dialogPane.add(label10);
			label10.setBounds(new Rectangle(new Point(235, 70), label10.getPreferredSize()));

			//---- label11 ----
			label11.setText(bundle.getString("label11.text"));
			label11.setFont(new Font("Tahoma", Font.BOLD, 11));
			dialogPane.add(label11);
			label11.setBounds(new Rectangle(new Point(235, 85), label11.getPreferredSize()));

			//---- label12 ----
			label12.setText(bundle.getString("label12.text"));
			dialogPane.add(label12);
			label12.setBounds(new Rectangle(new Point(285, 70), label12.getPreferredSize()));

			//---- label13 ----
			label13.setText(bundle.getString("label13.text"));
			dialogPane.add(label13);
			label13.setBounds(new Rectangle(new Point(285, 85), label13.getPreferredSize()));

			//---- label14 ----
			label14.setText(bundle.getString("label14.text"));
			label14.setFont(new Font("Tahoma", Font.BOLD, 11));
			dialogPane.add(label14);
			label14.setBounds(new Rectangle(new Point(10, 165), label14.getPreferredSize()));

			//---- lblInfo ----
			lblInfo.setText(bundle.getString("lblInfo.text"));
			lblInfo.setForeground(Color.blue);
			lblInfo.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					lblInfoMouseClicked(e);
				}
			});
			dialogPane.add(lblInfo);
			lblInfo.setBounds(new Rectangle(new Point(10, 180), lblInfo.getPreferredSize()));

			//---- lblSupport ----
			lblSupport.setText(bundle.getString("lblSupport.text"));
			lblSupport.setForeground(Color.blue);
			lblSupport.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					lblSupportMouseClicked(e);
				}
			});
			dialogPane.add(lblSupport);
			lblSupport.setBounds(new Rectangle(new Point(10, 195), lblSupport.getPreferredSize()));

			//---- lblVerwaltung ----
			lblVerwaltung.setText(bundle.getString("lblVerwaltung.text"));
			lblVerwaltung.setForeground(Color.blue);
			lblVerwaltung.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					lblVerwaltungMouseClicked(e);
				}
			});
			dialogPane.add(lblVerwaltung);
			lblVerwaltung.setBounds(new Rectangle(new Point(10, 210), lblVerwaltung.getPreferredSize()));

			//---- lblVerkauf ----
			lblVerkauf.setText(bundle.getString("lblVerkauf.text"));
			lblVerkauf.setForeground(Color.blue);
			lblVerkauf.addMouseListener(new MouseAdapter() {

				public void mouseClicked(MouseEvent e) {
					lblVerkaufMouseClicked(e);
				}
			});
			dialogPane.add(lblVerkauf);
			lblVerkauf.setBounds(new Rectangle(new Point(10, 225), lblVerkauf.getPreferredSize()));

			//---- label19 ----
			label19.setText(bundle.getString("label19.text"));
			dialogPane.add(label19);
			label19.setBounds(new Rectangle(new Point(165, 180), label19.getPreferredSize()));

			//---- label20 ----
			label20.setText(bundle.getString("label20.text"));
			dialogPane.add(label20);
			label20.setBounds(new Rectangle(new Point(165, 195), label20.getPreferredSize()));

			//---- label21 ----
			label21.setText(bundle.getString("label21.text"));
			dialogPane.add(label21);
			label21.setBounds(new Rectangle(new Point(165, 210), label21.getPreferredSize()));

			//---- label22 ----
			label22.setText(bundle.getString("label22.text"));
			dialogPane.add(label22);
			label22.setBounds(new Rectangle(new Point(165, 225), label22.getPreferredSize()));

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
		contentPane.add(dialogPane, BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Dennis Heine
	private JPanel dialogPane;
	private JButton okButton;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JLabel label8;
	private JLabel label9;
	private JLabel label10;
	private JLabel label11;
	private JLabel label12;
	private JLabel label13;
	private JLabel label14;
	private JLabel lblInfo;
	private JLabel lblSupport;
	private JLabel lblVerwaltung;
	private JLabel lblVerkauf;
	private JLabel label19;
	private JLabel label20;
	private JLabel label21;
	private JLabel label22;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}

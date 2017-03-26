import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import com.jgoodies.uif_lite.panel.*;
/*
 * Created by JFormDesigner on Mon Jun 25 03:31:44 CEST 2007
 */



/**
 * @author Dennis Heine
 */
public class SupportZentrale extends JFrame {
	String mUsername,mPasswort;
	Protokoll mProtokoll;
	SupportChat chat;
	public SupportZentrale(String Username, String Passwort) {
		initComponents();
		//chat=new SupportChat(mUsername, mPasswort);
		mUsername=Username;
		mPasswort=Passwort;		
		chat=new SupportChat(mUsername, mPasswort);
		update=new ZentraleUpdateThrd(this, mUsername, mPasswort);
		update.start();		
		mProtokoll=new Protokoll(Username, Passwort);
//		mProtokoll.SetAvailable(true);
	}

	public void EnableCheckbox(boolean enable)
	{
		cbAuftrBearbeiten.setEnabled(enable);
	}
	
	public void SetAvailable(boolean enabled)
	{
		mProtokoll.SetAvailable(enabled);
	}

	
	public void SetRueckfrage(String[][] Rueckfrage)
	{
		Object[][] row=null;

	
		for(int i=0;i<=Rueckfrage.length-1;i++)
		{
			row=new Object[Rueckfrage.length][5];
			row[i][0]=Rueckfrage[i][0];
			row[i][1]=Rueckfrage[i][1];				
			row[i][2]=Rueckfrage[i][2];				
			row[i][3]=Rueckfrage[i][3].equals("J")?true:false;				
			row[i][4]=Rueckfrage[i][4].equals("J")?true:false;								
		}
		
		
		DefaultTableModel mdl=new DefaultTableModel(row,new String[] {"Datum", "Kunde", "Komentar", "R\u00fcckfrage", "Abgeschlossen"})
				{
							Class[] columnTypes = new Class[] {
							String.class, String.class, String.class, Boolean.class, Boolean.class
						};
						public Class getColumnClass(int columnIndex) 
						{
							return columnTypes[columnIndex];
						}
				};																
		
		tblRueckfrage.setModel(mdl);
		{
			TableColumnModel cm = tblRueckfrage.getColumnModel();
			cm.getColumn(0).setPreferredWidth(100);
			cm.getColumn(1).setPreferredWidth(80);
			cm.getColumn(2).setPreferredWidth(500);
			cm.getColumn(3).setPreferredWidth(70);
			cm.getColumn(4).setPreferredWidth(85);
		};
		
	}
	
	public void ShowChat(boolean Show)
	{
		if(Show)
		{			
			chat.show(Show);
		}
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Dennis Heine
		ResourceBundle bundle = ResourceBundle.getBundle("Support.SupportZentrale");
		bnOK = new JButton();
		label1 = new JLabel();
		lblWarteschlangen = new JLabel();
		cbAuftrBearbeiten = new JCheckBox();
		bnAdminPanel = new JButton();
		simpleInternalFrame1 = new SimpleInternalFrame();
		scrollPane1 = new JScrollPane();
		tblRueckfrage = new JTable();
		simpleInternalFrame2 = new SimpleInternalFrame();
		bnPWZuruecksetzen = new JButton();
		bnBenutzerSperren = new JButton();
		bnBenutzerFreischalten = new JButton();
		bnBenutzernamenAendern = new JButton();
		bnBugreporting = new JButton();
		bnAuftragManuellAnlegen = new JButton();
		bnBenutzerSurfenLassen = new JButton();
		bnServerlogAnzeigen = new JButton();
		benBenutzerdatenAnzeigen = new JButton();
		bnKontingentAuffuellen = new JButton();

		//======== this ========
		setTitle(bundle.getString("this.title"));
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//---- bnOK ----
		bnOK.setText(bundle.getString("bnOK.text"));
		contentPane.add(bnOK);
		bnOK.setBounds(785, 270, 95, bnOK.getPreferredSize().height);

		//---- label1 ----
		label1.setText(bundle.getString("label1.text"));
		contentPane.add(label1);
		label1.setBounds(new Rectangle(new Point(5, 5), label1.getPreferredSize()));

		//---- lblWarteschlangen ----
		lblWarteschlangen.setText(bundle.getString("lblWarteschlangen.text"));
		lblWarteschlangen.setForeground(Color.blue);
		contentPane.add(lblWarteschlangen);
		lblWarteschlangen.setBounds(85, 5, 95, lblWarteschlangen.getPreferredSize().height);

		//---- cbAuftrBearbeiten ----
		cbAuftrBearbeiten.setText(bundle.getString("cbAuftrBearbeiten.text"));
		cbAuftrBearbeiten.setForeground(Color.blue);
		contentPane.add(cbAuftrBearbeiten);
		cbAuftrBearbeiten.setBounds(new Rectangle(new Point(745, 0), cbAuftrBearbeiten.getPreferredSize()));
		cbAuftrBearbeiten.addItemListener(new ItemListener(){
	        	public void itemStateChanged(ItemEvent e) {
				SetAvailable(e.getStateChange()==ItemEvent.SELECTED);
	        	}
	        });
          

		//---- bnAdminPanel ----
		bnAdminPanel.setText(bundle.getString("bnAdminPanel.text"));
		contentPane.add(bnAdminPanel);
		bnAdminPanel.setBounds(785, 240, 95, bnAdminPanel.getPreferredSize().height);

		//======== simpleInternalFrame1 ========
		{
			simpleInternalFrame1.setTitle(bundle.getString("simpleInternalFrame1.title"));

			// JFormDesigner evaluation mark
			simpleInternalFrame1.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), simpleInternalFrame1.getBorder())); simpleInternalFrame1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			Container simpleInternalFrame1ContentPane = simpleInternalFrame1.getContentPane();
			simpleInternalFrame1ContentPane.setLayout(null);

			//======== scrollPane1 ========
			{

				//---- tblRueckfrage ----
				tblRueckfrage.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null, Boolean.TRUE, Boolean.TRUE},
						{null, null, null, null, null},
					},
					new String[] {
						"Datum", "Kunde", "Komentar", "R\u00fcckfrage", "Abgeschlossen"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, Boolean.class, Boolean.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
				{
					TableColumnModel cm = tblRueckfrage.getColumnModel();
					cm.getColumn(0).setPreferredWidth(100);
					cm.getColumn(1).setPreferredWidth(80);
					cm.getColumn(2).setPreferredWidth(500);
					cm.getColumn(3).setPreferredWidth(70);
					cm.getColumn(4).setPreferredWidth(85);
				}
				scrollPane1.setViewportView(tblRueckfrage);
			}
			simpleInternalFrame1ContentPane.add(scrollPane1);
			scrollPane1.setBounds(5, 5, 850, 135);
		}
		contentPane.add(simpleInternalFrame1);
		simpleInternalFrame1.setBounds(10, 25, 865, 170);

		//======== simpleInternalFrame2 ========
		{
			simpleInternalFrame2.setTitle(bundle.getString("simpleInternalFrame2.title"));
			Container simpleInternalFrame2ContentPane = simpleInternalFrame2.getContentPane();
			simpleInternalFrame2ContentPane.setLayout(null);

			//---- bnPWZuruecksetzen ----
			bnPWZuruecksetzen.setText(bundle.getString("bnPWZuruecksetzen.text"));
			simpleInternalFrame2ContentPane.add(bnPWZuruecksetzen);
			bnPWZuruecksetzen.setBounds(new Rectangle(new Point(5, 5), bnPWZuruecksetzen.getPreferredSize()));

			//---- bnBenutzerSperren ----
			bnBenutzerSperren.setText(bundle.getString("bnBenutzerSperren.text"));
			simpleInternalFrame2ContentPane.add(bnBenutzerSperren);
			bnBenutzerSperren.setBounds(160, 5, 150, bnBenutzerSperren.getPreferredSize().height);

			//---- bnBenutzerFreischalten ----
			bnBenutzerFreischalten.setText(bundle.getString("bnBenutzerFreischalten.text"));
			simpleInternalFrame2ContentPane.add(bnBenutzerFreischalten);
			bnBenutzerFreischalten.setBounds(new Rectangle(new Point(315, 5), bnBenutzerFreischalten.getPreferredSize()));

			//---- bnBenutzernamenAendern ----
			bnBenutzernamenAendern.setText(bundle.getString("bnBenutzernamenAendern.text"));
			simpleInternalFrame2ContentPane.add(bnBenutzernamenAendern);
			bnBenutzernamenAendern.setBounds(465, 5, 155, bnBenutzernamenAendern.getPreferredSize().height);

			//---- bnBugreporting ----
			bnBugreporting.setText(bundle.getString("bnBugreporting.text"));
			simpleInternalFrame2ContentPane.add(bnBugreporting);
			bnBugreporting.setBounds(625, 5, 135, bnBugreporting.getPreferredSize().height);

			//---- bnAuftragManuellAnlegen ----
			bnAuftragManuellAnlegen.setText(bundle.getString("bnAuftragManuellAnlegen.text"));
			simpleInternalFrame2ContentPane.add(bnAuftragManuellAnlegen);
			bnAuftragManuellAnlegen.setBounds(new Rectangle(new Point(5, 35), bnAuftragManuellAnlegen.getPreferredSize()));

			//---- bnBenutzerSurfenLassen ----
			bnBenutzerSurfenLassen.setText(bundle.getString("bnBenutzerSurfenLassen.text"));
			simpleInternalFrame2ContentPane.add(bnBenutzerSurfenLassen);
			bnBenutzerSurfenLassen.setBounds(160, 35, 150, bnBenutzerSurfenLassen.getPreferredSize().height);

			//---- bnServerlogAnzeigen ----
			bnServerlogAnzeigen.setText(bundle.getString("bnServerlogAnzeigen.text"));
			simpleInternalFrame2ContentPane.add(bnServerlogAnzeigen);
			bnServerlogAnzeigen.setBounds(315, 35, 140, bnServerlogAnzeigen.getPreferredSize().height);

			//---- benBenutzerdatenAnzeigen ----
			benBenutzerdatenAnzeigen.setText(bundle.getString("benBenutzerdatenAnzeigen.text"));
			simpleInternalFrame2ContentPane.add(benBenutzerdatenAnzeigen);
			benBenutzerdatenAnzeigen.setBounds(new Rectangle(new Point(465, 35), benBenutzerdatenAnzeigen.getPreferredSize()));

			//---- bnKontingentAuffuellen ----
			bnKontingentAuffuellen.setText(bundle.getString("bnKontingentAuffuellen.text"));
			simpleInternalFrame2ContentPane.add(bnKontingentAuffuellen);
			bnKontingentAuffuellen.setBounds(625, 35, 135, bnKontingentAuffuellen.getPreferredSize().height);
		}
		contentPane.add(simpleInternalFrame2);
		simpleInternalFrame2.setBounds(10, 200, 770, 95);

		{ // compute preferred size
			Dimension preferredSize = new Dimension();
			for(int i = 0; i < contentPane.getComponentCount(); i++) {
				Rectangle bounds = contentPane.getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
			}
			Insets insets = contentPane.getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			((JComponent)contentPane).setMinimumSize(preferredSize);
			((JComponent)contentPane).setPreferredSize(preferredSize);
		}
		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Dennis Heine
	private JButton bnOK;
	private JLabel label1;
	private JLabel lblWarteschlangen;
	private JCheckBox cbAuftrBearbeiten;
	private JButton bnAdminPanel;
	private SimpleInternalFrame simpleInternalFrame1;
	private JScrollPane scrollPane1;
	private JTable tblRueckfrage;
	private SimpleInternalFrame simpleInternalFrame2;
	private JButton bnPWZuruecksetzen;
	private JButton bnBenutzerSperren;
	private JButton bnBenutzerFreischalten;
	private JButton bnBenutzernamenAendern;
	private JButton bnBugreporting;
	private JButton bnAuftragManuellAnlegen;
	private JButton bnBenutzerSurfenLassen;
	private JButton bnServerlogAnzeigen;
	private JButton benBenutzerdatenAnzeigen;
	private JButton bnKontingentAuffuellen;
	private ZentraleUpdateThrd update;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}

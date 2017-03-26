package com.teckwave.Status;


import com.teckwave.Status.*;



import com.teckwave.Protokoll.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import com.jgoodies.uif_lite.panel.*;
import java.net.*;
import java.io.*;
/*
 * Created by JFormDesigner on Sun Jun 24 19:09:52 CEST 2007
 */



/**
 * @author Dennis Heine
 */
public class MainForm extends JFrame  implements WindowListener {


	private class MainThread extends java.lang.Thread
	{

		

		public void run()
		{
			
			do
			{
				try
				{
					GetStatus();
					MainThread.sleep(60000L);
	
				}
				catch(java.lang.Exception exception1) {
					//exception1.
				}
			}
			while(true);
				
				

		}

		private MainThread()
		{
			super();
		}

		MainThread(MainThread mainthread)
		{
			this();
		}
	}

	private void HandleError(String Error)
	{
		
		if(Error.compareTo("TO")==0)
		{
			mThread.stop();
			lblCode.setText("<keiner>");
			lblGesamtzeit.setText("<0 Minuten>");
			lblRestzeit.setText("<0 Minuten>");
			JOptionPane.showMessageDialog(this, "Die Zeit Ihrer Kontingente ist leider abgelaufen. Bitte erwerben Sie eine neue Timecard.");
		}
		else if(Error.compareTo("SO")==0)
		{
			mThread.stop();
			JOptionPane.showMessageDialog(this, "Verbindung zum Server unterbrochen. Bitte melden Sie sich neu an.");
			Login mLogin=new Login();
			mLogin.show();
			this.hide();
		}
		else if(Error.compareTo("AF")==0)
		{
			mThread.stop();
			JOptionPane.showMessageDialog(this, "Authentifikationsfehler.");
			Login mLogin=new Login();
			mLogin.show();
			this.hide();			
		}
		else if(Error.compareTo("NC")==0)
		{
			mThread.stop();
			JOptionPane.showMessageDialog(this, "Konnte keine Verbindung zum Server aufbauen.\nBitte melden Sie sich beim Support.");
			Login mLogin=new Login();
			mLogin.show();
			this.hide();						
		}
	}
    private void DoExit()
    {
        mThread.stop();
        mProtokoll.Logout();
        System.exit(0);
    }

    public void DoLogout()
    {
        mThread.stop();
        mProtokoll.Logout();
        Login login = new Login();
        login.show();
        hide();
    }

    private void OpenWebpage(String s)
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
            try
            {
                int i = 0;
                Process process = Runtime.getRuntime().exec("find / -type f -name 'mozilla'");
                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String s2;
                while((s2 = bufferedreader.readLine()) != null) 
                    i++;
                bufferedreader.close();
                if(i > 0)
                    Runtime.getRuntime().exec(s2+s);
                else
                    JOptionPane.showMessageDialog(this, "Webbrowser konnte nicht gefunden werden.\nBitte \366ffnen Sie die Seite\n"+s+"\nin Ihrem Webbrowser.");
            }
            catch(Exception exception1)
            {
                JOptionPane.showMessageDialog(this, "Webbrowser konnte nicht gefunden werden.\nBitte \366ffnen Sie die Seite\n"+s+"\nin Ihrem Webbrowser.");
            }
    }

	private void GetStatus()
	{
		Object Werte[]=new Object[4];
		
		javax.swing.DefaultListModel model=null;	
		String ret=new String("");
		ret=mProtokoll.getStatus(Werte);

		if(ret.length()>1)
		{
			HandleError(ret);
		}
		else
		{
			lbNachfolgendeKarten.setModel((javax.swing.DefaultListModel)Werte[3]);
			lblRestzeit.setText((String)Werte[0]);	       
			lblGesamtzeit.setText((String)Werte[1]);
			lblCode.setText((String)Werte[2]);				
		}
	}

	private void ShowTray(final MainForm wnd)
	{
		System.out.println("version:");
		System.out.println(System.getProperty("java.version"));
		if(Character.toString(System.getProperty("java.version").charAt(2)).equals("6"))
		{
			try{mTray=new Tray(wnd);}catch(Exception e){}
		}
		else
		{
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	}



	public MainForm(String Username, String Passwort) {
		mUsername=Username;
		mPasswort=Passwort;
		mProtokoll=new Protokoll(Username,Passwort);
		mThread=new MainThread();
		mThread.start();

		initComponents();
		lblBenutzername.setText(mUsername);	
		ShowTray(this);
		addWindowListener(this);
	
		//ft FgndThrd =new FgndThrd ();
		//ft.start();
	}
	
		public void windowIconified(WindowEvent e){}
			
public void windowOpened(WindowEvent e) {}
public void windowClosing(WindowEvent e) {			
				mTray.ShowMsg();
				}				
public void windowClosed(WindowEvent e) {}	

public void windowDeiconified(WindowEvent e){} 	
public void windowActivated(WindowEvent e){}
public void windowDeactivated(WindowEvent e){}			

  private void miVordergrundActionPerformed(ActionEvent actionevent)
    {
    
 	System.out.println("version:");
	System.out.println(System.getProperty("java.version"));
	if(Character.toString(System.getProperty("java.version").charAt(2)).equals("6"))
	{
		try{OnTop mOnTop=new OnTop(this, miVordergrund.getState());}catch(Exception e){}
	}
	else
	{
		JOptionPane.showMessageDialog(this, "Um dieses Feature zu nutzen, müssen Sie erst\nJava Version 6 installieren. Sie finden diese unter\nhttp://java.sun.com");
	}
        	
    }

    private void miMinimierenActionPerformed(ActionEvent actionevent)
    {
        hide();
    }

    private void miAbmeldenActionPerformed(ActionEvent actionevent)
    {
        DoLogout();
    }

    private void miBeendenActionPerformed(ActionEvent actionevent)
    {
        DoLogout();
        System.exit(0);
    }

    private void miAccountErstellenActionPerformed(ActionEvent actionevent)
    {
        Account account = new Account(this);
        account.show();
    }

    private void miTimecardRegistrierenActionPerformed(ActionEvent actionevent)
    {
        Karte_Registrieren karte_registrieren = new Karte_Registrieren(this, mUsername, mPasswort);
        karte_registrieren.show();
        GetStatus();        
    }

    private void miTimecardErwerbenActionPerformed(ActionEvent actionevent)
    {
        OpenWebpage("http://www.teckwave.de/hotspot/bestellung/ttbuy.php?user="+mUsername);
    }

    private void miPCRegistrierenActionPerformed(ActionEvent actionevent)
    {
    }

    private void miSupportActionPerformed(ActionEvent actionevent)
    {
        Support_Connect support_connect = new Support_Connect(this,mUsername, mPasswort);
        support_connect.show();
    }

    private void miHilfeActionPerformed(ActionEvent actionevent)
    {
        OpenWebpage("http://www.teckwave.de/hotspot/hilfe.html");
    }
    
    private void miKontaktActionPerformed(ActionEvent actionevent)
    {
    	Kontakt mKontakt =new Kontakt(this);
    	mKontakt.show();
    }

    private void miForumActionPerformed(ActionEvent actionevent)
    {
        OpenWebpage("http://forum.teckwave.de/");
    }


	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Dennis Heine
		ResourceBundle bundle = ResourceBundle.getBundle("lang.Deutsch.MainForm");
		ResourceBundle resourcebundle=bundle;
		simpleInternalFrame1 = new SimpleInternalFrame();
		label4 = new JLabel();
		label5 = new JLabel();
		label6 = new JLabel();
		lblCode = new JLabel();
		lblGesamtzeit = new JLabel();
		lblRestzeit = new JLabel();
		simpleInternalFrame2 = new SimpleInternalFrame();
		scrollPane1 = new JScrollPane();
		lbNachfolgendeKarten = new JList();
		lblBenutzername = new JLabel();
		label1 = new JLabel();
		menuBar1 = new JMenuBar();
		menu1 = new JMenu();
		miVordergrund = new JCheckBoxMenuItem();
		miMinimieren = new JMenuItem();
		miAbmelden = new JMenuItem();
		miBeenden = new JMenuItem();
		menu2 = new JMenu();
		miAccountErstellen = new JMenuItem();
		miTimecardRegistrieren = new JMenuItem();
		miTimecardErwerben = new JMenuItem();
		miPCRegistrieren = new JMenuItem();
		miHilfe = new JMenu();
		menuItem6 = new JMenuItem();
		miForum = new JMenuItem();
		miSupport = new JMenuItem();
		miKontakt = new JMenuItem();
		bnLogout = new JButton();

		//======== this ========
		setResizable(false);
		setTitle(bundle.getString("this.title"));
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		//======== simpleInternalFrame1 ========
		
			simpleInternalFrame1.setTitle(bundle.getString("simpleInternalFrame1.title"));

			// JFormDesigner evaluation mark
			simpleInternalFrame1.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), simpleInternalFrame1.getBorder())); simpleInternalFrame1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			Container simpleInternalFrame1ContentPane = simpleInternalFrame1.getContentPane();
			simpleInternalFrame1ContentPane.setLayout(null);

			//---- label4 ----
			label4.setText(bundle.getString("label4.text"));
			label4.setFont(new Font("MS Sans Serif", Font.BOLD, 11));
			simpleInternalFrame1ContentPane.add(label4);
			label4.setBounds(5, 5, 60, label4.getPreferredSize().height);

			//---- label5 ----
			label5.setText(bundle.getString("label5.text"));
			label5.setFont(new Font("MS Sans Serif", Font.BOLD, 11));
			simpleInternalFrame1ContentPane.add(label5);
			label5.setBounds(new Rectangle(new Point(5, 20), label5.getPreferredSize()));

			//---- label6 ----
			label6.setText(bundle.getString("label6.text"));
			label6.setFont(new Font("MS Sans Serif", Font.BOLD, 11));
			simpleInternalFrame1ContentPane.add(label6);
			label6.setBounds(new Rectangle(new Point(5, 35), label6.getPreferredSize()));

			//---- lblCode ----
			lblCode.setText(bundle.getString("lblCode.text"));
			simpleInternalFrame1ContentPane.add(lblCode);
			lblCode.setBounds(90, 5, 85, lblCode.getPreferredSize().height);

			//---- lblGesamtzeit ----
			lblGesamtzeit.setText(bundle.getString("lblGesamtzeit.text"));
			simpleInternalFrame1ContentPane.add(lblGesamtzeit);
			lblGesamtzeit.setBounds(90, 20, 90, lblGesamtzeit.getPreferredSize().height);

			//---- lblRestzeit ----
			lblRestzeit.setText(bundle.getString("lblRestzeit.text"));
			lblRestzeit.setForeground(Color.blue);
			simpleInternalFrame1ContentPane.add(lblRestzeit);
			lblRestzeit.setBounds(90, 35, 85, lblRestzeit.getPreferredSize().height);
		
		contentPane.add(simpleInternalFrame1);
		simpleInternalFrame1.setBounds(5, 75, 180, 90);

		//======== simpleInternalFrame2 ========
		
			simpleInternalFrame2.setTitle(bundle.getString("simpleInternalFrame2.title"));
			Container simpleInternalFrame2ContentPane = simpleInternalFrame2.getContentPane();
			simpleInternalFrame2ContentPane.setLayout(null);

			//======== scrollPane1 ========
			

				//---- lbNachfolgendeKarten ----
				lbNachfolgendeKarten.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				scrollPane1.setViewportView(lbNachfolgendeKarten);
			
			simpleInternalFrame2ContentPane.add(scrollPane1);
			scrollPane1.setBounds(5, 5, 165, 120);
		
		contentPane.add(simpleInternalFrame2);
		simpleInternalFrame2.setBounds(5, 170, 180, 155);

		//---- lblBenutzername ----
		lblBenutzername.setText(bundle.getString("lblBenutzername.text"));
		lblBenutzername.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblBenutzername);
		lblBenutzername.setBounds(0, 46, 190, 15);

		//---- label1 ----
		label1.setText(bundle.getString("label1.text"));
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label1);
		label1.setBounds(0, 31, 190, 15);

		//======== menuBar1 ========
		

			//======== menu1 ========
			/*
				menu1.setText(bundle.getString("menu1.text"));

				//---- miVordergrund ----
				miVordergrund.setText(bundle.getString("miVordergrund.text"));
				miVordergrund.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						miVordergrundActionPerformed(e);
					}
				});
				menu1.add(miVordergrund);

				//---- miMinimieren ----
				miMinimieren.setText(bundle.getString("miMinimieren.text"));
				miMinimieren.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						miMinimierenActionPerformed(e);
					}
				});
				menu1.add(miMinimieren);

				//---- miAbmelden ----
				miAbmelden.setText(bundle.getString("miAbmelden.text"));
				miAbmelden.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						miAbmeldenActionPerformed(e);
					}
				});
				menu1.add(miAbmelden);

				//---- miBeenden ----
				miBeenden.setText(bundle.getString("miBeenden.text"));
				miBeenden.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						miBeendenActionPerformed(e);
					}
				});
				menu1.add(miBeenden);
			}
			menuBar1.add(menu1);

			//======== menu2 ========
			{
				menu2.setText(bundle.getString("menu2.text"));

				//---- miAccountErstellen ----
				miAccountErstellen.setText(bundle.getString("miAccountErstellen.text"));
				miAccountErstellen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						miAccountErstellenActionPerformed(e);
					}
				});
				menu2.add(miAccountErstellen);

				//---- miTimecardRegistrieren ----
				miTimecardRegistrieren.setText(bundle.getString("miTimecardRegistrieren.text"));
				miTimecardRegistrieren.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						miTimecardRegistrierenActionPerformed(e);
					}
				});
				menu2.add(miTimecardRegistrieren);

				//---- miTimecardErwerben ----
				miTimecardErwerben.setText(bundle.getString("miTimecardErwerben.text"));
				miTimecardErwerben.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						miTimecardErwerbenActionPerformed(e);
					}
				});
				menu2.add(miTimecardErwerben);

				//---- miPCRegistrieren ----
				miPCRegistrieren.setText(bundle.getString("miPCRegistrieren.text"));
				miPCRegistrieren.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						miPCRegistrierenActionPerformed(e);
					}
				});
				menu2.add(miPCRegistrieren);
			}
			menuBar1.add(menu2);

			//======== miHilfe ========
			{
				miHilfe.setText(bundle.getString("miHilfe.text"));

				//---- menuItem6 ----
				menuItem6.setText(bundle.getString("menuItem6.text"));
				miHilfe.add(menuItem6);

				//---- miForum ----
				miForum.setText(bundle.getString("miForum.text"));
				miHilfe.add(miForum);

				//---- miSupport ----
				miSupport.setText(bundle.getString("miSupport.text"));
				miHilfe.add(miSupport);

				//---- miKontakt ----
				miKontakt.setText(bundle.getString("miKontakt.text"));
				miHilfe.add(miKontakt);
			}
			menuBar1.add(miHilfe);*/
			menu1.setText(resourcebundle.getString("menu1.text"));
		     menu1.add(miVordergrund);
         miVordergrund.setText(resourcebundle.getString("miVordergrund.text"));
        miVordergrund.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                miVordergrundActionPerformed(actionevent);
            }


        });
        menu1.add(miVordergrund);
        miMinimieren.setText(resourcebundle.getString("miMinimieren.text"));
        miMinimieren.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                miMinimierenActionPerformed(actionevent);
            }

        });
        menu1.add(miMinimieren);
        miAbmelden.setText(resourcebundle.getString("miAbmelden.text"));
        miAbmelden.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                miAbmeldenActionPerformed(actionevent);
            }


        });
        menu1.add(miAbmelden);
        miBeenden.setText(resourcebundle.getString("miBeenden.text"));
        miBeenden.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                miBeendenActionPerformed(actionevent);
            }


        });
        menu1.add(miBeenden);
        menuBar1.add(menu1);
        menu2.setText(resourcebundle.getString("menu2.text"));
        miAccountErstellen.setText(resourcebundle.getString("miAccountErstellen.text"));
        miAccountErstellen.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                miAccountErstellenActionPerformed(actionevent);
            }


        });
        menu2.add(miAccountErstellen);
        miTimecardRegistrieren.setText(resourcebundle.getString("miTimecardRegistrieren.text"));
        miTimecardRegistrieren.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                miTimecardRegistrierenActionPerformed(actionevent);
            }


        });
        menu2.add(miTimecardRegistrieren);
        miTimecardErwerben.setText(resourcebundle.getString("miTimecardErwerben.text"));
        miTimecardErwerben.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                miTimecardErwerbenActionPerformed(actionevent);
            }


        });
        menu2.add(miTimecardErwerben);
        miPCRegistrieren.setText(resourcebundle.getString("miPCRegistrieren.text"));
        miPCRegistrieren.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                miPCRegistrierenActionPerformed(actionevent);
            }


        });
        menu2.add(miPCRegistrieren);
        menuBar1.add(menu2);
        miHilfe.setText(resourcebundle.getString("miHilfe.text"));
        menuItem6.setText(resourcebundle.getString("menuItem6.text"));
        menuItem6.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                miHilfeActionPerformed(actionevent);
            }


        });
        miHilfe.add(menuItem6);
        miForum.setText(resourcebundle.getString("miForum.text"));
        miForum.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                miForumActionPerformed(actionevent);
            }


        });
        miHilfe.add(miForum);
        miSupport.setText(resourcebundle.getString("miSupport.text"));
        miSupport.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                miSupportActionPerformed(actionevent);
            }


        });
        miHilfe.add(miSupport);
        miKontakt.setText(resourcebundle.getString("miKontakt.text"));
	miKontakt.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                miKontaktActionPerformed(actionevent);
            }      
           });  
        miHilfe.add(miKontakt);
        
        menuBar1.add(miHilfe);			
		
		contentPane.add(menuBar1);
		menuBar1.setBounds(0, 0, 187, 21);

		//---- bnLogout ----
		bnLogout.setText(bundle.getString("bnLogout.text"));
		contentPane.add(bnLogout);
		bnLogout.setBounds(10, 330, 170, bnLogout.getPreferredSize().height);
		bnLogout.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent e)
							{
								DoLogout();
							}
						});

		contentPane.setSize(new Dimension(195, 390));
		setSize(195, 390);
		setLocationRelativeTo(getOwner());
		
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	
	}
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Dennis Heine
	private SimpleInternalFrame simpleInternalFrame1;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel lblCode;
	private JLabel lblGesamtzeit;
	private JLabel lblRestzeit;
	private SimpleInternalFrame simpleInternalFrame2;
	private JScrollPane scrollPane1;
	private JList lbNachfolgendeKarten;
	private JLabel lblBenutzername;
	private JLabel label1;
	private JMenuBar menuBar1;
	private JMenu menu1;
	private JCheckBoxMenuItem miVordergrund;
	private JMenuItem miMinimieren;
	private JMenuItem miAbmelden;
	private JMenuItem miBeenden;
	private JMenu menu2;
	private JMenuItem miAccountErstellen;
	private JMenuItem miTimecardRegistrieren;
	private JMenuItem miTimecardErwerben;
	private JMenuItem miPCRegistrieren;
	private JMenu miHilfe;
	private JMenuItem menuItem6;
	private JMenuItem miForum;
	private JMenuItem miSupport;
	private JMenuItem miKontakt;
	private JButton bnLogout;
	private MainThread mThread;
	private String mUsername;
	private String mPasswort;
	private Protokoll mProtokoll;
	Tray mTray;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}

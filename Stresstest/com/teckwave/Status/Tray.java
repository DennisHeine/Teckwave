package com.teckwave.Status;
import com.teckwave.Status.*;
import java.awt.*;
import java.awt.event.*;

public class Tray
{

	TrayIcon ico;
	public void ShowMsg()
	{
		ico.displayMessage("Achtung","Das Statusfenster wurde geschlossen. Sie sind jedoch immernoch angemeldet, solange dieses Icon nangezeigt wird. Um sich abzumelden, klicken Sie mit der Rechten Maustaste auf das Icon und wählen Sie \"Abmelden\" oder \"Beenden\"",TrayIcon.MessageType.INFO);
	}

	Tray(final MainForm wnd)
	{
		java.awt.Image image = java.awt.Toolkit.getDefaultToolkit().getImage("comp-net11.gif");
		java.awt.PopupMenu popup = new PopupMenu();
		java.awt.MenuItem item = new MenuItem("Beenden");
		item.addActionListener(new java.awt.event.ActionListener() {
			

			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				//query.DoQuery((new StringBuilder("INSERT INTO Logout(Username) VALUES('")).append(username).append("')").toString());
				java.lang.System.exit(0);
			}

			
			
		});
		java.awt.MenuItem item1 = new MenuItem("Logout");
		item1.addActionListener(new java.awt.event.ActionListener() {
			

			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				//query.DoQuery((new StringBuilder("INSERT INTO Logout(Username) VALUES('")).append(username).append("')").toString());
				wnd.DoLogout();				
			}

			
			
		});

		popup.add(item1);
		popup.add(item);

		java.awt.TrayIcon trayIcon = new TrayIcon(image, "Teckwave Login ", popup);
		ico=trayIcon;
		trayIcon.setImageAutoSize(true);
		trayIcon.addMouseListener(new java.awt.event.MouseListener() {

			

			public void mouseClicked(java.awt.event.MouseEvent e)
			{
				wnd.show();
			}            
			public void mouseEntered(MouseEvent mouseevent)
            {
            }

            public void mouseExited(MouseEvent mouseevent)
            {
            }

            public void mousePressed(MouseEvent mouseevent)
            {
            }

            public void mouseReleased(MouseEvent mouseevent)
            {
            }


		});
		java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();
		try
		{
			tray.add(trayIcon);
		}
		catch(java.lang.Exception exception) { }

	}
}
// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 28.06.2007 13:52:24
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Support_Connect.java

package com.teckwave.Status;
import com.teckwave.Protokoll.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.border.*;

public class Support_Connect extends JDialog
{

	String Username, Passwort;
	UpdateThread Thrd;
	CSupport mCSupport;
	Support_Chat sc;	
    public Support_Connect(Frame frame,String mUsername, String mPasswort)
    {
        super(frame);
        Username=mUsername;
        Passwort=mPasswort;
        initComponents();
    }

    public Support_Connect(Dialog dialog,String mUsername, String mPasswort)
    {
        super(dialog);
        Username=mUsername;
        Passwort=mPasswort;
        initComponents();
    }
    
    private class UpdateThread extends Thread
    {
    	Support_Connect sco;
    	UpdateThread(Support_Connect msc)
    	{
	    	sco=msc;
	}
	
    	String[] WS;
    	public void run()
    	{
    		do
    		{
    		
    			
    		
    			WS=mCSupport.GetWarteschlange();
    			
    			if(WS[0].equals("0"))
    			{
	    			sc=new Support_Chat(Username, Passwort);
	    			sc.show();
	    			sco.hide();
    				this.stop();
    			}
    			else
    			{
    				lblPosition.setText(WS[0]+"/"+WS[1]);
    			}
    			
    			
    			try{this.sleep(5000);}catch(Exception e){}
    		}
    		while(true);
    	}
    }

    private void bnBeendenActionPerformed(ActionEvent actionevent)
    {
    }

    private void initComponents()
    {
        ResourceBundle resourcebundle = ResourceBundle.getBundle("lang.Deutsch.SupportConnect");
        dialogPane = new JPanel();
        label1 = new JLabel();
        lblPosition = new JLabel();
        bnBeenden = new JButton();
        setTitle(resourcebundle.getString("this.title"));
        setResizable(false);
        setModal(true);
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
        dialogPane.setBorder(new CompoundBorder(new TitledBorder(new EmptyBorder(0, 0, 0, 0), "", 2, 5, new Font("Dialog", 1, 12), Color.red), dialogPane.getBorder()));
        dialogPane.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent propertychangeevent)
            {
                if("border".equals(propertychangeevent.getPropertyName()))
                    throw new RuntimeException();
                else
                    return;
            }

       
        });
        dialogPane.setLayout(null);
        label1.setText(resourcebundle.getString("label1.text"));
        label1.setHorizontalAlignment(0);
        dialogPane.add(label1);
        label1.setBounds(0, 180, 275, label1.getPreferredSize().height);
        lblPosition.setText(resourcebundle.getString("lblPosition.text"));
        lblPosition.setHorizontalAlignment(0);
        dialogPane.add(lblPosition);
        lblPosition.setBounds(0, 195, 275, lblPosition.getPreferredSize().height);
        bnBeenden.setText(resourcebundle.getString("bnBeenden.text"));
        bnBeenden.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                bnBeendenActionPerformed(actionevent);
            }

         
        });
        dialogPane.add(bnBeenden);
        bnBeenden.setBounds(new Rectangle(new Point(100, 220), bnBeenden.getPreferredSize()));
        Dimension dimension = new Dimension();
        for(int i = 0; i < dialogPane.getComponentCount(); i++)
        {
            Rectangle rectangle = dialogPane.getComponent(i).getBounds();
            dimension.width = Math.max(rectangle.x + rectangle.width, dimension.width);
            dimension.height = Math.max(rectangle.y + rectangle.height, dimension.height);
        }

        Insets insets = dialogPane.getInsets();
        dimension.width += insets.right;
        dimension.height += insets.bottom;
        dialogPane.setMinimumSize(dimension);
        dialogPane.setPreferredSize(dimension);
        container.add(dialogPane, "Center");
        pack();
        setLocationRelativeTo(null);
        
        mCSupport=new CSupport(Username, Passwort);
        
        if(!mCSupport.QuerySession())
        {
        	lblPosition.setText("Der Live-Support steht momentan leider nicht zur Verfügung");
        }
        else
        {
        	Thrd=new UpdateThread(this);
        	Thrd.start();
        }
    }

    private JPanel dialogPane;
    private JLabel label1;
    private JLabel lblPosition;
    private JButton bnBeenden;

}
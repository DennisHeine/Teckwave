// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 28.06.2007 13:52:22
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Karte_Registrieren.java

package com.teckwave.Status;

import com.teckwave.Protokoll.Protokoll;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.ResourceBundle;
import javax.swing.*;

public class Karte_Registrieren extends JDialog
{

    public void HandleResult(String s)
    {
        if(s.equals("AF"))
        {
            JOptionPane.showMessageDialog(this, "Authentifikationsfehler. Bitte melden Sie sich neu an.");
            hide();
        } else
        if(s.equals("KB"))
            JOptionPane.showMessageDialog(this, "Fehler: Karte bereits benutzt.");
        else
        if(s.equals("KU"))
            JOptionPane.showMessageDialog(this, "Karte Unbekannt.");
        else
        if(s.equals("OK"))
        {
            JOptionPane.showMessageDialog(this, "Die Karte wurde erfolgreich hinzugef\374gt.");
            hide();
        } else
        {
            JOptionPane.showMessageDialog(this, "Unbekannter Fehler. Bitte melden Sie sich beim Support");
        }
    }

    public Karte_Registrieren(Frame frame, String s, String s1)
    {
        super(frame);
        mUsername = s;
        mPasswort = s1;
        initComponents();
    }

    public Karte_Registrieren(Dialog dialog, String s, String s1)
    {
        super(dialog);
        mUsername = s;
        mPasswort = s1;
        initComponents();
    }
    public void tbPasswortKeyReleased(KeyEvent keyevent)
    {
        if(keyevent.getKeyCode() == 10)
            Registrieren();
    }
    


    private void button1ActionPerformed(ActionEvent actionevent)
    {
        Registrieren();
    }

    private void Registrieren()
    {
        if(tbCode.getText().equals("") || tbKennwort.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Bitte f\374llen Sie alle Felder aus.");
        } else
        {
            Protokoll protokoll = new Protokoll(mUsername, mPasswort);
            String s = protokoll.Registrieren(tbCode.getText(), tbKennwort.getText());
            HandleResult(s);
        }
    }

    private void button2ActionPerformed(ActionEvent actionevent)
    {
        hide();
    }

    private void initComponents()
    {
        ResourceBundle resourcebundle = ResourceBundle.getBundle("lang.Deutsch.KarteRegistrieren");
        label1 = new JLabel();
        label2 = new JLabel();
        tbCode = new JTextField();
        tbKennwort = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        setTitle(resourcebundle.getString("this.title"));
        setResizable(false);
        setModal(true);
        Container container = getContentPane();
        container.setLayout(null);
        label1.setText(resourcebundle.getString("label1.text"));
        container.add(label1);
        label1.setBounds(new Rectangle(new Point(10, 10), label1.getPreferredSize()));
        label2.setText(resourcebundle.getString("label2.text"));
        container.add(label2);
        label2.setBounds(new Rectangle(new Point(10, 35), label2.getPreferredSize()));
        container.add(tbCode);
        tbCode.setBounds(135, 10, 95, tbCode.getPreferredSize().height);
        container.add(tbKennwort);
        tbKennwort.setBounds(135, 35, 95, tbKennwort.getPreferredSize().height);
        
        tbKennwort.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent keyevent)
            {
                tbPasswortKeyReleased(keyevent);
            }
       });
        
        button1.setText(resourcebundle.getString("button1.text"));
        button1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                button1ActionPerformed(actionevent);
            }


        });
        container.add(button1);
        button1.setBounds(75, 65, 54, button1.getPreferredSize().height);
        button2.setText(resourcebundle.getString("button2.text"));
        button2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                button2ActionPerformed(actionevent);
            }

        });
        container.add(button2);
        button2.setBounds(new Rectangle(new Point(135, 65), button2.getPreferredSize()));
        Dimension dimension = new Dimension();
        for(int i = 0; i < container.getComponentCount(); i++)
        {
            Rectangle rectangle = container.getComponent(i).getBounds();
            dimension.width = Math.max(rectangle.x + rectangle.width, dimension.width);
            dimension.height = Math.max(rectangle.y + rectangle.height, dimension.height);
        }

        Insets insets = container.getInsets();
        dimension.width += insets.right;
        dimension.height += insets.bottom;
        ((JComponent)container).setMinimumSize(dimension);
        ((JComponent)container).setPreferredSize(dimension);
        setSize(245, 130);
        setLocationRelativeTo(getOwner());
    }

    private JLabel label1;
    private JLabel label2;
    private JTextField tbCode;
    private JTextField tbKennwort;
    private JButton button1;
    private JButton button2;
    private String mUsername;
    private String mPasswort;


}
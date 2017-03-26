// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 28.06.2007 13:52:22
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Login.java

package com.teckwave.Status;

import com.jgoodies.uif_lite.panel.SimpleInternalFrame;
import com.teckwave.Protokoll.Protokoll;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.border.*;

// Referenced classes of package com.teckwave.Status:
//            MainForm

public class Login extends JDialog
{

    private void HandleError(String s)
    {
        if(s.compareTo("TO") != 0)
            if(s.compareTo("SO") == 0)
                JOptionPane.showMessageDialog(this, "Verbindung zum Server unterbrochen. Bitte melden Sie sich neu an.");
            else
            if(s.compareTo("AF") == 0)
                JOptionPane.showMessageDialog(this, "Authentifikationsfehler.");
            else
            if(s.compareTo("NC") == 0)
                JOptionPane.showMessageDialog(this, "Konnte keine Verbindung zum Server aufbauen.\nBitte melden Sie sich beim Support.");							if(s.compareTo("SE") == 0)                JOptionPane.showMessageDialog(this, "Unbekannter Serverfehler.\nBitte melden Sie sich beim Support.");								if(s.compareTo("AC") == 0)                JOptionPane.showMessageDialog(this, "Sie sind bereits eingelogt.\nBitte loggen Sie sich aus oder warten Sie einige Minuten.");					
    }

    public Login()
    {
        initComponents();
    }

    public Login(Dialog dialog)
    {
        super(dialog);
        initComponents();
    }

    private void tbBenutzernameKeyReleased(KeyEvent keyevent)
    {
    }

    private void tbPasswortKeyReleased(KeyEvent keyevent)
    {
        KeyEvent _tmp = keyevent;
        if(keyevent.getKeyCode() == 10)
            DoLogin();
    }

    private void bnAbbrechenActionPerformed(ActionEvent actionevent)
    {
        System.exit(0);
    }

    private void bnOKActionPerformed(ActionEvent actionevent)
    {
        DoLogin();
    }

    void DoLogin()
    {
        String s = tbBenutzername.getText();
        String s1 = tbPasswort.getText();
        if(s.length() < 3 || s1.length() < 3)
        {
            JOptionPane.showMessageDialog(this, "Bitte geben Sie die Benutzerdaten ein.");
        } else
        {
            Protokoll protokoll = new Protokoll(s, s1);
            String s2 = protokoll.Anmelden();
            if(s2 != null)
            {
                HandleError(s2);
                if(s2.equals("AO") || s2.equals("TO"))
                {
                    MainForm mainform = new MainForm(s, s1);
                    mainform.show();
                    hide();
                }
            }
        }
    }

    private void initComponents()
    {
        ResourceBundle resourcebundle = ResourceBundle.getBundle("lang.Deutsch.Login");
        dialogPane = new JPanel();
        simpleInternalFrame1 = new SimpleInternalFrame();
        label1 = new JLabel();
        label2 = new JLabel();
        tbBenutzername = new JTextField();
        tbPasswort = new JPasswordField();
        bnAbbrechen = new JButton();
        bnOK = new JButton();
        setTitle(resourcebundle.getString("this.title"));
        setResizable(false);
        Container container = getContentPane();
        container.setLayout(null);
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
        simpleInternalFrame1.setTitle(resourcebundle.getString("simpleInternalFrame1.title"));
        Object obj = simpleInternalFrame1.getContentPane();
        ((Container) (obj)).setLayout(null);
        label1.setText(resourcebundle.getString("label1.text"));
        ((Container) (obj)).add(label1);
        label1.setBounds(new Rectangle(new Point(5, 5), label1.getPreferredSize()));
        label2.setText(resourcebundle.getString("label2.text"));
        ((Container) (obj)).add(label2);
        label2.setBounds(new Rectangle(new Point(5, 30), label2.getPreferredSize()));
        tbBenutzername.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent keyevent)
            {
                tbBenutzernameKeyReleased(keyevent);
            }


        });
        ((Container) (obj)).add(tbBenutzername);
        tbBenutzername.setBounds(90, 5, 190, tbBenutzername.getPreferredSize().height);
        tbPasswort.setFont(new Font("Times New Roman", 0, 12));
        tbPasswort.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent keyevent)
            {
                tbPasswortKeyReleased(keyevent);
            }


        });
        ((Container) (obj)).add(tbPasswort);
        tbPasswort.setBounds(90, 30, 190, tbPasswort.getPreferredSize().height);
        dialogPane.add(simpleInternalFrame1);
        simpleInternalFrame1.setBounds(0, 0, 285, 90);
        bnAbbrechen.setText(resourcebundle.getString("bnAbbrechen.text"));
        bnAbbrechen.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                bnAbbrechenActionPerformed(actionevent);
            }

        });
        dialogPane.add(bnAbbrechen);
        bnAbbrechen.setBounds(180, 95, 100, bnAbbrechen.getPreferredSize().height);
        bnOK.setText(resourcebundle.getString("bnOK.text"));
        bnOK.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionevent)
            {
                bnOKActionPerformed(actionevent);
            }


        });
        dialogPane.add(bnOK);
        bnOK.setBounds(95, 95, 80, bnOK.getPreferredSize().height);
        Dimension obj1;
        obj1 = new Dimension();
        for(int i = 0; i < dialogPane.getComponentCount(); i++)
        {
            Rectangle rectangle = dialogPane.getComponent(i).getBounds();
            obj1.width = Math.max(rectangle.x + rectangle.width, ((JPanel) (obj)).getWidth());
            obj1.height = Math.max(rectangle.y + rectangle.height, ((JPanel) (obj)).getHeight());
        }

        Insets insets = dialogPane.getInsets();
        obj1.width += insets.right;
        obj1.height += insets.bottom;
        dialogPane.setMinimumSize(((Dimension) (obj1)));
        dialogPane.setPreferredSize(((Dimension) (obj1)));
        container.add(dialogPane);
        dialogPane.setBounds(5, 5, 295, 130);
        obj1 = new Dimension();
        for(int j = 0; j < container.getComponentCount(); j++)
        {
            Rectangle rectangle1 = container.getComponent(j).getBounds();
            obj1.width = Math.max(rectangle1.x + rectangle1.width, ((Dimension) (obj1)).width);
            obj1.height = Math.max(rectangle1.y + rectangle1.height, ((Dimension) (obj1)).height);
        }

        Insets j = container.getInsets();
        obj1.width += ((Insets) (j)).right;
        obj1.height += ((Insets) (j)).bottom;
        ((JComponent)container).setMinimumSize(((Dimension) (obj1)));
        ((JComponent)container).setPreferredSize(((Dimension) (obj1)));
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel dialogPane;
    private SimpleInternalFrame simpleInternalFrame1;
    private JLabel label1;
    private JLabel label2;
    private JTextField tbBenutzername;
    private JPasswordField tbPasswort;
    private JButton bnAbbrechen;
    private JButton bnOK;




}
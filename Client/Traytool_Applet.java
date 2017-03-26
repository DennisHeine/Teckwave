// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: fullnames fieldsfirst 
// Source File Name:   Traytool_Applet.java

import com.teckwave.Status.Login;
import java.applet.Applet;
import javax.swing.JFrame;

public class Traytool_Applet extends java.applet.Applet
{

    public Traytool_Applet()
    {
    }

    public static void main(java.lang.String args[])
    {
        javax.swing.JFrame jframe = new JFrame("Teckwave");
        com.teckwave.Status.Login login = new Login();
    }

    public void init()
    {
        com.teckwave.Status.Login login = new Login();	login.show();
    }
}

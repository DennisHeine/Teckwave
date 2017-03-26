
import java.applet.Applet;
import javax.swing.JFrame;import java.net.*;

public class Encrypt extends java.applet.Applet
{

    public Encrypt()
    {
    }

    public static void main(java.lang.String args[])
    {
    }

    public void init()
    {		String key=getParameter("key");		String pass=getParameter("pass");		String url=getParameter("url");		String crypted=null;				try		{			Blowfish crypt=new Blowfish(key);			crypted=crypt.encryptString(pass);					url=url+crypted;					getAppletContext().showDocument(new URL(url)); 		}		catch(Exception e){System.out.println("Chatuser konnte nicht erzeugt werden. Bitte melden Sie sich beim Support.");}
    }
}

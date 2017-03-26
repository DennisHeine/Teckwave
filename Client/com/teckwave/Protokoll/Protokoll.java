// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 28.06.2007 13:51:36
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Protokoll.java

package com.teckwave.Protokoll;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.*;
import javax.swing.DefaultListModel;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.*;
import java.util.*;
import java.security.SecureRandom;

// Referenced classes of package com.teckwave.Protokoll:
//            Util



public class Protokoll
{
    String mUsername, mPasswort;
    
    public Protokoll(String s, String s1)
    {
        mUsername = s;
        mPasswort = s1;
    }
    
    
    public SSLSocket GenSocket()
    {
	SSLSocket socket=null;
    	try
    	{
	    	
    	
        	TrustManager[] trustAllCerts = new TrustManager[] {
		new X509TrustManager() {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		return null;
		}
		public void checkClientTrusted(
		java.security.cert.X509Certificate[] certs, String authType) {
		}
		public void checkServerTrusted(
		java.security.cert.X509Certificate[] certs, String authType) {
		}
		}
		};
	      	    //SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
      		    SSLContext sslContext = SSLContext.getInstance("SSL");
	      	    sslContext.init(null, trustAllCerts, new SecureRandom());
      		    SSLSocketFactory sslsocketfactory = sslContext.getSocketFactory();

		    socket = (SSLSocket) sslsocketfactory.createSocket("twserver.org", 82);


//SSLSocketFactory sslFact = (SSLSocketFactory) SSLSocketFactory.getDefault();
//     socket = (SSLSocket) sslFact.createSocket( "twserver.org", 82 );


	}catch(Exception e){e.printStackTrace();}
	    return socket;
    }

    public String Logout()
    {
        Util util = new Util();
        SSLSocket socket = null;
        PrintWriter printwriter = null;
        Object obj = null;
		String s2=""; 
		
        try
        {
        

            socket = GenSocket();
                    
            //socket = new Socket("twserver.org", 82);
            printwriter = new PrintWriter(socket.getOutputStream(), true);
            String s = new String("DC;");
            String s1 = new String(mPasswort);

            s = s+mUsername+";"+mPasswort+";";
            printwriter.println(s);
            BufferedReader bufferedreader;
            try
            {
                bufferedreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            		
            s2 = bufferedreader.readLine();
		
				printwriter.close();
				bufferedreader.close();
				socket.close();
			}
        catch(Exception exception4) {System.out.println(exception4.getMessage()); }
		}catch(Exception e){System.out.println(e.getMessage());}
        
        
        return s2;			
			
        
    }




    public String Anmelden()
    {
        Object obj = null;
        String s1 = "";
        Object obj1 = null;
        SSLSocket socket = null;
        PrintWriter printwriter = null;
        BufferedReader bufferedreader = null;
        try
        {
	    socket = GenSocket();
            //socket = new Socket("twserver.org", 82);
            printwriter = new PrintWriter(socket.getOutputStream(), true);
            bufferedreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch(UnknownHostException unknownhostexception)
        {	
	    unknownhostexception.printStackTrace();
            s1 = "NC";
        }
        catch(IOException ioexception)
        {
		ioexception.printStackTrace();
            s1 = "NC";
        }
        catch(Exception exception)
        {
		exception.printStackTrace();
            s1 = "NC";
        }
        try
        {
           
	    
        }
        catch(Exception exception1) { }
        String s3 = null;
        System.out.println("HE Senden");
            
            String s4 = "1";
            String s5 = "1.1.1.1";
            String s = "1.1.1.2";
            String ret="";
 	    String hostname="";
            
            int test=0;
            
            try
            {
            	test++;
				s=socket.getLocalAddress().getHostAddress();
                
                Util util=new Util();
                Util _tmp3 = util;
                String s2 = Util.DownloadURL("http://www.teckwave.de/hotspot/ip.php");
                s5 = "";
                for(int i = 1; i <= s2.length() - 1; i++)
                    s5 = String.valueOf(s5)+Character.toString(s2.charAt(i));

                Util _tmp4 = util;
                String s6 = Util.DownloadURL("http://192.168.100.1/provider.html");
                s4 = "";
                for(int j = 1; j <= s6.length() - 1; j++)
                    s4 = String.valueOf(s4)+Character.toString(s6.charAt(j));

            }
            catch(Exception exception5) {System.out.println(exception5.toString());}
            if(s4.compareTo("") == 0 || s4 == null)
                s4 = new String("1");
            if(s5.compareTo("") == 0 || s5 == null)
                s5 = new String("1");
            if((s.compareTo("") == 0) | (s == null))
                s = new String("1");
            String s7 = new String("CO");
            String s8 = s7+";"+s+";"+s5+";"+s4+";";



        try
        {
printwriter.println("HE;"+mUsername+";"+mPasswort+";"+s+";"+s5+";"+s4+";");            
        }catch(Exception e){System.out.println("Fehler beim Einlesen der Userdaten");}

            
            System.out.println("CO;IP;... ausgeben");           
            System.out.println("Auth einlesen");

            String s10="";
            try
            {
		System.out.println("BEGIN READ");
                s10 = bufferedreader.readLine();
		System.out.println("END READ");
            }
            catch(Exception exception11)
            {
                System.out.println(exception11.getMessage());
            }
            if(s10.compareTo("AF") == 0)
                s1 = "AF";
            else
            if(s10.compareTo("TO") == 0)
                s1 = "TO";
            else
            if(s10.compareTo("AO") == 0)
                s1 = "AO";
			else
			if(s10.compareTo("SE") == 0)
                s1 = "SE";
			if(s10.compareTo("AC") == 0)
                s1 = "AC";	
				
				       
        try
        {
            printwriter.close();
            bufferedreader.close();
            socket.close();
        }
        catch(Exception exception4) { }
        System.out.println(s1);
        return s1;
    }

    public String getStatus(Object aobj[])
    {
        SSLSocket socket = null;
        PrintWriter printwriter = null;
        BufferedReader bufferedreader = null;
        Util util = new Util();
        String s = new String("");
        try
        {
       	    
            socket = GenSocket();
            //socket = new Socket("twserver.org", 82);
            printwriter = new PrintWriter(socket.getOutputStream(), true);
            bufferedreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch(UnknownHostException unknownhostexception)
        {
            s = "NC";
        }
        catch(IOException ioexception)
        {
            s = "NC";
        }
        if(s != "NC")
        {
            printwriter.println("ST;"+mUsername+";"+mPasswort+";");
            try
            {
                System.out.println("Warte auf Status...");
                String s1 = bufferedreader.readLine();
                s = StatusAuswerten(s1, aobj);
            }
            catch(Exception exception3) { exception3.printStackTrace();}
        }
        try
        {
            printwriter.close();
            bufferedreader.close();
            socket.close();
        }
        catch(Exception exception) { }
        return s;
    }

    private String StatusAuswerten(String s, Object aobj[])
    {
        String s1 = new String("");
        if(s.compareTo("TO") == 0)
            s1 = "TO";
        else
        if(s.compareTo("SO") == 0)
            s1 = "SO";
        else
        if(s.compareTo("AF") == 0)
        {
            s1 = "AF";
        } else
        {
            int i = 3;
            String s2 = new String("");
            String s3 = new String("");
            String s4 = new String("");
            String s5 = new String("");
            String s6 = new String("");
            String s8 = new String("");
            for(; s.charAt(i) != ';'; i++)
                s2 = s2+Character.toString(s.charAt(i));

            for(i++; s.charAt(i) != ';'; i++)
                s3 = s3+Character.toString(s.charAt(i));

            for(i++; s.charAt(i) != ';'; i++)
                s4 = s4+Character.toString(s.charAt(i));

            for(i++; s.charAt(i) != ';'; i++)
                s5 = s5+Character.toString(s.charAt(i));

            i++;
            DefaultListModel defaultlistmodel = new DefaultListModel();
            for(int j = 1; j <= Integer.parseInt(s5); j++)
            {
                String s7 = new String("");
                String s9 = new String("");
                for(; s.charAt(i) != ';'; i++)
                    s7 = s7+Character.toString(s.charAt(i));

                for(i++; s.charAt(i) != ';'; i++)
                    s9 = s9+Character.toString(s.charAt(i));

                i++;
                defaultlistmodel.addElement(s7+" - "+s9);
            }

            aobj[0] = s2;
            aobj[1] = s4;
            aobj[2] = s3;
            aobj[3] = defaultlistmodel;
        }
        return s1;
    }

    public String Registrieren(String s, String s1)
    {
        String s2 = "";
        SSLSocket socket = null;
        PrintWriter printwriter = null;
        BufferedReader bufferedreader = null;
        try
        {
       	    
       	    
            socket = GenSocket();
            //socket = new Socket("twserver.org", 82);
            printwriter = new PrintWriter(socket.getOutputStream(), true);
            bufferedreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String s3 = new String("RK;");
            String s4 = new String(mPasswort);
            s3 = s3+mUsername+";"+mPasswort+";"+s+";"+s1+";";
            printwriter.println(s3);
                try
                {
                    s2 = bufferedreader.readLine();
                }
                catch(Exception exception2) { }
        }
        catch(Exception exception) { }
        try
        {
            printwriter.close();
            bufferedreader.close();
            socket.close();
        }
        catch(Exception exception1) { }
        return s2;
    }

  
    String mPattern;
    String MyPattern;
}
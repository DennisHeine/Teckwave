package com.teckwave.Protokoll;

import java.net.*;

import java.io.*;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class CSupport

{

	String Username, Passwort;

	public CSupport(String mUsername, String mPasswort)

	{

		Username=mUsername;

		 Passwort=mPasswort;

	}

	/*

		if(Token=="GW")//GetWarteschlangeX

			ret=GetWarteschlange();

		else if(Token=="GS")//GetSessionX

			ret=GetSession(IDSupporter);

		else if(Token=="DA")//DoAbschlussX

			ret=DoAbschluss(IDSupporter,inputLine);

		else if(Token=="EB")//EnterBugreportX

			ret=EnterBugReport(inputLine,IDSupporter);

		else if(Token=="GR")//GetRueckfrageX

			ret=GetRueckfrage(IDSupporter);

		else if(Token=="SA")//SetAvailableX

			ret=SetAvailable(IDSupporter,inputLine);

		else if(Token=="EA")//EnterAuftragX

			ret=EnterAuftrag(IDSupporter,inputLine);

		else if(Token=="ET")//EnterTextX

			ret=NewText(IDSupporter, inputLine);

		else if(Token=="GC")//GetChat

			ret=GetConversation(out,IDSupporter);

		else if(Token=="IS")

			ret=IsSupporterInConversation(IDSupporter);

	*/

	

	public String[] GetWarteschlange()

	{

		String[] Warteschlange=new String[2];	

		String[] ret=Send("SC;GW;"+Username+";");

		Warteschlange[0]=ret[1];

		Warteschlange[1]=ret[2];

		return Warteschlange;

	}

	

	public void CancelWait()

	{

		Send("SC;CW;"+Username+";");		

	}

	

	public boolean QuerySession()

	{

		String[] ret=Send("SC;QS;"+Username+";");

		System.out.println(ret[0]);

		if(ret[0].compareTo("WS")==0)

			return true;

		else

			return false;

	}

	

	public String[] OpenSession()

	{

		return Send("SC;NS;"+Username+";");

	}

	

	public void CancelSession(String IDSession)

	{

		Send("SC;CS;"+Username+";"+IDSession+";");

	}

	

	public String[] GetChat(String IDSession)

	{

		return Send("SC;GC;"+Username+";"+IDSession+";");

	}

	

	public void SendText(String Text, String IDSession)

	{

		Send("SC;WT;"+Username+";"+Text+";");

	}

	

	private String[] Send(String Befehl)

	{		

		String[] Werte=null;

		try

		{

		        SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
                        SSLSocket echoSocket = (SSLSocket) sslsocketfactory.createSocket("twhosting.de", 1000);
	            	//Socket echoSocket = new Socket("twhosting.de", 1000);        	    	

			PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);

			BufferedReader in=new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));

			String SendText=new String(Befehl);

			out.println(SendText);

			SendPW(echoSocket);

			try{Thread.sleep(500L);}catch(Exception e){}

			System.out.println("Warte auf Antwort");

			String ret=in.readLine();

			System.out.println("Antwort empfangen");

			System.out.println(ret);

			Werte=GetLine(ret);

		}catch(Exception e){System.out.println(e.toString());}

		return Werte;	

	}			

	

	public String[] GetLine(String inputLine)

	{

		String[] tmp=new String[255];

		int num=0;

		tmp[0]="";

		for(int i=0;i<inputLine.length();i++)

		{



			if(inputLine.charAt(i)==';')

			{

				System.out.println(tmp[num]);

				num++;

				tmp[num]="";

			}

			else

			{

				tmp[num]+=Character.toString(inputLine.charAt(i));

			}

		}

		num--;

		if(num<0)num=0;

		String[] ret=new String[num+1];

		for(int i=0;i<=num;i++)

			ret[i]=tmp[i];

		return ret;

	}

	

	

	private void SendPW(SSLSocket echoSocket)

	{

		String MyPattern=Util.DownloadURL("http://testsys.twhosting.de/pattern.php?Username="+Username+"&Passwort="+Passwort+"&Supporter=1");

		String mMyPattern="";

		for(int i = 1; i <= MyPattern.length() - 1; i++)

			mMyPattern = mMyPattern+Character.toString(MyPattern.charAt(i));

		MyPattern=mMyPattern;

		

		byte[] pwCyphered=Util.XorPattern(Util.StrToByteArr(Passwort),Util.StrToByteArr(MyPattern));		

		try{echoSocket.getOutputStream().write(pwCyphered);

		     echoSocket.getOutputStream().flush();

		}catch(Exception e){}

				

	}



}
import java.net.*;
import java.io.*;

public class CSupportKunde
{
	String Username, Passwort;
	CSupportKunde(Socket sock, String inputLine)
	{

		PrintWriter out=null;

		try{out = new PrintWriter(sock.getOutputStream(), true);}catch(Exception e){}

		System.out.println("CSupportKunde");
		String Token="";		
		Token=Token+Character.toString(inputLine.charAt(3))+Character.toString(inputLine.charAt(4));
		System.out.println(inputLine);
//		System.out.println(Token);

		Username=GetUsername(inputLine);
		Passwort=GetPasswort(sock);
		Users mUsers=new Users(Username);
		String IDUser=mUsers.GetUserID();
		String ret="";
		
		System.out.println(Token);

		if(Token.equals("QS"))//QuerySession
		{
			System.out.println("QuerySession erkannt");
			ret=QuerySession(IDUser);
		}
		else if(Token.equals("GW"))//GetWait
		{
			ret=GetWait(IDUser);
		}
		else if(Token.equals("CW"))//CancelWait
		{
			ret=CancelWait(IDUser);
		}
		else if(Token.equals("NS"))//New Session
		{
			ret=NewSession(IDUser);
		}
		else if(Token.equals("WT"))//WriteText
		{
			ret=NewText(IDUser,inputLine);
		}
		else if(Token.equals("ES"))//EndSession
		{
			ret=EndSession(IDUser);
		}
		else if(Token.equals("GC"))//GetConversation
		{
			ret=GetConversation(IDUser, inputLine);
		}
	/*	else if(Token.equals("OS"))//OpenSession
		{
			ret=OpenSession(IDUser);
		}
		*/
		try{Thread.sleep(500L);}catch(Exception e){};
		System.out.println(ret);
		out.println(ret);
	}
	
/*	private String OpenSession(String IDUser)
	{
		String IDSupporter=CSupport.GetFreeSupporter();
		CSupport.GenSession(IDUser,IDSupporter);
		String IDSession=CSupport.SessionGetIDSession(IDUser);
		return "OS;"+IDSession+";";
	}*/

	private String QuerySession(String IDUser)
	{
		System.out.println("QuerySession");
		String ret="NS;";
		if(CSupport.IsSupportAvailable())
		{
			if(CSupport.IsSupporterFree())
			{
//				String IDSupporter=CSupport.GetFreeSupporter();
//				CSupport.SetSupporterBusy(IDSupporter,true);
//				CSupport.GenSession(IDSupporter,IDUser);
				ret="WS;";
			}
			else
			{
				CSupport.InsertIntoWarteschlange(IDUser);
				ret="WS;";
			}
		}
		else
		{
			ret="NS;";
		}
		System.out.println(ret);
		return ret;
	}

	private String GetWait(String IDUser)
	{
		int[] WS=CSupport.GetWarteschlangenPos(IDUser);
		String ret="NW;";
		if(WS[0]==1)
		{
			if(CSupport.IsSupporterFree())
			{
				ret="SR;"; //Support ready
			}
			else
			{
				ret="WP;"+Integer.toString(WS[0])+";"+Integer.toString(WS[1])+";";
			}
		}
		else
		{
			ret="WP;"+Integer.toString(WS[0])+";"+Integer.toString(WS[1])+";";
		}
		return ret;
	}

	private String CancelWait(String IDUser)
	{
		CSupport.CancelWait(IDUser);
		return "";
	}


	private String NewSession(String IDUser)
	{
		String ret=CSupport.WSToSP(IDUser);
		return ret;
	}

	private String NewText(String IDUser, String inputLine)
	{
		String Text=GetSendText(inputLine);
		System.out.println("CSupportKunde.NewText nach GetSendText vor CSupport.InsertText");
		CSupport.InsertText(IDUser, false, Text);
		return "TI;";
	}

	private String EndSession(String IDUser)
	{
		String ID=CSupport.SessionGetIDSession(IDUser);
		CSupport.CloseSession(ID);
		return "SC;";
	}


	private String GetSendText(String inputLine)
	{
		System.out.println("CSupportKunde.GetSendText");
		int i=GetStart(inputLine);
		String Text="";
		do
		{
			Text=Text+Character.toString(inputLine.charAt(i));
			i++;
		}while(inputLine.charAt(i)!=';');
		return Text;
	}



	private String GetUsername(String inputLine)
	{
		String Username="";
		int i=6;
		do
		{
			Username=Username+Character.toString(inputLine.charAt(i));
			i++;
		}while(inputLine.charAt(i)!=';');
		return Username;		
	}

	private String GetPasswort(Socket sock)
	{
		//Passwort einlesen
		byte[] CypheredPWTmp=new byte[255];
		int x=0;
		try
		{
			System.out.println("Passwort einlesen");			
			x=sock.getInputStream().read(CypheredPWTmp);
			System.out.println("Passwort eingelesen");
		}						
		catch(Exception e)
		{
			System.out.println("Connection-Fehler beim Login");
		}
		byte[] CypheredPW=new byte[x];
		for(int i=0;i<x;i++)
			CypheredPW[i]=CypheredPWTmp[i];
	
		System.out.println(Integer.toString(CypheredPW.length));
		
		System.out.println("Crypted");
		System.out.println(Util.ByteToString(CypheredPW));
		String Passwort=Util.Decypher(CypheredPW, Username);
		return Passwort;
	}
	
	private String GetConversation(String IDUser, String inputLine)
	{
		int i=GetStart(inputLine);
		String IDSession="";
		do		
		{
			IDSession=IDSession+Character.toString(inputLine.charAt(i));
			i++;
		}while(inputLine.charAt(i)!=';');
		
		System.out.println(IDSession);
		
		String ret="CT;";
		String[] Conv=CSupport.GetConversation(IDSession);
		for(i=0;i<=Conv.length-1;i++)
		{
			ret+=Conv[i]+";";
		}
		return ret;
	}

	private int GetStart(String inputLine)
	{
		int i=6;
		do
		{		
			i++;
		}while(inputLine.charAt(i)!=';');
		i++;
		return i;
	}

}
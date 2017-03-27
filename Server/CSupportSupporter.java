import java.net.*;import java.io.*;
public class CSupportSupporter{	CSupportSupporter(Socket sock, String inputLine)	{		PrintWriter out=null;		String ret="";		try{out = new PrintWriter(sock.getOutputStream(), true);}catch(Exception e){}		String Token="";		
		for(int i=3;i<=4;i++)			Token+=Character.toString(inputLine.charAt(i));
		String Username=GetUsername(inputLine);		String IDSupporter=GetIDSupporter(Username);		String Passwort=GetPasswort(sock,Username);
		if(Token.equals("GW"))//GetWarteschlangeX			ret=GetWarteschlange();		else if(Token.equals("GS"))//GetSessionX			ret=GetSession(IDSupporter);		else if(Token.equals("DA"))//DoAbschlussX			ret=DoAbschluss(IDSupporter,inputLine);		else if(Token.equals("EB"))//EnterBugreportX			ret=EnterBugReport(inputLine,IDSupporter);		else if(Token.equals("GR"))//GetRueckfrageX			ret=GetRueckfrage(IDSupporter);		else if(Token.equals("SA"))//SetAvailableX			ret=SetAvailable(IDSupporter,inputLine);		else if(Token.equals("EA"))//EnterAuftragX			ret=EnterAuftrag(IDSupporter,inputLine);		else if(Token.equals("ET"))//EnterTextX			ret=NewText(IDSupporter, inputLine);		else if(Token.equals("GC"))//GetChat			ret=GetConversation(IDSupporter);		else if(Token.equals("IS"))			ret=IsSupporterInConversation(IDSupporter);				out.println(ret);    }
	private String IsSupporterInConversation(String IDSupporter)	{		if(CSupport.IsSupporterInConversation(IDSupporter))			return "IS;J;";		else			return "IS;N;";	}
	private String GetIDSupporter(String Username)	{	    return CSupport.GetIDSupporter(Username);	}
	
	private String EnterAuftrag(String IDSupporter, String inputLine)	{		int i=GetStart(inputLine);		String Kommentar="";		do		{			Kommentar+=Character.toString(inputLine.charAt(i));		}while(inputLine.charAt(i)!=';');
		i++;				CSupport.GenSession(IDSupporter,"");		String IDSession=CSupport.SessionGetIDUserBySupporter(IDSupporter);		CSupport.CloseSession(IDSession);		CSupport.SetKommentar(IDSession,Kommentar);
		return "OK";			}
	private String SetAvailable(String IDSupporter,String inputLine)			{			System.out.println("Available"+Character.toString(inputLine.charAt(GetStart(inputLine))));		boolean Available=inputLine.charAt(GetStart(inputLine))=='J'?true:false;		CSupport.SetSupporterOnline(IDSupporter,Available);		return "OK";	}
	private String GetRueckfrage(String IDSupporter)	{				String ret=CSupport.GetRueckfrage(IDSupporter);		return ret;	}	private String EnterBugReport(String inputLine,String IDSupporter)	{			String BugCount="";			int i=GetStart(inputLine);						do			{				BugCount+=Character.toString(inputLine.charAt(i));			}while(inputLine.charAt(i)!=';');			i++;			DoBugs(i,Integer.parseInt(BugCount),inputLine, IDSupporter,"");			return "OK;";	}
	private int GetStart(String inputLine)	{		int i=6;		do		{			i++;		}
		while(inputLine.charAt(i)!=';')		     i++;
		return i;
	}
	private String DoAbschluss(String IDSupporter, String inputLine)	{		int i=GetStart(inputLine);		String IDSession="";
		do		{			IDSession+=Character.toString(inputLine.charAt(i));			i++;		}while(inputLine.charAt(i)!=';');
		i++;		EvalAbschluss(inputLine,IDSupporter,IDSession,i);		return "OK";	}


		private void EvalAbschluss(String inputLine,String IDSupporter, String IDSession,int Start)		{			String Geloest="";			String Rueckfrage="";			String Kommentar="";			String NaechsterAuftrag="";			String BugCount="";			int i=Start;				        //Geloest			do			{				Geloest+=Character.toString(inputLine.charAt(i));			}while(inputLine.charAt(i)!=';');			i++;
			//Rueckfrage			do			{				Rueckfrage+=Character.toString(inputLine.charAt(i));						}while(inputLine.charAt(i)!=';');			i++;				//Kommentar			do			{				Kommentar+=Character.toString(inputLine.charAt(i));						}while(inputLine.charAt(i)!=';');
			i++;				//NaechsterAuftrag			do			{				NaechsterAuftrag+=Character.toString(inputLine.charAt(i));						}while(inputLine.charAt(i)!=';');			i++;									//BugCount			do			{				BugCount+=Character.toString(inputLine.charAt(i));						}while(inputLine.charAt(i)!=';');			i++;
			if(Geloest.equals("J"))CSupport.SetSolved(IDSession);
			CSupport.SetRueckfrage(IDSession,Rueckfrage.equals("J")?true:false);			CSupport.SetKommentar(IDSession,Kommentar);
			if(NaechsterAuftrag.equals("J"))				CSupport.SetSupporterOnline(IDSupporter,true);			else				CSupport.SetSupporterOnline(IDSupporter,false);
			DoBugs(i,Integer.parseInt(BugCount),inputLine,IDSupporter, IDSession);		}
		private void DoBugs(int Start,int BugCount,String inputLine, String IDSupporter, String IDSession)		{
			int CurrBug=1;			int i=Start;			String Ueberschrift="";			String Bereich="";			String Anwendung="";			String Prioritaet="";			String Beschreibung="";
			do     		{				do				{					Ueberschrift+=Character.toString(inputLine.charAt(i));								i++;						}while(inputLine.charAt(i)!=';'&&i<2048);											    do				{					Bereich+=Character.toString(inputLine.charAt(i));								i++;					}while(inputLine.charAt(i)!=';'&&i<2048);
				do				{					Anwendung+=Character.toString(inputLine.charAt(i));							    i++;		
				}while(inputLine.charAt(i)!=';'&&i<2048);
				do				{					Prioritaet+=Character.toString(inputLine.charAt(i));							    i++;											}while(inputLine.charAt(i)!=';'&&i<2048);				do
				{
					Beschreibung+=Character.toString(inputLine.charAt(i));							    i++;											
				}while(inputLine.charAt(i)!=';'&&i<2048);
				CSupport.EnterBug(IDSupporter,IDSession,Ueberschrift,Bereich,Anwendung,Prioritaet,Beschreibung);																																																							
			}while(CurrBug<=BugCount);		}	
	private String GetSession(String IDSupporter)	{		String ret="";		String IDSession=CSupport.SessionGetIDSessionBySupporter(IDSupporter);				if(!IDSession.equals(""))		{			String IDUser=CSupport.SessionGetIDUserBySession(IDSession);			ret="SD;"+IDUser+";"+IDSession+";";		}		else		{					ret="NS;";		}				return ret;
	}
	private String GetWarteschlange()	{		return "WC;"+CSupport.GetWarteschlangenCount()+";";	}
	private String GetUsername(String inputLine)	{		String Username="";		int i=6;
		do		{			Username=Username+Character.toString(inputLine.charAt(i));			i++;		}while(inputLine.charAt(i)!=';'&&i<2048);
		return Username;		
	}

	private String GetPasswort(Socket sock, String Username)
	{
		//Passwort einlesen		byte[] CypheredPWTmp=new byte[255];		int x=0;
		try		{			System.out.println("Passwort einlesen");						x=sock.getInputStream().read(CypheredPWTmp);			System.out.println("Passwort eingelesen");		}						
		catch(Exception e)		{			System.out.println("Connection-Fehler beim Login");		}
		byte[] CypheredPW=new byte[x];
		for(int i=0;i<x;i++)			CypheredPW[i]=CypheredPWTmp[i];		System.out.println(Integer.toString(CypheredPW.length));		System.out.println("Crypted");		System.out.println(Util.ByteToString(CypheredPW));		String Passwort=Util.DecypherSupporter(CypheredPW, Username);
		return Passwort;	}
	
	private String GetConversation(String IDSupporter)	{		String IDSession=CSupport.SessionGetIDSessionBySupporter(IDSupporter);		String[] Conv=null;		String SendText="CV;";		Conv=CSupport.GetConversation(IDSession);
		for(int i=0;i<Conv.length;i++)		{			SendText+=Conv[i]+";";		}		return SendText;	}
	
	private String GetSendText(String inputLine)	{		int i=6;		String Text="";		do		{			i++;		}while(inputLine.charAt(i)!=';'&&i<2048);		i++;				do		{			Text=Text+Character.toString(inputLine.charAt(i));			i++;		}while(inputLine.charAt(i)!=';'&&i<2048);		i++;
		return Text;
	}
	
	private String NewText(String IDSupporter, String inputLine)		{		int i=GetStart(inputLine);		String Text="";
		do		{			Text+=Character.toString(inputLine.charAt(i));			i++;		}while(inputLine.charAt(i)!=';');		i++;
		String IDUser=CSupport.SessionGetIDUserBySupporter(IDSupporter);		CSupport.InsertText(IDUser, true, Text);
		return "OK";	}	
}
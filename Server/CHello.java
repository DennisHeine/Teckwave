
import java.io.*;
import java.net.*;

class CHello
{
	Util mUtil;
	CHello()
	{
		mUtil=new Util();
	}

	public void DoHello(Socket sock, String inputLine)
	{

		String Username=new String("");
		String Passwort=new String("");		
		int i;
		i=3;
		while(inputLine.charAt(i)!=';')
		{
			Username=Username+java.lang.Character.toString(inputLine.charAt(i));
			i++;
		}
		i++;
		
		while(inputLine.charAt(i)!=';')
		{
			Passwort=Passwort+java.lang.Character.toString(inputLine.charAt(i));
			i++;
		}
		i++;		
		

		Users mUser=new Users(Username);
		/*
		System.out.println(mUser.GetUserID());		
		String Pattern=mUtil.GenPattern();*/
		String Connect=new String("CO");
		//Connect=new StringBuilder(Connect).append(";").append(Pattern).append(";").toString();

		//out.println(Connect);

		
		//^^ HE;Username einlesen
		
		String UserDaten="";
		
/*		byte[] Bla=mUtil.StrToByteArr(Pattern);
		
		try
		{
			//Pattern ausgeben
			System.out.println("Pattern ausgeben");
			sock.getOutputStream().write(Bla);
			sock.getOutputStream().flush();
		}
		catch(Exception e)
		{

			System.out.println("Connection-Fehler beim Login1");
		}	
			//String ClientConnect=null;
			byte[] CypheredTxt=null;
			
			//Einlesen der Userdaten
			try{
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			UserDaten=in.readLine();
			}
			catch(Exception e)
			{
				System.out.println("Fehler beim Einlesen der Userdaten");
			}
			
			try{Thread.currentThread().sleep(1000);}catch(Exception e){}
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
	for(i=0;i<x;i++)
		CypheredPW[i]=CypheredPWTmp[i];
	
		System.out.println(Integer.toString(CypheredPW.length));
		
		System.out.println("Crypted");
		System.out.println(mUtil.ByteToString(CypheredPW));
		String Passwort=mUtil.Decypher(CypheredPW, Username);*/
		
					try{
			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			UserDaten=in.readLine();
			}
			catch(Exception e)
			{
				System.out.println("Fehler beim Einlesen der Userdaten");
			}

		
		System.out.println("Entcrypted");
		System.out.println(Passwort);		
		String IP=new String("");
		String RouterIP=new String("");
		String Provider=new String("");

		String[] Daten=new String[3];
		ParseInput(UserDaten, Daten);
		PrintWriter out=null;
		//Status zurückgeben
		try{
		out = new PrintWriter(sock.getOutputStream(), true);
		}
		catch(Exception e){
			System.out.println("Fehler beim Ausgeben des Pattern");
		}
		String ret=new String(DoLogin(Username, Passwort,Daten[0],Daten[1],Daten[2]).toCharArray());
		try{Thread.currentThread().sleep(1000);}catch(Exception e){}
		out.println(ret);
		System.out.println("Auth ausgegeben");
		
		
	}
	
	private void ParseInput(String ClientConnect, String[] Daten)
	{
		int i=3;

		Daten[0]=new String("");
		Daten[1]=new String("");
		Daten[2]=new String("");
		System.out.println(ClientConnect);
		
		while(ClientConnect.charAt(i)!=';'&&i<2048)
		{
			Daten[0]=Daten[0]+java.lang.Character.toString(ClientConnect.charAt(i));
			i++;
		}
		i++;
		while(ClientConnect.charAt(i)!=';'&&i<2048)
		{
			Daten[1]=Daten[1]+java.lang.Character.toString(ClientConnect.charAt(i));
			i++;
		}
		i++;
		while(ClientConnect.charAt(i)!=';'&&i<2048)
		{
			Daten[2]=Daten[2]+java.lang.Character.toString(ClientConnect.charAt(i));
			i++;
		}
		i++;
	}
	
	private String DoLogin(String Username, String Passwort, String IP, String RouterIP, String Provider)
	{
		String Pattern="0";
		KundenKarten mKundenKarten=new KundenKarten(Username);
		UserKarten mUserKarten=new UserKarten();
		Users mUser=new Users(Username);		
		String ret=new String("");
		if(mKundenKarten.IsExisting(Passwort) && !mKundenKarten.IsBenutzt())
        	{
			mUser.InsertNew(Username, Passwort, "N");
	                mUserKarten.InsertNew(mUser.GetUserID(), mKundenKarten.GetIDKarte(),Username);
	                
	                mKundenKarten.SetAngemdeldet(true);
			mKundenKarten.SetBenutzt();
	        }			
		System.out.println(Passwort);		
		if(mUser.CheckUser(Passwort))
		{
			LogoutExisting(Username);

			if(!mUserKarten.CheckCard(mUser.GetUserID()))
			{
				ret=new String("TO");
				
			}
			else
			{
				NewSession mNewSession=new NewSession(Username);
				mNewSession.InsertNew(IP,RouterIP,Provider);
				Sessions mSessions=new Sessions(Username);
				mSessions.InsertSession(Username,IP,RouterIP,Provider,Pattern);
				System.out.println("Session eingetragen");
				ret=new String("AO");
			}
		}
		else
		{
			System.out.println("AF");
			ret= new String("AF");
		}		

		return ret;
	}
	
	public void LogoutExisting(String Username)
	{
		
		Sessions mSessions=new Sessions(Username);
		Socket echoSocket=null;
		String IP=null;
		String IPProvider=null;
		PrintWriter out=null;
		
		if(mSessions.CheckSession())
		{			
			IP=mSessions.GetIP();
			IPProvider=mSessions.GetIPProvider();
			String pass=mSessions.GetProvider();
			mSessions.DeleteSession();
			try
			{
				ClientConThread cct =new ClientConThread(IP,IPProvider,"D",pass);
				cct.run();
			}			
			catch(Exception e){}
		}		
	}

}

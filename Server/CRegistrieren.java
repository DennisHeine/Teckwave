
import java.io.*;
import java.net.*;


class CRegistrieren
{
	CRegistrieren()
        {
        }

        public void DoRegistrieren(Socket sock, String inputLine)
        {
		String ret="";
		String Code="";
		String KartenPW="";
		String Username=new String("");
		PrintWriter out = null;
		String Passwort="";
		try{out=new PrintWriter(sock.getOutputStream(), true);}catch(Exception e){}

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

		while(inputLine.charAt(i)!=';')
		{
			Code=Code+java.lang.Character.toString(inputLine.charAt(i));
			i++;
		}
		i++;

		while(inputLine.charAt(i)!=';')
		{
			KartenPW=KartenPW+java.lang.Character.toString(inputLine.charAt(i));
			i++;
		}
		i++;		
		

		Users mUser=new Users(Username);
/*
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

		String Passwort=Util.Decypher(CypheredPW, Username);
*/

		if(mUser.CheckUser(Passwort))
		{
			ret=KarteRegistrieren(Username,Code,KartenPW);
		}
		else
		{
			ret="AF";
		}            
		out.println(ret);

	}

	private String KarteRegistrieren(String Username, String Code, String KartenPW)
	{
		String ret="";

		KundenKarten mKundenKarten=new KundenKarten(Code);
                if(mKundenKarten.IsExisting(KartenPW))
		{
			if(!mKundenKarten.IsBenutzt())
			{
				Users mUsers=new Users(Username);
				Karten mKarten=new Karten(mKundenKarten.GetIDKarte());
				mKundenKarten.SetBenutzt();
				UserKarten mUserKarten=new UserKarten();				
				mUserKarten.InsertNew(mUsers.GetUserID(),mKundenKarten.GetIDKarte(),Code);
				DBQuery qry=new DBQuery();
				qry.DoQuery("UPDATE Gekauft SET IDUser='"+mUsers.GetUserID()+"' where Code='"+Code+"'");
				ret="OK";
			}
			else
			{
				ret="KB";
			}
		}
		else
		{
			ret="KU";
		}

		return ret;

	}
}
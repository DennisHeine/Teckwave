
import java.sql.*;
import java.net.*;

class CLogout
{


	CLogout(Socket socket,String inputLine)
	{
		String Username=null;
		
		String Pattern=null;
		String UserPattern=null;

		Username=GetUsername(inputLine);
		String Passwort=GetPasswort(inputLine);
		System.out.println("//");
		System.out.println(Username);

		Sessions mSessions=new Sessions(Username);
		Users mUser=new Users(Username);		
		
		byte[] PasswortCypheredTmp=new byte[255];
		byte[] PasswortCyphered=null;
		int x=0;
		//try{x=socket.getInputStream().read(PasswortCypheredTmp);
		//PasswortCyphered=new byte[x];
	//	for(int i=0;i<x;i++)
	//		PasswortCyphered[i]=PasswortCypheredTmp[i];
	//	}catch(Exception e){System.out.println(e.toString());}
		
		Util mUtil=new Util();		
//		String Passwort=mUtil.Decypher(PasswortCyphered, Username);
						
		if(mUser.CheckUser(Passwort))
			DoLogout(Username);
		else
			System.out.println("AF");
	}

	private String GetUsername(String inputLine)
	{
		int i=3;
		String Username=new String("");
		while(inputLine.charAt(i)!=';')
		{
			Username=Username+Character.toString(inputLine.charAt(i));
			i++;
		}
		return Username;
	}
	private String GetPasswort(String inputLine)

	{

		int i=3;

		String Passwort=new String("");

		while(inputLine.charAt(i)!=';')

		{
			

			i++;

		}
		i++;
		



		while(inputLine.charAt(i)!=';')

		{

			Passwort=Passwort+Character.toString(inputLine.charAt(i));

			i++;

		}		

		return Passwort;

	}

	
	private void DoLogout(String Username)
	{
		System.out.println(Username);
		DBQuery query=new DBQuery();

		query.DoQuery("INSERT INTO Logout (Username) VALUES('"+Username+"')");
	}

}	
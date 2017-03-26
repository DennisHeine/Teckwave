import java.net.*;
import java.io.*;

class Protokoll
{
	String mUsername,mPasswort;
	Protokoll(String username, String passwort)
	{
		mUsername=username;
		mPasswort=passwort;		
	}
	
	public boolean IsSupporterInConversation()
	{
		String[] Werte=Connect("IS;"+mUsername+";");
		if(Werte[1].equals("J"))
			return true;
		else
			return false;
	}
	
	public int GetWarteschlange()
	{
		int iret=0;
		Socket echoSocket = null;
        	PrintWriter out = null;		
		BufferedReader in= null;
		try
		{
	            	echoSocket = new Socket("twhosting.de", 1000);        	    	
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in=new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			String SendText=new String("SS;GW;"+mUsername+";");
			out.println(SendText);
			SendPW(echoSocket);
			String ret=in.readLine();
			String WS="";
			int i=3;
			do
			{
				WS+=Character.toString(ret.charAt(i));
				i++;
			}while(ret.charAt(i)!=';');
			iret= Integer.parseInt(WS);
		}
		catch(Exception e){}
		return iret;
	}
	
	public String[] GetChat()
	{
		String[] Werte=Connect("GC;"+mUsername+";");
		String[] tmp=new String[Werte.length-1];
		for(int i=1;i<Werte.length;i++)
			tmp[i-1]=Werte[i];
		return tmp;
	}
	
	public void EnterText(String Text)
	{
		Connect("ET;"+mUsername+";"+Text+";");
	}
	
	public void EnterAuftrag(String Kommentar)
	{
		Connect("EA;"+mUsername+";"+Kommentar+";");
	}
	
	public void SetAvailable(boolean Available)
	{
		Connect("SA;"+mUsername+";"+(Available?"J":"N")+";");		
	}
	
	public String[] Connect(String Befehl)
	{
		Befehl="SS;"+Befehl;
		String[] Werte=null;
		try
		{
	            	Socket echoSocket = new Socket("twhosting.de", 1000);        	    	
			PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
			BufferedReader in=new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			out.println(Befehl);
			SendPW(echoSocket);
			String ret=in.readLine();
			Werte=GetLine(ret);
		}catch(Exception e){}
		return Werte;	
	}
	
	public String[][] GetRueckfrage()
	{
			
			String[] Werte=Connect("GR;"+mUsername+";");
			
			String[][] tmp=new String[4][255];
			String[][] ret=null;
			int i=1;
			int ArrCnt=0;
			if(Werte!=null)
			{
				while(i<Werte.length&&i<255)
				{
					tmp[ArrCnt][0]=Werte[i];
					i++;
					tmp[ArrCnt][1]=Werte[i];
					i++;
					tmp[ArrCnt][2]=Werte[i];
					i++;
					tmp[ArrCnt][3]=Werte[i];
					i++;
					ArrCnt++;
				};
				
				ret=new String[ArrCnt][4];
				for(i=0;i<ArrCnt;i++)
					ret[i]=tmp[i];
			}
			else
			{
				ret=new String[1][1];
				ret[0][0]="";
			}
			return ret;
	}
	
	public String[] GetSession()
	{
	
		String[] ret=Connect("GS;"+mUsername+";");
		return ret;			
	}
	
	public void DoAbschluss(String IDSession,boolean Geloest, boolean Rueckfrage, String Kommentar, boolean NaechsterAuftrag, int BugCount, String[][] Bugs)
	{		

			String SendText=new String("DA;"+"IDSession"+";"+(Geloest?"J;":"N;")+(Rueckfrage?"J;":"N;")+Kommentar+";"+(NaechsterAuftrag?"J;":"N;")+Integer.valueOf(BugCount)+";");
			for(int i=1;i<=BugCount;i++)
			{
				SendText+=Bugs[i-1][0]+";"+Bugs[i-1][1]+";"+Bugs[i-1][2]+";"+Bugs[i-1][3]+";"+Bugs[i-1][4]+";";
			}
			String[] ret=Connect(SendText);

			
	}
	
	public void EnterBugreport(String IDSupporter, String[][] Bugs)
	{
		
		String SendText="EB;"+mUsername+";";
		for(int i=1;i<=Bugs.length;i++)
		{
			SendText+=Bugs[i-1][0]+";"+Bugs[i-1][1]+";"+Bugs[i-1][2]+";"+Bugs[i-1][3]+";"+Bugs[i-1][4]+";";
		}
		Connect(SendText);
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
				num++;
				tmp[num]="";
			}
			else
			{			
				tmp[num]+=Character.toString(inputLine.charAt(i));
			}
		}
		num--;
		String[] ret=new String[num+1];
		for(int i=0;i<=num;i++)
			ret[i]=tmp[i];
		return ret;
	}

	public void SendPW(Socket echoSocket)
	{
		String MyPattern=Util.DownloadURL(new StringBuilder("http://testsys.twhosting.de/pattern.php?Username=").append(mUsername).append("&Passwort=").append(mPasswort).append("&Supporter=1").toString());
		String mMyPattern="";
		for(int i = 1; i <= MyPattern.length() - 1; i++)
			mMyPattern = new StringBuilder(mMyPattern).append(Character.toString(MyPattern.charAt(i))).toString();
		MyPattern=mMyPattern;
		
		byte[] pwCyphered=Util.XorPattern(Util.StrToByteArr(mPasswort),Util.StrToByteArr(MyPattern));		
		try{echoSocket.getOutputStream().write(pwCyphered);
		     echoSocket.getOutputStream().flush();
		}catch(Exception e){}
				
	}
}
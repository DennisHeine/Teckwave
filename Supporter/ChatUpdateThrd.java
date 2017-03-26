import java.util.*;

class ChatUpdateThrd extends Thread
{
	SupportChat wnd=null;
	Protokoll mProtokoll=null;
	String mUsername;
	String mPasswort;
	SupportAbschluss mAbschluss;
	String mIDSession;
	
	ChatUpdateThrd(SupportChat mwnd, String Username, String Passwort)
	{
		wnd=mwnd;
		mPasswort=Passwort;
		mUsername=Username;
		mProtokoll=new Protokoll(Username, Passwort);
	}
	
	
	public void SetIDSession(String Session)
	{
		mIDSession=Session;
	}

	public void run()
	{		
		String[] Chat=null;
		String Text="";
		Chat=mProtokoll.GetChat();
		int OldPos=0;
		do
		{	
			String[] SessionData=mProtokoll.GetSession();
			if(SessionData[0]!="NS")
			{
				String IDSession=SessionData[0];
				mIDSession=IDSession;
				Chat=mProtokoll.GetChat();
				for(int i=OldPos;i<Chat.length;i++)
				{
					Text+=Chat[i]+"\n";
				}	
				wnd.Append(Text);
				OldPos=Chat.length;				
			}
			else
			{
				if(wnd.isVisible())
				{
					mAbschluss=new SupportAbschluss(mUsername, mPasswort, mIDSession);
					mIDSession="";
					mAbschluss.show();
				}

				wnd.Clear();
			}
			Text="";
			
			try{this.sleep(5000L);}catch(Exception e){}
		}while(true);		
	}
}
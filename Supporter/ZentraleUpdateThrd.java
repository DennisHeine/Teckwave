import javax.swing.*;
import javax.swing.table.*;
class ZentraleUpdateThrd extends Thread
{
	SupportZentrale wnd=null;
	Protokoll mProtokoll=null;
	String mIDSession;
	String mUsername;
	String mPasswort;
	
	ZentraleUpdateThrd(SupportZentrale mwnd, String Username, String Passwort)
	{
		wnd=mwnd;
		mProtokoll=new Protokoll(Username, Passwort);
		mPasswort=Passwort;
		mUsername=Username;
	}


	public void run()
	{
		int Warteschlange=0;
		Object[] row=null;		
		DefaultTableModel mdl=null;
		do
		{
			Warteschlange=mProtokoll.GetWarteschlange();
			String[] SessionData=mProtokoll.GetSession();
			String Rueckfrage[][]=mProtokoll.GetRueckfrage();
			System.out.println(SessionData[0]);
			
			if(!SessionData[0].equals("NS"))
			{			
				mIDSession=SessionData[1];
					wnd.ShowChat(true);

			
			}
			else
			{
					wnd.ShowChat(false);
			}
			
			wnd.SetRueckfrage(Rueckfrage);							

			wnd.EnableCheckbox(SessionData[0].equals("NS"));
			
			try{this.sleep(15000L);}catch(Exception e){}
		}while(true);
	}
}
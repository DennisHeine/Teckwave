import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import java.net.*;

class CServer
{


	CServer()
	{
		CServerThread Thread=new CServerThread();

		while(true);
	}

	private class CServerThread
	{
		CServerThread()
		{
			
			SSLServerSocket serverSocket=null;
			try 
			{
		            SSLServerSocketFactory sslserversocketfactory =(SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
		            serverSocket = (SSLServerSocket) sslserversocketfactory.createServerSocket(82);			
    			    //serverSocket = new ServerSocket(82);
			} 
			catch (Exception e) 
			{
    				System.exit(-1);
			}
			//Socket clientSocket = null;
			SSLSocket clientSocket=null;
			CTalkingThread TalkingThread;
			do
			{
				try 
				{
					if(clientSocket!=null)
						clientSocket.close();
    			                clientSocket = (SSLSocket) serverSocket.accept();
    					//clientSocket = serverSocket.accept();
					TalkingThread=new CTalkingThread(clientSocket);
					TalkingThread.start();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			while(true);

			
		}

	}
}
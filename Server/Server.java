


public class Server
{

    public Server()
    {
    }

    public static String Server="127.0.0.1";
    public static String User="root";
    public static String Passwort="";
    
    public static String[] getServerData()
    {
    	String[] ret;
    	
    	if(Server!="")
    	{
    		ret=new String[3];
    		ret[0]=Server;
    		ret[1]=User;
    		ret[2]=Passwort;
    	}
    	else
    	{
    		ret=new String[1];
    		ret[0]="";
    	}
    	return ret;
    }
    
    public static void main(String args[])
    {
    Server="";
    User="";
    Passwort="";
    
    if(args.length==3)
    {	
    	Server=args[0];
    	User=args[1];
    	Passwort=args[2];
    }
    	   
	twserver mtwserver=new twserver();
	mtwserver.start();
	CServer server=new CServer();

/*	String str=new String("azAZ19");
	String out=new String("");
	for(int i=0;i<=str.length()-1;i++)
	{
		out=new StringBuilder(out).append(Integer.toString(str.charAt(i))).append(" ").toString();
	}
	System.out.println(out);*/
    }
}



import java.sql.ResultSet;

public class KundenKarten extends DBQuery
{
    String username;
    KundenKarten(String musername)
    {
        username = musername;
    }

    boolean IsExisting(String Passwort)
    {
        boolean ret;
        try
        {
            ResultSet rs = DoQuery("SELECT * FROM KundenKarten WHERE Code='"+username+"' AND Passwort='"+Passwort+"'");
            if(rs.first())
                ret = true;
            else
                ret = false;
        }
        catch(Exception e)
        {
            ret = false;
        }
        return ret;
    }
    

    boolean IsAngemeldet()
    {
	try
	{
        	ResultSet rs = DoQuery("SELECT * FROM KundenKarten WHERE Code='"+username+"'");
	        rs.first();
		if(rs.getString("Angemeldet").compareTo("J")==0)
			return true;
		else
			return false;
	}
	catch(Exception e)
	{
		return false;
	}
		
    }

    void SetAngemdeldet(boolean angemeldet)
    {
        DoQuery("UPDATE KundenKarten SET Angemeldet='"+(angemeldet ? "J" : "N")+"' WHERE Code='"+username+"'");
    }

    void SetBenutzt()
    {
	try
	{
        	DoQuery("UPDATE KundenKarten SET Benutzt='J' WHERE Code='"+username+"'");
	}
	catch(Exception e)
	{
	}
    }

    boolean IsBenutzt()
    {
	try
	{
	        ResultSet rs = DoQuery("SELECT * FROM KundenKarten WHERE Code='"+username+"'");
        	rs.first();
		if(rs.getString("Benutzt").compareTo("J")==0)
			return true;
		else
			return false;
	}
	catch(Exception e)
	{
		return false;
	}

    }

    public String GetIDKarte()
    {
	try
	{
	        ResultSet rs = DoQuery("SELECT * FROM KundenKarten WHERE Code='"+username+"'");
        	rs.first();
		return rs.getString("IDKarten");	
	}
	catch(Exception e)
	{
		return "0";
	}
    }

}

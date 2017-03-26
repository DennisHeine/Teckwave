

import java.sql.ResultSet;

public class Betreiber extends DBQuery
{

    Betreiber(String mID)
    {
        ID = mID;
    }

    String getPassword()
    {
	try
	{
        	ResultSet rs = DoQuery("SELECT * FROM Betreiber WHERE ID='"+ID+"'");
        	rs.first();
        	return rs.getString("Passwort");
	}
	catch(Exception e)
	{
        
        	return "";
	}
    }

    String GetMenge()
    {
        ResultSet rs = DoQuery("SELECT * FROM Karten WHERE ID='"+ID+"'");
        try
        {
            rs.first();
            return rs.getString(4);
        }
        catch(Exception e)
        {
            return "0";
        }
    }

    String ID;
}

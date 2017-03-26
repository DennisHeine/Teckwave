

import java.sql.ResultSet;

public class Karten extends DBQuery
{

    Karten(String mID)
    {
        ID = mID;
    }

    boolean IsFlatrate()
    {
	try
	{
        	ResultSet rs = DoQuery("SELECT * FROM Karten WHERE ID='"+ID+"'");
        	rs.first();
        	return rs.getString(4) == "7" || rs.getString(4) == "15";
	}
	catch(Exception e)
	{
        
        	return false;
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

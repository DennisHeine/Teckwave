import java.sql.*;
class Supporter
{
	String Username;
	Supporter(String mUsername)
	{
		Username=mUsername;
	}

	public String GetPattern()
	{
		String ret="";
		DBQuery qry=new DBQuery();
		try
		{
			ResultSet rs=qry.DoQuery("SELECT * FROM Supporter WHERE Username='"+Username+"'");
			ret=rs.getString("Pattern");
		}
		catch(Exception e)
		{
		}
		return ret;
	}
}